package towers;

import java.util.ArrayList;

public class Array {
	private ArrayList<Disc2> list=new ArrayList<Disc2>();
	public Array(int discNumber) {
		for(int x=0;x<discNumber;x++) {
			list.add(new Disc2(80-10*x,350+20*x-20*(discNumber-1),40+20*x,false,0));
		}
		list.add(new Disc2(0,0,0,false,-1));
		list.get(0).setTop(true);
	}
	public Disc2 get(int x) {
		return list.get(x);
	}
	public int size() {
		return list.size();
	}
	public ArrayList<Disc2> getList(){
		return list;
	}
}
