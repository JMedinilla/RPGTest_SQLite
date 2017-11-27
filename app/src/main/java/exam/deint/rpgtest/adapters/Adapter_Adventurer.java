package exam.deint.rpgtest.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import exam.deint.rpgtest.R;
import exam.deint.rpgtest.pojos.AdapterPojo_Adventurer;

public class Adapter_Adventurer extends ArrayAdapter<AdapterPojo_Adventurer> {
    private Context context;

    public Adapter_Adventurer(Context context) {
        super(context, R.layout.adapter_adventurer);
        this.context = context;
    }

    public void updateList(List<AdapterPojo_Adventurer> list) {
        clear();
        if (list != null) {
            if (list.size() > 0) {
                addAll(list);
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        AdventurerHolder adventurerHolder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_adventurer, parent, false);
            adventurerHolder = new AdventurerHolder();
            adventurerHolder.mName = view.findViewById(R.id.adapterAdventurer_name);
            adventurerHolder.mRace = view.findViewById(R.id.adapterAdventurer_race);
            adventurerHolder.mClass = view.findViewById(R.id.adapterAdventurer_class);
            adventurerHolder.mStr = view.findViewById(R.id.adapterAdventurer_str);
            adventurerHolder.mDex = view.findViewById(R.id.adapterAdventurer_dex);
            adventurerHolder.mInt = view.findViewById(R.id.adapterAdventurer_int);
            view.setTag(adventurerHolder);
        } else {
            adventurerHolder = (AdventurerHolder) view.getTag();
        }

        AdapterPojo_Adventurer adventurer = getItem(position);
        if (adventurer != null) {
            adventurerHolder.mName.setText(adventurer.getRad_name());
            adventurerHolder.mRace.setText(adventurer.getRad_race());
            adventurerHolder.mClass.setText(adventurer.getRad_class());
            adventurerHolder.mStr.setText(String.valueOf(adventurer.getRad_str()));
            adventurerHolder.mDex.setText(String.valueOf(adventurer.getRad_dex()));
            adventurerHolder.mInt.setText(String.valueOf(adventurer.getRad_int()));
        }

        return view;
    }

    @Nullable
    @Override
    public AdapterPojo_Adventurer getItem(int position) {
        return super.getItem(position);
    }

    private class AdventurerHolder {
        TextView mName;
        TextView mRace;
        TextView mClass;
        TextView mStr;
        TextView mDex;
        TextView mInt;
    }
}
