package Demo;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class QQReducer extends Reducer<Text, Text, Text, Text>{
	protected void reduce(Text key, Iterable<Text> value, Context context) 
			throws java.io.IOException ,InterruptedException {
		for (Text text : value) {
			if(!key.toString().equals(text.toString())){
				context.write(text, key);
			}
		}
		
	}
	
}
