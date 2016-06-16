package om.gov.ita.drawerbottomnavtabsmenu;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

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
    private Spinner genderSpinner;
    private EditText specialityEditText;
    private EditText bioEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        String name = intent.getStringExtra(ProfileActivity.NAME_EXTRA_ID);
        String gender = intent.getStringExtra(ProfileActivity.GENDER_EXTRA_ID);
        String specialisation = intent.getStringExtra(ProfileActivity.SPECIALISATION_EXTRA_ID);
        String bio = intent.getStringExtra(ProfileActivity.BIO_EXTRA_ID);

        nameEditText = (EditText) findViewById(R.id.et_name_edit);
        nameEditText.setText(name);

        genderSpinner = (Spinner) findViewById(R.id.spinner_gender_edit);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);
        if(gender.equalsIgnoreCase("male")){
            genderSpinner.setSelection(1);
        }else if(gender.equalsIgnoreCase("female")){
            genderSpinner.setSelection(2);
        }
        else{
            genderSpinner.setSelection(0);
        }

        specialityEditText = (EditText) findViewById(R.id.et_speciality_edit);
        specialityEditText.setText(specialisation);

        bioEditText = (EditText) findViewById(R.id.et_bio_edit);
        bioEditText.setText(bio);
    }

    @Override
    void dialogueAction(MenuItem item) {

        //plug new values into intent
        Intent intent = new Intent();
        intent.putExtra(ProfileActivity.NAME_EXTRA_ID, nameEditText.getText().toString());
        intent.putExtra(ProfileActivity.SPECIALISATION_EXTRA_ID, specialityEditText.getText().toString());
        intent.putExtra(ProfileActivity.BIO_EXTRA_ID, bioEditText.getText().toString());

        //plug gender into intent
        int genderPosition = genderSpinner.getSelectedItemPosition();
        if(genderPosition == 0){
            intent.putExtra(ProfileActivity.GENDER_EXTRA_ID, "");
        }
        else if(genderPosition == 1){
            intent.putExtra(ProfileActivity.GENDER_EXTRA_ID, "male");
        }
        else{
            intent.putExtra(ProfileActivity.GENDER_EXTRA_ID, "female");
        }

        //finish the job
        setResult(Activity.RESULT_OK,intent);
        finish();
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
