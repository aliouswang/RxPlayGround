package com.example.aliouswang.rxjava;

import com.example.aliouswang.rxjava.annotations.NonNull;
import com.example.aliouswang.rxjava.disposables.Disposable;

/**
 * Created by aliouswang on 2018/7/25.
 */

public interface ObservableEmitter<T> extends Emitter<T> {

    void setDisposable(@NonNull Disposable d);

//    void setCancellable(@NonNull)

    boolean isDisposed();

    ObservableEmitter<T> serialize();

    boolean tryOnError(@NonNull Throwable t);

}
