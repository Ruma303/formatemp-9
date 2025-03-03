import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;
		int input;
		
		while (true) {
			
			input = IO.getInt("Inserisci un numero oppure -1 per uscire: ");
				
			if (IO.getHasError()) {
				IO.println("Errore!");
				return;
			}
			
			IO.println("Il numero inserito è: " + input);
			
            if (input == -1) {
                exit = true;
                IO.println("Alla prossima!");
                break;
            }
		}
	}
}
