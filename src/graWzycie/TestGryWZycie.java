package graWzycie;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestGryWZycie {
	private int szerokosc;
	private int wysokosc;
	Plansza plansza;

	@Before
	public void ustawPlansze() {
		int szerokosc = 5;
		int wysokosc = 5;
		plansza = new Plansza(szerokosc, wysokosc);
	}

	@Test
	public void testCzyWszystkieWartosciTablicyFalse() {

		Assert.assertNotNull(plansza);
		for (int i = 0; i < szerokosc; i++) {
			for (int j = 0; j < wysokosc; j++) {
				Assert.assertEquals(false, plansza.odczytajPole(i, j));
			}
		}
	}

	@Test
	public void testCzyDobrzeInicjujeKomorki() {
		plansza.ustawPole(4, 4, true);
		Assert.assertEquals(true, plansza.odczytajPole(4, 4));

		plansza.ustawPole(0, 0, true);
		Assert.assertEquals(true, plansza.odczytajPole(0, 0));
		
		plansza.ustawPole(1, 1, true);
		Assert.assertEquals(true, plansza.odczytajPole(1, 1));		

		plansza.ustawPole(1, 0, true);
		Assert.assertEquals(true, plansza.odczytajPole(1, 0));
		
		plansza.ustawPole(0, 1, true);
		Assert.assertEquals(true, plansza.odczytajPole(0, 1));

		
	}

	@Test
	public void umiera0sasiadow1() {

		// warunek dla 0 sasiadow
		plansza.ustawPole(0, 0, true);
		Assert.assertEquals(0, plansza.ileZywychSasiadow(0, 0)); //zero sasiadow
		plansza.runda();
		Assert.assertEquals(false, plansza.odczytajPole(0, 0));

		// warunek dla 1 sasiada
		plansza.ustawPole(0, 0, true);
		plansza.ustawPole(0, 1, true);
		Assert.assertEquals(1, plansza.ileZywychSasiadow(0, 0));
		plansza.runda();
		Assert.assertEquals(false, plansza.odczytajPole(0, 0));

	}

	@Test
	public void zyje2sasiadow3() {

		// warunek dla 2 sasiadow
		plansza.ustawPole(1, 0, true);		
		plansza.ustawPole(0, 1, true);
		plansza.ustawPole(0, 0, true);
		Assert.assertEquals(2, plansza.ileZywychSasiadow(0, 0));
		plansza.runda();
		Assert.assertEquals(true, plansza.odczytajPole(0, 0));

		//  warunek dla 3 sasiadow
		plansza.ustawPole(0, 0, true);
		plansza.ustawPole(1, 1, true);
		plansza.ustawPole(0, 1, true);
		plansza.ustawPole(1, 0, true);
		
		Assert.assertEquals(3, plansza.ileZywychSasiadow(0, 0));
		plansza.runda();
		Assert.assertEquals(true, plansza.odczytajPole(0, 0));

	}

	@Test
	public void ginieZprzeludnienia4sasiadow() {

		// warunek dla 4 sasiadow
		plansza.ustawPole(2, 2, true);
		plansza.ustawPole(2, 1, true);
		plansza.ustawPole(2, 3, true);
		plansza.ustawPole(1, 2, true);
		plansza.ustawPole(3, 2, true);
		Assert.assertEquals(4, plansza.ileZywychSasiadow(2, 2));
		plansza.runda();
		Assert.assertEquals(false, plansza.odczytajPole(2, 2));

	}

	@Test
	public void ozywaDokladnie3sasiadow() {		
		plansza.ustawPole(2, 2, false);
		plansza.ustawPole(2, 1, true);
		plansza.ustawPole(2, 3, true);
		plansza.ustawPole(1, 2, true);
		Assert.assertEquals(3, plansza.ileZywychSasiadow(2, 2));
		plansza.runda();
		Assert.assertEquals(true, plansza.odczytajPole(2, 2));

	}
}
