package com.blame.artneunet.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.blame.artneunet.problemarena.ProblemArena;

@SuppressWarnings("serial")
public abstract class ProblemDisplay extends JFrame {

    protected JPanel jPanel;
    
	protected int sizeX;
	protected int sizeY;
	
	protected ProblemArena problemArena;
	protected int iteration;

	protected long delayTimeBetweenIterations = 100;

    public ProblemDisplay(int sizeX, int sizeY) {
    	
    	this.sizeX = sizeX;
    	this.sizeY = sizeY;
    	
    	// init window
        setTitle(this.getClass().getSimpleName());
        setSize(sizeX, sizeY);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
	}

    public void initializeProblemArena(ProblemArena problemArena) {
    	this.problemArena = problemArena;
    	this.iteration = 0;
    	
    	// init panel
    	jPanel = new JPanel();
        add(jPanel);
        jPanel.setPreferredSize(new Dimension(sizeX, sizeY));
        jPanel.setOpaque(false);
    }
    
	public boolean incrementIteration() {
		iteration++;
		return iteration == problemArena.getNumProblemIterations();
	}

    @Override
    public void paint(Graphics g) {
    	super.paint(g);
    	Graphics2D g2d = (Graphics2D) g;

    	paintProblemArenaSituation(g2d);
    }

    /**
     * 
     */
    public abstract void paintProblemArenaSituation(Graphics2D g2d);
    
	public void display() {
		try {
			while(!incrementIteration()) {
				Thread.sleep(delayTimeBetweenIterations);
				repaint(); // ends up calling this object paint method
			}
			Thread.sleep(5000);
		} catch (InterruptedException e) {/* nothing to do */}
	}
	
}
