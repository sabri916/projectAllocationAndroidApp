package om.gov.ita.drawerbottomnavtabsmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class EditProfileSkillsActivity extends BaseDialogActivity {

    private ArrayList<String> skillsArrayList;
    private SkillListAdapter adapter;

    private ListView skillsListView;
    private EditText skillsEditText;
    private Button addSkillButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent intent = getIntent();
        String[] skillsArray = intent.getStringArrayExtra(ProfileActivity.INTEREST_EXTRA_ID);
        skillsArrayList = new ArrayList<String>(Arrays.asList(skillsArray));
        for (String i : skillsArray) {
            Log.i(TAG, i);
        }

        adapter =
                new SkillListAdapter(getBaseContext(),R.layout.list_item_skill_interest, skillsArrayList);
        skillsListView = (ListView) findViewById(R.id.lv_intrests_edit);
        skillsListView.setAdapter(adapter);

        skillsEditText = (EditText) findViewById(R.id.et_skill_edit);

        addSkillButton = (Button) findViewById(R.id.btn_add_skill);
        addSkillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "add button pressed");
                String skill = skillsEditText.getText().toString();
                skillsEditText.setText("");
                skillsArrayList.add(skill);
                adapter.notifyDataSetChanged();
                skillsListView.post(new Runnable() {
                    @Override
                    public void run() {
                        // Select the last row so it will scroll into view...
                        skillsListView.setSelection(skillsListView.getCount() - 1);
                    }
                });
            }
        });
    }

    @Override
    void dialogueAction(MenuItem item) {
        Intent intent = new Intent();
        String[] skillArray = new String[adapter.getSkills().size()];
        skillArray = adapter.getSkills().toArray(skillArray);
        intent.putExtra(ProfileActivity.INTEREST_EXTRA_ID,skillArray);
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
