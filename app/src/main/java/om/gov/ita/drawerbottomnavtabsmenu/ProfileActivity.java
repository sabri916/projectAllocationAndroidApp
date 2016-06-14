package om.gov.ita.drawerbottomnavtabsmenu;

import android.content.Intent;
import android.renderscript.Sampler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    private TextView nameTextView;
    private TextView genderTextView;
    private TextView specialityTextView;
    private TextView bioTextView;

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
    }
}
