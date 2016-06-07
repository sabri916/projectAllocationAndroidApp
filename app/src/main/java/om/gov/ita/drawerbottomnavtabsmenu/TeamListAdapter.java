package om.gov.ita.drawerbottomnavtabsmenu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by training-4 on 5/26/16.
 */
public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.TeamViewHolder> {

    private Context context;
    private ArrayList<Team> teamArrayList;

    public TeamListAdapter(Context context, ArrayList<Team> teamArrayList) {
        this.context = context;
        this.teamArrayList = teamArrayList;
    }

    @Override
    public TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.card_team,parent,false);
        return new TeamViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(TeamViewHolder holder, int position) {
        Team team = teamArrayList.get(position);
        holder.getTeamFounderTextView().setText(team.getTeamName());
        holder.getTeamFounderTextView().setText(team.getTeamName());
    }

    @Override
    public int getItemCount() {
        return teamArrayList.size();
    }

    public class TeamViewHolder extends RecyclerView.ViewHolder {
        private TextView teamNameTextView;
        private TextView teamFounderTextView;
        private TextView teamInterestsTextView;


        public TeamViewHolder(View itemView) {
            super(itemView);
            teamNameTextView = (TextView) itemView.findViewById(R.id.tv_team_name);
            teamFounderTextView = (TextView) itemView.findViewById(R.id.tv_team_founder);
            teamInterestsTextView = (TextView) itemView.findViewById(R.id.tv_team_interests);
        }

        public TextView getTeamNameTextView() {
            return teamNameTextView;
        }

        public TextView getTeamFounderTextView() {
            return teamFounderTextView;
        }

        public TextView getTeamInterestsTextView() {
            return teamInterestsTextView;
        }
    }
}
