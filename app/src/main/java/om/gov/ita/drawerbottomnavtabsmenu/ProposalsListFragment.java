package om.gov.ita.drawerbottomnavtabsmenu;


import android.os.Bundle;
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

    private ArrayList<Proposal> dummyProposals;

    private String[] titles = {"Project Management Android Application", "Blue", "Green", "Red", "Orange",
            "Yellow", "Blue", "Green", "Red", "Orange",
            "Yellow", "Blue", "Green", "Red", "Orange",
            "Yellow", "Blue", "Green", "Red", "Orange",
            "Yellow", "Blue", "Green", "Red", "Orange",
            "Yellow", "Blue", "Green", "Red"};

    public ProposalsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        dummyProposals = createDummyArrayList();

        View rootView = inflater.inflate(R.layout.fragment_proposal_list,container,false);
        layoutManager = new GridLayoutManager(getActivity(),1);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_proposals_list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new ProposalListAdapter(getActivity(),dummyProposals));


        // Inflate the layout for this fragment
        return rootView;
    }

    private ArrayList<Proposal> createDummyArrayList() {
        ArrayList<Proposal> dummyProposals = new ArrayList<Proposal>();
        for(int i=0; i<titles.length ; i++){
            if(i%2 == 0){
                dummyProposals.add(new Proposal(titles[i],"Sabri K","Proposal Description Proposal Description Proposal Description Proposal Description Proposal Description Proposal Description Proposal Description Proposal Description Proposal Description ",true));

            }
            else{
                dummyProposals.add(new Proposal(titles[i],"Sabri K","Proposal Description Proposal Description Proposal Description Proposal Description Proposal Description Proposal Description Proposal Description Proposal Description Proposal Description ",false));
            }
        }
        return dummyProposals;
    }

}
