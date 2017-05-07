package com.pranay.rxretroapicall.api.rx;

/**
 * Created by Pranay.
 *
 * Common Callback to call API request response.
 * @param <P> : response Type
 */
public interface RxAPICallback<P> {
    void onSuccess(P t);

    void onFailed(Throwable throwable);
}
