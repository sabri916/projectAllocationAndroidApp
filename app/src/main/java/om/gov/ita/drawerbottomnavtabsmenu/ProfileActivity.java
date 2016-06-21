package om.gov.ita.drawerbottomnavtabsmenu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class ProfileActivity extends BaseFirebaseAuthenticationActivity {

    private static final int EDIT_ABOUT_REQUEST_CODE = 0;
    private static final int EDIT_CONTACTS_REQUEST_CODE = 1;
    private static final int EDIT_INTERESTS_REQUEST_CODE = 2;
    private static final int EDIT_SKILLS_REQUEST_CODE = 3;

    //Current user extra
    final static String CURRENT_USER_EXTRA_ID = "currentUser";

    //about card extra
    final static String NAME_EXTRA_ID = "name";
    final static String GENDER_EXTRA_ID = "gender";
    final static String SPECIALISATION_EXTRA_ID = "specialisation";
    final static String BIO_EXTRA_ID = "bio";

    //skill card extra
    static final String INTERESTS_EXTRA_ID = "interests";
    static final String SKILLS_EXTRA_ID = "skills";

    //Contacts card extra
    final static String PHONE_EXTRA_ID = "phone";

    private DatabaseReference dbRef;

    //About Card
    private ImageView editAboutImageView;
    private TextView nameTextView;
    private TextView genderTextView;
    private TextView specialityTextView;
    private TextView bioTextView;

    //Skills Card
    private ImageView editInterestsImageView;
    private ImageView editSkillsImageView;
    private TextView interestsTextview;
    private TextView skillsTextView;

    //Contact Details Card
    private ImageView editContactsImageView;
    private TextView phoneTextView;
    private TextView emailTextView;

    private Person person;
    private boolean isEditable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Log.i("profile","activity started");

        Intent intent = getIntent();
        String intentUid = intent.getStringExtra(CURRENT_USER_EXTRA_ID);
        String uid = firebaseAuth.getCurrentUser().getUid();
        if(uid.equals(intentUid)){
            isEditable = true;
        }else{
            isEditable = false;
            uid = intentUid;
        }
        dbRef = FirebaseDatabase.getInstance().getReference("users").child(uid);
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("profile","data read");
                person = dataSnapshot.getValue(Person.class);
                Log.i("profile","user's Name: " + person.getName());
                updateUi(person);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("profile","failure Error: " + databaseError.toString());
            }
        });
        Log.i("profile","End of listener");

        //Edit About Card
        editAboutImageView = (ImageView) findViewById(R.id.iv_profile_about_edit);
        editAboutImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person != null) {
                    Log.i("profile", "About Edit button pressed");
                    Snackbar.make(findViewById(R.id.coordinator_layout_profile_main), "Edit About", Snackbar.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProfileActivity.this, EditProfileAboutActivity.class);
                    intent.putExtra(NAME_EXTRA_ID, person.getName());
                    intent.putExtra(GENDER_EXTRA_ID, person.getGender());
                    intent.putExtra(SPECIALISATION_EXTRA_ID, person.getSpeciality());
                    intent.putExtra(BIO_EXTRA_ID, person.getBio());
                    startActivityForResult(intent, EDIT_ABOUT_REQUEST_CODE);
                }
            }
        });

        //edit Interests - Skills Card
        editInterestsImageView = (ImageView) findViewById(R.id.iv_profile_interests_edit);
        editInterestsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("profile","Interests Edit button pressed");
                interestsTextview = (TextView) findViewById(R.id.tv_profile_interests_content);
                String interests = interestsTextview.getText().toString();
                String[] interestsArray = interests.split("\n");

                Intent intent = new Intent(ProfileActivity.this, EditProfileSkillsActivity.class);
                intent.putExtra(INTERESTS_EXTRA_ID,interestsArray);
                startActivityForResult(intent, EDIT_INTERESTS_REQUEST_CODE);
            }
        });

        editSkillsImageView = (ImageView) findViewById(R.id.iv_profile_skills_edit);
        editSkillsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("profile","Skills Edit button pressed");
                skillsTextView = (TextView) findViewById(R.id.tv_profile_skills_content);

                String skills = skillsTextView.getText().toString();
                String[] skillsArray = skills.split("\n");

                Intent intent = new Intent(ProfileActivity.this, EditProfileSkillsActivity.class);
                intent.putExtra(SKILLS_EXTRA_ID,skillsArray);
                startActivityForResult(intent, EDIT_SKILLS_REQUEST_CODE);
            }
        });

        //edit Contacts
        editContactsImageView = (ImageView) findViewById(R.id.iv_profile_contacts_edit);
        editContactsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("profile","Skills Edit button pressed");
                Snackbar.make(findViewById(R.id.coordinator_layout_profile_main), "Edit About", Snackbar.LENGTH_SHORT).show();
                Intent intent = new Intent(ProfileActivity.this, EditProfileContactsActivity.class);
                intent.putExtra(PHONE_EXTRA_ID, person.getPhoneNumber());
                startActivityForResult(intent,EDIT_CONTACTS_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void notLoggedInAction() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void updateUi(Person person){
        //aboutCard
        nameTextView = (TextView) findViewById(R.id.tv_profile_name_content);
        nameTextView.setText(person.getName());

        genderTextView = (TextView) findViewById(R.id.tv_profile_gender_content);
        genderTextView.setText(person.getGender());

        specialityTextView = (TextView) findViewById(R.id.tv_profile_speciality_content);
        specialityTextView.setText(person.getSpeciality());

        bioTextView = (TextView) findViewById(R.id.tv_profile_bio_content);
        bioTextView.setText(person.getBio());

        if(isEditable)
            editAboutImageView.setVisibility(View.VISIBLE);

        //Skill Card
        //interests
        interestsTextview = (TextView) findViewById(R.id.tv_profile_interests_content);
        ArrayList<String> interestList = person.getInterests();
        if(interestList != null) {
            StringBuilder interestStringBuilder = new StringBuilder();
            for (String i : interestList) {
                interestStringBuilder.append(i + "\n");
            }
            String outputString = interestStringBuilder.toString().trim();
            interestsTextview.setText(outputString);
        }
        if(isEditable)
            editInterestsImageView.setVisibility(View.VISIBLE);

        //skills
        skillsTextView = (TextView) findViewById(R.id.tv_profile_skills_content);
        ArrayList<String> skillsList = person.getSkills();
        if(skillsList != null) {
            StringBuilder skillStringBuilder = new StringBuilder();
            for (String i : skillsList) {
                skillStringBuilder.append(i + "\n");
                Log.i("profile", i);
            }
            String outputString = skillStringBuilder.toString().trim();
            skillsTextView.setText(outputString);
        }
        if(isEditable)
            editSkillsImageView.setVisibility(View.VISIBLE);


        //Contact Details Card
        phoneTextView = (TextView) findViewById(R.id.tv_profile_phone_number);
        phoneTextView.setText(person.getPhoneNumber());

        emailTextView = (TextView) findViewById(R.id.tv_profile_email);
        emailTextView.setText(person.getEmail());

        if(isEditable)
            editContactsImageView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if(requestCode == EDIT_ABOUT_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                //unpack intent
                String name = intent.getStringExtra(NAME_EXTRA_ID);
                String gender = intent.getStringExtra(GENDER_EXTRA_ID);
                String specialisation = intent.getStringExtra(SPECIALISATION_EXTRA_ID);
                String bio = intent.getStringExtra(BIO_EXTRA_ID);

                //update person object
                person.setName(name);
                person.setGender(gender);
                person.setSpeciality(specialisation);
                person.setBio(bio);

                updateFirebasePerson(person, "User details updated ='D", "Profile details cannot be updated");
            }
        }
        else if(requestCode == EDIT_CONTACTS_REQUEST_CODE){
            if(resultCode==RESULT_OK){
                String phone = intent.getStringExtra(PHONE_EXTRA_ID);

                person.setPhoneNumber(phone);
                updateFirebasePerson(person, "User details updated ='D", "Profile details cannot be updated");
            }
        }else if(requestCode == EDIT_INTERESTS_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                String interests[] = intent.getStringArrayExtra(INTERESTS_EXTRA_ID);
                ArrayList<String> interestsArrayList = new ArrayList<String>(Arrays.asList(interests));

                person.setInterests(interestsArrayList);
                updateFirebasePerson(person, "Interests updated","Interests could not be updated D=");
            }
        }else if(requestCode == EDIT_SKILLS_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String skills[] = intent.getStringArrayExtra(SKILLS_EXTRA_ID);
                ArrayList<String> skillsArrayList = new ArrayList<String>(Arrays.asList(skills));

                person.setSkills(skillsArrayList);
                updateFirebasePerson(person, "Interests updated", "Interests could not be updated D=");
            }
        }
    }

    private void updateFirebasePerson(Person person, final String successMessage, final String failMessage){
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dbRef = FirebaseDatabase.getInstance().getReference("users").child(uid);
        dbRef.setValue(person).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(!task.isSuccessful()){
                    Snackbar.make(findViewById(R.id.coordinator_layout_profile_main),failMessage,Snackbar.LENGTH_LONG).show();
                }
                else{
                    Snackbar.make(findViewById(R.id.coordinator_layout_profile_main),successMessage,Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
}