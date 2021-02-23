package com.blame.artneunet.graphics;

import org.junit.Test;

import com.blame.artneunet.graphics.racing.RacingProblemDisplayFromFile;
import com.blame.artneunet.problemarena.racing.RacingProblemArena;

public class RacingProblemDisplayFromFileTest {

	@Test
	public void testRacingProblemDisplayFromFile() {
		
		RacingProblemDisplayFromFile racingProblemDisplayFromFile = new RacingProblemDisplayFromFile(RacingProblemArena.class);
		racingProblemDisplayFromFile.solveProblemArena();
		racingProblemDisplayFromFile.display();
	}
}
