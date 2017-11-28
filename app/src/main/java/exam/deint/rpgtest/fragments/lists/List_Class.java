package exam.deint.rpgtest.fragments.lists;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import exam.deint.rpgtest.R;
import exam.deint.rpgtest.activities.Activity_Home;
import exam.deint.rpgtest.adapters.Adapter_Class;
import exam.deint.rpgtest.interfaces.PresenterClass;
import exam.deint.rpgtest.pojos.Class;
import exam.deint.rpgtest.presenters.PresenterClassImpl;

public class List_Class extends Fragment implements PresenterClass.View {

    @BindView(R.id.listClass)
    ListView listClass;

    private Unbinder unbinder;
    private ListClassInterface listClassInterface;
    private PresenterClassImpl presenterClass;
    private Adapter_Class adapterClass;
    private FragmentActivity activity;

    @Override
    public void viewMessage(String message) {
        Activity_Home.showMessage(message);
    }

    @Override
    public void viewSelectAllResponse(List<Class> list) {
        adapterClass.updateList(list);
    }

    @Override
    public void viewInsertClassResponse(long result) {
        if (result == -1) {
            viewMessage(activity.getString(R.string.errorInsert));
        } else {
            viewMessage(activity.getString(R.string.successInsert));
            activity.onBackPressed();
        }
    }

    @Override
    public void viewUpdateClassResponse(int result) {
        if (result == 0) {
            viewMessage(activity.getString(R.string.errorUpdate));
        } else {
            viewMessage(activity.getString(R.string.successUpdate));
            activity.onBackPressed();
        }
    }

    @Override
    public void viewDeleteClassResponse(int result) {
        if (result == 0) {
            viewMessage(activity.getString(R.string.errorDelete));
        } else {
            viewMessage(activity.getString(R.string.successDelete));
        }
    }

    public void getClassFromHome(Class pojoClass, boolean update) {
        if (update) {
            presenterClass.implUpdateClass(pojoClass);
        } else {
            presenterClass.implInsertClass(pojoClass);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.listClassButton)
    public void onViewClicked() {
        listClassInterface.fromListClassToForm(null);
    }

    public interface ListClassInterface {
        void fromListClassToForm(Class pojoClass);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        presenterClass = new PresenterClassImpl(this);
        adapterClass = new Adapter_Class(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_class, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listClass.setAdapter(adapterClass);
        registerForContextMenu(listClass);

        presenterClass.implSelectClasses();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        activity.getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Class _class = adapterClass.getItem(info.position);
        switch (item.getItemId()) {
            case R.id.context_update:
                listClassInterface.fromListClassToForm(_class);
                break;
            case R.id.context_delete:
                presenterClass.implDeleteClass(_class);
                presenterClass.implSelectClasses();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listClassInterface = (ListClassInterface) context;
        if (getActivity() != null) {
            activity = getActivity();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listClassInterface = null;
        presenterClass = null;
        adapterClass = null;
    }
}
