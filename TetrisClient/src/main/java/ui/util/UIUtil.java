package ui.util;

import javax.swing.*;

public class UIUtil
{
    public static void switchFrames(JFrame frame1, JFrame frame2)
    {
        frame1.dispose();
        frame2.setVisible(true);
    }
}
