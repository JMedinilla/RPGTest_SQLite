package exam.deint.rpgtest.interfaces;

import java.util.List;

import exam.deint.rpgtest.pojos.Class;

public interface PresenterClass {
    void implSelectClasses();

    void implInsertClass(Class quest);

    void implUpdateClass(Class quest);

    void implDeleteClass(Class quest);

    void implSelectClassesResponse(List<Class> list);

    void implInsertClassResponse(Long aLong);

    void implUpdateClassResponse(Integer integer);

    void implDeleteClassResponse(Integer integer);

    interface View {
        void viewMessage(String message);

        void viewSelectAllResponse(List<Class> list);

        void viewInsertClassResponse(long result);

        void viewUpdateClassResponse(int result);

        void viewDeleteClassResponse(int result);
    }
}
