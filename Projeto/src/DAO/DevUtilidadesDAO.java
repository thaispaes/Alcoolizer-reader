/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Danilo
 */
public class DevUtilidadesDAO {

    public static void AtualizarPedidos() {
      PreparedStatement stm = null;//PreparedStatement que faz a execução do sql no banco
        ResultSet rs = null;//Variavel de retorno
         Connection con = Conect.getConnection();//Variavel de conexão
         try { String sintaxeSQL = null;//Variável que vai conter o meu sql
        
                sintaxeSQL = "SELECT * FROM usuario where pedido = '1'";
               stm = con.prepareStatement(sintaxeSQL);//Faço uma referencia com o que devo executar futuramente 
                rs = stm.executeQuery();//Executo a query
                int solicitacoes = 0;
                while (rs.next()) {
                    //Caso tiver algum usuario com o pedido ='1' ele vai mostrar na tela a solicitação do usuario de acordo o idioma
                    solicitacoes = solicitacoes + 1;
                   
                        JOptionPane.showMessageDialog(null, "The user " + rs.getString("nickname") + "\n Requested administration\nAccept or delete in:\nOptions-> View requests","",JOptionPane.PLAIN_MESSAGE);
                    
                    }
                  
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            Conect.closeConnection(con, stm, rs);
        }
    }
    
}
