package om.gov.ita.drawerbottomnavtabsmenu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by training-4 on 5/24/16.
 */
public class PeopleViewPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragmentArray;

    public PeopleViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentArray = new ArrayList<Fragment>();
    }

    public void addFragment(Fragment f){
        this.fragmentArray.add(f);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArray.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArray.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "yellow";
    }
}
