package message;

public class DownloadCntAddMsg extends BaseMsg{
    private String Id;
    private int downloadCnt;

    public DownloadCntAddMsg(String Id, int downloadCnt) {
        super(DOWNLOADCNT_ADD);
    }

    public String getId(){return Id;}

    private int getDownloadCnt(){return downloadCnt;}

}
