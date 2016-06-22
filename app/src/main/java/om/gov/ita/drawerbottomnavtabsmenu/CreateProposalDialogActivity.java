package om.gov.ita.drawerbottomnavtabsmenu;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class CreateProposalDialogActivity extends BaseDialogActivity {

    private EditText titleEditText;
    private EditText descriptionEditText;
    private AutoCompleteTextView tagsEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titleEditText = (EditText) findViewById(R.id.et_project_title);
        descriptionEditText = (EditText) findViewById(R.id.et_project_description);
        tagsEditText = (AutoCompleteTextView) findViewById(R.id.et_project_tags);
    }

    @Override
    void dialogueAction(MenuItem item) {
        Log.i("menuItem",item.getTitle().toString());

        //Data into variables
        String title = titleEditText.getText().toString();
        String authorUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String authorName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        String description = descriptionEditText.getText().toString();
        Map dateTimeMap = ServerValue.TIMESTAMP;
        String url = authorUid + "/ideas/filename.doc";
        String tags = tagsEditText.getText().toString();
        String[] tagArray = tags.split("\\s*,\\s*");
        for(String s: tagArray){
            s = s.trim();
            Log.i("tags",s);
        }

        final Proposal proposal = new Proposal(title,authorUid,authorName,description,dateTimeMap,url);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //Save in ideas node
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("ideas");
        final String ideaKey = dbRef.push().getKey();
        proposal.setIdeaKey(ideaKey);
        dbRef.child(ideaKey).setValue(proposal);

        //save ideas within user profile
        dbRef = FirebaseDatabase.getInstance().getReference("users").child(authorUid);
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("profile","data read");
                Person person = dataSnapshot.getValue(Person.class);
                Log.i("profile","user's Name: " + person.getName());
                ArrayList<String> ideasArrayList = person.getIdeas();
                if(ideasArrayList == null) {
                    ideasArrayList = new ArrayList<String>();
                }
                ideasArrayList.add(ideaKey);
                person.setIdeas(ideasArrayList);
                updateFirebasePerson(person,"added!!","could not add idea D=");

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("profile","failure Error: " + databaseError.toString());
            }
        });
        setResult(Activity.RESULT_OK);
        finish();
    }

    private void updateFirebasePerson(Person person, final String successMessage, final String failMessage){
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("users").child(uid);
        dbRef.setValue(person);
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
