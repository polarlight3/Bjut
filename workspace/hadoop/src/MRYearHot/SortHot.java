package MRYearHot;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class SortHot extends WritableComparator{

	public SortHot() {
		super(KeyPair.class, true);
	}
	
/**
 *该函数主要是进行两个对象的的比较
 *这里先转换成KeyPair对象进行比较
 */
	public int compare(WritableComparable a, WritableComparable b) {
		KeyPair o1 = (KeyPair) a;
		KeyPair o2 = (KeyPair) b;
		int res = Integer.compare(o1.getYear(), o2.getYear());
		if(res != 0){
			return res;
		}
		return -Integer.compare(o1.getHot(), o2.getHot());		
	}
}