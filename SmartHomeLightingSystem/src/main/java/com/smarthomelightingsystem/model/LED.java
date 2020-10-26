package com.smarthomelightingsystem.model;

//This class contains 

/**
 * The class works with animations of LED strip   
 * shown on the Palette page of web interface
 * 
 * @author Jasper van Amerongen
 * @author Albina Shynkar
 * @author Lola Solovyeva
 * @author Ilya Averchenko
 * @author Bugra Yildiz
 * @author Alexandru Matcov

 * 
 * @version 1
 *
 */
public class LED {
	/*
	 * integer value of red color of LED
	 */
	int R;
	/*
	 * integer value of green color of LED
	 */
	int G;
	/*
	 * integer value of blue color of LED
	 */
	int B;
	/*
	 * float value of brightness level of LED (0-100)
	 */
	float bright;
	public LED(){}
	public void setR(int r) {
		R = r;
	}
	public void setG(int g){
		G=g;
	}
	public void setB(int b) {
		B = b;
	}

	public int getB() {
		return B;
	}

	public int getG() {
		return G;
	}

	public int getR() {
		return R;
	}

	public void setBright(float bright) {
		this.bright = bright;
	}

	public float getBright() {
		return bright;
	}
}
