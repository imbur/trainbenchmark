/*******************************************************************************
 * Copyright (c) 2010-2015, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/

package hu.bme.mit.trainbenchmark.benchmark.graphflow;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.config.GraphflowBenchmarkConfig;

public class GraphflowBenchmarkMain {

	public static void main(final String[] args) throws Exception {
		final GraphflowBenchmarkConfig bc = BenchmarkConfig.fromFile(args[0], GraphflowBenchmarkConfig.class);
		final GraphflowBenchmarkScenario scenario = new GraphflowBenchmarkScenario(bc);
		scenario.performBenchmark();
	}

}