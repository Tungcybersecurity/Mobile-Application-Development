package com.example.demoviewerpapernnavigation;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ViewPager mViewPager;
    BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.view_pager);
        mBottomNavigationView = findViewById(R.id.bottom_navigation);

        ViewpageAdater viewpagerAdater = new ViewpageAdater(
                getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        );

        mViewPager.setAdapter(viewpagerAdater);
        mViewPager.setCurrentItem(0);
        getSupportActionBar().setTitle("Person");

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // No action needed here
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mBottomNavigationView.getMenu().findItem(R.id.person).setChecked(true);
                        getSupportActionBar().setTitle("Person");
                        break;
                    case 1:
                        mBottomNavigationView.getMenu().findItem(R.id.home).setChecked(true);
                        getSupportActionBar().setTitle("Home");
                        break;
                    case 2:
                        mBottomNavigationView.getMenu().findItem(R.id.settings).setChecked(true);
                        getSupportActionBar().setTitle("Setting");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // No action needed here
            }
        });

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.person:
                                mViewPager.setCurrentItem(0);
                                getSupportActionBar().setTitle("Person");
                                break;
                            case R.id.home:
                                mViewPager.setCurrentItem(1);
                                getSupportActionBar().setTitle("Home");
                                break;
                            case R.id.settings:
                                mViewPager.setCurrentItem(2);
                                getSupportActionBar().setTitle("Setting");
                                break;
                        }
                        return true;
                    }
                }
        );
    }
}
