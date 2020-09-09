package mineSweeper;

import java.awt.Image;

public class Cell {
	private String contents;
	private boolean flagged;
	private int x,y;
	private Image img;
	private int value;
	public Cell(int X, int Y, Image i) {
		this.contents="empty";
		this.flagged=false;
		this.x=X;
		this.y=Y;
		this.img=i;
		this.value=0;
	}
	public void toggleFlag() {
		if(this.flagged==false)
			this.flagged=true;
		else
			this.flagged=false;
	}
	public void changeContents(String e) {
		this.contents=e;
	}
	public void changeImg(Image i) {
		this.img=i;
	}
	public int getx() {
		return this.x;
	}
	public int gety() {
		return this.y;
	}
	public String getContents() {
		return this.contents;
	}
	public boolean getFlag() {
		return this.flagged;
	}
	public Image getImg() {
		return this.img;
	}
	public int getValue() {
		return this.value;
	}
	public void changeValue(int v) {
		this.value=v;
	}
}
