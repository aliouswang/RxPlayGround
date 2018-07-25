package com.example.aliouswang.rxjava;

import com.example.aliouswang.rxjava.annotations.NonNull;
import com.example.aliouswang.rxjava.disposables.Disposable;

/**
 * Created by aliouswang on 2018/7/24.
 */

public interface Observer<T> {

    void onSubscribe(@NonNull Disposable d);

    void onNext(@NonNull T t);

    void onComplete();

    void onError(@NonNull Throwable e);

}
