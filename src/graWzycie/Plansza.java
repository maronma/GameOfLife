package graWzycie;

public class Plansza {

	private int wysokosc;
	private int szerokosc;	
	private Pole[][] komorki;

	public Plansza(int szerokosc, int wysokosc) {
		this.szerokosc = szerokosc;
		this.wysokosc = wysokosc;
		komorki = new Pole[szerokosc][wysokosc];
		usunWszystkiePola();
	}
	
	public void ustawPole(int x, int y, boolean czyZyje) {
		if (x >= 0 && y >= 0 && x < szerokosc && y < wysokosc) {
			komorki[x][y].setZywePole(czyZyje);
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	private void usunWszystkiePola() {
		for (int i = 0; i < szerokosc; i++) {
			for (int j = 0; j < wysokosc; j++) {
				komorki[i][j] = new Pole(false);
			}
		}
	}
	
	public boolean odczytajPole(int x, int y) {
		if (x >= 0 && y >= 0 && x < szerokosc && y < wysokosc) {
			return komorki[x][y].czyZyje();
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	
	public void runda() {
		Pole[][] nowaPlansza = new Pole[szerokosc][wysokosc];
		for (int i = 0; i < szerokosc; i++) {
			for (int j = 0; j < wysokosc; j++) {
				nowaPlansza[i][j] = komorki[i][j].clone(); // clone() metoda z interfejsu Cloneable, kopiuje tablice komorki do tablicy nowaPlansza
			}
		}

		// sprawdzenie sasiaduw zywych nastepnie weryfikacja czy zywa czy martwa
		for (int i = 0; i < szerokosc; i++) {
			for (int j = 0; j < wysokosc; j++) {
				int ileSasiadow = ileZywychSasiadow(i, j);
				nowaPlansza[i][j].sprawdzCzyZywaCzyMartwa(ileSasiadow);
			}
		}
		komorki = nowaPlansza;
	}

	public int ileZywychSasiadow(int i, int j) {
		// sprawdzenie sasiadow z lewej, prawej oraz gory i dolu
		int lewaStronaX = Math.max(i - 1, 0);
		int prawaStronaX = Math.min(i + 1, szerokosc - 1);

		int lewaStronaY = Math.max(j - 1, 0);
		int prawaStronaY = Math.min(j + 1, wysokosc - 1);

		int zywiSasiedzi = 0;
		for (int x = lewaStronaX; x <= prawaStronaX; x++) {
			for (int y = lewaStronaY; y <= prawaStronaY; y++) {
				if (komorki[x][y].czyZyje()) {
					zywiSasiedzi++;
				}
			}
		}

		// pomniejszamy ilosc zywych sasiadow o komorke ktora sprawdzamy
		if (komorki[i][j].czyZyje()) {
			zywiSasiedzi--;
		}

		return zywiSasiedzi;
	}

}
