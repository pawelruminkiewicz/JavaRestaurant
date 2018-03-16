/* 
 * Projekt stworzony przez
 * Pawel Ruminkiewicz
 * Nr indeksu: 127212
 */
package czas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pawel
 *
 * Klasa reprezentująca harmonogram pracy dostawcy
 */
public class HarmonogramPracy implements Serializable{
    private List<DzienRoboczy> TydzienRoboczy = new ArrayList();
    
    /**
     * Konstruktor harmonogramu pracy,
     * tworzący po kolei dni robocze (dzień roboczy jest składową całego harmonogramu)
     */
    public HarmonogramPracy() {
        for (int i = 0; i < 7; i++) {
            TydzienRoboczy.add(new DzienRoboczy(i));
        }
    }

    /**
     * Metoda toString
     * @return opis obiektu harmonogramu pracy
     */
    @Override
    public String toString() {
        String element = "{";
        for (DzienRoboczy dzienRoboczy : getTydzienRoboczy()) {
            element += "|" + dzienRoboczy.toString() + "|";
        }
        return element;
    }

     /**
     * Metoda generująca sofrmatowany String dla okna informacyjnego
     *
     * @return sformatowany opis harmonogramu pracy
     */
    public String wyswietlInformacje() {
        String element = "";
        for (DzienRoboczy dzienRoboczy : getTydzienRoboczy()) {
            element += dzienRoboczy.wyswietlInformacje() + "\n";
        }
        return element;
    }

    /**
     * Zwraca tydzień roboczy czyli kompletny harmonogram
     * @return the TydzienRoboczy
     */
    public List<DzienRoboczy> getTydzienRoboczy() {
        return TydzienRoboczy;
    }

    /**
     * Ustawia tydzień roboczy czyli kompletny harmonogram
     * @param TydzienRoboczy the TydzienRoboczy to set
     */
    public void setTydzienRoboczy(List<DzienRoboczy> TydzienRoboczy) {
        this.TydzienRoboczy = TydzienRoboczy;
    }

}
