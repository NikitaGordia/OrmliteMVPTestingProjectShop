package com.nikitagordia.shop.Views.Activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nikitagordia.shop.MyApplication;
import com.nikitagordia.shop.Presenters.MainPresenter;
import com.nikitagordia.shop.R;
import com.nikitagordia.shop.Services.ApplicationServiceHolder;
import com.nikitagordia.shop.Views.Fragments.BrandFragment;
import com.nikitagordia.shop.Views.Fragments.PCFragment;

public class MainActivity extends AppCompatActivity {

    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MainPresenter presenter = new MainPresenter(getApplicationContext());

        ((MyApplication)getApplication()).registerLastCallback(new Runnable() {
            @Override
            public void run() {
                presenter.close();
            }
        });

        startService(new Intent(this, ApplicationServiceHolder.class));

        FragmentManager fm = getSupportFragmentManager();
        Fragment above = fm.findFragmentById(R.id.frag_above);

        if (above == null) {
            above = PCFragment.newInstance(presenter);
            fm.beginTransaction()
                    .add(R.id.frag_above, above)
                    .commit();
        }

        Fragment below = fm.findFragmentById(R.id.frag_bellow);

        if (below == null) {
            below = BrandFragment.newInstance(presenter);
            fm.beginTransaction()
                    .add(R.id.frag_bellow, below)
                    .commit();
        }
    }
}
