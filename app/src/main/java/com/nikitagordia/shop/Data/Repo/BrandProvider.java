package com.nikitagordia.shop.Data.Repo;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

import com.nikitagordia.shop.Data.DB.DatabaseHelper;
import com.nikitagordia.shop.Data.DB.DatabaseManager;
import com.nikitagordia.shop.Data.Models.Brand;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by nikitagordia on 2/7/18.
 */

public class BrandProvider implements Provider<Brand> {

    private DatabaseHelper mHelper;

    public BrandProvider(Context context) {
        DatabaseManager.init(context);
        mHelper = DatabaseManager.getInstance().getHelper();
    }

    @Override
    public int create(Brand item) {
        int index = -1;
        try {
            index = mHelper.getBrandDao().create(item);
        } catch (SQLException e) {
            Log.e("mytg", "Creating brand");
        }
        return index;
    }

    @Override
    public int delete(Brand item) {
        int index = -1;
        try {
            index = mHelper.getBrandDao().delete(item);
        } catch (SQLException e) {
            Log.e("mytg", "Deleting brand");
        }
        return index;
    }

    @Override
    public int update(Brand item) {
        int index = -1;
        try {
            index = mHelper.getBrandDao().update(item);
        } catch (SQLException e) {
            Log.e("mytg", "Updating brand");
        }
        return index;
    }

    @Override
    public Brand getById(int id) {
        Brand brand = null;
        try {
            brand = mHelper.getBrandDao().queryForId(id);
        } catch (SQLException e) {
            Log.e("mytg", "getting brand");
        }
        return brand;
    }

    @Override
    public List<Brand> findAll() {
        List<Brand> list = new LinkedList<>();
        try {
            list = mHelper.getBrandDao().queryForAll();
        } catch (SQLException e) {
            Log.e("mytg", "Brand find all\n" + e.getStackTrace());
        }
        return list;
    }

    public void close() {
        mHelper.close();
    }
}
