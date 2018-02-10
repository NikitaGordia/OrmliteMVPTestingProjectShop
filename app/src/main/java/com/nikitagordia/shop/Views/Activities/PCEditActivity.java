package com.nikitagordia.shop.Views.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.nikitagordia.shop.Data.Models.Brand;
import com.nikitagordia.shop.Presenters.PCEditPresenter;
import com.nikitagordia.shop.R;

import java.util.List;

public class PCEditActivity extends AppCompatActivity {

    private static final String EXTRA_ID = "com.nikitagordia.shop.View.Activities.extraIdPC";

    private PCEditPresenter mPresenter;

    private Button mDone;
    private EditText mName;
    private Spinner mBrand;
    private ArrayAdapter<Brand> mAdapter;

    private int selectedBrand;

    public static Intent getIntentActivity(Context context, int id) {
        Intent intent = new Intent(context, PCEditActivity.class);
        intent.putExtra(EXTRA_ID, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_edit);

        mPresenter = new PCEditPresenter(getApplicationContext());

        mDone = (Button) findViewById(R.id.bt_done);
        mName = (EditText) findViewById(R.id.et_pc_name);
        mBrand = (Spinner) findViewById(R.id.spinner);

        mName.setText(mPresenter.setPC(getIntent().getIntExtra(EXTRA_ID, 0)));

        mDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.store(mName.getText().toString(), mAdapter.getItem(mBrand.getSelectedItemPosition()));
                finish();
            }
        });

        List<Brand> list = mPresenter.getAllBrands();
        mAdapter = new ArrayAdapter<Brand>(getApplicationContext(), android.R.layout.simple_spinner_item, list);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBrand.setAdapter(mAdapter);
        initSpinner(list);
    }

    private void initSpinner(List<Brand> list) {
        int brandId = mPresenter.getBrandId();
        if (brandId == -1) {
            mBrand.setSelection(0);
            return;
        }
        int pos = getPos(list, brandId);
        if (pos == -1) {
            mBrand.setSelection(0);
            return;
        }
        mBrand.setSelection(pos);
    }

    private int getPos(List<Brand> brands, int id) {
        for (int i = 0; i < brands.size(); i++)
            if (brands.get(i).getId() == id) return i;
        return -1;
    }
}
