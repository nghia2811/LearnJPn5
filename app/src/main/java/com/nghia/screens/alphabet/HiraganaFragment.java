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

public class HiraganaFragment extends Fragment {
    List<String> lstHiragana;

    public static HiraganaFragment newInstance(String message) {
        HiraganaFragment hiraganaFragment = new HiraganaFragment();
        Bundle args = new Bundle();
        args.putString("title", message);
        hiraganaFragment.setArguments(args);
        return hiraganaFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_hiragana, container, false);

        setupView();
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 5, GridLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.rv_hiragana);
        recyclerView.setLayoutManager(layoutManager);
        AlphabetAdapter myAdapter= new AlphabetAdapter(getActivity(), lstHiragana);
        recyclerView.setAdapter(myAdapter);
        return root;
    }

    private void setupView() {
        lstHiragana = new ArrayList<>();
        lstHiragana.add("あ");
        lstHiragana.add("い");
        lstHiragana.add("う");
        lstHiragana.add("え");
        lstHiragana.add("お");
        lstHiragana.add("か");
        lstHiragana.add("き");
        lstHiragana.add("く");
        lstHiragana.add("け");
        lstHiragana.add("こ");
        lstHiragana.add("さ");
        lstHiragana.add("し");
        lstHiragana.add("す");
        lstHiragana.add("せ");
        lstHiragana.add("そ");
        lstHiragana.add("た");
        lstHiragana.add("ち");
        lstHiragana.add("つ");
        lstHiragana.add("て");
        lstHiragana.add("と");
        lstHiragana.add("な");
        lstHiragana.add("に");
        lstHiragana.add("ぬ");
        lstHiragana.add("ね");
        lstHiragana.add("の");
        lstHiragana.add("は");
        lstHiragana.add("ひ");
        lstHiragana.add("ふ");
        lstHiragana.add("へ");
        lstHiragana.add("ほ");
        lstHiragana.add("ま");
        lstHiragana.add("み");
        lstHiragana.add("む");
        lstHiragana.add("め");
        lstHiragana.add("も");
        lstHiragana.add("や");
        lstHiragana.add("");
        lstHiragana.add("ゆ");
        lstHiragana.add("");
        lstHiragana.add("よ");
        lstHiragana.add("ら");
        lstHiragana.add("り");
        lstHiragana.add("る");
        lstHiragana.add("れ");
        lstHiragana.add("ろ");
        lstHiragana.add("わ");
        lstHiragana.add("ゐ");
        lstHiragana.add("");
        lstHiragana.add("ゑ");
        lstHiragana.add("を");
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
