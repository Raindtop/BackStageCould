package org.raindrop.lambdaekl;

import java.io.Serializable;

@FunctionalInterface
public interface SFunction<T> extends Serializable {
    Object get(T source);
}
