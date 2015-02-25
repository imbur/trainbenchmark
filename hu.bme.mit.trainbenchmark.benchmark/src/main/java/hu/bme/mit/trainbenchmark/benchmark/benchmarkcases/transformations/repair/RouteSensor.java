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
package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.repair;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.TransformationDefinition;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

import java.io.IOException;

public class RouteSensor extends TransformationDefinition {

	@Override
	protected void lhs() throws IOException {
		elementsToModify = pickRandom(nElementsToModify, elements);
	}

	@Override
	protected void rhs() throws IOException {
		for (final Object sensor : elementsToModify) {
			driver.deleteAllIncomingEdges(sensor, ModelConstants.TRACKELEMENT_SENSOR, ModelConstants.TRACKELEMENT);
		}
	}

}