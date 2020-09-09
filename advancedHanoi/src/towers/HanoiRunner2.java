package towers;

import java.awt.*;

import javax.swing.*;

public class HanoiRunner2 {

	public static void main(String[] args) {
		JFrame j=new JFrame("Hanoi");
		j.setSize(602,500);
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.add(new Display());
		j.setSize(603, 500);
		j.setResizable(false);
	}

}
