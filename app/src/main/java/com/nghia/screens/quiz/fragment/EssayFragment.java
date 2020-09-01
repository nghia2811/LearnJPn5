package com.nghia.screens.quiz.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nghia.R;
import com.nghia.domain.DomainComponent;
import com.nghia.domain.interactor.KanjiInteractor;
import com.nghia.domain.interactor.WordInteractor;
import com.nghia.domain.pojo.Kanji;
import com.nghia.domain.pojo.Word;
import com.nghia.screens.quiz.QuestionActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EssayFragment extends Fragment {

    private WordInteractor wordInteractor = DomainComponent.getInstance().getWordInteractor(getActivity());
    private KanjiInteractor kanjiInteractor = DomainComponent.getInstance().getKanjiInteractor(getActivity());
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    TextView tvWord, answer, trueanswer;
    ImageView next, reset;
    Word word;
    Kanji kanji;
    List<String> lstHiragana, lstKanji, lstAnswer;
    String question;
    RecyclerView recyclerView;
    int type, nextQues;
    Random rd = new Random();

    public static EssayFragment newInstance(int type) {
        EssayFragment essayFragment = new EssayFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        essayFragment.setArguments(bundle);
        return essayFragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.item_essayquestion, container, false);
        tvWord = root.findViewById(R.id.eq_word);
        answer = root.findViewById(R.id.edt_eq);
        next = root.findViewById(R.id.quiz_next);
        reset = root.findViewById(R.id.quiz_reset);
        trueanswer = root.findViewById(R.id.eq_answer);
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt("type", 1);
            nextQues = bundle.getInt("next", 1);
        }

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 4, GridLayoutManager.VERTICAL, false);
        recyclerView = root.findViewById(R.id.rv_essay);
        recyclerView.setLayoutManager(layoutManager);

        setupList();

        if (type == 1) loadWord();
        else if (type == 2) loadKanji();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trueanswer.setVisibility(View.VISIBLE);
                if (type == 1) {
                    if (answer.equals(word.getWord())) ((QuestionActivity) getActivity()).point++;
                    trueanswer.setText(word.getWord());
                } else {
                    if (answer.equals(kanji.getKanji())) ((QuestionActivity) getActivity()).point++;
                    trueanswer.setText(kanji.getKanji());
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer.setText(null);
            }
        });

        return root;
    }

    private void loadWord() {
        lstAnswer = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            lstAnswer.add(lstHiragana.get(rd.nextInt(lstHiragana.size())));
        }

        Disposable disposable = wordInteractor.getWordsFromLocal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Word>>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void accept(List<Word> wordList) throws Exception {
                        word = wordList.get(rd.nextInt(wordList.size()));
                        question = word.getWord();

                        tvWord.setText(word.getWordVN());

                        for (int i = 0; i < question.length(); i++) {
                            lstAnswer.add(String.valueOf(question.charAt(i)));
                        }
                        List<String> sortedList = lstAnswer.stream().sorted().collect(Collectors.toList());
                        recyclerView.setAdapter(new EssayAdapter(EssayFragment.this, sortedList));
                    }
                }, throwable -> {
                });
        compositeDisposable.add(disposable);
    }

    private void loadKanji() {
        lstAnswer = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            lstAnswer.add(lstKanji.get(rd.nextInt(lstKanji.size())));
        }

        Disposable disposable = kanjiInteractor.getKanjisFromLocal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Kanji>>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void accept(List<Kanji> kanjiList) throws Exception {
                        kanji = kanjiList.get(rd.nextInt(kanjiList.size()));
                        question = kanji.getKanji();

                        tvWord.setText(kanji.getVn());

                        for (int i = 0; i < question.length(); i++) {
                            lstAnswer.add(String.valueOf(question.charAt(i)));
                        }
                        List<String> sortedList = lstAnswer.stream().sorted().collect(Collectors.toList());
                        recyclerView.setAdapter(new EssayAdapter(EssayFragment.this, sortedList));
                    }
                }, throwable -> {
                });
        compositeDisposable.add(disposable);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeDisposable.clear();
    }

    private void setupList() {
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


        lstKanji = new ArrayList<>();
        lstKanji.add("日");
        lstKanji.add("年");
        lstKanji.add("中");
        lstKanji.add("会");
        lstKanji.add("本");
        lstKanji.add("月");
        lstKanji.add("動");
        lstKanji.add("話");
        lstKanji.add("政");
        lstKanji.add("和");
        lstKanji.add("表");
        lstKanji.add("道");
    }
}
