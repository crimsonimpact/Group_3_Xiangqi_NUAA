package rule;

public class ChineseJudge
{
	private int chessmate[][];
		
	private int xBefore,yBefore,xAfter,yAfter;
	 
	public ChineseJudge() {}
	
    private void Copychess(int[][] a)
    {
    	chessmate = a;
    }

    private boolean CheckBing()
    {
    	if(yBefore>=5)
    	{
    		if(xBefore==xAfter&&yBefore==yAfter+1)
    			return true;
    		else
    			return false;
    	}
    	else
    	{
    		if((Math.abs(xAfter-xBefore)==1||yBefore==yAfter)||(xAfter==xBefore&&yBefore==yAfter+1))
    			return true;
    		else
    			return false;
    	}
    }
    
    private boolean CheckZu()
    {
    	if(yBefore<5)
    	{
    		if(xBefore==xAfter&&yBefore==yAfter-1)
    			return true;
    		else
    			return false;
    	}
    	else
    	{
    		if((Math.abs(xAfter-xBefore)==1||yBefore==yAfter)||(xAfter==xBefore&&yBefore==yAfter-1))
    			return true;
    		else
    			return false;
    	}
    }
    
    private boolean CheckShiRed()
    {
    	if(xAfter<3||xAfter>5||yAfter<7)
    		return false;
    	else
    	{
    		if(Math.abs(xBefore-xAfter)==1&&Math.abs(yBefore-yAfter)==1)
    			return true;
    		else
    			return false;
    	}
    }
    
    private boolean CheckShiBlack()
    {
    	if(xAfter<3||xAfter>5||yAfter>2)
    		return false;
    	else
    	{
    		if(Math.abs(xBefore-xAfter)==1&&Math.abs(yBefore-yAfter)==1)
    			return true;
    		else
    			return false;
    	}
    }
    
    private boolean CheckXiangRed()
    {
    	if(yAfter<5)
    		return false;
    	else
    	{
    		if(Math.abs(xBefore-xAfter)==2&&Math.abs(yBefore-yAfter)==2)
    		{
    			if(chessmate[(yBefore+yAfter)/2][(xBefore+xAfter)/2]==0)
    				return true;
    			else
    				return false;
    		}
    		else
    			return false;
    	}
    }
    
    private boolean CheckXiangBlack()
    {
    	if(yAfter>=5)
    		return false;
    	else
    	{
    		if(Math.abs(xBefore-xAfter)==2&&Math.abs(yBefore-yAfter)==2)
    		{
    			if(chessmate[(yBefore+yAfter)/2][(xBefore+xAfter)/2]==0)
    				return true;
    			else
    				return false;
    		}
    		else
    			return false;
    	}
    }
    
    private boolean CheckHorse()
    {
    	if(Math.abs(xBefore-xAfter)==2&&Math.abs(yBefore-yAfter)==1)
    	{
    		if(chessmate[yBefore][(xBefore+xAfter)/2]==0)
    			return true;
    		else
    			return false;
    	}
    	else if(Math.abs(xBefore-xAfter)==1&&Math.abs(yBefore-yAfter)==2)
    	{
    		if(chessmate[(yBefore+yAfter)/2][xBefore]==0)
    			return true;
    		else
    			return false;
    	}
    	else
    		return false;
    }
    
    private boolean CheckJu()
    {
    	if(xBefore==xAfter)
    	{
    		if(yAfter>yBefore)
    		{
    			for(int i=yBefore+1;i<yAfter;i++)
    			{
    				if(chessmate[i][xBefore]!=0)
    					return false;
    			}
    			return true;
    		}
    		else
    		{
    			for(int i=yBefore-1;i>yAfter;i--)
    			{
    				if(chessmate[i][xBefore]!=0)
    					return false;
    			}
    			return true;
    		}
    	}
    	else if(yBefore==yAfter)
    	{
    		if(xAfter>xBefore)
    		{
    			for(int i=xBefore+1;i<xAfter;i++)
    			{
    				if(chessmate[yBefore][i]!=0)
    					return false;
    			}
    			return true;
    		}
    		else
    		{
    			for(int i=xBefore-1;i>xAfter;i--)
    			{
    				if(chessmate[yBefore][i]!=0)
    					return false;
    			}
    			return true;
    		}
    	}
    	else
    		return false;
    }
    
    private boolean CheckPao()
    {
    	if(chessmate[yAfter][xAfter]==0)
    	{
    		return CheckJu();
    	}
    	else
    	{
    		if(xAfter==xBefore)
    		{
    			int shan=0, y = yBefore > yAfter ? yAfter : yBefore;
    			for (int i=y; i <= yBefore + yAfter - y; i++)
    				if(chessmate[i][xBefore]!=0)
    					shan++;
    			if(shan==3)
    				return true;
    			else
    				return false;
    		}
    		if(yAfter==yBefore)
    		{
    			int shan=0, x = xBefore > xAfter ? xAfter : xBefore;
    				for(int i=x;i<=xBefore+xAfter-x; i++)
    					if(chessmate[yBefore][i]!=0)
    						shan++;
    			if(shan==3)
    				return true;
    			else
    				return false;
    		}
    		else
    			return true;
    	}
    }
    
    private boolean CheckShuai()
    {
    	if(chessmate[yAfter][xAfter]==7)
    	{
    		if(xBefore==xAfter)
    		{
    			int shan=0;
    			for(int i=yBefore-1;i>yAfter;i--)
    				if(chessmate[i][xBefore]!=0)
    					shan++;
    			if(shan==0)
    				return true;
    			else
    				return false;
    		}
    		else
    			return false;
    	}
    	if(xAfter<3||xAfter>5||yAfter<7)
    		return false;
    	else
    	{
    		if((Math.abs(xBefore-xAfter)==1&&yBefore==yAfter)||(Math.abs(yBefore-yAfter)==1&&xBefore==xAfter))
    			return true;
    		else
    			return false;	
    	}
    }
    
    private boolean CheckJiang()
    {
    	if(chessmate[yAfter][xAfter]==14)
    	{
    		if(xBefore==xAfter)
    		{
    			int shan=0;
    			for(int i=yBefore+1;i<yAfter;i++)
    				if(chessmate[i][xBefore]!=0)
    					shan++;
    			if(shan==0)
    				return true;
    			else
    				return false;
    		}
    		else
    			return false;
    	}
    	if(xAfter<3||xAfter>5||yAfter>2)
    		return false;
    	else
    	{
    		if((Math.abs(xBefore-xAfter)==1&&yBefore==yAfter)||(Math.abs(yBefore-yAfter)==1&&xBefore==xAfter))
    			return true;
    		else
    			return false;	
    	}
    }
    
    public boolean choose(int y,int x,int y2,int x2,int[][] a)
    {
    	
    	Copychess(a);
    	xBefore=x;
    	yBefore=y;
    	xAfter=x2;
    	yAfter=y2;
    	int flag=a[y][x] ;

    	boolean isMoveRight=false;
    	switch(flag)
    	{
    	case 1:isMoveRight=CheckZu(); break;
    	case 8:isMoveRight=CheckBing(); break;
    	case 2:isMoveRight=CheckShiBlack(); break;
    	case 9:isMoveRight=CheckShiRed(); break;
    	case 3:isMoveRight=CheckXiangBlack(); break;
    	case 10:isMoveRight=CheckXiangRed(); break;
    	case 4:
    	case 11:isMoveRight=CheckHorse(); break;
    	case 5:
    	case 12:isMoveRight=CheckPao(); break;
    	case 6:
    	case 13:isMoveRight=CheckJu(); break;
    	case 7:isMoveRight=CheckJiang(); break;
    	case 14:isMoveRight=CheckShuai(); break;
    	default:assert false:"input wrong qizi";
    	}
    	return isMoveRight;
    }	
}



