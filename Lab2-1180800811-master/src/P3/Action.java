package P3;

import static org.junit.Assert.assertEquals;

public class Action {
	
	
	/**
	 * 检查移动棋子是否可以成功
	 * @param x   需要移动的棋子的原位置的x坐标
	 * @param y   需要移动的棋子的原位置的y坐标
	 * @param px  需要移动的棋子的目标位置的x坐标
	 * @param py  需要移动的棋子的目标位置的y坐标
	 * @param player 需要移动的棋子的player
	 * @param chessboard 棋盘
	 * @return  true :如果移动棋子能够成功 ，false : 如果移动棋子失败
	 */
	public boolean checkMove(int x, int y , int px , int py , Boolean player , Board chessboard) {
		Position position1 = new Position() ; 
		position1.setPosition(x, y);
		Position position2 = new Position() ; 
		position2.setPosition(px, py);
		
		if(position1.EqualPosition(position2)) {
			System.out.println("error : 原位置和目标位置相同");
			return false ;
		}
		if(position1.FanWei("chess")) {  //判断待移动的原棋子位置是否超出棋盘位置
			if(position2.FanWei("chess")) {//判断需要移动的棋子的位置是否超出棋盘位置
				if(chessboard.getZhanYong(position1, "chess")) {//判断原来的棋子的坐标是否被占用
					if(chessboard.getWhoZhanYong(position1, "chess")==player) {//判断被占用的位置是否是被player占用
						if(!chessboard.getZhanYong(position2, "chess")) {
							return true ;
						}else {
							System.out.println("error : 目标位置被棋子占用");
							return false ;
						}
					}else {
							System.out.println("error : 当前的棋子不是被己方占用");
							return false ;
					}
				}else {
					System.out.println("error : 当前需要移动的棋子的位置无棋子");
					return false ;					
				}
			}else {
				System.out.println("error :  目标位置超出棋盘范围");
				return false ;				
			}
		}else {
			System.out.println("error : 当前棋子的位置超出棋盘范围");
			return false ;
		}

		
	}
	
	/**
	 * 判断象棋吃子能否成功
	 * @param x   需要移动的棋子的原位置的x坐标
	 * @param y   需要移动的棋子的原位置的y坐标
	 * @param px  需要移动的棋子的目标位置的x坐标
	 * @param py  需要移动的棋子的目标位置的y坐标
	 * @param player 需要移动的棋子的player
	 * @param chessboard 棋盘
	 * @return  true :如果吃子能够成功 ，false : 如果吃子失败
	 */
	public boolean checkeatPiece(int x, int y , int px , int py , Boolean player , Board chessboard) {
		Position position1 = new Position() ; 
		position1.setPosition(x, y);
		Position position2 = new Position() ; 
		position2.setPosition(px, py);
		if(position1.EqualPosition(position2)) {
			System.out.println("error : 原位置和目标位置相同");
			return false ;
		}
		if(position1.FanWei("chess")) {  //判断待移动的原棋子位置是否超出棋盘位置
			if(position2.FanWei("chess")) {//判断需要移动的棋子的位置是否超出棋盘位置
				if(chessboard.getZhanYong(position1, "chess")) {//判断原来的棋子的坐标是否被占用
					if(chessboard.getWhoZhanYong(position1, "chess")==player) {//判断被占用的位置是否是被player占用
						if(chessboard.getZhanYong(position2, "chess")) {
							if(chessboard.getWhoZhanYong(position2, "chess")!=player) {
								return true ;
							}
							System.out.println("error : 目标位置的棋子不是敌方的");
							return false ;
						}
						System.out.println("error : 目标位置无棋子");
						return false;
					}
						System.out.println("error : 当前的棋子不是被己方占用");
						return false ;
				}
						System.out.println("error : 当前需要移动的棋子的位置无棋子");
						return false ;
			}
						System.out.println("error :  目标位置超出棋盘范围");
						return false ;
		}	
						System.out.println("error : 当前棋子的位置超出棋盘范围");
						return false ;
		
	}
	/**
	 * 判断围棋提子能否成功
	 * @param x   待提子的横坐标
	 * @param y   待提子的纵坐标 
	 * @param player  操作提子的player
	 * @param goBoard  棋盘
	 * @return  true : 如果能够提子成功 ，false :如果提子失败
	 */
	public boolean checkTiPiece(int x , int y ,boolean player, Board goBoard ) {
		Position position = new Position() ; 
		position.setPosition(x, y);
; 
		
		if(position.FanWei("go")) {//判断是否待提子的位置超过了棋盘范围
			if(goBoard.getZhanYong(position, "go")) {//判断待提子的位置有无棋子
				if(goBoard.getWhoZhanYong(position, "go")!=player) {//判断待提的子是不是对方的棋子
					return true ;
				}
				System.out.println("error : 待提的子不是对方的棋子");
				return false ;
			}
				System.out.println("error : 待提子的位置无棋子");
				return false;
		}
				System.out.println("error : 待提子的位置超过了棋盘范围");
				return false ;
	}
	/**
	 * 判断落子能否成功
	 * @param x  待落子的横坐标
	 * @param y  待落子的纵坐标
	 * @param goBoard  围棋棋盘
	 * @param player   棋子的player
	 * @return  true :如果落子成功 ，false : 落子失败
	 */
	public boolean checkLuoPiece(int x , int y , Board goBoard,boolean player) {
		Position position1 = new Position() ; 
		position1.setPosition(x, y);

		if(position1.FanWei("go")) {//判断待落子的位置是否超出期盼范围
			if(goBoard.getZhanYong(position1, "go")) {//判断待落子的位置是否有棋子
				System.out.println("error : 待落子的位置已经有棋子");
				return false;
			}
			return true ;
		}
				System.out.println("error : 待落子的位置超过了棋盘范围");
				return false ;
	}
	

}