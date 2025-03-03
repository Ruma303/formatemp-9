package MyClasses;

import java.io.IO;

public class DynamicArray {

	private Object[] elements;

	private int count = 0;

	public DynamicArray() {
		elements = new Object[this.count];
	}

	public DynamicArray(Integer i) {
		elements = new Object[this.count];
	}

	public DynamicArray(Double d) {
		elements = new Object[this.count];
	}

	public DynamicArray(String s) {
		elements = new Object[this.count];
	}

	public int size() {
		return this.elements.length;
	}

	// Aggiunge un elemento all'array ridimensionandolo se necessario
	public void add(Object o) {
		if (count == 0) {
			elements = new Object[1];
		}
		
		if (count == elements.length) {
			int newSize = elements.length * 2;
			Object[] newElements = new Object[newSize];

			// Copia elementi esistenti
			System.arraycopy(elements, 0, newElements, 0, elements.length);
			elements = newElements;
		}

		elements[count] = o;
		count++;
	}

	// Modifica l'elemento alla posizione richiesta
	public void set(int position, Object o) {
		int index = position - 1; // Convertiamo da posizione a indice
		if (isValid(index)) {
			System.out.println("Modifica elemento in posizione " + position + "...");
			elements[index] = o;
		}
	}

	// Rimuove un elemento dalla posizione richiesta
	public void remove(int position) {
		int index = position - 1;
		if (!isValid(index))
			return;

		System.out.println("Elimino l'elemento in posizione " + position + "...");

		for (int i = index; i < count - 1; i++) {
			elements[i] = elements[i + 1]; // Shift a sinistra
		}
		elements[count - 1] = null; // Rimuoviamo l'ultimo elemento per evitare residui
		count--;

		// Se il numero di elementi è meno della metà dello spazio allocato,
		// ridimensioniamo
		if (count > 0 && count <= elements.length / 4) {
			Object[] newElements = new Object[elements.length / 2];
			System.arraycopy(elements, 0, newElements, 0, count);
			elements = newElements;
		}
	}

	// Ottiene un elemento dalla posizione richiesta
	public Object get(int position) {
		int index = position - 1;
		if (isValid(index)) {
			return elements[index];
		}
		return null;
	}

	// Metodo di validazione con gestione errori
	private boolean isValid(int index) {
		if (index < 0 || index >= count) {
			System.out.println("\n!Indice non valido!\n");
			return false;
		}
		return true;
	}
	
	// Visualizza gli elementi presenti nell'array
	public void list() {
        IO.println("\nArray:");
        for (int i = 0; i < count; i++) {
            IO.println((i + 1) + ": " + elements[i]);
        }
        IO.println("\n");
    }
}
