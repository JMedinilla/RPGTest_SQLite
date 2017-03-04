package exam.deint.rpgtest.fragments.forms;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import exam.deint.rpgtest.R;
import exam.deint.rpgtest.activities.Activity_Home;
import exam.deint.rpgtest.database.ManageClass;
import exam.deint.rpgtest.pojos.Pojo_Adventurer;
import exam.deint.rpgtest.pojos.Spinner_Class;

public class Form_Adventurer extends Fragment {
    FormAdventurerInterface formAdventureInterface;

    EditText edtName;
    EditText edtStr;
    EditText edtDex;
    EditText edtInt;
    Spinner spinRace;
    Spinner spinAlignment;
    Spinner spinClass;
    FloatingActionButton btnSave;

    boolean update;
    Pojo_Adventurer pojoUpdate;

    public interface FormAdventurerInterface {
        void fromFormAdventurerToList(Pojo_Adventurer pojoAdventurer, boolean update);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        update = false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form_adventurer, container, false);

        edtName = (EditText) view.findViewById(R.id.formAdventurer_edtName);
        edtStr = (EditText) view.findViewById(R.id.formAdventurer_edtStr);
        edtDex = (EditText) view.findViewById(R.id.formAdventurer_edtDex);
        edtInt = (EditText) view.findViewById(R.id.formAdventurer_edtInt);
        spinRace = (Spinner) view.findViewById(R.id.formAdventurer_spinRace);
        spinAlignment = (Spinner) view.findViewById(R.id.formAdventurer_spinAlignment);
        spinClass = (Spinner) view.findViewById(R.id.formAdventurer_spinClass);
        btnSave = (FloatingActionButton) view.findViewById(R.id.formAdventurer_btnSave);

        String[] races = new String[]{
                Pojo_Adventurer.ADVENTURER_RACE_HUMAN, Pojo_Adventurer.ADVENTURER_RACE_ORC,
                Pojo_Adventurer.ADVENTURER_RACE_ELF, Pojo_Adventurer.ADVENTURER_RACE_DWARF,
                Pojo_Adventurer.ADVENTURER_RACE_DEMON
        };
        String[] alignments = new String[]{
                Pojo_Adventurer.ADVENTURER_ALIGNMENT_GOOD_LAWFUL, Pojo_Adventurer.ADVENTURER_ALIGNMENT_GOOD_NEUTRAL,
                Pojo_Adventurer.ADVENTURER_ALIGNMENT_GOOD_CHAOTIC, Pojo_Adventurer.ADVENTURER_ALIGNMENT_NEUTRAL_LAWFUL,
                Pojo_Adventurer.ADVENTURER_ALIGNMENT_NEUTRAL_NEUTRAL, Pojo_Adventurer.ADVENTURER_ALIGNMENT_NEUTRAL_CHAOTIC,
                Pojo_Adventurer.ADVENTURER_ALIGNMENT_EVIL_LAWFUL, Pojo_Adventurer.ADVENTURER_ALIGNMENT_EVIL_NEUTRAL,
                Pojo_Adventurer.ADVENTURER_ALIGNMENT_EVIL_CHAOTIC
        };

        ArrayAdapter<String> racesSpinner = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, races);
        racesSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinRace.setAdapter(racesSpinner);

        ArrayAdapter<String> alignmentsSpinner = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, alignments);
        alignmentsSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinAlignment.setAdapter(alignmentsSpinner);

        final List<Spinner_Class> list = ManageClass.getInstance().selectSpinnerClasses();
        if (list != null) {
            if (list.size() != 0) {
                String[] classes = new String[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    classes[i] = list.get(i).getName();
                }
                ArrayAdapter<String> classesSpinner = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, classes);
                classesSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinClass.setAdapter(classesSpinner);
            }
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vName = edtName.getText().toString();
                String vRace = spinRace.getSelectedItem().toString();
                String vAlignment = spinAlignment.getSelectedItem().toString();
                int vClass = 0;
                int vStr = Integer.parseInt(edtStr.getText().toString());
                int vDex = Integer.parseInt(edtDex.getText().toString());
                int vInt = Integer.parseInt(edtInt.getText().toString());

                if (list != null) {
                    if (spinClass != null) {
                        if (spinClass.getSelectedItem() != null) {
                            String sClass = spinClass.getSelectedItem().toString();
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).getName().equals(sClass)) {
                                    vClass = list.get(i).getId();
                                }
                            }

                            if (update) {
                                pojoUpdate.setAd_name(vName);
                                pojoUpdate.setAd_race(vRace);
                                pojoUpdate.setAd_alignment(vAlignment);
                                pojoUpdate.setAd_class(vClass);
                                pojoUpdate.setAd_str(vStr);
                                pojoUpdate.setAd_dex(vDex);
                                pojoUpdate.setAd_int(vInt);
                                formAdventureInterface.fromFormAdventurerToList(pojoUpdate, update);
                            } else {
                                Pojo_Adventurer tmp = new Pojo_Adventurer(
                                        vName, vRace, vAlignment, vClass, vStr, vDex, vInt
                                );
                                formAdventureInterface.fromFormAdventurerToList(tmp, update);
                            }
                        } else {
                            Activity_Home.showSnackbar(getString(R.string.noClasses));
                        }
                    }
                }
            }
        });

        Pojo_Adventurer test = getArguments().getParcelable("pojoAdventurer");
        if (test != null) {
            pojoUpdate = test;
            update = true;
            edtName.setText(test.getAd_name());
            edtStr.setText(String.valueOf(test.getAd_str()));
            edtDex.setText(String.valueOf(test.getAd_dex()));
            edtInt.setText(String.valueOf(test.getAd_int()));
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        formAdventureInterface = (FormAdventurerInterface) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        formAdventureInterface = null;
    }
}
