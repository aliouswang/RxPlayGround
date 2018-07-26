package com.example.aliouswang.rxjava.internal.operators.observable;

import com.example.aliouswang.rxjava.Observable;
import com.example.aliouswang.rxjava.ObservableEmitter;
import com.example.aliouswang.rxjava.ObservableOnSubcribe;
import com.example.aliouswang.rxjava.Observer;
import com.example.aliouswang.rxjava.disposables.Disposable;
import com.example.aliouswang.rxjava.disposables.DisposableHelper;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by aliouswang on 2018/7/25.
 */

public final class ObservableCreate<T> extends Observable<T> {
    final ObservableOnSubcribe<T> source;

    public ObservableCreate(ObservableOnSubcribe<T> source) {
        this.source = source;
    }

    @Override
    protected void subscribeActual(Observer<? super T> observer) {
        CreateEmitter<T> parent = new CreateEmitter<>(observer);
        observer.onSubscribe(parent);

        try {
            source.subscribe(parent);
        } catch (Throwable e) {
            parent.onError(e);
        }
    }

    static final class CreateEmitter<T> extends AtomicReference<Disposable>
        implements ObservableEmitter<T>, Disposable {

        final Observer<? super T> observer;

        CreateEmitter(Observer<? super T> observer) {
            this.observer = observer;
        }

        @Override
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override
        public void onNext(T value) {
            if (value == null) {
                onError(new NullPointerException("onNext called with null. no allowed in rxjava 2.x"));
                return;
            }
            if (!isDisposed()) {
                observer.onNext(value);
            }
        }

        @Override
        public void onError(Throwable error) {
            tryOnError(error);
        }

        @Override
        public void onComplete() {
            if (!isDisposed()) {
                try {
                    observer.onComplete();
                } finally {
                    dispose();
                }
            }
        }

        @Override
        public void setDisposable(Disposable d) {

        }



        @Override
        public ObservableEmitter<T> serialize() {
            return null;
        }

        @Override
        public boolean tryOnError(Throwable t) {
            if (t == null) {
                t = new NullPointerException("onError called with null");
            }
            if (!isDisposed()) {
                try {
                    observer.onError(t);
                }catch (Exception e) {
                    dispose();
                }
                return true;
            }
            return false;
        }
    }

}
