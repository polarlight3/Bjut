package Join;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Tnba {
	
	public static class JoinMapper extends Mapper<LongWritable, Text, LongWritable, Text>{
		public void map(LongWritable key, Text value,Context context) throws IOException, InterruptedException {
			String[] str = value.toString().split(" ");
			if (str.length == 2) //
				context.write(new LongWritable(Integer.parseInt(str[0])), new Text(str[1]));
		}
	}
	
	public static class JoinReducer extends Reducer<LongWritable, Text, LongWritable, Text>{
		public void reduce(LongWritable key, Iterable<Text> value,Context context) throws IOException, InterruptedException {
			String txt = "";
			for (Text text : value) {
				txt += text + "\t";
			}
			context.write(key, new Text(txt));
		}
	}
	
	public static void main(String[] args) throws Exception {
		String path = "hdfs://localhost:9000/Join";
		String path1 = "hdfs://localhost:9000/output";
		Configuration conf = new Configuration();
		Job job = new Job(conf,"join");
		job.setJarByClass(Tnba.class);
		job.setMapperClass(JoinMapper.class);
		job.setReducerClass(JoinReducer.class);
		job.setOutputKeyClass(LongWritable.class);
		job.setOutputValueClass(Text.class);
		FileInputFormat.addInputPath(job, new Path(path));
		FileOutputFormat.setOutputPath(job, new Path(path1));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
