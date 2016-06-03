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
package hu.bme.mit.trainbenchmark.benchmark.emf.benchmarkcases;

import java.util.Comparator;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfMatchComparator;
import hu.bme.mit.trainbenchmark.emf.EmfDriver;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;

public abstract class EmfBenchmarkCase<TDriver extends EmfDriver, TBenchmarkConfig extends BenchmarkConfig>
		extends AbstractBenchmarkCase<EmfMatch, RailwayElement, TDriver, TBenchmarkConfig> {

	@Override
	public Comparator<EmfMatch> getMatchComparator() {
		return new EmfMatchComparator();
	}

}
