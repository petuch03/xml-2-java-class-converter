package petuch03.xml2java

import petuch03.xml2java.javaclassgenerator.JavaClassGeneratorImpl
import petuch03.xml2java.utils.JavaType
import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class AppTest {
    @Test
    fun testXMLToJavaConversion() {
        val testInputPath = "src/test/resources/defaultExample.xml"
        val expectedOutputPath = "src/test/resources/defaultExample.java"
        val testOutputDir = "src/test/resources"
        val resultOutputPath = "$testOutputDir/PythonStep.java"

        App().run(testInputPath, testOutputDir)

        val generatedOutput: String = File(resultOutputPath).readText()
        File(resultOutputPath).delete()

        val expectedOutput: String = File(expectedOutputPath).readText()

        assertEquals(expectedOutput.trim(), generatedOutput.trim())
    }
}
