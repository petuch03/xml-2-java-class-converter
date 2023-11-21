package petuch03.xml2java.utils

import java.io.File

class FileWriter {
    fun writeToJavaFile(className: String, content: String, outputPath: String) {
        File(outputPath).delete()
        File("$outputPath/$className.java").writeText(content)
    }

    fun writeToXMLFile(className: String, content: String, outputPath: String) {
        File(outputPath).delete()
        File("$outputPath/$className.xml").writeText(content)
    }
}
