package com.dicoding.paul.dictionary;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;


import com.dicoding.paul.dictionary.BahasaToEnglish.BahasaModel;
import com.dicoding.paul.dictionary.EnglishToBahasa.EnglishModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_ENGLISH = "extra_english";
    public static final String EXTRA_BAHASA = "extra_bahasa";

    @BindView(R.id.tv_word) TextView tvWord;
    @BindView(R.id.tv_definition) TextView tvDefinition;
    @BindView(R.id.toolbar_detail) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        EnglishModel englishModel = getIntent().getParcelableExtra(EXTRA_ENGLISH);
        BahasaModel bahasaModel = getIntent().getParcelableExtra(EXTRA_BAHASA);

        if (englishModel != null) {
            tvWord.setText(englishModel.getWord());
            tvDefinition.setText(englishModel.getDefinition());
        } else if (bahasaModel != null) {
            tvWord.setText(bahasaModel.getKata());
            tvDefinition.setText(bahasaModel.getDefinisi());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
