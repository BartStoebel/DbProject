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
public class TestJDBC {
    private int vanMagazijn;
    private int naarMagazijn;
    private int artikelId;
    private int aantal;
    
    
    public boolean UI() {
        boolean ok = false;
        Scanner scan = new Scanner(System.in);
        System.out.println("Uit welk magazijn moet de voorraad worden gehaald?");
        try/*(Scanner scan = new Scanner(System.in))*/{
            vanMagazijn = Integer.parseInt(scan.nextLine());
            System.out.println("Naar welk magazijn moet de voorraad worden verstuurd?");
            naarMagazijn = Integer.parseInt(scan.nextLine());
            System.out.println("Over welk artikelId gaat het?");
            artikelId = Integer.parseInt(scan.nextLine());
            System.out.println("Over hoeveel stuks gaat het?");
            aantal = Integer.parseInt(scan.nextLine());
            return true;
        } catch(NumberFormatException e){
            e.printStackTrace();
            return false;
        }
    }
    
    
}
