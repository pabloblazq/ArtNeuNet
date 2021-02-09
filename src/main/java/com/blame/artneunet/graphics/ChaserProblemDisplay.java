package com.blame.artneunet.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.blame.artneunet.network.Network;
import com.blame.artneunet.problemarena.ChaserProblemArena;
import com.blame.artneunet.problemarena.Point;

@SuppressWarnings("serial")
public class ChaserProblemDisplay extends ProblemDisplay {

	protected static ChaserProblemDisplay chaserProblemDisplay = new ChaserProblemDisplay();
	
	protected List<Network> winnerNetworks;

	private ChaserProblemDisplay() {
		super(ChaserProblemArena.DIMENSION_X, ChaserProblemArena.DIMENSION_Y);
	}
	
	public static ChaserProblemDisplay getChaserProblemDisplay() {
		return chaserProblemDisplay;
	}
	
	public void initializeChaserProblemArena(ChaserProblemArena chaserProblemArena, List<Network> winnerNetworks) {
		initializeProblemArena(chaserProblemArena);
		this.winnerNetworks = winnerNetworks;
	}

	@Override
	public void paintProblemArenaSituation(Graphics2D g2d) {
		
		List<Point> targetPosition = ((ChaserProblemArena) problemArena).getTargetPositionHistory().get(iteration);

		// paint target
    	g2d.setColor(Color.RED);
		Point targetPoint = targetPosition.get(0);
		g2d.fillOval((int) targetPoint.getX(), (int) targetPoint.getY(), 4, 4);

		// paint target temp destination
    	g2d.setColor(Color.GREEN);
		Point targetTempDestination = targetPosition.get(1);
		g2d.fillOval((int) targetTempDestination.getX(), (int) targetTempDestination.getY(), 4, 4);

		// paint winner network positions
		Map<Network, Point> positionByNetwork = ((ChaserProblemArena) problemArena).getPositionByNetworkHistory().get(iteration);
		for(Entry<Network, Point> entry : positionByNetwork.entrySet()) {
			Point networksPosition = entry.getValue();
			if(winnerNetworks == null || winnerNetworks.contains(entry.getKey())) {
		    	g2d.setColor(Color.BLUE);
			} else {
		    	g2d.setColor(Color.LIGHT_GRAY);
			}
			g2d.fillOval((int) networksPosition.getX(), (int) networksPosition.getY(), 4, 4);
		}
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}

}
