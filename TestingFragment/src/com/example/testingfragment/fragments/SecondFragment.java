package com.example.testingfragment.fragments;

import com.example.testingfragment.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SecondFragment extends Fragment{
	private View mView;
	private TextView mTxtView;
	private static SecondFragment mSecondFragment;
	
	public static SecondFragment newInstance(int position) {
		mSecondFragment = new SecondFragment();
		Bundle args = new Bundle();
		args.putInt("pos", position);
		mSecondFragment.setArguments(args);
		return mSecondFragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_second, container,false);
		mTxtView = (TextView)mView.findViewById(R.id.txtValue);
		
		if(getArguments() != null) {
			Log.e("dhara","pos : " + getArguments().getInt("pos"));
		}
		
		return mView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		if(savedInstanceState != null) {
			mTxtView.setText(String.valueOf(savedInstanceState.getInt("pos")));
		}
	}
}
