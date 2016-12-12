package gui;
import ai.ArtificialIntelligence;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class PlayerVSEnviroment extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel mainPane=new JPanel();
	private JTextArea history=new JTextArea();
	private JScrollPane historyPane=new JScrollPane(history,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	private JPanel buttonPane=new JPanel();
	private ChessPanel rightPane=new ChessPanel();
	private int pos_row=0,pos_column=0;
	private int row=9,column=8;
	private int now_row=0,now_column=0;
	private int answer[]=new int[4];
	private boolean on=false;
	private boolean regretable=false;
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
	private ArtificialIntelligence AI=new ArtificialIntelligence();
    public PlayerVSEnviroment()
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
    			if(regretable)
    			{
                    button[9-answer[0]][answer[1]].setIcon(button[9-answer[2]][answer[3]].getIcon());
                    button[9-answer[2]][answer[3]].setIcon(redIcon);
                    button[pos_row][pos_column].setIcon(button[now_row][now_column].getIcon());
                    button[now_row][now_column].setIcon(blackIcon);
                    int[] pos=new int[8];
                    pos[0]=answer[0];pos[1]=answer[1];pos[2]=answer[2];pos[3]=answer[3];
                    pos[4]=9-pos_row;pos[5]=pos_column;pos[6]=9-now_row;pos[7]=now_column;
                    AI.Regret(pos);
                    totalString+=" regret once"+"\n";
	                history.setText(totalString);
                    regretable=false;
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
		            AI.set(9-pos_row,pos_column,9-row,column);
	                AI.GoNextStep();
		            if(AI.Valid())
		             {
		            	 now_row=row;
		            	 now_column=column;
		            	 regretable=true;
		            	 blackIcon=button[row][column].getIcon();
		                 button[row][column].setIcon(button[pos_row][pos_column].getIcon());
		                 button[pos_row][pos_column].setIcon(null);
		                 if(blackIcon!=null&&blackIcon.equals(blackGeneral))
		                 {
		                	JOptionPane.showMessageDialog(null,"The red side win");
		                	dispose();
		                 }
		                 AI.set(0,0,0,0);
		                 AI.GoNextStep();
		                 answer=AI.get();
		                 redIcon=button[9-answer[2]][answer[3]].getIcon();
		                 button[9-answer[2]][answer[3]].setIcon(button[9-answer[0]][answer[1]].getIcon());
		                 button[9-answer[0]][answer[1]].setIcon(null);
		                 string=" red side ("+(pos_column+1)+","+(10-pos_row)+")-->"+"("+(now_column+1)+","+(10-now_row)+")"+"\n"+ 
		                        " black side ("+(answer[1]+1)+","+(10-answer[0])+")-->"+"("+(answer[3]+1)+","+(10-answer[2])+")"+"\n";
		                 totalString+=string;
		                 history.setText(totalString);
		                 if(redIcon!=null&&redIcon.equals(redMarshal))
		                 {
		                	JOptionPane.showMessageDialog(null,"The black side win");
		                	dispose();
		                 }  	
		             }
		             
                }
	        }
          });
    }
}
