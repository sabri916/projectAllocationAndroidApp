package om.gov.ita.drawerbottomnavtabsmenu;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiscipleListFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<Person> dummyPersons;

    public DiscipleListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        dummyPersons = DummyData.createDummyPersons(50);
        layoutManager = new GridLayoutManager(getActivity(),1);
        recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutManager(layoutManager);

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("users");
        Query queryRef = dbRef.orderByChild("name");
        final PersonsListAdapter adapter = new PersonsListAdapter(getActivity(),new ArrayList<Person>());
        queryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> personIterable= dataSnapshot.getChildren();
                Iterator i = personIterable.iterator();
                ArrayList<Person> personArrayList = new ArrayList<Person>();
                while(i.hasNext()){
                    DataSnapshot personData = (DataSnapshot) i.next();
                    Person person = personData.getValue(Person.class);
                    personArrayList.add(person);
                }
                adapter.setPersonArrayList(personArrayList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }
}
