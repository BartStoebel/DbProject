/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbctest;

import UI.UI;
import model.Transactie;
import voorraadbeheerconnection.VoorraadbeheerConnection;

/**
 *
 * @author Bart.Stoebel
 */
public class JDBCTestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UI userInterface = new UI();
        VoorraadbeheerConnection vbcon = new VoorraadbeheerConnection();
        boolean herhaal = true;
        Transactie transactie = new Transactie();
        while (herhaal){
            while (!userInterface.user_input(transactie)){
            }
            vbcon.updateVoorraden(transactie);
            herhaal = userInterface.herhaal();
        }
    }
}
