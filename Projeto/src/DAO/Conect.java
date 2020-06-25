package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Platform.exit;
import javax.swing.JOptionPane;

public class Conect {

    public static void PreLoad() {
        PreparedStatement stm = null;
        Connection con = Conect.getConnection();
        try{
            stm = con.prepareStatement("SHOW tables;");
            stm.execute();
        }catch(SQLException e){
             System.exit(0);exit();
              
        }finally{
            closeConnection(con, stm);
        }
    }

    public static Connection getConnection() {

        try {

            String banco = "jdbc:mysql://localhost/BafometroLeitura";//local do banco
            String user = "root";//usuario
            String senha = null;//senha do usuario
            String driver = "com.mysql.jdbc.Driver";//Localização do drive
            Connection con = null;//Variavel de conexão
            Class.forName(driver);//Dou um get na classe do drive
            con = DriverManager.getConnection(banco, user, senha);//Dou um get na conexão com os dados
            return con;//Retorno a conexão
        } catch (ClassNotFoundException e) {
            String msg = "Error Starting application" + e;
            JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
             System.exit(0);exit();
        } catch (SQLException ex) {

            String msg = "Error connecting to database" + ex;
            JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(0);exit();
        }
        return null;
    }
//Metodos que fecham a conexão

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {

        }
    }

    public static void closeConnection(Connection con, PreparedStatement stm) {

        closeConnection(con);

        try {

            if (stm != null) {
                stm.close();
            }

        } catch (SQLException ex) {

        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {

        closeConnection(con, stmt);

        try {

            if (rs != null) {
                rs.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(Conect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
