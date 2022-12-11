package view;

public interface WaitModeAble {
    /**
     * 由于使用单通道的通信，不能同时发送并处理两个及以上的消息。
     * 除了在业务逻辑中使用互斥锁，来保证不会同时有两个方法尝试与服务器通信。
     * 在一个按钮激活之后，会由控制器调用各个窗口的该方法，来将各个按钮设置为不可点击状态。
     */
    void enWaitMode();

    /**
     * 解除各个按钮的不可点击状态。
     */
    void disWaitMode();
}
