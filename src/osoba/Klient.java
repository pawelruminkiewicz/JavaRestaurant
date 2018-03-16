/* 
 * Projekt stworzony przez
 * Pawel Ruminkiewicz
 * Nr indeksu: 127212
 */
package osoba;

import java.util.Random;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import jedzenie.Zamowienie;
import restauracja.Restauracja;

/**
 *
 * @author Pawel
 *
 * Klasa reprezentująca klienta
 */
public class Klient extends Osoba implements Runnable {

    private int kod;
    private int id;
    private transient static int staticId = 1;
    private int nrTelefonu;
    private String adresDostawy;
    private String oAdresDostawy[] = {"Poznań, ul. Akacjowa ", "Poznań, ul. Mickiewicza ", "Poznań, ul. Słowackiego ",
        "Poznań, ul. Słoneczna ", "Poznań, ul. Kochanowskiego ", "Poznań, ul. Kutrzeby ",
        "Poznań, ul. Armii Poznań ", "Poznań, ul. Matejki ", "Poznań, ul. Ratajczaka "};
    private String sciezkaIkony;
    private int godzinaZamowienia;
    private String adresEmail;
    private transient ImageView ikona;
    private boolean zywy;
    private double odlegloscOdRestauracji;
    private int pozycjaX;
    private int pozycjaY;

    /**
     * Zwraca kod pocztowy klienta
     *
     * @return the kod
     */
    public int getKod() {
        return kod;
    }

    /**
     * Ustawia kod pocztowy klienta
     *
     * @param kod the kod to set
     */
    public void setKod(int kod) {
        this.kod = kod;
    }

    /**
     * Zwraca nr telefonu klienta
     *
     * @return the nrTelefonu
     */
    public int getNrTelefonu() {
        return nrTelefonu;
    }

    /**
     * Ustawia nr telefonu klienta
     *
     * @param nrTelefonu the nrTelefonu to set
     */
    public void setNrTelefonu(int nrTelefonu) {
        this.nrTelefonu = nrTelefonu;
    }

    /**
     * Zwraca godzinę, o której dostawca zwykł otrzymywać zamówienia
     *
     * @return the godzinaZamowienia
     */
    public int getGodzinaZamowienia() {
        return godzinaZamowienia;
    }

    /**
     * Ustawia godzinę, o której dostawca zwykł otrzymywać zamówienia
     *
     * @param godzinaZamowienia the godzinaZamowienia to set
     */
    public void setGodzinaZamowienia(int godzinaZamowienia) {
        this.godzinaZamowienia = godzinaZamowienia;
    }

    /**
     * Zwraca adres email klienta
     *
     * @return the adresEmail
     */
    public String getAdresEmail() {
        return adresEmail;
    }

    /**
     * Ustawia sdres email klienta
     *
     * @param adresEmail the adresEmail to set
     */
    public void setAdresEmail(String adresEmail) {
        this.adresEmail = adresEmail;
    }

    /**
     * Dokonuje zapłaty za zamówienie
     *
     * @param zamowienie
     */
    public void oplacZamowienie(Zamowienie zamowienie) {
        zamowienie.getCalkowityKoszt();
    }

    /**
     * Konstruktor klienta, losujący dla tworzonego obiektu wszystkie wartości
     */
    public Klient() {

        super();
        zywy = true;
        kod = 60000 + (int) (Math.random() * 1999);
        id = staticId++;
        nrTelefonu = 600508000 + (int) (Math.random() * 999);
        adresDostawy = oAdresDostawy[(int) (Math.random() * (oAdresDostawy.length - 1))] + String.valueOf((int) (Math.random() * 888 + 111));
        godzinaZamowienia = ((int) (Math.random() * 15 + 8));
        adresEmail = super.getImie() + "@" + super.getNazwisko() + ".pl";

        boolean znaleziono = false;
        while (!znaleziono) {
            pozycjaX = (new Random().nextInt(568) + 16);
            pozycjaY = (new Random().nextInt(568) + 16);
            znaleziono = true;
            for (int i = pozycjaX - 8; i < pozycjaX + 8; i++) {
                for (int j = pozycjaY - 8; j < pozycjaY + 8; j++) {
                    if (Restauracja.getPolaMapy()[i][j] == true) {
                        znaleziono = false;
                    }
                }
            }
        }

        for (int i = pozycjaX - 8; i < pozycjaX + 8; i++) {
            for (int j = pozycjaY - 8; j < pozycjaY + 8; j++) {
                Restauracja.getPolaMapy()[i][j] = true;
            }
        }

        this.setOdleglscOdRestauracji(Math.abs(this.getPozycjaX() - 284) + Math.abs(this.getPozycjaY() - 284));
    }

    /**
     * Metoda usuwająca klienta z tablicy przechowującej zablokowane obszary
     * mapy
     */
    public synchronized void usunKlientaZMapy() {
        for (int i = 0; i < Restauracja.getZamowienia().size(); i++) {
            if (Restauracja.getZamowienia().get(i).getKlient() == this) {
                Restauracja.getZamowienia().remove(i);
                i--;
            }

        }
        for (int i = 0; i < Restauracja.getDostawcy().size(); i++) {
            for (int j = 0; j < Restauracja.getDostawcy().get(i).getZamowieniaDoRozwiezienia().size(); j++) {
                if (Restauracja.getDostawcy().get(i).getZamowieniaDoRozwiezienia().get(j).getKlient() == this) {
                    Restauracja.getDostawcy().get(i).getZamowieniaDoRozwiezienia().remove(j);
                    j--;
                }
            }
        }
        getIkona().setOnMouseClicked(null);
        this.setZywy(false);
        if (!this.isZywy()) {
            System.out.println(getImie() + "umarl");
        } else {
            System.out.println(getImie() + "zyje");
        }
        for (int i = pozycjaX - 8; i < pozycjaX + 8; i++) {
            for (int j = pozycjaY - 8; j < pozycjaY + 8; j++) {
                Restauracja.getPolaMapy()[i][j] = false;
            }
        }
        Restauracja.getKlienci().remove(this);

    }

    /**
     * Metoda toString
     *
     * @return zwraca opis obiektu klasy klient
     */
    @Override
    public String toString() {
        return super.toString()
                + "kod pocztowy: " + getKod()
                + "; id: " + getId()
                + "; nr tel: " + getNrTelefonu()
                + "; adres dostawy: " + getAdresDostawy()
                + "; godz zamowienia: " + getGodzinaZamowienia()
                + "; email: " + getAdresEmail();
    }

    /**
     * Metoda generująca sofrmatowany String dla okna informacyjnego
     *
     * @return sformatowany opis posiłku
     */
    public String wyswietlInfo() {
        return "+Id: " + getId()
                + "\n+Imię: " + getImie()
                + "\n+Nazwisko: " + getNazwisko()
                + "\n+Adres dostwy: " + getAdresDostawy()
                + "\n+Adres email: " + getAdresEmail().toLowerCase()
                + "\n+Godzina zamówienia: " + getGodzinaZamowienia()
                + "\n+Nr telefonu: " + getNrTelefonu()
                + "\n+Kod pocztowy: " + getKod();

    }

    /**
     * Metoda rysująca klienta na mapie
     *
     * @param mapa - płótno, na którym jest rysowany dostawca
     */
    public void rysuj(Pane mapa) {
        this.setIkona(new ImageView());
        getIkona().setImage(new Image(getSciezkaIkony()));
        getIkona().setLayoutX(this.getPozycjaX());
        getIkona().setLayoutY(this.getPozycjaY());
        getIkona().setUserData(this);
        getIkona().setCursor(Cursor.HAND);
        mapa.getChildren().add(getIkona());
    }

    /**
     * Metoda run wątku klienta
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted() && this.isZywy()) {
            try {
                Zamowienie zamowienie = new Zamowienie();
                Restauracja.getZamowienia().add(zamowienie);
                Thread.sleep(10000);

            } catch (InterruptedException e) {

            }
        }
    }

    /**
     * Zwraca ścieżkę do ikony klienta
     *
     * @return the sciezkaIkony
     */
    public String getSciezkaIkony() {
        return sciezkaIkony;
    }

    /**
     * Ustawia ścieżkę do ikony klienta
     *
     * @param sciezkaIkony the sciezkaIkony to set
     */
    public void setSciezkaIkony(String sciezkaIkony) {
        this.sciezkaIkony = sciezkaIkony;
    }

    /**
     * Zwraca id klienta
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Zwraca adres dostawy
     *
     * @return the adresDostawy
     */
    public String getAdresDostawy() {
        return adresDostawy;
    }

    /**
     * Zwraca ikone klienta
     *
     * @return the ikona
     */
    public ImageView getIkona() {
        return ikona;
    }

    /**
     * Ustawua ikonę klienta
     *
     * @param ikona the ikona to set
     */
    public void setIkona(ImageView ikona) {
        this.ikona = ikona;
    }

    /**
     * Zwraca współrzędną X klienta na mapie
     *
     * @return the pozycjaX
     */
    public int getPozycjaX() {
        return pozycjaX;
    }

    /**
     * Zwraca współrzędną Y klienta na mapie
     *
     * @return the pozycjaY
     */
    public int getPozycjaY() {
        return pozycjaY;
    }

    /**
     * Ustawia adres dostawy
     *
     * @param adresDostawy the adresDostawy to set
     */
    public void setAdresDostawy(String adresDostawy) {
        this.adresDostawy = adresDostawy;
    }

    /**
     * Zwraca czy klient ma zostać usunięty
     *
     * @return the zywy
     */
    public boolean isZywy() {
        return zywy;
    }

    /**
     * Ustawia czy klient ma zostać usunięty
     *
     * @param zywy the zywy to set
     */
    public void setZywy(boolean zywy) {
        this.zywy = zywy;
    }

    /**
     * Zwraca odległość klienta od restauracji
     *
     * @return the odleglscOdRestauracji
     */
    public double getOdlegloscOdRestauracji() {
        return odlegloscOdRestauracji;
    }

    /**
     * Ustawia odległość klienta od restauracji
     *
     * @param odleglscOdRestauracji the odleglscOdRestauracji to set
     */
    public final void setOdleglscOdRestauracji(double odleglscOdRestauracji) {
        this.setOdlegloscOdRestauracji(odleglscOdRestauracji);
    }

    /**
     * Zwraca id klienta
     *
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Zwraca listę losowych sdresów dostawy
     *
     * @return the oAdresDostawy
     */
    public String[] getoAdresDostawy() {
        return oAdresDostawy;
    }

    /**
     * Ustawia listę losowych adresów dostawy
     *
     * @param oAdresDostawy the oAdresDostawy to set
     */
    public void setoAdresDostawy(String[] oAdresDostawy) {
        this.oAdresDostawy = oAdresDostawy;
    }

    /**
     * Ustawia odległość klienta od restauracji
     *
     * @param odlegloscOdRestauracji the odlegloscOdRestauracji to set
     */
    public void setOdlegloscOdRestauracji(double odlegloscOdRestauracji) {
        this.odlegloscOdRestauracji = odlegloscOdRestauracji;
    }

    /**
     * Ustawia pozycję X klienta na mapie
     *
     * @param pozycjaX the pozycjaX to set
     */
    public void setPozycjaX(int pozycjaX) {
        this.pozycjaX = pozycjaX;
    }

    /**
     * Ustawia pozycję Y klienta na mapie
     *
     * @param pozycjaY the pozycjaY to set
     */
    public void setPozycjaY(int pozycjaY) {
        this.pozycjaY = pozycjaY;
    }

}
