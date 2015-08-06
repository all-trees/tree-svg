package nl.dvberkel.tree;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(Parameterized.class)
public class TreeTransformerTest {
    private static Configuration configuration;
    private final Tree tree;
    private final SvgTree expectedSvgTree;
    private TreeTransformer transformer;

    public TreeTransformerTest(Tree tree, SvgTree expectedSvgTree) {
        this.tree = tree;
        this.expectedSvgTree = expectedSvgTree;
    }

    @BeforeClass
    public static void createConfiguration() {
        configuration = mock(Configuration.class);
    }


    @Before
    public void createTreeTransformer() {
        transformer = new TreeTransformer(configuration);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldBeCreatedWithANonNullTree() {
        transformer.transform(null);
    }

    @Test
    public void shouldTransformATreeIntoASvgTree() {
        SvgTree svgTree = transformer.transform(tree);

        assertThat(svgTree, is(expectedSvgTree));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> data = new ArrayList<Object[]>();
        data.add(new Object[]{new Leaf(), new SvgLeaf(configuration)});
        data.add(new Object[]{new Node(new Leaf(), new Leaf()), new SvgNode(configuration,new SvgLeaf(configuration), new SvgLeaf(configuration))});
        data.add(new Object[]{new Node(new Node(new Leaf(), new Leaf()), new Leaf()), new SvgNode(configuration, new SvgNode(configuration, new SvgLeaf(configuration), new SvgLeaf(configuration)), new SvgLeaf(configuration))});
        return data;
    }
}
