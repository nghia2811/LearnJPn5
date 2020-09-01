package com.nghia.screens.quiz.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.nghia.R;
import com.nghia.domain.DomainComponent;
import com.nghia.domain.interactor.KanjiInteractor;
import com.nghia.domain.interactor.WordInteractor;
import com.nghia.domain.pojo.Kanji;
import com.nghia.domain.pojo.Word;
import com.nghia.screens.quiz.QuestionActivity;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SelectedFragment extends Fragment {

    private WordInteractor wordInteractor = DomainComponent.getInstance().getWordInteractor(getActivity());
    private KanjiInteractor kanjiInteractor = DomainComponent.getInstance().getKanjiInteractor(getActivity());
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    Random rd = new Random();
    TextView c1, c2, c3, c4;
    TextView tvWord;
    Word word;
    Kanji kanji;
    List<Word> words;
    List<Kanji> kanjis;
    List<TextView> list;
    int i, type;

    public static SelectedFragment newInstance(int type) {
        SelectedFragment selectedFragment = new SelectedFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        selectedFragment.setArguments(bundle);
        return selectedFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.item_selectedquestion, container, false);
        tvWord = root.findViewById(R.id.sq_word);
        c1 = root.findViewById(R.id.answer1);
        c2 = root.findViewById(R.id.answer2);
        c3 = root.findViewById(R.id.answer3);
        c4 = root.findViewById(R.id.answer4);
        setupView();
        return root;
    }

    private void setupView() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt("type", 1);
        }

        list = Arrays.asList(new TextView[]{c1, c2, c3, c4});
        i = rd.nextInt(list.size());

        if (type == 1) {
            loadWord();
        } else loadKanji();
    }


    private void loadWord() {
        Disposable disposable = wordInteractor.getWordsFromLocal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wordList -> {
                    words = wordList;
                    word = wordList.get(rd.nextInt(wordList.size()));
                    tvWord.setText(word.getWord());
                    list.get(i).setText(word.getWordVN());
                    for (int j = 0; j < 4; j++) {
                        if (j != i) {
                            list.get(j).setText(words.get(rd.nextInt(words.size())).getWordVN());
                        }
                    }
                }, throwable -> {
                });
        compositeDisposable.add(disposable);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickEvent();
                if (c1.getText().equals(word.getWordVN())) {
                    ((QuestionActivity) getActivity()).point++;
                    c1.setBackgroundResource(R.drawable.border_true);
                } else {
                    c1.setBackgroundResource(R.drawable.border_false);
                    list.get(i).setBackgroundResource(R.drawable.border_true);
                }
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickEvent();
                if (c2.getText().equals(word.getWordVN())) {
                    ((QuestionActivity) getActivity()).point++;
                    c2.setBackgroundResource(R.drawable.border_true);
                } else {
                    c2.setBackgroundResource(R.drawable.border_false);
                    list.get(i).setBackgroundResource(R.drawable.border_true);
                }

            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickEvent();
                if (c3.getText().equals(word.getWordVN())) {
                    ((QuestionActivity) getActivity()).point++;
                    c3.setBackgroundResource(R.drawable.border_true);
                } else {
                    c3.setBackgroundResource(R.drawable.border_false);
                    list.get(i).setBackgroundResource(R.drawable.border_true);
                }
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickEvent();
                if (c4.getText().equals(word.getWordVN())) {
                    ((QuestionActivity) getActivity()).point++;
                    c4.setBackgroundResource(R.drawable.border_true);
                } else {
                    c4.setBackgroundResource(R.drawable.border_false);
                    list.get(i).setBackgroundResource(R.drawable.border_true);
                }
            }
        });
    }

    private void loadKanji() {
        Disposable disposable = kanjiInteractor.getKanjisFromLocal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(kanjiList -> {
                    kanjis = kanjiList;
                    kanji = kanjiList.get(rd.nextInt(kanjiList.size()));
                    tvWord.setText(kanji.getKanji());
                    list.get(i).setText(kanji.getVn());
                    for (int j = 0; j < 4; j++) {
                        if (j != i) {
                            list.get(j).setText(kanjis.get(rd.nextInt(kanjis.size())).getVn());
                        }
                    }
                }, throwable -> {
                });
        compositeDisposable.add(disposable);


        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickEvent();
                if (c1.getText().equals(kanji.getVn())) {
                    ((QuestionActivity) getActivity()).point++;
                    c1.setBackgroundResource(R.drawable.border_true);
                } else {
                    c1.setBackgroundResource(R.drawable.border_false);
                    list.get(i).setBackgroundResource(R.drawable.border_true);
                }
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickEvent();
                if (c2.getText().equals(kanji.getVn())) {
                    ((QuestionActivity) getActivity()).point++;
                    c2.setBackgroundResource(R.drawable.border_true);
                } else {
                    c2.setBackgroundResource(R.drawable.border_false);
                    list.get(i).setBackgroundResource(R.drawable.border_true);
                }
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickEvent();
                if (c3.getText().equals(kanji.getVn())) {
                    ((QuestionActivity) getActivity()).point++;
                    c3.setBackgroundResource(R.drawable.border_true);
                } else {
                    c3.setBackgroundResource(R.drawable.border_false);
                    list.get(i).setBackgroundResource(R.drawable.border_true);
                }
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickEvent();
                if (c4.getText().equals(kanji.getVn())) {
                    ((QuestionActivity) getActivity()).point++;
                    c4.setBackgroundResource(R.drawable.border_true);
                } else {
                    c4.setBackgroundResource(R.drawable.border_false);
                    list.get(i).setBackgroundResource(R.drawable.border_true);
                }
            }
        });
    }

    private void clickEvent() {
        c1.setClickable(false);
        c2.setClickable(false);
        c3.setClickable(false);
        c4.setClickable(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeDisposable.clear();
    }

}
