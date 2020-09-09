package mineSweeper;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame j =new JFrame("Minesweeper");
		j.add(new Gameplay());
		j.setExtendedState(JFrame.MAXIMIZED_BOTH);
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
