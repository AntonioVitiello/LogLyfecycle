package com.android.example.loglyfecycle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class LifecycleActivityWithFragment extends AppCompatActivity {
    public static final int REMOVE_FRAGMENT_AFTER_MILLISEC = 1500;
    private static final String LIFECYCLE_FRAGMENT_TAG = "lifecycleFragment";
    private Toolbar myToolbar;
    private PreferenceHelper mPeferenceHelper;
    private LifecycleFragment mLifecycleFragment;
    FragmentManager mFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_fragment_lifecycle);
        mFragmentManager = getSupportFragmentManager();

        /*******************************************************************************************
         * IMPORTANT : ONLY CREATE NEW FRAGMENTS WHEN THERE IS NO PREVIOUSLY SAVED STATE !!!
         *
         * If the user rotates the device's screen from portrait to landscape
         * Or, the user changes languages
         * Or, the user puts the device into a manufacturer-supplied car dock
         * Or, the user does any other configuration change
         * Activity will be destroyed and recreated
         ******************************************************************************************/
/*
        if(savedInstanceState == null) {
            // Create the Addressbar Fragment and add Fragment to this Activity
            mLifecycleFragment = new LifecycleFragment();
            mFragmentManager.beginTransaction()
                    .add(R.id.dynamic_fragment_container, mLifecycleFragment, LIFECYCLE_FRAGMENT_TAG)
                    .commit();
        }
*/

        myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Enable the Up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
/*
        myToolbar.postDelayed(new Runnable() {
            @Override
            public void run() {
                Fragment fragment = mFragmentManager.findFragmentByTag(LIFECYCLE_FRAGMENT_TAG);
                mFragmentManager.beginTransaction()
                        .remove(fragment)
                        // WORKAROUND_FOR_BUG_19917_KEY
                        // .commit();
                        .commitAllowingStateLoss();
                setResult(RESULT_OK);
                finish();
            }
        }, REMOVE_FRAGMENT_AFTER_MILLISEC);
*/
    }

    public void addFragment(View view) {
        Fragment fragment = mFragmentManager.findFragmentByTag(LIFECYCLE_FRAGMENT_TAG);
        if(fragment == null) {
            // Create the Addressbar Fragment and add Fragment to this Activity
            mLifecycleFragment = new LifecycleFragment();
            mFragmentManager.beginTransaction()
                    .add(R.id.dynamic_fragment_container, mLifecycleFragment, LIFECYCLE_FRAGMENT_TAG)
                    .commit();
        }
    }

    public void closeFragment(View view) {
        if(mLifecycleFragment != null) {
            mFragmentManager.beginTransaction()
                    .remove(mLifecycleFragment)
                    .commit();
            mFragmentManager = null;
        }
    }

    public void closeActivity(View view) {
        setResult(RESULT_OK);
        finish();
    }

}
