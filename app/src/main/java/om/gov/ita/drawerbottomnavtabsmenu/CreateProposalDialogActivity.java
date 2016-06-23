package om.gov.ita.drawerbottomnavtabsmenu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
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
import android.widget.Button;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class CreateProposalDialogActivity extends BaseDialogActivity {

    private static final int PDF_REQUEST_CODE = 1;

    private EditText titleEditText;
    private EditText descriptionEditText;
    private AutoCompleteTextView tagsEditText;

    private Button selectFileButton;

    private File file;
    private String filePath;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titleEditText = (EditText) findViewById(R.id.et_project_title);
        descriptionEditText = (EditText) findViewById(R.id.et_project_description);
        tagsEditText = (AutoCompleteTextView) findViewById(R.id.et_project_tags);
        selectFileButton = (Button) findViewById(R.id.btn_upload_project_body);
        selectFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf"); //set mime type as per requirement
                startActivityForResult(intent,PDF_REQUEST_CODE);
            }
        });
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

        //Save file into Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://project-allocation-project.appspot.com/");
        storageRef.child(uid);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PDF_REQUEST_CODE && resultCode == RESULT_OK){
            Uri fileuri = data.getData();
            filePath = fileuri.getPath();
            Log.i(TAG,filePath);
            try{
                file = new File(new URI(filePath));
            }catch (Exception e){
                Log.i(TAG,e.toString());
            }
        }
    }
}
