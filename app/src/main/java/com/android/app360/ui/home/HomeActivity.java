package com.android.app360.ui.home;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.android.appcompose.layout.tabs.AppFragmentPagerAdapter;
import com.android.appcompose.layout.tabs.AppTabLayout;
import com.android.appcompose.layout.tabs.TabType;
import com.android.app360.R;

public class HomeActivity extends AppCompatActivity {

    private AppTabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (AppTabLayout) findViewById(R.id.tab_host);

        setSupportActionBar(toolbar);



        setupTabLayout();
    }

    private void setupTabLayout(){
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);
        tabLayout.setTextSize(18);
        tabLayout.setAllCaps(false);
        tabLayout.setDistributeEvenly(true);
        tabLayout.setTabType(TabType.ICON_ONLY);
        tabLayout.setSelectedIndicatorColors(getResources().getColor(android.R.color.white));
        tabLayout.setActionBar(getSupportActionBar());
        tabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.black));
        tabLayout.setCustomFocusedColor(getResources().getColor(R.color.black));
        tabLayout.setCustomUnfocusedColor(getResources().getColor(R.color.dark_gray));
        tabLayout.setBackground(getResources().getDrawable(R.drawable.background_tab));
        tabLayout.setViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ShowNotification:
                tabLayout.showIndicator(1);
                return true;

            case R.id.HideNotification:
                tabLayout.hideIndicator(1);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static class TabFragment extends Fragment {

        public static final String POSITION = "position";

        private View view;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            this.view = inflater.inflate(R.layout.fragment_tab, container, false);
            return view;
        }

        @Override
        public void onResume() {
            super.onResume();
            TextView positionText = (TextView) this.findViewById(R.id.FragmentTabText);

            int position = getArguments().getInt(POSITION);
            positionText.setText("Position " + position);
        }

        private View findViewById(int id) {
            return view.findViewById(id);
        }
    }

    public static class TabAdapter extends AppFragmentPagerAdapter {

        private String[] titles = {
                "Home",
                "My Classrooms",
                "My Network",
                "Post",
                "Notifications",
        };

        private int[] icons = {
                R.drawable.ic_home,
                R.drawable.ic_classrooms,
                R.drawable.ic_network,
                R.drawable.ic_post,
                R.drawable.ic_notifications
        };
        private Context context;

        public TabAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putInt(TabFragment.POSITION, position + 1);
            Fragment selectedFragment = null;
            switch (position){
                case 0:
                    //selectedFragment =  HomeFragment.newInstance("0", "Home Page");
                    selectedFragment =  HomeFragment.newInstance("0", "Home Page");
                    break;
                case 1:
                    selectedFragment =  ClassroomsFragment.newInstance("0", "Classrooms Page");
                    break;
                case 2:
                    selectedFragment =  NetworkFragment.newInstance("0", "Network Page");
                    break;
                case 3:
                    selectedFragment =  PostFragment.newInstance("0", "Post Page");
                    break;
                case 4:
                    selectedFragment =  NotificationFragment.newInstance("0", "Notification Page");
                    break;
                default:
                    return null;
            }

            selectedFragment.setArguments(bundle);

            return selectedFragment;
        }

        @Override
        public int getCount() {
            return icons.length == titles.length ? icons.length : 0;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Drawable getPageDrawable(int position) {
            return ResourcesCompat.getDrawable(context.getResources(), icons[position], null);
        }

        @NonNull
        @Override
        public String getToolbarTitle(int position) {
            return titles[position];
        }
    }
}