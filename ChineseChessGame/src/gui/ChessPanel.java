package gui;

import javax.swing.*;
import java.awt.*;
public class ChessPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	ImageIcon icon;  
    Image img;  
    public ChessPanel() {   
        icon=new ImageIcon("∆Â≈Ã.jpg");  
        img=icon.getImage();  
    }  
    public void paintComponent(Graphics g) {  
        super.paintComponent(g);   
        g.drawImage(img, 0, 0,660,670, this);  
    }  
}