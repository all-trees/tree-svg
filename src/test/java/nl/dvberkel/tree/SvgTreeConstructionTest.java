package nl.dvberkel.tree;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class SvgTreeConstructionTest {
    private Configuration configuration;

    @Before
    public void createConfiguration() {
        configuration = mock(Configuration.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void svgNodeShouldBeCreatedWithNonNullLeftSubSvgTree() {
        new SvgNode(configuration, null, new SvgLeaf(configuration));
    }

    @Test(expected = IllegalArgumentException.class)
    public void svgNodeShouldBeCreatedWithNonNullRightSubSvgTree() {
        new SvgNode(configuration, new SvgLeaf(configuration), null);
    }
}
