package com.blame.artneunet.problemarena.racing;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.blame.artneunet.problemarena.common.ColorMap;
import com.blame.artneunet.problemarena.common.Point;

public class RacingCircuit {

	public static final String TRACK_FILE = "/track_02_test.bmp";

	private static final Logger logger = LogManager.getLogger(RacingCircuit.class);

	protected static final Color COLOR_CHECKPOINT = Color.WHITE;
	protected static final Color COLOR_TRACK = new Color(127, 127, 127);
	protected static final Color COLOR_START_POINT = new Color(204, 72, 63); // seems to have changed r by b
	
	protected ColorMap colorMap;
	protected List<Checkpoint> checkpointList;
	protected Point startPoint;

	public RacingCircuit() {

		colorMap = ColorMap.loadColorMap(TRACK_FILE);
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
		logger.info("{} checkpoints at {}", checkpointList.size(), checkpointList);
	}

	private void buildCheckpointList(List<Point> checkpointCandidateList) {
		
		checkpointList = new ArrayList<>();
		int candidatesSize = checkpointCandidateList.size();
		for(int i = 0; i < candidatesSize / 2; i++) {
			Point candidateA = checkpointCandidateList.get(0);
			checkpointCandidateList.remove(candidateA);
			Point candidateB = findNearestCandidate(candidateA, checkpointCandidateList);
			checkpointCandidateList.remove(candidateB);
			
			checkpointList.add(new Checkpoint(candidateA, candidateB, i));
		}
	}

	private Point findNearestCandidate(Point candidateA, List<Point> checkpointCandidateList) {
		
		double minDistance = 1000d;
		Point minDistanceCandidate = null;
		for(Point candidate : checkpointCandidateList) {
			double distance = Point.distance(candidateA, candidate);
			if(minDistanceCandidate == null || distance < minDistance) {
				minDistance = distance;
				minDistanceCandidate = candidate;
			}
		}

		return minDistanceCandidate;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public ColorMap getColorMap() {
		return colorMap;
	}

	public Checkpoint findCrossedCheckpoint(Point previousPosition, Point position) {
		for(Checkpoint checkpoint : checkpointList) {
			if(checkpoint.isCrossedByTransition(previousPosition, position)) {
				return checkpoint;
			}
		}

		return null;
	}

	public List<Checkpoint> getCheckpointList() {
		return checkpointList;
	}

}
