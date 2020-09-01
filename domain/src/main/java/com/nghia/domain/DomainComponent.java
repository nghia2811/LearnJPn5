package com.nghia.domain;

import android.content.Context;

import com.nghia.domain.interactor.GrammarInteractor;
import com.nghia.domain.interactor.KanjiInteractor;
import com.nghia.domain.interactor.WordInteractor;
import com.nghia.domain.interactor.internal.GrammarInteractorImpl;
import com.nghia.domain.interactor.internal.KanjiInteractorImpl;
import com.nghia.domain.interactor.internal.WordInteractorImpl;

public class DomainComponent {
    static DomainComponent instance;

    private WordInteractor wordInteractor;
    private GrammarInteractor grammarInteractor;
    private KanjiInteractor kanjiInteractor;

    private DomainComponent() {
    }

    public static DomainComponent getInstance() {
        if (instance == null)
            instance = new DomainComponent();
        return instance;
    }

    public WordInteractor getWordInteractor(Context context) {
        if (wordInteractor == null)
            wordInteractor = new WordInteractorImpl(context);
        return wordInteractor;
    }

    public GrammarInteractor getGrammarInteractor(Context context) {
        if (grammarInteractor == null)
            grammarInteractor = new GrammarInteractorImpl(context);
        return grammarInteractor;
    }

    public KanjiInteractor getKanjiInteractor(Context context) {
        if (kanjiInteractor == null)
            kanjiInteractor = new KanjiInteractorImpl(context);
        return kanjiInteractor;
    }

}
