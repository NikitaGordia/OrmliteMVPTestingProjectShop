package com.nikitagordia.shop.Views.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nikitagordia.shop.Data.Models.PC;
import com.nikitagordia.shop.Presenters.BrandEditPresenter;
import com.nikitagordia.shop.R;
import com.nikitagordia.shop.Views.Adapters.ListAdapter;

import java.util.List;

public class BrandEditActivity extends AppCompatActivity {

    private static final String EXTRA_ID = "com.nikitagordia.shop.View.Activities.extraIdBrand";

    private BrandEditPresenter mPresenter;

    private EditText mName;
    private RecyclerView mRecyclerView;
    private Button mDone;
    private ListAdapter mAdapter;

    public static Intent getActivityInstance(Context context, int id) {
        Intent intent = new Intent(context, BrandEditActivity.class);
        intent.putExtra(EXTRA_ID, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_edit);

        mPresenter = new BrandEditPresenter(getApplicationContext());

        mName = (EditText) findViewById(R.id.et_brand_name);
        mDone = (Button) findViewById(R.id.bt_done);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);

        mName.setText(mPresenter.setBrand(getIntent().getIntExtra(EXTRA_ID, 0)));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ListAdapter(this, mPresenter.getPCs());
        mRecyclerView.setAdapter(mAdapter);

        mDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.store(mName.getText().toString());
                finish();
            }
        });
    }
}