package com.example.aliouswang.rxjava.observers;

import com.example.aliouswang.rxjava.Observer;
import com.example.aliouswang.rxjava.disposables.Disposable;
import com.example.aliouswang.rxjava.disposables.DisposableHelper;
import com.example.aliouswang.rxjava.functions.Action;
import com.example.aliouswang.rxjava.functions.Consumer;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by aliouswang on 2018/7/25.
 */

public final class LambdaObserver<T> extends AtomicReference<Disposable>
        implements Observer<T>, Disposable{

    private static final long serialVersionUID = -7251123623727029452L;
    final Consumer<? super T> onNext;
    final Consumer<? super Throwable> onError;
    final Action onComplete;
    final Consumer<? super Disposable> onSubscribe;

    public LambdaObserver(Consumer<? super T> onNext, Consumer<? super Throwable> onError,
                          Action onComplete,
                          Consumer<? super Disposable> onSubscribe) {
        super();
        this.onNext = onNext;
        this.onError = onError;
        this.onComplete = onComplete;
        this.onSubscribe = onSubscribe;
    }

    @Override
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override
    public boolean isDispose() {
        return get() == DisposableHelper.DISPOSED;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (DisposableHelper.setOnce(this, d)) {
            try {
                onSubscribe.accept(this);
            } catch (Throwable ex) {
                d.dispose();
                onError(ex);
            }
        }
    }

    @Override
    public void onNext(T t) {
        if (!isDispose()) {
            try {
                onNext.accept(t);
            } catch (Throwable e) {
                get().dispose();
                onError(e);
            }
        }
    }

    @Override
    public void onComplete() {
        if (!isDispose()) {
            lazySet(DisposableHelper.DISPOSED);
            try {
                onComplete.run();
            }catch (Throwable e) {
                onError(e);
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        if (!isDispose()) {
            lazySet(DisposableHelper.DISPOSED);
            try {
                onError.accept(e);
            } catch (Throwable t) {

            }
        }
    }
}
