package om.gov.ita.drawerbottomnavtabsmenu;

import android.content.Intent;
import android.media.Image;
import android.provider.ContactsContract;
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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import org.w3c.dom.Text;

public class MainActivity extends BaseFirebaseAuthenticationActivity {

    private BottomBar bottomBar;


    private Toolbar mainToolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private FragmentTransaction fragmentTransaction;
    private NavigationView navigationView;
    private CoordinatorLayout coordinatorLayout;

    private ProposalsListFragment proposalsListFragment;
    private PeopleTabbedListFragment peopleTabbedListFragment;
    private TeamListFragment teamListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Coordinator Layout
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout_main_fragment_container);

        //drawer

        //rest of the drawer - header view below
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.drawer_manu_logout:
                        FirebaseAuth.getInstance().signOut();
                }


                return true;
            }
        });

        //headerView
        View headerView = navigationView.getHeaderView(0);
        ImageView profilePhotoImageView = (ImageView) headerView.findViewById(R.id.profile_photo);
        profilePhotoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("nav_header","profile photo clicked");
                Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });

        TextView headerNameTextView = (TextView) headerView.findViewById(R.id.tv_drawer_full_name);
        if(firebaseAuth.getCurrentUser()!=null) {
            headerNameTextView.setText(firebaseAuth.getCurrentUser().getDisplayName());
        }

        //toolbar
        mainToolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(mainToolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,mainToolbar,
                R.string.drawer_open_tag,R.string.drawer_close_tag);

        //fragments init
        peopleTabbedListFragment = new PeopleTabbedListFragment();
        teamListFragment = new TeamListFragment();

        //first Fragment
        proposalsListFragment = new ProposalsListFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.coordinator_layout_main_fragment_container,proposalsListFragment).commit();
        getSupportActionBar().setTitle("Ideas");

        //Bottombar
        bottomBar = BottomBar.attach(findViewById(R.id.coordinator_layout_main_fragment_container),savedInstanceState);
        bottomBar.useFixedMode();
        bottomBar.setItemsFromMenu(R.menu.bottom_navigation, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                switch (menuItemId) {
                    case R.id.proposals_menu_item:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.coordinator_layout_main_fragment_container,proposalsListFragment).commit();
                        getSupportActionBar().setTitle("Ideas");
                        break;
                    case R.id.discipals_menu_item:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.coordinator_layout_main_fragment_container,peopleTabbedListFragment).commit();
                        getSupportActionBar().setTitle("People");
                        break;
                    case R.id.teams_menu_item:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.coordinator_layout_main_fragment_container,teamListFragment).commit();
                        getSupportActionBar().setTitle("Teams");
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
    protected void notLoggedInAction() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
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
