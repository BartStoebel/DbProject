/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbproject;

import Test.TestJDBC;

/**
 *
 * @author Vinnie
 */
public class DbProject {

    /**
     * MySQLServer setup thuis:
Connection name: VinniesConnection
Username : root
Password: admin
Windows Service Name: MySQL57

     */
    private static final String URL = "jdbc:mysql://localhost/tuincentrum?"
            + "useSSL=false&noAccessToProcedureBodies=true";            //dataverkeer encrypteren?
    private static final String USER = "cursist";
    private static final String PASSWORD = "admin";
    private static final String UPDATE_PRIJS = "update planten set "
            + "verkoopprijs = verkoopprijs * 1";
    private static final String SELECT_NAAM = "select id, naam from leveranciers"
            + " order by id";
    private static final String SELECT_GEMIDDELDE_VERKOOPPRIJS = "select "
            + "avg(verkoopprijs) as gemiddelde from planten";
    private static final String SELECT_ALLE_LEVERANCIERS =
            "select naam, aantalkinderen from leveranciers order by naam";
    private static final String SELECT_LEVERANCIERS_VAN_EEN_WOONPLAATS =
            "select naam from leveranciers where woonplaats = ?";
    private static final String UPDATE_PLANTEN_PRIJS_HACKER = "update planten "
            + "set verkoopprijs = verkoopprijs * 1.1 where naam = ?";
    private static final String SP = "{call testpar(?)}";
    
    
    public static void main(String[] args) {
        TestJDBC test = new TestJDBC();
        test.UI();
        
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("minimum:");
//        double min = Double.parseDouble(scanner.nextLine());
//        System.out.println("Maximum: ");
//        double max = Double.parseDouble(scanner.nextLine());

//        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//                CallableStatement statement = connection.prepareCall(SP)){
//            statement.setString(1, "%bloem");
//            try (ResultSet resultSet = statement.executeQuery()){
//                while(resultSet.next()){
//                    System.out.println(resultSet.getString("naam"));
//                }
//            }
//        
//        } catch (SQLException e){
//            e.printStackTrace();
//        }
        
        
//        TestMain main = new TestMain();
//        main.main();
//        SchoolTestDb school = new SchoolTestDb();
//        school.main(0.5, 3.2);

//        SchoolTestTuincentrum testTuin = new SchoolTestTuincentrum();
//        testTuin.test();
    }
    
}
