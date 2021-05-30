package P3;

import static org.junit.Assert.assertEquals;

public class Action {
	
	
	/**
	 * ����ƶ������Ƿ���Գɹ�
	 * @param x   ��Ҫ�ƶ������ӵ�ԭλ�õ�x����
	 * @param y   ��Ҫ�ƶ������ӵ�ԭλ�õ�y����
	 * @param px  ��Ҫ�ƶ������ӵ�Ŀ��λ�õ�x����
	 * @param py  ��Ҫ�ƶ������ӵ�Ŀ��λ�õ�y����
	 * @param player ��Ҫ�ƶ������ӵ�player
	 * @param chessboard ����
	 * @return  true :����ƶ������ܹ��ɹ� ��false : ����ƶ�����ʧ��
	 */
	public boolean checkMove(int x, int y , int px , int py , Boolean player , Board chessboard) {
		Position position1 = new Position() ; 
		position1.setPosition(x, y);
		Position position2 = new Position() ; 
		position2.setPosition(px, py);
		
		if(position1.EqualPosition(position2)) {
			System.out.println("error : ԭλ�ú�Ŀ��λ����ͬ");
			return false ;
		}
		if(position1.FanWei("chess")) {  //�жϴ��ƶ���ԭ����λ���Ƿ񳬳�����λ��
			if(position2.FanWei("chess")) {//�ж���Ҫ�ƶ������ӵ�λ���Ƿ񳬳�����λ��
				if(chessboard.getZhanYong(position1, "chess")) {//�ж�ԭ�������ӵ������Ƿ�ռ��
					if(chessboard.getWhoZhanYong(position1, "chess")==player) {//�жϱ�ռ�õ�λ���Ƿ��Ǳ�playerռ��
						if(!chessboard.getZhanYong(position2, "chess")) {
							return true ;
						}else {
							System.out.println("error : Ŀ��λ�ñ�����ռ��");
							return false ;
						}
					}else {
							System.out.println("error : ��ǰ�����Ӳ��Ǳ�����ռ��");
							return false ;
					}
				}else {
					System.out.println("error : ��ǰ��Ҫ�ƶ������ӵ�λ��������");
					return false ;					
				}
			}else {
				System.out.println("error :  Ŀ��λ�ó������̷�Χ");
				return false ;				
			}
		}else {
			System.out.println("error : ��ǰ���ӵ�λ�ó������̷�Χ");
			return false ;
		}

		
	}
	
	/**
	 * �ж���������ܷ�ɹ�
	 * @param x   ��Ҫ�ƶ������ӵ�ԭλ�õ�x����
	 * @param y   ��Ҫ�ƶ������ӵ�ԭλ�õ�y����
	 * @param px  ��Ҫ�ƶ������ӵ�Ŀ��λ�õ�x����
	 * @param py  ��Ҫ�ƶ������ӵ�Ŀ��λ�õ�y����
	 * @param player ��Ҫ�ƶ������ӵ�player
	 * @param chessboard ����
	 * @return  true :��������ܹ��ɹ� ��false : �������ʧ��
	 */
	public boolean checkeatPiece(int x, int y , int px , int py , Boolean player , Board chessboard) {
		Position position1 = new Position() ; 
		position1.setPosition(x, y);
		Position position2 = new Position() ; 
		position2.setPosition(px, py);
		if(position1.EqualPosition(position2)) {
			System.out.println("error : ԭλ�ú�Ŀ��λ����ͬ");
			return false ;
		}
		if(position1.FanWei("chess")) {  //�жϴ��ƶ���ԭ����λ���Ƿ񳬳�����λ��
			if(position2.FanWei("chess")) {//�ж���Ҫ�ƶ������ӵ�λ���Ƿ񳬳�����λ��
				if(chessboard.getZhanYong(position1, "chess")) {//�ж�ԭ�������ӵ������Ƿ�ռ��
					if(chessboard.getWhoZhanYong(position1, "chess")==player) {//�жϱ�ռ�õ�λ���Ƿ��Ǳ�playerռ��
						if(chessboard.getZhanYong(position2, "chess")) {
							if(chessboard.getWhoZhanYong(position2, "chess")!=player) {
								return true ;
							}
							System.out.println("error : Ŀ��λ�õ����Ӳ��ǵз���");
							return false ;
						}
						System.out.println("error : Ŀ��λ��������");
						return false;
					}
						System.out.println("error : ��ǰ�����Ӳ��Ǳ�����ռ��");
						return false ;
				}
						System.out.println("error : ��ǰ��Ҫ�ƶ������ӵ�λ��������");
						return false ;
			}
						System.out.println("error :  Ŀ��λ�ó������̷�Χ");
						return false ;
		}	
						System.out.println("error : ��ǰ���ӵ�λ�ó������̷�Χ");
						return false ;
		
	}
	/**
	 * �ж�Χ�������ܷ�ɹ�
	 * @param x   �����ӵĺ�����
	 * @param y   �����ӵ������� 
	 * @param player  �������ӵ�player
	 * @param goBoard  ����
	 * @return  true : ����ܹ����ӳɹ� ��false :�������ʧ��
	 */
	public boolean checkTiPiece(int x , int y ,boolean player, Board goBoard ) {
		Position position = new Position() ; 
		position.setPosition(x, y);
; 
		
		if(position.FanWei("go")) {//�ж��Ƿ�����ӵ�λ�ó��������̷�Χ
			if(goBoard.getZhanYong(position, "go")) {//�жϴ����ӵ�λ����������
				if(goBoard.getWhoZhanYong(position, "go")!=player) {//�жϴ�������ǲ��ǶԷ�������
					return true ;
				}
				System.out.println("error : ������Ӳ��ǶԷ�������");
				return false ;
			}
				System.out.println("error : �����ӵ�λ��������");
				return false;
		}
				System.out.println("error : �����ӵ�λ�ó��������̷�Χ");
				return false ;
	}
	/**
	 * �ж������ܷ�ɹ�
	 * @param x  �����ӵĺ�����
	 * @param y  �����ӵ�������
	 * @param goBoard  Χ������
	 * @param player   ���ӵ�player
	 * @return  true :������ӳɹ� ��false : ����ʧ��
	 */
	public boolean checkLuoPiece(int x , int y , Board goBoard,boolean player) {
		Position position1 = new Position() ; 
		position1.setPosition(x, y);

		if(position1.FanWei("go")) {//�жϴ����ӵ�λ���Ƿ񳬳����η�Χ
			if(goBoard.getZhanYong(position1, "go")) {//�жϴ����ӵ�λ���Ƿ�������
				System.out.println("error : �����ӵ�λ���Ѿ�������");
				return false;
			}
			return true ;
		}
				System.out.println("error : �����ӵ�λ�ó��������̷�Χ");
				return false ;
	}
	

}