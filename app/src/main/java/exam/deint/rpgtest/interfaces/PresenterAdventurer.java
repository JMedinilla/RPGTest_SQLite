package exam.deint.rpgtest.interfaces;

import java.util.List;

import exam.deint.rpgtest.pojos.AdapterPojo_Adventurer;
import exam.deint.rpgtest.pojos.DialogAdventurer;
import exam.deint.rpgtest.pojos.Pojo_Adventurer;

public interface PresenterAdventurer {
    void implSelectAdventurers();

    void implSelectAdventurer(int id);

    void implInsertAdventurer(Pojo_Adventurer adventurer);

    void implUpdateAdventurer(Pojo_Adventurer adventurer);

    void implDeleteAdventurer(int id);

    void implSelectAdventurerClass(int id);

    void implSelectAdventurersResponse(List<AdapterPojo_Adventurer> list);

    void implSelectAdventurerResponse(Pojo_Adventurer pojoAdventurer);

    void implInsertAdventurerResponse(Long aLong);

    void implUpdateAdventurerResponse(Integer integer);

    void implDeleteAdventurerResponse(Integer integer);

    void implSelectAdventurerClassResponse(DialogAdventurer dialogAdventurer);

    interface View {
        void viewMessage(String message);

        void viewSelectAllResponse(List<AdapterPojo_Adventurer> list);

        void viewSelectResponse(Pojo_Adventurer pojoAdventurer);

        void viewInsertAdventurerResponse(long result);

        void viewUpdateAdventurerResponse(int result);

        void viewDeleteAdventurerResponse(int result);

        void viewSelectAdventurerClassResponse(DialogAdventurer dialogAdventurer);
    }
}
