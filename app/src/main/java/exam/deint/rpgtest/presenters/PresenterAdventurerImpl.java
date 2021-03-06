package exam.deint.rpgtest.presenters;

import java.util.List;

import exam.deint.rpgtest.async_aux.AsyncAdventurer;
import exam.deint.rpgtest.interfaces.PresenterAdventurer;
import exam.deint.rpgtest.pojos.Adventurer;
import exam.deint.rpgtest.pojos.AdventurerForList;
import exam.deint.rpgtest.pojos.AdventurerWithClass;

public class PresenterAdventurerImpl implements PresenterAdventurer {
    private PresenterAdventurer.View view;

    public PresenterAdventurerImpl(PresenterAdventurer.View view) {
        this.view = view;
    }

    @Override
    public void implSelectAdventurers() {
        AsyncAdventurer.selectAdventurers(this);
    }

    @Override
    public void implSelectAdventurersResponse(List<AdventurerForList> list) {
        view.viewSelectAllResponse(list);
    }

    @Override
    public void implSelectAdventurer(int id) {
        AsyncAdventurer.selectAventurer(this, id);
    }

    @Override
    public void implSelectAdventurerResponse(Adventurer pojoAdventurer) {
        view.viewSelectResponse(pojoAdventurer);
    }

    @Override
    public void implInsertAdventurer(Adventurer pojoAdventurer) {
        AsyncAdventurer.insertAdventurer(this, pojoAdventurer);
    }

    @Override
    public void implInsertAdventurerResponse(Long aLong) {
        view.viewInsertAdventurerResponse(aLong);
    }

    @Override
    public void implUpdateAdventurer(Adventurer pojoAdventurer) {
        AsyncAdventurer.updateAdventurer(this, pojoAdventurer);
    }

    @Override
    public void implUpdateAdventurerResponse(Integer integer) {
        view.viewUpdateAdventurerResponse(integer);
    }

    @Override
    public void implDeleteAdventurer(int id) {
        AsyncAdventurer.deleteAdventurer(this, id);
    }

    @Override
    public void implDeleteAdventurerResponse(Integer integer) {
        view.viewDeleteAdventurerResponse(integer);
    }

    @Override
    public void implSelectAdventurerClass(int id) {
        AsyncAdventurer.selectAdventurerClass(this, id);
    }

    @Override
    public void implSelectAdventurerClassResponse(AdventurerWithClass adventurerWithClass) {
        view.viewSelectAdventurerClassResponse(adventurerWithClass);
    }
}
