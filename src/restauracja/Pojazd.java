/* 
 * Projekt stworzony przez
 * Pawel Ruminkiewicz
 * Nr indeksu: 127212
 */
package restauracja;

import enums.TypPojazdu;
import java.io.Serializable;

/**
 *
 * @author Pawel
 *
 * Klasa reprezentująca pojazd
 */
public class Pojazd implements Serializable {

    private final int ladownosc;
    private final int predkoscNominalnaPrzewozu;
    private final String nrRejestracyjny;
    private double stanPaliwa;
    private final String sciezkaIkony;
    private final int maksymalnaPojemnoscBaku;
    private final TypPojazdu typ;

    /**
     * Zwraca ładowność pojazdu
     *
     * @return the ladownosc
     */
    public int getLadownosc() {
        return ladownosc;
    }

    /**
     * Zwraca prędkość nominalną przewozu
     *
     * @return the predkoscNominalnaPrzewozu
     */
    public int getPredkoscNominalnaPrzewozu() {
        return predkoscNominalnaPrzewozu;
    }

    /**
     * Zwraca nr rejestracyjny pojazdu
     *
     * @return the nrRejestracyjny
     */
    public String getNrRejestracyjny() {
        return nrRejestracyjny;
    }

    /**
     * Zwraca pojemność baku
     *
     * @return the pojemnoscBaku
     */
    public int getPojemnoscBaku() {
        return getMaksymalnaPojemnoscBaku();
    }

    /**
     * Metoda uzupełniająca paliwo do pełna
     */
    public void uzupelnijPaliwo() {
        setStanPaliwa(getMaksymalnaPojemnoscBaku());
    }

    /**
     * Metoda toString
     *
     * @return zwraca opis obiektu klasy pojazd
     */
    @Override
    public String toString() {
        return "ładowność: " + getLadownosc()
                + "; prędkość: " + getPredkoscNominalnaPrzewozu()
                + "; nr rej: " + getNrRejestracyjny()
                + "; stan paliwa: " + getStanPaliwa()
                + "; typ pojazdu: " + getTyp();
    }

    /**
     * Metoda generująca sofrmatowany String dla okna informacyjnego
     *
     * @return sformatowany opis pojazdu
     */
    public String wyswietlInformacje() {
        return "\n         -ładowność: " + getLadownosc()
                + "\n         -prędkość: " + getPredkoscNominalnaPrzewozu()
                + "\n         -nr rej: " + getNrRejestracyjny()
                + "\n         -stan paliwa: " + getStanPaliwa() / 100.00
                + "\n         -typ pojazdu: " + getTyp();
    }

    /**
     * Konstruktor pojazdu, losujący dla tworzonego obiektu wszystkie wartości
     * @param oTyp - typ pojazdu
     */
    public Pojazd(TypPojazdu oTyp) {
        typ = oTyp;
        if (typ.equals(TypPojazdu.AUTO)) {
            ladownosc = (int) Math.round(Math.random() * 200 + 100);
            predkoscNominalnaPrzewozu = 50;
            sciezkaIkony="/ikony/auto.png";
            maksymalnaPojemnoscBaku = 6000;
            stanPaliwa = maksymalnaPojemnoscBaku;
        } else {
            ladownosc = (int) Math.round(Math.random() * 50 + 50);
            predkoscNominalnaPrzewozu = 40;
            sciezkaIkony="/ikony/skuter.png";
            maksymalnaPojemnoscBaku = 3000;
            stanPaliwa = maksymalnaPojemnoscBaku;
        }
        nrRejestracyjny = "PO" + String.valueOf((int) (Math.round(Math.random() * 88888 + 11111)));
    }

    /**
     * Zwraca stan paliwa
     *
     * @return the stanPaliwa
     */
    public double getStanPaliwa() {
        return stanPaliwa;
    }

    /**
     * Ustawia stan paliwa
     *
     * @param stanPaliwa the stanPaliwa to set
     */
    public void setStanPaliwa(double stanPaliwa) {
        this.stanPaliwa = stanPaliwa;
    }

    /**
     * Zwraca typ pojazdu
     *
     * @return the typ
     */
    public TypPojazdu getTyp() {
        return typ;
    }

    /**
     * Zwraca maksymalną pojemność baku
     *
     * @return the maksymalnaPojemnoscBaku
     */
    public int getMaksymalnaPojemnoscBaku() {
        return maksymalnaPojemnoscBaku;
    }

    /**
     * Zwraca ścieżkę do ikony
     * @return the sciezkaIkony
     */
    public String getSciezkaIkony() {
        return sciezkaIkony;
    }

}
