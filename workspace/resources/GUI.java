package resources;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;


public class GUI extends JFrame implements ActionListener, MouseListener, MouseMotionListener{

	Solitaire game;
   public GUI(Solitaire game){
	   this.game= game;
        //Create and set up the window.
       setTitle("Solitaire");
       setSize(1010,600);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	   
       
       // this supplies the background
       try {
		System.out.println(getClass().toString());
		Image blackImg = ImageIO.read(getClass().getResource("background.jpg"));
		setContentPane(new ImagePanel(blackImg));

       }catch(IOException e) {
    	   e.printStackTrace();
       }
	   getContentPane().setLayout(new GridLayout(2,1));
	   JPanel panel = new JPanel();
	   panel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.RED, Color.RED));
	   panel.setSize(new Dimension(1000,400));
	   panel.add(new JLabel("Solitaire"));
	   panel.setOpaque(false);
	   add(panel);
	   JPanel panel2 = new JPanel();
	   panel2.setBorder(new BevelBorder(BevelBorder.RAISED, Color.YELLOW, Color.YELLOW));
	   panel2.setSize(new Dimension(1000,100));
	   
	   panel2.setOpaque(false);
	   add(panel2);
	   

	   
       
       /*******
        * This is just a test to make sure images are being read correctly on your machine. Please replace
        * once you have confirmed that the card shows up properly. The code below should allow you to play the solitare
        * game once it's fully created.
        */
		 Card card = new Card(2, Card.Suit.Diamonds);
		 card.setPreferredSize(new Dimension(100,100));
       System.out.println(card);
       panel2.add(card);
    	this.setVisible(true);
    }


	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
