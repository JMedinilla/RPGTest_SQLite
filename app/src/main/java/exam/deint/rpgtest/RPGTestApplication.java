package exam.deint.rpgtest;

import android.app.Application;
import android.content.Context;

import exam.deint.rpgtest.database.DatabaseHelper;

public class RPGTestApplication extends Application {

    private static RPGTestApplication instance;

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
        DatabaseHelper.getInstance().open();
    }

    public static RPGTestApplication getInstance() {
        return instance;
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }
}
