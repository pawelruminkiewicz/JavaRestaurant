/* 
 * Projekt stworzony przez
 * Pawel Ruminkiewicz
 * Nr indeksu: 127212
 */
package jedzenie;

import enums.KategoriaPosilku;
import enums.RozmiarPorcji;
import enums.Skladniki;
import java.io.Serializable;

/**
 * Klasa reprezentująca posiłek
 *
 * @author Pawel
 */
public class Posilek implements Serializable{

    private String nazwa;
    private String nazwyPosilkow[] = {"warszawskie kurczę blade", "śmietnik leśniczego", "zezowate szczęście", "wściekła rybka", "szybki Bolek", "wściekły pies", "propaganda sukcesu", "koszmar fizyka", "przysmak informatyka", "cygara fidela", "lorneta z meduzą", "kwarki jaguar", "przegląd tygodnia", "zalotna Lolita", "syty Jasiek", "pastelowa Helenka", "złota Berta"};
    private String skladniki[];
    private float cena;
    private KategoriaPosilku kategoria;
    private RozmiarPorcji rozmiar;

    /**
     * Zwraca nazwę posiłku
     *
     * @return the nazwa
     */
    public String getNazwa() {
        return nazwa;
    }

    /**
     * Ustawia nazwę posiłku
     *
     * @param nazwa the nazwa to set
     */
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    /**
     * Zwraca cenę posiłku
     *
     * @return the cena
     */
    public float getCena() {
        return cena;
    }

    /**
     * Ustawia cene posiłku
     *
     * @param cena the cena to set
     */
    public void setCena(float cena) {
        this.cena = cena;
    }

    /**
     * Metoda toString
     *
     * @return opis obiektu klasy posiłek
     */
    @Override
    public String toString() {
        return "Nazwa posilku: " + getNazwa()
                + "; skladniki: " + getSkladniki()
                + ";  cena: " + getCena()
                + "; kategoria: " + getKategoria()
                + "; rozmiar: " + getRozmiar();
    }

    /**
     * Metoda generująca sofrmatowany String dla okna informacyjnego
     *
     * @return sformatowany opis posiłku
     */
    public String wyswietlInformacje() {
        return "\n     -----------------\n     -Nazwa: " + getNazwa()
                + "\n     -skladniki: " + getSkladniki()
                + "\n     -cena: " + getCena()
                + "\n     -kategoria: " + getKategoria()
                + "\n     -rozmiar: " + getRozmiar();
    }

    /**
     * Konstruktor posiłku, losujący dla tworzonego obiektu wszystkie wartości
     */
    public Posilek() {
        nazwa = nazwyPosilkow[(int) (Math.random() * (nazwyPosilkow.length))];
        kategoria = KategoriaPosilku.values()[(int) (Math.random() * (KategoriaPosilku.values().length))];
        if (kategoria == KategoriaPosilku.napój) {
            skladniki = new String[3];
            skladniki[0] = String.valueOf(Skladniki.values()[0]);
            skladniki[1] = String.valueOf(Skladniki.values()[1]);
            skladniki[2] = String.valueOf(Skladniki.values()[2]);
        } else {
            skladniki = new String[3];
            skladniki[0] = String.valueOf(Skladniki.values()[(int) (Math.random() * 2 + 3)]);
            skladniki[1] = String.valueOf(Skladniki.values()[(int) (Math.random() * 2 + 6)]);
            skladniki[2] = String.valueOf(Skladniki.values()[(int) (Math.random() * 2 + 9)]);
        }
        cena = Math.round(Math.random() * 90 + 10);
        rozmiar = RozmiarPorcji.values()[(int) (Math.random() * (RozmiarPorcji.values().length))];
    }

    /**
     * Zwraca kategorię posiłku
     *
     * @return the kategoria
     */
    public KategoriaPosilku getKategoria() {
        return kategoria;
    }

    /**
     * Zwraca rozmiar posiłku
     *
     * @return the rozmiar
     */
    public RozmiarPorcji getRozmiar() {
        return rozmiar;
    }

    /**
     * Zwraca listę sładników w formie jednego stringa
     *
     * @return the skladniki
     */
    public String getSkladniki() {
        String elementy = " ";
        for (String x : skladniki) {
            elementy += x + " ";
        }
        return elementy;
    }

    /**
     * Zwaraca tablicę zbioru nazw posiłków
     *
     * @return the nazwyPosilkow
     */
    public String[] getNazwyPosilkow() {
        return nazwyPosilkow;
    }

    /**
     * Ustawia tablicę zbioru nazw posiłków
     *
     * @param nazwyPosilkow the nazwyPosilkow to set
     */
    public void setNazwyPosilkow(String[] nazwyPosilkow) {
        this.nazwyPosilkow = nazwyPosilkow;
    }

    /**
     * Ustawia tablicę składników
     *
     * @param skladniki the skladniki to set
     */
    public void setSkladniki(String[] skladniki) {
        this.skladniki = skladniki;
    }

    /**
     * Ustawia kategorię posiłku
     *
     * @param kategoria the kategoria to set
     */
    public void setKategoria(KategoriaPosilku kategoria) {
        this.kategoria = kategoria;
    }

    /**
     * Ustawia rozmiar posiłku
     *
     * @param rozmiar the rozmiar to set
     */
    public void setRozmiar(RozmiarPorcji rozmiar) {
        this.rozmiar = rozmiar;
    }
}
