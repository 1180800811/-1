package P3;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Game {
		private String player1 = new String();  //玩家1
		private String player2 = new String();  //玩家2
		private Board chessBoard  =  new Board("chess") ;  //象棋棋盘
		private Board goBoard     =  new Board("go") ;    //围棋棋盘
 		private String history1 = new String();    //玩家1的下棋历史
		private String history2 = new String();    //玩家2的下棋历史
		private List<Piece> pieces1 = new ArrayList<>();   //玩家1的所有棋子
		private List<Piece> pieces2 = new ArrayList<>();  //玩家2的所有棋子
		
		// Abstraction Function:
		//	Represent two players of the game ,and the type of the game ,
		//	the operation history of two players,and the pieces of two players
		//Representation invariant:
		//	player1 != null ,player2 != null
		//Safety from rep exposure:
		// player1,player2,history1,history2 are immutable
		// All field is private 
		
		
		/**
		 * 初始化象棋棋盘
		 * @param type  棋盘的类型，type="chess"表示象棋棋盘
		 */
		public Game(String type) {
			if(type.equals("chess")) {//象棋，则需要初始化棋盘，放棋子
				//添加player1的棋子
	            pieces1.add(new Piece(0, 0, "车", true));
	            pieces1.add(new Piece(0, 1, "马", true));
	            pieces1.add(new Piece(0, 2, "象", true));
	            pieces1.add(new Piece(0, 3, "王", true));
	            pieces1.add(new Piece(0, 4, "后", true));
	            pieces1.add(new Piece(0, 5, "象", true));
	            pieces1.add(new Piece(0, 6, "马", true));
	            pieces1.add(new Piece(0, 7, "车", true));
	            //添加player2的棋子
	            pieces2.add(new Piece(7, 0, "车", false));
	            pieces2.add(new Piece(7, 1, "马", false));
	            pieces2.add(new Piece(7, 2, "象", false));
	            pieces2.add(new Piece(7, 3, "王", false));
	            pieces2.add(new Piece(7, 4, "后", false));
	            pieces2.add(new Piece(7, 5, "象", false));
	            pieces2.add(new Piece(7, 6, "马", false));
	            pieces2.add(new Piece(7, 7, "车", false));
	            for(int i = 0 ; i < 8 ; i++) {
	            	pieces1.add(new Piece(1, i, "兵", true));
	            	pieces2.add(new Piece(6, i, "兵", false));
	            }
			}
			
		}
		/**
		 * 设置棋手1的名字
		 * @param name  待设定的名字
		 */
		public void setPlayer1(String name) {
			this.player1 = name ;
		}
		/**
		 * 设置棋手2的名字
		 * @param name   待设定的名字
		 */
		public void setPlayer2(String name) {
			this.player2 = name ;
		}
		/**
		 * 返回player1的名字
		 * @return
		 */
		public String getPlayer1() {
			return this.player1;
		}
		/**
		 * 返回player2的名字
		 * @return
		 */
		public String getPlayer2() {
			return this.player2;
		}
		/**
		 * 得到棋手已经放入棋盘的棋子的数量
		 * @param player  棋手
		 * @return   棋手已经放入棋盘的棋子的数量
		 */
		public int getSize(boolean player) {
			if(player == true ) {
				return pieces1.size();
			}else {
				return pieces2.size();
			}
		}
		/**
		 * 得到操作历史
		 * @param player  棋手  
		 * @return  棋手的操作历史
		 */
		public String getHistory(boolean player) {
			if(player) {
				return this.history1;
			}
			return this.history2 ;
		}
		/**
		 * 在围棋棋盘上落子
		 * @param x  待落子的横坐标
		 * @param y  待落子的纵坐标
		 * @param player 待落的子的player
		 * @return  true :如果落子成功 ，false :落子失败
		 */
		public boolean LuoPieceGo(int x , int y ,boolean player ) {
			Action check = new Action();
			if(!check.checkLuoPiece(x, y, goBoard, player)) {//先判断落子能否成功
				return false ;
			}			
			Piece piece;
			if(player == true ) {
				piece = new Piece(x, y, "white", player) ;//player1建立一个白子
			}else {
				piece = new Piece(x, y, "black", player) ;//player2建立一个黑子
			}
				if(player == true ) {
					pieces1.add(piece) ;//增加棋子
					history1 += "落子:  (" + x + "," + y +")" ;//添加下棋历史
				}else {
					pieces2.add(piece) ;//增加棋子
					history2 += "落子:  (" + x + "," + y +")" ;//添加下棋历史
				}
				goBoard.setGoPiece(piece);//落子
			return true ;
		}
		
		/**
		 * 在围棋盘上提子
		 * @param x  待提的子的横坐标
		 * @param y  待提的子的纵坐标
  		 * @param player  待提子的player
		 * @return  true:提子成功 ， false :提子失败
		 */
		public boolean TiPieceGo(int x ,int y , boolean player ) {
			Action check = new Action();
			if(!check.checkTiPiece(x, y, player, goBoard)) {//判断提子能否成功
				return false ;
			}
			Piece piece ; 
			if(player == true ) {
				piece = new Piece(x, y, "white", player);//player1建立一个白子
			}else {
				piece = new Piece(x, y, "black", player) ;//player2建立一个黑子
			}
			goBoard.RemoveGoPiece(piece);//从棋盘移除棋子
			if(player==true) {
				history1 += "提子:" + x + "," + y +")" ;//添加历史
				for(int i = 0 ; i < pieces2.size() ; i++) {
					if(pieces2.get(i).getPosition().getX() == x && pieces2.get(i).getPosition().getY() == y ) {
						pieces2.remove(i);//移除棋子
						break;
					}
				}
			}else {
				history2 += "提子:" + x + "," + y +")" ;//添加历史
				for(int i = 0 ; i < pieces1.size() ; i++) {
					if(pieces1.get(i).getPosition().getX() == x && pieces1.get(i).getPosition().getY() == y ) {
						pieces1.remove(i);//移除棋子
						break;
					}
				}
			}
			
			return true ;
		}
		
	   /**
		 * 在象棋棋盘上把一个棋子移到另一个位置
		* @param x   需要移动的棋子的原位置的x坐标
	 	* @param y   需要移动的棋子的原位置的y坐标
	 	* @param px  需要移动的棋子的目标位置的x坐标
	 	* @param py  需要移动的棋子的目标位置的y坐标
	 	* @param player 需要移动的棋子的player
		 * @return  true: 移动棋子成功 ， false : 移动棋子失败
		 */
		public boolean movePiece(int x ,int y ,int px, int py , boolean player) {
			Action action = new Action();
			if(!action.checkMove(x, y, px, py, player, chessBoard)) {//判断移动棋子能否成功
				return false ;
			}
			Position position1 = new Position() ;
			position1.setPosition(x, y);
			Piece piece = getPiece(position1);//获取待移动的子
			chessBoard.RemoveChessPiece(piece);//把待移动的子删去
			piece.setPiecePosition(px, py);//更新棋子的位置
			chessBoard.setChessPiece(piece);
	        if (player == true ) {
	            history1 += "移子:(" + x + "," + y + ")" + "->(" + px + "," + py + ") ";//添加历史

	        } else {
	            history2 += "移子:(" + x + "," + y + ")" + "->(" + px + "," + py + ") ";//添加历史
	        }
			return true ;
		}
		/**
		 * 判断象棋吃子能否成功
		 * @param x   需要移动的棋子的原位置的x坐标
		 * @param y   需要移动的棋子的原位置的y坐标
		 * @param px  需要移动的棋子的目标位置的x坐标
		 * @param py  需要移动的棋子的目标位置的y坐标
		 * @param player 需要移动的棋子的player
		 * @return  true :如果吃子能够成功 ，false : 如果吃子失败
		 */
		public boolean eatPiece(int x ,int y , int px , int py , boolean player	) {
			Action action = new Action() ; 
			if(!action.checkeatPiece(x, y, px, py, player, chessBoard)) {//判断吃子能否成功
				return false ;
			}
			Position position = new Position() ; 
			position.setPosition(px, py);
			Piece piece = getPiece(position);//得到待删去的棋子
	        if (player ==true ) {
	            history1 += "吃子:(" + x + "," + y + ")" + "->(" + px + "," + py + ") ";
	            pieces2.remove(piece);
	        } else {
	            history2 += "吃子:(" + x + "," + y + ")" + "->(" + px + "," + py + ") ";
	            pieces1.remove(piece);
	        }
	        chessBoard.RemoveChessPiece(piece);//从棋盘去掉这个棋子
	        piece.setPlayer(player); //为棋子设定新的player
	        chessBoard.setChessPiece(piece);// 将棋盘上的印记标记为更新状态
	        position.setPosition(x, y); 
	        piece =getPiece(position);// 得到待移动的piece
	        chessBoard.RemoveChessPiece(piece);//删除棋子
	        piece.setPiecePosition(px, py);
			chessBoard.setChessPiece(piece);
			return true ;
		}
		/**
		 * 返回指定棋盘上指定位置的棋子对象
		 * @param position   给定的位置
		 * @param board   给定的棋盘
		 * @return  返回指定棋盘上的指定位置的棋子
		 */
	    public Piece getPiece(Position position) {
	        int size1 = getSize(true);
	        int size2 = getSize(false);
	        for (int i = 0; i < size1; i++) {// 遍历所有的棋子，找到符合位置坐标的
	            if (pieces1.get(i).getPosition().EqualPosition(position)) {
	                return pieces1.get(i);//得到棋子
	            }
	        }
	        for (int i = 0; i < size2; i++) {
	            if (pieces2.get(i).getPosition().EqualPosition(position)) {
	                return pieces2.get(i);//得到棋子
	            }
	        }
	        return null;
	    }

		/**
		 * 增加操作步骤
		 * @param s  增加的步骤的具体内容
		 * @param player  修改的player
		 */
		public void addHistory(String s , boolean player) {//增加“跳过”这一历史
			if(s != null ) {
				if(player)
					this.history1 += s ;//player1增加历史
				else
					this.history2 += s ;//player2增加历史
			}

		}
		/**
		 * 显示棋盘
		 * @param type  棋盘类型
		 */
		public void showBoard(String type) {//显示棋盘
			System.out.println("player1:" + this.getPlayer1() + "\t" + "player2:" +this.getPlayer2());
			if(type.equals("chess")) {
	        	System.out.println(" " + "\t"+ "\t"+0 + "\t"+"\t"+1 + "\t"+"\t"+2 + "\t"+"\t"+3+ "\t" +"\t"+4 + "\t"+"\t"+5 + "\t"+"\t"+6 + "\t"+"\t"+7+ "\t"+"\t");
				for(int i = 0 ; i < 8 ; i ++) {
	            	System.out.print( i +"\t" + "\t");
					for( int j = 0 ; j < 8 ; j ++) {
						Position position = new Position() ;
						position.setPosition(i, j);
						if(chessBoard.getZhanYong(position, "chess")) {//判断位置有没有棋子
							if(chessBoard.getWhoZhanYong(position, "chess")) {//判断棋子被谁占用
								System.out.print("1:" + this.getPiece(position).getName()+"\t" + "\t");//显示棋子
							}else {
								System.out.print("2:" + this.getPiece(position).getName()+"\t"+ "\t");//显示棋子
							}

						}else {
							System.out.print("   "+"\t"+ "\t");
						}
					}
					System.out.println();
				}
			}else {
	        	System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7\t8\t9\t10\t11\t12\t13\t14\t15\t16\t17\t18");
				for(int i= 0 ; i < 19 ; i ++) {
	            	System.out.print(i + "\t");
					for( int j = 0 ; j < 19 ; j ++) {
						Position position = new Position() ;
						position.setPosition(i, j);
						if(goBoard.getZhanYong(position, "go")) {//判断位置有没有棋子
							System.out.print(this.getPiece(position).getName() + "\t");//显示棋子
						}else {
							System.out.print("   \t");
						}
					}
					System.out.println();
				}
			}
		}
		
}
