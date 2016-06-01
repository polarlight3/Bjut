package hadoop;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by hadoop on 6/1/16.
 */
public class UserOutputFormat extends FileOutputFormat<Text, Text> {

    @Override
    public RecordWriter<Text, Text> getRecordWriter(TaskAttemptContext job) throws IOException, InterruptedException {
        return new UserRecordWriter(job);
    }
}
