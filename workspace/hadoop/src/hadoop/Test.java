package hadoop;

public class Test {
	static String f = "hello";
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String a = "hello2"; 
        final String b = "hello";
        String d = "hello";
        String c = b + 2; 
        String ff = f + 2;
        System.out.println(ff == a);
        String e = d + 2;
        System.out.println("== :");
        System.out.println((a == c));
        System.out.println((a == e));
        System.out.println("equal :");
        System.out.println((a.equals(c)));
        System.out.println((a.equals(e)));
	}
}
