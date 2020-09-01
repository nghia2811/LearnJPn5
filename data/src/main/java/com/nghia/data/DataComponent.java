package com.nghia.data;

import android.content.Context;

import com.nghia.data.local.LocalRepo;
import com.nghia.data.local.LocalRepoImpl;
import com.nghia.data.remote.ApiRepo;
import com.nghia.data.remote.ApiRepoImpl;

public class DataComponent {
    static DataComponent instance;

    public ApiRepo apiRepo = new ApiRepoImpl();
    public LocalRepo localRepo;

    private DataComponent() {
    }

    public static DataComponent getInstance() {
        if (instance == null) {
            synchronized (DataComponent.class) {
                if (instance == null)
                    instance = new DataComponent();
            }
        }
        return instance;
    }

    public LocalRepo getLocalRepo(Context context) {
        if (localRepo == null)
            localRepo = new LocalRepoImpl(context);
        return localRepo;
    }
}