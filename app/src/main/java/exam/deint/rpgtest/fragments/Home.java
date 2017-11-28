package exam.deint.rpgtest.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import exam.deint.rpgtest.R;

public class Home extends Fragment {

    private Unbinder unbinder;
    private HomeInterface homeInterface;

    @OnClick({R.id.btnHomeClasses, R.id.btnHomeAdventurers})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnHomeClasses:
                homeInterface.fromHomeToListClass();
                break;
            case R.id.btnHomeAdventurers:
                homeInterface.fromHomeToListAdventurer();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface HomeInterface {
        void fromHomeToListAdventurer();

        void fromHomeToListClass();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
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
