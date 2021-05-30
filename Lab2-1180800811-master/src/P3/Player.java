package P3;

public class Player {
		private String Name  ; //玩家姓名
		// Abstraction Function:
		//	Represent a name of a Person that is not null 
		//
		//Representation invariant:
		//	Name != null 
		//
		//Safety from rep exposure:
		// name is immutable so there is no need for getName() to make defensive copy 
		// the field of name is private 
		/**
		 * 设置玩家的姓名
		 * @param Name   玩家姓名
		 */
		public Player(String Name) {//初始化
			this.Name = Name ;
		}
		
		/**
		 * 返回玩家的姓名
		 * @return  玩家的姓名
		 */
		public String getName() {//得到玩家的姓名
			return this.Name ;
		}
}
