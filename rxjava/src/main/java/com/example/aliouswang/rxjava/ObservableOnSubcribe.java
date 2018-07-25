package com.example.aliouswang.rxjava;

import com.example.aliouswang.rxjava.annotations.NonNull;

/**
 * Created by aliouswang on 2018/7/25.
 */

public interface ObservableOnSubcribe<T> {

    void subscribe(@NonNull ObservableEmitter<T> emitter) throws Exception;

}
