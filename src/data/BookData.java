package data;

import java.util.Date;

public class BookData {
    private String Id;
    private String name;
    private String author;
    private String publish;
    private String introduction;
    private String language;
    private Date uploadDate;
    private int downloadCnt;
    private BookData(){
    }

    public void readInfo(String Id, String name, String author, String publish, String introduction, String language, Date uploadDate, int downloadCnt){
        this.Id = Id;
        this.name = name;
        this.author = author;
        this.publish = publish;
        this.introduction = introduction;
        this.language = language;
        this.uploadDate = uploadDate;
        this.downloadCnt = downloadCnt;
    }

    public void addDownloadCnt(){
        this.downloadCnt++;
    }

    public String getId(){
        return this.Id;
    }

    public String getName(){
        return this.name;
    }

    public String getAuthor(){
        return this.author;
    }

    public String getPublish(){
        return this.publish;
    }

    public String getIntroduction(){
        return this.introduction;
    }

    public String getLanguage(){
        return this.language;
    }

    public Date getUploadDate(){
        return this.uploadDate;
    }

    public int getDownloadCnt(){
        return this.downloadCnt;
    }
}
