package rule;

public class NextStep 
{
	private int chessmate[][],listOfPossiblePoint[][] = new int[91][2];	
	
	private int xOfChess,yOfChess,countOfPossiblePoint=0,color;
	
	private boolean IsOnBoard(int x1,int y1)
	{
		if(x1<0||x1>8||y1<0||y1>9)
			return false;
		else
			return true;
	}
	
	private int Occupy(int x1, int y1, int x2, int y2)
	{
		int tmp = 0;
		if (x1 == x2)
		{
			int ys = y1 < y2 ? y1 : y2, x = x1;
			for (int y = ys; y <=y1+y2-ys; y++)
				if (chessmate[x][y] != 0) {
					tmp++;
				}
		} else
		if (y1 == y2)
		{
			int xs = x1 < x2 ? x1 : x2, y = y1;
			for (int x = xs; x <= x1+x2-xs; x++)
				if (chessmate[x][y] != 0) {
					tmp++;
				}
		}
		return tmp;
	}
	
	private boolean IsOnSmallBoardRed(int x1,int y1)
	{
		if(x1<3||x1>5||y1<7||y1>9)
			return false;
		else
			return true;
	}
	
	private void Copychess(int[][] a)
    {
    	chessmate = a;
    }
	
	private boolean NoOurChess(int x,int y)
	{
		if(chessmate[y][x]==0)
			return true;
		else
		{
			if(color==1&&chessmate[y][x]>7)
				return true;
			if(color==2&&chessmate[y][x]<=7)
				return true;
		}
		return false;
	}
	
	private void WritePossiblePoint(int x,int y)
	{

		if(NoOurChess(x,y))
		{
			listOfPossiblePoint[countOfPossiblePoint][0]=y;
			listOfPossiblePoint[countOfPossiblePoint][1]=x;
			countOfPossiblePoint++;
		}
	}//write possible point
	
	private boolean IsOnSmallBoardBlack(int x1,int y1)
	{
		if(x1<3||x1>5||y1<0||y1>2)
			return false;
		else
			return true;
	}
	
	private void ShowBing()
	{
		if(yOfChess>=5)
			WritePossiblePoint(xOfChess,yOfChess-1);
		else
		{
			if(IsOnBoard(xOfChess-1,yOfChess))
				WritePossiblePoint(xOfChess-1,yOfChess);
			if(IsOnBoard(xOfChess+1,yOfChess))
				WritePossiblePoint(xOfChess+1,yOfChess);
			if(IsOnBoard(xOfChess,yOfChess-1))
				WritePossiblePoint(xOfChess,yOfChess-1);
		}
	}
	
	private void ShowZu()
	{
		if(yOfChess<5)
			WritePossiblePoint(xOfChess,yOfChess+1);
		else
		{
			if(IsOnBoard(xOfChess-1,yOfChess))
				WritePossiblePoint(xOfChess-1,yOfChess);
			if(IsOnBoard(xOfChess+1,yOfChess))
				WritePossiblePoint(xOfChess+1,yOfChess);
			if(IsOnBoard(xOfChess,yOfChess+1))
				WritePossiblePoint(xOfChess,yOfChess+1);
		}
	}
	
	private void ShowShiRed()
	{
		if(xOfChess==3||xOfChess==5)
			WritePossiblePoint(4,8);
		else
		{
			WritePossiblePoint(3,7);
			WritePossiblePoint(3,9);
			WritePossiblePoint(5,7);
			WritePossiblePoint(5,9);
		}
	}
	
	private void ShowShiBlack()
	{
		if(xOfChess==3||xOfChess==5)
			WritePossiblePoint(4,1);
		else
		{
			WritePossiblePoint(3,0);
			WritePossiblePoint(3,2);
			WritePossiblePoint(5,0);
			WritePossiblePoint(5,2);
		}
	}
	
	private void ShowXiangRed()
	{
		if(IsOnBoard(xOfChess-2,yOfChess-2)&&yOfChess-2>=5)
		{
			if(chessmate[yOfChess-1][xOfChess-1]==0)
				WritePossiblePoint(xOfChess-2,yOfChess-2);
		}
		if(IsOnBoard(yOfChess+2,xOfChess-2)&&yOfChess-2>=5)
		{
			if(chessmate[yOfChess-1][xOfChess+1]==0)
				WritePossiblePoint(xOfChess+2,yOfChess-2);
		}
		if(IsOnBoard(xOfChess-2,yOfChess+2))
		{
			if(chessmate[yOfChess+1][xOfChess-1]==0)
				WritePossiblePoint(xOfChess-2,yOfChess+2);
		}
		if(IsOnBoard(xOfChess+2,yOfChess+2))
		{
			if(chessmate[yOfChess+1][xOfChess+1]==0)
				WritePossiblePoint(xOfChess+2,yOfChess+2);
		}		
	}
	
	private void ShowXiangBlack()
	{
		if(IsOnBoard(xOfChess-2,yOfChess-2))
		{
			if(chessmate[yOfChess-1][xOfChess-1]==0)
				WritePossiblePoint(xOfChess-2,yOfChess-2);
		}
		if(IsOnBoard(xOfChess+2,yOfChess-2))
		{
			if(chessmate[yOfChess-1][xOfChess+1]==0)
				WritePossiblePoint(xOfChess+2,yOfChess-2);
		}
		if(IsOnBoard(xOfChess-2,yOfChess+2)&&yOfChess+2<=4)
		{
			if(chessmate[yOfChess+1][xOfChess-1]==0)
				WritePossiblePoint(xOfChess-2,yOfChess+2);
		}
		if(IsOnBoard(xOfChess+2,yOfChess+2)&&yOfChess+2<=4)
		{
			if(chessmate[yOfChess+1][xOfChess+1]==0)
				WritePossiblePoint(xOfChess+2,yOfChess+2);
		}
	}
	
	private void ShowHorse()
	{
		if(IsOnBoard(xOfChess-1,yOfChess) && chessmate[yOfChess][xOfChess-1]==0)
		{
			if(IsOnBoard(xOfChess-2,yOfChess+1))
				WritePossiblePoint(xOfChess-2,yOfChess+1);
			if(IsOnBoard(xOfChess-2,yOfChess-1))
				WritePossiblePoint(xOfChess-2,yOfChess-1);
		}
		if(IsOnBoard(xOfChess+1,yOfChess) && chessmate[yOfChess][xOfChess+1]==0)
		{
			if(IsOnBoard(xOfChess+2,yOfChess+1))
				WritePossiblePoint(xOfChess+2,yOfChess+1);
			if(IsOnBoard(xOfChess+2,yOfChess-1))
				WritePossiblePoint(xOfChess+2,yOfChess-1);
		}
		if(IsOnBoard(xOfChess,yOfChess-1) && chessmate[yOfChess-1][xOfChess]==0)
		{
			if(IsOnBoard(xOfChess-1,yOfChess-2))
				WritePossiblePoint(xOfChess-1,yOfChess-2);
			if(IsOnBoard(xOfChess+1,yOfChess-2))
				WritePossiblePoint(xOfChess+1,yOfChess-2);
		}
		if(IsOnBoard(xOfChess,yOfChess+1) && chessmate[yOfChess+1][xOfChess]==0)
		{
			if(IsOnBoard(xOfChess-1,yOfChess+2))
				WritePossiblePoint(xOfChess-1,yOfChess+2);
			if(IsOnBoard(xOfChess+1,yOfChess+2))
				WritePossiblePoint(xOfChess+1,yOfChess+2);
		}
	}
	
	private void ShowJu()
	{
		int j=1;
		while(xOfChess-j>=0)
		{
			if(chessmate[yOfChess][xOfChess-j]==0)
				WritePossiblePoint(xOfChess-j,yOfChess);
			else
			{
				WritePossiblePoint(xOfChess-j,yOfChess);
				break;
			}
			j++;
		}
		j=1;
		while(xOfChess+j<=8)
		{
			if(chessmate[yOfChess][xOfChess+j]==0)
				WritePossiblePoint(xOfChess+j,yOfChess);
			else
			{
				WritePossiblePoint(xOfChess+j,yOfChess);
				break;
			}
			j++;
		}
		j=1;
		while(yOfChess-j>=0)
		{
			if(chessmate[yOfChess-j][xOfChess]==0)
				WritePossiblePoint(xOfChess,yOfChess-j);
			else
			{
				WritePossiblePoint(xOfChess,yOfChess-j);
				break;
			}
			j++;
		}
		j=1;
		while(yOfChess+j<=9)
		{
			if(chessmate[yOfChess+j][xOfChess]==0)
				WritePossiblePoint(xOfChess,yOfChess+j);
			else
			{
				WritePossiblePoint(xOfChess,yOfChess+j);
				break;
			}
			j++;
		}
	}
	
	private void ShowPao()
	{
		for (int yy = 0; yy <= 8; yy++ )
			if (chessmate[yOfChess][yy] == 0 && Occupy(yOfChess, yy, yOfChess, xOfChess) == 1) {
				WritePossiblePoint(yy, yOfChess);
			} else
			if (chessmate[yOfChess][yy] != 0 && Occupy(yOfChess, yy, yOfChess, xOfChess) == 3) {
				WritePossiblePoint(yy, yOfChess);
			}
		for (int xx = 0; xx <= 9; xx++ )
			if (chessmate[xx][xOfChess] == 0 && Occupy(xx, xOfChess, yOfChess, xOfChess) == 1) {
				WritePossiblePoint(xOfChess, xx);
			} else
			if (chessmate[xx][xOfChess] != 0 && Occupy(xx, xOfChess, yOfChess, xOfChess) == 3) {
				WritePossiblePoint(xOfChess, xx);
			}
	}//炮要考虑隔山打牛的情况，先空着
	
	private void ShowShuai()
	{
		if(IsOnSmallBoardRed(xOfChess-1,yOfChess))
			WritePossiblePoint(xOfChess-1,yOfChess);
		if(IsOnSmallBoardRed(xOfChess+1,yOfChess))
			WritePossiblePoint(xOfChess+1,yOfChess);
		if(IsOnSmallBoardRed(xOfChess,yOfChess-1))
			WritePossiblePoint(xOfChess,yOfChess-1);
		if(IsOnSmallBoardRed(xOfChess,yOfChess+1))
			WritePossiblePoint(xOfChess,yOfChess+1);
	}//同将
	
	private void ShowJiang()
	{
		if(IsOnSmallBoardBlack(xOfChess-1,yOfChess))
			WritePossiblePoint(xOfChess-1,yOfChess);
		if(IsOnSmallBoardBlack(xOfChess+1,yOfChess))
			WritePossiblePoint(xOfChess+1,yOfChess);
		if(IsOnSmallBoardBlack(xOfChess,yOfChess-1))
			WritePossiblePoint(xOfChess,yOfChess-1);
		if(IsOnSmallBoardBlack(xOfChess,yOfChess+1))
			WritePossiblePoint(xOfChess,yOfChess+1);
	}//帅和将要考虑八百里开外一枪干掉对方老大的情况，暂时空着
	
	public int[][] showway(int y,int x,int flag,int a[][])
	{
		xOfChess=x;
		yOfChess=y;
		Copychess(a);
		if(flag<=7)
			color=1;
		else
			color=2;
		countOfPossiblePoint=0;
		switch(flag)
		{
		case 1:ShowZu(); break;
    	case 8:ShowBing(); break;
    	case 2:ShowShiBlack(); break;
    	case 9:ShowShiRed(); break;
    	case 3:ShowXiangBlack(); break;
    	case 10:ShowXiangRed(); break;
    	case 4:
    	case 11:ShowHorse(); break;
    	case 5:
    	case 12:ShowPao(); break;
    	case 6:
    	case 13:ShowJu(); break;
    	case 7:ShowJiang(); break;
    	case 14:ShowShuai(); break;
    	default:assert false:"input wrong qizi";
		}
		listOfPossiblePoint[countOfPossiblePoint][0]=-1;
		listOfPossiblePoint[countOfPossiblePoint][1]=-1;
		return listOfPossiblePoint;
	}

}
