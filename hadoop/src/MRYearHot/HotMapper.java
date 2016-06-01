package MRYearHot;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class HotMapper extends Mapper<LongWritable, Text, KeyPair, Text> {
	public static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/**
 *map函数对文件按行处理，切成一个个的块并规整数据
 *处理生成KeyPair类型的键后与行一起生成键值对输出
 */
	protected void map(LongWritable key, Text value, Context context) 
			throws IOException, InterruptedException {
		String line = value.toString();
		String[] val = line.split("\t");
		if (val.length == 2) {
			try {
				Date date = SDF.parse(val[0]);
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				int year = c.get(1);
				String hot = val[1].substring(0, val[1].indexOf("c"));
				KeyPair k = new KeyPair();
				k.setYear(year);
				k.setHot(Integer.parseInt(hot));
				context.write(k, value);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		else{
			System.out.println("Row Error!");
		}
	}
}