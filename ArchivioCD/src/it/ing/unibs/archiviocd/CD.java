package it.ing.unibs.archiviocd;

import java.util.ArrayList;

import mylib.math.MyMath;

//Si è deciso di usare il nome "CD" perchè sembrava più significativo di "Cd"

/**
 * Classe CD nella quale vengono definite le azioni che si possono compiere con
 * un cd e le sue caratteristiche.
 * 
 * @author Marco Cadei, Luca Festoni, Antonello Zanini
 *
 */
public class CD {

	private String titolo;
	private String autore;	
	// Si è preferito usare un ArrayList anche se nei metodi è sempre richiesta 
	// una Collection generica
	private ArrayList<Brano> brani = new ArrayList<>();
	
	// Le stringhe utilizzate nel toString()
	private static final String DESCRIZIONE_CD = "%s - %s";
	private static final String DESCRIZIONE_BRANO = "%n %d. %s";
	
	// Stringa per il costruttore di default
	private static final String DEFAULT = "senza nome";
	
	// I messaggi di errori lanciati dalle eccezioni
	private static final String ERRORE_AGGIUNGI_BRANO = "L'argomento non può essere null!";
	private static final String ERRORE_GET_BRANO = "Impossibile trovare il brano richiesto!";
	private static final String ERRORE_GET_BRANO_DA_INDICE = "Indice inserito non valido! Impossibile trovare il brano richiesto!";
	
	
	/**
	 * Costruttore per la creazione di un cd a partire da un titolo, un autore e
	 * dei brani
	 * 
	 * @param titolo
	 *        il titolo del cd
	 * @param autore
	 *        l'autore del cd
	 */
	public CD(String titolo, String autore) {
		this.titolo = titolo;
		this.autore = autore;

	}
	
	/**
	 * Costruttore di default per la creazione di un cd 
	 */
	public CD() {
		this(DEFAULT, DEFAULT);
	}

	/**
	 * Getter dell'attributo titolo
	 * 
	 * @return la stringa contente il titolo del cd
	 */
	public String getTitolo() {
		return titolo;
	}

	/**
	 * Setter dell'attributo titolo
	 * 
	 * @param titolo
	 *        il nome del titolo del cd
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	/**
	 * Getter dell'attributo autore
	 * 
	 * @return la stringa contente l'autore del cd
	 */
	public String getAutore() {
		return autore;
	}

	/**
	 * Setter dell'attributo autore
	 * 
	 * @param autore
	 *        il nome dell'autore del cd
	 */
	public void setAutore(String autore) {
		this.autore = autore;
	}

	
	/**
	 * Metodo per conoscere il numero di brani del CD
	 * @return il numero di brani presenti nel CD
	 */
	public int getNumeroBrani()
	{
		return brani.size();
	}

	
	/**
	 * Gestisce l'aggiunta di un brano alla lista dei brani del cd
	 * 
	 * @param brano
	 *        il brano da inserire
	 *        
	 * @throws IllegalArgumentException
	 *         se il parametro brano è null
	 */
	public void aggiungiBrano(Brano brano) throws IllegalArgumentException {
		// Se brano è null è lanciata una IllegalArgumentException
		if (brano == null) 
			throw new IllegalArgumentException(ERRORE_AGGIUNGI_BRANO);
		
		brani.add(brano);
	}

	/**
	 * Gestisce la selezione di un brano attraverso il titolo dalla lista 
	 * dei brani del cd
	 * 
	 * @param titolo
	 *        il titolo del brano da trovare
	 *        
	 * @return il brano selezionato
	 * 
	 * @throws NullPointerException
	 *         se titolo non è il titolo di nessuno dei brani della
	 *         lista dei brani del cd
	 */
	public Brano getBrano(String titolo) throws NullPointerException {
		for (Brano brano : brani) {
			if (brano.getTitolo().equals(titolo)) {
				return brano;
			}
		}

		// Se il brano non viene trovato è lanciata una NullPointerException
		throw new NullPointerException(ERRORE_GET_BRANO);
	}
	
	/**
	 * Gestisce la selezione di un brano attraverso l'indice dalla lista 
	 * dei brani del cd
	 * 
	 * @param indice
	 *        il numero del brano nel cd richiesto
	 *        
	 * @return il brano selezionato
	 * 
	 * @throws IndexOutOfBoundsException
	 *         se indice è minore di 1 oppure è maggiore della lunghezza della 
	 *         lista dei brani
	 */
	public Brano getBrano(int indice) throws IndexOutOfBoundsException {
		// Se l'indice va oltre i due estremi della lista è lanciata
		// una IndexOutOfBoundsException
		if(indice < 1 || indice > brani.size())
				throw new IndexOutOfBoundsException(ERRORE_GET_BRANO_DA_INDICE);
		
		return brani.get(indice - 1);		
	}
	
	/**
	 * Gestisce la selezione di un brano casuale dalla lista dei brani del cd
	 * 
	 * @return il brano selezionato casualmente
	 */
	public Brano getBranoCasuale() {
		int i = MyMath.randomIntero(0, brani.size() - 1);
		return brani.get(i);
	}


	@Override
	public String toString() {
		// Aggiunge alla descrizione del cd le informazioni base contenenti titolo e autore
		StringBuilder s1 = new StringBuilder(String.format(DESCRIZIONE_CD, titolo,autore));
		
		// Viene aggiunta alla descrizione del CD la lista di brani di cui è composto
		// 1-Brano1
		// 2-Brano2
		//....
		for (int i = 0; i < brani.size(); i++) {
			s1.append(String.format(DESCRIZIONE_BRANO, i + 1, brani.get(i).toString()));
		}
				
		return s1.toString();
	}

	//Il metodo equals è stato implementato solo per i test JUnit (non è usato nel main)
	//Per realizzarlo è stato utilizzato un comando di Eclipse
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CD other = (CD) obj;
		if (autore == null) {
			if (other.autore != null)
				return false;
		} else if (!autore.equals(other.autore))
			return false;
		if (brani == null) {
			if (other.brani != null)
				return false;
		} else if (!brani.equals(other.brani))
			return false;
		if (titolo == null) {
			if (other.titolo != null)
				return false;
		} else if (!titolo.equals(other.titolo))
			return false;
		return true;
	}
	
	
}
