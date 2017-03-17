/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import Test.UI;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Vinnie
 */
public class BankConnection {
     private final String URL = "jdbc:mysql://localhost/bank?"
            + "useSSL=false&noAccessToProcedureBodies=true";    //dataverkeer encrypteren?
    private final String USER = "cursist";
    private final String PASSWORD = "admin";
    private final String UPDATE_VERANDER_SALDO_VAN_REKENING = 
            "Update rekeningen"
            + " set saldo = saldo - ?"
            + " where rekeningnr = ? AND saldo >= ? ";
    private final String SELECT_REKENINGNUMMER = 
            "Select rekeningnr, saldo"
            + " from rekeningen"
            + " where rekeningnr = ?";
    private final String SELECT_MAGAZIJNID_ARTIKELID = 
            "Select magazijnid"
            + " from voorraden"
            + " where magazijnid = ? AND artikelid = ?";
    private final String INSERT_REKENINGNUMMER =
            "Insert into rekeningen (Rekeningnr, Saldo)"
            + " values(?,?)";
    
    public void voegRekeningnummerToe(long Rekeningnummer){
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement ps = connection.prepareStatement(INSERT_REKENINGNUMMER);
                ){
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            
            ps.setLong(1, Rekeningnummer);
            ps.setBigDecimal(2, BigDecimal.ZERO);
            
            if(ps.executeUpdate() == 0){
                System.out.println("Dit rekeningnummer bestaat reeds in de tabel");
            } else {
                System.out.println("De transfer is gelukt");
                connection.commit();
            }
        } catch (SQLException e){
            System.err.println("Dit rekeningnummer bestaat reeds in de tabel");
        }
    }
    public void controleerSaldo(long Rekeningnummer){
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement ps = connection.prepareStatement(SELECT_REKENINGNUMMER);
                ){
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            ps.setLong(1, Rekeningnummer);
                        
            try(ResultSet resultset = ps.executeQuery()){
                if(!resultset.isBeforeFirst()){
                    System.out.println("Dit rekeningnummer bestaat niet in de tabel");
                } else {
                    while (resultset.next()){
                        System.out.println("Het saldo bedraagt: " + resultset.getBigDecimal("saldo") + "€");
                    }
                }
            }
        } catch (SQLException e){
            System.err.println("Fout");
        }
    }
    public void bedragOverschrijven(long van, long naar, BigDecimal bedrag){
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement ps = connection.prepareStatement(UPDATE_VERANDER_SALDO_VAN_REKENING);
                ){
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            connection.setAutoCommit(false);
            
            ps.setBigDecimal(1, bedrag);
            ps.setLong(2, van);
            ps.setBigDecimal(3, bedrag);
            
            if(ps.executeUpdate() == 0){
                //System.out.println("update niet uitgevoerd");
                try(PreparedStatement ps1 = connection.prepareStatement(SELECT_REKENINGNUMMER)){
                    ps1.setLong(1, van);
                    try (ResultSet resultset = ps1.executeQuery()){
                        if(!resultset.isBeforeFirst()){         //Is er geen data aanwezig in de resultset?
                            System.out.println("Van rekeningnr bestaat niet");
                        }else {
                            System.out.println("Saldo niet toereikend");
                        }
                    }
                }
                
            //1e update is gelukt    
            } else {
                //System.out.println("update1 uitgevoerd: aantal verminderd");
                try(PreparedStatement ps2 = connection.prepareStatement(UPDATE_VERANDER_SALDO_VAN_REKENING);
                ){
                    ps2.setBigDecimal(1, bedrag.negate());
                    ps2.setLong(2, naar);
                    ps2.setBigDecimal(3, BigDecimal.ZERO);
                    
                    if(ps2.executeUpdate() == 0){
                        System.out.println("Naar rekeningnr bestaat niet");
                    } else {
                        System.out.println("De transfer is gelukt");
                        connection.commit();
                    }
                }
            }
        } catch (SQLException e){
            System.err.println("In de Catch van SQLException");
        }
    }
    
}
