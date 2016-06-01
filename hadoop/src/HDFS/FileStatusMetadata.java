package HDFS;

import java.io.IOException;
import java.net.URI;
import java.sql.Timestamp;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class FileStatusMetadata {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String str1 = "hdfs://localhost:9000/test";
		String str2 = "hdfs://localhost:9000/input";

		Configuration conf = new Configuration();
		
		conf.set("hadoop.job.ugi", "kobe");
		System.out.println("Test 1: LOOK FOR INFO OF HDFS FILES");
		FileSystem fs = FileSystem.get(URI.create(str1), conf);
		FileStatus fileStatus = fs.getFileStatus(new Path(str1));
		if(fileStatus.isDir() == false){
			System.out.println("This is a file!");
		}
		System.out.println("The File Path: " + fileStatus.getPath());
		System.out.println("The File Length: " + fileStatus.getLen());
		System.out.println("The Date of alter: " + new Timestamp(fileStatus.getModificationTime()).toString());
		System.out.println("The Access Time: " + new Timestamp(fileStatus.getAccessTime()).toString());
		System.out.println("The NUMBER OF REPLICATION: " + fileStatus.getReplication());
		System.out.println("THE SIZE OF FILE: " + fileStatus.getBlockSize());
		System.out.println("THE OWNER OF FILE: " + fileStatus.getOwner());
		System.out.println("THE GROUP OF FILE: " + fileStatus.getGroup());
		System.out.println("THE PERMISSION OF FILE: " + fileStatus.getPermission().toString());
		System.out.println();
		
		System.out.println("Test 2: LOOK FOR INFO OF HDFS DIRECTION");
		String dirUri = str2;
		FileSystem dirfs = FileSystem.get(URI.create(dirUri), conf);
		FileStatus dirStatus = dirfs.getFileStatus(new Path(dirUri));
		if (dirStatus.isDir() == true) {
			System.out.println("THIS IS A DIR");
		}
		System.out.println("The DIRECITON Path: " + dirStatus.getPath());
		System.out.println("The DIRECITON Length: " + dirStatus.getLen());
		System.out.println("The Date of alter: " + new Timestamp(dirStatus.getModificationTime()).toString());
		System.out.println("The Access Time: " + new Timestamp(dirStatus.getAccessTime()).toString());
		System.out.println("The NUMBER OF REPLICATION: " + dirStatus.getReplication());
		System.out.println("THE SIZE OF DIRECITON: " + dirStatus.getBlockSize());
		System.out.println("THE OWNER OF DIRECITON: " + dirStatus.getOwner());
		System.out.println("THE GROUP OF DIRECITON: " + dirStatus.getGroup());
		System.out.println("THE PERMISSION OF DIRECITON: " + dirStatus.getPermission().toString());
		System.out.println("THIS DIRECITON INCLUDE: ");
		for (FileStatus fstatus : dirfs.listStatus(new Path(dirUri))) {
			System.out.println(fstatus.getPath());
		}
		System.out.println();
	}

}
