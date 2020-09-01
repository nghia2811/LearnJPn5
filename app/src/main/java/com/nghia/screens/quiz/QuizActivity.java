package com.nghia.screens.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import com.nghia.R;
import com.nghia.screens.quiz.numberquizpicker.NumberQuizPickerDialog;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener, NumberQuizPickerDialog.OnItemClickListener {

    ImageView back, eqCheck, sqCheck, tfCheck;
    BottomSheetDialog bottomDialog;
    boolean eq = false, sq = false, tf = false;
    TextView setcategory;
    Button start;
    NumberQuizPickerDialog vNumberQuizPicker;
    private TextView vNumberOfQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        setupView();
    }

    private void setupView() {
        back = findViewById(R.id.quiz_back);
        setcategory = findViewById(R.id.tv_setcategory);
        start = findViewById(R.id.btn_start);
        vNumberOfQuiz = findViewById(R.id.edt_setAmount);
        tfCheck = findViewById(R.id.tf_question);
        sqCheck = findViewById(R.id.selected_question);
        eqCheck = findViewById(R.id.essay_question);

        vNumberQuizPicker = new NumberQuizPickerDialog();
        vNumberQuizPicker.setOnItemClickListener(this);

        tfCheck.setOnClickListener(this);
        sqCheck.setOnClickListener(this);
        eqCheck.setOnClickListener(this);
        back.setOnClickListener(this);
        setcategory.setOnClickListener(this);
        vNumberOfQuiz.setOnClickListener(this);
        start.setOnClickListener(this);
    }

    private void createBottomDialog() {
        if (bottomDialog == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.bottom_dialog, null);
            TextView kanji = (TextView) view.findViewById(R.id.tv_kanji);
            ((TextView) view.findViewById(R.id.tv_tuvung)).setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    setcategory.setText("Từ vựng");
                    if (bottomDialog != null) {
                        bottomDialog.dismiss();
                    }
                }
            });
            kanji.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    setcategory.setText("Kanji");
                    if (bottomDialog != null) {
                        bottomDialog.dismiss();
                    }
                }
            });
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
            bottomDialog = bottomSheetDialog;
            bottomSheetDialog.setContentView(view);
        }
        bottomDialog.show();
    }

    private void showNumberQuizPickerDialog() {
        vNumberQuizPicker.show(getSupportFragmentManager(), "numberquizdialog");
    }

    @Override
    public void onDoneClick(int position) {
        vNumberOfQuiz.setText(String.valueOf(position-2));
        hideNumerQuizPickerDialog();
    }

    private void hideNumerQuizPickerDialog() {
        BottomSheetDialogFragment fragment = (BottomSheetDialogFragment) getSupportFragmentManager().findFragmentByTag("numberquizdialog");
        if (fragment != null)
            fragment.dismiss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edt_setAmount:
                showNumberQuizPickerDialog();
                break;
            case R.id.quiz_back:
                finish();
                break;
            case R.id.tv_setcategory:
                createBottomDialog();
                break;
            case R.id.btn_start:
                if (!tf && !sq && !eq)
                    Toast.makeText(QuizActivity.this, "Vui lòng chọn loại câu hỏi!", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(QuizActivity.this, QuestionActivity.class);
                    intent.putExtra("type", setcategory.getText());
                    intent.putExtra("amount", "" + vNumberOfQuiz.getText());
                    intent.putExtra("tf", tf);
                    intent.putExtra("sq", sq);
                    intent.putExtra("eq", eq);
                    startActivity(intent);
                }
                break;
            case R.id.tf_question:
                if (tf) {
                    tf = false;
                    tfCheck.setImageResource(R.drawable.box);
                } else {
                    tf = true;
                    tfCheck.setImageResource(R.drawable.box_check);
                }
                break;
            case R.id.selected_question:
                if (sq) {
                    sq = false;
                    sqCheck.setImageResource(R.drawable.box);
                } else {
                    sq = true;
                    sqCheck.setImageResource(R.drawable.box_check);
                }
                break;
            case R.id.essay_question:
                if (eq) {
                    eq = false;
                    eqCheck.setImageResource(R.drawable.box);
                } else {
                    eq = true;
                    eqCheck.setImageResource(R.drawable.box_check);
                }
                break;
        }
    }
}
