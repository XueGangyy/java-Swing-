package Test;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FrameTest extends JFrame {
	public FrameTest(){
		
		JLabel lab=new JLabel();
		lab.setIcon(new ImageIcon("images/loginindex.jpg"));
		this.add(lab);
		
		this.setSize(500, 500);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new FrameTest();
	}

}
