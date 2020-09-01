package com.nghia.screens.mina;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import com.nghia.R;
import com.nghia.screens.alphabet.CustomPagerAdapter;

public class LessonActivity extends AppCompatActivity {
    CustomPagerAdapter pagerAdapter;
    Button newwords, grammars;
    ImageView back;
    TextView lesson_num;
    int lesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
       setupView();
    }

    private void setupView() {
        newwords = findViewById(R.id.btn_newwords);
        grammars = findViewById(R.id.btn_grammar);
        back = findViewById(R.id.lesson_back);
        lesson_num = findViewById(R.id.tv_lesson_num);

        Intent intent = getIntent();
        lesson = intent.getIntExtra("lesson", 0);
        lesson_num.setText("Lesson " + lesson);

        ViewPager pager = (ViewPager) findViewById(R.id.vp_lesson);
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
                    newwords.setTextColor(Color.WHITE);
                    newwords.setBackgroundResource(R.drawable.button1);
                    grammars.setTextColor(Color.BLACK);
                    grammars.setBackgroundResource(R.drawable.button);
                } else {
                    grammars.setTextColor(Color.WHITE);
                    grammars.setBackgroundResource(R.drawable.button1);
                    newwords.setTextColor(Color.BLACK);
                    newwords.setBackgroundResource(R.drawable.button);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        newwords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newwords.setTextColor(Color.WHITE);
                newwords.setBackgroundResource(R.drawable.button1);
                grammars.setTextColor(Color.BLACK);
                grammars.setBackgroundResource(R.drawable.button);
                pager.setCurrentItem(0);
            }
        });

        grammars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grammars.setTextColor(Color.WHITE);
                grammars.setBackgroundResource(R.drawable.button1);
                newwords.setTextColor(Color.BLACK);
                newwords.setBackgroundResource(R.drawable.button);
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

        fragmentList.add(NewwordFragment.newInstance(lesson));
        fragmentList.add(GrammarFragment.newInstance(lesson));
        return fragmentList;
    }
}