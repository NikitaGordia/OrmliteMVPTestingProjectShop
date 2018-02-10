package com.nikitagordia.shop.Presenters;

import android.content.Context;

import com.j256.ormlite.dao.ForeignCollection;
import com.nikitagordia.shop.Data.Models.Brand;
import com.nikitagordia.shop.Data.Models.PC;
import com.nikitagordia.shop.Data.Repo.BrandProvider;
import com.nikitagordia.shop.Data.Repo.PCProvider;
import com.nikitagordia.shop.Data.Repo.Provider;
import com.nikitagordia.shop.R;
import com.nikitagordia.shop.Views.Activities.PCEditActivity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by nikitagordia on 2/8/18.
 */

public class PCEditPresenter {

    private Provider<PC> mProvider;
    private Provider<Brand> mBrandProvider;
    private Context mContext;
    private PC mCurrentPC;

    public PCEditPresenter(Context context) {
        mProvider = new PCProvider(context);
        mBrandProvider = new BrandProvider(context);
        mContext = context;
    }

    public String setPC(int id) {
        mCurrentPC = mProvider.getById(id);
        if (mCurrentPC == null) return "";
        return mCurrentPC.getName();
    }

    public int getBrandId() {
        if (mCurrentPC.getBrand() == null) return -1;
        return mCurrentPC.getBrand().getId();
    }

    public List<Brand> getAllBrands() {
        List<Brand> list = mBrandProvider.findAll();
        Brand brand = new Brand();
        brand.setName(mContext.getResources().getString(R.string.nope));
        list.add(0, brand);
        return list;
    }

    public void store(String name, Brand brand) {
        if (mCurrentPC != null) {
            mCurrentPC.setName(name);
            mCurrentPC.setBrand(brand);
            mProvider.update(mCurrentPC);
        }
    }

    public void close() {
        mProvider.close();
        mBrandProvider.close();
    }
}
