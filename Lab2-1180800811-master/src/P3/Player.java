package P3;

public class Player {
		private String Name  ; //�������
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
		 * ������ҵ�����
		 * @param Name   �������
		 */
		public Player(String Name) {//��ʼ��
			this.Name = Name ;
		}
		
		/**
		 * ������ҵ�����
		 * @return  ��ҵ�����
		 */
		public String getName() {//�õ���ҵ�����
			return this.Name ;
		}
}
