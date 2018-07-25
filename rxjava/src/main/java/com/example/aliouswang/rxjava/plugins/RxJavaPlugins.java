package com.example.aliouswang.rxjava.plugins;

import com.example.aliouswang.rxjava.Observable;
import com.example.aliouswang.rxjava.Observer;
import com.example.aliouswang.rxjava.annotations.NonNull;
import com.example.aliouswang.rxjava.annotations.Nullable;
import com.example.aliouswang.rxjava.functions.BiFunction;
import com.example.aliouswang.rxjava.functions.Function;

/**
 * Created by aliouswang on 2018/7/25.
 */

public class RxJavaPlugins {

    @Nullable
    static volatile BiFunction<? super Observable, ? super Observer, ? extends Observer> onObservableSubscribe;

    static volatile Function<? super Observable, ? extends Observable> onObservableAssembly;

    @NonNull
    public static <T> Observer<? super T> onSubscribe(@NonNull Observable<T> source, @NonNull Observer<? super T> observer) {
        BiFunction<? super Observable, ? super Observer, ? extends Observer> f = onObservableSubscribe;
        if (f != null) {
            //TODO add onObserverableSubscribe
        }
        return observer;
    }

    @NonNull
    public static <T> Observable<T> onAssembly(@NonNull Observable<T> source) {
        Function<? super Observable, ? extends Observable> f = onObservableAssembly;
        if (f != null) {
            return apply(f, source);
        }
        return source;
    }

    static <T, R> R apply(@NonNull Function<T, R> f, @NonNull T t) {
        try {
            return f.apply(t);
        } catch (Throwable ex) {
//            throw ExceptionHelper.wrapOrThrow(ex);
            return null;
        }
    }

}
