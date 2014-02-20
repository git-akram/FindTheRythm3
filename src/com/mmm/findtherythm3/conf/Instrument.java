package com.mmm.findtherythm3.conf;

public class Instrument {
	
	private static int sonId;
	private static int imageId;
	
	public static int getSonId() {
		return sonId;
	}
	public static void setSonId(int sonId) {
		Instrument.sonId = sonId;
	}
	public static int getImageId() {
		return imageId;
	}
	public static void setImageId(int imageId) {
		Instrument.imageId = imageId;
	}
	
}
