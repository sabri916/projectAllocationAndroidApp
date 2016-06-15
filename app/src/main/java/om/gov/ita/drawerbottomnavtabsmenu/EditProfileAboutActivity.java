package om.gov.ita.drawerbottomnavtabsmenu;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfileAboutActivity extends BaseDialogActivity {

    final private String TAG = "editAbout";

    private DatabaseReference dbRef;
    private Person person;

    private EditText nameEditText;
    private EditText genderEditText;
    private EditText specialityEditText;
    private EditText bioEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dbRef = FirebaseDatabase.getInstance().getReference(uid);
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i(TAG,"data read");
                person = dataSnapshot.getValue(Person.class);
                Log.i(TAG,"user's Name: " + person.getName());
                updateUi(person);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i(TAG,"failure Error: " + databaseError.toString());
            }
        });
    }

    @Override
    void dialogueAction(MenuItem item) {



        //finish the job
        setResult(Activity.RESULT_OK);
        finish();
    }

    private void updateUi(Person person) {
        nameEditText = (EditText) findViewById(R.id.et_name_edit);
        nameEditText.setText(person.getName());

        genderEditText = (EditText) findViewById(R.id.et_gender_edit);
        genderEditText.setText(person.getGender());
        specialityEditText = (EditText) findViewById(R.id.et_speciality_edit);
        bioEditText = (EditText) findViewById(R.id.et_bio_edit);

    }

    @Override
    int getLayoutResourceId() {
        return R.layout.activity_dialog_profile_about_edit;
    }

    @Override
    void setSaveText(MenuItem menuItem) {
        menuItem.setTitle("Save");
    }
}
