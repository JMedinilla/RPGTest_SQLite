package exam.deint.rpgtest.presenters;

import android.os.AsyncTask;

import java.util.List;

import exam.deint.rpgtest.database.ManageClass;
import exam.deint.rpgtest.interfaces.PresenterClass;
import exam.deint.rpgtest.pojos.Pojo_Class;

public class PresenterClassImpl implements PresenterClass {
    private PresenterClass.View view;

    public PresenterClassImpl(PresenterClass.View view) {
        this.view = view;
    }

    @Override
    public void implSelectClasses() {
        new AsyncTask<Void, Void, List<Pojo_Class>>() {
            @Override
            protected List<Pojo_Class> doInBackground(Void... voids) {
                return ManageClass.getInstance().selectAllClass();
            }

            @Override
            protected void onPostExecute(List<Pojo_Class> list) {
                super.onPostExecute(list);
                view.viewSelectAllResponse(list);
            }
        }.execute();
    }

    @Override
    public void implInsertClass(final Pojo_Class pojoClass) {
        new AsyncTask<Void, Void, Long>() {

            @Override
            protected Long doInBackground(Void... voids) {
                return ManageClass.getInstance().insertClass(pojoClass);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                view.viewInsertClassResponse(aLong);
            }
        }.execute();
    }

    @Override
    public void implUpdateClass(final Pojo_Class pojoClass) {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                return ManageClass.getInstance().updateClass(pojoClass);
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                view.viewUpdateClassResponse(integer);
            }
        }.execute();
    }

    @Override
    public void implDeleteClass(final Pojo_Class pojoClass) {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                return ManageClass.getInstance().deleteClass(pojoClass);
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                view.viewDeleteClassResponse(integer);
            }
        }.execute();
    }
}
