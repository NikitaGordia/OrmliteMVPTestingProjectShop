package com.nikitagordia.shop.Data.Repo;

import java.util.List;

/**
 * Created by nikitagordia on 2/7/18.
 */

public interface Provider<T> {

    public int create(T item);

    public int delete(T item);

    public List<T> findAll();

    public T getById(int id);

    public int update(T item);

    public void close();
}
