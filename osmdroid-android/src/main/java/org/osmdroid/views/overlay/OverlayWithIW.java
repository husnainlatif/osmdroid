package org.osmdroid.views.overlay;

import android.content.Context;

import org.osmdroid.views.overlay.infowindow.BasicInfoWindow;
import org.osmdroid.views.overlay.infowindow.InfoWindow;

/**
 * Overlay able to open an InfoWindow (a bubble), displaying: a title, a snippet or description, 
 * and optionally a "sub-description". 
 * Handling tap event and showing the InfoWindow at a relevant position is let to sub-classes. 
 * 
 * @see BasicInfoWindow
 * 
 * @author M.Kergall
 */
public abstract class OverlayWithIW extends Overlay {
 
	//InfoWindow handling
	protected String mTitle, mSnippet, mSubDescription;
	protected InfoWindow mInfoWindow;
	protected Object mRelatedObject;

	/** Use {@link #OverlayWithIW()} instead */
	@Deprecated
	public OverlayWithIW(final Context ctx) {
		this();
	}

	public OverlayWithIW() {
		super();
	}


	public void setTitle(String title){
		mTitle = title;
	}
	
	public String getTitle(){
		return mTitle;
	}
	
	public void setSnippet(String snippet){
		mSnippet= snippet;
	}
	
	public String getSnippet(){
		return mSnippet;
	}

	/** set the "sub-description", an optional text to be shown in the InfoWindow, below the snippet, in a smaller text size */
	public void setSubDescription(String subDescription){
		mSubDescription = subDescription;
	}
	
	public String getSubDescription(){
		return mSubDescription;
	}

	/** Allows to link an Object (any Object) to this marker.
	 * This is particularly useful to handle custom InfoWindow. */
	public void setRelatedObject(Object relatedObject){
		mRelatedObject = relatedObject;
	}

	/** @return the related object. */
	public Object getRelatedObject(){
		return mRelatedObject;
	}

	/** By default, OverlayWithIW has no InfoWindow.
	 * Usage: setInfoWindow(new BasicInfoWindow(layoutResId, mapView));
	 * @param infoWindow the InfoWindow to be opened when tapping the overlay. 
	 * This InfoWindow MUST be able to handle an OverlayWithIW (as BasicInfoWindow does). 
	 * Set it to null to remove an existing InfoWindow. 
	 */
	public void setInfoWindow(InfoWindow infoWindow){
		mInfoWindow = infoWindow;
	}

	public InfoWindow getInfoWindow(){
		return mInfoWindow;
	}
	
	public void closeInfoWindow(){
		if (mInfoWindow != null)
			mInfoWindow.close();
	}

	public void onDestroy(){
		if (mInfoWindow != null) {
			mInfoWindow.close();
			mInfoWindow.onDetach();
			mInfoWindow=null;
			mRelatedObject=null;
		}
	}

	public boolean isInfoWindowOpen(){
		return (mInfoWindow != null) && mInfoWindow.isOpen();
	}

}
