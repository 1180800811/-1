package P3;

public class Position {
		private int x = -1 ;
		private int y = -1 ;
		// Abstraction Function:
		//	AF(x,y) = Represent a position with abscissa of x and ordinate of y  
		//
		//Representation invariant:
		//	 x and y are int 
		//
		//Safety from rep exposure:
		// the field of x and y is private 
		
		/**
		 * 设置棋子的坐标
		 * @param x 棋子的横坐标
		 * @param y 棋子的纵坐标
		 */
		public void setPosition(int x,int y) {//设置棋子的坐标
			this.x = x ;
			this.y = y ;
		}
	
		/**
		 * 返回棋子的横坐标
		 * @return  棋子的横坐标
		 */
		public int getX() {//得到棋子的横坐标
			return this.x ;
		}
		
		/**
		 * 返回棋子的纵坐标
		 * @return  棋子的纵坐标
		 */
		public int getY() {//得到棋子的纵坐标
			return this.y ;
		}
		
		@Override
		public String toString() {//重写toString方法
			return "(" + this.x  + ","  + this.y + ")" ;
		}
		/**
		 * 判断两个棋子的坐标是否相同
		 * @param s  待比较的棋子
		 * @return   true:如果两个棋子坐标相同，false :如果两个棋子坐标不同
		 */
		public boolean EqualPosition(Position s) {//判断两个位置是否一样
			if(s.getX() == this.getX()) {
				if(s.getY() == this.getY()) {
					return true ;
				}
			}	
			return false;
		}
		
		/**
		 * 判断棋子是否超出棋盘范围
		 * @param type   棋子的类型
		 * @return  true :未超出范围 ，false : 超出范围
		 */
		public boolean FanWei(String type) {//判断棋子是否超出棋盘范围
			if(type.equals("go")) { //围棋
				return x >= 0 && x <= 18 && y >= 0 && y <= 18;
			}
			 return x >= 0 && x <= 7 && y >= 0 && y <= 7;
			
		}
}
