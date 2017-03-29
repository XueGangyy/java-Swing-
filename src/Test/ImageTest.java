package Test;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImageTest extends JPanel{
	ImageIcon img;
	Image im=Toolkit.getDefaultToolkit().getImage("images/loginindex.jpg");
	public ImageTest(){
		img=new ImageIcon("/images/loginindex.jpg");
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(im, 0, 0, null);
	}
	
	
}
