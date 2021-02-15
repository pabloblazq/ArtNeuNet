package com.blame.artneunet;

import org.junit.Test;

import com.blame.artneunet.problemarena.common.Point;
import com.blame.artneunet.problemarena.racing.Checkpoint;
import com.blame.artneunet.problemarena.util.AngleUtil;

public class MiscTest {

	@Test
	public void test() {
		
		Point point1 = new Point(1d, 1d);
		Point point2 = new Point(3d, 3d);
		Checkpoint checkpoint = new Checkpoint(point1, point2);

		Point point3 = new Point(2d, 4d);
		Point point4 = new Point(4d, 2d);

		double angle = AngleUtil.sumAngles(0, -AngleUtil.PI_2);
		boolean cross = checkpoint.isCrossedByTransition(point3, point4);
		System.out.println(cross);
	}
}
