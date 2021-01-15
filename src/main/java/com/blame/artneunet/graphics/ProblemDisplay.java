package com.blame.artneunet.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class ProblemDisplay extends JFrame {

    protected JPanel jPanel;
	protected int sizeX;
	protected int sizeY;

    public ProblemDisplay(int sizeX, int sizeY) {
    	this.sizeX = sizeX;
    	this.sizeY = sizeY;
    	
    	// init panel
    	jPanel = new JPanel();
        add(jPanel);
        jPanel.setPreferredSize(new Dimension(sizeX, sizeY));
    	
    	// init window
        setTitle(this.getClass().getSimpleName());
        setSize(sizeX, sizeY);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
	}

    /**
     * 
     */
    public abstract void paintProblemArenaSituation(Graphics2D g2d);
    
    @Override
    public void paint(Graphics g) {
    	super.paint(g);
    	Graphics2D g2d = (Graphics2D) g;

    	g2d.setColor(Color.BLUE);
    	paintNetworkEntity(g2d);
    }

    /**
     * 
     * @param g2d
     */
	public abstract void paintNetworkEntity(Graphics2D g2d);
}
