package hadoop;

import org.apache.hadoop.conf.Configuration;
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
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

/**
 * Created by hadoop on 6/1/16.
 */
public class NewText {
    public static class WordMapper extends Mapper<NullWritable, Text, Text, Text> {

        protected void map(NullWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] words = value.toString().split("\\s+");
            for (int i = 1; i < words.length ; i++) {
                context.write(new Text(words[i]), new Text(words[0]));
            }
        }
    }

    public static class WordReducer extends Reducer<Text, Text, Text, Text> {

        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            StringBuffer sb = new StringBuffer();
            for (Text val: values) {
                sb.append(val.toString() + " ");
            }
            sb.deleteCharAt(sb.length() - 1);
            Text valuee = new Text(sb.toString());
            context.write(key, valuee);
        }
    }

    public static void main(String[] args) throws Exception {
        String path = "hdfs://localhost:9000/new";
        String path2 = "hdfs://localhost:9000/out";
        Configuration conf = new Configuration();
        Path pathout = new Path("hdfs://localhost:9000/out");
        FileSystem fs = pathout.getFileSystem(conf);
        fs.delete(pathout, true);
        Job job = new Job(conf);
        job.setJarByClass(Wordcount.class);
        job.setMapperClass(WordMapper.class);
        job.setReducerClass(WordReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job, new Path(path));
        FileOutputFormat.setOutputPath(job, new Path(path2));
//		job.setOutputFormatClass(TextOutputFormat.class);
        job.setInputFormatClass(UserInputFormat.class);
        job.setOutputFormatClass(UserOutputFormat.class);
        System.exit(job.waitForCompletion(true) ? 0 : 1);

    }
}
