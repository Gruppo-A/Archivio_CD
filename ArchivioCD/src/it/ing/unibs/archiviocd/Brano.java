package it.ing.unibs.archiviocd;

/**
 * Classe Brano nella quale vengono definite le azioni che si possono compiere con
 * un brano e le sue caratteristiche.
 * 
 * @author Marco Cadei, Luca Festoni, Antonello Zanini
 *
 */
public class Brano {
	
	private String titolo;
	private double durata;
	
	// Stringa utilizzata nel toString()
	private final static String DESCRIZIONE = "%s - %05.2f";
	
	// Stringa e intero per il costruttore di default
    private static final String TITOLO_DEFAULT = "senza nome";    
    private static final int DURATA_DEFAULT = 0;
	
	/**
	 * Costruttore per la creazione di un brano a partire da titolo e
	 * dalla durata del brano
	 * 
	 * @param titolo
	 * 	      il titolo del brano	  
	 * @param durata
	 *        la durata del brano
	 */
	public Brano(String titolo, double durata) {
		this.titolo = titolo;
		this.durata = durata;
	}
	
	/**
	 * Costruttore di default per la creazione di un brano 
	 */
	public Brano() {
		this(TITOLO_DEFAULT, DURATA_DEFAULT);
	}
	
	/**
	 * Getter dell'attributo titolo
	 * 
	 * @return la stringa contenente il titolo del brano
	 */
	public String getTitolo() {
		return titolo;
	}
	
	/**
	 * Setter dell'attributo titolo
	 * 
	 * @param titolo
	 *        il titolo del brano
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	/**
	 * Getter dell'attributo durata
	 * 
	 * @return il double con la durata del brano        
	 */
	public double getDurata() {
		return durata;
	}
	
	/**
	 * Setter dell'attributo durata
	 * 
	 * @param durata
	 *        la durata della canzone
	 */
	public void setDurata(double durata) {
		this.durata = durata;
	}
	
	@Override
	public String toString()
	{
		StringBuilder descrizione = new StringBuilder();
		descrizione.append(String.format(DESCRIZIONE, titolo, durata));
		return descrizione.toString();
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
		Brano other = (Brano) obj;
		if (Double.doubleToLongBits(durata) != Double
				.doubleToLongBits(other.durata))
			return false;
		if (titolo == null) {
			if (other.titolo != null)
				return false;
		} else if (!titolo.equals(other.titolo))
			return false;
		return true;
	}
	
	
}
