package P3;

public class Piece {
		private Position position = new Position() ;//���ӵ�����
		private String PieceName = new String();//���ӵ�����
		private boolean Owner; //���ӵ�ӵ����,true��ʾplayer1��false��ʾplayer2
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
		 * ��ʼ������
		 * @param x  ���ӵĺ�����
		 * @param y  ���ӵ�������
		 * @param owner  ���ӵ�ӵ����
		 * @param Piecename  ���ӵ�����
		 */
		public Piece(int x ,int y , String Piecename, boolean  owner ) {//��ʼ��
			this.position.setPosition(x, y);
			this.Owner = owner ; 
			this.PieceName  = Piecename ;
		}
		/**
		 * �������ӵ�λ��
		 * @param px  ���ӵĺ�����
		 * @param py  ���ӵ�������
		 */
	    public void setPiecePosition(int px, int py) {//�������ӵ�λ��
	        this.position.setPosition(px, py);
	    }
		/**
		 * �������ӵ�λ��
		 * @return  ���ӵ�λ��
		 */
		public Position getPosition() {//�õ����ӵ�λ��
			return this.position;
		}
		/**
		 * �������ӵ�ӵ����
		 * @return
		 */
		public boolean getOwner() {//�õ����ӵ�player
			return this.Owner;
		}
		/**
		 * �趨���ӵ�player
		 * @param player   ���趨��player
		 */
		public void setPlayer(boolean player) {//�������ӵ�player
			this.Owner = player ;
		}
		/**
		 * �������ӵ�����
		 * @return
		 */
		public String getName() {//�õ����ӵ�����
			return this.PieceName ;
		}
	    /**
	     * �������ӵ�����
	     * 
	     * @param pieceName ���ӵ�����
	     */
	    public void setPieceName(String pieceName) {//�������ӵ�����
	        this.PieceName = pieceName;
	    }
		
}
