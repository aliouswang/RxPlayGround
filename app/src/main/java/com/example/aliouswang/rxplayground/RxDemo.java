package com.example.aliouswang.rxplayground;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by aliouswang on 2018/7/27.
 */

public class RxDemo {

    public static void subscribe_observer_map_subscribe_observer_map() {
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
                        L.e("map 1 apply");
                        return s + s;
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        L.e("map 2 apply");
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

    public static void observable_just() {
        Observable.just("Hello rxJava")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("rxjava", s);
                    }
                });

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("Hello rxJava");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("rxjava", s);
                    }
                });
    }

    public static void test_download() {
//        ApiService.getAdvImageUrl()
//        Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                emitter.onNext("");
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .map(new Function<String, String>() {
//                    @Override
//                    public String apply(String s) throws Exception {
//                        return s + "/w=800/h=1080";
//                    }
//                }).observeOn(Schedulers.newThread())
//                .map(new Function<String, Drawable>() {
//                    @Override
//                    public Drawable apply(String s) throws Exception {
//                        return downloadImageToLocal(s);
//                    }
//                }).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Drawable>() {
//                    @Override
//                    public void accept(Drawable drawable) throws Exception {
//                        //TODO
//                    }
//                });
    }

    public static void test_switchmap() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("Hello");
                emitter.onNext("rxJava");
                emitter.onNext("!!!");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .switchMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        List<String> result = new ArrayList<>();
                        result.add(s + "1");
                        result.add(s + "2");
                        result.add(s + "3");
                        result.add(s + "4");
                        return Observable.fromIterable(result).delay(1, TimeUnit.SECONDS);
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                L.e(s);
            }
        });
    }

    public static void test_flatmap() {
//        Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                emitter.onNext("1");
//                emitter.onComplete();
//            }
//        }).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                L.e("normal:" + s);
//            }
//        });
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("Hello");
                emitter.onNext("rxJava");
                emitter.onNext("!!!");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        L.e("flatmap: " + s);
                        List<String> result = new ArrayList<>();
                        result.add(s + "1");
                        result.add(s + "2");
                        result.add(s + "3");
                        result.add(s + "4");
                        return Observable.
                                fromIterable(result);
//                                .delay(1, TimeUnit.SECONDS);
//                                .observeOn(Schedulers.single());
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                L.e(s);
            }
        });
    }

    public static void test_concatmap() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("Hello");
                emitter.onNext("rxJava");
                emitter.onNext("!!!");
                emitter.onComplete();
            }
        })
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        L.e("concatmap: " + s);
                        List<String> result = new ArrayList<>();
                        result.add(s + "1");
                        result.add(s + "2");
                        result.add(s + "3");
                        result.add(s + "4");
                        return Observable.fromIterable(result).delay(3, TimeUnit.SECONDS);
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                L.e(s);
            }
        });
    }


}
