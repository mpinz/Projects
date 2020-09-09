package game;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.Scanner;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
			JFrame j = new JFrame("2048");
			j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
			j.add(new Display());
			j.setExtendedState(JFrame.MAXIMIZED_BOTH);
			j.setVisible(true);
			j.setVisible(true);
	}
}

