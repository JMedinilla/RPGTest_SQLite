package exam.deint.rpgtest.pojos;

public class AdapterPojo_Adventurer {
    private int rad_id;
    private String rad_name;
    private String rad_race;
    private String rad_class;
    private int rad_str;
    private int rad_dex;
    private int rad_int;

    public AdapterPojo_Adventurer(int rad_id, String rad_name, String rad_race, String rad_class, int rad_str, int rad_dex, int rad_int) {
        this.rad_id = rad_id;
        this.rad_name = rad_name;
        this.rad_race = rad_race;
        this.rad_class = rad_class;
        this.rad_str = rad_str;
        this.rad_dex = rad_dex;
        this.rad_int = rad_int;
    }

    public int getRad_id() {
        return rad_id;
    }

    public void setRad_id(int rad_id) {
        this.rad_id = rad_id;
    }

    public String getRad_name() {
        return rad_name;
    }

    public void setRad_name(String rad_name) {
        this.rad_name = rad_name;
    }

    public String getRad_race() {
        return rad_race;
    }

    public void setRad_race(String rad_race) {
        this.rad_race = rad_race;
    }

    public String getRad_class() {
        return rad_class;
    }

    public void setRad_class(String rad_class) {
        this.rad_class = rad_class;
    }

    public int getRad_str() {
        return rad_str;
    }

    public void setRad_str(int rad_str) {
        this.rad_str = rad_str;
    }

    public int getRad_dex() {
        return rad_dex;
    }

    public void setRad_dex(int rad_dex) {
        this.rad_dex = rad_dex;
    }

    public int getRad_int() {
        return rad_int;
    }

    public void setRad_int(int rad_int) {
        this.rad_int = rad_int;
    }
}
