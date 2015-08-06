package nl.dvberkel.tree;

import nl.dvberkel.box.BoundingBox;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

import static nl.dvberkel.tree.Configuration.configuration;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        SvgTree left = mock(SvgTree.class);
        when(left.boundingBox(configuration)).thenReturn(leftBoundingBox);
        SvgTree right = mock(SvgTree.class);
        when(right.boundingBox(configuration)).thenReturn(rightBoundingBox);
        SvgTree tree = new SvgNode(left, right);

        BoundingBox box = tree.boundingBox(configuration);

        assertThat(box, is(expectedBoundingBox));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        Collection<Object[]> data = new ArrayList<Object[]>();
        data.add(new Object[]{ configuration().withRadius(1).withPadding(0), new BoundingBox(0, 0, 1, 1), new BoundingBox(0, 0, 1, 1), new BoundingBox(0, 0, 2, 3)});
        return data;
    }
}
