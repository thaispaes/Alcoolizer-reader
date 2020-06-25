package DAO;

import BC.GSPassive;
import VIEW.PAINEIS.*;
import static VIEW.PAINEIS.PainelAnaliseADM.*;
import java.sql.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Samuelson
 */
public class TablePassive {

    public static int valor;

    public static List<GSPassive> readForDesc(String desc) {
//RawData,RecordNum,MainSerial,MainFWV,SampleSerial,SampleFWV,DateD,TimeT,EventType,DownloadDate,DownloadTime,ResultQualitative,NameFile
        Connection con = Conect.getConnection();//Variavel de conexão

        PreparedStatement stm = null;//PreparedStatement que faz a execução do sql no banco
        ResultSet rs = null;//Variavel de retorno

        List<GSPassive> produtos = new ArrayList<>();//Array com os dados que vou adicionar sobre o rank 

        try {
            String v = null;
            try {
                v = selecao.getSelectedItem().toString();
            } catch (NullPointerException e) {

            }
            if (valor == 0) {

                if (v.equals("January")) {
                    v = "01";
                }
                if (v.equals("February")) {
                    v = "02";
                }
                if (v.equals("March")) {
                    v = "03";
                }
                if (v.equals("April")) {
                    v = "04";
                }
                if (v.equals("May")) {
                    v = "05";
                }
                if (v.equals("June")) {
                    v = "06";
                }
                if (v.equals("July")) {
                    v = "07";
                }
                if (v.equals("August")) {
                    v = "08";
                }
                if (v.equals("September")) {
                    v = "09";
                }
                if (v.equals("October")) {
                    v = "10";
                }
                if (v.equals("November")) {
                    v = "11";
                }
                if (v.equals("December")) {
                    v = "12";
                }
                stm = con.prepareStatement("SELECT * FROM Passive WHERE MONTH(DateD)=" + v + ";");
            }
            if (valor == 1) {
                stm = con.prepareStatement("SELECT * FROM Passive WHERE YEAR(DateD) =" + selecao.getSelectedItem().toString() + ";");
            }

            if (valor == 3) {
                stm = con.prepareStatement("SELECT * FROM Passive WHERE NameFile='" + v + "' ;");
            }
            if (valor == 2) {
                stm = con.prepareStatement("SELECT * FROM Passive ORDER BY MainSerial ASC;");
            }
            rs = stm.executeQuery();

            while (rs.next()) {

                GSPassive produto = new GSPassive();//Array com o metodo gs desta tabela
                //retorno os dados
                produto.setId(rs.getInt("ID"));
                produto.setRawData(rs.getString("RawData"));
                produto.setRecordNum(rs.getString("RecordNum"));
                produto.setMainSerial(rs.getString("MainSerial"));
                produto.setMainFWV(rs.getString("MainFWV"));
                produto.setSampleSerial(rs.getString("SampleSerial"));
                produto.setSampleFWV(rs.getString("SampleFWV"));
                produto.setDate(rs.getString("DateD"));
                produto.setTime(rs.getString("TimeT"));
                produto.setEventType(rs.getString("EventType"));
                produto.setDownloadDate(rs.getString("DownloadDate"));
                produto.setDownloadTime(rs.getString("DownloadTime"));
                produto.setResultQualitative(rs.getString("ResultQualitative"));
                produto.setNameFile(rs.getString("NameFile"));
                produtos.add(produto);
            }
            stm = con.prepareStatement("SELECT count(*) FROM Passive");//Faço uma referencia com o que vou executar futuramente

            rs = stm.executeQuery();//Executo a query

            if (rs.next()) {
                //retorno os valores do banco
                int va = rs.getInt("count(*)");
                if (va == 0) {
                    //Se o count for igual a 0 ele entrará aqui
                    DefaultTableModel modelo = (DefaultTableModel) PainelAnaliseADM.Table.getModel();//Pego o modelo da tabela
                    //Adiciono um a linha 
                    modelo.addRow(new Object[]{
                        "- - -",
                        "- - -",
                        "- - -",
                        "- - -",});
                }
                
            }
        } catch (SQLException ex) {
          
        } finally {

            Conect.closeConnection(con, stm, rs);
        }
//Retorno o array com os dados
        return produtos;

    }

//Metodo abaixo serve apenas para ordenar a tabela
    public static void readJTableForDesc(String desc) {
        try {
            DefaultTableModel modelo = (DefaultTableModel) PainelAnaliseADM.Table.getModel();
            modelo.setNumRows(0);
            TablePassive.readForDesc(desc).forEach((p) -> {
                modelo.addRow(new Object[]{
                    p.getId(),
                    p.getRawData(),
                    p.getRecordNum(),
                    p.getMainSerial(),
                    p.getMainFWV(),
                    p.getSampleSerial(),
                    p.getSampleFWV(),
                    p.getDate(),
                    p.getTime(),
                    p.getEventType(),
                    p.getDownloadDate(),
                    p.getDownloadTime(),
                    p.getResultQualitative(),
                    p.getNameFile(),});
            });

            PainelAnaliseADM.Table.validate();
            PainelAnaliseADM.Table.repaint();
        } catch (Exception e) {

        }
    }

    public void table() {
        DefaultTableModel modelo = (DefaultTableModel) PainelAnaliseADM.Table.getModel();
        PainelAnaliseADM.Table.setRowSorter(new TableRowSorter(modelo));
        PainelAnaliseADM.Table.validate();
        PainelAnaliseADM.Table.repaint();
    }
}
