package com.dicoding.paul.dictionary;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.dicoding.paul.dictionary.EnglishToBahasa.EnglishAdapter;
import com.dicoding.paul.dictionary.EnglishToBahasa.EnglishHelper;
import com.dicoding.paul.dictionary.EnglishToBahasa.EnglishModel;
import com.dicoding.paul.dictionary.EnglishToBahasa.EnglishSearchFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private EnglishHelper englishHelper;
    private EnglishAdapter englishAdapter;
    private SearchView searchView;

    @BindView(R.id.toolbar_main) Toolbar toolbar;
    @BindView(R.id.drawer_layout_main) DrawerLayout drawer;
    @BindView(R.id.nav_view_main) NavigationView navigationView;
    @BindView(R.id.rv_english) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        showRecyclerView();
    }

    @Override
    public void onBackPressed() {
        ButterKnife.bind(this);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getFragmentManager().getBackStackEntryCount()>0){
            for (int i = 0; i < getFragmentManager().getBackStackEntryCount(); ++i) {
                getFragmentManager().popBackStack();
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        searchView.setQuery("",false);
        searchView.clearFocus();
        if (getFragmentManager().getBackStackEntryCount()>0) {
            for (int i = 0; i < getFragmentManager().getBackStackEntryCount(); ++i) {
                getFragmentManager().popBackStack();
            }
        }
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        searchView = (SearchView) searchItem.getActionView();
        searchView.onActionViewExpanded();
        searchView.clearFocus();
        searchView.setQueryHint(getResources().getString(R.string.search));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.isEmpty()) {
                    EnglishSearchFragment fragment = new EnglishSearchFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString(EnglishSearchFragment.EXTRA_WORD, query);
                    fragment.setArguments(bundle);

                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.frame_english_container, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                    searchView.clearFocus();
                }
                return true;
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        ButterKnife.bind(this);
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_english_to_bahasa) {
            drawer.closeDrawer(GravityCompat.START);

        } else if (id == R.id.nav_bahasa_to_english) {
            Intent intent = new Intent(MainActivity.this, BahasaActivity.class);
            startActivity(intent);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showRecyclerView() {
        ButterKnife.bind(this);

        englishHelper = new EnglishHelper(this);
        englishHelper.open();
        ArrayList<EnglishModel> models = englishHelper.getAllData();
        englishHelper.close();

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        englishAdapter = new EnglishAdapter(this);
        englishAdapter.setModelArrayList(models);
        englishAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(englishAdapter);
        recyclerView.setHasFixedSize(true);
    }
}
