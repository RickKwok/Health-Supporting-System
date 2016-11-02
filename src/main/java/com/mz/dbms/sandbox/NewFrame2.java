package com.mz.dbms.sandbox;

/**
 * Created by Muchen on 10/8/16.
 */
//import statements here
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NewFrame2 extends JFrame implements ActionListener
{
    //initialises the frame and opens it
    public NewFrame2()
    {
        JButton open = new JButton("New Window");
        open.addActionListener(this);
        add(open);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent event)
    {
        //code for the new frame
    }
}