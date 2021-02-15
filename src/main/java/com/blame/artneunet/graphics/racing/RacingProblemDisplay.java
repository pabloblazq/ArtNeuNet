package com.blame.artneunet.graphics.racing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.blame.artneunet.graphics.ProblemDisplay;
import com.blame.artneunet.network.Network;
import com.blame.artneunet.problemarena.common.Point;
import com.blame.artneunet.problemarena.racing.Racer;
import com.blame.artneunet.problemarena.racing.RacingProblemArena;

@SuppressWarnings("serial")
public class RacingProblemDisplay extends ProblemDisplay {

	private static final Logger logger = LogManager.getLogger(RacingProblemDisplay.class);

	protected List<Network> winnerNetworks;
	protected Image backgroundImage;

	public RacingProblemDisplay(int sizeX, int sizeY, String trackFilepathname) {
		super(sizeX, sizeY);
		delayTimeBetweenIterations = 50;
		try {
			backgroundImage = ImageIO.read(RacingProblemDisplay.class.getResourceAsStream(trackFilepathname));
		} catch (IOException e) {
			logger.catching(e);
		}
	}

	public void initialize(RacingProblemArena racingProblemArena, List<Network> winnerNetworks) {
		initializeProblemArena(racingProblemArena);
		this.winnerNetworks = winnerNetworks;
	}
	
	@Override
	public void paintProblemArenaSituation(Graphics2D g2d) {
		if(problemArena == null) {
			return;
		}
		
		g2d.drawImage(backgroundImage, 0, 0, null);
		
    	g2d.setColor(Color.LIGHT_GRAY);
		for(Racer racer : ((RacingProblemArena) problemArena).getRacerList()) {
			Point position = racer.getPositionHistory().get(iteration);
			if(!isWinner(racer)) {
				paintRacer(g2d, position);
			}
		}
    	g2d.setColor(Color.BLUE);
    	for(Racer racer : ((RacingProblemArena) problemArena).getRacerList()) {
    		Point position = racer.getPositionHistory().get(iteration);
    		if(isWinner(racer)) {
    			paintRacer(g2d, position);
    		}
    	}
	}

	private boolean isWinner(Racer racer) {
		return winnerNetworks != null && winnerNetworks.contains(racer.getNetwork());
	}

	private void paintRacer(Graphics2D g2d, Point position) {
		int pixelX = (int) Math.round(position.getX());
		int pixelY = sizeY - (int) Math.round(position.getY()) -1;
		g2d.fillOval(pixelX -2, pixelY -2, 5, 5);
	}

	public void close() {
		// TODO Auto-generated method stub
	}

}
