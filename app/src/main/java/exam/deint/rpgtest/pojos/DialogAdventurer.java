package exam.deint.rpgtest.pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class DialogAdventurer implements Parcelable {
    private int ad_id;
    private String ad_name;
    private String ad_race;
    private String ad_alignment;
    private String ad_class;
    private String ad_weapon;
    private String ad_role;
    private int ad_str;
    private int ad_dex;
    private int ad_int;

    public DialogAdventurer(int ad_id, String ad_name, String ad_race, String ad_alignment,
                            String ad_class, String ad_weapon, String ad_role,
                            int ad_str, int ad_dex, int ad_int) {
        this.ad_id = ad_id;
        this.ad_name = ad_name;
        this.ad_race = ad_race;
        this.ad_alignment = ad_alignment;
        this.ad_class = ad_class;
        this.ad_weapon = ad_weapon;
        this.ad_role = ad_role;
        this.ad_str = ad_str;
        this.ad_dex = ad_dex;
        this.ad_int = ad_int;
    }

    private DialogAdventurer(Parcel in) {
        ad_id = in.readInt();
        ad_name = in.readString();
        ad_race = in.readString();
        ad_alignment = in.readString();
        ad_class = in.readString();
        ad_weapon = in.readString();
        ad_role = in.readString();
        ad_str = in.readInt();
        ad_dex = in.readInt();
        ad_int = in.readInt();
    }

    public static final Creator<DialogAdventurer> CREATOR = new Creator<DialogAdventurer>() {
        @Override
        public DialogAdventurer createFromParcel(Parcel in) {
            return new DialogAdventurer(in);
        }

        @Override
        public DialogAdventurer[] newArray(int size) {
            return new DialogAdventurer[size];
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

    public String getAd_class() {
        return ad_class;
    }

    public void setAd_class(String ad_class) {
        this.ad_class = ad_class;
    }

    public String getAd_weapon() {
        return ad_weapon;
    }

    public void setAd_weapon(String ad_weapon) {
        this.ad_weapon = ad_weapon;
    }

    public String getAd_role() {
        return ad_role;
    }

    public void setAd_role(String ad_role) {
        this.ad_role = ad_role;
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
        parcel.writeString(ad_class);
        parcel.writeString(ad_weapon);
        parcel.writeString(ad_role);
        parcel.writeInt(ad_str);
        parcel.writeInt(ad_dex);
        parcel.writeInt(ad_int);
    }
}
