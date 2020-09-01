package com.nghia.screens.mina;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import com.nghia.domain.DomainComponent;
import com.nghia.domain.interactor.WordInteractor;
import com.nghia.domain.pojo.Word;
import com.nghia.R;
import com.nghia.screens.favourite.WordAdapter;

public class NewwordFragment extends Fragment implements WordAdapter.WordItemViewHolder.OnWordClickListener {

    private WordInteractor wordInteractor = DomainComponent.getInstance().getWordInteractor(getActivity());
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    int lesson_num;
    View frameLayout;
    RecyclerView recyclerView;
    WordAdapter wordAdapter;
    TextToSpeech tts;

    public static NewwordFragment newInstance(Integer lesson) {
        NewwordFragment newwordFragment = new NewwordFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("lesson_num", lesson);
        newwordFragment.setArguments(bundle);
        return newwordFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_newwords, container, false);
        frameLayout = root.findViewById(R.id.fr_newword);
        recyclerView = root.findViewById(R.id.rv_newwords);

        setupView();
        return root;
    }

    private void setupView() {
        frameLayout.setVisibility(View.VISIBLE);

        Bundle bundle = getArguments();
        if (bundle != null) {
            lesson_num = bundle.getInt("lesson_num");
        }

        tts = new TextToSpeech(getActivity().getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.JAPAN);
                    tts.setSpeechRate(1.0f);
                }
                else if (status == TextToSpeech.LANG_MISSING_DATA)
                    Toast.makeText(getActivity(), "Missing language data", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "Text To Speech error", Toast.LENGTH_SHORT).show();
            }
        });

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        getWordsFromLocal();
    }

    private void getWordsFromLocal(){
        Disposable disposable = wordInteractor.getWordsFromLocal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wordList -> {
                    if(wordList.size() == 0)
                        getWordsFromRemote();
                    else {
                        List<Word> words = new ArrayList<>();
                        for (Word word : wordList)
                            if (Integer.parseInt(word.getLesson()) == lesson_num) words.add(word);
                        wordAdapter = new WordAdapter(getActivity(), words);
                        wordAdapter.notifyDataSetChanged();
                        wordAdapter.setOnWordClickListener(this);

                        recyclerView.setAdapter(wordAdapter);
                        frameLayout.setVisibility(View.GONE);
                    }
                }, throwable -> {
                    frameLayout.setVisibility(View.GONE);
                });
        compositeDisposable.add(disposable);
    }

    private void getWordsFromRemote() {
        Disposable disposable = wordInteractor.getWordList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((List<Word> words) -> {
                    saveWordsToLocal(words);
                }, throwable -> {
                });
        compositeDisposable.add(disposable);
    }

    private void saveWordsToLocal(List<Word> words) {
        Disposable disposable = wordInteractor.insertWords(words)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();

        compositeDisposable.add(disposable);
    }

    private void updateWordFavorite(Word word, Action onComplete, Consumer<Throwable> throwable) {
        Disposable disposable = wordInteractor.updateFavoriteWord(word)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onComplete, throwable);
        compositeDisposable.add(disposable);
    }

    @Override
    public void onFavoriteIconClick(int position) {
        Word word = wordAdapter.getWords().get(position);
        if (word.getIsFavorite()) {
            word.setIsFavorite(false);
        } else {
            word.setIsFavorite(true);
        }
        updateWordFavorite(word, () -> wordAdapter.notifyItemChanged(position), throwable -> {
            Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onSpeakerIconClick(int position) {
        String utteranceId = UUID.randomUUID().toString();
        String word = wordAdapter.getWords().get(position).getWord();
        tts.speak(word, TextToSpeech.QUEUE_FLUSH, null, utteranceId);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeDisposable.clear();
    }
}
