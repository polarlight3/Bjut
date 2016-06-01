package HDFS;

import java.io.IOException;
import java.net.URI;

import org.apache.zookeeper.common.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;

public class SeqWrite {
	private static final String[] data = {
			"a,b,c,d,e,f,g","h,i,j,k,l,m,n","o,p,q,r,s,t","u,v,w,x,y,z",
			"0,1,2,3,4","5,6,7,8,9"
	};
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String str = "hdfs://localhost:9000";
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(str), conf);
		Path path = new Path("test.seq");
		SequenceFile.Writer writer = null;
		IntWritable key = new IntWritable();
		Text value = new Text();
		try {
			writer = SequenceFile.createWriter(fs, conf, path, key.getClass(), value.getClass());
			for (int i = 0; i < 10000; i++) {
				key.set(i);
				value.set(SeqWrite.data[i % SeqWrite.data.length]);
				writer.append(key, value);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// TODO: handle finally clause
			IOUtils.closeStream(writer);
		}
	}

}
