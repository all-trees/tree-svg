package nl.dvberkel.tree;

import nl.dvberkel.box.BoundingBox;
import nl.dvberkel.box.Translation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

import static nl.dvberkel.tree.Configuration.configuration;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class SvgNodeBoundingBoxTest {
    private final Configuration configuration;
    private final BoundingBox leftBoundingBox;
    private final BoundingBox rightBoundingBox;
    private final BoundingBox expectedBoundingBox;

    public SvgNodeBoundingBoxTest(Configuration configuration, BoundingBox leftBoundingBox, BoundingBox rightBoundingBox, BoundingBox expectedBoundingBox) {
        this.configuration = configuration;
        this.leftBoundingBox = leftBoundingBox;
        this.rightBoundingBox = rightBoundingBox;
        this.expectedBoundingBox = expectedBoundingBox;
    }

    @Test
    public void shouldReturnBoundingBoxThatFitsNodeAndTheSubtrees() {
        SvgTree tree = new SvgNode(configuration, new SvgLeaf(configuration), new SvgLeaf(configuration));

        BoundingBox box = tree.boundingBox();

        assertThat(box, is(expectedBoundingBox));
    }

    @Test
    public void shouldRealignTheSubtrees() {
        SvgTree left = mock(SvgTree.class);
        when(left.boundingBox()).thenReturn(leftBoundingBox);
        SvgTree right = mock(SvgTree.class);
        when(right.boundingBox()).thenReturn(rightBoundingBox);
        SvgTree tree = new SvgNode(configuration, left, right);

        BoundingBox box = tree.boundingBox();

        verify(left).translateBy(any(Translation.class));
        verify(right).translateBy(any(Translation.class));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        Collection<Object[]> data = new ArrayList<Object[]>();
        data.add(new Object[]{ configuration().withNodeRadius(1).withPadding(0), new BoundingBox(0, 0, 2, 2), new BoundingBox(0, 0, 2, 2), new BoundingBox(0, 0, 4, 4)});
        return data;
    }
}
