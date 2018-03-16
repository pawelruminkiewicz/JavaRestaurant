/* 
 * Projekt stworzony przez
 * Pawel Ruminkiewicz
 * Nr indeksu: 127212
 */
package jedzenie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import restauracja.Restauracja;

/**
 *
 * @author Pawel
 *
 * Klasa implementująca zestaw obiadowy
 */
public class ZestawObiadowy implements Serializable{

    private double znizka;
    private List<Posilek> listaPosilkow;
    private double cenaZestawu;

    /**
     * Konstruktor zestawu obiadowego, losujący dla tworzonego obiektu wszystkie
     * wartości
     */
    public ZestawObiadowy() {
        listaPosilkow = new ArrayList();
        int liczbaPosilkowWZestawie = (int) (Math.random() * (Restauracja.getMenuRestauracji().size() - 1) + 1);
        for (int i = 0; i < liczbaPosilkowWZestawie; i++) {
            listaPosilkow.add(Restauracja.getMenuRestauracji().get(i));
        }
        obliczCeneZestawu();
        obliczZnizke();
        cenaZestawu *= (1 - znizka);
    }

    /**
     * Metoda obliczjąca zniżkę dla zestawu. Zniżka w wysokości 10% przydzielana
     * jest, kiedy kwota zestawu osiągnie 150 zł
     */
    public final void obliczZnizke() {
        if (getCenaZestawu() > 150) {
            setZnizka(0.1);
        } else {
            setZnizka(0);
        }
    }

    /**
     * Metoda obliczająca cenę pojedynczego zestawu
     *
     */
    public final void obliczCeneZestawu() {
        for (Posilek i : getListaPosilkow()) {
            setCenaZestawu(getCenaZestawu() + i.getCena());
        }
    }

    /**
     * Metoda toString
     *
     * @return zwraca opis obiektu klasy zestaw obiadowy
     */
    @Override
    public String toString() {
        return "[Zniżka: " + getZnizka() * 100
                + "%] lista posilkow w zestawie: " + getListaSkladowychPosilkow(getListaPosilkow())
                + ";  cena zestawu: " + getCenaZestawu();
    }

    /**
     * Metoda generująca sofrmatowany String dla okna informacyjnego
     *
     * @return sformatowany opis zestawu obiadowego
     */
    public String wyswietlInformacje() {
        return "\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n[Zniżka: " + getZnizka() * 100
                + "% |  cena zestawu: " + getCenaZestawu()
                + "]\n lista posiłków w zestawie: " + getListaSkladowychPosilkow(getListaPosilkow());
    }

    /**
     * Zwraca zniżkę
     *
     * @return the znizka
     */
    public double getZnizka() {
        return znizka;
    }

    /**
     * Ustawia zniżkę
     *
     * @param znizka the znizka to set
     */
    public void setZnizka(double znizka) {
        this.znizka = znizka;
    }

    /**
     * Metoda zwracająca sformatowaną listę posiłków, wykorzystywana także w
     * klasie zamówienie
     *
     * @param listaP - listaw posiłków
     * @return sformatowany opis posiłków
     */
    public String getListaSkladowychPosilkow(List<Posilek> listaP) {
        String element = " ";
        for (Posilek posilek : listaP) {
            element += posilek.wyswietlInformacje() + " ";
        }
        return element;
    }

    /**
     * Ustawia listę posiłków w zestawie
     *
     * @param listaPosilkow the listaPosilkow to set
     */
    public void setListaPosilkow(List<Posilek> listaPosilkow) {
        this.listaPosilkow = listaPosilkow;
    }

    /**
     * Zwraca cenę zestawu
     *
     * @return the cenaZestawu
     */
    public double getCenaZestawu() {
        return Math.round(cenaZestawu * 100.0) / 100.0;
    }

    /**
     * Ustawia cenę zestawu
     *
     * @param cenaZestawu the cenaZestawu to set
     */
    public void setCenaZestawu(double cenaZestawu) {
        this.cenaZestawu = cenaZestawu;
    }

    /**
     * Zwraca listę posiłków
     *
     * @return the listaPosilkow
     */
    public List<Posilek> getListaPosilkow() {
        return listaPosilkow;
    }

}
