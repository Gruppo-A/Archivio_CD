package it.ing.unibs.archiviocd;

import java.util.ArrayList;

import mylib.math.MyMath;

/**
 * Classe ArchivioCD nella quale vengono definite le azioni che si possono compiere con
 * un archivio di cd e le sue caratteristiche.
 * 
 * @author Marco Cadei, Luca Festoni, Antonello Zanini
 *
 */
public class ArchivioCD {

	private String nome;
	// Si è preferito usare un ArrayList anche se nei metodi è sempre richiesta
	// una Collection generica
	private ArrayList<CD> archivio = new ArrayList<>();

	// Stringa per il costruttore di default
	private final static String NOME_DEFAULT = "senza nome";

	// Le stringhe utilizzate nel toString()
	private static final String DESCRIZIONE_ARCHIVIO = "%s - numero di CD: %d";
	private static final String DESCRIZIONE_CD = "%n%s";
	private static final String CORNICE = "%n******************************";

	// I messaggi di errori lanciati dalle eccezioni
	private static final String ERRORE_AGGIUNGI_CD = "L'argomento non può essere null!";
	private static final String ERRORE_TITOLO_DOPPIO = "Il nome del CD che si sta provando ad inserire è già presente nell'archivio!";
	private static final String ERRORE_GET_CD = "Impossibile trovare il CD richiesto!";
	private static final String ERRORE_GET_CD_DA_INDICE = "Indice inserito non valido! Impossibile trovare il CD richiesto!";

	/**
	 * Costruttore per la creazione di un archivio di nome specificato a partire
	 * da una collezione di CD
	 * 
	 * @param nome
	 *        nome dell'archivio
	 */
	public ArchivioCD(String nome) {
		this.nome = nome;
	}

	/**
	 * Costruttore di default per la classe ArchivioCD La collezione di CD viene
	 * istanziata a una collezione vuota
	 */
	public ArchivioCD() {
		this(NOME_DEFAULT);
	}
	
	/**
	 * Setter dell'attributo nome
	 * @param nome
	 *        nome dell'archivio
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	
	/**
	 * Getter dell'attributo nome
	 * @return il nome dell'arhivio
	 */
	public String getNome()
	{
		return nome;
	}
	
	/**
	 * Metodo per conoscere il numero di CD nell'archivio 
	 * @return il numero di CD presenti nell'archivio
	 */
	public int getNumeroCD()
	{
		return archivio.size();
	}

	/**
	 * Gestisce l'aggiunta di un CD all'archivio dei CD
	 * 
	 * @param cd
	 *        il cd da inserire
	 *         
	 * @throws IllegalArgumentException
	 * 		   - se il parametro cd è null
	 * 		   - se il CD che si sta provando ad inserire ha il
	 *           titolo uguale ad un CD già presente nella raccolta
	 */
	public void aggiungiCD(CD cd) throws IllegalArgumentException {
		// Se il CD è null è lanciata una IllegalArgumentException
		if (cd == null) {
			throw new IllegalArgumentException(ERRORE_AGGIUNGI_CD);
		}
		// Se il CD ha un titolo uguale ad un dei titoli dei CD già presenti
		// é lanciata una IllegalArgumentException
		else {
			for(CD cdControllo : archivio){
				if(cdControllo.getTitolo().equals(cd.getTitolo())){
					throw new IllegalArgumentException(ERRORE_TITOLO_DOPPIO);
				}
			}
		}
		// Se il metodo arriva fino a questo punto dell'esecuzione  vuol dire che il CD che
		// si sta provando ad aggiungere è corretto e lo si può aggiungere
		archivio.add(cd);
	}

	/**
	 * Gestisce la selezione di un CD attraverso il titolo
	 * 
	 * @param titolo
	 *        il titolo del CD da trovare
	 * 
	 * @return il CD selezionato
	 * 
	 * @throws NullPointerException
	 *         se titolo non è il titolo di nessuno dei CD dell'archivio
	 */
	public CD getCD(String titolo) throws NullPointerException {
		for (CD cd : archivio) {
			if (cd.getTitolo().equals(titolo)) {
				return cd;
			}
		}
        // Se il CD non è trovato è lanciata una NullPointerException
		throw new NullPointerException(ERRORE_GET_CD);
	}

	/**
	 * Gestisce la selezione di un CD attraverso l'indice dall'archivio
	 * 
	 * @param indice
	 *        il numero del CD nell'archivio
	 * 
	 * @return il CD selezionato
	 * 
	 * @throws IndexOutOfBoundsException
	 *         se indice è minore di 1 oppure è maggiore della lunghezza dell'archivio
	 */

	public CD getCD(int indice) throws IndexOutOfBoundsException {
		// Se l'indice va oltre i due estremi della lista è lanciata
	    // una IndexOutOfBoundsException
		if (indice < 1 || indice > archivio.size())
			throw new IndexOutOfBoundsException(ERRORE_GET_CD_DA_INDICE);

		return archivio.get(indice - 1);
	}

	/**
	 * Gestisce la selezione di un CD casuale dall'archivio
	 * 
	 * @return il CD selezionato casualmente
	 */
	public CD getCDCasuale() {
		int n = MyMath.randomIntero(0, archivio.size() - 1);
		return archivio.get(n);
	}
	
	/**
	 * Gestisce l'eliminazione di un CD dall'archivio
	 * @param titolo
	 *  	  dato che il titolo di un CD è univoco posso usarlo come
	 *        parametro per eliminare un CD (dato che se un CD ha il titolo
	 *        corrispondente so per certo che è l'unico)
	 * @throws NullPointerException se il CD che si sta provando ad eliminare non esiste
	 */
	public void eliminaCD(String titolo) throws NullPointerException
	{
		//Prima trovo il CD cercato col metodo get dopodiché lo rimuovo dall'array
		archivio.remove(getCD(titolo));
	}

	
	@Override
	public String toString() {
		StringBuilder descrizione = new StringBuilder();
		// Aggiunge alla descrizione dell'archivio le informazioni base
		// contenenti nome e numero di brani
		descrizione.append(String.format(DESCRIZIONE_ARCHIVIO, nome, archivio.size()));

		// Viene aggiunta alla descrizione dell'archivio la lista di CD di cui è
		// composto
		for (CD cd : archivio) {
			descrizione.append(String.format(CORNICE));
			descrizione.append(String.format(DESCRIZIONE_CD, cd.toString()));
			descrizione.append(String.format(CORNICE));
		}

		return descrizione.toString();
	}
	
}