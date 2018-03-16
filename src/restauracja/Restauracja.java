/* 
 * Projekt stworzony przez
 * Pawel Ruminkiewicz
 * Nr indeksu: 127212
 */
package restauracja;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jedzenie.Posilek;
import jedzenie.Zamowienie;
import osoba.Dostawca;
import osoba.Klient;
import osoba.Osoba;

/**
 *
 * @author Pawel
 * 
 * Klasa główna projketu
 */
public class Restauracja extends Application implements Serializable{
    private static List <Dostawca> dostawcy;
    private static List <Klient> klienci;
    private volatile static List <Zamowienie> zamowienia;
    private static List <Posilek> menuRestauracji;
    private volatile static boolean [][] polaMapy;
    private volatile static boolean [][] polaMapyDostawcow;
    private static Object ostatniKlikniety;
    
    /**
     * Metoda inicjalizująca kolekcje
     */
    public void stworzKolekcje(){
        dostawcy = new ArrayList<>();
        klienci = new ArrayList<>();
        zamowienia = new ArrayList<>();
        menuRestauracji = new ArrayList<>();
        setPolaMapy(new boolean[600][600]);
        setPolaMapyDostawcow(new boolean[600][600]);
    }


    
/**
 * Metoda uruchamiająca
 * @param stage
 * @throws Exception 
 */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        stworzKolekcje();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Symulator Restauracji 1.0");
        stage.show();
    }

    /**
     * Metoda main
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);       
        
    }
    /**
     * Zwraca listę dostawców
     * @return the dostawcy
     */
    public static List <Dostawca> getDostawcy() {
        return dostawcy;
    }
    /**
     * Metoda czyści liste zamówień
     */
    public static synchronized void odbierzZamowienia(){
        zamowienia.clear();
    }

    /**
     * Zwraca liste dostawców
     * @param aDostawcy the dostawcy to set
     */
    public static void setDostawcy(List <Dostawca> aDostawcy) {
        dostawcy = aDostawcy;
    }

    /**
     * Zwraca listę klientów
     * @return the klienci
     */
    public static List <Klient> getKlienci() {
        return klienci;
    }

    /**
     * Ustawia listę klientów
     * @param aKlienci the klienci to set
     */
    public static void setKlienci(List <Klient> aKlienci) {
        klienci = aKlienci;
    }

    /**
     * Zwraca listę zamówień
     * @return the zamowienia
     */
    public static List <Zamowienie> getZamowienia() {
        return zamowienia;
    }

    /**
     * Ustawia listę zamówień
     * @param aZamowienia the zamowienia to set
     */
    public static void setZamowienia(List <Zamowienie> aZamowienia) {
        zamowienia = aZamowienia;
    }


    /**
     * Zwraca listę menu restauracji
     * @return the menuRestauracji
     */
    public static List <Posilek> getMenuRestauracji() {
        return menuRestauracji;
    }

    /**
     * Ustawia listę menu restauracji
     * @param aMenuRestauracji the menuRestauracji to set
     */
    public static void setMenuRestauracji(List <Posilek> aMenuRestauracji) {
        menuRestauracji = aMenuRestauracji;
    }

    /**
     * Zwraca obiekt ostatnio kliknięty
     * @return the ostaniKlikniety
     */
    public static Object getOstatniKlikniety() {
        return ostatniKlikniety;
    }

    /**
     * Ustawia ostatnio kliknięty obiekt
     * @param aOstaniKlikniety the ostaniKlikniety to set
     */
    public static void setOstatniKlikniety(Object aOstaniKlikniety) {
        ostatniKlikniety = aOstaniKlikniety;
    }

    /**
     * Zwraca pola mapy
     * @return the polaMapy
     */
    public static boolean[][] getPolaMapy() {
        return polaMapy;
    }

    /**
     * Ustawia pola mapy
     * @param aPolaMapy the polaMapy to set
     */
    public static void setPolaMapy(boolean[][] aPolaMapy) {
        polaMapy = aPolaMapy;
    }

    /**
     * Zwraca pola mapy dostawców
     * @return the polaMapyDostawcow
     */
    public static boolean[][] getPolaMapyDostawcow() {
        return polaMapyDostawcow;
    }

    /**
     * Ustawia pola mapy dostawców
     * @param aPolaMapyDostawcow the polaMapyDostawcow to set
     */
    public static void setPolaMapyDostawcow(boolean[][] aPolaMapyDostawcow) {
        polaMapyDostawcow = aPolaMapyDostawcow;
    }


}
