package com.mmm.findtherythm3.conf;

public class Instrument {
	
	
	private Instrument(){
		
	}

 
	/** Instance unique pré-initialisée */
	private static Instrument INSTANCE = new Instrument();
 
	/** Point d'accès pour l'instance unique du singleton */
	public static Instrument getInstance()
	{	return INSTANCE;
	}
	
	private int sonId;
	private int imageId;
	

	public int getSonId() {
		return sonId;
	}
	public void setSonId(int sonId) {
		this.sonId = sonId;
	}
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	
}
