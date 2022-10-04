package io.materialstudies.testviewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    // creating an object of ViewPager
    ViewPager mViewPager;

    // images array
    int[] images = {R.drawable.ic_flag_of_argentina, R.drawable.ic_flag_of_australia, R.drawable.ic_flag_of_belgium,
                    R.drawable.ic_flag_of_brazil, R.drawable.ic_flag_of_fiji, R.drawable.ic_flag_of_denmark,
                    R.drawable.ic_flag_of_germany, R.drawable.ic_flag_of_india, R.drawable.ic_flag_of_kuwait,
                    R.drawable.ic_flag_of_new_zealand};

    // creating an object of the viewPagerAdapter
    ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing the ViewPager Object
        mViewPager = (ViewPager)findViewById(R.id.viewPagerMain);

        // Initializing the ViewPagerAdapter
        mViewPagerAdapter = new ViewPagerAdapter(MainActivity.this, images);

        // Adding the Adapter to the ViewPager
        mViewPager.setAdapter(mViewPagerAdapter);
    }
}