# 开发文档

## 变量的命名

记得把变量的作用和大致类型写出来，避免混淆

例如：

```java
JMenu menuAccount = new JMenu("个人账户");
```

而不要：

```java
JMenu menu_0 = new JMenu("个人账户");
```

也不要：

```java
JButton buttonLeftTop = new JButton("登录");
```

此时可以使用注释表明位置，例如：

```java
// Located at left top
JButton buttonLogin = new JButton("登录");
```

## 标准输出

遇到触发事件等等，可以添加控制台输出，标明类名以及发生了什么事，例如：

```java
menuItemSelfCentre.addActionListener(actionEvent -> {
            System.out.println("MyFrame : Menu Clicked");
            //TODO:添加打开个人中心的逻辑；
        });
```

如果是来自于异常捕获或者是不应该进入的代码块，在输出前添加三个感叹号：

```java
switch (styleType) {
            case LIGHT -> applyLight();
            case INTELLIJ -> applyIntelliJ();
            case DARK -> applyDark();
            case DARCULA -> applyDarcula();
            default -> System.out.println("!!! StyleCtrl : undefined style code");
        }
```

