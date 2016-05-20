package com.durodola.mobile.newsapp;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String TITLES[] = {"Home", "Events", "Mail", "Shop", "Travel"};
    int ICONS[] = {R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher};

    String NAME = "Akash Bangad";
    String EMAIL = "akash.bangad@android4devs.com";
    int PROFILE = R.drawable.ic_launcher;

    RecyclerView mRecyclerView;                           // Declaring RecyclerView
    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    DrawerLayout Drawer;                                  // Declaring DrawerLayout

    ActionBarDrawerToggle mDrawerToggle;
    //test
    private ExpandableListView expListView;
    private HashMap<String, List<String>> listDataChild;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    View view_Group;
    Fragment fragment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MainFragment frag = MainFragment.newInstance();
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.mylayout2, frag);
        transaction.commit();


     /*   mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View

        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size

        mAdapter = new MyAdapter(TITLES, ICONS, NAME, EMAIL, PROFILE);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,


        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView

        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager

        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager
*/

        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }


        }; // Drawer Toggle Object Made
        setUpDrawer();
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();               // Finally we set the drawer toggle sync State


    }

    private void setUpDrawer() {
        //  mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        /*
         * mDrawerLayout.setScrimColor(getResources().getColor(
		 * android.R.color.transparent));
		 */
        // mDrawerLayout.setDrawerListener(mDrawerListener);
        expListView = (ExpandableListView) findViewById(R.id.list_slidermenu);
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader,
                listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);
        // expandable list view click listener

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // setbackground color for list that is selected in child group
                v.setSelected(true);
                if (view_Group != null) {
                    view_Group.setBackgroundColor(Color.parseColor("#ffffff"));
                }
                view_Group = v;
                view_Group.setBackgroundColor(Color.parseColor("#F21E1E"));

                switch (groupPosition) {

				/*
                 * Here add your fragment class name for each case menu (eg.
				 * Layout1, layout2 in screen) you can add n number of classes
				 * to the swithch case Also when you add the class name here,
				 * also add the corresponding name to the array list
				 */
                    // dash board
                    case 0:
                        //   fragment = new One();
                        fragment = MainFragment.newInstance();
                        break;

                    // before you file
                    case 1:
                        switch (childPosition) {
                            case 0:
                                //  fragment = new One();
                                fragment = MainFragment.newInstance();
                                break;
                            case 1:
                                //   fragment = new One();
                                break;

                            default:
                                break;
                        }
                        break;

                    // my profile
                    case 2:
                        switch (childPosition) {
                            case 0:
                                //    fragment = new One();
                                fragment = MainFragment.newInstance();
                                break;
                            case 1:
                                //  fragment = new One();
                                break;
                            case 2:
                                //  fragment = new One();
                                break;

                            default:
                                break;
                        }
                        break;

                    // income slip
                    case 3:
                        switch (childPosition) {

                            case 0:
                                //  fragment = new One();
                                break;
                            case 1:
                                //    fragment = new One();
                                break;
                            case 2:
                                //   fragment = new One();
                                break;

                            default:
                                break;
                        }
                        break;

                    // federal deduction
                    case 4:
                        switch (childPosition) {
                            case 0:
                                //   fragment = new One();
                                break;
                            case 1:
                                //   fragment = new One();
                                break;
                            case 2:
                                //  fragment = new One();
                                break;

                            default:
                                break;
                        }
                        break;

                    // provincial activity
                    case 5:
                        switch (childPosition) {
                            case 0:
                                ////    fragment = new One();
                                break;
                            case 1:
                                //   fragment = new One();
                                break;

                            default:
                                break;
                        }
                        break;

                    // expenses
                    case 6:
                        switch (childPosition) {
                            case 0:
                                ////    fragment = new One();
                                break;
                            case 1:
                                //   fragment = new One();
                                break;
                            case 2:
                                //   fragment = new One();
                                break;

                            default:
                                break;
                        }
                        break;

                    case 7:
                        //   fragment = new One();

                    default:
                        break;
                }
                //  expListView.setItemChecked(childPosition, true);

              /* *//* FragmentTransaction transaction = getFragmentManager().beginTransaction();

                //  transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                transaction.replace(R.id.mylayout2, fragment);
                transaction.addToBackStack(null);
                transaction.commit();*//*
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.mylayout2 , fragment);
                transaction.commit();*/
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, fragment).commit();
                expListView.setItemChecked(childPosition, true);
                Drawer.closeDrawer(expListView);
                return false;
            }
        });
    }

    private void prepareListData() {

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        String[] array = getResources()
                .getStringArray(R.array.nav_drawer_items);
        listDataHeader = Arrays.asList(array);

        // Adding child data
        // dash board
        List<String> dashboard = new ArrayList<String>();
        String[] dash = getResources().getStringArray(R.array.dash_board);
        dashboard = Arrays.asList(dash);

        // before you file
        List<String> l1 = new ArrayList<String>();
        String[] before = getResources()
                .getStringArray(R.array.before_you_file);
        l1 = Arrays.asList(before);

        // profile
        List<String> l2 = new ArrayList<String>();
        String[] myproe = getResources().getStringArray(R.array.my_profile);
        l2 = Arrays.asList(myproe);

        // income slip
        List<String> l3 = new ArrayList<String>();
        String[] inco = getResources().getStringArray(R.array.income_slip);
        l3 = Arrays.asList(inco);

        // federal deduction
        List<String> l4 = new ArrayList<String>();
        String[] fed = getResources().getStringArray(R.array.federal_deduction);
        l4 = Arrays.asList(fed);

        // provincial credits
        List<String> l5 = new ArrayList<String>();
        String[] provi = getResources().getStringArray(
                R.array.provincial_credit);
        l5 = Arrays.asList(provi);

        // expenses
        List<String> l6 = new ArrayList<String>();
        String[] expen = getResources().getStringArray(R.array.expenses);
        l6 = Arrays.asList(expen);

        // review
        List<String> l7 = new ArrayList<String>();
        String[] revieww = getResources().getStringArray(R.array.review);
        l7 = Arrays.asList(revieww);

        // cra profile
        List<String> l8 = new ArrayList<String>();

        // submit
        List<String> l9 = new ArrayList<String>();

        // cloud drive
        List<String> l10 = new ArrayList<String>();

        // assigning values to menu and submenu
        listDataChild.put(listDataHeader.get(0), dashboard); // Header, Child
        // data
        listDataChild.put(listDataHeader.get(1), l1);
        listDataChild.put(listDataHeader.get(2), l2);
        listDataChild.put(listDataHeader.get(3), l3);
        listDataChild.put(listDataHeader.get(4), l4);
        listDataChild.put(listDataHeader.get(5), l5);
        listDataChild.put(listDataHeader.get(6), l6);
        listDataChild.put(listDataHeader.get(7), l7);
        listDataChild.put(listDataHeader.get(8), l8);
        listDataChild.put(listDataHeader.get(9), l9);
        listDataChild.put(listDataHeader.get(10), l10);

    }


    public class ExpandableListAdapter extends BaseExpandableListAdapter {

        private Context _context;
        private List<String> _listDataHeader; // header titles
        // child data in format of header title, child title
        private HashMap<String, List<String>> _listDataChild;

        public ExpandableListAdapter(Context context,
                                     List<String> listDataHeader,
                                     HashMap<String, List<String>> listChildData) {
            this._context = context;
            this._listDataHeader = listDataHeader;
            this._listDataChild = listChildData;
        }

        @Override
        public Object getChild(int groupPosition, int childPosititon) {
            return this._listDataChild.get(
                    this._listDataHeader.get(groupPosition))
                    .get(childPosititon);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, final int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {

            final String childText = (String) getChild(groupPosition,
                    childPosition);

            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_item, null);
            }

            TextView txtListChild = (TextView) convertView
                    .findViewById(R.id.lblListItem);

            txtListChild.setText(childText);
            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return this._listDataChild.get(
                    this._listDataHeader.get(groupPosition)).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return this._listDataHeader.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return this._listDataHeader.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            String headerTitle = (String) getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_group, null);
            }
            // changing the +/- on expanded list view
            TextView txt_plusminus = (TextView) convertView
                    .findViewById(R.id.plus_txt);
            if (isExpanded) {
                txt_plusminus.setText("-");
            } else {
                txt_plusminus.setText("+");
            }

            TextView lblListHeader = (TextView) convertView
                    .findViewById(R.id.lblListHeader);
            // lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeader.setText(headerTitle);

            // nav drawer icons from resources
            // navMenuIcons =
            // getResources().obtainTypedArray(R.array.nav_drawer_icons);
            // imgListGroup.setImageDrawable(navMenuIcons.getDrawable(groupPosition));

            // adding icon to expandable list view
            ImageView imgListGroup = (ImageView) convertView
                    .findViewById(R.id.ic_txt);
            //imgListGroup.setImageResource(MainActivity.icon[groupPosition]);
            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

}
