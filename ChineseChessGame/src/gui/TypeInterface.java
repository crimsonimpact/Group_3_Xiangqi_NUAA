package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class TypeInterface extends JFrame{
	private static final long serialVersionUID = 1L;
	private Background mainPane=new Background();
	private JButton pvp=new JButton("PVP"),pve=new JButton("PVE");
	public TypeInterface()
	{
		super("Welcome to Chinese Chess");
    	try
    	{
    		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
		mainPane.add(pvp);
		mainPane.add(pve);
		pvp.setToolTipText("Enter the player VS player game");
		pve.setToolTipText("Enter the player VS enviroment game");
		pvp.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e)
	        {
				new PlayerVSPlayer();
	        }
        });
		pve.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e)
	        {
				new PlayerVSEnviroment();
	        }
        });
		Toolkit kit=Toolkit.getDefaultToolkit();
	    Dimension screenSize=kit.getScreenSize();
	    int width=screenSize.width;
	    int height=screenSize.height;
    	this.setSize(430,240);
	    int x=(width-430)/2, y=(height-240)/2;
	    this.setLocation(x,y);
		this.getContentPane().add(mainPane);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setVisible(true);
	}
	public static void main(String[] args)
	{
	    new TypeInterface();
	}
}
