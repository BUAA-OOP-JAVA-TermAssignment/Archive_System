package message;

import java.io.Serializable;

public class BaseMsg implements Serializable {
    public final static int SUCCESS = 0, UNDEFINED_FAILED = -1, TIME_OUT = -2;
    public final static int LOGIN = 1 << 3;
    public final static int REGISTER = 1 << 4;
    public final static int SUGGEST_ARCHIVE = 1 << 5;
    public final static int SEARCH_ARCHIVE = 1 << 6;
    public final static int MODIFY_USER_INFO = 1 << 7;
    public final static int ADMIN_USER_EDIT = 1 << 8;
    public final static int ADMIN_ARCHIVE_EDIT = 1 << 9;
    private final int msgCode;

    public BaseMsg(int msgCode) {
        this.msgCode = msgCode;
    }

    public int getMsgCode() {
        return msgCode;
    }
}
