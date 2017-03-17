/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author Vinnie
 */
public class BankUI {
    public int userMenu() {
        
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Nieuwe rekening");
        System.out.println("2. Saldo consulteren");
        System.out.println("3. Overschrijven");
        int a = 0;
        try{
            a = Integer.parseInt(scan.nextLine());
            while ((a < 1) || (a > 3 )){
                System.out.println("Gelieve enkel een numerieke waarde in te geven van 1 tot 3!");
                System.out.println("1. Nieuwe rekening");
                System.out.println("2. Saldo consulteren");
                System.out.println("3. Overschrijven");
                a = Integer.parseInt(scan.nextLine());
            }
        } catch(NumberFormatException e){
            System.err.println("Gelieve enkel een numerieke waarde in te geven van 1 tot 3!");
        }
        return a;
    }
    
    public long userInputRekeningnummer(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Geef het rekeningnummer in:");
        long a=0;
        try{
            String Rekeningnummer = scan.nextLine();
            if (Rekeningnummer.length() == 12){
                int rest = Integer.parseInt(Rekeningnummer.substring(10, Rekeningnummer.length()));
                a = Long.parseLong(Rekeningnummer);
                if (a/100%97 == rest){
                    return a;
                } 
            }
            System.out.println("Dit is geen geldig rekeningnummer");
        }catch (NumberFormatException e){
            System.err.println("Dit is geen geldig rekeningnummer");
        }
        return a;
    }
    
    public BigDecimal userInputBedrag(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Welk bedrag wenst u over te schrijven?");
        try{
            BigDecimal bd = BigDecimal.valueOf(Double.parseDouble(scan.nextLine()));
            if (bd.compareTo(BigDecimal.ZERO)>0){
                return bd;
            }
        }catch (NumberFormatException e ){
            System.err.println("Dit is geen geldig bedrag");
        }
        return BigDecimal.ZERO;
    }
    
    public boolean herhaal(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Wil je nog een ingave doen?   (j om te bevestigen)");
        String herhaal = scan.nextLine();
        return herhaal.equals("j")|| herhaal.equals("J");
    }
}
