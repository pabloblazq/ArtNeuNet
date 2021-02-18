package com.blame.artneunet.paneltest;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.blame.artneunet.graphics.racing.RacingProblemDisplay;

public class BackgroundPanel extends JPanel {

	private BufferedImage backgroundImage;
	private boolean painted = false;

	public BackgroundPanel() throws IOException {
		super();
		
		backgroundImage = ImageIO.read(RacingProblemDisplay.class.getResourceAsStream("/track_01.bmp"));
		setSize(500, 500);
	}

	@Override
	public void paint(Graphics g) {
		System.out.println("back paint");
		if(!painted) {
			super.paint(g);
			g.drawImage(backgroundImage, 0, 0, null);
			painted = true;
		}
	}
	
}
