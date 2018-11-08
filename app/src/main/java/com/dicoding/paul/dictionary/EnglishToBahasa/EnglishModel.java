package com.dicoding.paul.dictionary.EnglishToBahasa;

import android.os.Parcel;
import android.os.Parcelable;

public class EnglishModel implements Parcelable {
    private int id;
    private String word;
    private String definition;

    public EnglishModel() {

    }

    public EnglishModel(String word, String definition) {
        this.word = word;
        this.definition = definition;
    }

    public EnglishModel(int id, String word, String definition) {
        this.id = id;
        this.word = word;
        this.definition = definition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.word);
        dest.writeString(this.definition);
    }

    protected EnglishModel(Parcel in) {
        this.id = in.readInt();
        this.word = in.readString();
        this.definition = in.readString();
    }

    public static final Parcelable.Creator<EnglishModel> CREATOR = new Parcelable.Creator<EnglishModel>() {
        @Override
        public EnglishModel createFromParcel(Parcel source) {
            return new EnglishModel(source);
        }

        @Override
        public EnglishModel[] newArray(int size) {
            return new EnglishModel[size];
        }
    };
}
