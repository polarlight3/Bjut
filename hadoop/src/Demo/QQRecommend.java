package Demo;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class QQRecommend {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String path1 = "hdfs://localhost:9000/tt";
		String path2 = "hdfs://localhost:9000/res";
		Configuration conf = new Configuration();
		Job job = new Job(conf, "QQ");
		job.setJarByClass(QQRecommend.class);
		job.setMapperClass(QQMapper.class);
		job.setReducerClass(QQReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		FileInputFormat.addInputPath(job, new Path(path1));
		FileOutputFormat.setOutputPath(job, new Path(path2));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
