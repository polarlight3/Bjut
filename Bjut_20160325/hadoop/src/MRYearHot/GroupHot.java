package MRYearHot;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupHot extends WritableComparator{

	public GroupHot() {
		super(KeyPair.class, true);
	}
	
/**
 *该函数主要是用于分组而对进行两个对象年份的比较
 *相同年份的将存在一起
 */
public int compare(WritableComparable a, WritableComparable b) {
		int res;
		KeyPair o1 = (KeyPair) a;
		KeyPair o2 = (KeyPair) b;
		res = Integer.compare(o1.getYear(), o2.getYear());
		return res; 		
	}
}