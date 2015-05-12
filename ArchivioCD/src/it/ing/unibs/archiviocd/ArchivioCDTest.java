package it.ing.unibs.archiviocd;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Classe di test junit relativa alla classe ArchivioCD
 * 
 * @author Marco Cadei, Luca Festoni, Antonello Zanini
 *
 */
public class ArchivioCDTest {
	
	public ArchivioCD archivio = new ArchivioCD("ArchivioProva");
	
	// Metodo utilizzato per riempire l'archivio con dei dati di prova
	public void riempiArchivio()
	{
		// Dichiaro una lista di CD
		CD[] listaCD = {
			new CD("Titolo1","Autore1"),
			new CD("Titolo2","Autore2"),
			new CD("Titolo3","Autore3")
		};
		
		// Riempo i CD con dei brani e poi l'archivio con i CD appena creati
		for(int i = 0; i < listaCD.length; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				listaCD[i].aggiungiBrano(new Brano("TitoloBrano"+(j+1),3.00));
			}
			archivio.aggiungiCD(listaCD[i]);
		}
	}
	
	@Test
	public void testGetNome() throws Exception {
		assertEquals("ArchivioProva", archivio.getNome());
	}
	
	@Test
	public void testSetNome() throws Exception {
		archivio.setNome("Nuovo nome");
		assertEquals("Nuovo nome", archivio.getNome());
	}
	
	@Test
	public void testGetNumeroCD()
	{
		riempiArchivio();
		assertEquals(3, archivio.getNumeroCD());
	}
	
	@Test
	public void testAggiungiCD() throws Exception {
		riempiArchivio();
		// Controllo che il numero di CD sia 3
		assertEquals(3, archivio.getNumeroCD());
		
		// Se il CD passato è null è lanciata un'eccezione
		try	{
			archivio.aggiungiCD(null);
		} catch(IllegalArgumentException e)	{
			assertEquals("L'argomento non può essere null!", e.getMessage());
		}
	}
	
	@Test 
	public void testGetCD() throws Exception {
		riempiArchivio();
		// Controllo che il numero di CD sia 3
		assertEquals(3, archivio.getNumeroCD());
		
		// Se il CD non viene trovato nella ricerca per titolo è lanciata un'eccezione
		try {
			archivio.getCD("Inesistente");
		} catch (NullPointerException e) {
			assertEquals("Impossibile trovare il CD richiesto!", e.getMessage());
		}

		// Se il brano non viene trovato nella ricerca per indice è lanciata un'eccezione
		try {
			//Indice maggiore del limite superiore
			archivio.getCD(100);
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Indice inserito non valido! Impossibile trovare il CD richiesto!", e.getMessage());
		}
		
		// Se il brano non viene trovato nella ricerca per indice è lanciata un'eccezione
		try {
			//Indice minore del limite inferiore
			archivio.getCD(-1);
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Indice inserito non valido! Impossibile trovare il CD richiesto!", e.getMessage());
		}
	}
	
	@Test
	public void testGetCDCasuale() throws Exception {
		riempiArchivio();
		
		CD casuale = archivio.getCDCasuale();
		// Verifico che il CD estratto casualmente faccia parte dell'archivio
		assertEquals(archivio.getCD(casuale.getTitolo()), casuale);
	}
	
	@Test
	public void testEliminaCD() throws Exception {
		riempiArchivio();
		
		// Provo a eliminare il cd dal titolo "Prova"
		archivio.eliminaCD("Titolo1");
		// Innanzitutto mi aspetto che il numero di CD sia diminuito da 3 a 2
		assertEquals(2, archivio.getNumeroCD());
		// Adesso provo ad ottenre il CD eliminato per verificare l'eliminazione
		// L'assertEquals è nel catch perché dato che il CD è stato eliminato mi aspetto
		// che la ricerca produca un'eccezione
		try {
			archivio.getCD("Titolo1");
		} catch (NullPointerException e) {
			assertEquals("Impossibile trovare il CD richiesto!", e.getMessage());
		}
		
		// Provo ad eliminare un CD inesistente e mi aspetto un'eccezione
		try {
			archivio.eliminaCD("Inesistente");
		} catch (NullPointerException e) {
			assertEquals("Impossibile trovare il CD richiesto!", e.getMessage());
		}
	}
	
	@Test
	public void testToString() throws Exception {
		riempiArchivio();
		assertEquals("ArchivioProva - numero di CD: 3\r\n"+
				    "******************************\r\n" + 
					"Titolo1 - Autore1\r\n" + 
					" 1. TitoloBrano1 - 03,00\r\n" + 
					" 2. TitoloBrano2 - 03,00\r\n" + 
					" 3. TitoloBrano3 - 03,00\r\n" + 
					"******************************\r\n" + 
					"******************************\r\n" + 
					"Titolo2 - Autore2\r\n" + 
					" 1. TitoloBrano1 - 03,00\r\n" + 
					" 2. TitoloBrano2 - 03,00\r\n" + 
					" 3. TitoloBrano3 - 03,00\r\n" + 
					"******************************\r\n" + 
					"******************************\r\n" + 
					"Titolo3 - Autore3\r\n" + 
					" 1. TitoloBrano1 - 03,00\r\n" + 
					" 2. TitoloBrano2 - 03,00\r\n" + 
					" 3. TitoloBrano3 - 03,00\r\n" + 
					"******************************", archivio.toString());
	}
}
