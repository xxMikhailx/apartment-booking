package com.epam.apartmentbooking.dao;

public interface GenericDAO<K, T> {

        T findEntityById(K id);

        boolean remove(K id);

        boolean create(T entity);

        boolean update(T entity);
}