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
package hu.bme.mit.trainbenchmark.benchmark.graphflow.driver;

import ca.waterloo.dsg.graphflow.query.QueryProcessor;
import ca.waterloo.dsg.graphflow.query.result.QueryResult;
import ca.waterloo.dsg.graphflow.server.ServerQueryString;
import com.google.common.collect.ImmutableMap;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.matches.GraphflowMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class GraphflowDriver extends Driver {

	public GraphflowDriver() throws IOException {
		super();
	}

	@Override
	public void initialize() throws Exception {
		super.initialize();
	}

	@Override
	public void destroy() {
	}

	@Override
	public void read(final String modelPath) {
		final String POSTFIX = ".csv";
		final Map<String, Collection<String>> vertexFiles = ImmutableMap.<String, Collection<String>>builder() //
			.put("Region"        , Arrays.asList("Region")) //
			.put("Route"         , Arrays.asList("Route")) //
			.put("Segment"       , Arrays.asList("Segment", "TrackElement")) //
			.put("Semaphore"     , Arrays.asList("Semaphore")) //
			.put("Sensor"        , Arrays.asList("Sensor")) //
			.put("Switch"        , Arrays.asList("Switch", "TrackElement")) //
			.put("SwitchPosition", Arrays.asList("SwitchPosition")) //
			.build();

		final Map<String, String> edgeFiles = ImmutableMap.<String, String>builder() //
			.put("connectsTo" , "connectsTo") //
			.put("entry"      , "entry") //
			.put("exit"       , "exit") //
			.put("follows"    , "follows") //
			.put("monitoredBy", "monitoredBy") //
			.put("requires"   , "requires") //
			.put("target"     , "target") //
			.build();

		// TODO
		// load vertices

		// load edges
	}

	@Override
	public String getPostfix() {
		return "cypher";
	}

	@Override
	public Number generateNewVertexId() throws Exception {
		return null;
	}

	public Collection<GraphflowMatch> runQuery(final RailwayQuery query, final String queryDefinition) throws IOException {
		final Collection<GraphflowMatch> results = new ArrayList<>();

		final ServerQueryString serverQueryString = ServerQueryString.newBuilder().setQuery(queryDefinition).build();
		final QueryProcessor processor = new QueryProcessor();
		final QueryResult queryResult = processor.process(serverQueryString);

		System.out.println(queryDefinition);
		System.out.println(queryResult.toString());

		return results;
	}

	public void runTransformation(final String transformationDefinition, final Map<String, Object> parameters)
			throws IOException {
		throw new UnsupportedOperationException("TODO: Implement transformations for Graphflow	");
	}

}