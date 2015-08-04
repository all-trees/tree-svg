package nl.dvberkel.tree;

import org.junit.Test;

public class SvgTreeConstructionTest {
    @Test(expected = IllegalArgumentException.class)
    public void svgNodeShouldBeCreatedWithNonNullLeftSubSvgTree() {
        new SvgNode(null, new SvgLeaf());
    }

    @Test(expected = IllegalArgumentException.class)
    public void svgNodeShouldBeCreatedWithNonNullRightSubSvgTree() {
        new SvgNode(new SvgLeaf(), null);
    }
}
