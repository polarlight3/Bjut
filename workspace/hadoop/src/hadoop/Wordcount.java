package hadoop;


import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Wordcount {

	public static class WordCountMapper extends Mapper<Object, Text, Text, IntWritable> {
		private final static IntWritable one = new IntWritable(1);
		private final Text word = new Text();
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			String[] words = value.toString().split(" ");
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
		String path = "hdfs://localhost:9000/test";
		String path2 = "hdfs://localhost:9000/he2";
		Configuration conf = new Configuration();
		Job job = new Job(conf,"word count");
		job.setJarByClass(Wordcount.class);
		job.setMapperClass(WordCountMapper.class);
		job.setCombinerClass(WordCountReducer.class);
		job.setReducerClass(WordCountReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, new Path(path));
		FileOutputFormat.setOutputPath(job, new Path(path2));
		//IOUtils.copyBytes(in, System.out, 1024, true);
		System.exit(job.waitForCompletion(true) ? 0 : 1);

	}

}
