package DAO;

import VIEW.PAINEIS.PainelTrocarSenha;
import static DAO.Basica.*;
import static DAO.DevUtilidadesDAO.AtualizarPedidos;
import VIEW.TelaLogin.*;
import VIEW.*;
import java.awt.HeadlessException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Danilo
 */
public class UsuarioDAO {

    public static boolean validacao_login() {
        boolean livre = false;
        PreparedStatement stm = null;//Variavel que permite a execução do sql
        ResultSet rs = null;//Variavel de retorno
        Connection con = Conect.getConnection();//Variavel que conexão

        try {
            String sintaxeSQL = null;
            MessageDigest m = null;//Variavel de instancias para fazer a conexão
            try {
                m = MessageDigest.getInstance("MD5");//Dou um get na instancia pra codificar no padrão MD5
            } catch (NoSuchAlgorithmException ex) {
                JOptionPane.showMessageDialog(null, ex, "erro", JOptionPane.ERROR_MESSAGE);
            }
            m.update(TelaLogin.txtsenha.getText().trim().getBytes());//Dou um um update na variável de instancia
            byte[] digest1 = m.digest();// Crio uma variável byte e a codifico a mensagem
            BigInteger bigInt1 = new BigInteger(1, digest1);//Converto ela para BigInteger
            String senhacod = bigInt1.toString(16);//Converto de BigInt para String
            sintaxeSQL = "SELECT * FROM usuario where nickname = ? and senha = ?";
            stm = con.prepareStatement(sintaxeSQL);//Preparo a execução reaconando o meu sql

            stm.setString(1, TelaLogin.txtusuario.getText().trim());//Mando o usuario por parametro
            stm.setString(2, senhacod);//Mando a senha já codificada por parametro

            rs = stm.executeQuery();//Executo a query

            if (rs.next()) {
                livre = rs.getString("tipo").equals("A");
                Basica.SetId(rs.getInt("usuarioID"));
            } else {

                livre = false;

            }

        } catch (HeadlessException e) {

            JOptionPane.showMessageDialog(null, e, " Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            Conect.closeConnection(con, stm, rs);
        }
        return livre;

    }

    public static void apagarconta() {

        Connection con = Conect.getConnection();//Variavel que conexão

        PreparedStatement stm = null;//Variavel que permite a execução do sql

        try {
            stm = con.prepareStatement("DELETE FROM rank WHERE IDrank = ? ");
            stm.setString(1, GetId());//Envia o id por parametro
            stm.executeUpdate();//Faz a atualização
            stm = con.prepareStatement("delete from usuario where usuarioID = ? ");
            stm.setString(1, GetId());//Envia o id por parametro
            stm.executeUpdate();//Faz a atualização
            UtilidadesDAO.logout();//Faz logout

            JOptionPane.showMessageDialog(null, "Account deleted successfully!",null,JOptionPane.PLAIN_MESSAGE);

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {

            Conect.closeConnection(con, stm);
        }

    }

    public static void verificartroca() {
        PreparedStatement stm = null;//Variavel que permite a execução do sql
        ResultSet rs = null;//Variavel de retorno
        Connection con = Conect.getConnection();//Variavel que conexão

        try {

            String sintaxeSQL = "SELECT * FROM usuario where usuarioID = ?";
            stm = con.prepareStatement(sintaxeSQL);//Preparo a execução relacionando o meu sql
            MessageDigest m = null;//Variavel de instancias para fazer a conexão
            try {
                m = MessageDigest.getInstance("MD5");//Dou um get na instancia pra codificar no padrão MD5
            } catch (NoSuchAlgorithmException ex) {
                JOptionPane.showMessageDialog(null, ex, "erro", JOptionPane.ERROR_MESSAGE);
            }
            m.update(PainelTrocarSenha.txtsenhaatual.getText().trim().getBytes());//Dou um um update na variável de instancia
            byte[] digest1 = m.digest();// Crio uma variável byte e a codifico a mensagem
            BigInteger bigInt1 = new BigInteger(1, digest1);//Converto ela para BigInteger
            String senhacod = bigInt1.toString(16);//Converto de BigInt para String
            stm.setString(1, (GetId()));//Envio o id por parametro
            rs = stm.executeQuery();//Executo a query
            String senhabanco;
            if (rs.next()) {
                //Se os dados baterem ele entra aq
                senhabanco = (rs.getString("senha"));//Ele retorna a senha do banco
                if (senhabanco.equals(senhacod)) {
                    trocarsenha();//Se a senha qu tá no banco for igual a que foi digitada ele ira trocar
                } else {
                    //Se não for ele vai dar a mensagem se senha incorreta de acordo com o 

                    JOptionPane.showMessageDialog(null, "Incorrect Password", null, JOptionPane.ERROR_MESSAGE);

                }
            }
        } catch (HeadlessException | SQLException e) {
            System.out.println(e);
        } finally {
            //Fecho a conexão
            Conect.closeConnection(con, stm, rs);
        }

    }

    public static void trocarsenha() {
        PreparedStatement stm = null;//Variavel que permite a execução do sql
        Connection con = Conect.getConnection();//Variavel que conexão

        try {

            String sintaxeSQL = "UPDATE usuario SET senha= ? WHERE usuarioID = ?";
            stm = con.prepareStatement(sintaxeSQL);//Preparo a execução e relaciono com o meu sql
            MessageDigest m = null;//Variavel de instancias para fazer a conexão
            try {
                m = MessageDigest.getInstance("MD5");//Dou um get na instancia pra codificar no padrão MD5
            } catch (NoSuchAlgorithmException ex) {
                JOptionPane.showMessageDialog(null, ex, "erro", JOptionPane.ERROR_MESSAGE);
            }
            m.update(PainelTrocarSenha.senha2.getText().trim().getBytes());//Dou um um update na variável de instancia
            byte[] digest1 = m.digest();// Crio uma variável byte e a codifico a mensagem
            BigInteger bigInt1 = new BigInteger(1, digest1);//Converto ela para BigInteger
            String senhacod = bigInt1.toString(16);//Converto de BigInt para String

            stm.setString(1, senhacod);//Envio a senha por parametro
            stm.setString(2, (GetId()));//Envio o id por parametro
            stm.executeUpdate();//Faço a atualização
            //Se ocorrer ocorrer a troca o usuario receberá uma mensagem de acordo com o idioma

            JOptionPane.showMessageDialog(null, "Password changed successfully", "", JOptionPane.PLAIN_MESSAGE);

            PainelTrocarSenha.txtsenhaatual.setText(null);
            PainelTrocarSenha.senha1.setText(null);
            PainelTrocarSenha.senha2.setText(null);
        } catch (HeadlessException|SQLException e) {
            System.out.println("e");
        } finally {
            Conect.closeConnection(con, stm);
        }

   
    }
}
