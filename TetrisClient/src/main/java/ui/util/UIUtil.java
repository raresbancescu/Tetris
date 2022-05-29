package ui.util;

import javax.swing.*;

public class UIUtil
{
    /**
     * Switches between the 2 frames given as parameters
     * @param frame1
     * @param frame2
     */
    public static void switchFrames(JFrame frame1, JFrame frame2)
    {
        frame1.dispose();
        frame2.setVisible(true);
    }
}
