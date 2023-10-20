package org.example;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToggleButton;
import java.awt.BorderLayout;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public JFrame frame;
    private MyRobot myRobot;
    private boolean firstCycle = true;
    private JLabel lblCursorPointText;
    private JToggleButton tglbtnNewToggleButton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
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

    @SuppressWarnings("serial")
    public Action actionDELETED = new AbstractAction("UserPage?") {
        @Override
        public void actionPerformed(ActionEvent e) {
            PointerInfo a = MouseInfo.getPointerInfo();
            Point b = a.getLocation();
            int x = (int) b.getX();
            int y = (int) b.getY();
            myRobot.setCursorPoint(x, y);
            lblCursorPointText.setText("(" + x + "," + y + ")");
        }
    };

    @SuppressWarnings("serial")
    public Action actionSPACE = new AbstractAction("UserPage?") {
        @SuppressWarnings({"removal", "deprecation"})
        @Override
        public void actionPerformed(ActionEvent e) {
            myRobot.suspend();
            myRobot.moveCursorPoint();
            myRobot.click();
            tglbtnNewToggleButton.setSelected(false);
        }
    };

    /**
     * Create the application.
     */
    public Main() {
        myRobot = new MyRobot();
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        String KEY_DELETED = "UserPageActionSupr";
        String KEY_SPACE = "UserPageActionSpace";
        frame = new JFrame();
        frame.addWindowListener(new WindowAdapter() {
            @SuppressWarnings("deprecation")
            @Override
            public void windowClosing(WindowEvent e) {
                myRobot.stop();
                System.exit(0);
            }
        });

        frame.setResizable(false);
        frame.setBounds(100, 100, 450, 215);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getRootPane().getActionMap().put(KEY_DELETED, actionDELETED);
        frame.getRootPane().getActionMap().put(KEY_SPACE, actionSPACE);
        InputMap im = frame.getRootPane().getInputMap();
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), KEY_DELETED);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), KEY_SPACE);

        tglbtnNewToggleButton = new JToggleButton("");
        tglbtnNewToggleButton.setFocusable(false);
        tglbtnNewToggleButton.setBorderPainted(false);
        tglbtnNewToggleButton.setOpaque(true);
        tglbtnNewToggleButton.setIcon(new ImageIcon(getClass().getResource("/OFF.png")));
        tglbtnNewToggleButton.setSelectedIcon(new ImageIcon(getClass().getResource("/ON.png")));
        tglbtnNewToggleButton.addActionListener(new ActionListener() {
            @SuppressWarnings({"deprecation", "removal"})
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                boolean selected = abstractButton.getModel().isSelected();
                if (selected) {
                    if (firstCycle) {
                        myRobot.start();
                        firstCycle = false;
                    } else {
                        myRobot.resume();
                    }
                } else {
                    myRobot.suspend();
                }
            }
        });
        frame.getContentPane().add(tglbtnNewToggleButton, BorderLayout.WEST);

        JButton btnNewButton = new JButton("");
        btnNewButton.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            public void actionPerformed(ActionEvent e) {
                myRobot.stop();
                System.exit(0);
            }
        });
        btnNewButton.setOpaque(false);
        btnNewButton.setBorderPainted(false);
        btnNewButton.setFocusable(false);
        btnNewButton.setIcon(new ImageIcon(getClass().getResource("/exit_icon.png")));
        frame.getContentPane().add(btnNewButton, BorderLayout.EAST);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        panel.setLayout(new BorderLayout(0, 0));

        JLabel lblNewLabel = new JLabel("Cursor Point (X,Y):");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel.add(lblNewLabel, BorderLayout.WEST);

        this.lblCursorPointText = new JLabel("(1102,2022)");
        lblCursorPointText.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblCursorPointText.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblCursorPointText);

        JLabel lblNewLabel_2 = new JLabel("Atajo Supr");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel.add(lblNewLabel_2, BorderLayout.EAST);
    }

}
