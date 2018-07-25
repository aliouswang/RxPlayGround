package com.example.aliouswang.rxjava.functions;

import com.example.aliouswang.rxjava.annotations.NonNull;

/**
 * Created by aliouswang on 2018/7/25.
 */

public interface BiFunction<T1, T2, R> {

    @NonNull
    R apply(@NonNull T1 t1, @NonNull T2 t2) throws Exception;

}
