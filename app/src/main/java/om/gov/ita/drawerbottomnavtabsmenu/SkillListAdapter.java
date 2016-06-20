package om.gov.ita.drawerbottomnavtabsmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SkillListAdapter extends ArrayAdapter<String> {

    private Context context;
    private int layout;
    private ArrayList<String> skills;

    public SkillListAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        layout = resource;
        skills = (ArrayList<String>) objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //To be optimised
        View view = LayoutInflater.from(context).inflate(layout,parent,false);
        TextView skillTextView = (TextView) view.findViewById(R.id.tv_skill_item);
        ImageView deleteButton = (ImageView) view.findViewById(R.id.iv_skill_delete_icon);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skills.remove(position);
                notifyDataSetChanged();
            }
        });
        skillTextView.setText(skills.get(position));
        return view;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }
}