package com.example.testingfragment.adapters;

import java.util.List;

import com.example.testingfragment.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter<String>{
	private Context mContext;
	private int RESOURCE;
	private List<String> objects;
	
	public MyAdapter(Context context, int resource, List<String> objects) {
		super(context, resource, objects);
		mContext = context;
		RESOURCE = resource;
		this.objects = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder vh = null;
		
		if(view == null) {
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(RESOURCE,parent,false);
			vh = new ViewHolder();
			vh.mTxtView = (TextView)view.findViewById(R.id.txtItem);
			view.setTag(vh);
		}else {
			vh = (ViewHolder)view.getTag();
		}
		
		vh.mTxtView.setText(objects.get(position));
		return view;
	}
	
	static class ViewHolder {
		TextView mTxtView;
	}
}
