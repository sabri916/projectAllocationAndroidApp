package om.gov.ita.drawerbottomnavtabsmenu;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
public class ProposalsListFragment extends Fragment {

    private static final int NEW_IDEA_RQ_CODE = 1;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton addProposalFab;
    private View rootView;

    private ArrayList<Proposal> ideasArrayList;

    public ProposalsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.recycler_with_fab,container,false);
        this.rootView = rootView;

        //get data from DB
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("ideas");
        Query queryRef = dbRef.orderByChild("title");
        final ProposalListAdapter adapter = new ProposalListAdapter(getActivity(),new ArrayList<Proposal>());
        queryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> personIterable= dataSnapshot.getChildren();
                Iterator i = personIterable.iterator();
                ArrayList<Proposal> proposalArrayList = new ArrayList<Proposal>();
                while(i.hasNext()){
                    DataSnapshot personData = (DataSnapshot) i.next();
                    Proposal proposal = personData.getValue(Proposal.class);
                    proposalArrayList.add(proposal);
                }
                adapter.setProposalArrayList(proposalArrayList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_list);
        layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        addProposalFab = (FloatingActionButton) rootView.findViewById(R.id.id_fab_add_propoal);
        addProposalFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreateProposalDialogActivity.class);
                startActivityForResult(intent,NEW_IDEA_RQ_CODE);
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == NEW_IDEA_RQ_CODE){
            if(resultCode == Activity.RESULT_OK){
                Snackbar.make(rootView,"Idea Successfully Added =D",Snackbar.LENGTH_LONG).show();
            }
        }
    }
}
