package exam.deint.rpgtest.fragments.lists;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import exam.deint.rpgtest.R;
import exam.deint.rpgtest.activities.Activity_Home;
import exam.deint.rpgtest.adapters.Adapter_Class;
import exam.deint.rpgtest.interfaces.PresenterClass;
import exam.deint.rpgtest.pojos.Pojo_Class;
import exam.deint.rpgtest.presenters.PresenterClassImpl;

public class List_Class extends Fragment implements PresenterClass.View {
    private ListClassInterface listClassInterface;
    private PresenterClassImpl presenterClass;
    private Adapter_Class adapterClass;

    private ListView listView;
    private FloatingActionButton btnForm;

    @Override
    public void viewMessage(String message) {
        Activity_Home.showSnackbar(message);
    }

    @Override
    public void viewSelectAllResponse(List<Pojo_Class> list) {
        adapterClass.updateList(list);
    }

    @Override
    public void viewInsertClassResponse(long result) {
        if (result == -1) {
            viewMessage(getContext().getString(R.string.errorInsert));
        } else {
            viewMessage(getContext().getString(R.string.successInsert));
            getActivity().onBackPressed();
        }
    }

    @Override
    public void viewUpdateClassResponse(int result) {
        if (result == 0) {
            viewMessage(getContext().getString(R.string.errorUpdate));
        } else {
            viewMessage(getContext().getString(R.string.successUpdate));
            getActivity().onBackPressed();
        }
    }

    @Override
    public void viewDeleteClassResponse(int result) {
        if (result == 0) {
            viewMessage(getContext().getString(R.string.errorDelete));
        } else {
            viewMessage(getContext().getString(R.string.successDelete));
        }
    }

    public void getClassFromHome(Pojo_Class pojoClass, boolean update) {
        if (update) {
            presenterClass.implUpdateClass(pojoClass);
        } else {
            presenterClass.implInsertClass(pojoClass);
        }
    }

    public interface ListClassInterface {
        void fromListClassToForm(Pojo_Class pojoClass);
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_class, container, false);
        listView = view.findViewById(R.id.listClass);
        btnForm = view.findViewById(R.id.listClassButton);
        btnForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listClassInterface.fromListClassToForm(null);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView.setAdapter(adapterClass);
        registerForContextMenu(listView);

        presenterClass.implSelectClasses();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listClassInterface = (ListClassInterface) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listClassInterface = null;
        presenterClass = null;
        adapterClass = null;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Pojo_Class pojo_class = adapterClass.getItem(info.position);
        switch (item.getItemId()) {
            case R.id.context_update:
                listClassInterface.fromListClassToForm(pojo_class);
                break;
            case R.id.context_delete:
                presenterClass.implDeleteClass(pojo_class);
                presenterClass.implSelectClasses();
                break;
        }
        return super.onContextItemSelected(item);
    }
}
