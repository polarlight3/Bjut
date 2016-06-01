package hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * Created by hadoop on 5/30/16.
 */
public class TestComparator extends WritableComparator {
//    public TestComparator(){
//        super(IntWritable.class,true);
//    }
    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        MyPairWritable m1 = (MyPairWritable)a;
        MyPairWritable m2 = (MyPairWritable)b;
        if(!m1.first.toString().equals(m2.first.toString()))
            return m1.first.toString().compareTo(m2.first.toString());
        else
            return m1.second.get() - m2.second.get();
    }

    public void show(){
        System.out.println("hello world!");
    }
}
