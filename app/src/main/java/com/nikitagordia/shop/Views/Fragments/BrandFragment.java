package com.nikitagordia.shop.Views.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nikitagordia.shop.Data.Models.Brand;
import com.nikitagordia.shop.Data.Models.ListableItem;
import com.nikitagordia.shop.Presenters.MainPresenter;
import com.nikitagordia.shop.R;
import com.nikitagordia.shop.Views.Activities.BrandEditActivity;
import com.nikitagordia.shop.Views.Adapters.ClickItemListener;
import com.nikitagordia.shop.Views.Adapters.ListAdapter;

/**
 * Created by nikitagordia on 2/7/18.
 */

public class BrandFragment extends Fragment {

    private Button mButtonAdd, mButtonDel;
    private RecyclerView mRecyclerView;
    private ListAdapter mAdapter;

    private MainPresenter mPresenter;

    private int updateId;

    public static BrandFragment newInstance(MainPresenter presenter) {
        BrandFragment fragment = new BrandFragment();
        fragment.mPresenter = presenter;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_button_list, container, false);

        mButtonAdd = (Button) v.findViewById(R.id.button_add);
        mButtonDel = (Button) v.findViewById(R.id.button_del);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.list);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ListAdapter(getContext(), mPresenter.getBrands());
        mAdapter.registerClickItemListener(new ClickItemListener() {
            @Override
            public void onClick(int id) {
                updateId = id;
                startActivity(BrandEditActivity.getActivityInstance(getContext(), updateId));
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        updateId = -1;

        mButtonAdd.setText(R.string.add_brand);
        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.addNewItem(mPresenter.addNewBrand());
            }
        });

        mButtonDel.setText(R.string.del_brand);
        mButtonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.deleteBrand((Brand)mAdapter.deleteFirstItem());
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (updateId != -1) {
            Brand brand = mPresenter.getBrand(updateId);
            if (brand != null)
                mAdapter.onUpdateItem(brand);
            updateId = -1;
        }
    }
}
