package com.nghia.screens.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.nghia.R;
import com.nghia.screens.quiz.fragment.EssayFragment;
import com.nghia.screens.quiz.fragment.SelectedFragment;
import com.nghia.screens.quiz.fragment.TfFragment;

public class QuestionActivity extends AppCompatActivity {
    int amount, type;
    ImageView back, medal;
            TextView number, next;
    int no = 1;
    public int point = 0;
    View resultLayout;
    TextView result;
    Button btnBack;
    Boolean tf, eq, sq;
    Random random;
    List<Integer> questionType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        resultLayout = findViewById(R.id.fr_result);
        result = findViewById(R.id.tv_result);
        btnBack = findViewById(R.id.btn_result);
        medal = findViewById(R.id.medal);
        back = findViewById(R.id.question_back);
        number = findViewById(R.id.tv_quiz_number);
        next = findViewById(R.id.tv_quiz_next);

        setupView();
    }

    private void setupView() {
        Intent intent = getIntent();
        if(intent.getStringExtra("type").equals("Từ vựng")) type = 1;
        else type = 2;
        amount = Integer.parseInt(intent.getStringExtra("amount"));
        tf = intent.getBooleanExtra("tf", true);
        sq = intent.getBooleanExtra("sq", true);
        eq = intent.getBooleanExtra("eq", true);

        random = new Random();
        questionType = new ArrayList<>();
        if(tf) questionType.add(2);
        if(sq) questionType.add(1);
        if(eq) questionType.add(3);

        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        next.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(type, getRandomType());
            }
        });

        loadFragment(type, getRandomType());
    }

    public void loadFragment(int typeWord, int typeQues) {
        if (no <= amount) {
            Fragment fragment;
            if (typeQues == 2) fragment = TfFragment.newInstance(typeWord);
            else if (typeQues == 1) fragment = SelectedFragment.newInstance(typeWord);
            else fragment = EssayFragment.newInstance(typeWord);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
            transaction.replace(R.id.fr_question, fragment);
            transaction.commit();
            number.setText(no+"/"+amount);
            no++;
        } else {
            resultLayout.setVisibility(View.VISIBLE);
            result.setText(point + "/" + amount);
            if (point <= amount - 5) medal.setBackgroundResource(R.drawable.medal_2);
        }
    }

    public int getRandomType() {
        return questionType.get(random.nextInt(questionType.size()));
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
