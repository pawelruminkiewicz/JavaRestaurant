/* 
 * Projekt stworzony przez
 * Pawel Ruminkiewicz
 * Nr indeksu: 127212
 */
package jedzenie;

import java.util.ArrayList;
import java.util.List;
import osoba.Klient;
import restauracja.Restauracja;

/**
 *
 * @author Pawel Klasa reprezentująca zamówienie, dziedziczy ono po zestawie
 * obiadowym
 */
public final class Zamowienie extends ZestawObiadowy{

    private double calkowityKoszt;
    private static double minimalnaKwotaDarmowegoDowozu = 150.00;
    private double kwotaDowozu;
    private List<Posilek> listaSkladowychPosilkow;
    private List<ZestawObiadowy> listaSkladowychZestawow;
    private Klient klient;

    /**
     * Ustawia minimalną kwotę, od której dowóz jest bezpłatny
     *
     * @param minimalnaKwotaDarmowegoDowozu
     */
    public void setMinimalnaKwotaDarmowegoDowozu(float minimalnaKwotaDarmowegoDowozu) {
        this.setMinimalnaKwotaDarmowegoDowozu(minimalnaKwotaDarmowegoDowozu);
    }

    /**
     * Ustawia kwotę za dowóz
     *
     * @param kwotaDowozu the kwotaDowozu to set
     */
    public void setKwotaDowozu(double kwotaDowozu) {
        this.kwotaDowozu = kwotaDowozu;
    }

    /**
     * Konstruktor zamówienia, losujący dla tworzonego obiektu wszystkie
     * wartości
     */
    public Zamowienie() {
        listaSkladowychPosilkow = new ArrayList();
        listaSkladowychZestawow = new ArrayList();
        int liczbaPosilkowWZamowieniu = (int) (Math.random() * (Restauracja.getMenuRestauracji().size() - 1) + 1);
        for (int i = 0; i < liczbaPosilkowWZamowieniu; i++) {
            listaSkladowychPosilkow.add(Restauracja.getMenuRestauracji().get(i));
            calkowityKoszt += listaSkladowychPosilkow.get(i).getCena();
        }
        int liczbaZestawowWZamowieniu = (int) (Math.random() * 2 + 1);
        for (int i = 0; i < liczbaZestawowWZamowieniu; i++) {
            listaSkladowychZestawow.add(new ZestawObiadowy());
            calkowityKoszt += listaSkladowychZestawow.get(i).getCenaZestawu();
        }
        klient = Restauracja.getKlienci().get((int) (Math.random() * (Restauracja.getKlienci().size())));
        ustalKwoteDowozu();
        calkowityKoszt += kwotaDowozu;
        klient.oplacZamowienie(this);
    }

    /**
     * Metoda ustalająca kwotę dowozu na podstawie minimalnej kwoty darmowego
     * dowozu i odległości klienta od restauracji
     */
    public void ustalKwoteDowozu() {
        if (getCalkowityKoszt() > getMinimalnaKwotaDarmowegoDowozu()) {
            setKwotaDowozu(0);
        } else {
            setKwotaDowozu(this.getKlient().getOdlegloscOdRestauracji() * 0.25);
        }
    }

    /**
     * Metoda toString
     *
     * @return opis obiektu klasy zamówienie
     */
    @Override
    public String toString() {
        return "Całkowity kosztu: " + getCalkowityKoszt()
                + "; kwota dowozu: " + getKwotaDowozu()
                + ";  posilki w zamowieniu: " + getListaSkladowychPosilkow(getListaSkladowychPosilkow())
                + "; zestawy w zamowieniu: " + getListaSkladowychZestawow()
                + "; klient: " + getKlient().toString();
    }

    /**
     * Metoda generująca sofrmatowany String dla okna informacyjnego
     *
     * @return sformatowany opis zamówienia
     */
    @Override
    public String wyswietlInformacje() {
        return "+ Całkowity kosztu: " + getCalkowityKoszt()
                + "\n+ kwota dowozu: " + getKwotaDowozu()
                + "\n+ klient: " + getKlient().getImie() + " " + getKlient().getNazwisko()
                + "\n+ posilki w zamowieniu: " + getListaSkladowychPosilkow(getListaSkladowychPosilkow())
                + "\n+ zestawy w zamowieniu: " + getListaSkladowychZestawow();
    }

    /**
     * Metoda zwracająca sformatowany opis zestawów obiadowych
     *
     * @return opis zestawów
     */
    public String getListaSkladowychZestawow() {
        String element = " ";
        for (ZestawObiadowy zestawObiadowy : listaSkladowychZestawow) {
            element += zestawObiadowy.wyswietlInformacje();
        }
        return element;
    }

    /**
     * Zwraca całkowity koszt zamówienia
     *
     * @return the calkowityKoszt
     */
    public double getCalkowityKoszt() {
        return Math.round(calkowityKoszt * 100.0) / 100.0;
    }

    /**
     * Ustawia całkowity koszt zamówienia
     *
     * @param calkowityKoszt the calkowityKoszt to set
     */
    public void setCalkowityKoszt(double calkowityKoszt) {
        this.calkowityKoszt = calkowityKoszt;
    }

    /**
     * Zwraca kwotę za dowóz
     *
     * @return the kwotaDowozu
     */
    public double getKwotaDowozu() {
        return Math.round(kwotaDowozu * 100.0) / 100.0;
    }

    /**
     * Zwraca obiekt klienta, który zamówił
     *
     * @return the klient
     */
    public Klient getKlient() {
        return klient;
    }

    /**
     * Ustawia obiekt klienta, który zamówił
     *
     * @param klient the klient to set
     */
    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    /**
     * Zwraca minimalną kwotę, od której dowóz jest darmowy
     *
     * @return the minimalnaKwotaDarmowegoDowozu
     */
    public static double getMinimalnaKwotaDarmowegoDowozu() {
        return minimalnaKwotaDarmowegoDowozu;
    }

    /**
     * Ustawia minimalną kwotę, od której dowóz jest darmowy
     *
     * @param aMinimalnaKwotaDarmowegoDowozu the minimalnaKwotaDarmowegoDowozu
     * to set
     */
    public static void setMinimalnaKwotaDarmowegoDowozu(double aMinimalnaKwotaDarmowegoDowozu) {
        minimalnaKwotaDarmowegoDowozu = aMinimalnaKwotaDarmowegoDowozu;
    }

    /**
     * Zwraca liste posiłków w zamówieniu
     *
     * @return the listaSkladowychPosilkow
     */
    public List<Posilek> getListaSkladowychPosilkow() {
        return listaSkladowychPosilkow;
    }

    /**
     * Ustawia liste posiłków w zamówieniu
     *
     * @param listaSkladowychPosilkow the listaSkladowychPosilkow to set
     */
    public void setListaSkladowychPosilkow(List<Posilek> listaSkladowychPosilkow) {
        this.listaSkladowychPosilkow = listaSkladowychPosilkow;
    }

    /**
     * Ustawia listę zestawów w zamówieniu
     *
     * @param listaSkladowychZestawow the listaSkladowychZestawow to set
     */
    public void setListaSkladowychZestawow(List<ZestawObiadowy> listaSkladowychZestawow) {
        this.listaSkladowychZestawow = listaSkladowychZestawow;
    }

}
