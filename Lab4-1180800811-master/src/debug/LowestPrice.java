package debug;

import java.util.ArrayList;
import java.util.List;

/**
*在商店中，有一些商品要出售。 每个项目都有一个价格。
*但是，有一些特别优惠，特别优惠包括一个
*或其他具有销售价格的商品。
*为您提供每件商品的价格，一套特价和编号
*我们需要为每个项目购买。 工作是输出您拥有的最低价格
*完全按照给定的价格付款，您可以在其中进行最佳利用
*特别优惠。
*每个特价商品均以数组的形式表示，最后一个数字
*表示您需要为此特价支付的价格，其他数字
*表示购买此优惠可获得的特定商品数量。
*您可以根据需要多次使用任何特殊优惠。
*示例1：
*输入：[2,5]，[[3,0,5]，[1,2,10]]，[3,2]输出：14
*说明：
*有两种商品，A和B。它们的价格分别是2美元和5美元
* 分别。
*在特惠1中，您可以为3A和0B支付$ 5
*在特惠2中，您可以为1A和2B支付10美元。
*您需要购买3A和2B，因此您可能需要为$ 1A和2B支付10美元（特价）
*＃2），2A则为$ 4。
*示例2：
*输入：[2,3,4]，[[1,1,0,4]，[2,2,1,9]]，[1,2,1]输出：11
*说明：
* A的价格为$ 2，B的价格为$ 3，C的价格为$ 4。
*您可以为1A和1B支付$ 4，为2A，2B和1C支付$ 9。
*您需要购买1A，2B和1C，因此您可能要为1A和1B支付4美元（特价）
*＃1），1B为3美元，1C为4美元。
*虽然2A，2B和1C仅售$ 9，但您无法添加更多物品。
* 注意：
* 1.最多有6种商品，特别优惠100种。
* 2.对于每件物品，您最多需要购买6件。
* 3.不允许购买超出所需数量的物品，即使这样做可能会
*降低整体价格。
*
* ------------------------------------------------- --------------------------------------
*请在以下代码中调试并修复潜在的错误，并使其正确执行，
*健壮，并且完全符合上述要求。
*不要更改代码的初始逻辑。
* ------------------------------------------------- --------------------------------------
*/
public class LowestPrice {

	public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) throws Exception {
		if(price.size() > 6) {
			throw new Exception("商品数量最多为6");
		}
		if(special.size() > 100 ) {
			throw new Exception("优惠方案最多为100种");
		}
		
		for (int i = 0 ; i < needs.size() ; i ++) {
			if(needs.get(i) > 6) {
				throw new Exception("每件商品的购买数量最多为6");
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
