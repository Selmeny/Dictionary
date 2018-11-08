package com.dicoding.paul.dictionary.EnglishToBahasa;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dicoding.paul.dictionary.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EnglishSearchFragment extends Fragment {
    public static String EXTRA_WORD = "extra_word";
    private EnglishAdapter adapter;
    private EnglishHelper helper;

    @BindView(R.id.rv_english_search) RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_english_search, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            String word = getArguments().getString(EXTRA_WORD);
            searchWord(word);
        }
    }

    public void searchWord(String word) {
        ButterKnife.bind(this, getActivity());

        helper = new EnglishHelper(getActivity());
        helper.open();
        ArrayList<EnglishModel> modelArrayList = helper.getDataByWord(word);
        helper.close();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new EnglishAdapter(getActivity());
        adapter.setModelArrayList(modelArrayList);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }
}
