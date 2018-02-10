package com.nikitagordia.shop.Data.DB;

import android.content.Context;

/**
 * Created by nikitagordia on 2/7/18.
 */

public class DatabaseManager {

    private static DatabaseManager mInstance;
    private DatabaseHelper mHelper;

    public static void init(Context context) {
        if (mInstance == null)
            mInstance = new DatabaseManager(context);
    }

    public static DatabaseManager getInstance() {
        return mInstance;
    }

    public DatabaseManager(Context context) {
        mHelper = new DatabaseHelper(context);
    }

    public DatabaseHelper getHelper() {
        return mHelper;
    }
}
