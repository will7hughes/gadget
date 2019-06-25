package com.whughes.gadget.fragment;

public class CompanyListFragment extends SwipeRefreshListFragment {

//    private static final String LOG_TAG = "COMPANY_LIST_FRAGMENT"
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");
//
//                initiateRefresh();
//            }
//        });
//    }
//
//    private void initiateRefresh() {
//        Log.i(LOG_TAG, "initiateRefresh");
//
//    }
//
//    private void onRefreshComplete(List<String> result) {
//        Log.i(LOG_TAG, "onRefreshComplete");
//
//        // Remove all items from the ListAdapter, and then replace them with the new items
//        ArrayAdapter<String> adapter = (ArrayAdapter<String>) getListAdapter();
//        adapter.clear();
//        for (String cheese : result) {
//            adapter.add(cheese);
//        }
//
//        // Stop the refreshing indicator
//        setRefreshing(false);
//    }
//    // END_INCLUDE (refresh_complete)

}
