package com.example.aliouswang.rxjava;

/**
 * Created by aliouswang on 2018/7/24.
 */

public class ObjectHelper {

    private ObjectHelper() {
        throw new IllegalStateException("No instances!");
    }

    /**
     * Verifies if the object is not null or throws a NullPointerException
     * with the given message
     * @param object the object to verify
     * @param message the message to use with the NullPointerException
     * @param <T> the value type
     * @return  the object itself
     * @throws NullPointerException if object is null
     */
    public static <T> T requireNonNull(T object, String message) {
        if (object == null) throw new NullPointerException(message);
        return object;
    }

}
