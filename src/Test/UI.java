package Test;


import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vinnie
 */
public class UI {
    private int vanMagazijn;
    private int naarMagazijn;
    private int artikelId;
    private int aantal;
    
    
    public boolean user_input() {
        boolean ok = false;
        Scanner scan = new Scanner(System.in);
        System.out.println("Uit welk magazijn moet de voorraad worden gehaald?");
        try/*(Scanner scan = new Scanner(System.in))*/{
            setVanMagazijn( Integer.parseInt(scan.nextLine()));
            System.out.println("Naar welk magazijn moet de voorraad worden verstuurd?");
            setNaarMagazijn(Integer.parseInt(scan.nextLine()));
            System.out.println("Over welk artikelId gaat het?");
            setArtikelId (Integer.parseInt(scan.nextLine()));
            System.out.println("Over hoeveel stuks gaat het?");
            setAantal (Integer.parseInt(scan.nextLine()));
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

    public int getVanMagazijn() {
        return vanMagazijn;
    }

    public int getNaarMagazijn() {
        return naarMagazijn;
    }

    public int getArtikelId() {
        return artikelId;
    }

    public int getAantal() {
        return aantal;
    }

    private final void setVanMagazijn(int vanMagazijn) {
        if (vanMagazijn>0){
            this.vanMagazijn = vanMagazijn;
        } else {
            throw new NumberFormatException();
        }
    }

    private final void setNaarMagazijn(int naarMagazijn) {
        if (naarMagazijn>0){
            this.naarMagazijn = naarMagazijn;
        } else {
            throw new NumberFormatException();
        }
    }

    private final void setArtikelId(int artikelId) {
        if (artikelId > 0){
            this.artikelId = artikelId;
        } else {
            throw new NumberFormatException();
        }
    }

    private final void setAantal(int aantal) {
        if (aantal > 0){
            this.aantal = aantal;
        } else {
            throw new NumberFormatException();
        }
    }
    
    
}
