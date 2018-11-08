package com.dicoding.paul.dictionary.BahasaToEnglish;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dicoding.paul.dictionary.DetailActivity;
import com.dicoding.paul.dictionary.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BahasaAdapter extends RecyclerView.Adapter<BahasaAdapter.BahasaViewHolder>{
    private ArrayList<BahasaModel> modelArrayList = new ArrayList<>();
    private Context context;

    public BahasaAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<BahasaModel> getModelArrayList() {
        return modelArrayList;
    }

    public void setModelArrayList(ArrayList<BahasaModel> modelArrayList) {
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public BahasaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_bahasa, parent, false);
        return new BahasaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BahasaViewHolder bahasaViewHolder, int position) {
        BahasaModel bahasaModel = getModelArrayList().get(position);

        bahasaViewHolder.tvBahasa.setText(bahasaModel.getKata());
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class BahasaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_bahasa) TextView tvBahasa;

        public BahasaViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            BahasaModel bahasaModel = getModelArrayList().get(getAdapterPosition());
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_BAHASA, bahasaModel);
            context.startActivity(intent);
        }
    }
}
