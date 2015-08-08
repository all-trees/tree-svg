package nl.dvberkel.svg;

import nl.dvberkel.tree.Configuration;
import nl.dvberkel.tree.SvgLeaf;
import nl.dvberkel.tree.SvgNode;
import nl.dvberkel.tree.SvgTree;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.svg.SVGSVGElement;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

import static nl.dvberkel.tree.Configuration.configuration;
import static org.apache.commons.io.IOUtils.copy;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SvgTreeTransformerTest {
    private Configuration configuration;
    private SvgTreeTransformer transformer;
    private String expectedLeafSvg;
    private String expectedNodeSvg;

    @Before
    public void createConfigurationAndSvgTreeTransformer(){
        configuration = configuration().withRadius(10).withPadding(3);
        transformer = new SvgTreeTransformer(configuration);
    }

    @Before
    public void readExpectedLeafSvgFromFile() throws IOException {
        expectedLeafSvg = contentOf("src/test/resources/svg-tree-transformer-test.leaf.svg");
    }

    private String contentOf(String pathname) throws IOException {
        FileInputStream inputStream = new FileInputStream(new File(pathname));
        StringWriter writer = new StringWriter();
        copy(inputStream, writer, "utf-8");

        return writer.toString();
    }

    @Before
    public void readExpectedNodeSvgFromFile() throws IOException {
        expectedNodeSvg = contentOf("src/test/resources/svg-tree-transformer-test.node.svg");
    }

    @Test
    public void shouldTransformASvgLeaf() throws TransformerException, UnsupportedEncodingException {
        SvgTree tree = new SvgLeaf(configuration);

        SVGSVGElement root = transformer.transform(tree);

        assertThat(stringify(root), is(expectedLeafSvg));
    }

    @Test
    public void shouldTransformASvgNode() throws TransformerException, UnsupportedEncodingException {
        SvgTree tree = new SvgNode(configuration, new SvgLeaf(configuration), new SvgLeaf(configuration));

        SVGSVGElement root = transformer.transform(tree);

        assertThat(stringify(root), is(expectedNodeSvg));
    }

    private String stringify(SVGSVGElement root) throws TransformerException, UnsupportedEncodingException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        output(root, stream);
        return stream.toString("utf-8");
    }

    private void output(SVGSVGElement root, OutputStream stream) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        StreamResult result = new StreamResult(stream);
        transformer.transform(new DOMSource(root), result);
    }
}
