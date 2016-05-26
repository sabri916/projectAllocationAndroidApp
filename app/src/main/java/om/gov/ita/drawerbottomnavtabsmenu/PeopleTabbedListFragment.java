package om.gov.ita.drawerbottomnavtabsmenu;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class PeopleTabbedListFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PeopleViewPagerAdapter peopleViewPagerAdapter;

    public PeopleTabbedListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tabbed_view_pager,container,false);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tab_layout_people_list);
        viewPager = (ViewPager) rootView.findViewById(R.id.vp_people_list);
        String[] tabTitles = {"Disciples", "Mentors"};

        peopleViewPagerAdapter = new PeopleViewPagerAdapter(getChildFragmentManager(),tabTitles);
        peopleViewPagerAdapter.addFragment(new DiscipleListFragment());
        peopleViewPagerAdapter.addFragment(new DiscipleListFragment());

        viewPager.setAdapter(peopleViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }

}
