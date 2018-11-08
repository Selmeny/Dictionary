package com.dicoding.paul.dictionary;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.dicoding.paul.dictionary.BahasaToEnglish.BahasaHelper;
import com.dicoding.paul.dictionary.BahasaToEnglish.BahasaModel;
import com.dicoding.paul.dictionary.EnglishToBahasa.EnglishHelper;
import com.dicoding.paul.dictionary.EnglishToBahasa.EnglishModel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new LoadData().execute();
    }

    private class LoadData extends AsyncTask<Void, Void, Void> {
        final String TAG = LoadData.class.getSimpleName();
        EnglishHelper englishHelper;
        BahasaHelper bahasaHelper;
        AppPreference preference;

        @Override
        protected void onPreExecute() {
            englishHelper = new EnglishHelper(SplashActivity.this);
            bahasaHelper = new BahasaHelper(SplashActivity.this);
            preference = new AppPreference(SplashActivity.this);
        }

        @Override
        protected Void doInBackground(Void... params) {
            Boolean firstRun = preference.getFirstRun();

            if (firstRun) {
                ArrayList<EnglishModel> englishModels = preLoadEnglishRaw();
                ArrayList<BahasaModel> bahasaModels = preLoadBahasaRaw();

                englishHelper.open();
                englishHelper.beginTransaction();
                try {
                    for (EnglishModel englishModel: englishModels) {
                        englishHelper.insert(englishModel);
                    }
                    englishHelper.setTransactionSuccess();

                } catch (Exception e) {
                    Log.e(TAG, "doInBackground: Exception");
                }
                englishHelper.endTransaction();
                englishHelper.close();

                bahasaHelper.open();
                bahasaHelper.beginTransaction();
                try {
                    for (BahasaModel bahasaModel: bahasaModels) {
                        bahasaHelper.insert(bahasaModel);
                    }
                    bahasaHelper.setTransactionSuccess();
                } catch (Exception e) {
                    Log.e(TAG, "doInBackground: Exception");
                }
                bahasaHelper.endTransaction();
                bahasaHelper.close();

                preference.setFirstRun(false);
            } else {
                try {
                    synchronized (this) {
                        this.wait(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public ArrayList<EnglishModel> preLoadEnglishRaw() {
        ArrayList<EnglishModel> arrayList = new ArrayList<>();
        String line;
        BufferedReader reader;

        try {
            Resources res = getResources();
            InputStream raw_english = res.openRawResource(R.raw.english_indonesia);

            reader = new BufferedReader(new InputStreamReader(raw_english));
            int count = 0;

            do {
                line = reader.readLine();
                String[] splitString = line.split("\t");

                EnglishModel englishModel;
                englishModel = new EnglishModel(splitString[0], splitString[1]);
                arrayList.add(englishModel);
                count++;
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public ArrayList<BahasaModel> preLoadBahasaRaw() {
        ArrayList<BahasaModel> arrayList = new ArrayList<>();
        String baris;
        BufferedReader reader;

        try {
            Resources res = getResources();
            InputStream raw_bahasa = res.openRawResource(R.raw.indonesia_english);

            reader = new BufferedReader(new InputStreamReader(raw_bahasa));
            int count = 0;

            do {
                baris = reader.readLine();
                String[] splitBaris = baris.split("\t");

                BahasaModel bahasaModel;
                bahasaModel = new BahasaModel(splitBaris[0], splitBaris[1]);
                arrayList.add(bahasaModel);
                count++;
            } while (baris != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
