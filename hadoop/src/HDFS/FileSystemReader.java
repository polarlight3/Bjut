package HDFS;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class FileSystemReader {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String str1 = "hdfs://localhost:9000/test";
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(str1), conf);
		InputStream inputStream = fs.open(new Path(str1));
		IOUtils.copyBytes(inputStream, System.out, conf);
	}

}
