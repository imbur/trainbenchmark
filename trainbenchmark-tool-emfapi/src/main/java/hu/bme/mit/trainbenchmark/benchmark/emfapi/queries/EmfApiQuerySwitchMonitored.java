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

package hu.bme.mit.trainbenchmark.benchmark.emfapi.queries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriver;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfSwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.query.EmfApiQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;
import hu.bme.mit.trainbenchmark.railway.Region;
import hu.bme.mit.trainbenchmark.railway.Switch;
import hu.bme.mit.trainbenchmark.railway.TrackElement;

public class EmfApiQuerySwitchMonitored<TDriver extends EmfDriver> extends EmfApiQuery<EmfSwitchMonitoredMatch, TDriver> {

	public EmfApiQuerySwitchMonitored(final TDriver driver) {
		super(RailwayQuery.SWITCHMONITORED, driver);
	}

	@Override
	public Collection<EmfSwitchMonitoredMatch> evaluate() {
		final List<EmfSwitchMonitoredMatch> matches = new ArrayList<>();

		final EList<Region> regions = driver.getContainer().getRegions();
		for (final Region region : regions) {
			for (final TrackElement element : region.getElements()) {
				if (!RailwayPackage.eINSTANCE.getSwitch().isInstance(element)) {
					continue;
				}

				// (sw:Switch)
				final Switch sw = (Switch) element;

				// (sw)-[:monitoredBy]->() NAC
				if (sw.getMonitoredBy().isEmpty()) {
					matches.add(new EmfSwitchMonitoredMatch(sw));
				}
			}
		}

		return matches;
	}
}
