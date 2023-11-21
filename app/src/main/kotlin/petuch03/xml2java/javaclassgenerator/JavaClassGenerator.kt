package petuch03.xml2java.javaclassgenerator

import petuch03.xml2java.utils.JavaClassPrintable
import petuch03.xml2java.utils.XMLExtensionElementModel

interface JavaClassGenerator {
    fun identifyType(value: String?): String
    fun generateJavaClass(extensionElement: XMLExtensionElementModel): JavaClassPrintable
}