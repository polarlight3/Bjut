package JavaTest;

/**
 * Created by hadoop on 5/12/16.
 */
public class Test {
    public static void main(String[] args){
        String frame = "<frame src='baidu.com'/>";
        int start = frame.indexOf("src=");
        frame = frame.substring(start);
        int end = frame.indexOf(" ");
        if (end == -1)
            end = frame.indexOf(">");
        String frameUrl = frame.substring(5, end - 1);
        System.out.println(start);
        System.out.println(end);
        System.out.println(frameUrl);
    }
}
