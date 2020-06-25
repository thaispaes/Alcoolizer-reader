package BC;

public class GSActive {
private String  RawData,RecordNum,MainSerial,MainFWV,SampleSerial,SampleFWV,DateTime,EventType,Date,Time,DownloadDate,DownloadTime,TrueResult,NameFile;
private int Id;

    public String getRawData() {
        return RawData;
    }

    public String getRecordNum() {
        return RecordNum;
    }

    public String getMainSerial() {
        return MainSerial;
    }

    public String getMainFWV() {
        return MainFWV;
    }

    public String getSampleSerial() {
        return SampleSerial;
    }

    public String getSampleFWV() {
        return SampleFWV;
    }

    public String getDateTime() {
        return DateTime;
    }

    public String getEventType() {
        return EventType;
    }

    public String getDate() {
        return Date;
    }

    public String getResultQualitative() {
        return getTrueResult();
    }

    public void setRawData(String RawData) {
        this.RawData = RawData;
    }

    public void setRecordNum(String RecordNum) {
        this.RecordNum = RecordNum;
    }

    public void setMainSerial(String MainSerial) {
        this.MainSerial = MainSerial;
    }

    public void setMainFWV(String MainFWV) {
        this.MainFWV = MainFWV;
    }

    public void setSampleSerial(String SampleSerial) {
        this.SampleSerial = SampleSerial;
    }

    public void setSampleFWV(String SampleFWV) {
        this.SampleFWV = SampleFWV;
    }

    public void setDateTime(String DateTime) {
        this.DateTime = DateTime;
    }

    public void setEventType(String EventType) {
        this.EventType = EventType;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public void setResultQualitative(String TrueResult) {
        this.setTrueResult(TrueResult);
    }

    public String getNameFile() {
        return NameFile;
    }

    public void setNameFile(String NameFile) {
        this.NameFile = NameFile;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTime() {
        return Time;
    }

    public String getDownloadDate() {
        return DownloadDate;
    }

    public String getDownloadTime() {
        return DownloadTime;
    }

    public String getTrueResult() {
        return TrueResult;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public void setDownloadDate(String DownloadDate) {
        this.DownloadDate = DownloadDate;
    }

    public void setDownloadTime(String DownloadTime) {
        this.DownloadTime = DownloadTime;
    }

    public void setTrueResult(String TrueResult) {
        this.TrueResult = TrueResult;
    }

}
