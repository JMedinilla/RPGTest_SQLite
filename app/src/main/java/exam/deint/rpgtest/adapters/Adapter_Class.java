package exam.deint.rpgtest.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import exam.deint.rpgtest.Constants;
import exam.deint.rpgtest.R;
import exam.deint.rpgtest.pojos.Class;

public class Adapter_Class extends ArrayAdapter<Class> {
    private Context context;

    public Adapter_Class(Context context) {
        super(context, R.layout.adapter_class);
        this.context = context;
    }

    public void updateList(List<Class> list) {
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
        ClassHolder classHolder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_class, parent, false);
            classHolder = new ClassHolder(view);
            view.setTag(classHolder);
        } else {
            classHolder = (ClassHolder) view.getTag();
        }

        Class classVar = getItem(position);
        if (classVar != null) {
            classHolder.name.setText(classVar.getCl_name());
            classHolder.role.setText(classVar.getCl_role());
            classHolder.weapon.setText(classVar.getCl_weapon());

            if (classVar.getCl_role().equals(Constants.CLASS_ROLE_DPS)) {
                view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorDPS));
            }
            if (classVar.getCl_role().equals(Constants.CLASS_ROLE_TANK)) {
                view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorTANK));
            }
            if (classVar.getCl_role().equals(Constants.CLASS_ROLE_HEALER)) {
                view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorHEALER));
            }
        }

        return view;
    }

    @Nullable
    @Override
    public Class getItem(int position) {
        return super.getItem(position);
    }

    static class ClassHolder {
        @BindView(R.id.adapterClass_name)
        TextView name;
        @BindView(R.id.adapterClass_role)
        TextView role;
        @BindView(R.id.adapterClass_weapon)
        TextView weapon;

        ClassHolder(View v) {
            ButterKnife.bind(this, v);
        }
    }
}
