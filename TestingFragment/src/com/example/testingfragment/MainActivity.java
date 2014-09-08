package com.example.testingfragment;

import java.util.ArrayList;
import java.util.Stack;

import com.example.testingfragment.fragments.MyListFragment;
import com.example.testingfragment.fragments.SecondFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

public class MainActivity extends ActionBarActivity {
	private static Stack<Fragment> mLstFragments;
	private ArrayList<Fragment.SavedState> mSavedState = new ArrayList<Fragment.SavedState>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mLstFragments = new Stack<Fragment>();
		
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		MyListFragment fragment = new MyListFragment();
		ft.replace(R.id.content_frame, fragment);
		mLstFragments.push(fragment);
		ft.commit();
	}
	
	public void replaceFragment(int position) {
		mLstFragments.lastElement().onPause();
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		mSavedState.add(getSupportFragmentManager().saveFragmentInstanceState(mLstFragments.lastElement()));
		
		ft.remove(mLstFragments.lastElement());
		SecondFragment fragment = SecondFragment.newInstance(position);
		mLstFragments.push(fragment);
		ft.add(R.id.content_frame,fragment );
		ft.commit();
	}
	
	@Override
	public void onBackPressed() {
		Log.e("dhara","mLstFragments : " + mLstFragments.size());
		if(mLstFragments.size() > 1) {
			mLstFragments.lastElement().onPause();
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.remove(mLstFragments.pop());
			
			if(mLstFragments.lastElement() instanceof MyListFragment) {
				 Fragment.SavedState fss = mSavedState.get(0);
			        if (fss != null) {
			        	mLstFragments.lastElement().setInitialSavedState(fss);
			        	mSavedState.remove(0);
			        }
			}
			
			ft.add(R.id.content_frame, mLstFragments.lastElement());
			ft.commit();
		}else {
			super.onBackPressed();
		}
	}
}
