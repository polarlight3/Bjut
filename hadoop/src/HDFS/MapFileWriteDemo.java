package HDFS;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.MapFile;
import org.apache.hadoop.io.Text;
import org.apache.zookeeper.common.IOUtils;

public class MapFileWriteDemo {
	private static final String[] DATA ={
			"one, two, buckle my shoe",
			"three, four, shut the door",
			"five, six, pick up sticks",
			"seven, eight, lay them straight",
			"nine, ten, a big fat hen"
	};
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String uri = "hdfs://localhost:9000/";
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(uri), conf);
		IntWritable key = new IntWritable();
		Text value = new Text();
		MapFile.Writer writer = null;
		try {
			writer = new MapFile.Writer(conf, fs, uri, key.getClass(), value.getClass());
			for (int i = 0; i < 1024; i++) {
				key.set(i + 1);
				value.set(DATA[i % DATA.length]);
				writer.append(key, value);
			}
		} finally {
			// TODO: handle finally clause
			IOUtils.closeStream(writer);
		}
	}

}
