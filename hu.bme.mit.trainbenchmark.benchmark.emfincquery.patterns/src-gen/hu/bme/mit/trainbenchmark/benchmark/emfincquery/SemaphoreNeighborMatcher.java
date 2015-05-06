package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.SemaphoreNeighborQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Route;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.incquery.runtime.api.IMatchProcessor;
import org.eclipse.incquery.runtime.api.IQuerySpecification;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BaseMatcher;
import org.eclipse.incquery.runtime.exception.IncQueryException;
import org.eclipse.incquery.runtime.matchers.tuple.Tuple;
import org.eclipse.incquery.runtime.util.IncQueryLoggingUtil;

/**
 * Generated pattern matcher API of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.SemaphoreNeighbor pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(IncQueryEngine)},
 * e.g. in conjunction with {@link IncQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link SemaphoreNeighborMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern SemaphoreNeighbor(route1) {
 * 	Route.exit(route1, semaphore);
 * 	Route.definedBy(route1, sensor1);
 * 	TrackElement.sensor(te1, sensor1);
 * 	TrackElement.connectsTo(te1, te2);
 * 	TrackElement.sensor(te2, sensor2);
 * 	Route.definedBy(route2, sensor2);
 * 	
 * 	neg find entrySemaphore(route2, semaphore);
 * 
 * 	route1 != route2;
 * }
 * </pre></code>
 * 
 * @see SemaphoreNeighborMatch
 * @see SemaphoreNeighborProcessor
 * @see SemaphoreNeighborQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class SemaphoreNeighborMatcher extends BaseMatcher<SemaphoreNeighborMatch> {
  /**
   * Initializes the pattern matcher within an existing EMF-IncQuery engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing EMF-IncQuery engine in which this matcher will be created.
   * @throws IncQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static SemaphoreNeighborMatcher on(final IncQueryEngine engine) throws IncQueryException {
    // check if matcher already exists
    SemaphoreNeighborMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = new SemaphoreNeighborMatcher(engine);
    	// do not have to "put" it into engine.matchers, reportMatcherInitialized() will take care of it
    }
    return matcher;
  }
  
  private final static int POSITION_ROUTE1 = 0;
  
  private final static Logger LOGGER = IncQueryLoggingUtil.getLogger(SemaphoreNeighborMatcher.class);
  
  /**
   * Initializes the pattern matcher over a given EMF model root (recommended: Resource or ResourceSet).
   * If a pattern matcher is already constructed with the same root, only a light-weight reference is returned.
   * The scope of pattern matching will be the given EMF model root and below (see FAQ for more precise definition).
   * The match set will be incrementally refreshed upon updates from this scope.
   * <p>The matcher will be created within the managed {@link IncQueryEngine} belonging to the EMF model root, so
   * multiple matchers will reuse the same engine and benefit from increased performance and reduced memory footprint.
   * @param emfRoot the root of the EMF containment hierarchy where the pattern matcher will operate. Recommended: Resource or ResourceSet.
   * @throws IncQueryException if an error occurs during pattern matcher creation
   * @deprecated use {@link #on(IncQueryEngine)} instead, e.g. in conjunction with {@link IncQueryEngine#on(Notifier)}
   * 
   */
  @Deprecated
  public SemaphoreNeighborMatcher(final Notifier emfRoot) throws IncQueryException {
    this(IncQueryEngine.on(emfRoot));
  }
  
  /**
   * Initializes the pattern matcher within an existing EMF-IncQuery engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing EMF-IncQuery engine in which this matcher will be created.
   * @throws IncQueryException if an error occurs during pattern matcher creation
   * @deprecated use {@link #on(IncQueryEngine)} instead
   * 
   */
  @Deprecated
  public SemaphoreNeighborMatcher(final IncQueryEngine engine) throws IncQueryException {
    super(engine, querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pRoute1 the fixed value of pattern parameter route1, or null if not bound.
   * @return matches represented as a SemaphoreNeighborMatch object.
   * 
   */
  public Collection<SemaphoreNeighborMatch> getAllMatches(final Route pRoute1) {
    return rawGetAllMatches(new Object[]{pRoute1});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pRoute1 the fixed value of pattern parameter route1, or null if not bound.
   * @return a match represented as a SemaphoreNeighborMatch object, or null if no match is found.
   * 
   */
  public SemaphoreNeighborMatch getOneArbitraryMatch(final Route pRoute1) {
    return rawGetOneArbitraryMatch(new Object[]{pRoute1});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pRoute1 the fixed value of pattern parameter route1, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Route pRoute1) {
    return rawHasMatch(new Object[]{pRoute1});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pRoute1 the fixed value of pattern parameter route1, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Route pRoute1) {
    return rawCountMatches(new Object[]{pRoute1});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pRoute1 the fixed value of pattern parameter route1, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Route pRoute1, final IMatchProcessor<? super SemaphoreNeighborMatch> processor) {
    rawForEachMatch(new Object[]{pRoute1}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pRoute1 the fixed value of pattern parameter route1, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Route pRoute1, final IMatchProcessor<? super SemaphoreNeighborMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pRoute1}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pRoute1 the fixed value of pattern parameter route1, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public SemaphoreNeighborMatch newMatch(final Route pRoute1) {
    return SemaphoreNeighborMatch.newMatch(pRoute1);
  }
  
  /**
   * Retrieve the set of values that occur in matches for route1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Route> rawAccumulateAllValuesOfroute1(final Object[] parameters) {
    Set<Route> results = new HashSet<Route>();
    rawAccumulateAllValues(POSITION_ROUTE1, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for route1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfroute1() {
    return rawAccumulateAllValuesOfroute1(emptyArray());
  }
  
  @Override
  protected SemaphoreNeighborMatch tupleToMatch(final Tuple t) {
    try {
    	return SemaphoreNeighborMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Route) t.get(POSITION_ROUTE1));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected SemaphoreNeighborMatch arrayToMatch(final Object[] match) {
    try {
    	return SemaphoreNeighborMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Route) match[POSITION_ROUTE1]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected SemaphoreNeighborMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return SemaphoreNeighborMatch.newMutableMatch((hu.bme.mit.trainbenchmark.railway.Route) match[POSITION_ROUTE1]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  /**
   * @return the singleton instance of the query specification of this pattern
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static IQuerySpecification<SemaphoreNeighborMatcher> querySpecification() throws IncQueryException {
    return SemaphoreNeighborQuerySpecification.instance();
  }
}