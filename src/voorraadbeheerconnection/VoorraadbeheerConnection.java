/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voorraadbeheerconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Transactie;

/**
 *
 * @author Bart.Stoebel
 */
public class VoorraadbeheerConnection {
    private final String URL = "jdbc:mysql://localhost/voorraadbeheer?"
            + "useSSL=false&noAccessToProcedureBodies=true";    //dataverkeer encrypteren?
    private final String USER = "cursist";
    private final String PASSWORD = "cursist";
    private final String UPDATE_VERANDER_VOORRAAD_VAN_MAGAZIJN = 
            "Update voorraden"
            + " set voorraad = voorraad - ?"
            + " where magazijnid = ? AND artikelid = ? AND voorraad >= ? ";
    private final String SELECT_MAGAZIJNID = 
            "Select magazijnid"
            + " from voorraden"
            + " where magazijnid = ?";
    private final String SELECT_MAGAZIJNID_ARTIKELID = 
            "Select magazijnid"
            + " from voorraden"
            + " where magazijnid = ? AND artikelid = ?";
    private final String INSERT_VOORRADEN =
            "Insert into voorraden (magazijnid, artikelid, voorraad)"
            + " values(?,?,?)";
    
    public void updateVoorraden(Transactie transactie){
        if (transactie.getVanMagazijn() == transactie.getNaarMagazijn()){
            System.out.println("van magazijn = naar magazijn ... geen verplaatsing"
                    + " noodzakelijk");
            return;
        }
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement ps = connection.prepareStatement(UPDATE_VERANDER_VOORRAAD_VAN_MAGAZIJN);
                ){
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            connection.setAutoCommit(false);
            
            ps.setInt(1, transactie.getAantal());
            ps.setInt(2, transactie.getVanMagazijn());
            ps.setInt(3, transactie.getArtikelId());
            ps.setInt(4, transactie.getAantal());

            if(ps.executeUpdate() == 0){
                //System.out.println("update niet uitgevoerd");
                try(PreparedStatement ps1 = connection.prepareStatement(SELECT_MAGAZIJNID)){
                    ps1.setInt(1, transactie.getVanMagazijn());
                    try (ResultSet resultset = ps1.executeQuery()){
                        if(!resultset.isBeforeFirst()){         //Is er geen data aanwezig in de resultset?
                            System.out.println("Van magazijn bestaat niet");
                        }else {
                           try(PreparedStatement ps11 = connection.prepareStatement(SELECT_MAGAZIJNID_ARTIKELID)){
                                ps11.setInt(1, transactie.getVanMagazijn());
                                ps11.setInt(2, transactie.getArtikelId());
                                try (ResultSet resultset1 = ps11.executeQuery()){
                                    if(!resultset1.isBeforeFirst()){         //Is er geen data aanwezig in de resultset?
                                        System.out.println("Dit artikel komt niet voor in het magazijn.");
                                    }else{
                                        System.out.println("Te weinig voorraad");
                                    }
                                }
                            }
                        }
                    }
                }
                
            //1e update is gelukt    
            } else {
                //System.out.println("update1 uitgevoerd: aantal verminderd");
                try(PreparedStatement ps2 = connection.prepareStatement(UPDATE_VERANDER_VOORRAAD_VAN_MAGAZIJN);
                ){
                    ps2.setInt(1, 0 - transactie.getAantal());
                    ps2.setInt(2, transactie.getNaarMagazijn());
                    ps2.setInt(3, transactie.getArtikelId());
                    ps2.setInt(4, -100000);
                    
                    if(ps2.executeUpdate() == 0){
                        //System.out.println("update2 NIET uitgevoerd");
                        try(PreparedStatement ps3 = connection.prepareStatement(INSERT_VOORRADEN)){
                            ps3.setInt(1, transactie.getNaarMagazijn());
                            ps3.setInt(2, transactie.getArtikelId());
                            ps3.setInt(3, transactie.getAantal());
                            
                            if (ps3.executeUpdate() == 0){
                                System.out.println("Naar magazijn bestaat niet");
                            }else{
                                System.out.println ("De transfer is gelukt");
                                connection.commit();
                            }
                        }
                    } else {
                        System.out.println("De transfer is gelukt");
                        connection.commit();
                    }
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
}
