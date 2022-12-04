package view;

public interface ClickChangeable {
    //TODO:控制器相关逻辑，当有事件向网络控制器提交时，需要避免有新的事件提交，并在得到回应或超时后返回
    void disAbleClickable();
    void enAbleClickable();
}
