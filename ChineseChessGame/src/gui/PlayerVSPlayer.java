package gui;

import ai.PVPController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class PlayerVSPlayer extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel mainPane=new JPanel();
	private JTextArea history=new JTextArea();
	private JScrollPane historyPane=new JScrollPane(history,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	private JPanel buttonPane=new JPanel();
	private ChessPanel rightPane=new ChessPanel();
	private int pos_row=0,pos_column=0;
	private int row=0,column=0;
	private int now_row=0,now_column=0;
	private int posForReg[]=new int[8];
	private boolean on=false;
	private boolean regretableRed=false,regretableBlack=false;
	private boolean redSide=true;
	private String string="";
	private String totalString="";
	private JButton [][]button=new JButton[10][9];
	private JButton giveup=new JButton("give up");
	private JButton regret=new JButton("regret");
	private ImageIcon blackCar=new ImageIcon("ºÚ³µ.png"),blackHorse=new ImageIcon("ºÚÂí.png"),blackElephant=new ImageIcon("ºÚÏó.png");
	private ImageIcon blackDefender=new ImageIcon("ºÚÊ¿.png"),blackGeneral=new ImageIcon("ºÚ½«.png"),blackCannon=new ImageIcon("ºÚÅÚ.png");
	private ImageIcon blackSoldier=new ImageIcon("ºÚ×ä.png"),redCar=new ImageIcon("ºì³µ.png"),redHorse=new ImageIcon("ºìÂí.png");
	private ImageIcon redElephant=new ImageIcon("ºìÏà.png"),redDefender=new ImageIcon("ºìÊ¿.png"),redMarshal=new ImageIcon("ºìË§.png");
	private ImageIcon redCannon=new ImageIcon("ºìÅÚ.png"),redSoldier=new ImageIcon("ºì±ø.png");
	private Icon blackIcon=null;
	private Icon redIcon=null;
	private PVPController pc=new PVPController();
    public PlayerVSPlayer()
    {
    	super("Chinese Chess");
    	try
    	{
    		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	for(column=0;column<9;column++)
    	{
    		for(row=0;row<10;row++)
    	    button[row][column]=new JButton();
    	}
    	button[0][0].setIcon(blackCar);
    	button[0][8].setIcon(blackCar);
    	button[0][1].setIcon(blackHorse);
    	button[0][7].setIcon(blackHorse);
    	button[0][2].setIcon(blackElephant);
    	button[0][6].setIcon(blackElephant);
    	button[0][3].setIcon(blackDefender);
    	button[0][5].setIcon(blackDefender);
    	button[0][4].setIcon(blackGeneral);
    	button[2][1].setIcon(blackCannon);
    	button[2][7].setIcon(blackCannon);
    	button[3][0].setIcon(blackSoldier);
    	button[3][2].setIcon(blackSoldier);
    	button[3][4].setIcon(blackSoldier);
    	button[3][6].setIcon(blackSoldier);
    	button[3][8].setIcon(blackSoldier);
    	button[9][0].setIcon(redCar);
    	button[9][8].setIcon(redCar);
    	button[9][1].setIcon(redHorse);
    	button[9][7].setIcon(redHorse);
    	button[9][2].setIcon(redElephant);
    	button[9][6].setIcon(redElephant);
    	button[9][3].setIcon(redDefender);
    	button[9][5].setIcon(redDefender);
    	button[9][4].setIcon(redMarshal);
    	button[7][1].setIcon(redCannon);
    	button[7][7].setIcon(redCannon);
    	button[6][0].setIcon(redSoldier);
    	button[6][2].setIcon(redSoldier);
    	button[6][4].setIcon(redSoldier);
    	button[6][6].setIcon(redSoldier);
    	button[6][8].setIcon(redSoldier);
    	for(column=0;column<9;column++)
     	{
     		for(row=0;row<10;row++)
     		{
     			addListener(row,column);
     		}
     	}
    	rightPane.setSize(660, 670);
	    rightPane.setLayout(null);
    	for(column=0;column<9;column++)
    	{
    		for(row=0;row<10;row++)
    		{
    		    rightPane.add(button[row][column]);
    	        button[row][column].setBounds(column*66+30,row*66+1,66,66);
    	        button[row][column].setContentAreaFilled(false);
    		}
    	}
    	JSplitPane jsp=new JSplitPane();
	    jsp.setOneTouchExpandable(true);
	    jsp.setContinuousLayout(true);
	    jsp.setPreferredSize(new Dimension(1000,700));
	    jsp.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
	    jsp.setDividerSize(5);
	    jsp.setDividerLocation(315);
	    JSplitPane line=new JSplitPane();
	    line.setOneTouchExpandable(true);
	    line.setContinuousLayout(true);
	    line.setOrientation(JSplitPane.VERTICAL_SPLIT);
	    line.setDividerSize(5);
	    line.setDividerLocation(615);
    	giveup.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent e)
	        {
	           dispose();	        
	        }
    	});
    	regret.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent e)
	        {
    			if(regretableRed&&regretableBlack)
    			{

                    int[] pos=new int[8];
    				if(redSide)
    				{
    					button[posForReg[4]][posForReg[5]].setIcon(button[posForReg[6]][posForReg[7]].getIcon());
                        button[posForReg[6]][posForReg[7]].setIcon(redIcon);
                        button[posForReg[0]][posForReg[1]].setIcon(button[posForReg[2]][posForReg[3]].getIcon());
                        button[posForReg[2]][posForReg[3]].setIcon(blackIcon);
                        pos[0]=9-posForReg[4];pos[1]=posForReg[5];pos[2]=9-posForReg[6];pos[3]=posForReg[7];
                        pos[4]=9-posForReg[0];pos[5]=posForReg[1];pos[6]=9-posForReg[2];pos[7]=posForReg[3];
    				}
    				else
    				{
                        button[posForReg[0]][posForReg[1]].setIcon(button[posForReg[2]][posForReg[3]].getIcon());
                        button[posForReg[2]][posForReg[3]].setIcon(blackIcon);
                        button[posForReg[4]][posForReg[5]].setIcon(button[posForReg[6]][posForReg[7]].getIcon());
                        button[posForReg[6]][posForReg[7]].setIcon(redIcon);
                        pos[0]=9-posForReg[0];pos[1]=posForReg[1];pos[2]=9-posForReg[2];pos[3]=posForReg[3];
                        pos[4]=9-posForReg[4];pos[5]=posForReg[5];pos[6]=9-posForReg[6];pos[7]=posForReg[7];
    				}
                    pc.Regret(pos);
                    totalString+=" regret once"+"\n";
	                history.setText(totalString);
	                regretableRed=false;
	                regretableBlack=false;
    			}
	        }
    	});
    	buttonPane.add(regret);
    	buttonPane.add(giveup);
    	line.setTopComponent(historyPane);
    	line.setBottomComponent(buttonPane);
    	jsp.setLeftComponent(line);
    	jsp.setRightComponent(rightPane);
    	mainPane.add(jsp);
    	this.getContentPane().add(mainPane);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setSize(1000,730);
    	this.setVisible(true);
    }
    public void addListener(int row,int column)
    {
    	button[row][column].addActionListener(new ActionListener()
        {
			@Override
	        public void actionPerformed(ActionEvent e)
	        {
                if(!on)
                {
                	if(button[row][column].getIcon()!=null)
                	{
                	    pos_row=row;pos_column=column;
                	    on=true;
                	}
                }
                else
                {
		            on=false;
		            pc.set(9-pos_row,pos_column,9-row,column);
	                pc.GoNextStep();
		            if(pc.Valid())
		             {
		            	if(redSide)
		            	{
			            	 posForReg[0]=pos_row;
			            	 posForReg[1]=pos_column;
		            	     posForReg[2]=now_row=row;
		            	     posForReg[3]=now_column=column;
		            	     regretableRed=true;
		            	     blackIcon=button[row][column].getIcon();
		                     button[row][column].setIcon(button[pos_row][pos_column].getIcon());
		                     button[pos_row][pos_column].setIcon(null);
		                     string=" red side ("+(pos_column+1)+","+(10-pos_row)+")-->"+"("+(now_column+1)+","+(10-now_row)+")"+"\n";
		                     if(blackIcon!=null&&blackIcon.equals(blackGeneral))
		                     {
		                	    JOptionPane.showMessageDialog(null,"The red side win");
		                	    dispose();
		                     }
		                     redSide=false;
		            	}
		            	else
		            	{
		            		 posForReg[4]=pos_row;
			            	 posForReg[5]=pos_column;
		            	     posForReg[6]=now_row=row;
		            	     posForReg[7]=now_column=column;
		            	     regretableBlack=true;
		            	     redIcon=button[row][column].getIcon();
		                     button[row][column].setIcon(button[pos_row][pos_column].getIcon());
		                     button[pos_row][pos_column].setIcon(null);
		                     string=" black side ("+(pos_column+1)+","+(10-pos_row)+")-->"+"("+(now_column+1)+","+(10-now_row)+")"+"\n";
		                     if(redIcon!=null&&redIcon.equals(redMarshal))
		                     {
		                	    JOptionPane.showMessageDialog(null,"The black side win");
		                	    dispose();
		                     }  
		                     redSide=true;
		                 }
	    				totalString+=string;
		                history.setText(totalString);
		             }		             
                }
	        }
          });
    }
}