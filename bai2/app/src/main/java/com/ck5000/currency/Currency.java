package com.ck5000.currency;

import android.graphics.Bitmap;

public class Currency{
	String 	name;
	Bitmap	bitmap;
	double 	rate;

	public Currency(String name, double rate){
		this.name = name;
		this.rate = rate;
	}
	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public Bitmap getBitmap(){
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap){
		this.bitmap = bitmap;
	}

	public double getRate(){
		return rate;
	}

	public void setRate(double rate){
		this.rate = rate;
	}
}
