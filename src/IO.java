import java.util.Scanner;

public class IO {
		
	private static boolean hasError = false;
	
	public static boolean getHasError() {
		return hasError;
	}
	
	public static void println(Object o) {
		System.out.println(o);
	}
	
	public static void print(Object o) {
		System.out.print(o);
	}

	public static String getString()
	{
		hasError = false;
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
	
	public static String getString(String msg) {
		println(msg);
		return getString();
	}
	
	
	public static int getInt() {
		hasError = false;
		int res = 0;
		String str = getString();		
		try 
		{
			res = Integer.parseInt(str);	
			hasError = false;
		}
		catch (NumberFormatException e) 
		{
			hasError = true;
		}
		
		if (res != 0) return res;
		
		return 0;
	}
	
	public static int getInt(String msg) {
		println(msg);
		return getInt();
	}
	
	public static double getDouble() {
		hasError = false;
		double res = 0;
		String datos = getString();
		datos = datos.replace(',', '.');
		try {
			res = Double.parseDouble(datos);
			hasError = false;
		} catch (NumberFormatException e) {
			hasError = true;
		}
		// tutto fatto... devo solo restituire il risultato
		return res;
	}
	
	public static double getDouble(String msg) {
		println(msg);
		return getDouble();
	}
	
	public static char getChar() {
		hasError = false;
		String datos = getString();
		if (datos.length() == 0) {
			hasError = true;
			return ' ';
		}
		return datos.charAt(0);
	}
	
	public static char getChar(String msg) {
		println(msg);
		return getChar();
	}
	
	public static boolean getBoolean() {
		hasError = false;
		String datos = getString();
		if (datos.equalsIgnoreCase("si") || datos.equalsIgnoreCase("s") || datos.equalsIgnoreCase("yes")
				|| datos.equalsIgnoreCase("y")) {
			return true;
		}
		if (datos.equalsIgnoreCase("no") || datos.equalsIgnoreCase("n")) {
			return false;
		}
		hasError = true;
		return false;
	}
	
	public static boolean getBoolean(String msg) {
		println(msg);
		return getBoolean();
	}
}


