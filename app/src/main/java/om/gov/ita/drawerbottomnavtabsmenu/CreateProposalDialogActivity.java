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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import java.util.Calendar;

public class CreateProposalDialogActivity extends BaseDialogActivity {

    private EditText titleEditText;
    private EditText descriptionEditText;
    //private EditText tagsEditText;
    private AutoCompleteTextView tagsEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titleEditText = (EditText) findViewById(R.id.et_project_title);
        descriptionEditText = (EditText) findViewById(R.id.et_project_description);
//        tagsEditText = (EditText) findViewById(R.id.et_project_tags);
        tagsEditText = (AutoCompleteTextView) findViewById(R.id.et_project_tags);

        //Autocomplete Stuff

        String[] tagList = {"Belgium", "France", "Italy", "Germany", "Spain"};
        ArrayAdapter<String> autoCompleteAdapter = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_dropdown_item_1line,tagList);
        tagsEditText.setAdapter(autoCompleteAdapter);

    }

    @Override
    void dialogueAction(MenuItem item) {
        Log.i("menuItem",item.getTitle().toString());

        //Data into variables
        String title = titleEditText.getText().toString();
        String author = "Sabri K";
        String description = descriptionEditText.getText().toString();
        long dateTime = Calendar.getInstance().getTimeInMillis();
        String url = "yellow.docx";
        String tags = tagsEditText.getText().toString();
        Proposal proposal = new Proposal(title,author,description,dateTime,url,false);

        //instantiate repo
        IdeasDbRepo ideasDbRepo = new IdeasDbRepo(getApplicationContext());
        TagDbRepo tagDbRepo = new TagDbRepo(getApplicationContext());
        IdeasTagRelationshipRepo ideasTagRelationshipRepo =
                new IdeasTagRelationshipRepo(getApplicationContext());

        //Insert data
        String ideaId = String.valueOf(ideasDbRepo.insert(proposal));
        String tagId = String.valueOf(tagDbRepo.insert(tags));
        ideasTagRelationshipRepo.insert(ideaId,tagId);

        //finish the job
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
