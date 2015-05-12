package it.ing.unibs.archiviocd;

import static org.junit.Assert.*;

import org.junit.Test;


/**
 * Classe di test junit relativa alla classe CD
 * 
 * @author Marco Cadei, Luca Festoni, Antonello Zanini
 *
 */

public class CDTest {
	// Lista di CD contenente dei dati di prova per i test
	public CD[] listaCD = {
			new CD("Titolo1","Autore1"),
			new CD("Titolo2","Autore2"),
			new CD("Titolo3","Autore3")
		};
	
	// Metodo utilizzato per riempire i CD con dei brani fantoccio
	public void riempiCD()
	{
		for(int i = 0; i < listaCD.length; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				listaCD[i].aggiungiBrano(new Brano("TitoloBrano"+(j+1),3.00));
			}
		}
	}
	
	@Test
	public void testGetTitolo() throws Exception {
		for(int i = 0; i < listaCD.length; i++)
			assertEquals("Titolo"+(i+1), listaCD[i].getTitolo());
	}

	@Test
	public void testSetTitolo() throws Exception {
		for(int i = 0; i < listaCD.length; i++)
		{
			listaCD[i].setTitolo("TitoloProva");
			assertEquals("TitoloProva", listaCD[i].getTitolo());
		}
	}

	@Test
	public void testGetAutore() throws Exception {
		for(int i = 0; i < listaCD.length; i++)
			assertEquals("Autore"+(i+1), listaCD[i].getAutore());
	}

	@Test
	public void testSetAutore() throws Exception {
		for(int i = 0; i < listaCD.length; i++)
		{
			listaCD[i].setAutore("AutoreProva");
			assertEquals("AutoreProva", listaCD[i].getAutore());
		}
	}

	@Test
	public void testGetNumeroBrani() {
		riempiCD();
		
		for(int i = 0; i < listaCD.length; i++)
			assertEquals(3, listaCD[i].getNumeroBrani());
	}


	@Test
	public void testAggiungiBrano() throws Exception {
		// Inserisco dei brani di prova
		riempiCD();
		// Verifico che per ogni CD siano stati effettivamente inseriti 3 brani
		for(int i = 0; i < listaCD.length; i++)
		{
			assertEquals(3, listaCD[i].getNumeroBrani());
		}

		// Se il brano passato è null è lanciata un'eccezione
		try {
			listaCD[0].aggiungiBrano(null);
		} catch (IllegalArgumentException e) {
			assertEquals("L'argomento non può essere null!", e.getMessage());
		}

	}

	@Test
	public void testSelezionaBranoCasuale() throws Exception {
		Brano b = new Brano("TitoloBrano",3.00);
		listaCD[0].aggiungiBrano(b);

		// Se ho un solo brano, getBranoCasuale deve ritornarmi quel brano
		assertEquals(b, listaCD[0].getBranoCasuale());
	}

	@Test
	public void testGetBrano() throws Exception {
		riempiCD();
		// Per ogni CD provo a cercare ogni brano
		for(int i = 0; i < listaCD.length; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				Brano brano = new Brano("TitoloBrano"+(j+1),3.00);
				assertEquals(brano, listaCD[i].getBrano("TitoloBrano"+(j+1))); 
			}
		}

		// Se il brano non viene trovato nella ricerca per titolo è lanciata un'eccezione
		try {
			listaCD[0].getBrano("Inesistente");
		} catch (NullPointerException e) {
			assertEquals("Impossibile trovare il brano richiesto!", e.getMessage());
		}

		// Se il brano non viene trovato nella ricerca per indice è lanciata un'eccezione
		try {
			// Indice maggiore del limite superiore
			listaCD[0].getBrano(100);
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Indice inserito non valido! Impossibile trovare il brano richiesto!", e.getMessage());
		}
		
		// Se il brano non viene trovato nella ricerca per indice è lanciata un'eccezione
		try {
			// Indice minore del limite inferiore
			listaCD[0].getBrano(-1);
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Indice inserito non valido! Impossibile trovare il brano richiesto!", e.getMessage());
		}

	}	

	@Test
	public void testToString() throws Exception {
		riempiCD();

		assertEquals(String.format("Titolo1 - Autore1%n"
				+ " 1. TitoloBrano1 - 03,00%n"
				+ " 2. TitoloBrano2 - 03,00%n"
				+ " 3. TitoloBrano3 - 03,00"), listaCD[0].toString());
	}

}
