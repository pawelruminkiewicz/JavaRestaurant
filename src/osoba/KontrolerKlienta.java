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
 * Klasa kontrolera klienta, odpowiedzialna za zdarzenia do niego przypisane
 */
public class KontrolerKlienta implements EventHandler<MouseEvent>, Serializable {

    private TextArea pole;
    private Button usun;
    private Button odswiez;
    private Button powrot;

    /**
     * Kontstruktor kontrolera
     *
     * @param poleInformacyjne - okno po prawej stronie aplikacji
     * @param przycisk - przycisk usuń element
     * @param przyciskOdswiezenia - przycisk odświeżania
     * @param przyciskPowrotu - przycisk powrotu do restauracji
     */
    public KontrolerKlienta(TextArea poleInformacyjne, Button przycisk, Button przyciskOdswiezenia, Button przyciskPowrotu) {
        pole = poleInformacyjne;
        usun = przycisk;
        odswiez = przyciskOdswiezenia;
        powrot=przyciskPowrotu;
    }

    /**
     * Metoda po kliknięciu na ikone klienta
     *
     * @param event - zdarzenie dla myszki
     */
    @Override
    public synchronized void handle(MouseEvent event) {
        if (((Node) event.getSource()).getUserData() instanceof Klient) {
            powrot.disableProperty().set(true);
            Klient klient = (Klient) (((Node) event.getSource()).getUserData());
            getPole().clear();
            getPole().appendText(klient.wyswietlInfo());
            restauracja.Restauracja.setOstatniKlikniety(klient);
            getUsun().disableProperty().set(false);
            getOdswiez().disableProperty().set(false);
        } else {
            System.out.println("\n\nNie znaleziono klienta\n\n");
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
