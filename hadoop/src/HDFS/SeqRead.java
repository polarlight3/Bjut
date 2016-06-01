package HDFS;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.util.ReflectionUtils;
import org.apache.zookeeper.common.IOUtils;

public class SeqRead {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String str = "hdfs://localhost:9000";
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(str), conf);
		Path path = new Path("test.seq");
		SequenceFile.Reader reader = null;
		try {
			reader = new SequenceFile.Reader(fs, path, conf);
			Writable key = (Writable)ReflectionUtils.newInstance(reader.getKeyClass(), conf);
			Writable value = (Writable)ReflectionUtils.newInstance(reader.getValueClass(), conf);
			while (reader.next(key, value)) {
				System.out.println(key + "\t" + value);				
			}
		} finally {
			// TODO: handle finally clause
			IOUtils.closeStream(reader);
		}
	}

}
