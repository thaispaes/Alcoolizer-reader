package DAO;

import VIEW.PAINEIS.PainelUsuarios;
import java.awt.HeadlessException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Samuelson
 */
public class MetodosTabelaDev {

    public static List<Metodos_gs_TabelaDev> LerSolicitacoes() {

        Connection con = Conect.getConnection();//Variavel de conexão

        PreparedStatement stm = null;//Variavel que permite executar os sql's
        ResultSet rs = null;//Varivavel de retorno

        List<Metodos_gs_TabelaDev> produtos = new ArrayList<>();//array pra retornar no final

        try {
            String SintaxeSQL = "SELECT * FROM usuario WHERE pedido='1';";//String com o sql 
            stm = con.prepareStatement(SintaxeSQL);//Referenciando a execução com a string

            rs = stm.executeQuery();//Executo a query
            while (rs.next()) {
//Retorno os dados do banco
                Metodos_gs_TabelaDev produto = new Metodos_gs_TabelaDev();//Array da classe gs
                produto.setId(rs.getInt("usuarioID"));
                produto.setUsuario(rs.getString("nickname"));
                produto.setEmail(rs.getString("email"));
                produto.setNome(rs.getString("nomeU"));
                produto.setTipo(rs.getString("tipo"));
                produto.setSenha(rs.getString("senha"));
                produtos.add(produto);
            }
            stm = con.prepareStatement("SELECT count(*) FROM usuario usuario WHERE pedido='1';");//String com a sql

            rs = stm.executeQuery();//Executo a query

            if (rs.next()) {
                int valor = rs.getInt("count(*)");//Retorno do numero de usuarios com o pedido ='1'
                if (valor == 0) {
//Se for zero ele ira criar apenas uma linha
                    DefaultTableModel modelo = (DefaultTableModel) PainelUsuarios.TabelaUsuario.getModel();
                    modelo.addRow(new Object[]{
                        "-",
                        "- - -",
                        "- - -",
                        "- - -", "- - -", "- - -",});
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex);

        } finally {
//Fecha a conexão
            Conect.closeConnection(con, stm, rs);
        }

        return produtos;

    }

    public static List<Metodos_gs_TabelaDev> readForDesc(String desc) {

        Connection con = Conect.getConnection();

        PreparedStatement stm = null;
        ResultSet rs = null;

        List<Metodos_gs_TabelaDev> produtos = new ArrayList<>();

        try {

            stm = con.prepareStatement("SELECT * FROM usuario WHERE nickname LIKE ?  and usuarioID<> '" + Basica.GetId() + "' and pedido='1';");//Referenciando a execucução com a string que eu preciso executar
            stm.setString(1, desc + "%");//Enviando desc qe é a pesquisa por parametro
            rs = stm.executeQuery();//Executando a query

            while (rs.next()) {
//Retorna os dados do banco
                Metodos_gs_TabelaDev produto = new Metodos_gs_TabelaDev();//Array com a classe gs
                produto.setId(rs.getInt("usuarioID"));
                produto.setUsuario(rs.getString("nickname"));
                produto.setEmail(rs.getString("email"));
                produto.setNome(rs.getString("nomeU"));
                produto.setTipo(rs.getString("tipo"));
                produto.setSenha(rs.getString("senha"));
                produtos.add(produto);
            }
            stm = con.prepareStatement("SELECT count(*) FROM usuario ");//Referenciando a minha execucução com o sql

            rs = stm.executeQuery();//Executo o sql

            if (rs.next()) {
                int valor = rs.getInt("count(*)");//Retorna o valor do count
                if (valor == 0) {
//Se for zero ele cria uma linha 
                    DefaultTableModel modelo = (DefaultTableModel) PainelUsuarios.TabelaUsuario.getModel();
                    modelo.addRow(new Object[]{
                        "-",
                        "- - -",
                        "- - -",
                        "- - -", "- - -", "- - -",});
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
//Fecha a conexão
            Conect.closeConnection(con, stm, rs);
        }

        return produtos;

    }
//Metodo que apenas sorteia a tabela

    public static void readJTableForDesc(String desc) {
        try {
            DefaultTableModel modelo = (DefaultTableModel) PainelUsuarios.TabelaUsuario.getModel();
            modelo.setNumRows(0);

            MetodosTabelaDev.readForDesc(desc).forEach((p) -> {
                modelo.addRow(new Object[]{
                    p.getId(),
                    p.getUsuario(),
                    p.getNome(),
                    p.getEmail(),
                    p.getSenha(),
                    p.getTipo(),});
            });

            PainelUsuarios.TabelaUsuario.validate();
            PainelUsuarios.TabelaUsuario.repaint();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void MudarTipo(int i, Object id) {
        Connection con = Conect.getConnection();//Variavel de conexão
        PreparedStatement stm = null;//Variavel que permite executar o sql
        ResultSet rs = null;//Retorno
        try {
            String sintaxeSQL = null;

            sintaxeSQL = "UPDATE usuario SET tipo='A' WHERE usuarioID=" + id + ";";

            stm = con.prepareStatement(sintaxeSQL);//Prepara a execucão referenciando com a string
            stm.executeUpdate();//Executo a atualização

            sintaxeSQL = "UPDATE usuario SET pedido='0' WHERE usuarioID =" + id + ";";
            stm = con.prepareStatement(sintaxeSQL);
            stm.executeUpdate();
            MetodosTabelaDev.readJTableForDesc("");
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            //Fecho a conexão
            Conect.closeConnection(con, stm, rs);
        }
    }

    public static void DeletarUsuario(int id) {
        PreparedStatement stm = null;
        Connection con = Conect.getConnection();//Variavel de conexão

        try {

            String sintaxeSQL = "DELETE FROM usuario WHERE usuarioID = " + id + ";";//String que possui meu sql
            stm = con.prepareStatement(sintaxeSQL);//Referencio a minha execução com a meu sql 
            stm.executeUpdate();//executo a atualização

        } catch (HeadlessException | SQLException e) {

        } finally {
            Conect.closeConnection(con, stm);//Fecho a conexão
        }

    }

  
}
