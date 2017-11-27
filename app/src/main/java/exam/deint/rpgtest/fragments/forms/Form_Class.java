package exam.deint.rpgtest.fragments.forms;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;

import exam.deint.rpgtest.R;
import exam.deint.rpgtest.activities.Activity_Home;
import exam.deint.rpgtest.pojos.Pojo_Class;

public class Form_Class extends Fragment {
    private FormClassInterface formClassInterface;

    private EditText edtName;
    private EditText edtWeapon;
    private RadioGroup rdgRole;
    private FloatingActionButton btnSave;

    private boolean update;
    private Pojo_Class pojoUpdate;

    public interface FormClassInterface {
        void fromFormClassToList(Pojo_Class pojoClass, boolean update);
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
        View view = inflater.inflate(R.layout.fragment_form_class, container, false);

        edtName = view.findViewById(R.id.formClassEdtName);
        edtWeapon = view.findViewById(R.id.formClassEdtWeapon);
        rdgRole = view.findViewById(R.id.formClassRdg);
        btnSave = view.findViewById(R.id.formClassBtnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String weapon = edtWeapon.getText().toString();
                String role = Pojo_Class.CLASS_ROLE_DPS;

                switch (rdgRole.getCheckedRadioButtonId()) {
                    case R.id.rdBtnDPS:
                        role = Pojo_Class.CLASS_ROLE_DPS;
                        break;
                    case R.id.rdBtnTANK:
                        role = Pojo_Class.CLASS_ROLE_TANK;
                        break;
                    case R.id.rdBtnHEALER:
                        role = Pojo_Class.CLASS_ROLE_HEALER;
                        break;
                }

                if (name.length() == 0 || weapon.length() == 0 || role.length() == 0) {
                    Activity_Home.showSnackbar("Campos vacíos");
                } else {
                    if (update) {
                        pojoUpdate.setCl_name(name);
                        pojoUpdate.setCl_weapon(weapon);
                        pojoUpdate.setCl_role(role);
                        formClassInterface.fromFormClassToList(pojoUpdate, update);
                    } else {
                        Pojo_Class tmp = new Pojo_Class(name, weapon, role);
                        formClassInterface.fromFormClassToList(tmp, update);
                    }
                }
            }
        });

        Pojo_Class test = getArguments().getParcelable("pojoClass");
        if (test != null) {
            pojoUpdate = test;
            update = true;
            edtName.setText(test.getCl_name());
            edtWeapon.setText(test.getCl_weapon());
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
