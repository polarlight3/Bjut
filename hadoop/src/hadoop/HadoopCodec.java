package hadoop;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.util.ReflectionUtils;

public class HadoopCodec {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String inputFile = "/home/hadoop/Desktop/test";
		String outputFolder = "hdfs://localhost:9000/";
		Configuration conf = new Configuration();
		conf.set("hadoop.job.ugi", "hadoop-user, hadoop-user");
		long gzipTime = copyAndZipFile(conf, inputFile, outputFolder, "org.apache.hadoop.io.compress.GzipCodec", "gz");
		long bzip2Time = copyAndZipFile(conf, inputFile, outputFolder, "org.apache.hadoop.io.compress.BZip2Codec", "bz2");
		long deflateTime = copyAndZipFile(conf, inputFile, outputFolder, "org.apache.hadoop.io.compress.DefaultCodec", "deflate");
		System.out.println("The name of file that been compressed:" + inputFile);
		System.out.println("Time of gzip: " + gzipTime);
		System.out.println("Time of bzip2: " + bzip2Time);
		System.out.println("Time of defalte: " + deflateTime);
	}

	private static long copyAndZipFile(Configuration conf, String inputFile, String outputFolder, String codeClassName, String suffixName) throws Exception {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();
		InputStream in = new BufferedInputStream(new FileInputStream(inputFile));
		String baseName = inputFile;
		String outputFile = outputFolder + baseName + "." + suffixName;
		FileSystem fs = FileSystem.get(URI.create(outputFile), conf);
		CompressionCodec codec = (CompressionCodec)ReflectionUtils.newInstance(Class.forName(codeClassName), conf);
		OutputStream out = codec.createOutputStream(fs.create(new Path(outputFile)));
		try {
			IOUtils.copyBytes(in, out, conf);
		} finally {
			// TODO: handle finally clause
			IOUtils.closeStream(in);
			IOUtils.closeStream(out);
		}
		long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}

}
