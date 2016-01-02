package MRYearHot;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class RunJob {

	public static void run() throws ClassNotFoundException, InterruptedException {
		Input in = new Input();
		in.isAvailable();
		Path inpath = new Path(in.getInPath());
		Path outpath = new Path(in.getOutpath());
		Configuration conf = new Configuration();
		try {
			Job job;
			job = new Job(conf, "hot");
			job.setJarByClass(RunJob.class);
			job.setMapperClass(HotMapper.class);
			job.setReducerClass(HotReducer.class);
			job.setNumReduceTasks(1);
			job.setPartitionerClass(FirstPartitioner.class);
			job.setSortComparatorClass(SortHot.class);
			job.setGroupingComparatorClass(GroupHot.class);
			job.setMapOutputKeyClass(KeyPair.class);
			job.setMapOutputValueClass(Text.class);
			FileInputFormat.addInputPath(job, inpath);
			FileOutputFormat.setOutputPath(job, outpath);
			System.exit(job.waitForCompletion(true) ? 0 : 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		run();
	}
}
