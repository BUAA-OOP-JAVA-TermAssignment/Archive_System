package data;

import java.util.Date;

public class BookData {
    private String Id;
    private String name;
    private String author;

    private int downloadCnt;
    private float score;
    private String matchedText;

    public BookData(String Id, String name, String author, String matchedText, int downloadCnt, float score) {
        this.Id = Id;
        this.name = name;
        this.author = author;
        this.matchedText = matchedText;
        this.downloadCnt = downloadCnt;
        this.score = score;
    }

    public void addDownloadCnt() {
        this.downloadCnt++;
    }

    public String getId() {
        return this.Id;
    }

    public String getName() {
        return this.name;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getDownloadCnt() {
        return this.downloadCnt;
    }

    public String getMatchedText() {
        return this.matchedText;
    }

    public float getScore() {
        return this.score;
    }
}
