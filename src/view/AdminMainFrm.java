package view;

public class AdminMainFrm extends MyFrame{
    private static final AdminMainFrm adminMainFrm = new AdminMainFrm();

    private AdminMainFrm() {
    }

    public static AdminMainFrm getInstance() {
        return adminMainFrm;
    }
}
