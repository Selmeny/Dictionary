package com.dicoding.paul.dictionary.BahasaToEnglish;

import android.os.Parcel;
import android.os.Parcelable;

public class BahasaModel implements Parcelable {
    private int id;
    private String kata;
    private String definisi;

    public BahasaModel() {

    }

    public BahasaModel(String kata, String definisi) {
        this.kata = kata;
        this.definisi = definisi;
    }

    public BahasaModel(int id, String kata, String definisi) {
        this.id = id;
        this.kata = kata;
        this.definisi = definisi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKata() {
        return kata;
    }

    public void setKata(String kata) {
        this.kata = kata;
    }

    public String getDefinisi() {
        return definisi;
    }

    public void setDefinisi(String definisi) {
        this.definisi = definisi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.kata);
        dest.writeString(this.definisi);
    }

    protected BahasaModel(Parcel in) {
        this.id = in.readInt();
        this.kata = in.readString();
        this.definisi = in.readString();
    }

    public static final Parcelable.Creator<BahasaModel> CREATOR = new Parcelable.Creator<BahasaModel>() {
        @Override
        public BahasaModel createFromParcel(Parcel source) {
            return new BahasaModel(source);
        }

        @Override
        public BahasaModel[] newArray(int size) {
            return new BahasaModel[size];
        }
    };
}
