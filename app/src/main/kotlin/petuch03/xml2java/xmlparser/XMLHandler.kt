package petuch03.xml2java.xmlparser

import petuch03.xml2java.utils.XMLExtensionElementModel
import petuch03.xml2java.utils.XMLExtensionElementPrintable

interface XMLHandler {
    fun parseSingleExtension(xmlFilePath: String): XMLExtensionElementModel

    fun restoreSingleExtension(extensionElement: XMLExtensionElementModel): XMLExtensionElementPrintable
}