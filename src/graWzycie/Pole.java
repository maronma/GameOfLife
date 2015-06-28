package graWzycie;


public class Pole implements Cloneable {
	 
    private boolean zywePole;
 
    public Pole(boolean zywe) {
        this.setZywePole(zywe);
    }
 
    public boolean czyZyje() {
        return zywePole;
    }
 
    public void setZywePole(boolean zywe) {
        this.zywePole = zywe;
    }
 
    @Override
    //nadpisanie metody z interfejsu Cloneable. Metoda clone()- Kopia oryginalu
    public Pole clone() {
        return new Pole(zywePole);
    }
 
    public void sprawdzCzyZywaCzyMartwa(int ileSasiadow) {
 
        if (zywePole) {
 
            if (ileSasiadow < 2) {
                zywePole = false;
            } else if (ileSasiadow > 3) {
                zywePole = false;
            }
 
        } else {
 
            if (ileSasiadow == 3) {
                zywePole = true;
            }
 
        }
 
    }
 
}
