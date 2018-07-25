package com.example.aliouswang.rxjava;

import com.example.aliouswang.rxjava.annotations.NonNull;

/**
 * Created by aliouswang on 2018/7/25.
 */

public interface Emitter<T> {

    void onNext(@NonNull T value);

    void onError(@NonNull Throwable error);

    void onComplete();

}
