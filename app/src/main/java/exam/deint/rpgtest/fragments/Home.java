package exam.deint.rpgtest.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import exam.deint.rpgtest.R;

public class Home extends Fragment implements View.OnClickListener {
    HomeInterface homeInterface;

    Button btnHomeClass;
    Button btnHomeAdventurer;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnHomeClasses:
                homeInterface.fromHomeToListClass();
                break;
            case R.id.btnHomeAdventurers:
                homeInterface.fromHomeToListAdventurer();
                break;
        }
    }

    public interface HomeInterface {
        void fromHomeToListAdventurer();

        void fromHomeToListClass();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btnHomeClass = (Button) view.findViewById(R.id.btnHomeClasses);
        btnHomeClass.setOnClickListener(this);
        btnHomeAdventurer = (Button) view.findViewById(R.id.btnHomeAdventurers);
        btnHomeAdventurer.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        homeInterface = (HomeInterface) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        homeInterface = null;
    }
}
