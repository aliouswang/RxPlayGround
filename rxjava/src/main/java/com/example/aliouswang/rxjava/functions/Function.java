package com.example.aliouswang.rxjava.functions;

import com.example.aliouswang.rxjava.annotations.NonNull;

/**
 * Created by aliouswang on 2018/7/25.
 */

public interface Function<T, R> {

    R apply(@NonNull T t) throws Exception;

}
