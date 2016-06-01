package hadoop;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

public class Test{
	public static void main(String[] args) throws Exception {
		TestComparator testComparator = new TestComparator();
//		testComparator.show();
		MyPairWritable m1 = new MyPairWritable();
		m1.set(new Text("a"), new IntWritable(12));
		MyPairWritable m2 = new MyPairWritable();
		m2.set(new Text("b"), new IntWritable(24));
		System.out.println(testComparator.compare(m1, m2));
	}

}
