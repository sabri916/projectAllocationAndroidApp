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
 * Created by training-4 on 5/17/16.
 */
public class ProposalListAdapter extends RecyclerView.Adapter<ProposalListAdapter.ProposalViewHolder> {

    private Context context;
    private ArrayList<Proposal> proposalArrayList;

    public ProposalListAdapter(Context context, ArrayList<Proposal> proposalArrayList) {
        this.context = context;
        this.proposalArrayList = proposalArrayList;
    }

    @Override
    public ProposalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.proposal_card,parent,false);
        return new ProposalViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ProposalViewHolder holder, int position) {
        Proposal proposal = proposalArrayList.get(position);
        holder.getTitleTextView().setText(proposal.getTitle());
        holder.getAuthorTextView().setText(proposal.getAuthor());
        holder.getDescriptionTextView().setText(proposal.getDescription());

        if(proposal.isFavourite()){
            holder.getFavouriteStarImageView().setImageResource(R.drawable.ic_star_black_24dp);
        }else{
            holder.getFavouriteStarImageView().setImageResource(R.drawable.ic_star_border_black_24dp);
        }
    }

    @Override
    public int getItemCount() {
        return proposalArrayList.size();
    }

    static public class ProposalViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView authorTextView;
        private TextView descriptionTextView;
        private ImageView favouriteStarImageView;

        public ProposalViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.tv_proposal_title_card);
            authorTextView = (TextView) itemView.findViewById(R.id.tv_proposal_author_card);
            descriptionTextView = (TextView) itemView.findViewById(R.id.tv_proposal_description_card);
            favouriteStarImageView = (ImageView) itemView.findViewById(R.id.iv_favourite_star);
        }

        public TextView getTitleTextView() {
            return titleTextView;
        }

        public TextView getDescriptionTextView() {
            return descriptionTextView;
        }

        public ImageView getFavouriteStarImageView() {
            return favouriteStarImageView;
        }

        public TextView getAuthorTextView() {
            return authorTextView;
        }
    }
}
