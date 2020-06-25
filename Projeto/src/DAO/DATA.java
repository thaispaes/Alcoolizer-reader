/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import BC.*;

/**
 *
 * @author Danilo
 */
public class DATA {

    public static String nomearquivo = null;

    public static void SendDataPA() {
        PreparedStatement stm;
        Connection con = Conect.getConnection();//Variavel de conexão
        for (int i = 0; i < Active.EventType.size(); i++) {
            try {

                String sintaxeSQL = "INSERT INTO Active(RawData,RecordNum,MainSerial,MainFWV,SampleSerial,SampleFWV,DateD,TimeT,EventType,DownloadDate,DownloadTime,TrueResult,NameFile)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
                stm = con.prepareStatement(sintaxeSQL);
                stm.setString(1, Active.getRawData(1));
                stm.setString(2, Active.getRecordNum(i));
                stm.setString(3, Active.getMainSerial(i));
                stm.setString(4, Active.getMainFWV(i));
                stm.setString(5, Active.getSampleSerial(i));
                stm.setString(6, Active.getSampleFWV(i));
                stm.setString(7, Active.getDateTime(i).substring(0, 10));
                stm.setString(8, (Active.getDateTime(i).replaceAll(Active.getDateTime(i).substring(0, 10) + "T", "")).replaceAll("-03:00",""));
                stm.setString(9, Active.getEventType(i));
                String tudo = Active.getDownloadDateTime(i);
                tudo = tudo.substring(0, 10);
                tudo = tudo.replaceAll("/", "");
                String AAAA = (tudo.substring(4));
                String DD = (tudo.replaceAll(tudo.substring(2), ""));
                String MM = (tudo.replaceAll(DD, ""));
                MM = MM.replaceAll(AAAA, "");
                String date = AAAA + "-" + MM + "-" + DD;
                stm.setString(10, date.replaceAll(" ", ""));
                stm.setString(11, Active.getDownloadDateTime(i).substring(10));
                stm.setString(12, Active.getTrueResult(i).replaceAll(",", ".").replaceAll("mg/L", "").trim());
                stm.setString(13, nomearquivo);
                stm.executeUpdate();
            } catch (SQLException sqle) {
                System.out.println("erro1"+sqle);
            }
        }
        for (int i = 0; i < Passive.EventType.size(); i++) {
            try {

                String sintaxeSQL = "INSERT INTO Passive(RawData,RecordNum,MainSerial,MainFWV,SampleSerial,SampleFWV,DateD,TimeT,EventType,DownloadDate,DownloadTime,ResultQualitative,NameFile)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
                stm = con.prepareStatement(sintaxeSQL);
                stm.setString(1, Passive.getRawData(1));
                stm.setString(2, Passive.getRecordNum(i));
                stm.setString(3, Passive.getMainSerial(i));
                stm.setString(4, Passive.getMainFWV(i));
                stm.setString(5, Passive.getSampleSerial(i));
                stm.setString(6, Passive.getSampleFWV(i));
                stm.setString(7, Passive.getDateTime(i).substring(0, 10));
                stm.setString(8, (Passive.getDateTime(i).replaceAll(Passive.getDateTime(i).substring(0, 10) + "T", "")).replaceAll("-03:00",""));
               
                stm.setString(9, Passive.getEventType(i));
                String tudo = Passive.getDownloadDateTime(i);
                tudo = tudo.substring(0, 10);
                tudo = tudo.replaceAll("/", "");
                String AAAA = (tudo.substring(4));
                String DD = (tudo.replaceAll(tudo.substring(2), ""));
                String MM = (tudo.replaceAll(DD, ""));
                MM = MM.replaceAll(AAAA, "");
                String date = AAAA + "-" + MM + "-" + DD;
                stm.setString(10, date.replaceAll(" ", ""));
                stm.setString(11, Passive.getDownloadDateTime(i).substring(10));
                stm.setString(12, Passive.getResultQualitative(i));
                stm.setString(13, nomearquivo);
                stm.executeUpdate();
            } catch (SQLException sqle) {
                System.out.println("erro2"+sqle);
            }
        }
        Active.DeleteActive();
        Passive.DeletePassive();
    }
}
