package petuch03.xml2java.javaclassgenerator

import petuch03.xml2java.utils.JavaClassPrintable
import petuch03.xml2java.utils.JavaType
import petuch03.xml2java.utils.XMLExtensionElementModel

interface JavaClassGenerator {
    fun identifyType(value: String?): JavaType
    fun generateJavaClass(extensionElement: XMLExtensionElementModel): JavaClassPrintable
}