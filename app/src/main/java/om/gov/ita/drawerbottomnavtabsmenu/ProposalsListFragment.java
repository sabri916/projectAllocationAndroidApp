package om.gov.ita.drawerbottomnavtabsmenu;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProposalsListFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    //private FloatingActionButton addProposalFab;

    private ArrayList<Proposal> dummyProposals;

    public ProposalsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.recycler_with_fab,container,false);

        dummyProposals = DummyData.createDummyProposals();

        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_list);
        layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new ProposalListAdapter(getActivity(), dummyProposals));

//        addProposalFab = (FloatingActionButton) rootView.findViewById(R.id.id_fab_add_propoal);
//        addProposalFab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Snackbar.make(rootView, "Fab Action", Snackbar.LENGTH_SHORT).show();
//            }
//        });


        // Inflate the layout for this fragment
        return rootView;
    }
}
