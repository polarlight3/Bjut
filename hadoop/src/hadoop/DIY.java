package hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

/**
 * Created by hadoop on 5/28/16.
 */
public class DIY extends Configured implements Tool {
    public static class MyMap extends Mapper<LongWritable, Text, Text, IntWritable>{
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] strs = value.toString().split("\\s+");
            if(strs.length == 2){
                Text keyy = new Text(strs[0]);
                IntWritable valuee = new IntWritable(Integer.parseInt(strs[1]));
                context.write(keyy, valuee);
            }
        }
    }

    public static class MyReduce extends Reducer<Text, IntWritable, Text, Text>{
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            StringBuffer sb = new StringBuffer();
            sb.append("[");
            for (IntWritable it : values) {
                sb.append(it);
                sb.append("_");
            }
            sb.append("]");
            context.write(key, new Text(sb.toString()));
        }
    }

    public static class MyPartitioner extends Partitioner{

        @Override
        public int getPartition(Object key, Object value, int i) {
            if(key.toString().startsWith("aaa")){
                return 1;
            }else if(key.toString().startsWith("bbb")){
                return 2;
            }else if(key.toString().startsWith("ccc")){
                return 3;
            }else
                return 0;
        }
    }

    public static class MyComparater extends WritableComparator {
        public MyComparater(){
            super(Text.class, true);
        }
        @Override
        public int compare(WritableComparable a, WritableComparable b) {
            String t1 = a.toString();
            String t2 = b.toString();
            return t2.compareTo(t1);
        }
    }

    public static class MyGroupComparater extends WritableComparator {
        public MyGroupComparater(){
            super(Text.class, true);
        }
        @Override
        public int compare(WritableComparable a, WritableComparable b) {
            String t1 = a.toString();
            String t2 = b.toString();
            if(t1.equals("aaa")||t1.equals("bbb"))
                t1 = "aaa";
            if(t1.equals("ccc")||t1.equals("ddd"))
                t1 = "ccc";
            if(t2.equals("aaa")||t2.equals("bbb"))
                t2 = "aaa";
            if(t2.equals("ccc")||t2.equals("ddd"))
                t2 = "ccc";
            return t1.compareTo(t2);
        }
    }

    public static void main(String[] args) throws Exception {
        ToolRunner.run(new DIY(), args);
        print();
    }

    @Override
    public int run(String[] strings) throws Exception {
        Configuration conf = getConf();
        Job job = new Job(conf);
        job.setJarByClass(getClass());
        Path path = new Path("hdfs://localhost:9000/out");
        FileSystem fs = path.getFileSystem(conf);
        fs.delete(path, true);
        FileInputFormat.addInputPath(job, new Path("hdfs://localhost:9000/tt"));
        FileOutputFormat.setOutputPath(job, new Path("hdfs://localhost:9000/out"));
        job.setMapperClass(MyMap.class);
        job.setReducerClass(MyReduce.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
//        job.setNumReduceTasks(4);
//        job.setPartitionerClass(MyPartitioner.class);
        job.setSortComparatorClass(MyComparater.class);
        job.setGroupingComparatorClass(MyGroupComparater.class);
        job.waitForCompletion(true);
        return 0;
    }

    public static void print() throws Exception{
        Path path = new Path("hdfs://localhost:9000/out/part-r-00000");
        Configuration conf  = new Configuration();
        FileSystem fs = path.getFileSystem(conf);
        FSDataInputStream fsin = fs.open(path);
        int length = 0;
        byte[] buff = new byte[128];
        while((length = fsin.read(buff, 0, 128)) != -1){
            System.out.println(new String(buff, 0, length));
        }
    }
}
