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
package hu.bme.mit.trainbenchmark.benchmark.sesame.matches;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT3;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SENSOR;

import org.openrdf.model.URI;
import org.openrdf.query.BindingSet;

import hu.bme.mit.trainbenchmark.benchmark.matches.ConnectedSegmentsInjectMatch;

public class SesameConnectedSegmentsInjectMatch extends SesameMatch implements ConnectedSegmentsInjectMatch {

	public SesameConnectedSegmentsInjectMatch(final BindingSet bs) {
		super(bs);
	}

	@Override
	public URI getSensor() {
		return (URI) bs.getValue(VAR_SENSOR);
	}

	@Override
	public URI getSegment1() {
		return (URI) bs.getValue(VAR_SEGMENT1);
	}

	@Override
	public URI getSegment3() {
		return (URI) bs.getValue(VAR_SEGMENT3);
	}

}
