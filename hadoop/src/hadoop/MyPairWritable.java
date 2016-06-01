package hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by hadoop on 5/30/16.
 */
public class MyPairWritable implements WritableComparable<MyPairWritable>{
    Text first;
    IntWritable second;
    public void set(Text f, IntWritable s){
        first = f;
        second = s;
    }

    @Override
    public int compareTo(MyPairWritable o) {
        if(this.first != o.first){
            return this.first.toString().compareTo(o.first.toString());
        }else if(this.second != o.second){
            return this.second.get() - o.second.get();
        }else
            return 0;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(first.toString());
        out.writeInt(second.get());
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        first = new Text(in.readUTF());
        second = new IntWritable(in.readInt());
    }

    @Override
    public String toString() {
        return first.toString() + " " + second;
    }

    @Override
    public boolean equals(Object obj) {
        MyPairWritable o = (MyPairWritable)obj;
        return this.first.toString().equals(o.first.toString()) && this.second.get() == o.second.get();
    }

    @Override
    public int hashCode() {
        return first.hashCode()*100 + second.hashCode();
    }
}
