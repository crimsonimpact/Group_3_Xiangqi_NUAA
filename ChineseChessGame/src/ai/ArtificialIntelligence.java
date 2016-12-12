package ai;
import rule.NextStep;
import rule.ChineseJudge;
public class ArtificialIntelligence {
	
	private int composition[][] = { {6,4,3,2,7,2,3,4,6},
									{0,0,0,0,0,0,0,0,0},
									{0,5,0,0,0,0,0,5,0},
									{1,0,1,0,1,0,1,0,1},
									{0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0},
									{8,0,8,0,8,0,8,0,8},
									{0,12,0,0,0,0,0,12,0},
									{0,0,0,0,0,0,0,0,0},
									{13,11,10,9,14,9,10,11,13} };
	private int xfrom, yfrom, xto, yto, playerFlag;  //0 : AI , 1 : player
	//private int x, y;
	private int maxValue, tmpValue;
	private int blackRegretValue,redRegretValue;
	private boolean updateFlag;
	private boolean validStepFlag = true;
	
	
	public ArtificialIntelligence() {
		playerFlag = 0;
	}
	
	ArtificialIntelligence(int _xfrom, int _yfrom, int _xto, int _yto) {
		xfrom = _xfrom;
		yfrom = _yfrom;
		xto = _xto;
		yto = _yto;
	}
	
	public void set(int _xfrom, int _yfrom, int _xto, int _yto) {
		xfrom = _xfrom;
		yfrom = _yfrom;
		xto = _xto;
		yto = _yto;
		maxValue = -1;
		validStepFlag = true;
	}
	
	private boolean notSame(int x1, int y1, int x2, int y2) {
		int chess1 = composition[x1][y1], chess2 = composition[x2][y2];
		if (chess2 == 0) return true;
		if (chess1 <= 7 && chess2 > 7) return true;
		if (chess1 > 7 && chess2 <= 7) return true;
		return false;
	}
	
	public static int Change(int x) { return (x + 1) % 2; }
	
	public void DoAI(int playerFlag, int dep) {
		
		if (dep == 2) {
			if (maxValue < tmpValue) {
				maxValue = tmpValue;
				updateFlag = true;
			}
			return;
		}
		
		if (playerFlag == 0) {
			for (int row = 0; row <= 9; row ++)
				for (int column = 0; column <= 8; column++) {
					if (composition[row][column] > 7) {
						NextStep nextstep = new NextStep();
						int validRange[][] = nextstep.showway(row, column, composition[row][column], composition);
						for (int i=0; i<validRange.length; i++) {
							int tmpX = validRange[i][0], tmpY = validRange[i][1];
							if (validRange[i][0] == -1 || validRange[i][1] == -1) break;
							if ( notSame(row, column, tmpX, tmpY) ) {
								int chess1 = composition[row][column], chess2 = composition[tmpX][tmpY];
								tmpValue += chess2;
								composition[row][column] = 0;
								composition[tmpX][tmpY] = chess1;
								DoAI(Change(playerFlag), dep);
								tmpValue -= chess2;
								composition[row][column] = chess1;
								composition[tmpX][tmpY] = chess2;
								
							}
						}
					}
				}
		} else {
			for (int row = 0; row <= 9; row ++)
				for (int column = 0; column <= 8; column++) {
					if (composition[row][column] <= 7) {
						NextStep nextstep = new NextStep();
						int validRange[][] = nextstep.showway(row, column, composition[row][column], composition);
						for (int i=0; i<validRange.length; i++) {
							if (validRange[i][0] == -1 || validRange[i][1] == -1) break;
							int tmpX = validRange[i][0], tmpY = validRange[i][1];
							if ( notSame(row, column, tmpX, tmpY) ) {
								int chess1 = composition[row][column], chess2 = composition[tmpX][tmpY];
								tmpValue -= chess2 == 0 ? chess2 : chess2-7;
								composition[row][column] = 0;
								composition[tmpX][tmpY] = chess1;
								DoAI(Change(playerFlag), dep+1);
								tmpValue += chess2 == 0 ? chess2 : chess2-7;
								composition[row][column] = chess1;
								composition[tmpX][tmpY] = chess2;
							}
						}
					}
			}
		}
		
	}
	public void Regret(int[] pos)
	{
		composition[pos[0]][pos[1]]=composition[pos[2]][pos[3]];
		composition[pos[2]][pos[3]]=blackRegretValue;
		composition[pos[4]][pos[5]]=composition[pos[6]][pos[7]];
		composition[pos[6]][pos[7]]=redRegretValue;
	}
	public void DoPlayer() {
		ChineseJudge judge = new ChineseJudge();
		//System.out.println(composition[xfrom][yfrom]+ " " + composition[xto][yto] + " " + notSame(xfrom, yfrom, xto, yto));
		if ( notSame(xfrom, yfrom, xto, yto) ) {
			if (judge.choose(xfrom, yfrom, xto , yto, composition)) {
				redRegretValue = composition[xto][yto];
				composition[xto][yto] = composition[xfrom][yfrom];
				composition[xfrom][yfrom] = 0;
				//System.out.println(composition[xfrom][yfrom]+ " " + composition[xto][yto]);
			} else {
				validStepFlag = false;
			}
		} else {
			validStepFlag = false;
		}
		
	}
	
	public void GoNextStep() {
		playerFlag = Change(playerFlag);
		if (playerFlag == 0) {		//电脑走
			xfrom = yfrom = xto = yto = -1;
			maxValue = -1;
			for (int row = 0; row <= 9; row++)
				for (int column = 0; column <= 8; column++)
					if (composition[row][column] > 7) {
						System.out.println(row + " " + column + " " + composition[row][column]);
						tmpValue = 0;
						NextStep nextstep = new NextStep();
						int validRange[][] = nextstep.showway(row, column, composition[row][column], composition);
						
						for (int i=0; i<validRange.length; i++) {
							if (validRange[i][0] == -1 || validRange[i][1] == -1) {
								System.out.println(i-1);
								break;
							}
						}
						for (int i=0; i<validRange.length; i++) {
							updateFlag = false;
							int tmpX = validRange[i][0], tmpY = validRange[i][1];
							if (tmpX == -1 || tmpY == -1) break;
							System.out.println(validRange[i][0] + " " + validRange[i][1]);
							if ( notSame(row, column, tmpX, tmpY) ) {
								int chess1 = composition[row][column], chess2 = composition[tmpX][tmpY];
								tmpValue += chess2;
								composition[row][column] = 0;
								composition[tmpX][tmpY] = chess1;
								
								 
								
								DoAI(Change(playerFlag), 1);
								if (chess1 == 13) System.out.printf("%d %d\n", tmpValue, maxValue);
								//System.out.println(maxValue + " " + tmpValue);
								
								if (updateFlag) {
									System.out.println("Updated " + tmpX + " " + tmpY + " " + chess2);
									xfrom = row; yfrom = column;
									xto = tmpX; yto = tmpY;
								}
								
								//tmpValue -= chess2 == 0 ? 0 : chess2 - 7;
								composition[row][column] = chess1;
								composition[tmpX][tmpY] = chess2;
								
								tmpValue = 0;
							}
						}
					}
			
			blackRegretValue = composition[xto][yto];
			composition[xto][yto] = composition[xfrom][yfrom];
			composition[xfrom][yfrom] = 0;
		} else {
			if (composition[xfrom][yfrom] > 7) {
				validStepFlag = false;
			} else DoPlayer();
		}
	}
	
	public void print() {
		System.out.println(composition[xfrom][yfrom]);
	}
	
	public int[] get() {		//只有AI调用
		int ans[] = {xfrom, yfrom, xto, yto};
		return ans;
	}
	
	public boolean Valid() {
		if (!validStepFlag) playerFlag = 0;
		return validStepFlag;
	}
	
}



