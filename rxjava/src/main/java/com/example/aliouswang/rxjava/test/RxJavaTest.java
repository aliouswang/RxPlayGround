package com.example.aliouswang.rxjava.test;

import com.example.aliouswang.rxjava.Observable;
import com.example.aliouswang.rxjava.ObservableEmitter;
import com.example.aliouswang.rxjava.ObservableOnSubcribe;
import com.example.aliouswang.rxjava.Observer;
import com.example.aliouswang.rxjava.disposables.Disposable;

/**
 * Created by aliouswang on 2018/7/26.
 */

public class RxJavaTest {

    public static void main(String[] args) {
        Observable.create(new ObservableOnSubcribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("Hello");
                emitter.onNext("rxjava!!!");
                emitter.onComplete();
                emitter.onNext("emitter after dispose");
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onComplete() {
                System.out.println("on complete");
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

}
