package nl.dvberkel.tree;

import nl.dvberkel.box.BoundingBox;
import nl.dvberkel.box.Translation;
import org.junit.Before;
import org.junit.Test;

import static nl.dvberkel.tree.Configuration.configuration;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SvgTreeTranslateTest {
    private static final int dx = 13;
    private static final int dy = 21;
    private Configuration configuration;

    @Before
    public void createConfiguration(){
        configuration = configuration();
    }

    @Test
    public void shouldTranslateALeaf() {
        SvgTree tree = new SvgLeaf(configuration);
        BoundingBox originalBoundingBox = tree.boundingBox();

        tree.translateBy(new Translation(dx, dy));

        assertThat(tree.boundingBox(), is(new BoundingBox(
                originalBoundingBox.x + dx,
                originalBoundingBox.y +dy,
                originalBoundingBox.width,
                originalBoundingBox.height)));
    }

    @Test
    public void shouldTranslateANodeAndItsSubtrees() {
        SvgTree left = createMockSvgTreeThatReturns(new BoundingBox(0, 0, 1, 1));
        SvgTree right = createMockSvgTreeThatReturns(new BoundingBox(0, 0, 1, 1));
        SvgTree tree = new SvgNode(configuration, left, right);
        Translation translation = new Translation(dx, dy);
        BoundingBox originalBoundingBox = tree.boundingBox();

        tree.translateBy(translation);

        assertThat(tree.boundingBox(), is(new BoundingBox(
                originalBoundingBox.x + dx,
                originalBoundingBox.y + dy,
                originalBoundingBox.width,
                originalBoundingBox.height)));
        verify(left).translateBy(translation);
        verify(right).translateBy(translation);
    }

    private SvgTree createMockSvgTreeThatReturns(BoundingBox boundingBox) {
        SvgTree left = mock(SvgTree.class);
        when(left.boundingBox()).thenReturn(boundingBox);
        return left;
    }


}
