package towers;

import java.util.ArrayList;

public class DiscList {
	private Disc2[][] table;
	public DiscList(ArrayList<Disc2> l, int discNumber) {
		table=new Disc2[discNumber][3];
		for(int x=0;x<l.size()-1;x++) {
			for(int j=0;j<3;j++) {
				if(j==0) {
					table[x][j]=l.get(x);
				}
				else {
					table[x][j]=l.get(discNumber);
				}
			}
		}
	}
	public Disc2 getDisc(int r,int c) {
		return(table[r][c]);
	}
	public void set(int r,int c, Disc2 d) {
		table[r][c]=d;
	}
}
