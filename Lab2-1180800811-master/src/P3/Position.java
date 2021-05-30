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
		 * �������ӵ�����
		 * @param x ���ӵĺ�����
		 * @param y ���ӵ�������
		 */
		public void setPosition(int x,int y) {//�������ӵ�����
			this.x = x ;
			this.y = y ;
		}
	
		/**
		 * �������ӵĺ�����
		 * @return  ���ӵĺ�����
		 */
		public int getX() {//�õ����ӵĺ�����
			return this.x ;
		}
		
		/**
		 * �������ӵ�������
		 * @return  ���ӵ�������
		 */
		public int getY() {//�õ����ӵ�������
			return this.y ;
		}
		
		@Override
		public String toString() {//��дtoString����
			return "(" + this.x  + ","  + this.y + ")" ;
		}
		/**
		 * �ж��������ӵ������Ƿ���ͬ
		 * @param s  ���Ƚϵ�����
		 * @return   true:�����������������ͬ��false :��������������겻ͬ
		 */
		public boolean EqualPosition(Position s) {//�ж�����λ���Ƿ�һ��
			if(s.getX() == this.getX()) {
				if(s.getY() == this.getY()) {
					return true ;
				}
			}	
			return false;
		}
		
		/**
		 * �ж������Ƿ񳬳����̷�Χ
		 * @param type   ���ӵ�����
		 * @return  true :δ������Χ ��false : ������Χ
		 */
		public boolean FanWei(String type) {//�ж������Ƿ񳬳����̷�Χ
			if(type.equals("go")) { //Χ��
				return x >= 0 && x <= 18 && y >= 0 && y <= 18;
			}
			 return x >= 0 && x <= 7 && y >= 0 && y <= 7;
			
		}
}
