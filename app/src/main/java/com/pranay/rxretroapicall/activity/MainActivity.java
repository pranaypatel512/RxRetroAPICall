package com.pranay.rxretroapicall.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;
import android.widget.Toast;

import com.pranay.rxretroapicall.R;
import com.pranay.rxretroapicall.api.ApiProduction;
import com.pranay.rxretroapicall.api.response.StackOverFlowUserBadgesResponse;
import com.pranay.rxretroapicall.api.rx.RxAPICallHelper;
import com.pranay.rxretroapicall.api.rx.RxAPICallback;
import com.pranay.rxretroapicall.api.service.StackOverFlowUserBadgesService;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvBronzeBadge)
    TextView tvBronzeBadge;
    @BindView(R.id.tvSilverBadge)
    TextView tvSilverBadge;
    @BindView(R.id.tvGoldBadge)
    TextView tvGoldBadge;
    private Integer bronze_count = 0, silver_count = 0, gold_count = 0;
    private ProgressDialog mProgressDialog;
    private Disposable disposable = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.str_progress_message));
        if (isInternetAvailable()) {
            getUserBadgeDetails();
        } else {
            showToast(getString(R.string.no_internet_connection));
        }

    }

    /**
     * Call API to get stackoverflow User badge details
     */
    private void getUserBadgeDetails() {
        mProgressDialog.show();
        StackOverFlowUserBadgesService badgesService = ApiProduction.getInstance(this).provideService(StackOverFlowUserBadgesService.class);
        Observable<StackOverFlowUserBadgesResponse> responseObservable = badgesService.getBadges("2949612");
        disposable = RxAPICallHelper.call(responseObservable, new RxAPICallback<StackOverFlowUserBadgesResponse>() {
            @Override
            public void onSuccess(StackOverFlowUserBadgesResponse badgesResponse) {
                mProgressDialog.dismiss();
                showToast(badgesResponse.getItems().size() > 0 ? "Success" : "Failed");
                if (badgesResponse.getItems().size() > 0) {
                    calculateBadges(badgesResponse);
                }
                disposeCall();
            }

            @Override
            public void onFailed(Throwable throwable) {
                disposeCall();
                mProgressDialog.dismiss();
                showToast(throwable.getLocalizedMessage());
            }
        });
    }

    /**
     * After Called API, dispose call after success or failure.
     */
    private void disposeCall() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    /**
     * Calculate user badges as per aPI response
     *
     * @param badgesResponse : use badge response
     */
    private void calculateBadges(StackOverFlowUserBadgesResponse badgesResponse) {
        for (StackOverFlowUserBadgesResponse.Item badge : badgesResponse.getItems()) {
            switch (badge.getRank()) {
                case "bronze":
                    bronze_count = bronze_count + badge.getAwardCount();
                    break;
                case "silver":
                    silver_count = silver_count + badge.getAwardCount();
                    break;
                case "gold":
                    gold_count = gold_count + badge.getAwardCount();
                    break;
            }
        }


        // Set Badge Counts in TextView
        tvBronzeBadge.setText(getStringFromHtmlText(getString(R.string.str_bronze_badge_text, String.valueOf(bronze_count))));
        tvSilverBadge.setText(getStringFromHtmlText(getString(R.string.str_silver_badge_text, String.valueOf(silver_count))));
        tvGoldBadge.setText(getStringFromHtmlText(getString(R.string.str_gold_badge_text, String.valueOf(gold_count))));

    }

    /**
     * Show toast message
     *
     * @param message : toast message string
     */
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    /**
     * Check internet connection is available
     *
     * @return : true if internet connection is available else false
     */
    public boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting()
                && cm.getActiveNetworkInfo().isAvailable()
                && cm.getActiveNetworkInfo().isConnected();
    }

    public Spanned getStringFromHtmlText(String baseString) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(baseString, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(baseString);
        }
    }
}
