package hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * Created by hadoop on 6/1/16.
 */
public class UserRecordReader extends RecordReader<NullWritable, Text> {
    private FileSplit split;
    private Configuration conf;
    private Text value;
    private boolean flag;

    @Override
    public void initialize(InputSplit inputSplit, TaskAttemptContext context) throws IOException, InterruptedException {
        this.split = (FileSplit)inputSplit;
        this.conf = context.getConfiguration();
    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        if(!flag){
            Path file = split.getPath();
            byte[] fileBuffer = new byte[(int)split.getLength()];
            FileSystem fs = file.getFileSystem(conf);
            FSDataInputStream input = fs.open(file);
            input.read(fileBuffer);
            String str = new String(fileBuffer);
            value = new Text(str+"liu");
            flag = true;
            return true;
        }
        return false;
    }

    @Override
    public NullWritable getCurrentKey() throws IOException, InterruptedException {
        return NullWritable.get();
    }

    @Override
    public Text getCurrentValue() throws IOException, InterruptedException {
        return value;
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        return 0;
    }

    @Override
    public void close() throws IOException {

    }
}
