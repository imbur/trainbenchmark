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
package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.viatra.config.ViatraBenchmarkConfig;

public class ViatraBenchmarkMain {

	public static void main(final String[] args) throws Exception {
		final ViatraBenchmarkConfig bc = BenchmarkConfig.fromFile(args[0], ViatraBenchmarkConfig.class);
		final ViatraBenchmarkScenario scenario = new ViatraBenchmarkScenario(bc);
		scenario.performBenchmark();
	}

}
