package HDFS;

import java.io.*;
import java.net.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.log4j.Logger;

public class HDFSDataInputExample {
	static final Logger logger = Logger.getLogger(HDFSDataInputExample.class);
	static{
		URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		HDFSDataInputExample sample = new HDFSDataInputExample();
		String cmd = "get";
		String localPath = "/home/hadoop/Desktop/hellow";
		String hdfsPath = "hdfs://localhost:9000/test1";
		if (cmd.equals("create")) {
			sample.createFile(localPath, hdfsPath);
		}	
		else if(cmd.equals("get")){
			boolean print = false;
			sample.getFile(localPath, hdfsPath, print);
		}
	}
	
	public void createFile(String localPath, String hdfsPath) throws IOException{
		InputStream in = null;
		Configuration conf = new Configuration();
		FileSystem fileSystem = FileSystem.get(URI.create(hdfsPath), conf);
		FSDataOutputStream out = fileSystem.create(new Path(hdfsPath));
		in = new BufferedInputStream(new FileInputStream(new File(localPath)));
		IOUtils.copyBytes(in, out, 4096 , false);
		out.hsync();
		out.close();
		logger.info("create file in hdfs: " + hdfsPath);
		IOUtils.closeStream(in);
	}
	
	public void getFile(String localPath, String hdfsPath, boolean print) throws IOException{
		Configuration conf = new Configuration();
		FileSystem fileSystem = FileSystem.get(URI.create(hdfsPath), conf);
		FSDataInputStream in = null;
		OutputStream out = null;
		in = fileSystem.open(new Path(hdfsPath));
		out = new BufferedOutputStream(new FileOutputStream(new File(localPath)));
		IOUtils.copyBytes(in, out, 4096 , !print);
		logger.info("get file form hdfs to local: " + ", " + localPath );
		if(print){
			in.seek(0);
			IOUtils.copyBytes(in, System.out, 4069, true);
		}
		IOUtils.closeStream(out);
	}

}
