package ru.ncedu.java.tasks;

import java.io.InputStream;
import javax.xml.transform.Transformer;
import org.xml.sax.SAXException;

public interface SimpleXML {
	/*
	 * С помощью DOM API в Java-коде создать XML документ вида "<tagName>textNode</tagName>".
	 * В частности, для вызова createXML("root","<R&D>") должно вернуться <root>&lt;R&amp;D&gt;</root>.
	 * Требования:
	 * - Результат должен быть корректным (well-formed) XML документом.
	 * - Никаких переводов строк или других дополнительных символов не должно быть добавлено в textNode.
	 * Правильный подход к решению:
	 * - Использовать именно DOM, а не писать логику обработки спецсимволов вручную.
	 * - С целью удаления в документе декларации "<?xml...?>" следует использовать метод
	 *     {@link Transformer#setOutputProperty(String, String)} для свойства OMIT_XML_DECLARATION.
	 */
    //НИЖЕ - ТОТ ЖЕ САМЫЙ КОММЕНТАРИЙ, НО КОРРЕКТНО ОТОБРАЖАЕМЫЙ В JAVADOC
    /**
     * С помощью DOM API в Java-коде создать XML документ вида "&lt;tagName&gt;textNode&lt;/tagName&gt;".<br>
     * В частности, для вызова createXML("root","&lt;R&amp;D&gt;") должно вернуться &lt;root&gt;&amp;lt;R&amp;amp;D&amp;gt;&lt;/root&gt;.<br>
     * Требования:<br>
     * - Результат должен быть корректным (well-formed) XML документом.<br>
     * - Никаких переводов строк или других дополнительных символов не должно быть добавлено в textNode.<br>
     * Правильный подход к решению:<br>
     * - Использовать именно DOM, а не писать логику обработки спецсимволов вручную.<br>
     * - С целью удаления в документе декларации "&lt;?xml...?&gt;" следует использовать метод
     *     {@link Transformer#setOutputProperty(String, String)} для свойства OMIT_XML_DECLARATION.
     * @param tagName Имя тега элемента
     * @param textNode Текстовое содержимое тега.
     * @return Корректный XML документ без декларации "&lt;?xml...?&gt;"
     */
    public String createXML(String tagName, String textNode);
    /**
     * Check xml-document if it is well-formed
     * And return the name of root
     * if document is not well-formed throw {@link SAXException}.<br>
     * SAX usage is obligational
     * @param xmlStream Stream with XML document
     * @return root name
     */
    public String parseRootElement(InputStream xmlStream) throws SAXException;
}

