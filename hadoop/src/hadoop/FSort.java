package hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by hadoop on 5/30/16.
 */
public class FSort {
    public static class FSortMap extends Mapper<LongWritable, Text, MyPairWritable, NullWritable> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            FileSplit fileSplit = (FileSplit)context.getInputSplit();
            System.out.println(fileSplit.getPath().toString());
            String[] val = value.toString().split("\\s+");
            MyPairWritable mw = new MyPairWritable();
            if(val.length == 2){
                mw.set(new Text(val[0]), new IntWritable(Integer.parseInt(val[1])));
                context.write(mw, NullWritable.get());
            }
        }
    }

    public static class FSortReduce extends Reducer<MyPairWritable, NullWritable, MyPairWritable, NullWritable> {
        @Override
        protected void reduce(MyPairWritable key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
            context.write(key, NullWritable.get());
        }
    }

    public static class FSortComparator extends WritableComparator{
        public FSortComparator(){
            super(MyPairWritable.class, true);
        }
        @Override
        public int compare(WritableComparable a, WritableComparable b) {
            MyPairWritable m1 = (MyPairWritable)a;
            MyPairWritable m2 = (MyPairWritable)b;
            if(!m1.first.toString().equals(m2.first.toString()))
                return m1.first.toString().compareTo(m2.first.toString());
            else
                return m1.second.get() - m2.second.get();
        }
    }

    public static class FGroupComparator extends WritableComparator{
        public FGroupComparator(){
            super(MyPairWritable.class, true);
        }
        @Override
        public int compare(WritableComparable a, WritableComparable b) {
            MyPairWritable m1 = (MyPairWritable)a;
            MyPairWritable m2 = (MyPairWritable)b;
            return m1.first.toString().compareTo(m2.first.toString());
        }
    }

    public  static class FPartitioner extends Partitioner<MyPairWritable, NullWritable>{
        @Override
        public int getPartition(MyPairWritable o, NullWritable o2, int i) {
            if(o.first.toString().startsWith("aaa"))
                return 1;
            if(o.first.toString().startsWith("bbb"))
                return 2;
            if(o.first.toString().startsWith("ccc"))
                return 3;
            else
                return 0;
        }
    }

    public static void main(String[] args) throws Exception {
        Path path = new Path("hdfs://localhost:9000/tt");
        Path pathout = new Path("hdfs://localhost:9000/out");
        Configuration conf = new Configuration();
        FileSystem fs = pathout.getFileSystem(conf);
        fs.delete(pathout, true);
        Job job = new Job(conf);
        job.setJarByClass(FSort.class);
        job.setMapperClass(FSortMap.class);
        job.setReducerClass(FSortReduce.class);
        job.setOutputKeyClass(MyPairWritable.class);
        job.setOutputValueClass(NullWritable.class);
        job.setGroupingComparatorClass(FGroupComparator.class);
        job.setSortComparatorClass(FSortComparator.class);
        job.setPartitionerClass(FPartitioner.class);
//        job.setNumReduceTasks(4);
        FileInputFormat.addInputPath(job, path);
        FileOutputFormat.setOutputPath(job, new Path("hdfs://localhost:9000/out"));
        job.waitForCompletion(true);
        DIY.print();

    }
}
