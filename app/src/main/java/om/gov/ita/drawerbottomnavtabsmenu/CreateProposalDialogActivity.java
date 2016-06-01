package om.gov.ita.drawerbottomnavtabsmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class CreateProposalDialogActivity extends BaseDialogActivity {

    @Override
    void dialogueAction() {

    }

    @Override
    void setLayout() {
        setContentView(R.layout.activity_create_proposal_dialog);
    }

    @Override
    void setSaveText(MenuItem menuItem) {
        menuItem.setTitle("Create");
    }
}
