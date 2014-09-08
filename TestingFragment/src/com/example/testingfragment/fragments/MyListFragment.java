package com.example.testingfragment.fragments;

import java.util.List;

import com.example.testingfragment.MainActivity;
import com.example.testingfragment.R;
import com.example.testingfragment.adapters.MyAdapter;
import com.example.testingfragment.staticdata.StaticManager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MyListFragment extends Fragment{
	private View mView;
	private ListView mListView;
	private MyAdapter mAdapter;
	private List<String> lstStrings;
	private int mCurrentX;
	private int mCurrentY;
	private int mFirstVisibleItem;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_list, container,false);
		mListView = (ListView)mView.findViewById(android.R.id.list);
		setRetainInstance(true);
		
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				((MainActivity)getActivity()).replaceFragment(position);				
			}
		});
		
		mListView.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView arg0, int arg1) {
				
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
		            int visibleItemCount, int totalItemCount) {
				mCurrentX = view.getScrollX();
		        mCurrentY = view.getScrollY();
		        Log.e("dhara","firstVisibleItem : " + firstVisibleItem);
		        mFirstVisibleItem = firstVisibleItem;
			}
		});
		
		return mView;
	}
	
	@Override
	public void onActivityCreated(final Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		loadData();
		
		if(savedInstanceState != null) {
			Log.e("dhara","savedInstanceState : " + savedInstanceState.getString("topPosition"));
			Log.e("dhara","savedInstanceState index : " + savedInstanceState.getString("index") + 
					Integer.parseInt(savedInstanceState.getString("index")));
			
			getActivity().runOnUiThread(new Runnable() {
				@Override
				public void run() {
					//mListView.setSelectionFromTop(Integer.parseInt(savedInstanceState.getString("index")), 
					//		Integer.parseInt(savedInstanceState.getString("topPosition")));
					mListView.setSelection(Integer.parseInt(savedInstanceState.getString("index")));
					
				}
			});
			//mListView.onRestoreInstanceState(savedInstanceState.getParcelable("state"));
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		//loadData();
	}
	
	private void loadData() {
		lstStrings = StaticManager.getStrings();
		mAdapter = new MyAdapter(getActivity(), R.layout.individual_list_item, lstStrings);
		mListView.setAdapter(mAdapter);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		// save index and top position
		Log.e("dhara","top : " + mListView.getTop());
		
		int index = mListView.getFirstVisiblePosition();
		View v = mListView.getChildAt(0);
		int top = (v == null) ? 0 : v.getTop();
		
		/*if (top < 0 && mListView.getChildAt(1) != null) {
            index++;
            v = mListView.getChildAt(1);
            top = v.getTop();
        }*/
		
		Log.e("dhara","String.valueOf(mFirstVisibleItem) : " + String.valueOf(mFirstVisibleItem));
		Log.e("dhara","String.valueOf(top) : " + String.valueOf(top));
		outState.putString("topPosition", String.valueOf(top));
		outState.putString("index", String.valueOf(mFirstVisibleItem));
		outState.putParcelable("state", mListView.onSaveInstanceState());
	}
}
