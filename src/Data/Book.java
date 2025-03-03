package Data;

import java.util.Scanner;

public class Book {

    private String title;
    private String author;
    private Integer publicationYear;
    
    // Costruttore
    public Book(String title, String author, Integer publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }
    
    // Getters e Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    
    public Integer getPublicationYear() { return publicationYear; }
    public void setPublicationYear(Integer publicationYear) { this.publicationYear = publicationYear; }
    
    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Year: " + publicationYear;
    }
    
    // --- Metodi CRUD statici per gestire un array dinamico di Book ---
    
    private static Book[] books = new Book[1];
    private static int count = 0;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        
        while (!exit) {
            menu();
            String scelta = scanner.nextLine();
            
            switch (scelta) {
                case "1":
                    add(scanner);
                    break;
                case "2":
                    list();
                    break;
                case "3":
                    find(scanner);
                    break;
                case "4":
                    update(scanner);
                    break;
                case "5":
                    delete(scanner);
                    break;
                case "6":
                    System.out.println("\nTermine programma!\n");
                    exit = true;
                    break;
                default:
                    System.out.println("\nScelta non valida!\n");
                    break;
            }
        }
        scanner.close();
    }
    
    // Mostra il menu deletele operazioni
    private static void menu() {
        System.out.println("\n******** Menu ********\n");
        System.out.println("1. Aggiungi Book");
        System.out.println("2. Elenca Books");
        System.out.println("3. Cerca Book");
        System.out.println("4. Aggiorna Book");
        System.out.println("5. Elimina Book");
        System.out.println("6. Termine programma");
        System.out.println("\n======================\n");
    }
    
    // Aggiunge un nuovo Book all'array (espande l'array se necessario)
    private static void add(Scanner scanner) {
        // Se l'array è pieno, lo espandiamo raddoppiando la sua dimensione
        if (count == books.length) {
            Book[] newBooks = new Book[books.length * 2];
            System.arraycopy(books, 0, newBooks, 0, books.length);
            books = newBooks;
        }
        
        Book newBook = create(scanner);
        if (newBook == null) {
            System.out.println("Operazione annullata: Book già esistente!");
            return;
        }
        books[count] = newBook;
        count++;
        System.out.println("Nuovo Book \"" + newBook.getTitle() + "\" aggiunto con successo!");
    }
    
    // Crea un nuovo Book inserendo i dati dall'utente e controllando duplicati
    private static Book create(Scanner scanner) {
        System.out.println("Inserisci il titolo delete Book:");
        String title = scanner.nextLine();
        System.out.println("Inserisci l'autore delete Book:");
        String author = scanner.nextLine();
        System.out.println("Inserisci l'anno di pubblicazione delete Book:");
        int year;
        try {
            year = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Anno non valido. Operazione annullata.");
            return null;
        }
        
        // Verifica duplicati: qui si controlla se esiste già un Book con gli stessi dati
        if (find(title, author, year) != null) {
            System.out.println("Book già esistente!");
            return null;
        }
        
        return new Book(title, author, year);
    }
    
    // Elenca tutti i Book presenti nell'array
    private static void list() {
        if (!exsist()) return;
        System.out.println("I Books inseriti sono:");
        for (int i = 0; i < count; i++) {
            System.out.println("\t" + (i + 1) + ": " + books[i]);
        }
    }
    
    // Cerca un Book (ricerca generica: titolo, autore o anno)
    private static void find(Scanner scanner) {
        if (!exsist()) return;
        System.out.println("Cerca Book per titolo, autore o anno:");
        String input = scanner.nextLine().toLowerCase();
        boolean trovato = false;
        for (int i = 0; i < count; i++) {
            Book b = books[i];
            if (b.getTitle().toLowerCase().contains(input) ||
                b.getAuthor().toLowerCase().contains(input) ||
                b.getPublicationYear().toString().contains(input)) {
                System.out.println((i + 1) + ": " + b);
                trovato = true;
            }
        }
        if (!trovato) {
            System.out.println("\nNessun Book trovato con questi valori.");
        }
    }
    
    // Metodo di ricerca (overload) che controlla se esiste un Book con i dati esatti
    private static Book find(String title, String author, int year) {
        for (int i = 0; i < count; i++) {
            Book b = books[i];
            if (b.getTitle().equalsIgnoreCase(title) &&
                b.getAuthor().equalsIgnoreCase(author) &&
                b.getPublicationYear() == year) {
                return b;
            }
        }
        return null;
    }
    
    // Aggiorna un Book specificando la posizione (1-based)
    private static void update(Scanner scanner) {
        if (!exsist()) return;
        System.out.println("Inserisci la posizione delete Book da aggiornare:");
        int pos;
        try {
            pos = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Inserisci un numero valido!");
            return;
        }
        if (pos < 1 || pos > count) {
            System.out.println("Posizione non valida!");
            return;
        }
        Book aggiornato = create(scanner);
        if (aggiornato == null) {
            System.out.println("Operazione annullata: Book già esistente con quei dati!");
            return;
        }
        books[pos - 1] = aggiornato;
        System.out.println("Book aggiornato con successo!");
    }
    
    // Elimina un Book dalla posizione specificata (1-based)
    private static void delete(Scanner scanner) {
        if (!exsist()) return;
        System.out.println("Inserisci la posizione delete Book da eliminare:");
        int pos;
        try {
            pos = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Inserisci un numero valido!");
            return;
        }
        if (pos < 1 || pos > count) {
            System.out.println("Posizione non valida!");
            return;
        }
        // Sposta a sinistra gli elementi successivi
        for (int i = pos - 1; i < count - 1; i++) {
            books[i] = books[i + 1];
        }
        books[count - 1] = null; // Aiuta il garbage collector
        count--;
        System.out.println("Book eliminato con successo!");
    }
    
    // Verifica se esistono Book nell'array
    private static boolean exsist() {
        if (count == 0) {
            System.out.println("\nNessun Book esistente!\n");
            return false;
        }
        return true;
    }
}
