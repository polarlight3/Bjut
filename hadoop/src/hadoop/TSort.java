package hadoop;

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hadoop on 5/29/16.
 */
public class TSort {
    public static class SortMap extends Mapper<LongWritable, Text, Text, IntWritable> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] val = value.toString().split("\\s+");
            if(val.length == 2)
                context.write(new Text(val[0]), new IntWritable(Integer.parseInt(val[1])));
        }
    }

    public static class SortReduce extends Reducer<Text, IntWritable, Text, Text>{
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            IntWritable in = new IntWritable();
            if(values.iterator().hasNext())
                in.set(values.iterator().next().get());
            for (IntWritable it : values) {
                if(it.compareTo(in) == -1){
                    in.set(it.get());
                }
            }
            context.write(key, new Text(in.toString()));
        }
    }

    public static void main(String[] args) throws Exception {
        Path path = new Path("hdfs://localhost:9000/tt");
        Path pathout = new Path("hdfs://localhost:9000/out");
        Configuration conf = new Configuration();
        FileSystem fs = pathout.getFileSystem(conf);
        fs.delete(pathout, true);
        Job job = new Job(conf);
        job.setJarByClass(TSort.class);
        job.setMapperClass(SortMap.class);
        job.setReducerClass(SortReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        job.setMapOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, path);
        FileOutputFormat.setOutputPath(job, new Path("hdfs://localhost:9000/out"));
        job.waitForCompletion(true);
        DIY.print();

    }
}
