package towers;

public class Disc2 {
	private int discposx;
	private int discposy;
	private int Size;
	private boolean top;
	private int pole;
	public Disc2(int x, int y, int size, boolean t, int p) {
		this.discposx=x;
		this.discposy=y;
		this.Size=size;
		this.top=t;
		this.pole=p;
	}
	public int getSize() {
		return this.Size;
	}
	public int getPole() {
		return this.pole;
	}
	public boolean getTop() {
		return this.top;
	}
	public void setTop(boolean b) {
		this.top=b;
	}
	public int getxpos() {
		return this.discposx;
	}
public void changexpos(int x) {
		this.discposx=x;
	}
public int getypos() {
	return this.discposy;
}
public void changeypos(int y) {
	this.discposy=y;
}
public void changePole(int p) {
	this.pole=p;
}
}
