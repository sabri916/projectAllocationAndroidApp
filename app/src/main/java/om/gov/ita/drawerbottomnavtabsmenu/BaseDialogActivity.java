package om.gov.ita.drawerbottomnavtabsmenu;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseDialogActivity extends BaseFirebaseAuthenticationActivity {

    final static String TAG = "BaseDialogActivity";

    private Toolbar createProposalToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        createProposalToolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(createProposalToolbar);
        createProposalToolbar.setNavigationIcon(R.drawable.ic_close_white_24dp);
        createProposalToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"on back click");
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu_create_item, menu);
        MenuItem menuItem = menu.findItem(R.id.save_create_item_toolbar);
        setSaveText(menuItem);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.save_create_item_toolbar) {
            dialogueAction(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void notLoggedInAction(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /* Action of the save Button*/
    abstract void dialogueAction(MenuItem item);

    /*return layout resource id*/
    abstract int getLayoutResourceId();

    /*Set properties of the Save Button*/
    abstract void setSaveText(MenuItem menuItem);
}