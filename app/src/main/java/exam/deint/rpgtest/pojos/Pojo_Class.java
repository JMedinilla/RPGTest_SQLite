package exam.deint.rpgtest.pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class Pojo_Class implements Parcelable {
    public static final String CLASS_ROLE_DPS = "DPS";
    public static final String CLASS_ROLE_TANK = "TANK";
    public static final String CLASS_ROLE_HEALER = "HEALER";

    private int cl_id;
    private String cl_name;
    private String cl_weapon;
    private String cl_role;

    public Pojo_Class(String cl_name, String cl_weapon, String cl_role) {
        this.cl_name = cl_name;
        this.cl_weapon = cl_weapon;
        this.cl_role = cl_role;
    }

    public Pojo_Class(int cl_id, String cl_name, String cl_weapon, String cl_role) {
        this.cl_id = cl_id;
        this.cl_name = cl_name;
        this.cl_weapon = cl_weapon;
        this.cl_role = cl_role;
    }

    private Pojo_Class(Parcel in) {
        cl_id = in.readInt();
        cl_name = in.readString();
        cl_weapon = in.readString();
        cl_role = in.readString();
    }

    public static final Creator<Pojo_Class> CREATOR = new Creator<Pojo_Class>() {
        @Override
        public Pojo_Class createFromParcel(Parcel in) {
            return new Pojo_Class(in);
        }

        @Override
        public Pojo_Class[] newArray(int size) {
            return new Pojo_Class[size];
        }
    };

    public int getCl_id() {
        return cl_id;
    }

    public void setCl_id(int cl_id) {
        this.cl_id = cl_id;
    }

    public String getCl_name() {
        return cl_name;
    }

    public void setCl_name(String cl_name) {
        this.cl_name = cl_name;
    }

    public String getCl_weapon() {
        return cl_weapon;
    }

    public void setCl_weapon(String cl_weapon) {
        this.cl_weapon = cl_weapon;
    }

    public String getCl_role() {
        return cl_role;
    }

    public void setCl_role(String cl_role) {
        this.cl_role = cl_role;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(cl_id);
        parcel.writeString(cl_name);
        parcel.writeString(cl_weapon);
        parcel.writeString(cl_role);
    }
}
