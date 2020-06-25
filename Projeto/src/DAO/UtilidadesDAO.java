/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.awt.*;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Danilo
 */
public class UtilidadesDAO {

    private static Connection conec = null;

    ;
    public static void logout() {
        Basica.ApagarTudo();

    }

    public static void PreCarregamento() {
//Metodo que é só usado para fazer um pre carregamento do banco
        PreparedStatement stm = null;
        conec = Conect.getConnection();
        try {

            String sintaxeSQL = "SELECT * FROM usuario;";
            stm = conec.prepareStatement(sintaxeSQL);
            stm.executeQuery();

        } catch (SQLException e) {

        } finally {
            Conect.closeConnection(conec, stm);
        }
    }

    public static boolean JaExiste(String arquivo) {
        conec = Conect.getConnection();
        boolean tem = false;
        PreparedStatement stm = null;
        ResultSet rs;
        try {

            String sintaxeSQL = "SELECT * FROM Passive where NameFile= '" + arquivo + "';";
            System.out.println(sintaxeSQL);
            stm = conec.prepareStatement(sintaxeSQL);
            rs = stm.executeQuery();
            while (rs.next()) {
                tem = true;
            }
            sintaxeSQL = "SELECT * FROM Active where NameFile= '" + arquivo + "';";
            stm = conec.prepareStatement(sintaxeSQL);
            rs = stm.executeQuery();
            while (rs.next()) {
                tem = true;
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            Conect.closeConnection(conec, stm);
        }
        return tem;
    }

}
