/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.util.Scanner;
import model.Transactie;

/**
 *
 * @author Bart.Stoebel
 */
public class UI {
    
    public boolean user_input(Transactie transactie) {
        boolean ok = false;
        Scanner scan = new Scanner(System.in);
        System.out.println("Uit welk magazijn moet de voorraad worden gehaald?");
        try{
            //Niet met constructor gewerkt, omdat je per ingave >0 checkt en indien
            //niet een fout weergeeft.
            transactie.setVanMagazijn(Integer.parseInt(scan.nextLine()));
            System.out.println("Naar welk magazijn moet de voorraad worden verstuurd?");
            transactie.setNaarMagazijn(Integer.parseInt(scan.nextLine()));
            System.out.println("Over welk artikelId gaat het?");
            transactie.setArtikelId(Integer.parseInt(scan.nextLine()));
            System.out.println("Over hoeveel stuks gaat het?");
            transactie.setAantal(Integer.parseInt(scan.nextLine()));
            return true;
        } catch(NumberFormatException e){
            System.out.println("Gelieve enkel positieve numerieke waarden in te geven!");
            return false;
        }
    }
    
    public boolean herhaal(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Wil je nog een ingave doen?   (j om te bevestigen)");
        String herhaal = scan.nextLine();
        return herhaal.equals("j")|| herhaal.equals("J");
    }
}
