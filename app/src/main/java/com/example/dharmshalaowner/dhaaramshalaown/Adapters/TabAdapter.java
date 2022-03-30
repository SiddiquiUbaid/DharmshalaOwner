package com.example.dharmshalaowner.dhaaramshalaown.Adapters;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.dharmshalaowner.dhaaramshalaown.Fragments.CancelledOrdersList;
import com.example.dharmshalaowner.dhaaramshalaown.Fragments.CompletedOrdersList;
import com.example.dharmshalaowner.dhaaramshalaown.Fragments.OngoingOrdersList;
import com.example.dharmshalaowner.dhaaramshalaown.Fragments.UpcomingOrdersList;

public class TabAdapter extends FragmentPagerAdapter {
    private Context myContext;
    int totalTabs;
    public TabAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }
    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                UpcomingOrdersList upcomingOrdersList = new UpcomingOrdersList();
                fragment =upcomingOrdersList;
                break;
            case 1:
                OngoingOrdersList ongoingOrdersList = new OngoingOrdersList();
                fragment = ongoingOrdersList;
                break;
            case 2:
                CompletedOrdersList completedOrdersList = new CompletedOrdersList();
                fragment = completedOrdersList;
                break;
            case 3:
                CancelledOrdersList cancelledOrdersList = new CancelledOrdersList();
                fragment = cancelledOrdersList;
                break;
            default:
                fragment =  null;
        }
        return  fragment;
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}