package com.nghia.screens.quiz.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import java.util.Random;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import com.nghia.domain.DomainComponent;
import com.nghia.domain.interactor.KanjiInteractor;
import com.nghia.domain.interactor.WordInteractor;
import com.nghia.domain.pojo.Kanji;
import com.nghia.domain.pojo.Word;
import com.nghia.R;
import com.nghia.screens.quiz.QuestionActivity;

public class TfFragment extends Fragment {

    CardView falseCheck, trueCheck;
    TextView tvVn, tvWord;
    private WordInteractor wordInteractor;
    private KanjiInteractor kanjiInteractor;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    int type;
    Random rand;
    int tf;

    public static TfFragment newInstance(int type) {
        TfFragment tfFragment = new TfFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        tfFragment.setArguments(bundle);
        return tfFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.item_tfquestion, container, false);
        tvWord = root.findViewById(R.id.tf_word);
        tvVn = root.findViewById(R.id.tf_vn);
        trueCheck = root.findViewById(R.id.true_check);
        falseCheck = root.findViewById(R.id.false_check);
        wordInteractor = DomainComponent.getInstance().getWordInteractor(getActivity());
        kanjiInteractor = DomainComponent.getInstance().getKanjiInteractor(getActivity());
        setupView();
        return root;
    }

    private void setupView() {
        rand = new Random();
        tf = rand.nextInt(2);

        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt("type", 1);
        }

        if (type == 1) loadWord();
        else loadKanji();

        trueCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((QuestionActivity) getActivity()).point += tf;
                click();
            }
        });

        falseCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((QuestionActivity) getActivity()).point += 1-tf;
                click();
            }
        });
    }

    private void click(){
        trueCheck.setClickable(false);
        falseCheck.setClickable(false);
        if(tf==1){
            trueCheck.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
            falseCheck.setCardBackgroundColor(getResources().getColor(R.color.colorGray));
        }
        else{
            trueCheck.setCardBackgroundColor(getResources().getColor(R.color.colorGray));
            falseCheck.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    private void loadWord(){
        Disposable disposable = wordInteractor.getWordsFromLocal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wordList -> {
                    Word randomElement = wordList.get(rand.nextInt(wordList.size()));
                    tvWord.setText(randomElement.getWord());
                    if (tf == 1) {
                        tvVn.setText(randomElement.getWordVN());
                    } else {
                        tvVn.setText(wordList.get(rand.nextInt(wordList.size())).getWordVN());
                    }
                }, throwable -> {
                });
        compositeDisposable.add(disposable);
    }

    private void loadKanji(){
        Disposable disposable = kanjiInteractor.getKanjisFromLocal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(kanjiList -> {
                    Kanji randomElement = kanjiList.get(rand.nextInt(kanjiList.size()));
                    tvWord.setText(randomElement.getKanji());
                    if (tf == 1) {
                        tvVn.setText(randomElement.getVn());
                    } else {
                        tvVn.setText(kanjiList.get(rand.nextInt(kanjiList.size())).getVn());
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

}
