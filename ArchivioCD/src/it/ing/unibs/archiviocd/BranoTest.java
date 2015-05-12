package it.ing.unibs.archiviocd;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Classe di test junit relativa alla classe Brano
 * 
 * @author Marco Cadei, Luca Festoni, Antonello Zanini
 *
 */

public class BranoTest {
	private final static double epsilon = 1E-03;

	@Test
	public void testGetTitolo() throws Exception {
		Brano b = new Brano("Dalla: Caruso", 5.21);
		assertEquals("Dalla: Caruso", b.getTitolo());
	}

	@Test
	public void testSetTitolo() throws Exception {
		Brano b = new Brano();
		b.setTitolo("Dalla: Caruso");
		assertEquals("Dalla: Caruso", b.getTitolo());
	}

	@Test
	public void testGetDurata() throws Exception {
		Brano b = new Brano("Dalla: Caruso", 5.21);
		assertEquals(5.21, b.getDurata(), epsilon);
	}

	@Test
	public void testSetDurata() throws Exception {
		Brano b = new Brano();
		b.setDurata(5.21);
		assertEquals(5.21, b.getDurata(), epsilon);
	}

	@Test
	public void testToString() throws Exception {
		Brano b = new Brano("Dalla: Caruso", 5.21);
		assertEquals(String.format("Dalla: Caruso - 05,21"), b.toString());
	}

}
