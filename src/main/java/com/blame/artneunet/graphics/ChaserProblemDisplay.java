package com.blame.artneunet.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Collection;
import java.util.List;

import com.blame.artneunet.problemarena.ChaserProblemArena;
import com.blame.artneunet.problemarena.Point;

@SuppressWarnings("serial")
public class ChaserProblemDisplay extends ProblemDisplay {

	public ChaserProblemDisplay(ChaserProblemArena chaserProblemArena) {
		super(chaserProblemArena, ChaserProblemArena.DIMENSION_X, ChaserProblemArena.DIMENSION_Y);
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

		// paint network positions
		Collection<Point> networksPositions = ((ChaserProblemArena) problemArena).getPositionByNetworkHistory().get(iteration).values();
		for(Point networksPosition : networksPositions) {
	    	g2d.setColor(Color.BLUE);
			g2d.fillOval((int) networksPosition.getX(), (int) networksPosition.getY(), 4, 4);
		}
	}

}
