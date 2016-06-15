package om.gov.ita.drawerbottomnavtabsmenu;

import android.content.Intent;
import android.renderscript.Sampler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends BaseFirebaseAuthenticationActivity {

    private DatabaseReference dbRef;

    //About Card
    private ImageView editAboutImageView;
    private TextView nameTextView;
    private TextView genderTextView;
    private TextView specialityTextView;
    private TextView bioTextView;

    //Skills Card
    private ImageView editSkillsImageView;
    private TextView interestsTextview;
    private TextView skillsTextView;

    //Contact Details Card
    private ImageView editContactsImageView;
    private TextView phoneTextView;
    private TextView emailTextView;

    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Log.i("profile","activity started");

        String uid = firebaseAuth.getCurrentUser().getUid();
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

        //Edit Buttons
        editAboutImageView = (ImageView) findViewById(R.id.iv_profile_about_edit);
        editAboutImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("profile","About Edit button pressed");
                Snackbar.make(findViewById(R.id.coordinator_layout_profile_main),"Edit About",Snackbar.LENGTH_SHORT).show();
            }
        });

        editSkillsImageView = (ImageView) findViewById(R.id.iv_profile_skills_edit);
        editSkillsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("profile","Skills Edit button pressed");
                Snackbar.make(findViewById(R.id.coordinator_layout_profile_main),"Edit Skills",Snackbar.LENGTH_SHORT).show();
            }
        });

        editContactsImageView = (ImageView) findViewById(R.id.iv_profile_contacts_edit);
        editContactsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("profile","Skills Edit button pressed");
                Snackbar.make(findViewById(R.id.coordinator_layout_profile_main),"Edit Skills",Snackbar.LENGTH_SHORT).show();
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

        //Skill Card
        interestsTextview = (TextView) findViewById(R.id.tv_profile_interests_content);
        interestsTextview.setText("dummy interests");

        skillsTextView = (TextView) findViewById(R.id.tv_profile_skills_content);
        skillsTextView.setText("dummy skills");


        //Contact Details Card
        phoneTextView = (TextView) findViewById(R.id.tv_profile_phone_number);
        phoneTextView.setText(person.getPhoneNumber());

        emailTextView = (TextView) findViewById(R.id.tv_profile_email);
        emailTextView.setText(person.getEmail());
    }
}
