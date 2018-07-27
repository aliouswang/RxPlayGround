package com.example.aliouswang.rxplayground;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by aliouswang on 2018/7/27.
 */

public class RxDemo {

    public static void subscribe_observer_map() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                L.e("subscribe : ");
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        L.e("map apply");
                        return s + s;
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        L.e("observer onSubscribe");
                    }

                    @Override
                    public void onNext(String s) {
                        L.e("observer onNext : " + " vaule is " + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        L.e("observer onError");
                    }

                    @Override
                    public void onComplete() {
                        L.e("observer onComplete");
                    }
                });
    }

}
