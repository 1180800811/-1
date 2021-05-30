package P3;

import java.util.Scanner;
import java.io.IOException;
public class MyChessAndGoGame {
	public static void main(String[] args) {
		System.out.println("请选择游戏类型 : chess or go ");
		Scanner in = new Scanner(System.in);
		int player = 0 ;//判断当前操作的玩家是谁
		String  GameType  =  in.next() ;
		while(!GameType.equals("chess")&&!GameType.equals("go")) {
			System.out.println("输入错误，请根据提示重新输入");
			GameType  =  in.next() ;
		}
		int x = -1 ; //操作的类型
		if(GameType.equals("chess")) {
			Game game = new Game("chess") ; 
			System.out.println("请输入player1的名字 :");
			game.setPlayer1(in.next());//设置玩家1的名字
			System.out.println("请输入player2的名字 :");
			game.setPlayer2(in.next());//设置玩家1的名字
			String player3 = new String() ;//player3表示当前操作的棋手
			while(true) {
				if((player % 2 )== 0)
					player3 = game.getPlayer1() ;//player3表示player1
				else{
					player3 = game.getPlayer2();//player3表示player2
				}	
				System.out.println("现在是" + player3 + "进行操作" );
				System.out.println("根据提示输入数字来选择操作");
				System.out.println("-------------------------------------------------------------------------------------------------------------");
				System.out.println("1.移子\t2.吃子\t3.历史\t4.棋子数量\t5.显示棋盘\t6.查询棋子\t7跳过\t8.结束");
				System.out.println("--------------------------------------------------------------------------------------------------------------");
				x= in.nextInt();
				switch(x) {
					case  1 :
						System.out.println("请输入需要移动的子的初始横纵坐标和目标横纵坐标:");
							if(game.movePiece(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), player % 2 == 0)) {//判断移子能否成功
								player ++ ;//操作权改变
							}
		                    game.showBoard("chess");//显示棋盘
						break ;
					case  2  :
						System.out.println("请输入吃子操作需要的初始横纵坐标和目标横纵坐标:");
						if(game.eatPiece(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), player%2 == 0)) {//判断吃子能否成功
							player++;//操作权改变
						}
	                    game.showBoard("chess");//显示棋盘
						break;
					case  3 : 
						System.out.println("请输入需要查看的选手,选择1(1代表player1)或2(代表player2):");
						System.out.println("The history of " + game.getPlayer1() + " : " + game.getHistory(in.nextInt()==1));//查询玩家的历史
						break;
					case 4 :
						System.out.println("请输入需要查看的选手,选择1或2:");
						System.out.println(game.getSize(in.nextInt() == 1 ));//查询棋子数量
						break ;
					case 5 : 
						game.showBoard("chess");//显示棋盘
						break;
					case 6 :
						System.out.println("请输入查询的坐标x和y");
						Position position = new Position() ;
						position.setPosition(in.nextInt(), in.nextInt());
						Piece piece ;
						if((piece=game.getPiece(position))== null) {//判断位置(x,y)有没有棋子
							System.out.println("(" + position.getX() + "," + position.getY() +")" +"无棋子");
						}else {
							piece = game.getPiece(position);
							String player4 = new String() ;
							if(piece.getOwner() == true )
								player4 = game.getPlayer1();//棋子的player是玩家1
							else
								player4 = game.getPlayer2();//棋子的player是玩家2
							System.out.println("棋子的player:" + player4 );
							System.out.println("棋子的name ：" + piece.getName());
							System.out.println("棋子的position："+piece.getPosition());
						}
						break ;
					case 7 :
	                    System.out.println("已经跳过该选手");
	                    game.addHistory("跳过", player % 2 == 0);//跳过
	                    player++;//操作权改变
	                    game.showBoard("chess");//显示棋盘
	                    break;
					case 8 :
						in.close();
						System.exit(0);
					default:
						System.out.println("输入错误,请根据提示信息进行输入:");
						break; 	
				}
			}
		}else  {
			Game game = new Game("go") ; 
			System.out.println("请输入player1的名字 :");
			game.setPlayer1(in.next());//设置玩家1的名字
			System.out.println("请输入player2的名字 :");
			game.setPlayer2(in.next());//设置玩家2的名字
			String player3 = new String() ;//player3表示当前操作的棋手
			while(true) {
				if((player % 2 )== 0)
					player3 = game.getPlayer1() ;//player3表示player1
				else{
					player3 = game.getPlayer2();//player3表示player2
				}
				System.out.println("现在是" + player3 + "进行操作" );
				System.out.println("根据提示输入数字来选择操作");
				System.out.println("-----------------------------------------------------------------------------------------------------------------");
				System.out.println("1.落子\t2.提子\t3.历史\t4.棋子数量\t5.显示棋盘\t6.查询棋子\t7跳过\t8.结束");
				System.out.println("-------------------------------------------------------------------------------------------------------------------");
				x= in.nextInt();
				switch(x) {
					case 1 :
						System.out.println("请输入需要落子的横坐标和纵坐标:");
						if(game.LuoPieceGo(in.nextInt(), in.nextInt(), player % 2 == 0 )) {//判断落子能否成功
							player ++ ;//操作权改变
						}
						game.showBoard("go");//显示棋盘
						break;
					case 2 :
						System.out.println("请输入需要提子的横坐标和纵坐标:");
						if(game.TiPieceGo(in.nextInt(), in.nextInt(), player % 2 == 0)) {//判断提子能否改变
							player++;//操作权改变
						}
						game.showBoard("go");//显示棋盘
						break;
					case 3:
						System.out.println("请输入需要查看的选手,选择1(1代表player1)或2(代表player2):");
						System.out.println("The history of " + game.getPlayer1() + " : " + game.getHistory(in.nextInt()==1));//显示历史
						break;
					case 4 :
						System.out.println("请输入需要查看的选手,选择1或2:");
						System.out.println(game.getSize(in.nextInt() == 1 ));//显示棋子的数目
						break;
					case 5 : 
						game.showBoard("go");//显示棋盘
						break ;
					case 6 :
						System.out.println("请输入查询的坐标x和y");
						Position position = new Position() ;
						position.setPosition(in.nextInt(), in.nextInt());
						Piece piece ;
						if((piece=game.getPiece(position))== null) {//判断位置(x,y)是否有棋子
							System.out.println("(" + position.getX() + "," + position.getY() +")" +"无棋子");//显示无棋子
							
						}else {
							piece = game.getPiece(position);
							String player4 = new String() ;
							if(piece.getOwner() == true )
								player4 = game.getPlayer1();
							else
								player4 = game.getPlayer2();
							System.out.println("棋子的player:" + player4 );
							System.out.println("棋子的name ：" + piece.getName());
							System.out.println("棋子的position："+piece.getPosition());
						}
						break ;
					case 7 :
	                    System.out.println("已经跳过该选手");
	                    game.addHistory("跳过", player % 2 == 0);//跳过
	                    player++;
	                    game.showBoard("go");//显示棋盘
	                    break;
					case 8 :
						in.close();
	                    game.showBoard("go");//显示棋盘
						System.exit(0);
					default:
						System.out.println("输入错误,请根据提示信息进行输入:");
						break; 	
						
				}
			}
		}
	}
}
