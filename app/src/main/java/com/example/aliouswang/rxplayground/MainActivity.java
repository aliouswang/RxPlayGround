package com.example.aliouswang.rxplayground;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "rxjava";

    Button btn_test_subscribe;
    Button btn_test_observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_test_subscribe = findViewById(R.id.btn_test_subscribe);
        btn_test_observer = findViewById(R.id.btn_test_observer);

        btn_test_subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxDemo.subscribe_observer_map();
//                Observable.create(new ObservableOnSubscribe<String>() {
//                    @Override
//                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                        Log.e(TAG, "0:" + Thread.currentThread().getName());
//                        emitter.onNext("1");
//                        emitter.onNext("2");
//                        emitter.onNext("3");
//                    }
//                }).observeOn(Schedulers.computation()).subscribeOn(Schedulers.newThread())
//                        .map(new Function<String, String>() {
//                            @Override
//                            public String apply(String s) throws Exception {
//                                Log.e(TAG, "1:" + Thread.currentThread().getName());
//                                return s + s;
//                            }
//                        }).subscribeOn(Schedulers.newThread())
//                        .observeOn(Schedulers.io())
////                        .observeOn(Schedulers.newThread())
//                        .subscribe(new Observer<String>() {
//                            @Override
//                            public void onSubscribe(Disposable d) {
//
//                            }
//
//                            @Override
//                            public void onNext(String s) {
//                                Log.e(TAG, "observer:" + Thread.currentThread().getName());
//                                Log.e(TAG, s);
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//
//                            }
//
//                            @Override
//                            public void onComplete() {
//
//                            }
//                        });
            }
        });
    }
}
