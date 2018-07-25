package com.example.aliouswang.rxjava;

import com.example.aliouswang.rxjava.annotations.NonNull;

/**
 * Created by aliouswang on 2018/7/25.
 */

public interface ObservableSource<T> {

    void subscribe(@NonNull Observer<? super T> observer);

}
