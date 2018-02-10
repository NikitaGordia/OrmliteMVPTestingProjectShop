package com.nikitagordia.shop.Data.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.nikitagordia.shop.Data.Models.Brand;
import com.nikitagordia.shop.Data.Models.PC;

import java.sql.SQLException;

/**
 * Created by nikitagordia on 2/7/18.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "shop.sqlite";

    private Dao<PC, Integer> mPCDao = null;
    private Dao<Brand, Integer> mBrandDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, PC.class);
            TableUtils.createTable(connectionSource, Brand.class);
        } catch (SQLException e) {
            Log.e("mytg", "can't create DB\n" + e.getStackTrace());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    public Dao<PC, Integer> getPCDao() {
        if (mPCDao == null) {
            try {
                mPCDao = getDao(PC.class);
            } catch (SQLException e) {
                Log.e("mytg", "can't get PC Dao\n" + e.getStackTrace());
            }
        }
        return mPCDao;
    }

    public Dao<Brand, Integer> getBrandDao() {
        if (mBrandDao == null) {
            try {
                mBrandDao = getDao(Brand.class);
            } catch (SQLException e) {
                Log.e("mytg", "can't get Brand Dao\n" + e.getStackTrace());
            }
        }
        return mBrandDao;
    }
}
