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

import butterknife.BindView;
import butterknife.ButterKnife;
import exam.deint.rpgtest.R;
import exam.deint.rpgtest.pojos.AdventurerForList;

public class Adapter_Adventurer extends ArrayAdapter<AdventurerForList> {
    private Context context;

    public Adapter_Adventurer(Context context) {
        super(context, R.layout.adapter_adventurer);
        this.context = context;
    }

    public void updateList(List<AdventurerForList> list) {
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
            adventurerHolder = new AdventurerHolder(view);
            view.setTag(adventurerHolder);
        } else {
            adventurerHolder = (AdventurerHolder) view.getTag();
        }

        AdventurerForList adventurer = getItem(position);
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
    public AdventurerForList getItem(int position) {
        return super.getItem(position);
    }

    static class AdventurerHolder {
        @BindView(R.id.adapterAdventurer_name)
        TextView mName;
        @BindView(R.id.adapterAdventurer_race)
        TextView mRace;
        @BindView(R.id.adapterAdventurer_class)
        TextView mClass;
        @BindView(R.id.adapterAdventurer_str)
        TextView mStr;
        @BindView(R.id.adapterAdventurer_dex)
        TextView mDex;
        @BindView(R.id.adapterAdventurer_int)
        TextView mInt;

        AdventurerHolder(View v) {
            ButterKnife.bind(this, v);
        }
    }
}
