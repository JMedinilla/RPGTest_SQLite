package exam.deint.rpgtest.activities;

import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import exam.deint.rpgtest.R;
import exam.deint.rpgtest.fragments.Home;
import exam.deint.rpgtest.fragments.forms.Form_Adventurer;
import exam.deint.rpgtest.fragments.forms.Form_Class;
import exam.deint.rpgtest.fragments.lists.List_Adventurer;
import exam.deint.rpgtest.fragments.lists.List_Class;
import exam.deint.rpgtest.pojos.Pojo_Adventurer;
import exam.deint.rpgtest.pojos.Pojo_Class;

public class Activity_Home extends AppCompatActivity implements
        List_Adventurer.ListAdventurerInterface, List_Class.ListClassInterface,
        Form_Adventurer.FormAdventurerInterface, Form_Class.FormClassInterface,
        Home.HomeInterface {

    static FrameLayout activity_home;

    Home home;
    List_Class list_class;
    List_Adventurer list_adventurer;
    Form_Class form_class;
    Form_Adventurer form_adventurer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        activity_home = (FrameLayout) findViewById(R.id.activity_home);

        home = new Home();
        list_class = new List_Class();
        list_adventurer = new List_Adventurer();

        showHome();
    }

    public static void showSnackbar(String message) {
        Snackbar.make(activity_home, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void fromFormAdventurerToList(Pojo_Adventurer pojoAdventurer, boolean update) {
        list_adventurer.getAdventurerFromHome(pojoAdventurer, update);
    }

    @Override
    public void fromFormClassToList(Pojo_Class pojoClass, boolean update) {
        list_class.getClassFromHome(pojoClass, update);
    }

    @Override
    public void fromListAdventurerToForm(Pojo_Adventurer pojoAdventurer) {
        form_adventurer = new Form_Adventurer();
        Bundle bundle = new Bundle();
        bundle.putParcelable("pojoAdventurer", pojoAdventurer);
        form_adventurer.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_home, form_adventurer, "formAdventurer");
        fragmentTransaction.addToBackStack("formAdventurer");
        fragmentTransaction.commit();
    }

    @Override
    public void fromListClassToForm(Pojo_Class pojoClass) {
        form_class = new Form_Class();
        Bundle bundle = new Bundle();
        bundle.putParcelable("pojoClass", pojoClass);
        form_class.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_home, form_class, "formClass");
        fragmentTransaction.addToBackStack("formClass");
        fragmentTransaction.commit();
    }

    public void fromHomeToListAdventurer() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_home, list_adventurer, "listAdventurer");
        fragmentTransaction.addToBackStack("listAdventurer");
        fragmentTransaction.commit();
    }

    public void fromHomeToListClass() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_home, list_class, "listClass");
        fragmentTransaction.addToBackStack("listClass");
        fragmentTransaction.commit();
    }

    public void showHome() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_home, home, "homeFragment");
        fragmentTransaction.commit();
    }
}
