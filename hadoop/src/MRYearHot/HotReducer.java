package MRYearHot;
import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class HotReducer extends Reducer<KeyPair, Text, KeyPair, Text> {
/**
 *reduce函数对接收到的数据进行简单的整理
 *生成最终的键值对并写入文件
 */
	protected void reduce(KeyPair key, Iterable<Text> value, Context context) 
			throws IOException, InterruptedException {
		int count = 1;
		String v = null;
		for (Text val : value) {
			v = val.toString() + "\t" + count;
			context.write(key, new Text(v));
			count++;
		}
	}
}