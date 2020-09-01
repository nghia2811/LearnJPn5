package com.nghia.screens.mina;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import com.nghia.domain.DomainComponent;
import com.nghia.domain.interactor.GrammarInteractor;
import com.nghia.domain.pojo.Grammar;
import com.nghia.R;

public class GrammarFragment extends Fragment {

    private GrammarInteractor grammarInteractor = DomainComponent.getInstance().getGrammarInteractor(getActivity());
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    int lesson_num;
    ProgressBar frameLayout;
    WebView webview;

    public static GrammarFragment newInstance(Integer lesson) {
        GrammarFragment grammarFragment = new GrammarFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("lesson_num", lesson);
        grammarFragment.setArguments(bundle);
        return grammarFragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_grammar, container, false);
        frameLayout = root.findViewById(R.id.progressBar);
        webview = root.findViewById(R.id.tv_grammar);

        setupView();
        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setupView() {
        frameLayout.setVisibility(View.VISIBLE);

        Bundle bundle = getArguments();
        if (bundle != null) {
            lesson_num = bundle.getInt("lesson_num");
        }

        getGrammarFromLocal();
    }


    private void getGrammarFromLocal() {
        Disposable disposable = grammarInteractor.getGrammarFromLocal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(grammars -> {
                    if(grammars.size() == 0)
                        getGrammarFromRemote();
                    else{
                        for(Grammar grammar : grammars){
                            if(grammar.getLesson() == lesson_num)
                                webview.loadDataWithBaseURL(null, grammar.getContent(), "text/html", "utf-8", null);
                        }
                        frameLayout.setVisibility(View.GONE);
                    }

                }, throwable -> {
                    frameLayout.setVisibility(View.GONE);
                });
        compositeDisposable.add(disposable);
    }

    private void getGrammarFromRemote() {
        Disposable disposable = grammarInteractor.getGrammarList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(grammars -> {
                    saveGrammarToLocal(grammars);
                }, throwable -> {
                });
        compositeDisposable.add(disposable);
    }

    private void saveGrammarToLocal(List<Grammar> grammars) {
        Disposable disposable = grammarInteractor.insertGrammars(grammars)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();

        compositeDisposable.add(disposable);
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
