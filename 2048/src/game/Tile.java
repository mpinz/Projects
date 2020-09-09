package game;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

public class Tile {

	Dimension dim = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
	
	private int value;
	private int x;
	private int y;
	private Image image;
	private int imgSize;
	private int apploc;private int tempx,tempy;
	

	// constructor not given a position
	public Tile(int v, int x, int y, Image i) {
		this.value = v;
		this.x = x;
		this.y = y;
		this.image = i;
		this.imgSize=149;
		this.apploc=0;
		this.tempx=dim.width / 2 - 300 + (150 * this.x) + 1;
		this.tempy=dim.height / 2 - 350 +( 150 * this.y )+ 1;
	}

	public void changeValue(int v) {
		this.value = v;
	}

	public int getValue() {
		return this.value;
	}

	public int getx() {
		return this.x;
	}

	public int gety() {
		return this.y;
	}
	
	public int getimg() {
		return this.imgSize;
	}

	public Image getImage() {
		return this.image;
	}

	public void changex(int x2) {
		this.x = x2;
	}
	public void changeimg(int x2) {
		this.imgSize += x2;
		this.apploc=(149-this.imgSize)/2;
	}

	public void changey(int y2) {
		this.y = y2;
	}
	
	public int getapploc() {
		return this.apploc;
	}

	public int gettempx() {
		return (this.tempx);
	}

	public int gettempy() {
		return(this.tempy) ;
	}
	
	public void changetempx(int d) {
		this.tempx += d;
	}
	
	public void changetempy(int d) {
		this.tempy += d;
	}
}
