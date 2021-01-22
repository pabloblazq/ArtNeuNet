package com.blame.artneunet.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import com.blame.artneunet.problemarena.ChaserProblemArena;
import com.blame.artneunet.problemarena.Point;

@SuppressWarnings("serial")
public class ChaserProblemDisplay extends ProblemDisplay {

	public ChaserProblemDisplay(int sizeX, int sizeY) {
		super(sizeX, sizeY);
	}

	@Override
	public void paintProblemArenaSituation(Graphics2D g2d) {
		
		List<Point> situation = ((ChaserProblemArena) problemArena).getPositions().get(iteration);

		// paint target
    	g2d.setColor(Color.RED);
		Point targetPoint = situation.get(0);
		g2d.drawOval((int) targetPoint.getX(), (int) targetPoint.getY(), 4, 4);

		// paint network entity
    	g2d.setColor(Color.BLUE);
		Point networkEntityPoint = situation.get(1);
		g2d.drawOval((int) networkEntityPoint.getX(), (int) networkEntityPoint.getY(), 4, 4);
		
	}

}
