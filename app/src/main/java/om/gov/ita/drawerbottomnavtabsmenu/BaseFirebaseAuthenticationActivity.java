package om.gov.ita.drawerbottomnavtabsmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by training-4 on 6/12/16.
 */
public abstract class BaseFirebaseAuthenticationActivity extends AppCompatActivity {

    protected FirebaseAuth firebaseAuth;
    protected FirebaseAuth.AuthStateListener firebaseAuthListener;



    protected FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser == null){
                    notLoggedInAction();
                }
                else{
                    BaseFirebaseAuthenticationActivity.this.firebaseUser = firebaseUser;
                }
            }
        };
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

    protected FirebaseUser getFirebaseUser() {
        return firebaseUser;
    }

    /*Action if user is not logged in*/
    protected abstract void notLoggedInAction();
}
