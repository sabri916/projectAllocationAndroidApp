package om.gov.ita.drawerbottomnavtabsmenu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    private DatabaseReference dbRef;

    private EditText nameEditText;
    private EditText specialityEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        nameEditText = (EditText) findViewById(R.id.et_name);
        specialityEditText = (EditText) findViewById(R.id.et_speciality);
        emailEditText = (EditText) findViewById(R.id.et_email);
        passwordEditText = (EditText) findViewById(R.id.et_password);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser!=null){
                    Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };

        //button
        registerButton = (Button) findViewById(R.id.btn_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.createUserWithEmailAndPassword(emailEditText.getText().toString(),
                        passwordEditText.getText().toString()).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {

                    //if user not created successfully, give message. else, create user profile.
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(RegistrationActivity.this, "Registration Failed D= !!!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            dbRef = FirebaseDatabase.getInstance().getReference("users");
                            FirebaseUser newUser = firebaseAuth.getCurrentUser();
                            Person person = createPerson(newUser.getUid());
                            if(newUser!=null){
                                dbRef.child(firebaseAuth.getCurrentUser().getUid()).setValue(person);
                                UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(person.getName()).build();
                                Log.i("registration", "displayName = " + person.getName());
                                newUser.updateProfile(profileUpdate);
                            }
                        }
                    }
                });
            }
        });
    }

    /**
     * creates Person object to push to firebase
     * @return
     */
    private Person createPerson(String uid){
        String name = nameEditText.getText().toString();
        String speciality = specialityEditText.getText().toString();
        String email = emailEditText.getText().toString();
        Person person = new Person(name,speciality);
        person.setEmail(email);
        person.setUid(uid);
        return person;
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(firebaseAuthListener != null){
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }
}
