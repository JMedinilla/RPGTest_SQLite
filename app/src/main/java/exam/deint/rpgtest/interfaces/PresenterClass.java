package exam.deint.rpgtest.interfaces;

import java.util.List;

import exam.deint.rpgtest.pojos.Pojo_Class;

public interface PresenterClass {
    void implSelectClasses();

    void implInsertClass(Pojo_Class quest);

    void implUpdateClass(Pojo_Class quest);

    void implDeleteClass(Pojo_Class quest);

    interface View {
        void viewMessage(String message);

        void viewSelectAllResponse(List<Pojo_Class> list);

        void viewInsertClassResponse(long result);

        void viewUpdateClassResponse(int result);

        void viewDeleteClassResponse(int result);
    }
}
