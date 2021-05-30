package P3;

public class Piece {
		private Position position = new Position() ;//棋子的坐标
		private String PieceName = new String();//棋子的名字
		private boolean Owner; //棋子的拥有者,true表示player1，false表示player2
		// Abstraction Function:
		//	AF(position,PieceName,Owner) = Represent the position of the piece, 
		//		the name of the piece and the owner of the piece
		//
		//Representation invariant:
		//	PieceName != null , position!=null 
		//
		//Safety from rep exposure:
		// PieceName is immutable so there is no need for getName() to make defensive copy 
		// All field is private 
		//
		/**
		 * 初始化棋子
		 * @param x  棋子的横坐标
		 * @param y  棋子的纵坐标
		 * @param owner  棋子的拥有者
		 * @param Piecename  棋子的名字
		 */
		public Piece(int x ,int y , String Piecename, boolean  owner ) {//初始化
			this.position.setPosition(x, y);
			this.Owner = owner ; 
			this.PieceName  = Piecename ;
		}
		/**
		 * 设置棋子的位置
		 * @param px  棋子的横坐标
		 * @param py  棋子的纵坐标
		 */
	    public void setPiecePosition(int px, int py) {//设置棋子的位置
	        this.position.setPosition(px, py);
	    }
		/**
		 * 返回棋子的位置
		 * @return  棋子的位置
		 */
		public Position getPosition() {//得到棋子的位置
			return this.position;
		}
		/**
		 * 返回棋子的拥有者
		 * @return
		 */
		public boolean getOwner() {//得到棋子的player
			return this.Owner;
		}
		/**
		 * 设定棋子的player
		 * @param player   待设定的player
		 */
		public void setPlayer(boolean player) {//设置棋子的player
			this.Owner = player ;
		}
		/**
		 * 返回棋子的名字
		 * @return
		 */
		public String getName() {//得到棋子的名字
			return this.PieceName ;
		}
	    /**
	     * 设置棋子的名字
	     * 
	     * @param pieceName 棋子的名字
	     */
	    public void setPieceName(String pieceName) {//设置棋子的名字
	        this.PieceName = pieceName;
	    }
		
}
