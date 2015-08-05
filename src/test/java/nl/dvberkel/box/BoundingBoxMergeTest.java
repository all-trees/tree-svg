package nl.dvberkel.box;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BoundingBoxMergeTest {
    @Test
    public void shouldBeAbleToMergeWithNoOtherBoxes() {
        BoundingBox a = new BoundingBox(0, 0, 1, 1);

        BoundingBox merged = a.merge();

        assertThat(merged, is(a));
    }

    @Test
    public void shouldBeAbleToMergeWithSameBox() {
        BoundingBox a = new BoundingBox(0, 0, 1, 1);

        BoundingBox merged = a.merge(a);

        assertThat(merged, is(a));
    }

    @Test
    public void shouldBeAbleToMergeWithOtherBox() {
        BoundingBox a = new BoundingBox(0, 0, 1, 1);
        BoundingBox b = new BoundingBox(1, 1, 1, 1);

        BoundingBox merged = a.merge(b);

        assertThat(merged, is(new BoundingBox(0, 0, 2, 2)));
    }

    @Test
    public void shouldBeAbleToMergeWithOtherBoxes() {
        BoundingBox a = new BoundingBox(0, 0, 1, 1);
        BoundingBox b = new BoundingBox(1, 1, 1, 1);
        BoundingBox c = new BoundingBox(2, 0, 1, 1);

        BoundingBox merged = a.merge(b, c);

        assertThat(merged, is(new BoundingBox(0, 0, 3, 2)));
    }
}
