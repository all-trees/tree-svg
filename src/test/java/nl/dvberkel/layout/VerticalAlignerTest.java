package nl.dvberkel.layout;

import nl.dvberkel.box.BoundingBox;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class VerticalAlignerTest {
    private final BoundingBox[] boxes;
    private final BoundingBox[] expectedBoxes;
    private Aligner aligner;

    public VerticalAlignerTest(BoundingBox[] boxes, BoundingBox[] expectedBoxes) {
        this.boxes = boxes;
        this.expectedBoxes = expectedBoxes;
    }

    @Before
    public void createVerticalAligner() {
        aligner = new VerticalAligner();
    }

    @Test
    public void shouldStackBoxesHorizontally(){
        BoundingBox[] alignedBoxes = aligner.align(boxes);

        assertThat(alignedBoxes, is(expectedBoxes));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> data = new ArrayList<Object[]>();
        data.add(new Object[]{new BoundingBox[]{}, new BoundingBox[]{}});
        data.add(new Object[]{new BoundingBox[]{ new BoundingBox(0, 0, 1, 1) }, new BoundingBox[]{ new BoundingBox(0, 0, 1, 1) }});
        data.add(new Object[]{
                new BoundingBox[]{ new BoundingBox(0, 0, 1, 1), new BoundingBox(0, 0, 1, 1) },
                new BoundingBox[]{ new BoundingBox(0, 0, 1, 1), new BoundingBox(0, 1, 1, 1) }});
        data.add(new Object[]{
                new BoundingBox[]{ new BoundingBox(0, 0, 1, 1), new BoundingBox(0, 0, 2, 2), new BoundingBox(0, 0, 1, 1) },
                new BoundingBox[]{ new BoundingBox(0, 0, 1, 1), new BoundingBox(0, 1, 2, 2), new BoundingBox(0, 3, 1, 1) }});
        return data;
    }
}
