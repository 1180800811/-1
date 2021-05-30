package P3;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Game {
		private String player1 = new String();  //���1
		private String player2 = new String();  //���2
		private Board chessBoard  =  new Board("chess") ;  //��������
		private Board goBoard     =  new Board("go") ;    //Χ������
 		private String history1 = new String();    //���1��������ʷ
		private String history2 = new String();    //���2��������ʷ
		private List<Piece> pieces1 = new ArrayList<>();   //���1����������
		private List<Piece> pieces2 = new ArrayList<>();  //���2����������
		
		// Abstraction Function:
		//	Represent two players of the game ,and the type of the game ,
		//	the operation history of two players,and the pieces of two players
		//Representation invariant:
		//	player1 != null ,player2 != null
		//Safety from rep exposure:
		// player1,player2,history1,history2 are immutable
		// All field is private 
		
		
		/**
		 * ��ʼ����������
		 * @param type  ���̵����ͣ�type="chess"��ʾ��������
		 */
		public Game(String type) {
			if(type.equals("chess")) {//���壬����Ҫ��ʼ�����̣�������
				//���player1������
	            pieces1.add(new Piece(0, 0, "��", true));
	            pieces1.add(new Piece(0, 1, "��", true));
	            pieces1.add(new Piece(0, 2, "��", true));
	            pieces1.add(new Piece(0, 3, "��", true));
	            pieces1.add(new Piece(0, 4, "��", true));
	            pieces1.add(new Piece(0, 5, "��", true));
	            pieces1.add(new Piece(0, 6, "��", true));
	            pieces1.add(new Piece(0, 7, "��", true));
	            //���player2������
	            pieces2.add(new Piece(7, 0, "��", false));
	            pieces2.add(new Piece(7, 1, "��", false));
	            pieces2.add(new Piece(7, 2, "��", false));
	            pieces2.add(new Piece(7, 3, "��", false));
	            pieces2.add(new Piece(7, 4, "��", false));
	            pieces2.add(new Piece(7, 5, "��", false));
	            pieces2.add(new Piece(7, 6, "��", false));
	            pieces2.add(new Piece(7, 7, "��", false));
	            for(int i = 0 ; i < 8 ; i++) {
	            	pieces1.add(new Piece(1, i, "��", true));
	            	pieces2.add(new Piece(6, i, "��", false));
	            }
			}
			
		}
		/**
		 * ��������1������
		 * @param name  ���趨������
		 */
		public void setPlayer1(String name) {
			this.player1 = name ;
		}
		/**
		 * ��������2������
		 * @param name   ���趨������
		 */
		public void setPlayer2(String name) {
			this.player2 = name ;
		}
		/**
		 * ����player1������
		 * @return
		 */
		public String getPlayer1() {
			return this.player1;
		}
		/**
		 * ����player2������
		 * @return
		 */
		public String getPlayer2() {
			return this.player2;
		}
		/**
		 * �õ������Ѿ��������̵����ӵ�����
		 * @param player  ����
		 * @return   �����Ѿ��������̵����ӵ�����
		 */
		public int getSize(boolean player) {
			if(player == true ) {
				return pieces1.size();
			}else {
				return pieces2.size();
			}
		}
		/**
		 * �õ�������ʷ
		 * @param player  ����  
		 * @return  ���ֵĲ�����ʷ
		 */
		public String getHistory(boolean player) {
			if(player) {
				return this.history1;
			}
			return this.history2 ;
		}
		/**
		 * ��Χ������������
		 * @param x  �����ӵĺ�����
		 * @param y  �����ӵ�������
		 * @param player ������ӵ�player
		 * @return  true :������ӳɹ� ��false :����ʧ��
		 */
		public boolean LuoPieceGo(int x , int y ,boolean player ) {
			Action check = new Action();
			if(!check.checkLuoPiece(x, y, goBoard, player)) {//���ж������ܷ�ɹ�
				return false ;
			}			
			Piece piece;
			if(player == true ) {
				piece = new Piece(x, y, "white", player) ;//player1����һ������
			}else {
				piece = new Piece(x, y, "black", player) ;//player2����һ������
			}
				if(player == true ) {
					pieces1.add(piece) ;//��������
					history1 += "����:  (" + x + "," + y +")" ;//���������ʷ
				}else {
					pieces2.add(piece) ;//��������
					history2 += "����:  (" + x + "," + y +")" ;//���������ʷ
				}
				goBoard.setGoPiece(piece);//����
			return true ;
		}
		
		/**
		 * ��Χ����������
		 * @param x  ������ӵĺ�����
		 * @param y  ������ӵ�������
  		 * @param player  �����ӵ�player
		 * @return  true:���ӳɹ� �� false :����ʧ��
		 */
		public boolean TiPieceGo(int x ,int y , boolean player ) {
			Action check = new Action();
			if(!check.checkTiPiece(x, y, player, goBoard)) {//�ж������ܷ�ɹ�
				return false ;
			}
			Piece piece ; 
			if(player == true ) {
				piece = new Piece(x, y, "white", player);//player1����һ������
			}else {
				piece = new Piece(x, y, "black", player) ;//player2����һ������
			}
			goBoard.RemoveGoPiece(piece);//�������Ƴ�����
			if(player==true) {
				history1 += "����:" + x + "," + y +")" ;//�����ʷ
				for(int i = 0 ; i < pieces2.size() ; i++) {
					if(pieces2.get(i).getPosition().getX() == x && pieces2.get(i).getPosition().getY() == y ) {
						pieces2.remove(i);//�Ƴ�����
						break;
					}
				}
			}else {
				history2 += "����:" + x + "," + y +")" ;//�����ʷ
				for(int i = 0 ; i < pieces1.size() ; i++) {
					if(pieces1.get(i).getPosition().getX() == x && pieces1.get(i).getPosition().getY() == y ) {
						pieces1.remove(i);//�Ƴ�����
						break;
					}
				}
			}
			
			return true ;
		}
		
	   /**
		 * �����������ϰ�һ�������Ƶ���һ��λ��
		* @param x   ��Ҫ�ƶ������ӵ�ԭλ�õ�x����
	 	* @param y   ��Ҫ�ƶ������ӵ�ԭλ�õ�y����
	 	* @param px  ��Ҫ�ƶ������ӵ�Ŀ��λ�õ�x����
	 	* @param py  ��Ҫ�ƶ������ӵ�Ŀ��λ�õ�y����
	 	* @param player ��Ҫ�ƶ������ӵ�player
		 * @return  true: �ƶ����ӳɹ� �� false : �ƶ�����ʧ��
		 */
		public boolean movePiece(int x ,int y ,int px, int py , boolean player) {
			Action action = new Action();
			if(!action.checkMove(x, y, px, py, player, chessBoard)) {//�ж��ƶ������ܷ�ɹ�
				return false ;
			}
			Position position1 = new Position() ;
			position1.setPosition(x, y);
			Piece piece = getPiece(position1);//��ȡ���ƶ�����
			chessBoard.RemoveChessPiece(piece);//�Ѵ��ƶ�����ɾȥ
			piece.setPiecePosition(px, py);//�������ӵ�λ��
			chessBoard.setChessPiece(piece);
	        if (player == true ) {
	            history1 += "����:(" + x + "," + y + ")" + "->(" + px + "," + py + ") ";//�����ʷ

	        } else {
	            history2 += "����:(" + x + "," + y + ")" + "->(" + px + "," + py + ") ";//�����ʷ
	        }
			return true ;
		}
		/**
		 * �ж���������ܷ�ɹ�
		 * @param x   ��Ҫ�ƶ������ӵ�ԭλ�õ�x����
		 * @param y   ��Ҫ�ƶ������ӵ�ԭλ�õ�y����
		 * @param px  ��Ҫ�ƶ������ӵ�Ŀ��λ�õ�x����
		 * @param py  ��Ҫ�ƶ������ӵ�Ŀ��λ�õ�y����
		 * @param player ��Ҫ�ƶ������ӵ�player
		 * @return  true :��������ܹ��ɹ� ��false : �������ʧ��
		 */
		public boolean eatPiece(int x ,int y , int px , int py , boolean player	) {
			Action action = new Action() ; 
			if(!action.checkeatPiece(x, y, px, py, player, chessBoard)) {//�жϳ����ܷ�ɹ�
				return false ;
			}
			Position position = new Position() ; 
			position.setPosition(px, py);
			Piece piece = getPiece(position);//�õ���ɾȥ������
	        if (player ==true ) {
	            history1 += "����:(" + x + "," + y + ")" + "->(" + px + "," + py + ") ";
	            pieces2.remove(piece);
	        } else {
	            history2 += "����:(" + x + "," + y + ")" + "->(" + px + "," + py + ") ";
	            pieces1.remove(piece);
	        }
	        chessBoard.RemoveChessPiece(piece);//������ȥ���������
	        piece.setPlayer(player); //Ϊ�����趨�µ�player
	        chessBoard.setChessPiece(piece);// �������ϵ�ӡ�Ǳ��Ϊ����״̬
	        position.setPosition(x, y); 
	        piece =getPiece(position);// �õ����ƶ���piece
	        chessBoard.RemoveChessPiece(piece);//ɾ������
	        piece.setPiecePosition(px, py);
			chessBoard.setChessPiece(piece);
			return true ;
		}
		/**
		 * ����ָ��������ָ��λ�õ����Ӷ���
		 * @param position   ������λ��
		 * @param board   ����������
		 * @return  ����ָ�������ϵ�ָ��λ�õ�����
		 */
	    public Piece getPiece(Position position) {
	        int size1 = getSize(true);
	        int size2 = getSize(false);
	        for (int i = 0; i < size1; i++) {// �������е����ӣ��ҵ�����λ�������
	            if (pieces1.get(i).getPosition().EqualPosition(position)) {
	                return pieces1.get(i);//�õ�����
	            }
	        }
	        for (int i = 0; i < size2; i++) {
	            if (pieces2.get(i).getPosition().EqualPosition(position)) {
	                return pieces2.get(i);//�õ�����
	            }
	        }
	        return null;
	    }

		/**
		 * ���Ӳ�������
		 * @param s  ���ӵĲ���ľ�������
		 * @param player  �޸ĵ�player
		 */
		public void addHistory(String s , boolean player) {//���ӡ���������һ��ʷ
			if(s != null ) {
				if(player)
					this.history1 += s ;//player1������ʷ
				else
					this.history2 += s ;//player2������ʷ
			}

		}
		/**
		 * ��ʾ����
		 * @param type  ��������
		 */
		public void showBoard(String type) {//��ʾ����
			System.out.println("player1:" + this.getPlayer1() + "\t" + "player2:" +this.getPlayer2());
			if(type.equals("chess")) {
	        	System.out.println(" " + "\t"+ "\t"+0 + "\t"+"\t"+1 + "\t"+"\t"+2 + "\t"+"\t"+3+ "\t" +"\t"+4 + "\t"+"\t"+5 + "\t"+"\t"+6 + "\t"+"\t"+7+ "\t"+"\t");
				for(int i = 0 ; i < 8 ; i ++) {
	            	System.out.print( i +"\t" + "\t");
					for( int j = 0 ; j < 8 ; j ++) {
						Position position = new Position() ;
						position.setPosition(i, j);
						if(chessBoard.getZhanYong(position, "chess")) {//�ж�λ����û������
							if(chessBoard.getWhoZhanYong(position, "chess")) {//�ж����ӱ�˭ռ��
								System.out.print("1:" + this.getPiece(position).getName()+"\t" + "\t");//��ʾ����
							}else {
								System.out.print("2:" + this.getPiece(position).getName()+"\t"+ "\t");//��ʾ����
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
						if(goBoard.getZhanYong(position, "go")) {//�ж�λ����û������
							System.out.print(this.getPiece(position).getName() + "\t");//��ʾ����
						}else {
							System.out.print("   \t");
						}
					}
					System.out.println();
				}
			}
		}
		
}
