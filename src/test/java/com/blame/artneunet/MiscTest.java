package com.blame.artneunet;

import org.junit.Test;

import com.blame.artneunet.problemarena.common.Point;

public class MiscTest {

	@Test
	public void test() {
		
		Point point1 = new Point(1d, 1d);
		Point point2 = new Point(3d, 1d);
		
		Point pointMedium = Point.mediumPoint(point1, point2);

		Point point3 = new Point(-1d, -1d);
		Point point4 = new Point(6d, -1d);

		Point pointInter = Point.intersection(point2, point1, point4, point3);
		
		System.out.println(pointMedium);
		System.out.println(pointInter);
	}
}
