package hadoop;


import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class Wordcount {

	public static class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
		private final static IntWritable one = new IntWritable(1);
		private final Text word = new Text();
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String[] words = value.toString().split("\\s+");
			for (String str : words) {
				word.set(str);
				context.write(word, one);
			}
		}
	}

	public static class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
		private IntWritable result = new IntWritable();
		public void reduce(Text key, Iterable<IntWritable> values, Context context)
				throws IOException, InterruptedException {
			int total = 0;
			for (IntWritable val : values) {
				total += val.get();
			}
			result.set(total);
			context.write(key, result);
		}
	}

	public static void main(String[] args) throws Exception {
		String path = "hdfs://localhost:9000/ff";
		String path2 = "hdfs://localhost:9000/out";
		Configuration conf = new Configuration();
		Path pathout = new Path("hdfs://localhost:9000/out");
		FileSystem fs = pathout.getFileSystem(conf);
		fs.delete(pathout, true);
		Job job = new Job(conf);
		job.setJarByClass(Wordcount.class);
		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, new Path(path));
		FileOutputFormat.setOutputPath(job, new Path(path2));
//		job.setOutputFormatClass(TextOutputFormat.class);
		job.setOutputFormatClass(UserOutputFormat.class);
		System.exit(job.waitForCompletion(true) ? 0 : 1);

	}

}
