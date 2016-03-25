package JavaTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class YearTemper {
	public static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) throws IOException {
		int len = 0;
		String str = "/home/hadoop/Desktop/hot";
		String str2 = "/home/hadoop/Desktop/test";
		Temper[] t = new Temper[20];
		for (int j = 0; j < t.length; j++) {
			t[j] = new Temper();
		}
		File f = new File(str);
		File f2 = new File(str2);
		FileReader fre = new FileReader(f);
		BufferedReader bre = new BufferedReader(fre);
		FileWriter fw = new FileWriter(f2);
		BufferedWriter bw = new BufferedWriter(fw);

		while ((str = bre.readLine()) != null) {
			String[] val = str.split("\t");
			if (val.length == 2) {
				Date date;
				try {
					date = SDF.parse(val[0]);
					Calendar c = Calendar.getInstance();
					c.setTime(date);
					int year = c.get(1);
					String hot = val[1].substring(0, val[1].indexOf("c"));
					t[len].setYear(year);
					t[len].setHot(Integer.parseInt(hot));
					len++;
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}			
		}
		//按照温度由高到低排序
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len-1; j++) {
				if(t[j].getHot() < t[j+1].getHot()){
					Temper temp = t[j+1];
					t[j+1] = t[j]; 
					t[j] = temp;
				}
			}
		}
		//按照年份由低到高排序
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len-1; j++) {
				if(t[j].getYear() > t[j+1].getYear()){
					Temper temp = t[j+1];
					t[j+1] = t[j]; 
					t[j] = temp;
				}
			}
		}
		for (int j = 0; j < len; j++) {
			bw.write(t[j].getYear() + " " + t[j].getHot());
			bw.newLine();
		}
		bw.flush();
		bre.close();
		fre.close();
	}
}
