package P3;

import java.util.Scanner;
import java.io.IOException;
public class MyChessAndGoGame {
	public static void main(String[] args) {
		System.out.println("��ѡ����Ϸ���� : chess or go ");
		Scanner in = new Scanner(System.in);
		int player = 0 ;//�жϵ�ǰ�����������˭
		String  GameType  =  in.next() ;
		while(!GameType.equals("chess")&&!GameType.equals("go")) {
			System.out.println("��������������ʾ��������");
			GameType  =  in.next() ;
		}
		int x = -1 ; //����������
		if(GameType.equals("chess")) {
			Game game = new Game("chess") ; 
			System.out.println("������player1������ :");
			game.setPlayer1(in.next());//�������1������
			System.out.println("������player2������ :");
			game.setPlayer2(in.next());//�������1������
			String player3 = new String() ;//player3��ʾ��ǰ����������
			while(true) {
				if((player % 2 )== 0)
					player3 = game.getPlayer1() ;//player3��ʾplayer1
				else{
					player3 = game.getPlayer2();//player3��ʾplayer2
				}	
				System.out.println("������" + player3 + "���в���" );
				System.out.println("������ʾ����������ѡ�����");
				System.out.println("-------------------------------------------------------------------------------------------------------------");
				System.out.println("1.����\t2.����\t3.��ʷ\t4.��������\t5.��ʾ����\t6.��ѯ����\t7����\t8.����");
				System.out.println("--------------------------------------------------------------------------------------------------------------");
				x= in.nextInt();
				switch(x) {
					case  1 :
						System.out.println("��������Ҫ�ƶ����ӵĳ�ʼ���������Ŀ���������:");
							if(game.movePiece(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), player % 2 == 0)) {//�ж������ܷ�ɹ�
								player ++ ;//����Ȩ�ı�
							}
		                    game.showBoard("chess");//��ʾ����
						break ;
					case  2  :
						System.out.println("��������Ӳ�����Ҫ�ĳ�ʼ���������Ŀ���������:");
						if(game.eatPiece(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), player%2 == 0)) {//�жϳ����ܷ�ɹ�
							player++;//����Ȩ�ı�
						}
	                    game.showBoard("chess");//��ʾ����
						break;
					case  3 : 
						System.out.println("��������Ҫ�鿴��ѡ��,ѡ��1(1����player1)��2(����player2):");
						System.out.println("The history of " + game.getPlayer1() + " : " + game.getHistory(in.nextInt()==1));//��ѯ��ҵ���ʷ
						break;
					case 4 :
						System.out.println("��������Ҫ�鿴��ѡ��,ѡ��1��2:");
						System.out.println(game.getSize(in.nextInt() == 1 ));//��ѯ��������
						break ;
					case 5 : 
						game.showBoard("chess");//��ʾ����
						break;
					case 6 :
						System.out.println("�������ѯ������x��y");
						Position position = new Position() ;
						position.setPosition(in.nextInt(), in.nextInt());
						Piece piece ;
						if((piece=game.getPiece(position))== null) {//�ж�λ��(x,y)��û������
							System.out.println("(" + position.getX() + "," + position.getY() +")" +"������");
						}else {
							piece = game.getPiece(position);
							String player4 = new String() ;
							if(piece.getOwner() == true )
								player4 = game.getPlayer1();//���ӵ�player�����1
							else
								player4 = game.getPlayer2();//���ӵ�player�����2
							System.out.println("���ӵ�player:" + player4 );
							System.out.println("���ӵ�name ��" + piece.getName());
							System.out.println("���ӵ�position��"+piece.getPosition());
						}
						break ;
					case 7 :
	                    System.out.println("�Ѿ�������ѡ��");
	                    game.addHistory("����", player % 2 == 0);//����
	                    player++;//����Ȩ�ı�
	                    game.showBoard("chess");//��ʾ����
	                    break;
					case 8 :
						in.close();
						System.exit(0);
					default:
						System.out.println("�������,�������ʾ��Ϣ��������:");
						break; 	
				}
			}
		}else  {
			Game game = new Game("go") ; 
			System.out.println("������player1������ :");
			game.setPlayer1(in.next());//�������1������
			System.out.println("������player2������ :");
			game.setPlayer2(in.next());//�������2������
			String player3 = new String() ;//player3��ʾ��ǰ����������
			while(true) {
				if((player % 2 )== 0)
					player3 = game.getPlayer1() ;//player3��ʾplayer1
				else{
					player3 = game.getPlayer2();//player3��ʾplayer2
				}
				System.out.println("������" + player3 + "���в���" );
				System.out.println("������ʾ����������ѡ�����");
				System.out.println("-----------------------------------------------------------------------------------------------------------------");
				System.out.println("1.����\t2.����\t3.��ʷ\t4.��������\t5.��ʾ����\t6.��ѯ����\t7����\t8.����");
				System.out.println("-------------------------------------------------------------------------------------------------------------------");
				x= in.nextInt();
				switch(x) {
					case 1 :
						System.out.println("��������Ҫ���ӵĺ������������:");
						if(game.LuoPieceGo(in.nextInt(), in.nextInt(), player % 2 == 0 )) {//�ж������ܷ�ɹ�
							player ++ ;//����Ȩ�ı�
						}
						game.showBoard("go");//��ʾ����
						break;
					case 2 :
						System.out.println("��������Ҫ���ӵĺ������������:");
						if(game.TiPieceGo(in.nextInt(), in.nextInt(), player % 2 == 0)) {//�ж������ܷ�ı�
							player++;//����Ȩ�ı�
						}
						game.showBoard("go");//��ʾ����
						break;
					case 3:
						System.out.println("��������Ҫ�鿴��ѡ��,ѡ��1(1����player1)��2(����player2):");
						System.out.println("The history of " + game.getPlayer1() + " : " + game.getHistory(in.nextInt()==1));//��ʾ��ʷ
						break;
					case 4 :
						System.out.println("��������Ҫ�鿴��ѡ��,ѡ��1��2:");
						System.out.println(game.getSize(in.nextInt() == 1 ));//��ʾ���ӵ���Ŀ
						break;
					case 5 : 
						game.showBoard("go");//��ʾ����
						break ;
					case 6 :
						System.out.println("�������ѯ������x��y");
						Position position = new Position() ;
						position.setPosition(in.nextInt(), in.nextInt());
						Piece piece ;
						if((piece=game.getPiece(position))== null) {//�ж�λ��(x,y)�Ƿ�������
							System.out.println("(" + position.getX() + "," + position.getY() +")" +"������");//��ʾ������
							
						}else {
							piece = game.getPiece(position);
							String player4 = new String() ;
							if(piece.getOwner() == true )
								player4 = game.getPlayer1();
							else
								player4 = game.getPlayer2();
							System.out.println("���ӵ�player:" + player4 );
							System.out.println("���ӵ�name ��" + piece.getName());
							System.out.println("���ӵ�position��"+piece.getPosition());
						}
						break ;
					case 7 :
	                    System.out.println("�Ѿ�������ѡ��");
	                    game.addHistory("����", player % 2 == 0);//����
	                    player++;
	                    game.showBoard("go");//��ʾ����
	                    break;
					case 8 :
						in.close();
	                    game.showBoard("go");//��ʾ����
						System.exit(0);
					default:
						System.out.println("�������,�������ʾ��Ϣ��������:");
						break; 	
						
				}
			}
		}
	}
}
