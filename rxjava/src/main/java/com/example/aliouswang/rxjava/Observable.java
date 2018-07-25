package com.example.aliouswang.rxjava;

import com.example.aliouswang.rxjava.disposables.Disposable;
import com.example.aliouswang.rxjava.functions.Action;
import com.example.aliouswang.rxjava.functions.Consumer;
import com.example.aliouswang.rxjava.internal.operators.observable.ObservableCreate;
import com.example.aliouswang.rxjava.observers.LambdaObserver;
import com.example.aliouswang.rxjava.plugins.RxJavaPlugins;

/**
 * Created by aliouswang on 2018/7/25.
 */

public abstract class Observable<T> implements ObservableSource<T>{

    public static <T> Observable<T> create(ObservableOnSubcribe<T> source) {
        ObjectHelper.requireNonNull(source, "source is null");
        return RxJavaPlugins.onAssembly(new ObservableCreate<T>(source));
    }

    public final Disposable subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError,
                                      Action onComplete, Consumer<? super Disposable> onSubscribe) {
        ObjectHelper.requireNonNull(onNext, "onNext is null");
        ObjectHelper.requireNonNull(onError, "onError is null");
        ObjectHelper.requireNonNull(onComplete, "onComplete is null");
        ObjectHelper.requireNonNull(onSubscribe, "onSubscribe is null");

        LambdaObserver<T> ls = new LambdaObserver<>(onNext, onError, onComplete, onSubscribe);
        subscribe(ls);
        return ls;
    }

    public final void subscribe(Observer<? super T> observer) {
        ObjectHelper.requireNonNull(observer, "observer is null");
        try {
            observer = RxJavaPlugins.onSubscribe(this, observer);
            ObjectHelper.requireNonNull(observer, "Plugin returned null Observer");
            subscribeActual(observer);
        } catch (NullPointerException e) {
            throw(e);
        }
    }

    protected abstract void subscribeActual(Observer<? super T> observer);

}
