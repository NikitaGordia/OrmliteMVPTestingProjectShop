package com.nikitagordia.shop.Data.Repo;

import android.content.Context;
import android.util.Log;

import com.nikitagordia.shop.Data.DB.DatabaseHelper;
import com.nikitagordia.shop.Data.DB.DatabaseManager;
import com.nikitagordia.shop.Data.Models.PC;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by nikitagordia on 2/7/18.
 */

public class PCProvider implements Provider<PC> {

    private DatabaseHelper mHelper;

    public PCProvider(Context context) {
        DatabaseManager.init(context);
        mHelper = DatabaseManager.getInstance().getHelper();
    }

    @Override
    public int create(PC item) {
        int index = -1;
        try {
            index = mHelper.getPCDao().create(item);
        } catch (SQLException e) {
            Log.e("mytg", "Creating PC\n" + e.getStackTrace());
        }
        return index;
    }

    @Override
    public int delete(PC item) {
        int index = -1;
        try {
            index = mHelper.getPCDao().delete(item);
        } catch (SQLException e) {
            Log.e("mytg", "Deleting PC\n" + e.getStackTrace());
        }
        return index;
    }

    @Override
    public PC getById(int id) {
        PC pc = null;
        try {
            pc = mHelper.getPCDao().queryForId(id);
        } catch (SQLException e) {
            Log.e("mytg", "getting PC");
        }
        return pc;
    }

    @Override
    public int update(PC item) {
        int index = -1;
        try {
            index = mHelper.getPCDao().update(item);
        } catch (SQLException e) {
            Log.e("mytg", "Updating PC");
        }
        return index;
    }

    @Override
    public List<PC> findAll() {
        List<PC> list = new LinkedList<>();
        try {
            list = mHelper.getPCDao().queryForAll();
        } catch (SQLException e) {
            Log.e("mytg", "getting all\n" + e.getStackTrace());
        }
        return list;
    }

    public void close() {
        mHelper.close();
    }
}
