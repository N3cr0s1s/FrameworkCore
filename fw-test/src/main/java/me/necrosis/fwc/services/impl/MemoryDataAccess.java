package me.necrosis.fwc.services.impl;

import me.necrosis.fwc.services.DataAccess;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class MemoryDataAccess implements DataAccess {

    private final Map<Class<?>, List<?>> entitiesMap = new HashMap<>();

    @Override
    public <T> T saveEntity(T entity) {
        List<T> orDefault = (List<T>) entitiesMap.getOrDefault(entity.getClass(), new ArrayList<T>());
        orDefault.add(entity);
        entitiesMap.put(entity.getClass(),orDefault);
        return entity;
    }

    @Override
    public <T> Optional<T> findEntityBy(Class<T> tClass, Predicate<T> filter) {
        List<T> list = (List<T>) entitiesMap.getOrDefault(tClass, new ArrayList<>());
        return list.stream().filter(filter).findFirst();
    }

    @Override
    public <T> boolean deleteEntityWhere(Class<T> tClass, Predicate<T> filter) {
        List<T> list = (List<T>) entitiesMap.getOrDefault(tClass, new ArrayList<>());
        List<T> collect = list.stream().filter(filter).toList();
        boolean removed = list.removeAll(collect);
        entitiesMap.put(tClass,list);
        return removed;
    }
}
