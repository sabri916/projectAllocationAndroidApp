package om.gov.ita.drawerbottomnavtabsmenu;

import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;
import com.roughike.bottombar.OnMenuTabSelectedListener;

public class MainActivity extends AppCompatActivity {

    private BottomBar bottomBar;


    private Toolbar mainToolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private FragmentTransaction fragmentTransaction;
    private NavigationView navigationView;
    private CoordinatorLayout coordinatorLayout;

    private ProposalsListFragment proposalsListFragment;
    private PeopleTabbedListFragment peopleTabbedListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Coordinator Layout
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout_main_container);

        //drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        //toolbar
        mainToolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(mainToolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,mainToolbar,
                R.string.drawer_open_tag,R.string.drawer_close_tag);

        //first Fragment
        proposalsListFragment = new ProposalsListFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.coordinator_layout_main_container,proposalsListFragment).commit();
        getSupportActionBar().setTitle("Ideas");

        //fragments init
        peopleTabbedListFragment = new PeopleTabbedListFragment();

        //Bottombar
        bottomBar = BottomBar.attach(findViewById(R.id.coordinator_layout_main_container),savedInstanceState);
        bottomBar.useFixedMode();
        bottomBar.setItemsFromMenu(R.menu.bottom_navigation, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                switch (menuItemId) {
                    case R.id.proposals_menu_item:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.coordinator_layout_main_container,proposalsListFragment).commit();
                        getSupportActionBar().setTitle("Ideas");
                        break;
                    case R.id.discipals_menu_item:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.coordinator_layout_main_container,peopleTabbedListFragment).commit();
                        getSupportActionBar().setTitle("People");
                        break;
                    case R.id.mentors_menu_item:
                        Snackbar.make(coordinatorLayout, "Teams", Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.my_account_menu_item:
                        Snackbar.make(coordinatorLayout, "My Account", Snackbar.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        bottomBar.onSaveInstanceState(outState);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                actionBarDrawerToggle.syncState();
            }
        });
    }
}
