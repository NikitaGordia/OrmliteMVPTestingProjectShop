package com.nikitagordia.shop.Presenters;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.ForeignCollection;
import com.nikitagordia.shop.Data.Models.Brand;
import com.nikitagordia.shop.Data.Models.ListableItem;
import com.nikitagordia.shop.Data.Models.PC;
import com.nikitagordia.shop.Data.Repo.BrandProvider;
import com.nikitagordia.shop.Data.Repo.Provider;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by nikitagordia on 2/8/18.
 */

public class BrandEditPresenter {

    private Provider<Brand> mPrivider;
    private Brand mCurrentBrand;

    public BrandEditPresenter(Context context) {
        mPrivider = new BrandProvider(context);
    }

    public String setBrand(int id) {
        mCurrentBrand = mPrivider.getById(id);
        if (mCurrentBrand == null) return "";
        return mCurrentBrand.getName();
    }

    public List<ListableItem> getPCs() {
        LinkedList<ListableItem> list = new LinkedList<>();
        if (mCurrentBrand == null) return list;
        for (PC pc : mCurrentBrand.getProducts())
            list.add(pc);
        return list;
    }

    public void store(String s) {
        if (mCurrentBrand != null) {
            mCurrentBrand.setName(s);
            mPrivider.update(mCurrentBrand);
        }
    }

    public void close() {
        mPrivider.close();
    }
}
