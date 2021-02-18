package com.blame.artneunet.paneltest;

import java.awt.HeadlessException;
import java.io.IOException;

import javax.swing.JFrame;

public class FrameTwoPanels extends JFrame {

	public FrameTwoPanels() throws HeadlessException, IOException {
		super();

        setTitle(this.getClass().getSimpleName());
        setSize(1000, 1000);
        //setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        getContentPane().add(new BackgroundPanel());
        getContentPane().add(new FrontPanel());
        
        setVisible(true);
	}

}
