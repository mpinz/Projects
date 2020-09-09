package mineSweeper;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {

	Dimension dim = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());

	// game settings
	
	int gridxSize = 9;
	int gridySize = 9;
	int mines = 10;
	int cellSize = dim.width / (int)(gridySize*2.5);

	int mineCount;

	boolean scan;
	String difficulty = "Easy";
	boolean flag;
	boolean play;
	String message = null;
	Timer t = new Timer(0, this);
	ArrayList<Cell> cellList = new ArrayList<Cell>();
	Image cell = null;
	Image cellOver = null;
	Image cellDown = null;
	Image emptyCell = null;
	Image explodedMineCell = null;
	Image flaggedCell = null;
	Image flaggedWrongCell = null;
	Image revealedMineCell = null;
	Image newGame = null;
	Image newGameOver = null;
	Image newGameDown = null;
	int seconds;
	int s;
	Cell temp;
	long time;

	public Gameplay() {
		setBackground(Color.white);
		addMouseMotionListener(this);
		addMouseListener(this);
		addKeyListener(this);
		setFocusTraversalKeysEnabled(true);
		setFocusable(true);
		requestFocus();
		if (cell == null) {
			cell = getImage("Cell.png");
		}
		if (cellOver == null) {
			cellOver = getImage("CellOver.png");
		}
		if (cellDown == null) {
			cellDown = getImage("CellDown.png");
		}
		if (emptyCell == null) {
			emptyCell = getImage("EmptyCell.png");
		}
		if (explodedMineCell == null) {
			explodedMineCell = getImage("ExplodedMineCell.png");
		}
		if (flaggedCell == null) {
			flaggedCell = getImage("FlaggedCell.png");
		}
		if (flaggedWrongCell == null) {
			flaggedWrongCell = getImage("FlaggedWrongCell.png");
		}
		if (revealedMineCell == null) {
			revealedMineCell = getImage("RevealedMineCell.png");
		}
		if (newGame == null) {
			newGame = getImage("NewGame.png");
		}
		if (newGameOver == null) {
			newGameOver = getImage("NewGameOver.png");
		}
		if (newGameDown == null) {
			newGameDown = getImage("NewGameDown.png");
		}

		newGame();

	}

	public void newGame() {
		seconds = 0;
		play = true;
		flag = false;
		mineCount = 0;
		message = "You win!";
		time = System.currentTimeMillis();
		cellList.clear();

		// sets the grid
		for (int col = 0; col < gridySize; col++)
			for (int row = 0; row < gridxSize; row++) {
				cellList.add(new Cell(dim.width / 2 + cellSize * row - gridxSize * cellSize / 2,
						dim.height / 2 + cellSize * col - gridySize * cellSize / 2, cell));
			}

		// sets the mines
		int count = 0;
		do {
			int r = (int) (Math.random() * cellList.size());
			if (cellList.get(r).getContents() != "mine") {
				cellList.get(r).changeContents("mine");
				count++;
			}
		} while (count < mines);

		// sets number values
		for (int x = 0; x < cellList.size(); x++) {
			for (Cell c : getSurrounding(x)) {
				if (c.getContents().equals("mine")) {
					cellList.get(x).changeValue(cellList.get(x).getValue() + 1);
				}
			}

		}
		repaint();
		t.start();

	}

	private Image getImage(String path) {
		Image i = null;
		try {
			URL imageURL = Gameplay.class.getResource(path);
			i = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			System.out.println("no");
		}
		return i;
	}

	public void paintComponent(Graphics g) {
		//if (play) {
			Font f = new Font("serif", cellSize, cellSize);
			Font s = new Font("serif", dim.width / 40, dim.width / 40);
			super.paintComponent(g);
			g.setFont(s);
			// difficulty
			switch (difficulty) {
			case "Easy":
				g.setColor(new Color(75, 175, 54));
				break;
			case "Medium":
				g.setColor(Color.ORANGE);
				break;
			case "Hard":
				g.setColor(new Color(165, 21, 21));
				break;
			case "Custom":
				g.setColor(Color.magenta);
				break;
			}
			g.drawString("" + (difficulty), cellList.get(0).getx(),
					cellList.get(gridySize * gridxSize - 1).gety() + cellSize + dim.width / 40);

			g.setColor(Color.black);
			int num = mines - mineCount;
			if (mineCount >= mines)
				num = 0;
			// instructions
			g.drawString(
					"Hold shift to toggle flag - Press N to start a new game - Press 1, 2, or 3 to change difficulty",
					dim.width / 25, dim.width / 25);
			g.setFont(f);
			// mines
			g.drawString("" + (num), cellList.get(0).getx(), cellList.get(0).gety() - cellSize / 2);
			// timer
			g.drawString("" + ((System.currentTimeMillis() - time) / 1000), cellList.get(gridxSize - 1).getx(),
					cellList.get(0).gety() - cellSize / 2);

			for (Cell c : cellList) {
				g.drawImage(c.getImg(), c.getx(), c.gety(), cellSize, cellSize, this);
				if (c.getImg() == emptyCell && c.getValue() != 0) {
					switch (c.getValue()) {
					case 1:
						g.setColor(Color.blue);
						break;
					case 2:
						g.setColor(new Color(75, 175, 54));
						break;
					case 3:
						g.setColor(Color.red);
						break;
					case 4:
						g.setColor(new Color(53, 14, 210));
						break;
					case 5:
						g.setColor(new Color(165, 21, 21));
						break;
					case 6:
						g.setColor(new Color(58, 175, 175));
						break;
					case 7:
						g.setColor(Color.gray);
						break;
					case 8:
						g.setColor(Color.black);
						break;
					}
					g.drawString("" + c.getValue(), c.getx() + cellSize / 4, c.gety() + cellSize * 27 / 32);
				}
				if (flag == true && (c.getImg() == cell || c.getImg() == cellOver)) {
					g.setColor(Color.black);
					g.drawString(".", c.getx() + cellSize / 3, c.gety() + cellSize * 19 / 32);
				}
			}
		 if(!play) {
			 Graphics2D g2=(Graphics2D)g;
			 g2.setStroke(new BasicStroke(5));
			 g2.setColor(Color.black);
			 g2.drawRect(dim.width / 2 - cellSize *5/2, dim.height / 2-cellSize, cellSize*5,cellSize*4/3);
			 g2.setColor(Color.WHITE);
			 g2.fillRect(dim.width / 2 - cellSize *5/2, dim.height / 2-cellSize, cellSize*5,cellSize*4/3);
			 switch(message) {
			 case "You win!":g.setColor(Color.green);break;
			 default:g.setColor(Color.red);break;
			 }
			g2.drawString(message, dim.width / 2 - cellSize * 2, dim.height / 2);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (temp != null)
			if (!new Rectangle(temp.getx(), temp.gety(), cellSize, cellSize).contains(e.getPoint())) {
				for (Cell c : cellList) {
					if (temp.getx() == c.getx() && temp.gety() == c.gety()) {
						c.changeImg(cell);
					}
				}
			} else {
				for (Cell c : cellList) {
					if (temp.getx() == c.getx() && temp.gety() == c.gety()) {
						c.changeImg(cellDown);
					}
				}
			}
	}

	@Override
	public void mouseMoved(MouseEvent e) {

		for (int x = 0; x < cellList.size(); x++) {
			Cell c = cellList.get(x);
			if (new Rectangle(c.getx(), c.gety(), cellSize, cellSize).contains(e.getPoint()) && play
					&& (c.getImg() == cell || c.getImg() == cellOver)&&scan) {
				for (Cell t : cellList) {
					if (t.getImg() == cellOver)
						t.changeImg(cell);
				}
				for(Cell q:getSurrounding(x)) {
					if(q.getImg()!=emptyCell)
						q.changeImg(cellOver);
					c.changeImg(cellOver);
				}
				
				break;
			}
			else if (new Rectangle(c.getx(), c.gety(), cellSize, cellSize).contains(e.getPoint()) && play
					&& (c.getImg() == cell || c.getImg() == cellOver)) {
				for (Cell t : cellList) {
					if (t.getImg() == cellOver)
						t.changeImg(cell);
				}
				c.changeImg(cellOver);
				break;
			}
			else {
				for (Cell t : cellList) {
					if (t.getImg() == cellOver)
						t.changeImg(cell);
				}
			}
		}

	}

	public ArrayList<Cell> getSurrounding(int x) {
		ArrayList<Cell> list = new ArrayList<Cell>();
//		list.add(cellList.get(x));

		// west
		if (x % gridxSize != 0) {
			list.add(cellList.get(x - 1));
		}
		// east
		if (x % gridxSize != gridxSize - 1) {
			list.add(cellList.get(x + 1));
		}
		// north
		if (!(x - gridxSize < 0)) {
			list.add(cellList.get(x - gridxSize));
		}
		// south
		if (!(x + gridxSize >= cellList.size())) {
			list.add(cellList.get(x + gridxSize));
		}
		// nw
		if (x % gridxSize != 0 && !(x - gridxSize < 0)) {
			list.add(cellList.get(x - gridxSize - 1));
		}
		// ne
		if (x % gridxSize != gridxSize - 1 && !(x - gridxSize < 0)) {
			list.add(cellList.get(x - gridxSize + 1));
		}
		// sw
		if (x % gridxSize != 0 && !(x + gridxSize >= cellList.size())) {
			list.add(cellList.get(x + gridxSize - 1));
		}
		// se
		if (x % gridxSize != gridxSize - 1 && !(x + gridxSize >= cellList.size())) {
			list.add(cellList.get(x + gridxSize + 1));
		}

		return list;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (Cell c : cellList) {
			if (new Rectangle(c.getx(), c.gety(), cellSize, cellSize).contains(e.getPoint()) && play
					&& (c.getImg() == cellOver || (c.getImg() == flaggedCell && flag))) {
				c.changeImg(cellDown);
				repaint();
				temp = c;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (temp != null) {
			int gameOver = 0;
			if (new Rectangle(temp.getx(), temp.gety(), cellSize, cellSize).contains(e.getPoint())) {
				for (int x = 0; x < cellList.size(); x++) {
					Cell c = cellList.get(x);
					if (temp.getx() == c.getx() && temp.gety() == c.gety() && !flag) {
						switch (c.getContents()) {
						case "empty":
							c.changeImg(emptyCell);
							if (c.getValue() == 0)
								fill(c);
							break;
						case "mine":
							play = false;
							t.stop();
							for (Cell f : cellList) {
								if (f.getContents() == "empty" && f.getFlag()) {
									f.changeImg(flaggedWrongCell);
								} else if (f.getContents() == "mine") {
									f.changeImg(revealedMineCell);
								}
							}
							c.changeImg(explodedMineCell);
							message = "You lose!";
							break;
						}
					} else if (temp.getx() == c.getx() && temp.gety() == c.gety() && c.getFlag() == true && flag) {
						c.changeImg(cellOver);
						c.toggleFlag();
						mineCount--;
					} else if (temp.getx() == c.getx() && temp.gety() == c.gety()) {
						c.changeImg(flaggedCell);
						c.toggleFlag();
						mineCount++;
					}
					if (cellList.get(x).getImg() != emptyCell) {
						gameOver++;
					}
					repaint();

				}
				if (gameOver == mines) {
					play = false;
					t.stop();
				}
				temp = null;
			} else {
				temp = null;
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SHIFT && temp == null) {
			flag = true;
		
//		} else if (e.getKeyCode() == KeyEvent.VK_Z&&!scan) {
//			scan = true;
//			for (int x = 0; x < cellList.size(); x++) {
//				if(cellList.get(x).getImg()==cellOver){
//					for(Cell q:getSurrounding(x)) {
//						q.changeImg(cellOver);
//						
//					}
//					break;
//				}
//			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			flag = false;
		} else if (e.getKeyCode() == KeyEvent.VK_Z) {
			scan = false;
			int y=0;
			for (int x = 0; x < cellList.size(); x++) {
				if(cellList.get(x).getImg()==cellOver){
					y++;
					if(y==5) {
						for(Cell q:getSurrounding(x)) {
							q.changeImg(cell);
						}
					}
				}
			}
		} else if (e.getKeyCode() == KeyEvent.VK_N) {
			t.stop();
			play = false;
			newGame();
		} else if (e.getKeyCode() == KeyEvent.VK_1) {
			t.stop();
			play = false;
			mines = 10;
			gridySize = 9;
			gridxSize = gridySize;
			cellSize = dim.width / (int)(gridySize*2.5);
			difficulty = "Easy";
			newGame();
		} else if (e.getKeyCode() == KeyEvent.VK_2) {
			t.stop();
			play = false;
			mines = 40;
			gridySize = 16;
			gridxSize = gridySize;
			cellSize = dim.width / (int)(gridySize*2.5);
			difficulty = "Medium";
			newGame();
		} else if (e.getKeyCode() == KeyEvent.VK_3) {
			t.stop();
			play = false;
			mines = 99;
			gridySize = 16;
			gridxSize = 30;
			cellSize = dim.width / (int)(gridySize*2.5);
			difficulty = "Hard";
			newGame();
		}else if (e.getKeyCode() == KeyEvent.VK_4) {
			t.stop();
			play = false;
			mines = 3;
			gridySize = 80;
			gridxSize = 60;
			cellSize = dim.width / (int)(gridySize*2.5);
			difficulty = "Custom";
			newGame();
		}
	}

	public void fill(Cell c) {
		ArrayList<Cell> list = new ArrayList<Cell>();
		for (int x = 0; x < cellList.size(); x++) {
			if (c == cellList.get(x)) {
				for (Cell t : getSurrounding(x)) {
					if (t.getValue() == 0 && t.getImg() != emptyCell)
						list.add(t);
					t.changeImg(emptyCell);
				}
				break;
			}
		}
		if (list.size() > 0) {
			for (Cell t : list) {
				fill(t);
			}
		}
	}
	

}
