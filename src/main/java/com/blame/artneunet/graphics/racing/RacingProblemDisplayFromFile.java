package com.blame.artneunet.graphics.racing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.blame.artneunet.network.Network;
import com.blame.artneunet.problemarena.ProblemArena;
import com.blame.artneunet.problemarena.ProblemArenaFactory;
import com.blame.artneunet.problemarena.racing.RacingProblemArena;

public class RacingProblemDisplayFromFile {

	private static final Logger logger = LogManager.getLogger(RacingProblemDisplayFromFile.class);

	protected RacingProblemArena racingProblemArena;
	protected List<Network> networkList;

	public RacingProblemDisplayFromFile(Class<? extends ProblemArena> racingProblemArenaClass) {
		super();

		networkList = new ArrayList<>();
		try {
			String problemFolderName = "output/" + racingProblemArenaClass.getSimpleName();
			for(String subfolderName : new File(problemFolderName).list()) {
				for(String networkFilename : new File(problemFolderName + "/" + subfolderName).list()) {
					logger.info("loading network {}/{}", subfolderName, networkFilename);
			        FileInputStream fis = new FileInputStream(problemFolderName + "/" + subfolderName + "/" + networkFilename);
			        ObjectInputStream ois = new ObjectInputStream(fis);
			        networkList.add(((Network) ois.readObject()).setEnabled(true));
			        ois.close();
				}
			}
		} catch (Exception e) {
			logger.catching(e);
		}

		this.racingProblemArena = (RacingProblemArena) ProblemArenaFactory.getProblemArenaInstance(racingProblemArenaClass, networkList);
	}
	
	public void solveProblemArena() {
		racingProblemArena.processProblem();
	}
	
	public void display() {
		racingProblemArena.display(networkList);
	}
}
