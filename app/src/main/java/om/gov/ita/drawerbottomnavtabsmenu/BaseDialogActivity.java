package om.gov.ita.drawerbottomnavtabsmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public abstract class BaseDialogActivity extends AppCompatActivity {

    private Toolbar createProposalToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setLayout();
        createProposalToolbar = (Toolbar) findViewById(R.id.toolbar_main);
        createProposalToolbar.setNavigationIcon(R.drawable.ic_close_white_24dp);
        setSupportActionBar(createProposalToolbar);
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
        dialogueAction();
        return super.onOptionsItemSelected(item);
    }

    /* Action of the save Button*/
    abstract void dialogueAction();

    /*setContentView of the dialogue*/
    abstract void setLayout();

    /*Set properties of the Save Button*/
    abstract void setSaveText(MenuItem menuItem);
}