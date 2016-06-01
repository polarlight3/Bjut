package hadoop;


import org.apache.commons.lang.ObjectUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

/**
 * Created by hadoop on 6/1/16.
 */
public class UserRecordWriter extends RecordWriter<Text, Text> {
    FileSystem fs;
    FSDataOutputStream fsout;

    public UserRecordWriter(TaskAttemptContext job){
        init(job);
    }

    public void init(TaskAttemptContext job){
        try {

            Configuration conf = job.getConfiguration();
            Path outpath = new Path("hdfs://localhost:9000/");
            Path outpath1 = new Path("hdfs://localhost:9000/lf");
            fs = outpath.getFileSystem(conf);
            fsout = fs.create(outpath1);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void writeObject(Object o) throws IOException {
        if(o instanceof Text) {
            Text to = (Text)o;
            fsout.write(to.getBytes(), 0, to.getLength());
        } else {
            fsout.write(o.toString().getBytes("UTF-8"));
        }

    }

    @Override
    public void write(Text key, Text value) throws IOException, InterruptedException {
        writeObject(key);
        writeObject(" ");
        writeObject(value);
        writeObject(":lf");
        writeObject("\r\n");
    }

    @Override
    public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        if (fsout != null)
            fsout.close();
    }
}
