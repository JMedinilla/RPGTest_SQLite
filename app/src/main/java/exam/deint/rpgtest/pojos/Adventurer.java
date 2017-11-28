package exam.deint.rpgtest.pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class Adventurer implements Parcelable {

    private int ad_id;
    private String ad_name;
    private String ad_race;
    private String ad_alignment;
    private int ad_class;
    private int ad_str;
    private int ad_dex;
    private int ad_int;

    public Adventurer(String ad_name, String ad_race, String ad_alignment, int ad_class, int ad_str, int ad_dex, int ad_int) {
        this.ad_name = ad_name;
        this.ad_race = ad_race;
        this.ad_alignment = ad_alignment;
        this.ad_class = ad_class;
        this.ad_str = ad_str;
        this.ad_dex = ad_dex;
        this.ad_int = ad_int;
    }

    public Adventurer(int ad_id, String ad_name, String ad_race, String ad_alignment, int ad_class, int ad_str, int ad_dex, int ad_int) {
        this.ad_id = ad_id;
        this.ad_name = ad_name;
        this.ad_race = ad_race;
        this.ad_alignment = ad_alignment;
        this.ad_class = ad_class;
        this.ad_str = ad_str;
        this.ad_dex = ad_dex;
        this.ad_int = ad_int;
    }

    private Adventurer(Parcel in) {
        ad_id = in.readInt();
        ad_name = in.readString();
        ad_race = in.readString();
        ad_alignment = in.readString();
        ad_class = in.readInt();
        ad_str = in.readInt();
        ad_dex = in.readInt();
        ad_int = in.readInt();
    }

    public static final Creator<Adventurer> CREATOR = new Creator<Adventurer>() {
        @Override
        public Adventurer createFromParcel(Parcel in) {
            return new Adventurer(in);
        }

        @Override
        public Adventurer[] newArray(int size) {
            return new Adventurer[size];
        }
    };

    public int getAd_id() {
        return ad_id;
    }

    public void setAd_id(int ad_id) {
        this.ad_id = ad_id;
    }

    public String getAd_name() {
        return ad_name;
    }

    public void setAd_name(String ad_name) {
        this.ad_name = ad_name;
    }

    public String getAd_race() {
        return ad_race;
    }

    public void setAd_race(String ad_race) {
        this.ad_race = ad_race;
    }

    public String getAd_alignment() {
        return ad_alignment;
    }

    public void setAd_alignment(String ad_alignment) {
        this.ad_alignment = ad_alignment;
    }

    public int getAd_class() {
        return ad_class;
    }

    public void setAd_class(int ad_class) {
        this.ad_class = ad_class;
    }

    public int getAd_str() {
        return ad_str;
    }

    public void setAd_str(int ad_str) {
        this.ad_str = ad_str;
    }

    public int getAd_dex() {
        return ad_dex;
    }

    public void setAd_dex(int ad_dex) {
        this.ad_dex = ad_dex;
    }

    public int getAd_int() {
        return ad_int;
    }

    public void setAd_int(int ad_int) {
        this.ad_int = ad_int;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ad_id);
        parcel.writeString(ad_name);
        parcel.writeString(ad_race);
        parcel.writeString(ad_alignment);
        parcel.writeInt(ad_class);
        parcel.writeInt(ad_str);
        parcel.writeInt(ad_dex);
        parcel.writeInt(ad_int);
    }
}
