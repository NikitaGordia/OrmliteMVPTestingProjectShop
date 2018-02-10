package com.nikitagordia.shop.Presenters;

import android.content.Context;
import android.util.Log;

import com.nikitagordia.shop.Data.Models.Brand;
import com.nikitagordia.shop.Data.Models.ListableItem;
import com.nikitagordia.shop.Data.Models.PC;
import com.nikitagordia.shop.Data.Repo.BrandProvider;
import com.nikitagordia.shop.Data.Repo.PCProvider;
import com.nikitagordia.shop.Data.Repo.Provider;
import com.nikitagordia.shop.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by nikitagordia on 2/7/18.
 */

public class MainPresenter {

    private Provider<Brand> mBrandProvider;
    private Provider<PC> mPCProvider;
    private Context mContext;

    public MainPresenter(Context context) {
        mBrandProvider = new BrandProvider(context);
        mPCProvider = new PCProvider(context);
        mContext = context;
    }

    public List<ListableItem> getBrands() {
        LinkedList<ListableItem> list = new LinkedList<>();
        for (Brand i : mBrandProvider.findAll())
            list.add(i);
        return list;
    }

    public Brand getBrand(int id) {
        return mBrandProvider.getById(id);
    }

    public PC getPC(int id) {
        return mPCProvider.getById(id);
    }

    public List<ListableItem> getPCs() {
        LinkedList<ListableItem> list = new LinkedList<>();
        for (PC i : mPCProvider.findAll())
            list.add(i);
        return list;
    }

    public PC addNewPC() {
        PC pc = new PC();
        pc.setName(mContext.getResources().getString(R.string.noname_pc));
        mPCProvider.create(pc);
        return pc;
    }

    public Brand addNewBrand() {
        Brand brand = new Brand();
        brand.setName(mContext.getResources().getString(R.string.noname_brand));
        mBrandProvider.create(brand);
        return brand;
    }

    public void deleteBrand(Brand brand) {
        if (brand == null) return;
        mBrandProvider.delete(brand);
    }

    public void deletePC(PC pc) {
        if (pc == null) return;
        mPCProvider.delete(pc);
    }

    public void close() {
        mBrandProvider.close();
        mPCProvider.close();
    }
}
