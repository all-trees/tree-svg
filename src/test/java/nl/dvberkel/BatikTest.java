package nl.dvberkel;

import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.svg.SVGSVGElement;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

import static org.apache.commons.io.IOUtils.copy;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class BatikTest {
    private String expectedOutput;

    @Before
    public void readExpectedOutputFromFile() throws IOException {
        FileInputStream inputStream = new FileInputStream(new File("src/test/resources/batik-test.xml"));
        StringWriter writer = new StringWriter();
        copy(inputStream, writer, "utf-8");

        expectedOutput = writer.toString();
    }

    @Test
    public void shouldBeAbleToCreateAnSvgDomAndWriteItToOutputStream() throws TransformerException, UnsupportedEncodingException {
        SVGSVGElement root = createSvgDocument();

        assertThat(root, is(not(nullValue())));

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        output(root, stream);
        String output = stream.toString("utf-8");

        assertThat(output, is(expectedOutput));
    }

    private SVGSVGElement createSvgDocument() {
        DOMImplementation dom = SVGDOMImplementation.getDOMImplementation();
        String namespace = SVGDOMImplementation.SVG_NAMESPACE_URI;
        SVGDocument document = (SVGDocument) dom.createDocument(namespace, "svg", null);
        SVGSVGElement root = document.getRootElement();

        root.setAttributeNS(null, "width", "400");
        root.setAttributeNS(null, "height", "450");

        Element rectangle = document.createElementNS(namespace, "rect");
        rectangle.setAttributeNS(null, "x", "10");
        rectangle.setAttributeNS(null, "y", "20");
        rectangle.setAttributeNS(null, "width", "100");
        rectangle.setAttributeNS(null, "height", "50");
        rectangle.setAttributeNS(null, "fill", "red");

        root.appendChild(rectangle);
        return root;
    }


    private void output(SVGSVGElement root, OutputStream stream) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        StreamResult result = new StreamResult(stream);
        transformer.transform(new DOMSource(root), result);
    }
}
