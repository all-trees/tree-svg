package nl.dvberkel.svg;

import nl.dvberkel.box.BoundingBox;
import nl.dvberkel.tree.Configuration;
import nl.dvberkel.tree.SvgLeaf;
import nl.dvberkel.tree.SvgNode;
import nl.dvberkel.tree.SvgTree;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.svg.SVGSVGElement;

public class SvgTreeTransformer {
    private final Configuration configuration;
    private final DOMImplementation dom;
    private final String namespace;

    public SvgTreeTransformer(Configuration configuration){
        this.configuration = configuration;
        dom = SVGDOMImplementation.getDOMImplementation();
        namespace = SVGDOMImplementation.SVG_NAMESPACE_URI;
    }

    public SVGSVGElement transform(SvgTree tree) {
        SVGDocument document = (SVGDocument) dom.createDocument(namespace, "svg", null);
        SVGSVGElement root = document.getRootElement();
        root.setAttributeNS(null, "width", Integer.toString(tree.boundingBox().width));
        root.setAttributeNS(null, "height", Integer.toString(tree.boundingBox().height));
        Element group = createGroup(document);
        root.appendChild(group);
        append(document, group, tree);
        return root;
    }

    private Element createGroup(SVGDocument document) {
        Element element = document.createElement("g");
        element.setAttributeNS(null, "stroke", configuration.stroke);
        element.setAttributeNS(null, "fill", configuration.fill);
        element.setAttributeNS(null, "stroke-width", Integer.toString(configuration.strokeWidth));
        return element;
    }

    private void append(SVGDocument document, Element root, SvgTree tree) {
        if (tree instanceof SvgNode) {
            SvgNode node = (SvgNode) tree;
            Element leftEdge = createEdgeSvg(document, node, node.left());
            root.appendChild(leftEdge);
            Element rightEdge = createEdgeSvg(document, node, node.right());
            root.appendChild(rightEdge);
            append(document, root, node.left());
            append(document, root, node.right());
            Element nodeSvg = createLeafSvg(document, node);
            root.appendChild(nodeSvg);
        } else {
            SvgLeaf leaf = (SvgLeaf) tree;
            Element leafSvg = createLeafSvg(document, leaf);
            root.appendChild(leafSvg);
        }
    }

    private Element createEdgeSvg(SVGDocument document, SvgTree parent, SvgTree child) {
        Position parentPosition = leafPosition(parent.boundingBox());
        Position childPosition = leafPosition(child.boundingBox());
        Element element = document.createElementNS(namespace, "line");
        element.setAttributeNS(null, "x1", Integer.toString(parentPosition.x));
        element.setAttributeNS(null, "y1", Integer.toString(parentPosition.y));
        element.setAttributeNS(null, "x2", Integer.toString(childPosition.x));
        element.setAttributeNS(null, "y2", Integer.toString(childPosition.y));
        return element;
    }


    private Element createLeafSvg(SVGDocument document, SvgTree tree) {
        Position position = leafPosition(tree.boundingBox());
        Element element = document.createElementNS(namespace, "circle");
        element.setAttributeNS(null, "cx", Integer.toString(position.x));
        element.setAttributeNS(null, "cy", Integer.toString(position.y));
        element.setAttributeNS(null, "r", Integer.toString(configuration.radius));
        return element;
    }

    private Position leafPosition(BoundingBox box) {
        return new Position(box.x + box.width/2, box.y + configuration.radius + configuration.padding);
    }
}

class Position {
    public final int x;
    public final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}