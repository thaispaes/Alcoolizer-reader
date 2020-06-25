package DAO;


import VIEW.TelaCadastro;
import static VIEW.TelaCadastro.txtemail;
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
public class CadastroDAO {

    public static void salvar() {//Metodo para cadastrar o usuario.
        PreparedStatement stm = null;//PreparedStatement que faz a execução do sql no banco
 
        Connection con = Conect.getConnection();//Variavel de conexão
        try {

            String sintaxeSQL = "insert into usuario (nickname,senha,nomeU,email) values (? ,? , ?, ?)";//variavel com o Sql que irei executar futuramente

            MessageDigest m = null;//Variavel de instancias para fazer a conexão
            try {
                m = MessageDigest.getInstance("MD5");//Dou um get na instancia pra codificar no padrão MD5
            } catch (NoSuchAlgorithmException ex) {
                
            }
            m.update(DAO.Basica.GetSenha().trim().getBytes());//Dou um um update na variável de instancia
            byte[] digest1 = m.digest();// Crio uma variável byte e a codifico a mensagem
            BigInteger bigInt1 = new BigInteger(1, digest1);//Converto ela para BigInteger
            String senhacod = bigInt1.toString(16);//Converto de BigInt para String
            stm = con.prepareStatement(sintaxeSQL);//Faço uma referencia com o que devo executar futuramente 
            stm.setString(1, Basica.GetUsuario());//Envio o nome de usuario por parametro pra sql
            stm.setString(2, senhacod);//Envio a senha do usuario por parametro pra sql
            stm.setString(3, Basica.GetNome());//Envio o nome do usuario por parametro pra sql

            stm.setString(4, Basica.GetEmail());//Envio o email do usuario por parametro pra sql
            stm.executeUpdate();//Executo os dados que estão na query
            
            //Manda uma mensagem de acordo com o idioma que está usado no momento
           
                JOptionPane.showMessageDialog(null, "Saved successfully!", "", JOptionPane.PLAIN_MESSAGE);
            
//Deixa todos os campos nulo
            TelaCadastro.txtusuario.setText(null);
            TelaCadastro.txtnome.setText(null);

            TelaCadastro.txtsenha1.setText(null);
            TelaCadastro.msg.setText(null);
            TelaCadastro.txtemail.setText(null);
            TelaCadastro.txtsenha.setText(null);

        } catch (HeadlessException | SQLException e) {

           
        } finally {
            //Fecho a conexão
            Conect.closeConnection(con, stm);
        }

    }

    public static void validacao_email(String email) {
        PreparedStatement stm = null;//PreparedStatement que faz a execução do sql no banco
        ResultSet rs = null;
        Connection con = Conect.getConnection();//Variavel de conexão
        try {

            String sintaxeSQL = "SELECT * FROM usuario where email = ?";//variavel com o Sql que irei executar futuramente
            stm = con.prepareStatement(sintaxeSQL);//Faço uma referencia com o que devo executar futuramente 
            stm.setString(1, email);//Mando o email por parametro para string
            rs = stm.executeQuery(); //Executo a query
            if (rs.next()) {
              //Caso já existir o email cadastrado no banco o usuario vai receber uma mensagem
               
                    JOptionPane.showMessageDialog(null, "Email entered already matches another account", "", JOptionPane.PLAIN_MESSAGE);
                

            } else {
//Se o email não estiver sendo usado os dados serão salvos
                salvar();
            }

        } catch (HeadlessException | SQLException e) {

           
        } finally {
            //Fecho a conexão
            Conect.closeConnection(con, stm, rs);
        }
    }

    public static void validacao_nickname(String nickname) {
        PreparedStatement stm = null;//PreparedStatement que faz a execução do sql no banco
        ResultSet rs = null;
        Connection con = Conect.getConnection();//Variavel de conexão
        try {

            String sintaxeSQL = "SELECT * FROM usuario where nickname = ?";//variavel com o Sql que irei executar futuramente
            stm = con.prepareStatement(sintaxeSQL);//Faço uma referencia com o que devo executar futuramente 
            stm.setString(1, Basica.GetUsuario());//Mando o usuario por parametro
            rs = stm.executeQuery();//Executo a query
            if (rs.next()) {
                 //Caso já existir o usuario cadastrado no banco o usuario vai receber uma mensagem
             
                    JOptionPane.showMessageDialog(null, "Informed user can not be used", "", JOptionPane.PLAIN_MESSAGE);
               

            } else {
                //Se não existir um usuario sendo usado é verificado se o email já esta sendo usado
                validacao_email(txtemail.getText());
            }

        } catch (HeadlessException | SQLException e) {

        } finally {
            //fecho a conexão
            Conect.closeConnection(con, stm, rs);
        }
    }
}
