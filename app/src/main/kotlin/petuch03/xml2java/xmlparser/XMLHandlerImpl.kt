package petuch03.xml2java.xmlparser

import org.w3c.dom.Document
import org.w3c.dom.Element
import javax.xml.parsers.DocumentBuilderFactory
import petuch03.xml2java.utils.XMLExtensionElementModel
import petuch03.xml2java.utils.XMLExtensionElementPrintable
import java.io.StringWriter
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

class XMLHandlerImpl : XMLHandler {
    override fun parseSingleExtension(xmlFilePath: String): XMLExtensionElementModel {
        val dbFactory = DocumentBuilderFactory.newInstance()
        val dBuilder = dbFactory.newDocumentBuilder()
        val doc = dBuilder.parse(xmlFilePath)
        doc.documentElement.normalize()

        val root = doc.documentElement
        val extensionType = root.getAttribute("type")
        val extensionContents: MutableMap<String, String> = mutableMapOf()

        for (i in 1..root.childNodes.length step 2) {
            val node = root.childNodes.item(i)
            if (node is Element) {
                val tagName = node.tagName
                val value = node.firstChild.textContent
                extensionContents[tagName] = value
            }
        }
        return (XMLExtensionElementModel(extensionType, extensionContents))

    }

    override fun restoreSingleExtension(extensionElement: XMLExtensionElementModel): XMLExtensionElementPrintable {
        val document = createNewDocument()

        val extension = document.createElement("extension")
        extension.setAttribute("type", extensionElement.type)

        extensionElement.contents.forEach { (tagName, textContent) ->
            val childElement = document.createElement(tagName)
            childElement.appendChild(document.createTextNode(textContent))
            extension.appendChild(childElement)
        }

        document.appendChild(extension)

        return XMLExtensionElementPrintable(extensionElement.type, transformDocumentToString(document))
    }

    private fun createNewDocument(): Document {
        val dbFactory = DocumentBuilderFactory.newInstance()
        return dbFactory.newDocumentBuilder().newDocument()
    }

    private fun transformDocumentToString(document: Document): String {
        val transformer = TransformerFactory.newInstance().newTransformer().apply {
            setOutputProperty(OutputKeys.INDENT, "yes")
            setOutputProperty(OutputKeys.METHOD, "xml")
            setOutputProperty(OutputKeys.ENCODING, "UTF-8")
            setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes")
            setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4")
        }

        val writer = StringWriter()
        transformer.transform(DOMSource(document), StreamResult(writer))
        return writer.buffer.toString()
    }
}