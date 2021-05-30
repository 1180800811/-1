package debug;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class LowestPriceTest {
	// Partition for shoppingOffers()方法
	//  物品所需的数量不能通过优惠来购买 ， 物品的所需的数量一部分可以通过优惠购买 ，物品所需的数量全部可以通过优惠购买。
	//
	//  测试异常:* 1.最多有6种商品，特别优惠100种。
	//			* 2.对于每件物品，您最多需要购买6件。
	//			
	
	/*
	 * 测试异常
	//  覆盖:* 1.商品数量超过六种，特别优惠超过100种。
	//			* 2.物品所需的数量超过6件
	*/
	@Test
	public void LowestPriceTest1(){
		List<Integer> price = new ArrayList<Integer>() ;
		for(int i = 0 ; i < 8 ; i++) {
			price.add(i) ;//商品数量超过6件
		}
		List<List<Integer>> special = new ArrayList<List<Integer>>() ;
		for(int i = 0 ; i < 103 ; i ++ ) {
			List<Integer> s1 = new ArrayList<Integer>() ;//特别优惠数量超过100种
			for(int j = 0 ; j < 9 ; j ++) {
				s1.add(j) ;
			}
			special.add(s1);
		}
		List<Integer> needs = new ArrayList<Integer>() ;
		for(int i = 0 ; i < 8 ; i++) {
			needs.add(i) ;//物品所需的数量超过六件
		}
		try {
				new LowestPrice().shoppingOffers(price, new ArrayList<List<Integer>>() , new ArrayList<Integer>() );
		}catch(Exception e) {
			assertEquals("商品数量最多为6",e.getMessage());
		}
		try {
			new LowestPrice().shoppingOffers(new ArrayList<Integer>(), special , new ArrayList<Integer>() );
		}catch(Exception e) {
			assertEquals("优惠方案最多为100种",e.getMessage());
		}
		try {
			new LowestPrice().shoppingOffers(new ArrayList<Integer>(),new ArrayList<List<Integer>>() , needs);
		}catch(Exception e) {
			assertEquals("每件商品的购买数量最多为6",e.getMessage());
		}

	}
	
	/*
	 * 测试shoppingOffers()方法
	 * 覆盖:物品所需的数量不能通过优惠来购买
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
//		测试用例为[2,3,4] ,[[3,1,0,7] ,[2,4,1,9]] , [1,2,1]
		assertEquals(12,new LowestPrice().shoppingOffers(price, special, needs));//覆盖:物品所需的数量不能通过优惠来购买
	}
	
	/*
	 * 测试shoppingOffers()方法
	 * 覆盖:物品所需的数量一部分能通过优惠来购买
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
		//测试用例[2,3,4]，[[1,1,0,4]，[2,2,1,9]]，[1,2,1]输出：11
		assertEquals(11,new LowestPrice().shoppingOffers(price, special, needs));//覆盖:物品所需的数量一部分能通过优惠来购买
	}
	
	/*
	 * 测试shoppingOffers()方法
	 * 覆盖:物品所需的数量全部能通过优惠来购买
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
		//测试用例[2,3,4],[[1,1,3,4]，[2,2,1,9]，[1,2,1,9]]，[4,5,4] 输出：22
		assertEquals(22,new LowestPrice().shoppingOffers(price, special, needs));//覆盖:物品所需的数量一部分能通过优惠来购买
	}
}
