package exam.deint.rpgtest.fragments.forms;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import exam.deint.rpgtest.Constants;
import exam.deint.rpgtest.R;
import exam.deint.rpgtest.activities.Activity_Home;
import exam.deint.rpgtest.database.ManageClass;
import exam.deint.rpgtest.pojos.Adventurer;
import exam.deint.rpgtest.pojos.ClassForSpinner;

public class Form_Adventurer extends Fragment {

    @BindView(R.id.formAdventurer_edtName)
    EditText formAdventurerEdtName;
    @BindView(R.id.formAdventurer_spinRace)
    Spinner formAdventurerSpinRace;
    @BindView(R.id.formAdventurer_spinAlignment)
    Spinner formAdventurerSpinAlignment;
    @BindView(R.id.formAdventurer_spinClass)
    Spinner formAdventurerSpinClass;
    @BindView(R.id.formAdventurer_edtStr)
    EditText formAdventurerEdtStr;
    @BindView(R.id.formAdventurer_edtDex)
    EditText formAdventurerEdtDex;
    @BindView(R.id.formAdventurer_edtInt)
    EditText formAdventurerEdtInt;

    private Unbinder unbinder;
    private FormAdventurerInterface formAdventureInterface;
    private List<ClassForSpinner> list;
    private FragmentActivity activity;

    private boolean update;
    private Adventurer pojoUpdate;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.formAdventurer_btnSave)
    public void onViewClicked() {
        String vName = formAdventurerEdtName.getText().toString();
        String vRace = formAdventurerSpinRace.getSelectedItem().toString();
        String vAlignment = formAdventurerSpinAlignment.getSelectedItem().toString();
        int vClass = 0;
        int vStr = Integer.parseInt(formAdventurerEdtStr.getText().toString());
        int vDex = Integer.parseInt(formAdventurerEdtDex.getText().toString());
        int vInt = Integer.parseInt(formAdventurerEdtInt.getText().toString());

        if (list != null) {
            if (formAdventurerSpinClass.getSelectedItem() != null) {
                String sClass = formAdventurerSpinClass.getSelectedItem().toString();
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
                    Adventurer tmp = new Adventurer(
                            vName, vRace, vAlignment, vClass, vStr, vDex, vInt
                    );
                    formAdventureInterface.fromFormAdventurerToList(tmp, update);
                }
            } else {
                Activity_Home.showMessage(getString(R.string.noClasses));
            }
        }
    }

    public interface FormAdventurerInterface {
        void fromFormAdventurerToList(Adventurer pojoAdventurer, boolean update);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        update = false;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form_adventurer, container, false);
        unbinder = ButterKnife.bind(this, view);

        String[] races = new String[]{
                Constants.ADVENTURER_RACE_HUMAN, Constants.ADVENTURER_RACE_ORC,
                Constants.ADVENTURER_RACE_ELF, Constants.ADVENTURER_RACE_DWARF,
                Constants.ADVENTURER_RACE_DEMON
        };
        String[] alignments = new String[]{
                Constants.ADVENTURER_ALIGNMENT_GOOD_LAWFUL, Constants.ADVENTURER_ALIGNMENT_GOOD_NEUTRAL,
                Constants.ADVENTURER_ALIGNMENT_GOOD_CHAOTIC, Constants.ADVENTURER_ALIGNMENT_NEUTRAL_LAWFUL,
                Constants.ADVENTURER_ALIGNMENT_NEUTRAL_NEUTRAL, Constants.ADVENTURER_ALIGNMENT_NEUTRAL_CHAOTIC,
                Constants.ADVENTURER_ALIGNMENT_EVIL_LAWFUL, Constants.ADVENTURER_ALIGNMENT_EVIL_NEUTRAL,
                Constants.ADVENTURER_ALIGNMENT_EVIL_CHAOTIC
        };

        ArrayAdapter<String> racesSpinner = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_item, races);
        racesSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        formAdventurerSpinRace.setAdapter(racesSpinner);

        ArrayAdapter<String> alignmentsSpinner = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_item, alignments);
        alignmentsSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        formAdventurerSpinAlignment.setAdapter(alignmentsSpinner);

        list = ManageClass.getInstance().selectSpinnerClasses();
        if (list != null) {
            if (list.size() != 0) {
                String[] classes = new String[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    classes[i] = list.get(i).getName();
                }
                ArrayAdapter<String> classesSpinner = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_item, classes);
                classesSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                formAdventurerSpinClass.setAdapter(classesSpinner);
            }
        }

        if (getArguments() != null) {
            Adventurer test = getArguments().getParcelable("pojoAdventurer");
            if (test != null) {
                pojoUpdate = test;
                update = true;
                formAdventurerEdtName.setText(test.getAd_name());
                formAdventurerEdtStr.setText(String.valueOf(test.getAd_str()));
                formAdventurerEdtDex.setText(String.valueOf(test.getAd_dex()));
                formAdventurerEdtInt.setText(String.valueOf(test.getAd_int()));
            }
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        formAdventureInterface = (FormAdventurerInterface) context;
        if (getActivity() != null) {
            activity = getActivity();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        formAdventureInterface = null;
    }
}
