/* 
 * Projekt stworzony przez
 * Pawel Ruminkiewicz
 * Nr indeksu: 127212
 */
package czas;

import enums.Tydzien;
import java.io.Serializable;

/**
 *
 * @author Pawel 
 * 
 * Klasa łącząca elementy dnia roboczego czyli godzinę rozpoczęcia
 * pracy przez dostawcę, godzinę zakończenia pracy przez dostawcę i dzień
 * tygodnia
 */
public class DzienRoboczy implements Serializable{

    private Tydzien dzienTygodnia;
    private int godzinaRozpoczeciaPracy;
    private int godzinaZakonczeniaPracy;

    /**
     * Konstruktor dnia roboczego z ręcznym ustawieniem godzin pracy
     *
     * @param oDzienTygodnia - dzień tygodnia, dla którego ustalany jest
     * harmonogram
     * @param oGodzinaRozpoczeniaPracy - godzina rozpoczęcia pracy przez
     * dostawcę
     * @param oGodzinaZakonczeniaPracy - godzina zakończenia pracy przez
     * dostawcę
     */
    public DzienRoboczy(Tydzien oDzienTygodnia, int oGodzinaRozpoczeniaPracy, int oGodzinaZakonczeniaPracy) {
        dzienTygodnia = oDzienTygodnia;
        godzinaRozpoczeciaPracy = oGodzinaRozpoczeniaPracy;
        godzinaZakonczeniaPracy = oGodzinaZakonczeniaPracy;
    }

    /**
     * Konstruktor dnia roboczego z automatycznym przydziałem godzin pracy
     *
     * @param numerDnia - numer dnia w tygodniu (0=poniedziałek)
     */
    public DzienRoboczy(int numerDnia) {
        dzienTygodnia = Tydzien.values()[numerDnia];
        godzinaRozpoczeciaPracy = ((int) (Math.random() * 3 + 8));
        godzinaZakonczeniaPracy = ((int) (Math.random() * 3 + 20));
    }

    /**
     * Metoda toString
     *
     * @return opis obiektu dnia roboczego
     */
    @Override
    public String toString() {
        return "dzien: " + getDzienTygodnia()
                + "; poczatek pracy: " + getGodzinaRozpoczeciaPracy()
                + "; koniec pracy: " + getGodzinaZakonczeniaPracy();
    }

    /**
     * Metoda generująca sofrmatowany String dla okna informacyjnego
     *
     * @return sformatowany opis dnia roboczego
     */
    public String wyswietlInformacje() {
        return "   " + getDzienTygodnia() + " "
                + getGodzinaRozpoczeciaPracy()
                + " - " + getGodzinaZakonczeniaPracy();
    }

    /**
     * Zwraca roboczy dzień tygodnia
     *
     * @return the dzienTygodnia
     */
    public Tydzien getDzienTygodnia() {
        return dzienTygodnia;
    }

    /**
     * Zwraca godzinę rozpoczęcia pracy
     *
     * @return the godzinaRozpoczeciaPracy
     */
    public int getGodzinaRozpoczeciaPracy() {
        return godzinaRozpoczeciaPracy;
    }

    /**
     * Zwraca godzinę zakończenia pracy
     *
     * @return the godzinaZakonczeniaPracy
     */
    public int getGodzinaZakonczeniaPracy() {
        return godzinaZakonczeniaPracy;
    }

    /**
     * Ustawia dzień tygodnia
     *
     * @param dzienTygodnia the dzienTygodnia to set
     */
    public void setDzienTygodnia(Tydzien dzienTygodnia) {
        this.dzienTygodnia = dzienTygodnia;
    }

    /**
     * Ustawia godzinę rozpoczęcia pracy
     *
     * @param godzinaRozpoczeciaPracy the godzinaRozpoczeciaPracy to set
     */
    public void setGodzinaRozpoczeciaPracy(int godzinaRozpoczeciaPracy) {
        this.godzinaRozpoczeciaPracy = godzinaRozpoczeciaPracy;
    }

    /**
     * Ustawia godzinę zakończenia pracy
     *
     * @param godzinaZakonczeniaPracy the godzinaZakonczeniaPracy to set
     */
    public void setGodzinaZakonczeniaPracy(int godzinaZakonczeniaPracy) {
        this.godzinaZakonczeniaPracy = godzinaZakonczeniaPracy;
    }

}
