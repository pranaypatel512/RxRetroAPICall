package com.pranay.rxretroapicall.api;

/**
 * Created by Pranay.
 */

public interface RxJava2APICallback<P> {
    void onSuccess(P t);

    void onFailed(Throwable throwable);
}
