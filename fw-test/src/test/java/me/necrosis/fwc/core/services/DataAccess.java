package me.necrosis.fwc.core.services;

import java.util.Optional;
import java.util.function.Predicate;

public interface DataAccess {

    <T> T saveEntity(T entity);

    <T> Optional<T> findEntityBy(Class<T> tClass, Predicate<T> filter);

    <T> boolean deleteEntityWhere(Class<T> tClass, Predicate<T> filter);
}
