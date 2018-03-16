/* 
 * Projekt stworzony przez
 * Pawel Ruminkiewicz
 * Nr indeksu: 127212
 */
package osoba;

/**
 *
 * @author Pawel
 *
 * Klasa reprezentująca klienta firmowego
 */
public class KlientFirmowy extends Klient{

    private String adresKorespondencyjny;
    private String oAdresKorespondencyjny[] = {"Poznań, ul. Akacjowa ", "Poznań, ul. Mickiewicza ", "Poznań, ul. Słowackiego ",
        "Poznań, ul. Słoneczna ", "Poznań, ul. Kochanowskiego ", "Poznań, ul. Kutrzeby ",
        "Poznań, ul. Armii Poznań ", "Poznań, ul. Matejki ", "Poznań, ul. Ratajczaka "};
    private String nrKontaBankowego;
    private int regon;

    /**
     * Zwraca adres korespondencyjny
     *
     * @return the adresKorespondencyjny
     */
    public String getAdresKorespondencyjny() {
        return adresKorespondencyjny;
    }

    /**
     * Ustawia adres korespondencyjny
     *
     * @param adresKorespondencyjny the adresKorespondencyjny to set
     */
    public void setAdresKorespondencyjny(String adresKorespondencyjny) {
        this.adresKorespondencyjny = adresKorespondencyjny;
    }

    /**
     * Zwraca regon
     *
     * @return the regon
     */
    public int getRegon() {
        return regon;
    }

    /**
     * Ustawia regon
     *
     * @param regon the regon to set
     */
    public void setRegon(int regon) {
        this.regon = regon;
    }

    /**
     * Konstruktor klienta firmowego, losujący dla tworzonego obiektu wszystkie
     * wartości
     */
    public KlientFirmowy() {
        super();
        super.setSciezkaIkony("/ikony/klientfirmowy.png");
        adresKorespondencyjny = oAdresKorespondencyjny[(int) (Math.random() * (oAdresKorespondencyjny.length - 1))] + String.valueOf((int) (Math.random() * 888 + 111));
        nrKontaBankowego = "PL32-1140-0000-0877-1150-" + String.valueOf((int) (Math.random() * 8888 + 1111)) + "-" + String.valueOf((int) (Math.random() * 8888 + 1111));
        regon = 123456000 + (int) (Math.random() * 888 + 111);

    }

    /**
     * Metoda toString
     *
     * @return zwraca opis obiektu klasy klient firmowy
     */
    @Override
    public String toString() {
        return super.toString()
                + "adres korespondencyjny: " + getAdresKorespondencyjny()
                + "; nr konta bakowego: " + getNrKontaBankowego()
                + "; REGON: " + getRegon();
    }

    /**
     * Metoda generująca sofrmatowany String dla okna informacyjnego
     *
     * @return sformatowany opis klienta firmowego
     */
    @Override
    public String wyswietlInfo() {
        return super.wyswietlInfo()
                + "\n+Adres korespondencyjny: " + getAdresKorespondencyjny()
                + "\n+Nr konta bankowego: " + getNrKontaBankowego()
                + "\n+Regon: " + getRegon();
    }

    /**
     * Zwraca nr konta bankowego
     *
     * @return the nrKontaBankowego
     */
    public String getNrKontaBankowego() {
        return nrKontaBankowego;
    }

    /**
     * Zwraca adres korespondencyjny
     *
     * @return the oAdresKorespondencyjny
     */
    public String[] getoAdresKorespondencyjny() {
        return oAdresKorespondencyjny;
    }

    /**
     * Ustawia listę losowych adresów korespondencyjnych
     *
     * @param oAdresKorespondencyjny the oAdresKorespondencyjny to set
     */
    public void setoAdresKorespondencyjny(String[] oAdresKorespondencyjny) {
        this.oAdresKorespondencyjny = oAdresKorespondencyjny;
    }

    /**
     * Ustawia nr konta bankowego
     *
     * @param nrKontaBankowego the nrKontaBankowego to set
     */
    public void setNrKontaBankowego(String nrKontaBankowego) {
        this.nrKontaBankowego = nrKontaBankowego;
    }

}
