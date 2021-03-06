import hu.bme.mit.trainbenchmark.config.ExecutionConfig
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBase
import hu.bme.mit.trainbenchmark.generator.config.Scenario
import hu.bme.mit.trainbenchmark.generator.emf.config.EmfGeneratorConfigBuilder
import hu.bme.mit.trainbenchmark.generator.graph.neo4j.config.Neo4jGraphGeneratorConfigBuilder
import hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.config.TinkerGraphFormat
import hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.config.TinkerGraphGeneratorConfigBuilder
import hu.bme.mit.trainbenchmark.generator.rdf.config.RdfGeneratorConfigBuilder
import hu.bme.mit.trainbenchmark.generator.runner.GeneratorRunner
import hu.bme.mit.trainbenchmark.generator.sql.config.SqlGeneratorConfigBuilder
import hu.bme.mit.trainbenchmark.rdf.RdfFormat

def ec = new ExecutionConfig(4000, 6000)
def minSize = 1
def maxSize = 32

def scenarios = [
	Scenario.INJECT,
]

def formats = [
	new EmfGeneratorConfigBuilder(),
]

for (scenario in scenarios) {
	formats.each { generatorConfigBuilder ->
		try {
			for (def size = minSize; size <= maxSize; size *= 2) {
				println("Scenario: ${scenario}, size: ${size}")

				def configBase = new GeneratorConfigBase(scenario, size)
				def config = generatorConfigBuilder.setConfigBase(configBase).createConfig()

				def exitValue = GeneratorRunner.run(config, ec)
				if (exitValue != 0) {
					println "Timeout or error occured, skipping models for larger sizes. Error code: ${exitValue}"
					break
				}
			}
		} catch (all) {
			println "Exception occured during execution."
		}
	}
}
