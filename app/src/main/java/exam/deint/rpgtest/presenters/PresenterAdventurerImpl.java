package exam.deint.rpgtest.presenters;

import android.os.AsyncTask;

import java.util.List;

import exam.deint.rpgtest.database.ManageAdventurer;
import exam.deint.rpgtest.interfaces.PresenterAdventurer;
import exam.deint.rpgtest.pojos.AdapterPojo_Adventurer;
import exam.deint.rpgtest.pojos.DialogAdventurer;
import exam.deint.rpgtest.pojos.Pojo_Adventurer;

public class PresenterAdventurerImpl implements PresenterAdventurer {
    private PresenterAdventurer.View view;

    public PresenterAdventurerImpl(PresenterAdventurer.View view) {
        this.view = view;
    }

    @Override
    public void implSelectAdventurers() {
        new AsyncTask<Void, Void, List<AdapterPojo_Adventurer>>() {
            @Override
            protected List<AdapterPojo_Adventurer> doInBackground(Void... voids) {
                return ManageAdventurer.getInstance().selectAllAdventurer();
            }

            @Override
            protected void onPostExecute(List<AdapterPojo_Adventurer> list) {
                super.onPostExecute(list);
                view.viewSelectAllResponse(list);
            }
        }.execute();
    }

    @Override
    public void implSelectAdventurer(final int id) {
        new AsyncTask<Void, Void, Pojo_Adventurer>() {
            @Override
            protected Pojo_Adventurer doInBackground(Void... voids) {
                return ManageAdventurer.getInstance().selectAdventurer(id);
            }

            @Override
            protected void onPostExecute(Pojo_Adventurer pojoAdventurer) {
                super.onPostExecute(pojoAdventurer);
                view.viewSelectResponse(pojoAdventurer);
            }
        }.execute();
    }

    @Override
    public void implInsertAdventurer(final Pojo_Adventurer pojoAdventurer) {
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                return ManageAdventurer.getInstance().insertAdventurer(pojoAdventurer);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                view.viewInsertAdventurerResponse(aLong);
            }
        }.execute();
    }

    @Override
    public void implUpdateAdventurer(final Pojo_Adventurer pojoAdventurer) {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                return ManageAdventurer.getInstance().updateAdventurer(pojoAdventurer);
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                view.viewUpdateAdventurerResponse(integer);
            }
        }.execute();
    }

    @Override
    public void implDeleteAdventurer(final int id) {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                return ManageAdventurer.getInstance().deleteAdventurer(id);
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                view.viewDeleteAdventurerResponse(integer);
            }
        }.execute();
    }

    @Override
    public void implSelectAdventurerClass(final int id) {
        new AsyncTask<Void, Void, DialogAdventurer>() {
            @Override
            protected DialogAdventurer doInBackground(Void... voids) {
                return ManageAdventurer.getInstance().selectAdventurerClass(id);
            }

            @Override
            protected void onPostExecute(DialogAdventurer dialogAdventurer) {
                super.onPostExecute(dialogAdventurer);
                view.viewSelectAdventurerClassResponse(dialogAdventurer);
            }
        }.execute();
    }
}
