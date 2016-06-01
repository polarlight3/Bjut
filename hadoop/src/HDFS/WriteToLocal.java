package HDFS;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RawLocalFileSystem;


public class WriteToLocal { 

	public static void main(String[] args) throws IOException, URISyntaxException {
		// TODO Auto-generated method stub
		String str = "file:///home/hadoop/Desktop/test";
		Configuration conf = new Configuration();
		LocalFileSystem fs = new LocalFileSystem(new RawLocalFileSystem());
		fs.initialize(new URI(str), conf);
		OutputStream out = fs.create(new Path("file:///home/hadoop/Desktop/test"));
		for (int i = 0; i < 512*10; i++) {
			out.write(97);
		}
		out.close();
		Path file = fs.getChecksumFile(new Path("file:///home/hadoop/Desktop/test"));
		System.out.println(file.getName());
		fs.close();
	}

}
