/* 
 * Projekt stworzony przez
 * Pawel Ruminkiewicz
 * Nr indeksu: 127212
 */
package osoba;

import jedzenie.Zamowienie;

/**
 *
 * @author Pawel
 *
 * Klasa reprezentująca klienta okazjonalnego
 */
public class KlientOkazjonalny extends Klient{

    /**
     * Zwraca czy klient jest zweryfikowany aby zamawiać powyżej zadanej kwoty
     *
     * @return the zweryfikowanyDoWiekszychZamowien
     */
    public boolean isZweryfikowanyDoWiekszychZamowien() {
        return zweryfikowanyDoWiekszychZamowien;
    }

    /**
     * Ustawia czy klient jest zweryfikowany aby zamawiać powyżej zadanej kwoty
     *
     * @param zweryfikowanyDoWiekszychZamowien the
     * zweryfikowanyDoWiekszychZamowien to set
     */
    public void setZweryfikowanyDoWiekszychZamowien(boolean zweryfikowanyDoWiekszychZamowien) {
        this.zweryfikowanyDoWiekszychZamowien = zweryfikowanyDoWiekszychZamowien;
    }

    /**
     * Konstruktor klineta okazjonalnego, losujący dla tworzonego obiektu wszystkie wartości
     */
    public KlientOkazjonalny() {
        super();
        super.setSciezkaIkony("/ikony/klientokazjonalny.png");
        zweryfikowanyDoWiekszychZamowien = false;
    }

    /**
     * Przesłonięta metoda opłacania zamówienia,
     * zmieniająca pole obiektu klient okazjonalny
     * @param zamowienie 
     */
    @Override
    public void oplacZamowienie(Zamowienie zamowienie) {
        if (zamowienie.getCalkowityKoszt() > 100) {
            setZweryfikowanyDoWiekszychZamowien(true);
        }
    }

    private boolean zweryfikowanyDoWiekszychZamowien;
    /**
     * Metoda toString
     * @return opis obiektu
     */
    @Override
    public String toString() {
        return super.toString()
                + "zweryfikowany: " + isZweryfikowanyDoWiekszychZamowien();
    }

    /**
     * Metoda generująca sofrmatowany String dla okna informacyjnego
     *
     * @return sformatowany opis klienta okazjonalnego
     */
    @Override
    public String wyswietlInfo() {
        if (isZweryfikowanyDoWiekszychZamowien()) {
            return super.wyswietlInfo()
                    + "\n+Zweryfikowany do dużych zamówień: tak";
        } else {
            return super.wyswietlInfo()
                    + "\n+Zweryfikowany do dużych zamówień: nie";
        }

    }

}
