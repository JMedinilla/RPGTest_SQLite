package exam.deint.rpgtest.fragments.lists;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import exam.deint.rpgtest.R;
import exam.deint.rpgtest.activities.Activity_Home;
import exam.deint.rpgtest.adapters.Adapter_Adventurer;
import exam.deint.rpgtest.interfaces.PresenterAdventurer;
import exam.deint.rpgtest.pojos.AdapterPojo_Adventurer;
import exam.deint.rpgtest.pojos.DialogAdventurer;
import exam.deint.rpgtest.pojos.Pojo_Adventurer;
import exam.deint.rpgtest.presenters.PresenterAdventurerImpl;

public class List_Adventurer extends Fragment implements PresenterAdventurer.View {
    private ListAdventurerInterface listAdventureInterface;
    private PresenterAdventurerImpl presenterAdventurer;
    private Adapter_Adventurer adapterAdventurer;

    private ListView listView;
    private FloatingActionButton btnForm;

    boolean calledFromUpdate;

    @Override
    public void viewMessage(String message) {
        Activity_Home.showSnackbar(message);
    }

    @Override
    public void viewSelectAllResponse(List<AdapterPojo_Adventurer> list) {
        adapterAdventurer.updateList(list);
    }

    @Override
    public void viewSelectResponse(Pojo_Adventurer pojoAdventurer) {
        if (calledFromUpdate) {
            listAdventureInterface.fromListAdventurerToForm(pojoAdventurer);
            calledFromUpdate = false;
        } else {
            presenterAdventurer.implSelectAdventurerClass(pojoAdventurer.getAd_id());
        }
    }

    @Override
    public void viewInsertAdventurerResponse(long result) {
        if (result == -1) {
            viewMessage(getString(R.string.errorInsert));
        } else {
            viewMessage(getString(R.string.successInsert));
            getActivity().onBackPressed();
        }
    }

    @Override
    public void viewUpdateAdventurerResponse(int result) {
        if (result == 0) {
            viewMessage(getString(R.string.errorUpdate));
        } else {
            viewMessage(getString(R.string.successUpdate));
            getActivity().onBackPressed();
        }
    }

    @Override
    public void viewDeleteAdventurerResponse(int result) {
        if (result == 0) {
            viewMessage(getString(R.string.errorDelete));
        } else {
            viewMessage(getString(R.string.successDelete));
        }
    }

    @Override
    public void viewSelectAdventurerClassResponse(DialogAdventurer dialogAdventurer) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_adventurer, null);
        builder.setView(view);

        TextView txtName = view.findViewById(R.id.dialog_name);
        txtName.setText(dialogAdventurer.getAd_name());
        TextView txtRace = view.findViewById(R.id.dialog_race);
        txtRace.setText(dialogAdventurer.getAd_race());
        TextView txtAlignment = view.findViewById(R.id.dialog_alignment);
        txtAlignment.setText(dialogAdventurer.getAd_alignment());
        TextView txtClass = view.findViewById(R.id.dialog_class);
        txtClass.setText(dialogAdventurer.getAd_class());
        TextView txtWeapon = view.findViewById(R.id.dialog_weapon);
        txtWeapon.setText(dialogAdventurer.getAd_weapon());
        TextView txtRole = view.findViewById(R.id.dialog_role);
        txtRole.setText(dialogAdventurer.getAd_role());
        TextView txtSTR = view.findViewById(R.id.dialog_str);
        txtSTR.setText(String.valueOf(dialogAdventurer.getAd_str()));
        TextView txtDEX = view.findViewById(R.id.dialog_dex);
        txtDEX.setText(String.valueOf(dialogAdventurer.getAd_dex()));
        TextView txtINT = view.findViewById(R.id.dialog_int);
        txtINT.setText(String.valueOf(dialogAdventurer.getAd_int()));

        builder.setPositiveButton(R.string.dialogPositiveButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void getAdventurerFromHome(Pojo_Adventurer pojoAdventurer, boolean update) {
        if (update) {
            presenterAdventurer.implUpdateAdventurer(pojoAdventurer);
        } else {
            presenterAdventurer.implInsertAdventurer(pojoAdventurer);
        }
    }

    public interface ListAdventurerInterface {
        void fromListAdventurerToForm(Pojo_Adventurer pojoAdventurer);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        calledFromUpdate = false;
        presenterAdventurer = new PresenterAdventurerImpl(this);
        adapterAdventurer = new Adapter_Adventurer(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_adventurer, container, false);
        listView = view.findViewById(R.id.listAdventurer);
        btnForm = view.findViewById(R.id.listAdventurerButton);
        btnForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listAdventureInterface.fromListAdventurerToForm(null);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView.setDivider(null);
        listView.setAdapter(adapterAdventurer);
        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AdapterPojo_Adventurer adventurer = adapterAdventurer.getItem(i);
                if (adventurer != null) {
                    presenterAdventurer.implSelectAdventurer(adventurer.getRad_id());
                }
            }
        });

        presenterAdventurer.implSelectAdventurers();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listAdventureInterface = (ListAdventurerInterface) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listAdventureInterface = null;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        AdapterPojo_Adventurer adapterPojoAdventurer = adapterAdventurer.getItem(info.position);
        switch (item.getItemId()) {
            case R.id.context_update:
                calledFromUpdate = true;
                if (adapterPojoAdventurer != null) {
                    presenterAdventurer.implSelectAdventurer(adapterPojoAdventurer.getRad_id());
                }
                break;
            case R.id.context_delete:
                if (adapterPojoAdventurer != null) {
                    presenterAdventurer.implDeleteAdventurer(adapterPojoAdventurer.getRad_id());
                    presenterAdventurer.implSelectAdventurers();
                }
                break;
        }
        return super.onContextItemSelected(item);
    }
}
