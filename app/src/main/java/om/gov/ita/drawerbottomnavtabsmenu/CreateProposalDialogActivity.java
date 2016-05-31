package om.gov.ita.drawerbottomnavtabsmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

public class CreateProposalDialogActivity extends AppCompatActivity {

    private Toolbar createProposalToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_proposal_dialog);

        createProposalToolbar = (Toolbar) findViewById(R.id.toolbar_main);
        createProposalToolbar.setTitle("New Idea");
        setSupportActionBar(createProposalToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu_create_item, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
