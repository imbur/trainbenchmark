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

package hu.bme.mit.trainbenchmark.generator.emf;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CONNECTS_TO;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CURRENTPOSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ELEMENTS;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ENTRY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.EXIT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.FOLLOWS;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.LENGTH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.MONITORED_BY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.POSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.REGION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.REQUIRES;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SIGNAL;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCHPOSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.TARGET;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.emf.EmfConstants;
import hu.bme.mit.trainbenchmark.emf.EmfUtil;
import hu.bme.mit.trainbenchmark.generator.ModelSerializer;
import hu.bme.mit.trainbenchmark.generator.emf.config.EmfGeneratorConfig;
import hu.bme.mit.trainbenchmark.railway.RailwayContainer;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;
import hu.bme.mit.trainbenchmark.railway.RailwayFactory;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;
import hu.bme.mit.trainbenchmark.railway.Region;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Segment;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.Switch;
import hu.bme.mit.trainbenchmark.railway.SwitchPosition;

public class EmfSerializer extends ModelSerializer<EmfGeneratorConfig> {

	public EmfSerializer(final EmfGeneratorConfig generatorConfig) {
		super(generatorConfig);
	}

	@Override
	public String syntax() {
		return "EMF";
	}

	protected Resource resource;
	protected RailwayFactory factory;
	protected RailwayContainer container;

	@Override
	public void initModel() {
		EmfUtil.registerExtension();
		final String modelPath = gc.getConfigBase().getModelPathWithoutExtension() + "." + EmfConstants.MODEL_EXTENSION;
		final URI resourceURI = URI.createFileURI(modelPath);

		final ResourceSet resourceSet = new ResourceSetImpl();

		resource = resourceSet.createResource(resourceURI);
		resource.getContents().clear();

		factory = RailwayFactory.eINSTANCE;
		container = factory.createRailwayContainer();
		resource.getContents().add(container);
	}

	@Override
	public void persistModel() throws IOException {
		final String EMF_RESOURCES_DIR = "trainbenchmark-generator-emf/src/main/resources/";

		// source file
		final String srcFilePath = gc.getConfigBase().getWorkspaceDir() + EMF_RESOURCES_DIR + "railway-header.json";
		final File srcFile = new File(srcFilePath);

		// destination file
		final String destFilePath = gc.getConfigBase().getModelPathWithoutExtension() + ".json";
		final File destFile = new File(destFilePath);

		// this overwrites the destination file if it exists
		FileUtils.copyFile(srcFile, destFile);

		try (final BufferedWriter w = new BufferedWriter(new FileWriter(destFile, true))) {
			// model
			Iterable<EObject> iterator = () -> resource.getAllContents();

			for (EObject eObject : iterator) {
				if (eObject instanceof RailwayContainer)
					continue;

				final String line = toLine(eObject);
				w.write("\t\t{\t" + line + "}\n");
			}

			// footer
			final String footerFilePath = gc.getConfigBase().getWorkspaceDir() + EMF_RESOURCES_DIR
					+ "railway-footer.json";
			final File footerFile = new File(footerFilePath);
			final List<String> lines = FileUtils.readLines(footerFile, StandardCharsets.UTF_8);
			for (final String line : lines) {
				w.write(line + "\n");
			}
		}
	}

	private String quote(String s) {
		return "\"" + s + "\"";
	}

	private String keyValue(String key, String value) {
		return SEP + quote(key) + ": \"" + value + "\"";
	}

	private String keyValue(String key, int value) {
		return SEP + quote(key) + ": " + value;
	}

	private String keyValue(String key, RailwayElement value) {
		if (value == null) {
			return SEP + quote(key) + ": null";
		} else {
			return keyValue(key, value.getId());	
		}
	}
	
	private String keyValue(String key, EList<? extends RailwayElement> value) {
		return SEP + quote(key) + ": " +  
			"[" + 
			value
				.stream()
				.map(x -> Integer.toString(x.getId()))
				.collect(Collectors.joining(", ")) + 
			"]";					
	}

	private String getType(EObject o) {
		if (o instanceof Route)
			return ROUTE;
		if (o instanceof Region)
			return REGION;
		if (o instanceof Segment)
			return SEGMENT;
		if (o instanceof Semaphore)
			return SEMAPHORE;
		if (o instanceof Sensor)
			return SENSOR;
		if (o instanceof Switch)
			return SWITCH;
		if (o instanceof SwitchPosition)
			return SWITCHPOSITION;

		throw new IllegalStateException();
	}

	final String SEP = ",\t";

	private String toLine(EObject o) {
		String props = "";
		// add type information

		props += " \"allocation data\": 0";
		
		String type = getType(o);
		props += keyValue(":type", type);

		switch (type) {
		case ModelConstants.ROUTE:
			Route route = (Route) o;
			props += keyValue("active", route.getActive());
			props += keyValue(FOLLOWS,  route.getFollows());
			props += keyValue(ENTRY,    route.getEntry());
			props += keyValue(EXIT,     route.getExit());
			props += keyValue(REQUIRES, route.getRequires());
			break;
		case ModelConstants.SEGMENT:
			Segment segment = (Segment) o;
			props += keyValue(LENGTH, segment.getLength());
			props += keyValue(CONNECTS_TO, segment.getConnectsTo());
			props += keyValue(MONITORED_BY, segment.getMonitoredBy());			
			break;
		case ModelConstants.SEMAPHORE:
			Semaphore semaphore = (Semaphore) o;
			props += keyValue(SIGNAL, semaphore.getSignal().toString());
			break;
		case ModelConstants.SENSOR:
			Sensor sensor = (Sensor) o;
			props += keyValue(ELEMENTS, sensor.getMonitors());
			break;
		case ModelConstants.SWITCH:
			Switch sw = (Switch) o;
			props += keyValue(CURRENTPOSITION, sw.getCurrentPosition().toString());
			props += keyValue(CONNECTS_TO, sw.getConnectsTo());
			props += keyValue(MONITORED_BY, sw.getMonitoredBy());
			props += keyValue("POSITIONS", sw.getPositions());
			break;
		case ModelConstants.SWITCHPOSITION:
			SwitchPosition swP = (SwitchPosition) o;
			props += keyValue(ROUTE, swP.getRoute());
			props += keyValue(TARGET, swP.getTarget());
			props += keyValue(POSITION, swP.getPosition().toString());
			break;
		}
		return props;
	}

	@Override
	public Object createVertex(final int id, final String type, final Map<String, ? extends Object> attributes,
			final Map<String, Object> outgoingEdges, final Map<String, Object> incomingEdges) throws IOException {
		final EClass clazz = (EClass) RailwayPackage.eINSTANCE.getEClassifier(type);
		final RailwayElement railwayElement = (RailwayElement) RailwayFactory.eINSTANCE.create(clazz);
		railwayElement.setId(id);
		for (final Entry<String, ? extends Object> attribute : attributes.entrySet()) {
			setEmfAttribute(clazz, railwayElement, attribute.getKey(), attribute.getValue());
		}

		switch (type) {
		case ModelConstants.REGION:
			container.getRegions().add((Region) railwayElement);
			break;
		case ModelConstants.ROUTE:
			container.getRoutes().add((Route) railwayElement);
			break;
		default:
			break;
		}

		for (final Entry<String, Object> outgoingEdge : outgoingEdges.entrySet()) {
			createEdge(outgoingEdge.getKey(), railwayElement, outgoingEdge.getValue());
		}

		for (final Entry<String, Object> incomingEdge : incomingEdges.entrySet()) {
			createEdge(incomingEdge.getKey(), incomingEdge.getValue(), railwayElement);
		}

		return railwayElement;
	}

	@Override
	public void createEdge(final String label, final Object from, final Object to) throws IOException {
		final EObject objectFrom = (EObject) from;
		final EStructuralFeature edgeType = objectFrom.eClass().getEStructuralFeature(label);

		if (edgeType.isMany()) {
			@SuppressWarnings("unchecked")
			final List<Object> l = (List<Object>) objectFrom.eGet(edgeType);
			l.add(to);
		} else {
			objectFrom.eSet(edgeType, to);
		}
	}

	protected void setEmfAttribute(final EClass clazz, final RailwayElement node, final String key, Object value) {
		// change the enum value from the
		// hu.bme.mit.trainbenchmark.constants.Signal enum to the
		// hu.bme.mit.trainbenchmark.railway.Signal enum
		if (SIGNAL.equals(key)) {
			final int ordinal = ((hu.bme.mit.trainbenchmark.constants.Signal) value).ordinal();
			value = hu.bme.mit.trainbenchmark.railway.Signal.get(ordinal);
		} else if (CURRENTPOSITION.equals(key) || POSITION.equals(key)) {
			final int ordinal = ((hu.bme.mit.trainbenchmark.constants.Position) value).ordinal();
			value = hu.bme.mit.trainbenchmark.railway.Position.get(ordinal);
		}

		final EStructuralFeature feature = clazz.getEStructuralFeature(key);
		node.eSet(feature, value);
	}

}
