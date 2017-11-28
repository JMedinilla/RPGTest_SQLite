package exam.deint.rpgtest.fragments.forms;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import exam.deint.rpgtest.Constants;
import exam.deint.rpgtest.R;
import exam.deint.rpgtest.activities.Activity_Home;
import exam.deint.rpgtest.pojos.Class;

public class Form_Class extends Fragment {

    @BindView(R.id.formClassEdtName)
    EditText formClassEdtName;
    @BindView(R.id.formClassEdtWeapon)
    EditText formClassEdtWeapon;
    @BindView(R.id.formClassRdg)
    RadioGroup formClassRdg;
    @BindView(R.id.formClassBtnSave)
    FloatingActionButton formClassBtnSave;

    private Unbinder unbinder;
    private FormClassInterface formClassInterface;

    private boolean update;
    private Class pojoUpdate;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.formClassBtnSave)
    public void onViewClicked() {
        String name = formClassEdtName.getText().toString();
        String weapon = formClassEdtWeapon.getText().toString();
        String role = Constants.CLASS_ROLE_DPS;

        switch (formClassRdg.getCheckedRadioButtonId()) {
            case R.id.rdBtnDPS:
                role = Constants.CLASS_ROLE_DPS;
                break;
            case R.id.rdBtnTANK:
                role = Constants.CLASS_ROLE_TANK;
                break;
            case R.id.rdBtnHEALER:
                role = Constants.CLASS_ROLE_HEALER;
                break;
        }

        if (name.length() == 0 || weapon.length() == 0 || role.length() == 0) {
            Activity_Home.showMessage("Campos vacíos");
        } else {
            if (update) {
                pojoUpdate.setCl_name(name);
                pojoUpdate.setCl_weapon(weapon);
                pojoUpdate.setCl_role(role);
                formClassInterface.fromFormClassToList(pojoUpdate, update);
            } else {
                Class tmp = new Class(name, weapon, role);
                formClassInterface.fromFormClassToList(tmp, update);
            }
        }
    }

    public interface FormClassInterface {
        void fromFormClassToList(Class pojoClass, boolean update);
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
        View view = inflater.inflate(R.layout.fragment_form_class, container, false);
        unbinder = ButterKnife.bind(this, view);

        if (getArguments() != null) {
            Class test = getArguments().getParcelable("pojoClass");
            if (test != null) {
                pojoUpdate = test;
                update = true;
                formClassEdtName.setText(test.getCl_name());
                formClassEdtWeapon.setText(test.getCl_weapon());
            }
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        formClassInterface = (FormClassInterface) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        formClassInterface = null;
    }
}
