package com.dicoding.paul.dictionary.EnglishToBahasa;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dicoding.paul.dictionary.DetailActivity;
import com.dicoding.paul.dictionary.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EnglishAdapter extends RecyclerView.Adapter<EnglishAdapter.EnglishViewHolder> {
    private ArrayList<EnglishModel> modelArrayList = new ArrayList<>();
    private Context context;

    public EnglishAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<EnglishModel> getModelArrayList() {
        return modelArrayList;
    }

    public void setModelArrayList(ArrayList<EnglishModel> modelArrayList) {
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public EnglishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_english, parent, false);
        return new EnglishViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EnglishViewHolder englishViewHolder, int position) {
        EnglishModel englishModel = getModelArrayList().get(position);

        englishViewHolder.tvEnglish.setText(englishModel.getWord());
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class EnglishViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_english) TextView tvEnglish;

        public EnglishViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            EnglishModel englishModel = getModelArrayList().get(getAdapterPosition());
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_ENGLISH, englishModel);
            context.startActivity(intent);
        }
    }
}
