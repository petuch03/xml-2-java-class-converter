package petuch03.xml2java

import kotlinx.cli.*
import petuch03.xml2java.javaclassgenerator.JavaClassGeneratorImpl
import petuch03.xml2java.utils.FileWriter
import petuch03.xml2java.xmlparser.XMLHandlerImpl

class App {
    private val xmlHandler = XMLHandlerImpl()
    private val javaClassGenerator = JavaClassGeneratorImpl()
    private val fileWriter = FileWriter()

    fun run(inputFilePath: String, outputDir: String) {
        val xmlModel = xmlHandler.parseSingleExtension(inputFilePath)
//        val xmlPrintable = xmlHandler.restoreSingleExtension(xmlModel)
        val javaPrintable = javaClassGenerator.generateJavaClass(xmlModel)
//        fileWriter.writeToXMLFile(xmlPrintable.type, xmlPrintable.printable, outputDir)
        fileWriter.writeToJavaFile(javaPrintable.className, javaPrintable.printable, outputDir)
    }
}

fun main(args: Array<String>) {
    val argParser = ArgParser("xml-2-java-class-converter")
    val fileInputPath by argParser.option(ArgType.String, shortName = "i", description = "Path to the input XML file").required()
    val fileOutputDir by argParser.option(ArgType.String, shortName = "o", description = "Path to the output directory").required()
    argParser.parse(args)

    val mainApp = App()
    mainApp.run(fileInputPath, fileOutputDir)
}
