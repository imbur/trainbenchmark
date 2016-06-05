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

package hu.bme.mit.trainbenchmark.benchmark.jena.test;

import java.util.Arrays;
import java.util.Collection;

import hu.bme.mit.trainbenchmark.benchmark.test.TestBenchmarkInitializer;

public class JenaBenchmarkInitializer extends TestBenchmarkInitializer {

	protected boolean inferencing;

	public JenaBenchmarkInitializer(final boolean inferencing) {
		this.inferencing = inferencing;
	}
	
	public static Collection<Object[]> getTestParameters() {
		return Arrays.asList(new Object[][] { { true }, { false } });
	}

//	@Override
//	protected BenchmarkRunner initializeBenchmark(final RailwayQuery query, final ScenarioEnum scenario) {
//		final RdfBenchmarkConfig rbc = new RdfBenchmarkConfig("Jena", scenario, size, 1, query, iterationCount,
//				transformationStrategy, transformationConstant, inferencing);
//		return new BenchmarkRunner(rbc, new JenaBenchmarkCase());
//	}

}
