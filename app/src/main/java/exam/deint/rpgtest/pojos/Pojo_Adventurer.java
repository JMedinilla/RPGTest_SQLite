package exam.deint.rpgtest.pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class Pojo_Adventurer implements Parcelable {
    public static final String ADVENTURER_RACE_HUMAN = "Human";
    public static final String ADVENTURER_RACE_ORC = "Orc";
    public static final String ADVENTURER_RACE_ELF = "Elf";
    public static final String ADVENTURER_RACE_DWARF = "Dwarf";
    public static final String ADVENTURER_RACE_DEMON = "Demon";

    public static final String ADVENTURER_ALIGNMENT_GOOD_LAWFUL = "Lawful good";
    public static final String ADVENTURER_ALIGNMENT_GOOD_NEUTRAL = "Neutral good";
    public static final String ADVENTURER_ALIGNMENT_GOOD_CHAOTIC = "Chaotic good";
    public static final String ADVENTURER_ALIGNMENT_NEUTRAL_LAWFUL = "Lawful neutral";
    public static final String ADVENTURER_ALIGNMENT_NEUTRAL_NEUTRAL = "True neutral";
    public static final String ADVENTURER_ALIGNMENT_NEUTRAL_CHAOTIC = "Chaotic neutral";
    public static final String ADVENTURER_ALIGNMENT_EVIL_LAWFUL = "Lawful evil";
    public static final String ADVENTURER_ALIGNMENT_EVIL_NEUTRAL = "Neutral evil";
    public static final String ADVENTURER_ALIGNMENT_EVIL_CHAOTIC = "Chaotic evil";

    private int ad_id;
    private String ad_name;
    private String ad_race;
    private String ad_alignment;
    private int ad_class;
    private int ad_str;
    private int ad_dex;
    private int ad_int;

    public Pojo_Adventurer(String ad_name, String ad_race, String ad_alignment, int ad_class, int ad_str, int ad_dex, int ad_int) {
        this.ad_name = ad_name;
        this.ad_race = ad_race;
        this.ad_alignment = ad_alignment;
        this.ad_class = ad_class;
        this.ad_str = ad_str;
        this.ad_dex = ad_dex;
        this.ad_int = ad_int;
    }

    public Pojo_Adventurer(int ad_id, String ad_name, String ad_race, String ad_alignment, int ad_class, int ad_str, int ad_dex, int ad_int) {
        this.ad_id = ad_id;
        this.ad_name = ad_name;
        this.ad_race = ad_race;
        this.ad_alignment = ad_alignment;
        this.ad_class = ad_class;
        this.ad_str = ad_str;
        this.ad_dex = ad_dex;
        this.ad_int = ad_int;
    }

    private Pojo_Adventurer(Parcel in) {
        ad_id = in.readInt();
        ad_name = in.readString();
        ad_race = in.readString();
        ad_alignment = in.readString();
        ad_class = in.readInt();
        ad_str = in.readInt();
        ad_dex = in.readInt();
        ad_int = in.readInt();
    }

    public static final Creator<Pojo_Adventurer> CREATOR = new Creator<Pojo_Adventurer>() {
        @Override
        public Pojo_Adventurer createFromParcel(Parcel in) {
            return new Pojo_Adventurer(in);
        }

        @Override
        public Pojo_Adventurer[] newArray(int size) {
            return new Pojo_Adventurer[size];
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
