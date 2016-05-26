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

import java.util.ArrayList;


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
        dummyPersons = createDummyArrayList();
        layoutManager = new GridLayoutManager(getActivity(),1);
        recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new PersonsListAdapter(getActivity(),dummyPersons));

        return recyclerView;
    }

    private ArrayList<Person> createDummyArrayList() {
        ArrayList<Person> dummyPersons = new ArrayList<Person>();
        for(int i=0; i<20 ; i++){
            if(i%2 == 0){
                dummyPersons.add(new Person("Sabri K","Computer Science"));

            }
            else{
                dummyPersons.add(new Person("Ian Grimstead","Discrete Maths"));
            }
        }
        return dummyPersons;
    }

}
