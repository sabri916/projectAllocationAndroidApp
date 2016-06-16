package om.gov.ita.drawerbottomnavtabsmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        //To be optimised
        View view = LayoutInflater.from(context).inflate(layout,parent,false);
        TextView skillTextView = (TextView) view.findViewById(R.id.tv_skill_item);
        skillTextView.setText(skills.get(position));
        return skillTextView;
    }
}