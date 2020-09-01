package com.nghia.screens.mina;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import com.nghia.R;

public class MinaActivity extends AppCompatActivity {
    List<Integer> lstLesson;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mina);
        back = findViewById(R.id.mina_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setDanhmuc();
    }

    private void setDanhmuc() {
        lstLesson = new ArrayList<>();
        for(int i=1; i<=25; i++)
            lstLesson.add(i);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_mina);
        recyclerView.setLayoutManager(layoutManager);
        LessonAdapter myAdapter= new LessonAdapter(this, lstLesson);
        recyclerView.setAdapter(myAdapter);
    }
}