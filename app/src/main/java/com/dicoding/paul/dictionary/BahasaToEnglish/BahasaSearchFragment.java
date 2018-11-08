package com.dicoding.paul.dictionary.BahasaToEnglish;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dicoding.paul.dictionary.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BahasaSearchFragment extends Fragment {
    public static String EXTRA_KATA = "extra_word";
    private BahasaAdapter adapter;
    private BahasaHelper helper;

    @BindView(R.id.rv_bahasa_search) RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bahasa_search, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            String kata = getArguments().getString(EXTRA_KATA);
            searchWord(kata);
        }
    }

    public void searchWord(String kata) {
        ButterKnife.bind(this, getActivity());

        helper = new BahasaHelper(getActivity());
        helper.open();
        ArrayList<BahasaModel> modelArrayList = helper.getDataByKata(kata);
        helper.close();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new BahasaAdapter(getActivity());
        adapter.setModelArrayList(modelArrayList);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }
}
