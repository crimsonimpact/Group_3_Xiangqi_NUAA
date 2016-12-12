package gui;

import javax.swing.*;
import java.awt.*;
public class Background extends JPanel{
	private static final long serialVersionUID = 1L;
	ImageIcon icon;  
    Image img;  
    public Background() {   
        icon=new ImageIcon("background.jpg");  
        img=icon.getImage();  
    }  
    public void paintComponent(Graphics g) {  
        super.paintComponent(g);   
        g.drawImage(img, 0, 0,422,223, this);  
    }  
}
