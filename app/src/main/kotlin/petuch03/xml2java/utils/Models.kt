package petuch03.xml2java.utils

data class XMLExtensionElementModel(val type: String, val contents: Map<String, String?>)

data class XMLExtensionElementPrintable(val type: String, val printable: String)

data class JavaClassPrintable(val className: String, val printable: String)

enum class JavaType(private val printable: String){
    STRING("String"),
    INT("Int"),
    @Suppress("unused")
    FLOAT("Float");

    override fun toString(): String {
        return this.printable
    }
}


