package om.gov.ita.drawerbottomnavtabsmenu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class EditProfileSkillsActivity extends BaseDialogActivity {

    private ArrayList<String> interestsArrayList;
    private SkillListAdapter adapter;

    private ListView interestListView;
    private EditText interestEditText;
    private Button addInterestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent intent = getIntent();

        String[] interestsArray = intent.getStringArrayExtra(ProfileActivity.INTEREST_EXTRA_ID);
        interestsArrayList = new ArrayList<String>(Arrays.asList(interestsArray));
        for (String i : interestsArray) {
            Log.i(TAG, i);
        }

        adapter =
                new SkillListAdapter(getBaseContext(),R.layout.list_item_skill_interest,interestsArrayList);
        interestListView = (ListView) findViewById(R.id.lv_intrests_edit);
        interestListView.setAdapter(adapter);

        interestEditText = (EditText) findViewById(R.id.et_interests_edit);

        addInterestButton = (Button) findViewById(R.id.btn_add_intrest);
        addInterestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "add button pressed");
                String interest = interestEditText.getText().toString();
                interestEditText.setText("");
                interestsArrayList.add(interest);
                adapter.notifyDataSetChanged();
                interestListView.post(new Runnable() {
                    @Override
                    public void run() {
                        // Select the last row so it will scroll into view...
                        interestListView.setSelection(interestListView.getCount() - 1);
                    }
                });
            }
        });
    }

    @Override
    void dialogueAction(MenuItem item) {
        Intent intent = new Intent();
        String[] interestArray = new String[adapter.getSkills().size()];
        interestArray = adapter.getSkills().toArray(interestArray);
        intent.putExtra(ProfileActivity.INTEREST_EXTRA_ID,interestArray);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }

    @Override
    int getLayoutResourceId() {
        return R.layout.activity_edit_profile_skills;
    }

    @Override
    void setSaveText(MenuItem menuItem) {
        menuItem.setTitle("Save");
    }
}
