/* 
 * Projekt stworzony przez
 * Pawel Ruminkiewicz
 * Nr indeksu: 127212
 */
package restauracja;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import jedzenie.Posilek;
import jedzenie.Zamowienie;
import osoba.Dostawca;
import osoba.Klient;
import osoba.KlientFirmowy;
import osoba.KlientOkazjonalny;
import osoba.KlientStaly;
import osoba.KontrolerDostawcy;
import osoba.KontrolerKlienta;
import osoba.Osoba;

/**
 * FXML Controller class
 *
 * @author Pawel
 */
public class FXMLController implements Initializable, Serializable {

    @FXML
    private Button przyciskWczytywania;
    @FXML
    private Button przyciskZapisu;
    @FXML
    private Button przyciskNowegoPosilku;
    @FXML
    private Button przyciskNowegoZamowienia;
    @FXML
    private Button przyciskNowegoKlienta;
    @FXML
    private Button przyciskNowegoDostawcy;
    @FXML
    private Button przyciskUsuniecia;
    @FXML
    private Button przyciskPowrotuDoRestauracji;
    @FXML
    private Button przyciskOdswiezenia;
    @FXML
    private Pane mapaMiasta;
    @FXML
    private TextArea poleInformacyjne;

    /**
     * Metoda odpowiedzialna za wczytywanie stanu z pliku
     * @param mouseEvent
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void wczytajStan(MouseEvent mouseEvent) throws IOException, ClassNotFoundException {

        try {
            String nazwaPliku = "stan.out";

            try (ObjectInputStream in = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(nazwaPliku)))) {
                getPoleInformacyjne().clear();
                
                for (int i = 0; i < Restauracja.getKlienci().size(); i++) {
                    getMapaMiasta().getChildren().remove(Restauracja.getKlienci().get(i).getIkona());
                }
                while (Restauracja.getKlienci().size() > 0) {
                    Restauracja.getKlienci().get(0).usunKlientaZMapy();
                    
                }
                for (int i = 0; i < Restauracja.getDostawcy().size(); i++) {
                    getMapaMiasta().getChildren().remove(Restauracja.getDostawcy().get(i).getIkona());
                }
                while (Restauracja.getDostawcy().size() > 0) {
                    Restauracja.getDostawcy().get(0).usunDostawceZMapy();
                }
                
                Restauracja.getDostawcy().clear();
                Restauracja.getKlienci().clear();
                Restauracja.getMenuRestauracji().clear();
                Restauracja.setOstatniKlikniety(null);
                Restauracja.getZamowienia().clear();
                
                Restauracja.setDostawcy((List<Dostawca>) in.readObject());
                Restauracja.setKlienci((List<Klient>) in.readObject());
                Restauracja.setMenuRestauracji((List<Posilek>) in.readObject());
                Restauracja.setOstatniKlikniety((Object) in.readObject());
                Restauracja.setZamowienia((List<Zamowienie>) in.readObject());
            }
            for (int i = 0; i < Restauracja.getKlienci().size(); i++) {
                Thread watek = new Thread(Restauracja.getKlienci().get(i));
                watek.setDaemon(true);
                Restauracja.getKlienci().get(i).rysuj(this.getMapaMiasta());
                watek.start();
                System.out.println(Restauracja.getKlienci().get(i).toString());      
                Restauracja.getKlienci().get(i).getIkona().setOnMouseClicked(new KontrolerKlienta(this.getPoleInformacyjne(), this.getPrzyciskUsuniecia(), this.getPrzyciskOdswiezenia(),this.przyciskPowrotuDoRestauracji));
            }
            for (int i = 0; i < Restauracja.getDostawcy().size(); i++) {
                Thread watek = new Thread(Restauracja.getDostawcy().get(i));
                watek.setDaemon(true);
                Restauracja.getDostawcy().get(i).rysuj(this.getMapaMiasta());
                watek.start();
                System.out.println(Restauracja.getDostawcy().get(i).toString());     
                Restauracja.getDostawcy().get(i).getIkona().setOnMouseClicked(new KontrolerDostawcy(this.getPoleInformacyjne(), this.getPrzyciskUsuniecia(), this.getPrzyciskPowrotuDoRestauracji(), this.getPrzyciskOdswiezenia()));

            }
            przyciskNowegoKlienta.disableProperty().set(false);
            przyciskNowegoZamowienia.disableProperty().set(false);
            przyciskNowegoDostawcy.disableProperty().set(false);
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metoda odpowiedzialna za zapisywanie stanu do pliku
     * @param mouseEvent
     * @throws IOException
     */
    public void zapiszStan(MouseEvent mouseEvent) throws IOException {
        przyciskWczytywania.disableProperty().set(false);
        String nazwaPliku = "stan.out";

        try (ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(nazwaPliku)))) {
            out.writeObject(Restauracja.getDostawcy());
            out.writeObject(Restauracja.getKlienci());
            out.writeObject(Restauracja.getMenuRestauracji());
            out.writeObject(Restauracja.getOstatniKlikniety());
            out.writeObject(Restauracja.getZamowienia());
            out.close();
        }
        

    }

    /**
     * Metoda wykonywana po zdarzeniu kliknięcia na przycisk nowego posiłku
     *
     * @param mouseEvent - zdarzenie
     */
    public void nowyPosilekDoMenu(MouseEvent mouseEvent) {
        Posilek posilek = new Posilek();
        Restauracja.getMenuRestauracji().add(posilek);
        System.out.println(posilek.toString());
        getPrzyciskNowegoKlienta().disableProperty().set(false);
    }

    /**
     * Metoda wykonywana po zdarzeniu kliknięcia na przycisk nowego zamówienia
     *
     * @param mouseEvent - zdarzenie
     */
    public void noweZamowienie(MouseEvent mouseEvent) {
        Zamowienie zamowienie = new Zamowienie();
        System.out.println(zamowienie.toString());
        Restauracja.getZamowienia().add(zamowienie);
    }

    /**
     * Metoda wykonywana po zdarzeniu kliknięcia na przycisk nowego klienta
     *
     * @param mouseEvent - zdarzenie
     */
    public void nowyKlient(MouseEvent mouseEvent) {
        Klient klient;
        int ktoryKlient = new Random().nextInt(3);
        switch (ktoryKlient) {
            case 0:
                klient = new KlientFirmowy();
                break;
            case 1:
                klient = new KlientOkazjonalny();
                break;
            default:
                klient = new KlientStaly();
                break;
        }
        Thread watek = new Thread(klient);
        watek.setDaemon(true);
        klient.rysuj(this.getMapaMiasta());
        watek.start();
        Restauracja.getKlienci().add(klient);
        System.out.println(klient.toString());        
        klient.getIkona().setOnMouseClicked(new KontrolerKlienta(this.getPoleInformacyjne(), this.getPrzyciskUsuniecia(), this.getPrzyciskOdswiezenia(),this.przyciskPowrotuDoRestauracji));
        getPrzyciskNowegoDostawcy().disableProperty().set(false);
        getPrzyciskNowegoZamowienia().disableProperty().set(false);

    }

  
    /**
     * Metoda wykonywana po zdarzeniu kliknięcia na przycisk nowego dostawcy
     *
     * @param mouseEvent - zdarzenie
     *
     */
    public void nowyDostawca(MouseEvent mouseEvent) {
        Dostawca dostawca = new Dostawca();
        Thread watek = new Thread(dostawca);
        watek.setDaemon(true);
        dostawca.rysuj(this.getMapaMiasta());
        watek.start();
        Restauracja.getDostawcy().add(dostawca);
        System.out.println(dostawca.toString());        
        dostawca.getIkona().setOnMouseClicked(new KontrolerDostawcy(this.getPoleInformacyjne(), this.getPrzyciskUsuniecia(), this.getPrzyciskPowrotuDoRestauracji(), this.getPrzyciskOdswiezenia()));

    }

    /**
     * Metoda wykonywana po kliknięciu na ikonę restauracji, zwraca listę
     * zamówień
     *
     * @param mouseEvent
     */
    public void listaZamowienRestauracji(MouseEvent mouseEvent) {
        przyciskPowrotuDoRestauracji.disableProperty().set(true);
        przyciskUsuniecia.disableProperty().set(true);
        przyciskOdswiezenia.disableProperty().set(false);
        Restauracja.setOstatniKlikniety(this);
        getPoleInformacyjne().clear();
        for (int i = 0; i < Restauracja.getZamowienia().size(); i++) {
            getPoleInformacyjne().appendText(
                    "\n********************************************************************\n"
                    + "*********************** Z A M Ó W I E N I E ***********************\n"
                    + "********************************************************************\n"
                    + Restauracja.getZamowienia().get(i).wyswietlInformacje()
                    );
        }
    }

    /**
     * Metoda wykonywana po zdarzeniu kliknięcia na przycisk usunięcia elementu
     *
     * @param mouseEvent - zdarzenie
     *
     */
    public void usunElement(MouseEvent mouseEvent) {
        if (Restauracja.getOstatniKlikniety() instanceof Klient) {
            Klient klientDoUSuniecia = (Klient) Restauracja.getOstatniKlikniety();
            getPoleInformacyjne().clear();
            klientDoUSuniecia.usunKlientaZMapy();
            getMapaMiasta().getChildren().remove(klientDoUSuniecia.getIkona());

        } else if (Restauracja.getOstatniKlikniety() instanceof Dostawca) {
            Dostawca dostawcaDoUSuniecia = (Dostawca) Restauracja.getOstatniKlikniety();
            getPoleInformacyjne().clear();
            dostawcaDoUSuniecia.usunDostawceZMapy();
            getMapaMiasta().getChildren().remove(dostawcaDoUSuniecia.getIkona());

        }
        getPrzyciskUsuniecia().disableProperty().set(true);
    }

    /**
     * Metoda wykonywana po zdarzeniu kliknięcia na przycisk powrotu do
     * restauracji
     *
     * @param mouseEvent - zdarzenie
     *
     */
    public void powrotDoRestauracji(MouseEvent mouseEvent) {
        if (Restauracja.getOstatniKlikniety() instanceof Dostawca) {
            Dostawca dostawca = (Dostawca) Restauracja.getOstatniKlikniety();
            dostawca.setPowrocDoRestauracji(true);
            dostawca.setPowodPowrotuDoRestauracji("żądanie właściciela lokalu");
        }
    }

    /**
     * Metoda wykonywana po zdarzeniu kliknięcia na przycisk odświeżenia
     *
     * @param mouseEvent - zdarzenie
     *
     */
    public void odswiezWidok(MouseEvent mouseEvent) {

        if (Restauracja.getOstatniKlikniety() instanceof Klient) {
            Klient klientDoUSuniecia = (Klient) Restauracja.getOstatniKlikniety();
            getPoleInformacyjne().clear();
            getPoleInformacyjne().appendText(klientDoUSuniecia.wyswietlInfo());
        } else if (Restauracja.getOstatniKlikniety() instanceof Dostawca) {
            Dostawca dostawcaDoUsuniecia = (Dostawca) Restauracja.getOstatniKlikniety();
            getPoleInformacyjne().clear();
            getPoleInformacyjne().appendText(dostawcaDoUsuniecia.wyswietlInfo());
            restauracja.Restauracja.setOstatniKlikniety(dostawcaDoUsuniecia);
        }
        else{
            listaZamowienRestauracji(null);
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(new File("stan.out").exists()) przyciskWczytywania.disableProperty().set(false);
        else przyciskWczytywania.disableProperty().set(true);
        // TODO
    }

    /**
     * Zwraca mapę
     *
     * @return the mapaMiasta
     */
    public Pane getMapaMiasta() {
        return mapaMiasta;
    }

    /**
     * Ustawia mapę
     *
     * @param mapaMiasta the mapaMiasta to set
     */
    public void setMapaMiasta(Pane mapaMiasta) {
        this.mapaMiasta = mapaMiasta;
    }

    /**
     * Zwraca przycisk powrotu do restauracji
     *
     * @return the przyciskPowrotuDoRestauracji
     */
    public Button getPrzyciskPowrotuDoRestauracji() {
        return przyciskPowrotuDoRestauracji;
    }

    /**
     * Ustawia przycisk powrotu do restauracji
     *
     * @param przyciskPowrotuDoRestauracji the przyciskPowrotuDoRestauracji to
     * set
     */
    public void setPrzyciskPowrotuDoRestauracji(Button przyciskPowrotuDoRestauracji) {
        this.przyciskPowrotuDoRestauracji = przyciskPowrotuDoRestauracji;
    }

    /**
     * Zwraca przycisk nowego posiłku
     *
     * @return the przyciskNowegoPosilku
     */
    public Button getPrzyciskNowegoPosilku() {
        return przyciskNowegoPosilku;
    }

    /**
     * Ustawia przycisk nowego posiłku
     *
     * @param przyciskNowegoPosilku the przyciskNowegoPosilku to set
     */
    public void setPrzyciskNowegoPosilku(Button przyciskNowegoPosilku) {
        this.przyciskNowegoPosilku = przyciskNowegoPosilku;
    }

    /**
     * Zwraca przycisk nowego zamówienia
     *
     * @return the przyciskNowegoZamowienia
     */
    public Button getPrzyciskNowegoZamowienia() {
        return przyciskNowegoZamowienia;
    }

    /**
     * Ustawia przycisk nowego zamówienia
     *
     * @param przyciskNowegoZamowienia the przyciskNowegoZamowienia to set
     */
    public void setPrzyciskNowegoZamowienia(Button przyciskNowegoZamowienia) {
        this.przyciskNowegoZamowienia = przyciskNowegoZamowienia;
    }

    /**
     * Zwraca przycisk nowego klienta
     *
     * @return the przyciskNowegoKlienta
     */
    public Button getPrzyciskNowegoKlienta() {
        return przyciskNowegoKlienta;
    }

    /**
     * Ustawia przycisk nowego klienta
     *
     * @param przyciskNowegoKlienta the przyciskNowegoKlienta to set
     */
    public void setPrzyciskNowegoKlienta(Button przyciskNowegoKlienta) {
        this.przyciskNowegoKlienta = przyciskNowegoKlienta;
    }

    /**
     * Zwraca przycisk nowego dostawcy
     *
     * @return the przyciskNowegoDostawcy
     */
    public Button getPrzyciskNowegoDostawcy() {
        return przyciskNowegoDostawcy;
    }

    /**
     * Ustawia przycisk nowego dostawcy
     *
     * @param przyciskNowegoDostawcy the przyciskNowegoDostawcy to set
     */
    public void setPrzyciskNowegoDostawcy(Button przyciskNowegoDostawcy) {
        this.przyciskNowegoDostawcy = przyciskNowegoDostawcy;
    }

    /**
     * Zwraca przycisk usunięcia
     *
     * @return the przyciskUsuniecia
     */
    public Button getPrzyciskUsuniecia() {
        return przyciskUsuniecia;
    }

    /**
     * Ustawia przycisk usunięcia
     *
     * @param przyciskUsuniecia the przyciskUsuniecia to set
     */
    public void setPrzyciskUsuniecia(Button przyciskUsuniecia) {
        this.przyciskUsuniecia = przyciskUsuniecia;
    }

    /**
     * Zwraca przycisk odświeżenia
     *
     * @return the przyciskOdswiezenia
     */
    public Button getPrzyciskOdswiezenia() {
        return przyciskOdswiezenia;
    }

    /**
     * Ustawia przycisk odświeżenia
     *
     * @param przyciskOdswiezenia the przyciskOdswiezenia to set
     */
    public void setPrzyciskOdswiezenia(Button przyciskOdswiezenia) {
        this.przyciskOdswiezenia = przyciskOdswiezenia;
    }

    /**
     * Zwraca pole informacyjne typu TextArea
     *
     * @return the poleInformacyjne
     */
    public TextArea getPoleInformacyjne() {
        return poleInformacyjne;
    }

    /**
     * Ustawia pole informacyjne typu TextArea
     *
     * @param poleInformacyjne the poleInformacyjne to set
     */
    public void setPoleInformacyjne(TextArea poleInformacyjne) {
        this.poleInformacyjne = poleInformacyjne;
    }

}
