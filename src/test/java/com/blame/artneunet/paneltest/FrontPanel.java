package com.blame.artneunet.paneltest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Random;

import javax.swing.JPanel;

public class FrontPanel extends JPanel {

	private Random random;
	
	public FrontPanel() throws IOException {
		super();
		
		setSize(500, 500);
		setOpaque(false);
		setBackground(new Color(213, 134, 145, 123));
		random = new Random();
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(getBackground());
        Rectangle r = g.getClipBounds();
        g.fillRect(r.x, r.y, r.width, r.height);
		
        g.setColor(Color.BLACK);
		g.drawLine(20 + random.nextInt(10), 20 + random.nextInt(10), 80 + random.nextInt(10), 80 + random.nextInt(10));
		
		super.paint(g);
	}
	
}
