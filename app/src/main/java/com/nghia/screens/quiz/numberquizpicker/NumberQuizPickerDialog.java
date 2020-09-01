package com.nghia.screens.quiz.numberquizpicker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import com.nghia.R;

public class NumberQuizPickerDialog extends BottomSheetDialogFragment {

    private TextView vDone;
    private RecyclerView vNumbers;

    private SnapHelper snapHelper;
    private ZoomCenterLayoutManager layoutManager;
    private NumberAdapter adapter;

    private int selectedNumber = 25;

    private OnItemClickListener onItemClickListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_number_quiz_picker, container, false);
        initView(view);
        setupView();
        return view;
    }

    private void initView(View root) {
        vDone = root.findViewById(R.id.tv_done);
        vNumbers = root.findViewById(R.id.rv_number);
    }

    private void setupView() {
        setupRecycleView();
        vDone.setOnClickListener(view -> onItemClickListener.onDoneClick(getSelectedNumber()));
    }

    private void setupRecycleView() {
        layoutManager = new ZoomCenterLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        vNumbers.setLayoutManager(layoutManager);

        snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(vNumbers);

        adapter = new NumberAdapter();
        vNumbers.setAdapter(adapter);

        vNumbers.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    View centerView = snapHelper.findSnapView(layoutManager);
                    if (centerView != null)
                        selectedNumber = layoutManager.getPosition(centerView)+1;
                }
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public int getSelectedNumber() {
        return selectedNumber;
    }

    public interface OnItemClickListener {
        void onDoneClick(int position);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter = null;
    }
}
