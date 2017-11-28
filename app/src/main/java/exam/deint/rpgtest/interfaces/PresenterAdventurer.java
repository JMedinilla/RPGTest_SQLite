package exam.deint.rpgtest.interfaces;

import java.util.List;

import exam.deint.rpgtest.pojos.Adventurer;
import exam.deint.rpgtest.pojos.AdventurerForList;
import exam.deint.rpgtest.pojos.AdventurerWithClass;

public interface PresenterAdventurer {
    void implSelectAdventurers();

    void implSelectAdventurer(int id);

    void implInsertAdventurer(Adventurer adventurer);

    void implUpdateAdventurer(Adventurer adventurer);

    void implDeleteAdventurer(int id);

    void implSelectAdventurerClass(int id);

    void implSelectAdventurersResponse(List<AdventurerForList> list);

    void implSelectAdventurerResponse(Adventurer pojoAdventurer);

    void implInsertAdventurerResponse(Long aLong);

    void implUpdateAdventurerResponse(Integer integer);

    void implDeleteAdventurerResponse(Integer integer);

    void implSelectAdventurerClassResponse(AdventurerWithClass adventurerWithClass);

    interface View {
        void viewMessage(String message);

        void viewSelectAllResponse(List<AdventurerForList> list);

        void viewSelectResponse(Adventurer pojoAdventurer);

        void viewInsertAdventurerResponse(long result);

        void viewUpdateAdventurerResponse(int result);

        void viewDeleteAdventurerResponse(int result);

        void viewSelectAdventurerClassResponse(AdventurerWithClass adventurerWithClass);
    }
}
