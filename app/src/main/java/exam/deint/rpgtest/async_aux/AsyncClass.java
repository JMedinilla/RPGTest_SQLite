package exam.deint.rpgtest.async_aux;

import android.os.AsyncTask;

import java.util.List;

import exam.deint.rpgtest.database.ManageClass;
import exam.deint.rpgtest.pojos.Class;
import exam.deint.rpgtest.presenters.PresenterClassImpl;

public class AsyncClass {

    public static void selectClasses(final PresenterClassImpl presenterClass) {
        new AsyncTask<Void, Void, List<Class>>() {
            @Override
            protected List<Class> doInBackground(Void... voids) {
                return ManageClass.getInstance().selectAllClass();
            }

            @Override
            protected void onPostExecute(List<Class> list) {
                super.onPostExecute(list);
                presenterClass.implSelectClassesResponse(list);
            }
        }.execute();
    }

    public static void insertClass(final PresenterClassImpl presenterClass, final Class pojoClass) {
        new AsyncTask<Void, Void, Long>() {

            @Override
            protected Long doInBackground(Void... voids) {
                return ManageClass.getInstance().insertClass(pojoClass);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                presenterClass.implInsertClassResponse(aLong);
            }
        }.execute();
    }

    public static void updateClass(final PresenterClassImpl presenterClass, final Class pojoClass) {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                return ManageClass.getInstance().updateClass(pojoClass);
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                presenterClass.implUpdateClassResponse(integer);
            }
        }.execute();
    }

    public static void deleteClass(final PresenterClassImpl presenterClass, final Class pojoClass) {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                return ManageClass.getInstance().deleteClass(pojoClass);
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                presenterClass.implDeleteClassResponse(integer);
            }
        }.execute();
    }
}
