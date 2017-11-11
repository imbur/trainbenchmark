package hu.bme.mit.trainbenchmark.generator.tests;

import hu.bme.mit.trainbenchmark.config.ExecutionConfig;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBase;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBaseBuilder;
import hu.bme.mit.trainbenchmark.generator.config.Scenario;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;

@Ignore
@RunWith(Parameterized.class)
public abstract class GeneratorTest {

	@Parameters(name = "size={0}")
	public static Iterable<? extends Object> data() {
		return Arrays.asList(1, 2);
	}

	@Parameter
	public int size;

	protected final ExecutionConfig executionConfig = ExecutionConfig.defaultExecutionConfig();

	public abstract void generate(final GeneratorConfigBase gcb) throws Exception;

	@Test
	public void generateBatch() throws Exception {
		final Scenario scenario = Scenario.BATCH;
		final GeneratorConfigBase gcb = new GeneratorConfigBaseBuilder().setScenario(scenario).setSize(size).createGeneratorConfigBase();
		generate(gcb);
	}

	@Test
	public void generateInject() throws Exception {
		final Scenario scenario = Scenario.INJECT;
		final GeneratorConfigBase gcb = new GeneratorConfigBaseBuilder().setScenario(scenario).setSize(size).createGeneratorConfigBase();
		generate(gcb);
	}

	@Test
	public void generateRepair() throws Exception {
		final Scenario scenario = Scenario.REPAIR;
		final GeneratorConfigBase gcb = new GeneratorConfigBaseBuilder().setScenario(scenario).setSize(size).createGeneratorConfigBase();
		generate(gcb);
	}

}
