package com.example.testingfragment.fragments;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.testingfragment.R;

public class SecondFragment extends Fragment{
	private View mView;
	private TextView mTxtView;
	private ImageView mImgView;
	private com.example.testingfragment.fragments.views.ImageView mImgCustom;
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
		mImgView = (ImageView)mView.findViewById(R.id.imgView);
		mImgCustom = (com.example.testingfragment.fragments.views.ImageView)mView.findViewById(R.id.imgViewCustom);
		mImgCustom.setMinimumWidth(300);
		
		scaleImage();
		
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
	
	private void scaleImage()
	{
	    // Get the ImageView and its bitmap
	    Drawable drawing = mImgView.getDrawable();
	    if (drawing == null) {
	        return; // Checking for null & return, as suggested in comments
	    }
	    Bitmap bitmap = ((BitmapDrawable)drawing).getBitmap();

	    // Get current dimensions AND the desired bounding box
	    int width = bitmap.getWidth();
	    int height = bitmap.getHeight();
	    DisplayMetrics metrics = new DisplayMetrics();
	    getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

	    int windowWidth = metrics.widthPixels;
	    int bounding = dpToPx(windowWidth-10);
	    Log.i("Test", "original width = " + Integer.toString(width));
	    Log.i("Test", "original height = " + Integer.toString(height));
	    Log.i("Test", "bounding = " + Integer.toString(bounding));

	    // Determine how much to scale: the dimension requiring less scaling is
	    // closer to the its side. This way the image always stays inside your
	    // bounding box AND either x/y axis touches it.  
	    float xScale = ((float) bounding) / width;
	    float yScale = ((float) bounding) / height;
	    float scale = (xScale <= yScale) ? xScale : yScale;
	    Log.i("Test", "xScale = " + Float.toString(xScale));
	    Log.i("Test", "yScale = " + Float.toString(yScale));
	    Log.i("Test", "scale = " + Float.toString(scale));

	    // Create a matrix for the scaling and add the scaling data
	    Matrix matrix = new Matrix();
	    matrix.postScale(scale, scale);

	    // Create a new bitmap and convert it to a format understood by the ImageView 
	    Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
	    width = scaledBitmap.getWidth(); // re-use
	    height = scaledBitmap.getHeight(); // re-use
	    BitmapDrawable result = new BitmapDrawable(scaledBitmap);
	    Log.i("Test", "scaled width = " + Integer.toString(width));
	    Log.i("Test", "scaled height = " + Integer.toString(height));

	    // Apply the scaled bitmap
	    mImgView.setImageDrawable(result);

	    // Now change ImageView's dimensions to match the scaled image
	    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mImgView.getLayoutParams(); 
	    params.width = width;
	    params.height = height;
	    mImgView.setLayoutParams(params);

	    Log.i("Test", "done");
	}

	private int dpToPx(int dp)
	{
	    float density = getResources().getDisplayMetrics().density;
	    return Math.round((float)dp * density);
	}
}
