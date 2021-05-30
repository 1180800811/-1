package debug;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class LowestPriceTest {
	// Partition for shoppingOffers()����
	//  ��Ʒ�������������ͨ���Ż������� �� ��Ʒ�����������һ���ֿ���ͨ���Żݹ��� ����Ʒ���������ȫ������ͨ���Żݹ���
	//
	//  �����쳣:* 1.�����6����Ʒ���ر��Ż�100�֡�
	//			* 2.����ÿ����Ʒ���������Ҫ����6����
	//			
	
	/*
	 * �����쳣
	//  ����:* 1.��Ʒ�����������֣��ر��Żݳ���100�֡�
	//			* 2.��Ʒ�������������6��
	*/
	@Test
	public void LowestPriceTest1(){
		List<Integer> price = new ArrayList<Integer>() ;
		for(int i = 0 ; i < 8 ; i++) {
			price.add(i) ;//��Ʒ��������6��
		}
		List<List<Integer>> special = new ArrayList<List<Integer>>() ;
		for(int i = 0 ; i < 103 ; i ++ ) {
			List<Integer> s1 = new ArrayList<Integer>() ;//�ر��Ż���������100��
			for(int j = 0 ; j < 9 ; j ++) {
				s1.add(j) ;
			}
			special.add(s1);
		}
		List<Integer> needs = new ArrayList<Integer>() ;
		for(int i = 0 ; i < 8 ; i++) {
			needs.add(i) ;//��Ʒ�����������������
		}
		try {
				new LowestPrice().shoppingOffers(price, new ArrayList<List<Integer>>() , new ArrayList<Integer>() );
		}catch(Exception e) {
			assertEquals("��Ʒ�������Ϊ6",e.getMessage());
		}
		try {
			new LowestPrice().shoppingOffers(new ArrayList<Integer>(), special , new ArrayList<Integer>() );
		}catch(Exception e) {
			assertEquals("�Żݷ������Ϊ100��",e.getMessage());
		}
		try {
			new LowestPrice().shoppingOffers(new ArrayList<Integer>(),new ArrayList<List<Integer>>() , needs);
		}catch(Exception e) {
			assertEquals("ÿ����Ʒ�Ĺ����������Ϊ6",e.getMessage());
		}

	}
	
	/*
	 * ����shoppingOffers()����
	 * ����:��Ʒ�������������ͨ���Ż�������
	 */
	@Test
	public void LowestPriceTest2() throws Exception {
		List<Integer> price = new ArrayList<Integer>() ;
		price.add(2) ;
		price.add(3);
		price.add(4);
		List<List<Integer>> special = new ArrayList<List<Integer>>() ;
		List<Integer> s1 = new ArrayList<Integer>() ;
		s1.add(3) ;
		s1.add(1);
		s1.add(0);
		s1.add(7);
		special.add(s1);
		List<Integer> s2 = new ArrayList<Integer>() ;
		s2.add(2) ;
		s2.add(4);
		s2.add(1);
		s2.add(9);
		special.add(s2);
		List<Integer> needs =new ArrayList<Integer>() ;
		needs.add(1);
		needs.add(2);
		needs.add(1);
//		��������Ϊ[2,3,4] ,[[3,1,0,7] ,[2,4,1,9]] , [1,2,1]
		assertEquals(12,new LowestPrice().shoppingOffers(price, special, needs));//����:��Ʒ�������������ͨ���Ż�������
	}
	
	/*
	 * ����shoppingOffers()����
	 * ����:��Ʒ���������һ������ͨ���Ż�������
	 */
	@Test
	public void LowestPriceTest3() throws Exception {
		List<Integer> price = new ArrayList<Integer>() ;
		price.add(2) ;
		price.add(3);
		price.add(4);
		List<List<Integer>> special = new ArrayList<List<Integer>>() ;
		List<Integer> s1 = new ArrayList<Integer>() ;
		s1.add(1) ;
		s1.add(1);
		s1.add(0);
		s1.add(4);
		special.add(s1);
		List<Integer> s2 = new ArrayList<Integer>() ;
		s2.add(2) ;
		s2.add(2);
		s2.add(1);
		s2.add(9);
		special.add(s2);
		List<Integer> needs =new ArrayList<Integer>() ;
		needs.add(1);
		needs.add(2);
		needs.add(1);
		//��������[2,3,4]��[[1,1,0,4]��[2,2,1,9]]��[1,2,1]�����11
		assertEquals(11,new LowestPrice().shoppingOffers(price, special, needs));//����:��Ʒ���������һ������ͨ���Ż�������
	}
	
	/*
	 * ����shoppingOffers()����
	 * ����:��Ʒ���������ȫ����ͨ���Ż�������
	 */
	@Test
	public void LowestPriceTest4() throws Exception {
		List<Integer> price = new ArrayList<Integer>() ;
		price.add(2) ;
		price.add(3);
		price.add(4);
		List<List<Integer>> special = new ArrayList<List<Integer>>() ;
		List<Integer> s1 = new ArrayList<Integer>() ;
		s1.add(1) ;
		s1.add(1);
		s1.add(3);
		s1.add(4);
		special.add(s1);
		List<Integer> s2 = new ArrayList<Integer>() ;
		s2.add(2) ;
		s2.add(2);
		s2.add(1);
		s2.add(9);
		special.add(s2);
		List<Integer> s3 = new ArrayList<Integer>() ;
		s3.add(1) ;
		s3.add(2);
		s3.add(1);
		s3.add(9);
		special.add(s3);
		List<Integer> needs =new ArrayList<Integer>() ;
		needs.add(4);
		needs.add(5);
		needs.add(5);
		//��������[2,3,4],[[1,1,3,4]��[2,2,1,9]��[1,2,1,9]]��[4,5,4] �����22
		assertEquals(22,new LowestPrice().shoppingOffers(price, special, needs));//����:��Ʒ���������һ������ͨ���Ż�������
	}
}
