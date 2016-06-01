package MRYearHot;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.WritableComparable;

public class KeyPair implements WritableComparable<KeyPair> {
	private int year;
	private int hot;

	public KeyPair() {
		year = 0;
		hot = 0;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getHot() {
		return hot;
	}

	public void setHot(int hot) {
		this.hot = hot;
	}

	public void show() {
		System.out.print("Year: " + year);
		System.out.println("Temper: " + hot);
	}
/**
 * 函数用于判断两个KeyPair是否相等
 * 相等返回true，否则返回false
 */

	public boolean equals(KeyPair k) {
		if (k.year == this.year && k.hot == this.hot) {
			return true;
		}
		return false;
	}

/**
 *该函数用于将二进制的流数据反序列化
 *并按照KeyPair的格式保存数据
 */
	public void readFields(DataInput in) throws IOException {
		this.year = in.readInt();
		this.hot = in.readInt();
	}

/**
 *该函数用于将数据序列化
 */
	public void write(DataOutput out) throws IOException {
		out.writeInt(year);
		out.writeInt(hot);
	}

/**
 *该函数用于比较两个KeyPair类型变量
 *先比较时间，再比较温度
 */
	public int compareTo(KeyPair o) {
		int res = Integer.compare(year, o.year);
		if (res != 0) {
			return res;
		}
		return Integer.compare(hot, o.hot);
	}

	public String toString() {
		return year + "\t" + hot;
	}
/**
 *该函数主要用数据生成哈希码
 */
	public int hashCode() {
		int val = new Integer(year + hot).hashCode();
		return val;
	}
}