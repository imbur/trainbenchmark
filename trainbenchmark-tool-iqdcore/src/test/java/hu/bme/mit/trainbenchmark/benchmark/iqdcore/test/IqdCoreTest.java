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

package hu.bme.mit.trainbenchmark.benchmark.iqdcore.test;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.IqdCoreBenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.config.IqdCoreBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.test.TrainBenchmarkTest;

public class IqdCoreTest extends TrainBenchmarkTest {

	@Override
	protected BenchmarkResult runTest(BenchmarkConfigCore bc) throws Exception {
		final int messageSize = 16;
		final IqdCoreBenchmarkConfigWrapper config = new IqdCoreBenchmarkConfigWrapper(bc, messageSize, "", null);
		final IqdCoreBenchmarkScenario scenario = IqdCoreBenchmarkScenario.create(config);
		final BenchmarkResult result = scenario.performBenchmark();
		return result;
	}

}