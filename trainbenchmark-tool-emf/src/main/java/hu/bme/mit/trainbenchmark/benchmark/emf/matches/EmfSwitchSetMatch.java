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
package hu.bme.mit.trainbenchmark.benchmark.emf.matches;

import hu.bme.mit.trainbenchmark.benchmark.matches.SwitchSetMatch;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
import hu.bme.mit.trainbenchmark.railway.Switch;
import hu.bme.mit.trainbenchmark.railway.SwitchPosition;

public class EmfSwitchSetMatch extends EmfMatch implements SwitchSetMatch {

	protected final Semaphore semaphore;
	protected final Route route;
	protected final SwitchPosition swP;
	protected final Switch sw;

	public EmfSwitchSetMatch(final Semaphore semaphore, final Route route, final SwitchPosition swP, final Switch sw) {
		super();
		this.semaphore = semaphore;
		this.route = route;
		this.swP = swP;
		this.sw = sw;
	}

	@Override
	public Semaphore getSemaphore() {
		return semaphore;
	}

	@Override
	public Route getRoute() {
		return route;
	}

	@Override
	public SwitchPosition getSwP() {
		return swP;
	}

	@Override
	public Switch getSw() {
		return sw;
	}

}
