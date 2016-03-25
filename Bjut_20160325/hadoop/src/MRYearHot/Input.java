package MRYearHot;
import java.io.IOException;
import java.util.Scanner;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class Input {
	private String ipath;
	private String opath;

	public Input() {
		this.ipath = "hdfs://localhost:9000/Mr/hot";
		this.opath = "hdfs://localhost:9000/output2";
	}

	public String getInPath() {
		return ipath;
	}

	public void setInPath(String path) {
		this.ipath = path;
	}
//输入input路径
	public void setInPath() {
		Scanner in = new Scanner(System.in);
		System.out.println("please input the inpath:");
		ipath = in.nextLine();
	}

	public String getOutpath() {
		return opath;
	}
	public void setOutpath(String opath) {
		this.opath = opath;
	}
//输入output路径
	public void setOutpath() {
		Scanner out = new Scanner(System.in);
		System.out.println("please input the outpath:");
		opath = out.nextLine();
	}

/**
 *该函数用于检验给出的String类型值所指向的路径是否存在
 *存在返回true，不存在返回false
 */

	public boolean checkPath(String val) {
		Configuration conf = new Configuration();
		FileSystem fs;
		Path path = new Path(val);
		try {
			fs = path.getFileSystem(conf);
			if (fs.exists(path)) {
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

/**
 *该函数用于检验给出的路径对象是否存在
 *存在返回true，不存在返回false
 */
	public boolean checkPath(Path path) {
		Configuration conf = new Configuration();
		try {
			FileSystem fs = path.getFileSystem(conf);
			if (fs.exists(path)) {
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}



/**
 *该函数用于删除String类型对象值所指向的路径
 */
	public void deletePath(String val) {
		Configuration conf = new Configuration();
		Path path = new Path(val);
		FileSystem fs;
		try {
			fs = path.getFileSystem(conf);
			fs.delete(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
/**
 *该函数用于删除Path对象所指向的路径
 */
	public void deletePath(Path path) {
		Configuration conf = new Configuration();
		FileSystem fs;
		try {
			fs = path.getFileSystem(conf);
			fs.delete(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

/**
 *该函数用于判断给出的输入路径是否存在以及输出路径是否冲突
 *如果输入路径不存在，则提示重新输入路径知道路径可用
 *如果输出路径存在，则提示是否覆盖文件或重新输入路径知道路径可用
 */
	public void isAvailable() {
		Scanner s = new Scanner(System.in);
		while (!checkPath(ipath)) {
			System.out.println("Directory don't exists, please input available directory!");
			setInPath();
		}
		while (checkPath(opath)) {
			System.out.println("Output directory already exists!Do you want to cover the file?(yes/no)");
			String lead = s.nextLine();
			if (lead.equals("no")) {
				setOutpath();
			} else {
				deletePath(opath);
			}
		}
		s.close();
		System.out.println("Directories are available!");
	}
}