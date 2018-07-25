package com.example.aliouswang.rxjava;

import com.example.aliouswang.rxjava.disposables.Disposable;
import com.example.aliouswang.rxjava.functions.Action;
import com.example.aliouswang.rxjava.functions.Consumer;

/**
 * Created by aliouswang on 2018/7/25.
 */

public abstract class Observable<T> implements ObservableSource<T>{

    public final Disposable subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError,
                                      Action onComplete, Consumer<? super Disposable> onSubscribe) {
        ObjectHelper.requireNonNull(onNext, "onNext is null");
        ObjectHelper.requireNonNull(onError, "onError is null");
        ObjectHelper.requireNonNull(onComplete, "onComplete is null");
        ObjectHelper.requireNonNull(onSubscribe, "onSubscribe is null");


    }

}
