/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import java.math.BigDecimal;

/**
 *
 * @author Vinnie
 */

    
    
public class BankController {
    
    
    private BankUI bankUI;
    private BankConnection bc;
    
    public BankController(){
        this.bankUI = new BankUI();
        this.bc = new BankConnection();
    }
    
    public void menu(){
        int userKeuze = 0;
        do {
            userKeuze = bankUI.userMenu();
        }while(userKeuze==0);
        if (userKeuze==1){
            long Rekeningnummer = bankUI.userInputRekeningnummer();
            if (Rekeningnummer ==0){
                menu();
            }else {
                bc.voegRekeningnummerToe(Rekeningnummer);
                menu();
            }
        } else if (userKeuze==2){
            long rekeningnummer = bankUI.userInputRekeningnummer();
            if (rekeningnummer ==0){
                menu();
            }else {
                bc.controleerSaldo(rekeningnummer);
                menu();
            }
        } else if (userKeuze == 3){
            System.out.println("Geef eerst rekeningnummer in van, daarna naar:");
            long van = bankUI.userInputRekeningnummer();
            if(van == 0){
                menu();
            }else{
                long naar = bankUI.userInputRekeningnummer();
                if (naar == 0){
                    menu();
                } else if (naar == van){
                    System.out.println("Beide rekeningnummers moeten verschillend zijn!");
                    menu();
                } else {
                    BigDecimal bd = bankUI.userInputBedrag();
                    if (bd ==BigDecimal.ZERO ){
                        menu();
                    } else {
                        bc.bedragOverschrijven(van, naar, bd);
                        menu();
                    }
                    
                    
                    
                }
            }
        }
    }
    
}
