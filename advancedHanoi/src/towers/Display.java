package towers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Display extends JPanel implements ActionListener, MouseListener, MouseMotionListener{
	
	//number of moves taken
	private int moves=0;
	
	//number of discs present
	private int discNumber=3;
	
	//calculates the number of potential moves
	private int potentialMoves=(int)Math.pow(2, discNumber)-1;
	
	//timer
	private Timer t=new Timer(0,this);
	
	//play
	private boolean play;
	
	//list
	private Array l;
	
	//table
	private DiscList table;
	
	//temporary positions
	int tempx, tempy, tempi=0, diffx=0, diffy=0;
	
	//poles
	private Rectangle p1;
	private Rectangle p2;
	private Rectangle p3;
	ArrayList<Rectangle> l2=new ArrayList<Rectangle>();
	
	//creates disc buttons
	Rectangle plus=new Rectangle(122, 15, 16, 16);
	Rectangle minus=new Rectangle(121, 33, 19, 10);
	Rectangle reset=new Rectangle(400,15,80,36);
	
	//constructor
	public Display() {
		play=true;
		t.start();
		addMouseListener(this);
		addMouseMotionListener(this);
		p1=new Rectangle(98, 350-discNumber*20, 4, 20+discNumber*20);
		p2=new Rectangle(298, 350-discNumber*20, 4, 20+discNumber*20);
		p3=new Rectangle(498, 350-discNumber*20, 4, 20+discNumber*20);
		l2.add(p1);
		l2.add(p2);
		l2.add(p3);
		l=new Array(discNumber);
		table=new DiscList(l.getList(), discNumber);
	}
	//graphics method
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(play) {
		g.setColor(Color.BLACK);
		g.drawRect(400,15,80,36);
		g.setFont(new Font("serif", Font.BOLD, 20));
		g.drawString("+", 125, 30);
		g.drawString("_", 126, 35);
		g.drawString("Reset", 415, 39);
		//ring stand
		g.setColor(new Color(100,50,25));
		g.fillRect(98, 350-discNumber*20, 4, 20+discNumber*20);
		g.fillRect(298, 350-discNumber*20, 4, 20+discNumber*20);
		g.fillRect(498, 350-discNumber*20, 4, 20+discNumber*20);
		g.fillRect(50, 370,500,4);
		
		//adds discs to an object arraylist and displays them
		for(int x=0;x<discNumber;x++) {
		g.setColor(new Color((int)Math.abs(255-(double)x*70),(int)Math.abs(3*Math.pow(x-8, 2)-64),x*30));
		g.fillRoundRect(l.get(x).getxpos(),l.get(x).getypos(),l.get(x).getSize(),20,30,30);
		g.setColor(Color.BLACK);
		g.drawRoundRect(l.get(x).getxpos(),l.get(x).getypos(),l.get(x).getSize(),20,30,30);
		}
		
		//text
		g.setColor(Color.BLACK);
		g.setFont(new Font("serif", Font.BOLD, 20));
		g.drawString("Minimum Moves: "+potentialMoves, 390, 450);
		g.setFont(new Font("serif", Font.BOLD, 30));
		g.drawString("Discs: "+discNumber, 20, 40);
		g.drawString("Moves: "+moves, 200, 40);
		}
		else {
			t.stop();
			g.setFont(new Font("serif", Font.BOLD, 50));
			g.setColor(Color.green);
			if(moves==potentialMoves)
				g.drawString("Perfect", 50,150);
			else
				g.drawString("Good job", 50, 150);
			g.setColor(Color.black);
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Click anywhere to play again", 50, 175);
		}
		if(table.getDisc(0,2)==l.get(0)) {
			play=false;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		 if (diffx!=0&&diffy!=0){	
			 	l.get(tempi).changexpos(e.getX()-diffx);
				l.get(tempi).changeypos(e.getY()-diffy);
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(plus.contains(e.getPoint())) {
			if(discNumber<8) {
				moves=0;
				l=new Array(discNumber+1);
				table=new DiscList(l.getList(),discNumber+1);
				discNumber++;
				l2.remove(0);
				l2.remove(0);
				l2.remove(0);
				l2.add(new Rectangle(98, 350-discNumber*20, 4, 20+discNumber*20));
				l2.add(new Rectangle(298, 350-discNumber*20, 4, 20+discNumber*20));
				l2.add(new Rectangle(498, 350-discNumber*20, 4, 20+discNumber*20));
				potentialMoves=(int)Math.pow(2, discNumber)-1;
			}
		}
		if(minus.contains(e.getPoint())) {
			if(discNumber>3) {
				moves=0;
				l=new Array(discNumber-1);
				table=new DiscList(l.getList(),discNumber-1);
				discNumber--;
				l2.remove(0);
				l2.remove(0);
				l2.remove(0);
				l2.add(new Rectangle(98, 350-discNumber*20, 4, 20+discNumber*20));
				l2.add(new Rectangle(298, 350-discNumber*20, 4, 20+discNumber*20));
				l2.add(new Rectangle(498, 350-discNumber*20, 4, 20+discNumber*20));
				potentialMoves=(int)Math.pow(2, discNumber)-1;
			}
		}
		if(reset.contains(e.getPoint())) {
			moves=0;
			l=new Array(discNumber);
			table=new DiscList(l.getList(),discNumber);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for(int x=0;x<l.size()-1;x++) {
			if(new Rectangle(l.get(x).getxpos(),l.get(x).getypos(),l.get(x).getSize(),20).contains(e.getPoint())&&l.get(x).getTop()) {
				tempx=l.get(x).getxpos();
				tempy=l.get(x).getypos();
				tempi=x;
				diffx=e.getX()-l.get(x).getxpos();
				diffy=e.getY()-l.get(x).getypos();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
			if(new Rectangle(l.get(tempi).getxpos(),l.get(tempi).getypos(),l.get(tempi).getSize(),20).intersects(l2.get(0))&&(topDisc(0)==discNumber||table.getDisc(topDisc(0),0).getSize()>l.get(tempi).getSize())) {
				//change graphic
				if(topDisc(0)!=discNumber)
					l.get(tempi).changeypos((int) (l2.get(0).getY()+l2.get(0).getHeight()-20*(discNumber-topDisc(0)))-20);
				else
					l.get(tempi).changeypos((int) (l2.get(0).getY()+l2.get(0).getHeight()-20*(discNumber-topDisc(0)+1)));
				l.get(tempi).changexpos((int) (l2.get(0).getCenterX()-l.get(tempi).getSize()/2));
				//set new top
				if(topDisc(l.get(tempi).getPole())!=discNumber-1) {
					table.getDisc(topDisc(l.get(tempi).getPole())+1,l.get(tempi).getPole()).setTop(true);
				}
				//remove top & assign new disc
				if(topDisc(0)!=discNumber) {
					table.getDisc(topDisc(0),0).setTop(false);
					table.set(topDisc(0)-1,0,l.get(tempi));
				}
				else {
					table.set(topDisc(0)-1,0,l.get(tempi));
				}
				//remove disc
				table.set(topDisc(l.get(tempi).getPole()),l.get(tempi).getPole(),l.get(discNumber));
				//change pole
				l.get(tempi).changePole(0);
				//inc move
				moves++;
			}
			else if(new Rectangle(l.get(tempi).getxpos(),l.get(tempi).getypos(),l.get(tempi).getSize(),20).intersects(l2.get(1))&&(topDisc(1)==discNumber||table.getDisc(topDisc(1),1).getSize()>l.get(tempi).getSize())) {
				//change graphic
				if(topDisc(1)!=discNumber)
					l.get(tempi).changeypos((int) (l2.get(1).getY()+l2.get(1).getHeight()-20*(discNumber-topDisc(1)))-20);
				else
					l.get(tempi).changeypos((int) (l2.get(1).getY()+l2.get(1).getHeight()-20*(discNumber-topDisc(1)+1)));
				l.get(tempi).changexpos((int) (l2.get(1).getCenterX()-l.get(tempi).getSize()/2));
				//set new top
				if(topDisc(l.get(tempi).getPole())!=discNumber-1) {
					table.getDisc(topDisc(l.get(tempi).getPole())+1,l.get(tempi).getPole()).setTop(true);
				}
				//remove top & assign new disc
				if(topDisc(1)!=discNumber) {
					table.getDisc(topDisc(1),1).setTop(false);
					table.set(topDisc(1)-1,1,l.get(tempi));
				}
				else {
					table.set(topDisc(1)-1,1,l.get(tempi));
				}
				//remove disc
				table.set(topDisc(l.get(tempi).getPole()),l.get(tempi).getPole(),l.get(discNumber));
				//change pole
				l.get(tempi).changePole(1);
				//inc move
				moves++;
			}
			else if(new Rectangle(l.get(tempi).getxpos(),l.get(tempi).getypos(),l.get(tempi).getSize(),20).intersects(l2.get(2))&&(topDisc(2)==discNumber||table.getDisc(topDisc(2),2).getSize()>l.get(tempi).getSize())) {
				//change graphic
				if(topDisc(2)!=discNumber)
					l.get(tempi).changeypos((int) (l2.get(2).getY()+l2.get(2).getHeight()-20*(discNumber-topDisc(2)))-20);
				else
					l.get(tempi).changeypos((int) (l2.get(2).getY()+l2.get(2).getHeight()-20*(discNumber-topDisc(2)+1)));
				l.get(tempi).changexpos((int) (l2.get(2).getCenterX()-l.get(tempi).getSize()/2));
				//set new top
				if(topDisc(l.get(tempi).getPole())!=discNumber-1) {
					table.getDisc(topDisc(l.get(tempi).getPole())+1,l.get(tempi).getPole()).setTop(true);
				}
				//remove top & assign new disc
				if(topDisc(2)!=discNumber) {
					table.getDisc(topDisc(2),2).setTop(false);
					table.set(topDisc(2)-1,2,l.get(tempi));
				}
				else {
					table.set(topDisc(2)-1,2,l.get(tempi));
				}
				//remove disc
				table.set(topDisc(l.get(tempi).getPole()),l.get(tempi).getPole(),l.get(discNumber));
				//change pole
				l.get(tempi).changePole(2);
				//inc move
				moves++;
			}
			else if(diffx!=0&&diffy!=0){
				l.get(tempi).changexpos(tempx);
				l.get(tempi).changeypos(tempy);
				}
		diffx=0;
		diffy=0;
		if(!play) {
			t.start();
			play=true;
			moves=0;
			l=new Array(discNumber);
			table=new DiscList(l.getList(),discNumber);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	public int topDisc(int pole) {
		int index=discNumber;
		for(int x=0;x<discNumber;x++) {
			if(table.getDisc(x,pole).getSize()!=0) {
				index=x;
				break;
			}
		}
		return index;
	}
}
