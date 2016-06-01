package HDFS;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.MapFile;
import org.apache.hadoop.io.SequenceFile;

public class SequenceConvertToMapFile {
	private static final String[] DATA ={
			"one, two, buckle my shoe",
			"three, four, shut the door",
			"five, six, pick up sticks",
			"seven, eight, lay them straight",
			"nine, ten, a big fat hen"
	};
	public static class MapFileFixerTest{
		public void testMapFix(String sqFile) throws Exception  {
			String uri = sqFile;
			Configuration conf = new Configuration();
			FileSystem fs = FileSystem.get(URI.create(uri), conf);
			Path path = new Path(uri);
			Path mapData = new Path(path, MapFile.DATA_FILE_NAME);
			SequenceFile.Reader reader = new SequenceFile.Reader(fs, mapData, conf);
			Class keyClass = reader.getKeyClass();
			Class valueClass = reader.getValueClass();
			reader.close();
			long entries = MapFile.fix(fs, path, keyClass, valueClass, false, conf);
			System.out.printf("create MapFile from sequence about %d entries!", entries);
		}
	}
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		String str = "hdfs://localhost:9000/";
		SequenceConvertToMapFile.MapFileFixerTest fixer = new SequenceConvertToMapFile.MapFileFixerTest();
		fixer.testMapFix(str);
	}

}
