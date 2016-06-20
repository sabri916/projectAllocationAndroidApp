package om.gov.ita.drawerbottomnavtabsmenu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by training-4 on 5/24/16.
 */
public class PersonsListAdapter extends RecyclerView.Adapter<PersonsListAdapter.PersonViewHolder> {

    private Context context;
    private ArrayList<Person> personArrayList;

    public PersonsListAdapter(Context context, ArrayList<Person> personArrayList) {
        this.context = context;
        this.personArrayList = personArrayList;
    }

    public ArrayList<Person> getPersonArrayList() {
        return personArrayList;
    }

    public void setPersonArrayList(ArrayList<Person> personArrayList) {
        this.personArrayList = personArrayList;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.card_person,parent,false);
        return new PersonViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        Person person = personArrayList.get(position);
        holder.getAvatarImageView().setImageResource(R.drawable.blank_profile_picture);
        holder.getNameTextView().setText(person.getName());
        holder.getSpecialityTextView().setText(person.getSpeciality());
    }

    @Override
    public int getItemCount() {
        return personArrayList.size();
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;
        private TextView specialityTextView;
        private ImageView avatarImageView;

        public PersonViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.tv_person_list_name);
            specialityTextView = (TextView) itemView.findViewById(R.id.tv_disciple_list_speciality);
            avatarImageView = (ImageView) itemView.findViewById(R.id.iv_person_list_avatar);
        }

        public TextView getNameTextView() {
            return nameTextView;
        }

        public TextView getSpecialityTextView() {
            return specialityTextView;
        }

        public ImageView getAvatarImageView() {
            return avatarImageView;
        }
    }
}
