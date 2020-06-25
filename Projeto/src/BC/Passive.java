/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BC;

import java.util.ArrayList;

/**
 *
 * @author Danilo
 */
public class Passive {

    public static ArrayList<String> RawData = new ArrayList<String>();
    public static ArrayList<String> RecordNum = new ArrayList<String>();
    public static ArrayList<String> MainSerial = new ArrayList<String>();
    public static ArrayList<String> MainFWV = new ArrayList<String>();
    public static ArrayList<String> SampleSerial = new ArrayList<String>();
    public static ArrayList<String> SampleFWV = new ArrayList<String>();
    public static ArrayList<String> DateTime = new ArrayList<String>();
    public static ArrayList<String> EventType = new ArrayList<String>();
    public static ArrayList<String> DownloadDateTime = new ArrayList<String>();
    public static ArrayList<String> ResultQualitative = new ArrayList<String>();

    public static String getRawData(int i) {
        return RawData.get(i);
    }

    public static String getRecordNum(int i) {
        return RecordNum.get(i);
    }

    public static String getMainSerial(int i) {
        return MainSerial.get(i);
    }

    public static String getMainFWV(int i) {
        return MainFWV.get(i);
    }

    public static String getSampleSerial(int i) {
        return SampleSerial.get(i);
    }

    public static String getSampleFWV(int i) {
        return SampleFWV.get(i);
    }

    public static String getDateTime(int i) {
        return DateTime.get(i);
    }

    public static String getEventType(int i) {
        return EventType.get(i);
    }

    public static String getDownloadDateTime(int i) {
        return DownloadDateTime.get(i);
    }

    public static String getResultQualitative(int i) {
        return ResultQualitative.get(i);
    }

    public static void DeletePassive() {
        try {
            RawData.removeAll(RawData);
            RecordNum.removeAll(RecordNum);
            MainSerial.removeAll(MainSerial);
            MainFWV.removeAll(MainFWV);
            SampleSerial.removeAll(SampleSerial);
            SampleFWV.removeAll(SampleFWV);
            DateTime.removeAll(DateTime);
            EventType.removeAll(EventType);
            DownloadDateTime.removeAll(DownloadDateTime);
            ResultQualitative.removeAll(RawData);
        } catch (IndexOutOfBoundsException e) {

        }
    }

}
