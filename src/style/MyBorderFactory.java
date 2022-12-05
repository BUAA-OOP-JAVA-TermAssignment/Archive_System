package style;

import javax.swing.border.Border;
import java.awt.*;

public class MyBorderFactory {
    public static Border createRectBorder() {
        return new Border() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                g.drawRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, 15, 15);
            }

            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(5, 5, 5, 5);
            }

            @Override
            public boolean isBorderOpaque() {
                return false;
            }
        };
    }
}
