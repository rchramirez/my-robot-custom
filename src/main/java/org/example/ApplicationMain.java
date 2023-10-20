package org.example;

import java.awt.*;

public class ApplicationMain
{
   public static void main(final String[] args)
    {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main window = new Main();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}