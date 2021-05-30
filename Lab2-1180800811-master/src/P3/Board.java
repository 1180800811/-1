package P3;

public class Board {
		private boolean[][] goBoard1 = new boolean[19][19] ; //Χ�����̵�ռ������� true��ʾ��ռ�ã�false��ʾδ��ռ��
		private boolean[][] goBoard2 = new boolean[19][19] ; //Χ�����ֵ�ռ�������true��ʾ��player1ռ�ã�false��ʾ��player2ռ��
		
		private boolean[][] chessBoard1 = new boolean[8][8];//�������̵�ռ������� true��ʾ��ռ�ã�false��ʾδ��ռ��
		private boolean[][] chessBoard2 = new boolean[8][8]; //�������ֵ�ռ�������true��ʾ��player1ռ�ã�false��ʾ������2
		
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
		 * ��ʼ������
		 * @param type  type =="chess",��ʼ��chess���̣������ʼ��go����
		 */
		public Board(String type) {
			if(type.equals("go")) {
				for(int i = 0 ; i < 19 ; i++) {
					for(int j = 0 ; j < 19 ; j++) {
						goBoard1[i][j] = false ; //��ʼ����δ������ռ��
						goBoard2[i][j] = true  ; //��ʼ��
					}
				}
			}else {
				for(int i = 0 ; i < 8 ; i++) {
					for(int j = 0 ; j < 8 ; j++) {
						if(i==0 || i ==1) {
							chessBoard1[i][j] = true ; //����λ�ñ�ռ��
							chessBoard2[i][j] = true ; //����λ�ñ�player1ռ��
						}else if( i==6 || i==7 ) {
							chessBoard1[i][j] = true ;//����λ�ñ�ռ��
							chessBoard2[i][j] = false ;//����λ�ñ�player2ռ��
						}else {
							chessBoard1[i][j] = false;//����λ��δ��ռ��
							chessBoard2[i][j] = false ;//����λ�ñ�player2ռ��
						}
						
					}
				}
			}
		}
		/**
		 * ��һ��������goBoard������
		 * @param piece  �������
		 */
		public void setGoPiece(Piece piece) {//��һ��Χ����������������
			int x = piece.getPosition().getX();
			int y = piece.getPosition().getY();
			goBoard1[x][y] = true ;//λ��(x,y)���Ϊ������
			goBoard2[x][y] = piece.getOwner();//���ӵ�player
		}
	
		/**
		 * ��һ�����Ӵ�chessBoard�����Ƴ�
		 * @param piece  ���Ƴ�������
		 */
		public void RemoveChessPiece(Piece piece) {//��һ���������Ӵ��������Ƴ�
			int x = piece.getPosition().getX();
			int y = piece.getPosition().getY();
			chessBoard1[x][y] = false; //λ��(x,y)���Ϊû������
			chessBoard2[x][y] = piece.getOwner();
		}
		
		/**
		 * ��һ�����Ӵ�goBoard�����Ƴ�
		 * @param piece  ���Ƴ�������
		 */
		public void RemoveGoPiece(Piece piece) {//��һ��Χ�����Ӵ�goBoard�����Ƴ�
			int x = piece.getPosition().getX();
			int y = piece.getPosition().getY();
			goBoard1[x][y] = false; //λ��(x,y)���Ϊû������
			goBoard2[x][y] = piece.getOwner();
		}
		
		/**
		 * ��һ��������chess������
		 * @param piece
		 */
		public void setChessPiece(Piece piece) {//��һ��������������������
			int x = piece.getPosition().getX();
			int y = piece.getPosition().getY();
	        chessBoard1[x][y] = true;//λ��(x,y)���Ϊ������
	        chessBoard2[x][y] = piece.getOwner();	//���ӵ�player
		}
		
		/**
		 * �ж�ĳ��λ���Ƿ�����ռ��
		 * @param position   ���жϵ�λ��
		 * @param type 		 ��������
		 * @return  true�� ��ռ�� ��false �� δ��ռ��
		 */
		public boolean getZhanYong(Position position , String type ) {//�ж����̵�ĳ��λ���Ƿ�ռ��
			int x = position.getX();
			int y = position.getY();
			if(type.equals("chess")) {
				return chessBoard1[x][y] ;
			}else {
				return goBoard1[x][y];
			}	
		}
		/**
		 * �ж�ĳ��λ�õ����ӱ�˭ռ��
		 * @param position   ���жϵ�λ��
		 * @param type       ���ӵ�����
		 * @return     ˭ռ�������λ�õ�����
		 */
		public boolean getWhoZhanYong(Position position ,String type) {//�ж����̵�ĳ��λ�ñ��ĸ�playerռ��
			int x = position.getX();
			int y = position.getY();
			if(type.equals("chess")) {
				return chessBoard2[x][y] ;
			}else {
				return goBoard2[x][y];
			}
		}

		
		
}
