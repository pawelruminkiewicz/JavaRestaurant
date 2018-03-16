/* 
 * Projekt stworzony przez
 * Pawel Ruminkiewicz
 * Nr indeksu: 127212
 */
package osoba;

import czas.HarmonogramPracy;
import enums.TypPojazdu;
import enums.KategoriePrawaJazdy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import jedzenie.Zamowienie;
import restauracja.Pojazd;
import restauracja.Restauracja;

/**
 *
 * @author Pawel
 *
 * Klasa reprezentująca dostawcę
 */
public class Dostawca extends Osoba implements Runnable {

    private String pesel;
    private HarmonogramPracy czasPracy;
    private List<KategoriePrawaJazdy> oKategoriePrawaJazdy = new ArrayList();
    private Pojazd przypisanyPojazd;
    private transient ImageView ikona;
    private volatile List<Zamowienie> zamowieniaDoRozwiezienia;
    private int pozycjaX;
    private int pozycjaY;
    private boolean zywy;
    private boolean powrocDoRestauracji;
    private String powodPowrotuDoRestauracji;
    private volatile boolean zajety;

    /**
     * Konstruktor dostawcy, losujący dla tworzonego obiektu wszystkie wartości
     */
    public Dostawca() {
        zywy = true;
        powrocDoRestauracji = false;
        String rok = String.valueOf((int) (Math.random() * (98) + 1));
        String miesiac = String.valueOf((int) (Math.random() * (11) + 1));
        String dzien = String.valueOf((int) (Math.random() * (27) + 1));
        if (Integer.parseInt(rok) < 10) {
            rok = "0" + rok;
        }
        if (Integer.parseInt(miesiac) < 10) {
            rok = "0" + miesiac;
        }
        if (Integer.parseInt(dzien) < 10) {
            rok = "0" + dzien;
        }
        pesel = rok + miesiac + dzien + "0145" + String.valueOf(new Random().nextInt(10));
        czasPracy = new HarmonogramPracy();
        int losowaWartosc = new Random().nextInt(3);
        switch (losowaWartosc) {
            case 2:
                oKategoriePrawaJazdy.add(KategoriePrawaJazdy.B);
                break;
            case 1:
                oKategoriePrawaJazdy.add(KategoriePrawaJazdy.A);
                oKategoriePrawaJazdy.add(KategoriePrawaJazdy.B);
                break;
            default:
                oKategoriePrawaJazdy.add(KategoriePrawaJazdy.A);
                break;
        }

        if (oKategoriePrawaJazdy.contains(KategoriePrawaJazdy.A)) {
            przypisanyPojazd = new Pojazd(TypPojazdu.SKUTER);
        } else if (oKategoriePrawaJazdy.contains(KategoriePrawaJazdy.B)) {
            przypisanyPojazd = new Pojazd(TypPojazdu.AUTO);
        }
        pozycjaX = 284;
        pozycjaY = 284;
        zajety = false;
    }

    /**
     * Metoda usuwająca dostawcę z tablicy przechowującej zablokowane obszary
     * mapy
     */
    public void usunDostawceZMapy() {

        this.getIkona().setOnMouseClicked(null);
        /*for (int i = 0; i < Restauracja.getListaX().size(); i++) {
            if (this.getPozycjaX() == Restauracja.getListaX().get(i)) {
                Restauracja.getListaX().remove(i);
                break;
            }
        }
        for (int i = 0; i < Restauracja.getListaY().size(); i++) {
            if (this.getPozycjaY() == Restauracja.getListaY().get(i)) {
                Restauracja.getListaY().remove(i);
                break;
            }
        }*/
        this.setZywy(false);
        Restauracja.getDostawcy().remove(this);

    }

    /**
     * Metoda rysująca dostawcę na mapie
     *
     * @param mapa - płótno, na którym jest rysowany dostawca
     */
    public synchronized void rysuj(Pane mapa) {
        this.setIkona(new ImageView());
        getIkona().setImage(new Image(getPrzypisanyPojazd().getSciezkaIkony()));
        getIkona().setLayoutX(this.getPozycjaX());
        getIkona().setLayoutY(this.getPozycjaY());
        getIkona().setUserData(this);
        getIkona().setCursor(Cursor.HAND);
        mapa.getChildren().add(getIkona());
        getIkona().visibleProperty().set(false);

    }

    /**
     * Metoda generująca sofrmatowany String dla okna informacyjnego
     *
     * @return sformatowany opis dostawcy
     */
    public String wyswietlInfo() {
        return "+Imię: " + getImie()
                + "\n+Nazwisko: " + getNazwisko()
                + "\n+Pesel: " + getPesel()
                + "\n+Harmonogram pracy:\n" + getCzasPracy().wyswietlInformacje()
                + "+Kategorie prawa jazdy: " + getoKategoriePrawaJazdy()
                + "\n+Przypisany pojazd: " + getPrzypisanyPojazd().wyswietlInformacje()
                + "\n+Zamówienia w aucie: " + getZamowieniaDoRozwiezienia().size();
    }

    /**
     * Metoda toString
     *
     * @return zwraca opis obiektu klasy dostawca
     */
    @Override
    public String toString() {
        return "Mój pesel: " + getPesel()
                + ",aktualny czas pracy: " + getCzasPracy().toString()
                + ",posiadam prawo jazdy kategorii: " + getoKategoriePrawaJazdy()
                + ", moje auto: " + getPrzypisanyPojazd().toString();
    }

    /**
     * Zwraca pesel dostawcy
     *
     * @return the pesel
     */
    public String getPesel() {
        return pesel;
    }

    /**
     * Ustawia pesel dostawcy
     *
     * @param pesel the pesel to set
     */
    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    /**
     * Zwraca czas pracy dostawcy
     *
     * @return the czasPracy
     */
    public HarmonogramPracy getCzasPracy() {
        return czasPracy;
    }

    /**
     * Ustawia czas pracy dostawcy
     *
     * @param czasPracy the czasPracy to set
     */
    public void setCzasPracy(HarmonogramPracy czasPracy) {
        this.czasPracy = czasPracy;
    }

    /**
     * Zwraca kategorie prawa jazdy
     *
     * @return the oKategoriePrawaJazdy
     */
    public List<KategoriePrawaJazdy> getoKategoriePrawaJazdy() {
        return oKategoriePrawaJazdy;
    }

    /**
     * Ustawia kategorie prawa jazdy
     *
     * @param oKategoriePrawaJazdy the oKategoriePrawaJazdy to set
     */
    public void setoKategoriePrawaJazdy(List<KategoriePrawaJazdy> oKategoriePrawaJazdy) {
        this.oKategoriePrawaJazdy = oKategoriePrawaJazdy;
    }

    /**
     * Zwraca obiekt przypisanego do dostawcy pojazdu
     *
     * @return the przypisanyPojazd
     */
    public Pojazd getPrzypisanyPojazd() {
        return przypisanyPojazd;
    }

    /**
     * Ustawia obiekt przypisanego do dostawcy pojazdu
     *
     * @param przypisanyPojazd the przypisanyPojazd to set
     */
    public void setPrzypisanyPojazd(Pojazd przypisanyPojazd) {
        this.przypisanyPojazd = przypisanyPojazd;
    }

    /**
     * Metoda sprawdzająca poziom benzyny w aucie dostawcy, kiedy go nie
     * wystarczy na dalszą jazdę, dostawca wraca dotankować
     */
    public void sprawdzWskaznikBenzyny() {
        //System.out.println(this.getPrzypisanyPojazd().getStanPaliwa() + "|" + getZamowieniaDoRozwiezienia().get(0).getKlient().getOdlegloscOdRestauracji());
        if (this.getPrzypisanyPojazd().getStanPaliwa() < getZamowieniaDoRozwiezienia().get(0).getKlient().getOdlegloscOdRestauracji()) {
            this.setPowrocDoRestauracji(true);
            setPowodPowrotuDoRestauracji("brak paliwa");
        }
    }

    /**
     * Metoda run wątku dostawcy
     */
    @Override
    public synchronized void run() {
        while (!Thread.currentThread().isInterrupted() && this.isZywy()) {
            try {
                synchronized (this) {
                    if (!Restauracja.getZamowienia().isEmpty() && !isZajety()) {
                        setZajety(true);
                        System.out.println(Restauracja.getZamowienia().size());
                        setZamowieniaDoRozwiezienia((List<Zamowienie>) new ArrayList(Restauracja.getZamowienia()));
                        Restauracja.odbierzZamowienia();
                    }

                    if (isZajety()) {
                        dostarczZamowienie(0);
                    }
                }
                Thread.sleep(30);

            } catch (InterruptedException e) {

            }
        }
    }

    /**
     * Metoda odpowiedzialna za dostarczenie zamówienia
     *
     * @param i - nr zamówienia w tablicy zamówień do rozwiezienia
     */
    public synchronized void dostarczZamowienie(int i) throws InterruptedException {
        int wspolrzednaKlientaX = 0;
        int wspolrzednaKlientaY = 0;

        if (this.getPozycjaX() > 268 && this.getPozycjaX() < 300 && this.getPozycjaY() > 268 && this.getPozycjaY() < 300) {
            getIkona().visibleProperty().set(false);
        } else {
            getIkona().visibleProperty().set(true);
        }
        if (!isPowrocDoRestauracji()) {

            if (!getZamowieniaDoRozwiezienia().isEmpty()) {
                sprawdzWskaznikBenzyny();
                wspolrzednaKlientaX = getZamowieniaDoRozwiezienia().get(i).getKlient().getPozycjaX();
                wspolrzednaKlientaY = getZamowieniaDoRozwiezienia().get(i).getKlient().getPozycjaY();
            } else if (getZamowieniaDoRozwiezienia().isEmpty()) {
                wspolrzednaKlientaX = 284;
                wspolrzednaKlientaY = 284;
            }
            if (getZamowieniaDoRozwiezienia().isEmpty() && wspolrzednaKlientaX == this.getIkona().getLayoutX() && wspolrzednaKlientaY == this.getIkona().getLayoutY()) {
                getZamowieniaDoRozwiezienia().clear();
                setZajety(false);
                getPrzypisanyPojazd().uzupelnijPaliwo();
            }

            if (wspolrzednaKlientaX == this.getIkona().getLayoutX() && wspolrzednaKlientaY == this.getIkona().getLayoutY() && !getZamowieniaDoRozwiezienia().isEmpty()) {
                getZamowieniaDoRozwiezienia().remove(0);
            }
        } else {
            wspolrzednaKlientaX = 284;
            wspolrzednaKlientaY = 284;
            if (wspolrzednaKlientaX != this.getIkona().getLayoutX() && wspolrzednaKlientaY != this.getIkona().getLayoutY()) {
                System.out.println("Dostawca: " + getImie() + " " + getNazwisko() + " wraca z powodu: " + getPowodPowrotuDoRestauracji());
            } else if (wspolrzednaKlientaX == this.getIkona().getLayoutX() && wspolrzednaKlientaY == this.getIkona().getLayoutY()) {
                System.out.println("Dostawca: " + getImie() + " " + getNazwisko() + " WRÓCIŁ");
                setPowrocDoRestauracji(false);
                getPrzypisanyPojazd().setStanPaliwa(getPrzypisanyPojazd().getPojemnoscBaku());
            }
        }

        getPrzypisanyPojazd().setStanPaliwa(getPrzypisanyPojazd().getStanPaliwa() - 1);

        if (wspolrzednaKlientaX != this.getIkona().getLayoutX()) {
            if (wspolrzednaKlientaX > this.getIkona().getLayoutX()) {
                boolean stoj = true;
                while (stoj) {
                    stoj = false;
                    for (int j = pozycjaX + 1; j < pozycjaX + 9; j++) {
                        if (Restauracja.getPolaMapyDostawcow()[j][pozycjaY] == true) {
                            stoj = true;
                            System.out.println("Dostawca: " + getImie() + " " + getNazwisko() + " czeka w kolejce");
                            Thread.currentThread().sleep(200);
                        }
                    }
                }
                getIkona().setLayoutX(this.getIkona().getLayoutX() + 1);
            } else {
                boolean stoj = true;
                while (stoj) {
                    stoj = false;
                    for (int j = pozycjaX - 9; j > pozycjaX - 1; j++) {
                        if (Restauracja.getPolaMapyDostawcow()[j][pozycjaY] == true) {
                            stoj = true;
                            System.out.println("Dostawca: " + getImie() + " " + getNazwisko() + " czeka w kolejce");
                            Thread.currentThread().sleep(200);
                        }
                    }
                }
                getIkona().setLayoutX(this.getIkona().getLayoutX() - 1);
            }
        } else {
            if (wspolrzednaKlientaY != this.getIkona().getLayoutY()) {
                if (wspolrzednaKlientaY > this.getIkona().getLayoutY()) {
                    boolean stoj = true;
                    while (stoj) {
                        stoj = false;
                        for (int j = pozycjaY + 1; j < pozycjaY + 9; j++) {
                            if (Restauracja.getPolaMapyDostawcow()[pozycjaX][j] == true) {
                                stoj = true;
                                System.out.println("Dostawca: " + getImie() + " " + getNazwisko() + " czeka w kolejce");
                                Thread.currentThread().sleep(200);

                            }
                        }
                    }
                    getIkona().setLayoutY(this.getIkona().getLayoutY() + 1);
                } else {
                    boolean stoj = true;
                    while (stoj) {
                        stoj = false;
                        for (int j = pozycjaY - 9; j > pozycjaY - 1; j++) {
                            if (Restauracja.getPolaMapyDostawcow()[pozycjaX][j] == true) {
                                stoj = true;
                                System.out.println("Dostawca: " + getImie() + " " + getNazwisko() + " czeka w kolejce");
                                Thread.currentThread().sleep(200);
                                break;
                            }
                        }
                    }
                    getIkona().setLayoutY(this.getIkona().getLayoutY() - 1);

                }
            }

        }

        Restauracja.getPolaMapyDostawcow()[pozycjaX][pozycjaY] = false;

        this.setPozycjaX((int) this.getIkona().getLayoutX());
        this.setPozycjaY((int) this.getIkona().getLayoutY());

        Restauracja.getPolaMapyDostawcow()[pozycjaX][pozycjaY] = true;

    }

    /**
     * Zwraca ikonę dostawcy
     *
     * @return the ikona
     */
    public ImageView getIkona() {
        return ikona;
    }

    /**
     * Zwraca współrzędną x dostawcy na mapie
     *
     * @return the pozycjaX
     */
    public int getPozycjaX() {
        return pozycjaX;
    }

    /**
     * Ustwawia współrzędną x dostawcy na mapie
     *
     * @param pozycjaX the pozycjaX to set
     */
    public void setPozycjaX(int pozycjaX) {
        this.pozycjaX = pozycjaX;
    }

    /**
     * Zwraca współrzędną y dostawcy na mapie
     *
     * @return the pozycjaY
     */
    public int getPozycjaY() {
        return pozycjaY;
    }

    /**
     * Ustawia współrzędną y dostawcy na mapie
     *
     * @param pozycjaY the pozycjaY to set
     */
    public void setPozycjaY(int pozycjaY) {
        this.pozycjaY = pozycjaY;
    }

    /**
     * Ustawia ikonę dostawcy
     *
     * @param ikona the ikona to set
     */
    public void setIkona(ImageView ikona) {
        this.ikona = ikona;
    }

    /**
     * Zwraca czy dostawca jest w trakcie transportu zamówień
     *
     * @return the zajety
     */
    public boolean isZajety() {
        return zajety;
    }

    /**
     * Ustawia status dostawcy jako zajęty lub wolny
     *
     * @param zajety the zajety to set
     */
    public synchronized void setZajety(boolean zajety) {
        this.zajety = zajety;
    }

    /**
     * Zwraca czy dostawca ma zostać usunięty
     *
     * @return the zywy
     */
    public boolean isZywy() {
        return zywy;
    }

    /**
     * Zmienia status dostawcy na do usunięcia
     *
     * @param zywy the zywy to set
     */
    public void setZywy(boolean zywy) {
        this.zywy = zywy;
    }

    /**
     * Zwraca czy dostawca ma wrócić do restauracji
     *
     * @return the powrocDoRestauracji
     */
    public boolean isPowrocDoRestauracji() {
        return powrocDoRestauracji;
    }

    /**
     * Wymusza powrót dostawcy do restauracji
     *
     * @param powrocDoRestauracji the powrocDoRestauracji to set
     */
    public void setPowrocDoRestauracji(boolean powrocDoRestauracji) {
        this.powrocDoRestauracji = powrocDoRestauracji;
    }

    /**
     * Zwraca listę zamówień do rozwiezienia
     *
     * @return the zamowieniaDoRozwiezienia
     */
    public List<Zamowienie> getZamowieniaDoRozwiezienia() {
        return zamowieniaDoRozwiezienia;
    }

    /**
     * Ustawia listę zamówień do rozwiezienia
     *
     * @param zamowieniaDoRozwiezienia the zamowieniaDoRozwiezienia to set
     */
    public void setZamowieniaDoRozwiezienia(List<Zamowienie> zamowieniaDoRozwiezienia) {
        this.zamowieniaDoRozwiezienia = zamowieniaDoRozwiezienia;
    }

    /**
     * Zwraca powód powrotu do restauracji
     *
     * @return the powodPowrotuDoRestauracji
     */
    public String getPowodPowrotuDoRestauracji() {
        return powodPowrotuDoRestauracji;
    }

    /**
     * Ustawia powód powrotu do restauracji
     *
     * @param powodPowrotuDoRestauracji the powodPowrotuDoRestauracji to set
     */
    public void setPowodPowrotuDoRestauracji(String powodPowrotuDoRestauracji) {
        this.powodPowrotuDoRestauracji = powodPowrotuDoRestauracji;
    }

}
