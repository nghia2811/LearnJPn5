package com.nghia.screens.alphabet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.nghia.R;

public class KatakanaFragment extends Fragment {
    List<String> lstKatakana;

    public static KatakanaFragment newInstance(String message) {
        KatakanaFragment katakanaFragment = new KatakanaFragment();
        Bundle args = new Bundle();
        args.putString("title", message);
        katakanaFragment.setArguments(args);
        return katakanaFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_katakana, container, false);
        setupView();
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 5, GridLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.rv_katakana);
        recyclerView.setLayoutManager(layoutManager);
        AlphabetAdapter myAdapter = new AlphabetAdapter(getActivity(), lstKatakana);
        recyclerView.setAdapter(myAdapter);
        return root;
    }

    private void setupView() {
        lstKatakana = new ArrayList<>();
        lstKatakana.add("ア");
        lstKatakana.add("イ");
        lstKatakana.add("ウ");
        lstKatakana.add("エ");
        lstKatakana.add("オ");
        lstKatakana.add("カ");
        lstKatakana.add("キ");
        lstKatakana.add("ク");
        lstKatakana.add("ケ");
        lstKatakana.add("コ");
        lstKatakana.add("サ");
        lstKatakana.add("シ");
        lstKatakana.add("ス");
        lstKatakana.add("セ");
        lstKatakana.add("ソ");
        lstKatakana.add("タ");
        lstKatakana.add("チ");
        lstKatakana.add("ツ");
        lstKatakana.add("テ");
        lstKatakana.add("ト");
        lstKatakana.add("ナ");
        lstKatakana.add("ニ");
        lstKatakana.add("ヌ");
        lstKatakana.add("ネ");
        lstKatakana.add("ノ");
        lstKatakana.add("ハ");
        lstKatakana.add("ヒ");
        lstKatakana.add("フ");
        lstKatakana.add("ヘ");
        lstKatakana.add("ホ");
        lstKatakana.add("マ");
        lstKatakana.add("ミ");
        lstKatakana.add("ム");
        lstKatakana.add("メ");
        lstKatakana.add("モ");
        lstKatakana.add("ヤ");
        lstKatakana.add("");
        lstKatakana.add("ユ");
        lstKatakana.add("");
        lstKatakana.add("ヨ");
        lstKatakana.add("ラ");
        lstKatakana.add("リ");
        lstKatakana.add("ル");
        lstKatakana.add("レ");
        lstKatakana.add("ロ");
        lstKatakana.add("ワ");
        lstKatakana.add("ヰ");
        lstKatakana.add("");
        lstKatakana.add("ヱ");
        lstKatakana.add("ヲ");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }
}
