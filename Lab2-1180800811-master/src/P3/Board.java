package P3;

public class Board {
		private boolean[][] goBoard1 = new boolean[19][19] ; //围棋棋盘的占用情况， true表示被占用，false表示未被占用
		private boolean[][] goBoard2 = new boolean[19][19] ; //围棋棋手的占用情况，true表示被player1占用，false表示被player2占用
		
		private boolean[][] chessBoard1 = new boolean[8][8];//象棋棋盘的占用情况， true表示被占用，false表示未被占用
		private boolean[][] chessBoard2 = new boolean[8][8]; //象棋棋手的占用情况，true表示被player1占用，false表示被棋手2
		
		// Abstraction Function:
		//	AF(goBoard1,goBoard2)= goBoard1 and goBoard2 represent the Board of go , goBoard1[i][j] represent 
		//		whether the position of (i,j) is occupied by go piece,goBoard2[i][j] represent whose piece occupy the position of (i,j)
		//	AF(chessBoard1,chessBoard2)= chessBoard1 and chessBoard2 represent the Board of chess , chessBoard1[i][j] represent 
		//		whether the position of (i,j) is occupied by chess piece,chessBoard2[i][j] represent whose piece occupy the position of (i,j)	
		//Representation invariant:
		//	goBoard1
		//Safety from rep exposure:
		// All field is private 
		
		/**
		 * 初始化棋盘
		 * @param type  type =="chess",初始化chess棋盘，否则初始化go棋盘
		 */
		public Board(String type) {
			if(type.equals("go")) {
				for(int i = 0 ; i < 19 ; i++) {
					for(int j = 0 ; j < 19 ; j++) {
						goBoard1[i][j] = false ; //初始化，未被棋子占用
						goBoard2[i][j] = true  ; //初始化
					}
				}
			}else {
				for(int i = 0 ; i < 8 ; i++) {
					for(int j = 0 ; j < 8 ; j++) {
						if(i==0 || i ==1) {
							chessBoard1[i][j] = true ; //棋盘位置被占用
							chessBoard2[i][j] = true ; //棋盘位置被player1占用
						}else if( i==6 || i==7 ) {
							chessBoard1[i][j] = true ;//棋盘位置被占用
							chessBoard2[i][j] = false ;//棋盘位置被player2占用
						}else {
							chessBoard1[i][j] = false;//棋盘位置未被占用
							chessBoard2[i][j] = false ;//棋盘位置被player2占用
						}
						
					}
				}
			}
		}
		/**
		 * 将一个子落在goBoard棋盘上
		 * @param piece  待落的子
		 */
		public void setGoPiece(Piece piece) {//将一个围棋棋子落在棋盘上
			int x = piece.getPosition().getX();
			int y = piece.getPosition().getY();
			goBoard1[x][y] = true ;//位置(x,y)标记为有棋子
			goBoard2[x][y] = piece.getOwner();//棋子的player
		}
	
		/**
		 * 将一个棋子从chessBoard棋盘移除
		 * @param piece  待移除的棋子
		 */
		public void RemoveChessPiece(Piece piece) {//将一个象棋棋子从棋盘上移除
			int x = piece.getPosition().getX();
			int y = piece.getPosition().getY();
			chessBoard1[x][y] = false; //位置(x,y)标记为没有棋子
			chessBoard2[x][y] = piece.getOwner();
		}
		
		/**
		 * 将一个棋子从goBoard棋盘移除
		 * @param piece  待移除的棋子
		 */
		public void RemoveGoPiece(Piece piece) {//将一个围棋棋子从goBoard棋盘移除
			int x = piece.getPosition().getX();
			int y = piece.getPosition().getY();
			goBoard1[x][y] = false; //位置(x,y)标记为没有棋子
			goBoard2[x][y] = piece.getOwner();
		}
		
		/**
		 * 将一个子落在chess棋盘上
		 * @param piece
		 */
		public void setChessPiece(Piece piece) {//将一个象棋棋子落在棋盘上
			int x = piece.getPosition().getX();
			int y = piece.getPosition().getY();
	        chessBoard1[x][y] = true;//位置(x,y)标记为有棋子
	        chessBoard2[x][y] = piece.getOwner();	//棋子的player
		}
		
		/**
		 * 判断某个位置是否被棋子占用
		 * @param position   待判断的位置
		 * @param type 		 棋盘类型
		 * @return  true： 被占用 ，false ： 未被占用
		 */
		public boolean getZhanYong(Position position , String type ) {//判断棋盘的某个位置是否被占用
			int x = position.getX();
			int y = position.getY();
			if(type.equals("chess")) {
				return chessBoard1[x][y] ;
			}else {
				return goBoard1[x][y];
			}	
		}
		/**
		 * 判断某个位置的棋子被谁占用
		 * @param position   待判断的位置
		 * @param type       棋子的类型
		 * @return     谁占用了这个位置的棋子
		 */
		public boolean getWhoZhanYong(Position position ,String type) {//判断棋盘的某个位置被哪个player占用
			int x = position.getX();
			int y = position.getY();
			if(type.equals("chess")) {
				return chessBoard2[x][y] ;
			}else {
				return goBoard2[x][y];
			}
		}

		
		
}
