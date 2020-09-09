package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Display extends JPanel implements KeyListener, ActionListener {

	Image twoTile = null;
	Image fourTile = null;
	Image eightTile = null;
	Image sixteenTile = null;
	Image thirtytwoTile = null;
	Image sixtyfourTile = null;
	Image onetwentyeightTile = null;
	Image twofiftysixTile = null;
	Image fivetwelveTile = null;
	Image tentwentyfourTile = null;
	Image twentyfourtyeightTile = null;

	Dimension dim = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());

	boolean play=true;
	
	Tile[][] grid = new Tile[4][4];

	ArrayList<Tile> tiles = new ArrayList<Tile>();

	ArrayList<Image> images = new ArrayList<Image>();

	private Timer t = new Timer(6, this);

	public Display() {
		play=true;
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		requestFocus();
		if (twoTile == null) {
			twoTile = getImage("2 tile.png");
			images.add(twoTile);
		}
		if (fourTile == null) {
			fourTile = getImage("4 tile.png");
			images.add(fourTile);
		}
		if (eightTile == null) {
			eightTile = getImage("8 tile.png");
			images.add(eightTile);
		}
		if (sixteenTile == null) {
			sixteenTile = getImage("16 tile.png");
			images.add(sixteenTile);
		}
		if (thirtytwoTile == null) {
			thirtytwoTile = getImage("32 tile.png");
			images.add(thirtytwoTile);
		}
		if (sixtyfourTile == null) {
			sixtyfourTile = getImage("64 tile.png");
			images.add(sixtyfourTile);
		}
		if (onetwentyeightTile == null) {
			onetwentyeightTile = getImage("128 tile.png");
			images.add(onetwentyeightTile);
		}
		if (twofiftysixTile == null) {
			twofiftysixTile = getImage("256 tile.png");
			images.add(twofiftysixTile);
		}
		if (fivetwelveTile == null) {
			fivetwelveTile = getImage("512 tile.png");
			images.add(fivetwelveTile);
		}
		if (tentwentyfourTile == null) {
			tentwentyfourTile = getImage("1024 tile.png");
			images.add(tentwentyfourTile);
		}
		if (twentyfourtyeightTile == null) {
			twentyfourtyeightTile = getImage("2048 tile.png");
			images.add(twentyfourtyeightTile);
		}
		t.start();
		newTile();
		newTile();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(play) {
		// creates grid
		g.drawRect(dim.width / 2 - 300, dim.height / 2 - 350, 600, 600);
		for (int x = 1; x < 4; x++)
			g.drawLine(dim.width / 2 - 300 + 150 * x, dim.height / 2 - 350, dim.width / 2 - 300 + 150 * x,
					dim.height / 2 - 350 + 600);
		for (int x = 1; x < 4; x++)
			g.drawLine(dim.width / 2 - 300, dim.height / 2 - 350 + 150 * x, dim.width / 2 - 300 + 600,
					dim.height / 2 - 350 + 150 * x);

		// draws the tiles
		for (int x = 0; x < tiles.size(); x++) {
			g.drawImage(tiles.get(x).getImage(), tiles.get(x).gettempx()+tiles.get(x).getapploc() ,
					tiles.get(x).gettempy()+tiles.get(x).getapploc(), tiles.get(x).getimg()-1, tiles.get(x).getimg()-1, this);
		}
		}
		else {
			g.drawRect(50, 50, 100, 100);
		}
		g.dispose();
	}

	public void newTile() {
		int x = (int) (4 * Math.random());
		int y = (int) (4 * Math.random());
		while (grid[y][x] != null) {
			x = (int) (4 * Math.random());
			y = (int) (4 * Math.random());
		}
		if ((int) (Math.random() * 10) < 9) {
			Tile temp = new Tile(2, x, y, twoTile);
			tiles.add(temp);
			grid[y][x] = temp;
			temp.changeimg(-116);
		} else {
			Tile temp = new Tile(4, x, y, fourTile);
			tiles.add(temp);
			grid[y][x] = temp;
			temp.changeimg(-116);
		}
	}

	public void merge(Tile t1, Tile t2) {
		Image tempImage = null;
		for (int x = 0; x < images.size(); x++) {
			if (images.get(x) == t1.getImage()) {
				tempImage = images.get(x + 1);
			}
		}
		grid[t1.gety()][t1.getx()] = null;
		tiles.remove(t1);
		tiles.remove(t2);
		Tile temp = new Tile(2 * t2.getValue(), t2.getx(), t2.gety(), tempImage);
		tiles.add(temp);
		temp.changeimg(32);
		grid[t2.gety()][t2.getx()] = temp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		t.start();
		for(int x=0;x<tiles.size();x++) {
			if(tiles.get(x).getimg()!=149) {
				tiles.get(x).changeimg((149-tiles.get(x).getimg())/Math.abs(149-tiles.get(x).getimg())*4);
			}
			if(dim.width / 2 - 300 + 150 * tiles.get(x).getx() + 1!=tiles.get(x).gettempx()){
				 tiles.get(x).changetempx((dim.width / 2 - 300 + 150 * tiles.get(x).getx() + 1-(tiles.get(x).gettempx()))/Math.abs(dim.width / 2 - 300 + 150 * tiles.get(x).getx() + 1-(tiles.get(x).gettempx()))*30);
			}
			if(dim.height / 2 - 350 + 150 * tiles.get(x).gety() + 1!=tiles.get(x).gettempy()) {
				 tiles.get(x).changetempy((dim.height / 2 - 350 + 150 * tiles.get(x).gety() + 1-(tiles.get(x).gettempy()))/Math.abs(dim.height / 2 - 350 + 150 * tiles.get(x).gety() + 1-(tiles.get(x).gettempy()))*30);
			}
			if(tiles.get(tiles.size()-1).getimg()==149&&tiles.size()==16) {
				checkBoard();
			}
		}
		repaint();
	}

	public Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Display.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tempImage;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(play)
			slideRight();
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(play)
			slideLeft();
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if(play)
			slideUp();
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(play)
			slideDown();
		}

	}

	private void slideDown() {
		for(int x=0;x<tiles.size();x++) {
			tiles.get(x).changeimg(149-tiles.get(x).getimg());
			tiles.get(x).changetempx(dim.width / 2 - 300 + (150 *tiles.get(x).getx()) + 1-tiles.get(x).gettempx());
			tiles.get(x).changetempy(dim.height / 2 - 350 + (150 *tiles.get(x).gety()) + 1-tiles.get(x).gettempy());
		}
		boolean move = false;
		for (int count = 0; count < 4; count++) {
			int end=3;
			for (int o = 2; o > -1; o--) {
				if (grid[o][count] != null) {
					for (int x2 = o + 1; x2 < end+1; x2++) {
						if (grid[x2][count] != null || x2 == end) {
							if (grid[x2][count] == null) {
								grid[x2][count] = grid[o][count];
								grid[o][count] = null;
								move = true;
								for (int t = 0; t < tiles.size(); t++) {
									if (tiles.get(t).gety() == o && tiles.get(t).getx() == count) {
										tiles.get(t).changey(x2);
										break;
									}
								}
							} else if (grid[x2][count].getValue() == grid[o][count].getValue()) {
								merge(grid[o][count], grid[x2][count]);
								move = true;
								end=x2-1;
							} else if (x2 - 1 != o) {
								grid[x2-1][count] = grid[o][count];
								grid[o][count] = null;
								move = true;
								for (int t = 0; t < tiles.size(); t++) {
									if (tiles.get(t).gety() == o && tiles.get(t).getx() == count) {
										tiles.get(t).changey(x2 - 1);
										break;
									}
								}
							}
							break;
						}
					}
				}
			}

		}
		if (move)
			newTile();		
	}

	private void slideUp() {
		for(int x=0;x<tiles.size();x++) {
			tiles.get(x).changeimg(149-tiles.get(x).getimg());
			tiles.get(x).changetempx(dim.width / 2 - 300 + (150 *tiles.get(x).getx()) + 1-tiles.get(x).gettempx());
			tiles.get(x).changetempy(dim.height / 2 - 350 + (150 *tiles.get(x).gety()) + 1-tiles.get(x).gettempy());
		}
		boolean move = false;
		for (int count = 0; count < 4; count++) {
			int end =0;
			for (int o = 1; o < 4; o++) {
				if (grid[o][count] != null) {
					for (int x2 = o - 1; x2 > end-1; x2--) {
						if (grid[x2][count] != null || x2 == end) {
							if (grid[x2][count] == null) {
								grid[x2][count] = grid[o][count];
								grid[o][count] = null;
								move = true;
								for (int t = 0; t < tiles.size(); t++) {
									if (tiles.get(t).gety() == o && tiles.get(t).getx() == count) {
										tiles.get(t).changey(x2);
										break;
									}
								}
							} else if (grid[x2][count].getValue() == grid[o][count].getValue()) {
								merge(grid[o][count], grid[x2][count]);
								end=x2+1;
								move = true;
							} else if (x2 + 1 != o) {
								grid[x2+1][count] = grid[o][count];
								grid[o][count] = null;
								move = true;
								for (int t = 0; t < tiles.size(); t++) {
									if (tiles.get(t).gety() == o && tiles.get(t).getx() == count) {
										tiles.get(t).changey(x2 + 1);
										break;
									}
								}
							}
							break;
						}
					}
				}
			}

		}
		if (move)
			newTile();
		
	}

	public void slideRight() {
		for(int x=0;x<tiles.size();x++) {
			tiles.get(x).changeimg(149-tiles.get(x).getimg());
			tiles.get(x).changetempx(dim.width / 2 - 300 + (150 *tiles.get(x).getx()) + 1-tiles.get(x).gettempx());
			tiles.get(x).changetempy(dim.height / 2 - 350 + (150 *tiles.get(x).gety()) + 1-tiles.get(x).gettempy());
		}
		boolean move = false;
		for (int count = 0; count < 4; count++) {
			int end=3;
			for (int o = 2; o > -1; o--) {
				if (grid[count][o] != null) {
					for (int x2 = o + 1; x2 < end+1; x2++) {
						if (grid[count][x2] != null || x2 == end) {
							if (grid[count][x2] == null) {
								grid[count][x2] = grid[count][o];
								grid[count][o] = null;
								move = true;
								for (int t = 0; t < tiles.size(); t++) {
									if (tiles.get(t).gety() == count && tiles.get(t).getx() == o) {
										tiles.get(t).changex(x2);
										break;
									}
								}
							} else if (grid[count][x2].getValue() == grid[count][o].getValue()) {
								merge(grid[count][o], grid[count][x2]);
								move = true;
								end=x2-1;
							} else if (x2 - 1 != o) {
								grid[count][x2 - 1] = grid[count][o];
								grid[count][o] = null;
								move = true;
								for (int t = 0; t < tiles.size(); t++) {
									if (tiles.get(t).gety() == count && tiles.get(t).getx() == o) {
										tiles.get(t).changex(x2 - 1);
										break;
									}
								}
							}
							break;
						}
					}
				}
			}

		}
		if (move)
			newTile();
	}

	public void slideLeft() {
		for(int x=0;x<tiles.size();x++) {
			tiles.get(x).changeimg(149-tiles.get(x).getimg());
			tiles.get(x).changetempx(dim.width / 2 - 300 + (150 *tiles.get(x).getx()) + 1-tiles.get(x).gettempx());
			tiles.get(x).changetempy(dim.height / 2 - 350 + (150 *tiles.get(x).gety()) + 1-tiles.get(x).gettempy());
		}
		boolean move = false;
		for (int count = 0; count < 4; count++) {
			int end =0;
			for (int o = 1; o < 4; o++) {
				if (grid[count][o] != null) {
					for (int x2 = o - 1; x2 > end-1; x2--) {
						if (grid[count][x2] != null || x2 == end) {
							if (grid[count][x2] == null) {
								grid[count][x2] = grid[count][o];
								grid[count][o] = null;
								move = true;
								for (int t = 0; t < tiles.size(); t++) {
									if (tiles.get(t).gety() == count && tiles.get(t).getx() == o) {
										tiles.get(t).changex(x2);
										break;
									}
								}
							} else if (grid[count][x2].getValue() == grid[count][o].getValue()) {
								merge(grid[count][o], grid[count][x2]);
								end=x2+1;
								move = true;
							} else if (x2 + 1 != o) {
								grid[count][x2 + 1] = grid[count][o];
								grid[count][o] = null;
								move = true;
								for (int t = 0; t < tiles.size(); t++) {
									if (tiles.get(t).gety() == count && tiles.get(t).getx() == o) {
										tiles.get(t).changex(x2 + 1);
										break;
									}
								}
							}
							break;
						}
					}
				}
			}

		}
		if (move)
			newTile();

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	public void checkBoard() {
		for(int c=0;c<4;c++) {
			for(int x=1;x<3;x++) {
				if(grid[c][x+1].getValue()==grid[c][x].getValue()||grid[c][x-1].getValue()==grid[c][x].getValue())
					return;
			}
			for(int y=1;y<3;y++) {
				if(grid[y+1][c].getValue()==grid[y][c].getValue()||grid[y-1][c].getValue()==grid[y][c].getValue())
					return;
			}
		}
		play=false;
	}
}
