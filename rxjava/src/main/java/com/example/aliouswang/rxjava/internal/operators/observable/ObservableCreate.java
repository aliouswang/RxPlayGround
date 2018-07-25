package com.example.aliouswang.rxjava.internal.operators.observable;

import com.example.aliouswang.rxjava.Observable;
import com.example.aliouswang.rxjava.ObservableEmitter;
import com.example.aliouswang.rxjava.ObservableOnSubcribe;
import com.example.aliouswang.rxjava.Observer;
import com.example.aliouswang.rxjava.disposables.Disposable;

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

        }

        @Override
        public boolean isDispose() {
            return false;
        }

        @Override
        public void onNext(T value) {

        }

        @Override
        public void onError(Throwable error) {

        }

        @Override
        public void onComplete() {

        }

        @Override
        public void setDisposable(Disposable d) {

        }

        @Override
        public boolean isDisposed() {
            return false;
        }

        @Override
        public ObservableEmitter<T> serialize() {
            return null;
        }

        @Override
        public boolean tryOnError(Throwable t) {
            return false;
        }
    }

}
