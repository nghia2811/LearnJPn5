package com.nghia.screens.alphabet;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import com.nghia.R;

public class AlphabetActivity extends AppCompatActivity {
    Button hiragana, katakana;
    ImageView back;
    CustomPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);
        setupView();
    }

    private void setupView() {
        hiragana = findViewById(R.id.btn_hiragana);
        katakana = findViewById(R.id.btn_katakana);
        back = findViewById(R.id.alphabet_back);
        ViewPager pager = (ViewPager) findViewById(R.id.vp_alphabet);
        List<Fragment> fragments = getFragment();
        pagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(), fragments);

        pager.setAdapter(pagerAdapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    hiragana.setTextColor(Color.WHITE);
                    hiragana.setBackgroundResource(R.drawable.button1);
                    katakana.setTextColor(Color.BLACK);
                    katakana.setBackgroundResource(R.drawable.button);
                } else {
                    katakana.setTextColor(Color.WHITE);
                    katakana.setBackgroundResource(R.drawable.button1);
                    hiragana.setTextColor(Color.BLACK);
                    hiragana.setBackgroundResource(R.drawable.button);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        hiragana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hiragana.setTextColor(Color.WHITE);
                hiragana.setBackgroundResource(R.drawable.button1);
                katakana.setTextColor(Color.BLACK);
                katakana.setBackgroundResource(R.drawable.button);
                pager.setCurrentItem(0);
            }
        });

        katakana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                katakana.setTextColor(Color.WHITE);
                katakana.setBackgroundResource(R.drawable.button1);
                hiragana.setTextColor(Color.BLACK);
                hiragana.setBackgroundResource(R.drawable.button);
                pager.setCurrentItem(1);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private List<Fragment> getFragment() {
        List<Fragment> fragmentList = new ArrayList<>();

        fragmentList.add(HiraganaFragment.newInstance("Frag 1"));
        fragmentList.add(KatakanaFragment.newInstance("Frag 2"));
        return fragmentList;
    }
}