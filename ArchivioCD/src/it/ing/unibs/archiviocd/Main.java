package it.ing.unibs.archiviocd;

import mylib.util.MyMenu;
import mylib.util.MyScanner;

/**
 * Classe Main per la gestione completa dell'archivio di cd
 * 
 * @author Marco Cadei, Luca Festoni, Antonello Zanini
 *
 */

public class Main {

	public static ArchivioCD archivio = new ArchivioCD();

	private static final String INSERISCI_NOME_ARCHIVIO = "Prima di iniziare inserisci il nome del tuo archivio: ";

	private static final String NO_CD_INSERITO = "CD non inserito: ";
	
	private static final String ARCHIVIO_VUOTO = "L'archivio e' vuoto!!!%n";
	
	private static final String USCITA_ARCHIVIO = "Se davvero sicuro di voler uscire dall'archivio ";
	
	private static final String CORNICE = "%n*********************************%n";
	
	private static final String INSERISCI_AUTORE = "- Inserisci il nome dell'autore del CD: ";
	
	private static final String INSERISCI_TITOLO_CD = "- Inserisci il nome del titolo del CD: ";
	
	private static final String INSERISCI_BRANO = "Inserisci Brano";
	
	private static final String INSERISCI_BRANO_IN_CD = "Inserimento Brani in ";
	
	private static final String INSERISCI_TITOLO_BRANO = "- Inserisci il titolo del brano: ";
	
	private static final String INSERISCI_DURATA_BRANO = "- Inserisci la durata del brano nel formato minuti,secondi: ";
	
	private static final String INSERISCI_CD_CERCATO = "Inserisci il titolo del CD che stai cercando: ";
	
	private static final String ATTENZIONE = "ATTENZIONE: ";
	
	private static final String CONFERMA_ELIMINAZIONE = "E' veramente di sicuro di volere elimnare il CD ";
	
    private static final String CD_ELIMINATO = "Il CD e' stato eliminato!%n";
	
	private static final String CD_NON_ELIMINATO = "Il CD NON e' stato eliminato!%n";
	
	private static final String PUNTO_DI_DOMANDA = "?";	
	
	private static final String[] VOCI_MENU = {"Inserisci CD", "Visualizza CD", "Rimuovi CD",
		"Visualizza intera collezione", "Seleziona brano a caso" };

	
	public static void main(String[] args) {
		// Flag utilizzato per la condizione di continuazione del ciclo
		boolean continua = true;

		// Do un nome all'archivio
		String nome = MyScanner.leggiStringaNonVuota(INSERISCI_NOME_ARCHIVIO);
		archivio.setNome(nome);
		
		// Creo il menu con le voci precedemente create e il nome appena inserito
		MyMenu menu = new MyMenu(archivio.getNome(), VOCI_MENU);

		do {
			// Vado a capo prima di stampare il menu
			System.out.println();
			switch (menu.seleziona()) {
			case 1:
				// Provo ad aggiungere il CD, se il nome è duplicato viene gestita l'eccezione
				try {
					archivio.aggiungiCD(inserisciCD());
				} catch(IllegalArgumentException e) {
					System.out.println(NO_CD_INSERITO + e.getMessage());
				}
				break;
			case 2:		
				visualizzaCD();
				break;
			case 3:
				rimuoviCD();
				break;
			case 4:
				System.out.println(archivio.toString());
				break;
			case 5:
				// Prima di estrarre un brano casuale controllo che l'archivio abbia almeno un CD
				if(archivio.getNumeroCD() != 0)
					System.out.println(archivio.getCDCasuale().getBranoCasuale().toString());
				else
					System.out.println(String.format(ARCHIVIO_VUOTO));
				break;	
			case 6:
				// Leggo un char 'S' o 'N' e poiché la domanda è se si vuole uscire se viene premuto 'S' imposto
				// continua a false in modo da uscire, se viene inserito 'N' imposto continua a true per continuare
				continua = !(MyScanner.si_No(USCITA_ARCHIVIO + archivio.getNome() + PUNTO_DI_DOMANDA));
			}
		} while (continua);

	}
	
	// Di seguito vi sono i metodi per l'iterazione con l'utente.
	// E' stato deciso di metterli nel main, anche se si sarebbe potuto pensare di metterli nella classe ArchivioCD
	// effettuando chiamate del tipo archivio.inserisciCD(). Tuttavia questa implementazione avrebbe legato strettamente
	// la classe ArchivioCD al fatto che l'input è inserito da console, abbiamo perciò preferito lasciare la classe ArchivioCD
	// il più generale possibile, gestendo nel main la comunicazione con l'utente (lettura/scrittura su console).
	
	public static CD inserisciCD() {				
		System.out.println(String.format(CORNICE));
		String autoreCD = MyScanner.leggiStringaNonVuota(INSERISCI_AUTORE);
		String titoloCD = MyScanner.leggiStringaNonVuota(INSERISCI_TITOLO_CD);
		CD cdInCreazione = new CD(titoloCD, autoreCD);

		// Vado a capo
		System.out.println();
		String[] voci = { INSERISCI_BRANO };
		MyMenu menuCD = new MyMenu(INSERISCI_BRANO_IN_CD + cdInCreazione.getTitolo(), voci);
		boolean continua = true;
		do {
			switch (menuCD.seleziona()) {		
			case 1:
				cdInCreazione.aggiungiBrano(inserisciBrano());
				break;

			case 2:
				continua = false;
				break;			
			}
		} while (continua);

		return cdInCreazione;
	}
	
	public static Brano inserisciBrano()
	{
		String titolo = MyScanner.leggiStringaNonVuota(INSERISCI_TITOLO_BRANO);
		double durata = MyScanner.leggiDouble(INSERISCI_DURATA_BRANO);
		return new Brano(titolo, durata);
	}
	
	public static void visualizzaCD()
	{
		String ricerca = MyScanner.leggiStringaNonVuota(INSERISCI_CD_CERCATO);
		// Provo a trovare il CD cercato
		try{
			CD cdCercato = archivio.getCD(ricerca);
			System.out.println(cdCercato.toString());
		}catch(NullPointerException e){
			System.out.println(ATTENZIONE + e.getMessage());
		}
	}
	
	public static void rimuoviCD()
	{
		String ricerca = MyScanner.leggiStringaNonVuota(INSERISCI_CD_CERCATO);
		// Provo a trovare il CD cercato
		try{
			CD cdCercato = archivio.getCD(ricerca);
			boolean elimina = MyScanner.si_No(CONFERMA_ELIMINAZIONE + cdCercato.getTitolo() + PUNTO_DI_DOMANDA);
			if (elimina) {
				archivio.eliminaCD(ricerca);
				System.out.println(String.format(CD_ELIMINATO));
			}
			else
				System.out.println(String.format(CD_NON_ELIMINATO));
		}catch(NullPointerException e){
			System.out.println(ATTENZIONE + e.getMessage());
		}
	}

}
