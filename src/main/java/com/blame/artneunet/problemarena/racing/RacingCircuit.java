package com.blame.artneunet.problemarena.racing;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.blame.artneunet.problemarena.common.ColorMap;
import com.blame.artneunet.problemarena.common.Point;

public class RacingCircuit {

	private static final Logger logger = LogManager.getLogger(RacingCircuit.class);

	protected static final Color COLOR_CHECKPOINT = Color.WHITE;
	protected static final Color COLOR_TRACK = new Color(127, 127, 127);
	protected static final Color COLOR_START_POINT = new Color(204, 72, 63);
	
	protected ColorMap colorMap;
	protected List<Checkpoint> checkpointList;
	protected Point startPoint;

	public RacingCircuit() {

		colorMap = ColorMap.loadColorMap("/track_01.bmp");
		findRelevantItems();
	}

	private void findRelevantItems() {
		
		List<Point> checkpointCandidateList = new ArrayList<>();
		for(int rowIndex = 0; rowIndex < colorMap.getNumRows(); rowIndex++) {
			for(int columnIndex = 0; columnIndex < colorMap.getNumColumns(); columnIndex++) {
				Color color = colorMap.getColor(rowIndex, columnIndex);
				if(color.equals(COLOR_START_POINT)) {
					startPoint = new Point(columnIndex, rowIndex);
					logger.info("Starting point at {}", startPoint);
				} else if(color.equals(COLOR_CHECKPOINT)) {
					checkpointCandidateList.add(new Point(columnIndex, rowIndex));
				}
			}
		}
		
		buildCheckpointList(checkpointCandidateList);
		logger.info("Checkpoints at {}", checkpointList);
	}

	private void buildCheckpointList(List<Point> checkpointCandidateList) {
		
		checkpointList = new ArrayList<>();
		int candidatesSize = checkpointCandidateList.size();
		for(int i = 0; i < candidatesSize / 2; i++) {
			Point candidateA = checkpointCandidateList.get(0);
			checkpointCandidateList.remove(candidateA);
			Point candidateB = findNearestCandidate(candidateA, checkpointCandidateList);
			checkpointCandidateList.remove(candidateB);
			
			checkpointList.add(new Checkpoint(candidateA, candidateB));
		}
	}

	private Point findNearestCandidate(Point candidateA, List<Point> checkpointCandidateList) {
		
		float minDistance = 1000f;
		Point minDistanceCandidate = null;
		for(Point candidate : checkpointCandidateList) {
			float distance = Point.calculateDistance(candidateA, candidate);
			if(minDistanceCandidate == null || distance < minDistance) {
				minDistance = distance;
				minDistanceCandidate = candidate;
			}
		}

		return minDistanceCandidate;
	}

	public Point getStartPoint() {
		// TODO Auto-generated method stub
		return startPoint;
	}

}
