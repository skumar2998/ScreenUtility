package com.my.package;
/**
 * 
 * About
 * -----
 * 
 * A utility class for dynamically setting the height and width of screen objects in android,
 * based on the current screen dimensions.
 * 
 * Also has some methods for grabbing screen data, such as the height and width in pixels.
 * 
 * Android devices come in all shapes and sizes, so this class has helped me make my android
 * applications more "responsive" based on the device they are running on.
 * 
 * Hopefully this will help you set up your screen objects across devices too.
 * 
 * I wouldn't recommend using this utility class for bigger devices like tablets, as the objects
 * just end up looking gigantic if you're only using a few of them on screen at a time.
 * 
 * Legal stuff
 * -----------
 * 
 * Copyright (c) Paul Hallett 2012
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>
 * 
 * @author Paul Hallett www.phalt.co.uk
 */
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class ScreenUtility {
	
	private Activity _activity;
	private int _height;
	private int _width;
	
	/**
	 * Default constructor
	 * @param activity
	 * 	The activity the objects are in.
	 */
	public ScreenUtility(Activity activity)
	{
		try
		{
			setActivity(activity);
			discoverActivityScreenSize();
		}
		catch (Exception e)
		{
			Log.e("ScreenUtility.java", e.getMessage());
		}
	}
	
    /*
     * ========================================================================
     * Public functions
     * ========================================================================
     */
	
	/**
	 * Sets a view object height and width to a supplied percentage of the device screen.
	 * @param heightPercentage
	 * 	The desired height in percentage (between 1 and 100).
	 * @param widthPercentage
	 * 	The desired width in percentage (between 1 and 100).
	 * @param viewObject
	 * 	The view object that you are setting the dimensions for.
	 */
	public void setViewObjectDimensionsAsPercentage(int heightPercentage, int widthPercentage, View viewObject)
	{
		LayoutParams layout = viewObject.getLayoutParams();
		setDynamicDimensions(layout, heightPercentage, widthPercentage);
	}
	
    /*
     * ========================================================================
     * Getters and setters for activity, screen height and screen width
     * ========================================================================
     */
	
	private void setActivity(Activity activity)
	{
		if(activity == null)
		{
			throw new ActivityNotFoundException();
		}
		else
		{
			this._activity = activity;
		}
	}
	
	private Activity getActivity()
	{
		return this._activity;
	}
	
	private void setHeight(int height)
	{
		this._height = height;
	}
	
	/**
	 * Get the screen height
	 * @return The screen height.
	 */
	public int getScreenHeight()
	{
		return this._height;
	}
	
	/**
	 * Get the screen height
	 * @return The screen height as a double.
	 */
	public Double getScreenHeightAsDouble()
	{
		return Double.valueOf(this._height);
	}
	
	private void setWidth(int width)
	{
		this._width = width;
	}
	
	/**
	 * Get the screen width
	 * @return The screen width.
	 */
	public int getScreenWidth()
	{
		return this._width;
	}
	
	/**
	 * Get the screen width
	 * @return The screen width as a double.
	 */
	public Double getScreenWidthAsDouble()
	{
		return Double.valueOf(this._width);
	}
	
    /*
     * ========================================================================
     * Private functions for use internally
     * ========================================================================
     */
	
	/**
	 * Sets the view object to it's dynamic height and width
	 * @param layout
	 * @param height
	 * @param width
	 */
	private void setDynamicDimensions(LayoutParams layout, int height, int width)
	{
		double[] dimensions = setDimensions(height, width);
		layout.height=  (int)dimensions[0];
		layout.width =  (int)dimensions[1];
	}
	
	/**
	 * Set the dimensions needed for view objects
	 * @param heightPercentage
	 * @param widthPercentage
	 * @return
	 */
	private double[] setDimensions(int heightPercentage, int widthPercentage)
	{
		double[] dimensions = new double[2];
		dimensions[0] = ((double)heightPercentage * getScreenHeight()) / 100;
		dimensions[1] = ((double)widthPercentage * getScreenWidth()) / 100;
		return dimensions;
	}
	
	/**
	 * Discover the screen height and width
	 */
	private void discoverActivityScreenSize()
	{
		DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        setHeight(displaymetrics.heightPixels);
        setWidth(displaymetrics.widthPixels);
	}

}
