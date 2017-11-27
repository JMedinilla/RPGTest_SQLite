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

import exam.deint.rpgtest.R;
import exam.deint.rpgtest.pojos.Pojo_Class;

public class Adapter_Class extends ArrayAdapter<Pojo_Class> {
    private Context context;

    public Adapter_Class(Context context) {
        super(context, R.layout.adapter_class);
        this.context = context;
    }

    public void updateList(List<Pojo_Class> list) {
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
            classHolder = new ClassHolder();
            classHolder.name = view.findViewById(R.id.adapterClass_name);
            classHolder.role = view.findViewById(R.id.adapterClass_role);
            classHolder.weapon = view.findViewById(R.id.adapterClass_weapon);
            view.setTag(classHolder);
        } else {
            classHolder = (ClassHolder) view.getTag();
        }

        Pojo_Class pojo_class = getItem(position);
        if (pojo_class != null) {
            classHolder.name.setText(pojo_class.getCl_name());
            classHolder.role.setText(pojo_class.getCl_role());
            classHolder.weapon.setText(pojo_class.getCl_weapon());
            switch (pojo_class.getCl_role()) {
                case Pojo_Class.CLASS_ROLE_DPS:
                    view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorDPS));
                    break;
                case Pojo_Class.CLASS_ROLE_TANK:
                    view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorTANK));
                    break;
                case Pojo_Class.CLASS_ROLE_HEALER:
                    view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorHEALER));
                    break;
            }
        }

        return view;
    }

    @Nullable
    @Override
    public Pojo_Class getItem(int position) {
        return super.getItem(position);
    }

    private class ClassHolder {
        TextView name;
        TextView role;
        TextView weapon;
    }
}
