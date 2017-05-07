package com.pranay.rxretroapicall.api;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Pranay.
 */

public class RxJava2APICallHelper {
    public RxJava2APICallHelper() {
    }

    public static <T> Disposable call(Observable<T> observable, final RxJava2APICallback<T> rxJava2APICallback) {
        if (observable == null) {
            throw new IllegalArgumentException("Observable must not be null.");
        }

        if (rxJava2APICallback == null) {
            throw new IllegalArgumentException("Callback must not be null.");
        }

        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<T>() {
                    @Override
                    public void accept(@NonNull T t) throws Exception {
                        rxJava2APICallback.onSuccess(t);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        if (throwable != null) {
                            rxJava2APICallback.onFailed(throwable);
                        } else {
                            rxJava2APICallback.onFailed(new Exception("Error: Something went wrong in api call."));
                        }
                    }
                });

    }
}
