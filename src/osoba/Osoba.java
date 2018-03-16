/* 
 * Projekt stworzony przez
 * Pawel Ruminkiewicz
 * Nr indeksu: 127212
 */
package osoba;

import java.io.Serializable;

/**
 *
 * @author Pawel
 *
 * Klasa odpowiedzialna za reprezentację osoby
 */
public abstract class Osoba implements Serializable {

    private String imie;
    private String nazwisko;
    private String oImie[] = {"Anna", "Adam", "Bartosz", "Blanka", "Celina", "Cezary", "Dominik", "Daria", "Emil", "Eliza", "Franciszek", "Felicja", "Michał"};
    private String oNazwisko[] = {"Adamiak", "Babinicz", "Cajler", "Dębowicz", "Frankowicz", "Gregorowicz", "Hindenburg", "Malewicz"};

    /**
     * Zwróć imię
     *
     * @return the imie
     */
    public String getImie() {
        return imie;
    }

    /**
     * Ustaw imię
     *
     * @param imie the imie to set
     */
    public void setImie(String imie) {
        this.imie = imie;
    }

    /**
     * Zwróć nazwisko
     *
     * @return the nazwisko
     */
    public String getNazwisko() {
        return nazwisko;
    }

    /**
     * Ustaw nazwisko
     *
     * @param nazwisko the nazwisko to set
     */
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    /**
     * Konstruktor osoby, losujący dla tworzonego obiektu wszystkie wartości
     */
    public Osoba() {
        this.imie = oImie[(int) (Math.random() * (oImie.length - 1))];
        this.nazwisko = oNazwisko[(int) (Math.random() * (oNazwisko.length - 1))];
    }

    /**
     * Metoda toString
     *
     * @return zwraca opis obiektu klasy osoba
     */
    @Override
    public String toString() {
        return "Imie: " + getImie()
                + "; nazwisko: " + getNazwisko();
    }

    /**
     * Zwraca tablicę losowych imion
     * @return the oImie
     */
    public String[] getoImie() {
        return oImie;
    }

    /**
     * Ustawia tablice losowych imion
     * @param oImie the oImie to set
     */
    public void setoImie(String[] oImie) {
        this.oImie = oImie;
    }

    /**
     * Zwraca tablicę losowych nazwisk
     * @return the oNazwisko
     */
    public String[] getoNazwisko() {
        return oNazwisko;
    }

    /**
     * Ustawia tablicę losowych nazwisk
     * @param oNazwisko the oNazwisko to set
     */
    public void setoNazwisko(String[] oNazwisko) {
        this.oNazwisko = oNazwisko;
    }

}
