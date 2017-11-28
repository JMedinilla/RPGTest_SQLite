package exam.deint.rpgtest.async_aux;

import android.os.AsyncTask;

import java.util.List;

import exam.deint.rpgtest.database.ManageAdventurer;
import exam.deint.rpgtest.pojos.Adventurer;
import exam.deint.rpgtest.pojos.AdventurerForList;
import exam.deint.rpgtest.pojos.AdventurerWithClass;
import exam.deint.rpgtest.presenters.PresenterAdventurerImpl;

public class AsyncAdventurer {

    public static void selectAdventurers(final PresenterAdventurerImpl presenterAdventurer) {
        new AsyncTask<Void, Void, List<AdventurerForList>>() {
            @Override
            protected List<AdventurerForList> doInBackground(Void... voids) {
                return ManageAdventurer.getInstance().selectAllAdventurer();
            }

            @Override
            protected void onPostExecute(List<AdventurerForList> list) {
                super.onPostExecute(list);
                presenterAdventurer.implSelectAdventurersResponse(list);
            }
        }.execute();
    }

    public static void selectAventurer(final PresenterAdventurerImpl presenterAdventurer, final int id) {
        new AsyncTask<Void, Void, Adventurer>() {
            @Override
            protected Adventurer doInBackground(Void... voids) {
                return ManageAdventurer.getInstance().selectAdventurer(id);
            }

            @Override
            protected void onPostExecute(Adventurer pojoAdventurer) {
                super.onPostExecute(pojoAdventurer);
                presenterAdventurer.implSelectAdventurerResponse(pojoAdventurer);
            }
        }.execute();
    }

    public static void insertAdventurer(final PresenterAdventurerImpl presenterAdventurer, final Adventurer pojoAdventurer) {
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                return ManageAdventurer.getInstance().insertAdventurer(pojoAdventurer);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                presenterAdventurer.implInsertAdventurerResponse(aLong);
            }
        }.execute();
    }

    public static void updateAdventurer(final PresenterAdventurerImpl presenterAdventurer, final Adventurer pojoAdventurer) {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                return ManageAdventurer.getInstance().updateAdventurer(pojoAdventurer);
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                presenterAdventurer.implUpdateAdventurerResponse(integer);
            }
        }.execute();
    }

    public static void deleteAdventurer(final PresenterAdventurerImpl presenterAdventurer, final int id) {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                return ManageAdventurer.getInstance().deleteAdventurer(id);
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                presenterAdventurer.implDeleteAdventurerResponse(integer);
            }
        }.execute();
    }

    public static void selectAdventurerClass(final PresenterAdventurerImpl presenterAdventurer, final int id) {
        new AsyncTask<Void, Void, AdventurerWithClass>() {
            @Override
            protected AdventurerWithClass doInBackground(Void... voids) {
                return ManageAdventurer.getInstance().selectAdventurerClass(id);
            }

            @Override
            protected void onPostExecute(AdventurerWithClass adventurerWithClass) {
                super.onPostExecute(adventurerWithClass);
                presenterAdventurer.implSelectAdventurerClassResponse(adventurerWithClass);
            }
        }.execute();
    }
}
