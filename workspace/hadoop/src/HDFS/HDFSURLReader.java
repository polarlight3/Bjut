package HDFS;

import java.io.InputStream;
import java.net.URL;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;
public class HDFSURLReader {
	static{
		URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String str1 = "hdfs://localhost:9000/test";
		InputStream inputStream = new URL(str1).openStream();
		IOUtils.copyBytes(inputStream, System.out, 1024, false);
	}
}
