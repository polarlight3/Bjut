package hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hadoop on 5/31/16.
 */
public class BeerAndDiaper extends Configured implements Tool {
    public static void main(String[] args) throws Exception {
        ToolRunner.run(new BeerAndDiaper(), args);
        DIY.print();
    }
    @Override
    public int run(String[] strings) throws Exception {
        Path path = new Path("hdfs://localhost:9000/good");
        Path pathout = new Path("hdfs://localhost:9000/out");
        Configuration conf = getConf();
        FileSystem fs = pathout.getFileSystem(conf);
        fs.delete(pathout, true);
        Job job = new Job(conf);
        job.setJarByClass(getClass());
        job.setMapperClass(BeerAndDiaperMap.class);
        job.setReducerClass(BeerAndDiaperReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, path);
        FileOutputFormat.setOutputPath(job, new Path("hdfs://localhost:9000/out"));
        job.waitForCompletion(true);
        return 0;
    }

    public static class BeerAndDiaperMap extends Mapper<LongWritable, Text, Text, IntWritable>{
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] lists = value.toString().split("\\s+");
            String keyy;
            IntWritable one = new IntWritable(1);
            for (int i = 0 ; i < lists.length ; i++){
                for (int j = i + 1 ; j < lists.length ; j++){
                    keyy = lists[i] + "-" + lists[j];
                    context.write(new Text(keyy), one);
                }
            }
        }
    }

    public static class BeerAndDiaperReduce extends Reducer<Text, IntWritable, Text, IntWritable>{
        Set set = new HashSet();
        int max = 0;
        Text maxProduct = new Text();
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            String[] strs = key.toString().split("-");
            for (String  str : strs) {
                set.add(str);
            }
            int sum = 0;
            for (IntWritable t : values)
                sum += t.get();
            if(sum > max){
                max = sum ;
                maxProduct.set(key);
            }
        }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            context.write(new Text("The num of All goods: "), new IntWritable(set.size()));
            context.write(new Text(maxProduct + "is most close, the num is : "), new IntWritable(max));
        }
    }
}
