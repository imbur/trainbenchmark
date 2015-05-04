/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.sql.match;

import hu.bme.mit.trainbenchmark.benchmark.matches.LongMatch;

/**
 * Due to the iterator-like nature of the java.sql.ResultSet interface, we cannot store the ResultSet and have to copy its contents. This is
 * implemented for each match in the subclasses of the SQLMatch class.
 * 
 * @author szarnyasg
 * 
 */
public abstract class SQLMatch extends LongMatch {

}
