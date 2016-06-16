package om.gov.ita.drawerbottomnavtabsmenu;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

public class EditProfileContactsActivity extends BaseDialogActivity {

    EditText phoneEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String phoneNumber = getIntent().getStringExtra(ProfileActivity.PHONE_EXTRA_ID);
        phoneEditText = (EditText) findViewById(R.id.et_phone_edit);
        phoneEditText.setText(phoneNumber);
    }

    @Override
    void dialogueAction(MenuItem item) {
        Intent intent = new Intent();
        intent.putExtra(ProfileActivity.PHONE_EXTRA_ID, phoneEditText.getText().toString());

        setResult(Activity.RESULT_OK,intent);
        finish();
    }

    @Override
    int getLayoutResourceId() {
        return R.layout.activity_edit_profile_contacts;
    }

    @Override
    void setSaveText(MenuItem menuItem) {
        menuItem.setTitle("Save");
    }
}
