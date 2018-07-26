package com.example.aliouswang.rxjava.disposables;

import com.example.aliouswang.rxjava.ObjectHelper;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by aliouswang on 2018/7/25.
 */

public enum DisposableHelper implements Disposable{

    DISPOSED;

    public static boolean setOnce(AtomicReference<Disposable> field, Disposable d) {
        ObjectHelper.requireNonNull(d, "d is null");
        if (!field.compareAndSet(null, d)) {
            d.dispose();
            if (field.get() != DISPOSED) {

            }
            return false;
        }
        return true;
    }

    public static boolean dispose(AtomicReference<Disposable> field) {
        Disposable current = field.get();
        Disposable d = DISPOSED;
        if (current != d) {
            current = field.getAndSet(d);
            if (current != d) {
                if (current != null) {
                    current.dispose();
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean isDisposed() {
        return false;
    }

    public static boolean isDisposed(Disposable d) {
        return d == DISPOSED;
    }
}
