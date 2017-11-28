package exam.deint.rpgtest.fragments.lists;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import exam.deint.rpgtest.R;
import exam.deint.rpgtest.activities.Activity_Home;
import exam.deint.rpgtest.adapters.Adapter_Adventurer;
import exam.deint.rpgtest.interfaces.PresenterAdventurer;
import exam.deint.rpgtest.pojos.Adventurer;
import exam.deint.rpgtest.pojos.AdventurerForList;
import exam.deint.rpgtest.pojos.AdventurerWithClass;
import exam.deint.rpgtest.presenters.PresenterAdventurerImpl;

public class List_Adventurer extends Fragment implements PresenterAdventurer.View {

    @BindView(R.id.listAdventurer)
    ListView listAdventurer;

    private Unbinder unbinder;
    private ListAdventurerInterface listAdventureInterface;
    private PresenterAdventurerImpl presenterAdventurer;
    private Adapter_Adventurer adapterAdventurer;
    private FragmentActivity activity;

    private boolean calledFromUpdate;

    @Override
    public void viewMessage(String message) {
        Activity_Home.showMessage(message);
    }

    @Override
    public void viewSelectAllResponse(List<AdventurerForList> list) {
        adapterAdventurer.updateList(list);
    }

    @Override
    public void viewSelectResponse(Adventurer pojoAdventurer) {
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
            activity.onBackPressed();
        }
    }

    @Override
    public void viewUpdateAdventurerResponse(int result) {
        if (result == 0) {
            viewMessage(getString(R.string.errorUpdate));
        } else {
            viewMessage(getString(R.string.successUpdate));
            activity.onBackPressed();
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
    public void viewSelectAdventurerClassResponse(AdventurerWithClass adventurerWithClass) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_adventurer, null);
        builder.setView(view);

        TextView txtName = view.findViewById(R.id.dialog_name);
        TextView txtRace = view.findViewById(R.id.dialog_race);
        TextView txtAlignment = view.findViewById(R.id.dialog_alignment);
        TextView txtClass = view.findViewById(R.id.dialog_class);
        TextView txtWeapon = view.findViewById(R.id.dialog_weapon);
        TextView txtRole = view.findViewById(R.id.dialog_role);
        TextView txtSTR = view.findViewById(R.id.dialog_str);
        TextView txtDEX = view.findViewById(R.id.dialog_dex);
        TextView txtINT = view.findViewById(R.id.dialog_int);

        txtName.setText(adventurerWithClass.getAd_name());
        txtRace.setText(adventurerWithClass.getAd_race());
        txtAlignment.setText(adventurerWithClass.getAd_alignment());
        txtClass.setText(adventurerWithClass.getAd_class());
        txtWeapon.setText(adventurerWithClass.getAd_weapon());
        txtRole.setText(adventurerWithClass.getAd_role());
        txtSTR.setText(String.valueOf(adventurerWithClass.getAd_str()));
        txtDEX.setText(String.valueOf(adventurerWithClass.getAd_dex()));
        txtINT.setText(String.valueOf(adventurerWithClass.getAd_int()));

        builder.setPositiveButton(R.string.dialogPositiveButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void getAdventurerFromHome(Adventurer pojoAdventurer, boolean update) {
        if (update) {
            presenterAdventurer.implUpdateAdventurer(pojoAdventurer);
        } else {
            presenterAdventurer.implInsertAdventurer(pojoAdventurer);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.listAdventurerButton)
    public void onViewClicked() {
        listAdventureInterface.fromListAdventurerToForm(null);
    }

    public interface ListAdventurerInterface {
        void fromListAdventurerToForm(Adventurer pojoAdventurer);
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_adventurer, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listAdventurer.setDivider(null);
        listAdventurer.setAdapter(adapterAdventurer);
        registerForContextMenu(listAdventurer);

        listAdventurer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AdventurerForList adventurer = adapterAdventurer.getItem(i);
                if (adventurer != null) {
                    presenterAdventurer.implSelectAdventurer(adventurer.getRad_id());
                }
            }
        });

        presenterAdventurer.implSelectAdventurers();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        activity.getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        AdventurerForList adapterPojoAdventurer = adapterAdventurer.getItem(info.position);
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listAdventureInterface = (ListAdventurerInterface) context;
        if (getActivity() != null) {
            activity = getActivity();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listAdventureInterface = null;
    }
}
