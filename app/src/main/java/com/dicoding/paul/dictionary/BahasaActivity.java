package com.dicoding.paul.dictionary;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.dicoding.paul.dictionary.BahasaToEnglish.BahasaAdapter;
import com.dicoding.paul.dictionary.BahasaToEnglish.BahasaHelper;
import com.dicoding.paul.dictionary.BahasaToEnglish.BahasaModel;
import com.dicoding.paul.dictionary.BahasaToEnglish.BahasaSearchFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.dicoding.paul.dictionary.BahasaToEnglish.BahasaSearchFragment.EXTRA_KATA;

public class BahasaActivity extends AppCompatActivity {
    private BahasaHelper bahasaHelper;
    private BahasaAdapter bahasaAdapter;
    private SearchView searchView;

    @BindView(R.id.toolbar_bahasa) Toolbar toolbar;
    @BindView(R.id.rv_bahasa) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bahasa);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        showRecyclerView();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        searchView.setQuery("",false);
        searchView.clearFocus();
        if (getFragmentManager().getBackStackEntryCount()>0) {
            for (int i = 0; i<getFragmentManager().getBackStackEntryCount(); ++i) {
                getFragmentManager().popBackStack();
            }
        }
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bahasa, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        searchView = (SearchView) searchItem.getActionView();
        searchView.onActionViewExpanded();
        searchView.clearFocus();
        searchView.setQueryHint(getResources().getString(R.string.cari));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.isEmpty()) {
                    BahasaSearchFragment fragment = new BahasaSearchFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString(BahasaSearchFragment.EXTRA_KATA, query);
                    fragment.setArguments(bundle);

                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.frame_bahasa_container, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                    searchView.clearFocus();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void showRecyclerView() {
        ButterKnife.bind(this);

        bahasaHelper = new BahasaHelper(this);
        bahasaHelper.open();
        ArrayList<BahasaModel> models = bahasaHelper.getAllData();
        bahasaHelper.close();

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        bahasaAdapter = new BahasaAdapter(this);
        bahasaAdapter.setModelArrayList(models);
        bahasaAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(bahasaAdapter);
        recyclerView.setHasFixedSize(true);
    }

}
