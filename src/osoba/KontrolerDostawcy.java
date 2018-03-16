/* 
 * Projekt stworzony przez
 * Pawel Ruminkiewicz
 * Nr indeksu: 127212
 */
package osoba;

import java.io.Serializable;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Pawel
 *
 * Klasa kontrolera dostawcy, odpowiedzialna za zdarzenia do niego przypisane
 */
public class KontrolerDostawcy implements EventHandler<MouseEvent>, Serializable {

    private TextArea pole;
    private Button usun;
    private Button powrot;
    private Button odswiez;

    /**
     * Kontstruktor kontrolera
     *
     * @param poleInformacyjne - okno po prawej stronie aplikacji
     * @param przyciskUsuniecia - przycisk usuń element
     * @param przyciskPowrotu - przycisk powrotu dostawcy
     * @param przyciskOdswiezenia - przycisk odświeżania
     */
    public KontrolerDostawcy(TextArea poleInformacyjne, Button przyciskUsuniecia, Button przyciskPowrotu, Button przyciskOdswiezenia) {
        pole = poleInformacyjne;
        usun = przyciskUsuniecia;
        powrot = przyciskPowrotu;
        odswiez = przyciskOdswiezenia;
    }

    /**
     * Metoda po kliknięciu na ikone dostawcy
     *
     * @param event - zdarzenie dla myszki
     */
    @Override
    public synchronized void handle(MouseEvent event) {
        if (((Node) event.getSource()).getUserData() instanceof Dostawca) {
            Dostawca dostawca = (Dostawca) (((Node) event.getSource()).getUserData());
            getPole().clear();
            getPole().appendText(dostawca.wyswietlInfo());
            restauracja.Restauracja.setOstatniKlikniety(dostawca);
            getUsun().disableProperty().set(false);
            getPowrot().disableProperty().set(false);
            getOdswiez().disableProperty().set(false);
        } else {
            System.out.println("Nie znaleziono dostawcy");
        }
    }

    /**
     * Zwraca element TextArea okna aplikacji
     *
     * @return the pole
     */
    public TextArea getPole() {
        return pole;
    }

    /**
     * Ustawia element TextArea okna aplikacji
     *
     * @param pole the pole to set
     */
    public void setPole(TextArea pole) {
        this.pole = pole;
    }

    /**
     * Zwraca element przycisk usuń
     *
     * @return the usun
     */
    public Button getUsun() {
        return usun;
    }

    /**
     * Ustawia element przycisk usuń
     *
     * @param usun the usun to set
     */
    public void setUsun(Button usun) {
        this.usun = usun;
    }

    /**
     * Zwraca element przycisk powrotu
     *
     * @return the powrot
     */
    public Button getPowrot() {
        return powrot;
    }

    /**
     * Ustawia element przycisk powrotu
     *
     * @param powrot the powrot to set
     */
    public void setPowrot(Button powrot) {
        this.powrot = powrot;
    }

    /**
     * Zwraca element przycisk odśwież
     *
     * @return the odswiez
     */
    public Button getOdswiez() {
        return odswiez;
    }

    /**
     * Ustawia element przycisk odśwież
     *
     * @param odswiez the odswiez to set
     */
    public void setOdswiez(Button odswiez) {
        this.odswiez = odswiez;
    }
}
