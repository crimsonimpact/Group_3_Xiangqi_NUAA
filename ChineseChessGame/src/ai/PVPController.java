package ai;
import rule.ChineseJudge;
public class PVPController {
	
	private int composition[][] ={
			{6,4,3,2,7,2,3,4,6},
			{0,0,0,0,0,0,0,0,0},
			{0,5,0,0,0,0,0,5,0},
			{1,0,1,0,1,0,1,0,1},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{8,0,8,0,8,0,8,0,8},
			{0,12,0,0,0,0,0,12,0},
			{0,0,0,0,0,0,0,0,0},
			{13,11,10,9,14,9,10,11,13} };
	
	private int playerFlag=1;
	private int xfrom, xto, yfrom, yto;
	private int preRegretValue=0,nowRegretValue=0;
	private boolean validFlag;
	
	public PVPController() {}
	
	private int Change(int x) { int tem=(x + 1) % 2;  return tem; }
	
	public void set(int _xfrom, int _yfrom, int _xto, int _yto) {
		xfrom = _xfrom;
		xto = _xto;
		yfrom = _yfrom;
		yto = _yto;
	}
    
	public void Regret(int[] pos)
	{
		composition[pos[0]][pos[1]]=composition[pos[2]][pos[3]];
		composition[pos[2]][pos[3]]=nowRegretValue;
		composition[pos[4]][pos[5]]=composition[pos[6]][pos[7]];
		composition[pos[6]][pos[7]]=preRegretValue;
	}
	
	private boolean notSame(int x1, int y1, int x2, int y2) {
		int chess1 = composition[x1][y1], chess2 = composition[x2][y2];
		if (chess2 == 0) return true;
		if (chess1 <= 7 && chess2 > 7) return true;
		if (chess1 > 7 && chess2 <= 7) return true;
		return false;
	}
	
	private void FutherJudgeUpdate() {
		ChineseJudge judge = new ChineseJudge();
		if ( notSame(xfrom, yfrom, xto, yto) ) {
			if (judge.choose(xfrom, yfrom, xto , yto, composition)) {
				preRegretValue = nowRegretValue;
				nowRegretValue = composition[xto][yto];
				composition[xto][yto] = composition[xfrom][yfrom];
				composition[xfrom][yfrom] = 0;
			} else {
				validFlag = false;
			}
		} else {
			validFlag = false;
		}
	}
	
	public void GoNextStep() {
		playerFlag = Change(playerFlag);
		validFlag = true;
		if ((playerFlag == 0 && composition[xfrom][yfrom] <= 7) || (playerFlag == 1 && composition[xfrom][yfrom] > 7) ) {
			FutherJudgeUpdate();
		} else {
			validFlag = false;
		}
	}
	
	public boolean Valid() {
		if(!validFlag){
			playerFlag = Change(playerFlag);
		}
		return validFlag;
	}
	
}