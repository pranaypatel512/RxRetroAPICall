package com.pranay.rxretroapicall.api.rx;

/**
 * Created by Pranay.
 */

public interface RxAPICallback<P> {
    void onSuccess(P t);

    void onFailed(Throwable throwable);
}
