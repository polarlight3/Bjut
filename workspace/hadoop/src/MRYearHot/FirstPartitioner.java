package MRYearHot;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class FirstPartitioner extends Partitioner<KeyPair, Text>{
	public static int group; 
/**
 *该函数用于将各个键值对分区，返回区号
 *由于均是对num取余得出，最后也只有num个区
 */
	public int getPartition(KeyPair key, Text value, int num) {	
		group = key.getYear()*127;
		return group % num;
	}
}