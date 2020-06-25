/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import VIEW.PAINEIS.PainelAnaliseADM;
import static VIEW.PAINEIS.PainelDelete.combo;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.*;

/**
 *
 * @author Danilo
 */
public class Func {

    public static String retorno = null;
    public static String PS;

    public static void Add(int val) {
        Connection con = Conect.getConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String SintaxeSQL = null;
            if (val == 0) {
                SintaxeSQL = "SELECT DISTINCT SUBSTR(DateD, 6,2) FROM " + PS + " ORDER BY SUBSTR(DateD, 6,2) ASC;";
            }
            if (val == 1) {
                SintaxeSQL = "SELECT DISTINCT SUBSTR(DateD,1,4) FROM " + PS + " ORDER BY SUBSTR(DateD,1,4) ASC";
            }
            if (val == 3) {
                SintaxeSQL = "SELECT DISTINCT NameFile FROM " + PS + " ORDER BY NameFile ASC";
            }
            stm = con.prepareStatement(SintaxeSQL);

            rs = stm.executeQuery();
            String v2;
            PainelAnaliseADM.selecao.removeAllItems();
            while (rs.next()) {

                if (val == 0) {
                    v2 = rs.getString("SUBSTR(DateD, 6,2)");

                    if (v2.equals("01")) {
                        v2 = "January";
                    }
                    if (v2.equals("02")) {
                        v2 = "February";
                    }
                    if (v2.equals("03")) {
                        v2 = "March";
                    }
                    if (v2.equals("04")) {
                        v2 = "April";
                    }
                    if (v2.equals("05")) {
                        v2 = "May";
                    }
                    if (v2.equals("06")) {
                        v2 = "June";
                    }
                    if (v2.equals("07")) {
                        v2 = "July";
                    }
                    if (v2.equals("08")) {
                        v2 = "August";
                    }
                    if (v2.equals("09")) {
                        v2 = "September";
                    }
                    if (v2.equals("10")) {
                        v2 = "October";
                    }
                    if (v2.equals("11")) {
                        v2 = "November";
                    }
                    if (v2.equals("12")) {
                        v2 = "December";
                    }

                    PainelAnaliseADM.selecao.addItem(v2);
                }
                if (val == 1) {
                    PainelAnaliseADM.selecao.addItem(rs.getString("SUBSTR(DateD,1,4)"));

                }
                if (val == 3) {
                    PainelAnaliseADM.selecao.addItem(rs.getString("NameFile"));
                }
            }
        } catch (SQLException s) {

        } finally {
            Conect.closeConnection(con, stm, rs);
        }
    }

    public static String Agora() {
        Connection con = Conect.getConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {

            stm = con.prepareStatement("SELECT NOW()");
            rs = stm.executeQuery();
            if (rs.next()) {
                retorno = rs.getString("NOW()");
            }
            retorno = retorno.replaceAll(" ", "-");
            retorno = retorno.replaceAll("\\.", "-");
            retorno = retorno.replaceAll(":", "-");
            int tamanho = retorno.length();
            retorno = retorno.substring(0, tamanho - 2);

        } catch (SQLException e) {

        }
        return retorno;
    }

    public static void AddArquivos() {
        Connection con = Conect.getConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String SintaxeSQL = "SELECT DISTINCT NameFile FROM Passive ORDER BY NameFile ASC";
            stm = con.prepareStatement(SintaxeSQL);

            rs = stm.executeQuery();
            combo.removeAllItems();
            while (rs.next()) {
                combo.addItem(rs.getString("NameFile") + " - Passive");
            }
            SintaxeSQL = "SELECT DISTINCT NameFile FROM Active ORDER BY NameFile ASC";
            stm = con.prepareStatement(SintaxeSQL);

            rs = stm.executeQuery();

            while (rs.next()) {
                combo.addItem(rs.getString("NameFile") + " - Active");
            }
        } catch (SQLException e) {

        }
    }

    public static void DeleteArquivos(String valor) {
        Connection con = Conect.getConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            String v1 = null;
            if (valor.contains("Passive")) {
                v1 = "Passive";
            }
            if (valor.contains("Active")) {
                v1 = "Active";
            }
            valor = valor.replaceAll(" - Active", "");
            valor = valor.replaceAll(" - Passive", "");
            String sql = "DELETE FROM " + v1 + " WHERE NameFile= '" + valor + "'";
            stm = con.prepareStatement(sql);

            stm.executeUpdate();
            AddArquivos();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            Conect.closeConnection(con, stm);
        }
    }

    public static String DataTempo() {
        Connection con = Conect.getConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;
        String valor = null;
        try {

            stm = con.prepareStatement("SELECT DATE_FORMAT(CURRENT_DATE, \"%d %M %Y\");");
            rs = stm.executeQuery();
            while(rs.next()){
                valor=rs.getString("DATE_FORMAT(CURRENT_DATE, \"%d %M %Y\")");
            }
             stm = con.prepareStatement("SELECT CURRENT_TIME");
            rs = stm.executeQuery();
            while(rs.next()){
                valor= valor+" at "+rs.getString("CURRENT_TIME");
            }
            System.out.println(valor);
        } catch (SQLException c) {
            System.out.println(c);
        }
        return valor;
    }
}
