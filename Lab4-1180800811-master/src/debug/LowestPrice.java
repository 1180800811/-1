package debug;

import java.util.ArrayList;
import java.util.List;

/**
*���̵��У���һЩ��ƷҪ���ۡ� ÿ����Ŀ����һ���۸�
*���ǣ���һЩ�ر��Żݣ��ر��Żݰ���һ��
*�������������ۼ۸����Ʒ��
*Ϊ���ṩÿ����Ʒ�ļ۸�һ���ؼۺͱ��
*������ҪΪÿ����Ŀ���� �����������ӵ�е���ͼ۸�
*��ȫ���ո����ļ۸񸶿�����������н����������
*�ر��Żݡ�
*ÿ���ؼ���Ʒ�����������ʽ��ʾ�����һ������
*��ʾ����ҪΪ���ؼ�֧���ļ۸���������
*��ʾ������Żݿɻ�õ��ض���Ʒ������
*�����Ը�����Ҫ���ʹ���κ������Żݡ�
*ʾ��1��
*���룺[2,5]��[[3,0,5]��[1,2,10]]��[3,2]�����14
*˵����
*��������Ʒ��A��B�����ǵļ۸�ֱ���2��Ԫ��5��Ԫ
* �ֱ�
*���ػ�1�У�������Ϊ3A��0B֧��$ 5
*���ػ�2�У�������Ϊ1A��2B֧��10��Ԫ��
*����Ҫ����3A��2B�������������ҪΪ$ 1A��2B֧��10��Ԫ���ؼۣ�
*��2����2A��Ϊ$ 4��
*ʾ��2��
*���룺[2,3,4]��[[1,1,0,4]��[2,2,1,9]]��[1,2,1]�����11
*˵����
* A�ļ۸�Ϊ$ 2��B�ļ۸�Ϊ$ 3��C�ļ۸�Ϊ$ 4��
*������Ϊ1A��1B֧��$ 4��Ϊ2A��2B��1C֧��$ 9��
*����Ҫ����1A��2B��1C�����������ҪΪ1A��1B֧��4��Ԫ���ؼۣ�
*��1����1BΪ3��Ԫ��1CΪ4��Ԫ��
*��Ȼ2A��2B��1C����$ 9�������޷���Ӹ�����Ʒ��
* ע�⣺
* 1.�����6����Ʒ���ر��Ż�100�֡�
* 2.����ÿ����Ʒ���������Ҫ����6����
* 3.�������򳬳�������������Ʒ����ʹ���������ܻ�
*��������۸�
*
* ------------------------------------------------- --------------------------------------
*�������´����е��Բ��޸�Ǳ�ڵĴ��󣬲�ʹ����ȷִ�У�
*��׳��������ȫ��������Ҫ��
*��Ҫ���Ĵ���ĳ�ʼ�߼���
* ------------------------------------------------- --------------------------------------
*/
public class LowestPrice {

	public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) throws Exception {
		if(price.size() > 6) {
			throw new Exception("��Ʒ�������Ϊ6");
		}
		if(special.size() > 100 ) {
			throw new Exception("�Żݷ������Ϊ100��");
		}
		
		for (int i = 0 ; i < needs.size() ; i ++) {
			if(needs.get(i) > 6) {
				throw new Exception("ÿ����Ʒ�Ĺ����������Ϊ6");
			}
		}
		return shopping(price, special, needs);
	}

	public int shopping(List<Integer> price, List<List<Integer>> special, List<Integer> needs){
		

		int j = 0, res = dot(needs, price);
		for (List<Integer> s : special) {
			List<Integer> clone = new ArrayList<>(needs);
			for (j = 0; j < needs.size(); j++) {
				int diff = clone.get(j) - s.get(j);
				if (diff < 0)
					break ;
				clone.set(j, diff);
			}
			if (j == needs.size())
				res = Math.min(res, s.get(j) + shopping(price, special, clone));
		}
		return res;
	}

	public int dot(List<Integer> a, List<Integer> b) {
		int sum = 0;
		for (int i = 0; i < a.size(); i++) {
			sum += a.get(i) * b.get(i);
		}
		return sum;
	}
	

}
