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
 * Klasa reprezentująca klienta stałego
 */
public class KlientStaly extends Klient {

    private int punktyLojalnosciowe;
    private double stalaZnizka;

    /**
     * Zwraca punkty lojalnościowe
     *
     * @return the punktyLojalnosciowe
     */
    public int getPunktyLojalnosciowe() {
        return punktyLojalnosciowe;
    }

    /**
     * Ustawia punkty lojalnościowe
     *
     * @param punktyLojalnosciowe the punktyLojalnosciowe to set
     */
    public void setPunktyLojalnosciowe(int punktyLojalnosciowe) {
        this.punktyLojalnosciowe = punktyLojalnosciowe;
    }

    /**
     * Ustawia stała zniżkę
     *
     * @param stalaZnizka the stalaZnizka to set
     */
    public void setStalaZnizka(float stalaZnizka) {
        this.setStalaZnizka(stalaZnizka);
    }

    /**
     * Konstruktor klienta stałego, losujący dla tworzonego obiektu wszystkie
     * wartości
     */
    public KlientStaly() {
        super();
        super.setSciezkaIkony("/ikony/klientstaly.png");
        punktyLojalnosciowe = 0;
        stalaZnizka = 0.15;
    }

    /**
     * Przesłonięta metoda opłacania zamówienia, pozwalająca obsługiwać punkty
     * lojalnościowe
     *
     * @param zamowienie - zamawiane zamówienie
     */
    @Override
    public void oplacZamowienie(Zamowienie zamowienie) {
        setPunktyLojalnosciowe((int) (long) Math.floor(zamowienie.getCalkowityKoszt() / 10 + 0.5d) + getPunktyLojalnosciowe());
        zamowienie.setCalkowityKoszt(zamowienie.getCalkowityKoszt() * (1 - getStalaZnizka()));
        if (getPunktyLojalnosciowe() >= 500 && zamowienie.getCalkowityKoszt() > 150) {
            zamowienie.setCalkowityKoszt(zamowienie.getCalkowityKoszt() - 100);
            setPunktyLojalnosciowe(getPunktyLojalnosciowe() - 500);
        }
    }

    /**
     * Metoda toString
     *
     * @return zwraca opis obiektu klasy klietn stały
     */
    @Override
    public String toString() {
        return super.toString()
                + "punkty: " + getPunktyLojalnosciowe()
                + "; stała zniżka: " + getStalaZnizka();
    }

    /**
     * Zwraca stałą zniżkę klienta
     *
     * @return the stalaZnizka
     */
    public double getStalaZnizka() {
        return stalaZnizka;
    }

    /**
     * Metoda generująca sofrmatowany String dla okna informacyjnego
     *
     * @return sformatowany opis klienta stałego
     */
    @Override
    public String wyswietlInfo() {
        return super.wyswietlInfo()
                + "\n+Punkty lojalnościowe: " + getPunktyLojalnosciowe()
                + "\n+Stała zniżka: " + getStalaZnizka();
    }

    /**
     * Ustawia stałą zniżkę klienta
     *
     * @param stalaZnizka the stalaZnizka to set
     */
    public void setStalaZnizka(double stalaZnizka) {
        this.stalaZnizka = stalaZnizka;
    }

}
