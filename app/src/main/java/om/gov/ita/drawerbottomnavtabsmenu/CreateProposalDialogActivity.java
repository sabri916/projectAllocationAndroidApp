package om.gov.ita.drawerbottomnavtabsmenu;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;

public class CreateProposalDialogActivity extends BaseDialogActivity {

    private EditText titleEditText;
    private EditText descriptionEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titleEditText = (EditText) findViewById(R.id.et_project_title);
        descriptionEditText = (EditText) findViewById(R.id.et_project_description);
    }

    @Override
    void dialogueAction(MenuItem item) {
        Log.i("menuItem",item.getTitle().toString());
        String title = titleEditText.getText().toString();
        String author = "Sabri K";
        String description = descriptionEditText.getText().toString();
        long dateTime = Calendar.getInstance().getTimeInMillis();
        String url = "yellow.docx";
        Proposal proposal = new Proposal(title,author,description,dateTime,url,false);
        IdeasDbRepo ideasDbRepo = new IdeasDbRepo(getApplicationContext());
        ideasDbRepo.insert(proposal);
        setResult(Activity.RESULT_OK);
        finish();
    }

    @Override
    int getLayoutResourceId() {
        return R.layout.activity_create_proposal_dialog;
    }

    @Override
    void setSaveText(MenuItem menuItem) {
        menuItem.setTitle(R.string.dialogue_create_item);
    }
}
