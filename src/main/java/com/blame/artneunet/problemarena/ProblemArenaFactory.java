package com.blame.artneunet.problemarena;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.blame.artneunet.network.Network;

public class ProblemArenaFactory {
	
	private static final Logger logger = LogManager.getLogger(ProblemArenaFactory.class);

	public static ProblemArena getProblemArenaInstance(Class<? extends ProblemArena> problemArenaClass, List<Network> networkList) {
		try {
			return problemArenaClass.getConstructor(List.class).newInstance(networkList);
		} catch (ReflectiveOperationException e) {
			logger.catching(e);
			return null;
		}
	}
}
