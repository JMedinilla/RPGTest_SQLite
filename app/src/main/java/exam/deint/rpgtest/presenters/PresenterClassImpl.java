package exam.deint.rpgtest.presenters;

import java.util.List;

import exam.deint.rpgtest.async_aux.AsyncClass;
import exam.deint.rpgtest.interfaces.PresenterClass;
import exam.deint.rpgtest.pojos.Class;

public class PresenterClassImpl implements PresenterClass {
    private PresenterClass.View view;

    public PresenterClassImpl(PresenterClass.View view) {
        this.view = view;
    }

    @Override
    public void implSelectClasses() {
        AsyncClass.selectClasses(this);
    }

    @Override
    public void implSelectClassesResponse(List<Class> list) {
        view.viewSelectAllResponse(list);
    }

    @Override
    public void implInsertClass(Class pojoClass) {
        AsyncClass.insertClass(this, pojoClass);
    }

    @Override
    public void implInsertClassResponse(Long aLong) {
        view.viewInsertClassResponse(aLong);
    }

    @Override
    public void implUpdateClass(Class pojoClass) {
        AsyncClass.updateClass(this, pojoClass);
    }

    @Override
    public void implUpdateClassResponse(Integer integer) {
        view.viewUpdateClassResponse(integer);
    }

    @Override
    public void implDeleteClass(Class pojoClass) {
        AsyncClass.deleteClass(this, pojoClass);
    }

    @Override
    public void implDeleteClassResponse(Integer integer) {
        view.viewDeleteClassResponse(integer);
    }
}
