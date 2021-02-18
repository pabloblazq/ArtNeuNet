package com.blame.artneunet.paneltest;

import java.awt.HeadlessException;
import java.io.IOException;

import org.junit.Test;

public class FrameTwoPanelsTest {

	@Test
	public void testFrameTwoPanels() throws HeadlessException, IOException, InterruptedException {
		
		FrameTwoPanels ftp = new FrameTwoPanels();
		Thread.sleep(2000);

		ftp.repaint();
		Thread.sleep(2000);
		
		ftp.repaint();
		Thread.sleep(2000);
	}
}
