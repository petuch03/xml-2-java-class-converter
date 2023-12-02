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

    @Test
    fun stringConversionTest() {
        val expectedType = JavaType.STRING
        assertEquals(expectedType, JavaClassGeneratorImpl().identifyType("12345678a "))
        assertEquals(expectedType, JavaClassGeneratorImpl().identifyType("3.10"))
        assertEquals(expectedType, JavaClassGeneratorImpl().identifyType("     "))
        assertEquals(expectedType, JavaClassGeneratorImpl().identifyType("9 99"))
        assertEquals(expectedType, JavaClassGeneratorImpl().identifyType("new-york"))
        assertEquals(expectedType, JavaClassGeneratorImpl().identifyType(",., ., ."))
    }

    @Test
    fun intConversionTest() {
        val expectedType = JavaType.INT
        assertEquals(expectedType, JavaClassGeneratorImpl().identifyType("111"))
        assertEquals(expectedType, JavaClassGeneratorImpl().identifyType("3"))
        assertEquals(expectedType, JavaClassGeneratorImpl().identifyType("-012"))
        assertEquals(expectedType, JavaClassGeneratorImpl().identifyType("+0"))
    }
}
