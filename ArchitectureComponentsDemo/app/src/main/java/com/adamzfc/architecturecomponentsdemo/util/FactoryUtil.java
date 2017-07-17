package com.adamzfc.architecturecomponentsdemo.util;

import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by adamzfc on 7/15/17.
 */

public class FactoryUtil {
    static <K, T> Function<K, T> factoryKit(
            Consumer<BiConsumer<K, T>> consumer,
            Function<K, T> ifAbsent) {
        HashMap<K, T> map = new HashMap<>();
        consumer.accept(map::put);
        return key -> map.computeIfAbsent(key, ifAbsent);
    }
    /**
     * Function<String, Supplier<Vehicle>>factory = factoryKit(builder -> {
     *     builder.accept("car", Car::new);
     *     builder.accept("moto", Moto::new);
     * }, name -> {throw new IAE("unknown vehicle " + name);});
     * Vehicle vehicle = factory.apply("car").get();
     */
}
