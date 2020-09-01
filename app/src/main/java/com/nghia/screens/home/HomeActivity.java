package com.nghia.screens.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import com.nghia.R;
import com.nghia.screens.kanji.KanjiActivity;

public class HomeActivity extends AppCompatActivity {

    List<Category> lstCategory;
    ImageView imageKanji;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imageKanji = findViewById(R.id.kanji_image);

        imageKanji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, KanjiActivity.class);
                startActivity(intent);
            }
        });
        setDanhmuc();
    }

    private void setDanhmuc() {
        lstCategory = new ArrayList<>();
        lstCategory.add(new Category("Bảng chữ cái", R.drawable.bangchucai));
        lstCategory.add(new Category("25 Bài Mina N5", R.drawable.mina25bai));
        lstCategory.add(new Category("Quiz", R.drawable.quiz));
        lstCategory.add(new Category("Từ vựng yêu thích", R.drawable.yeuthich));

        GridLayoutManager layoutManagerDanhmuc = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        RecyclerView recyclerViewDanhmuc = (RecyclerView) findViewById(R.id.rv_cate);
        recyclerViewDanhmuc.setLayoutManager(layoutManagerDanhmuc);
        CategoryAdapter myAdapterDanhmuc = new CategoryAdapter(this, lstCategory);
        recyclerViewDanhmuc.setAdapter(myAdapterDanhmuc);
    }
}