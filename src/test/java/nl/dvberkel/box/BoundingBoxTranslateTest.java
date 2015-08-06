package nl.dvberkel.box;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static java.lang.Math.abs;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BoundingBoxTranslateTest {
    private BoundingBox original;

    @Before
    public void createOriginalBoundingBox() {
        Random random = new Random();
        original = new BoundingBox(
                random.nextInt(),
                random.nextInt(),
                abs(random.nextInt()),
                abs(random.nextInt())
        );
    }

    @Test
    public void shouldTranslateToAbsolutePosition() {
        int newX = 3, newY = 5;

        BoundingBox moved = original.translateTo(newX, newY);

        assertThat(moved, is(new BoundingBox(newX, newY, original.width, original.height)));
    }


    @Test
    public void shouldTranslateToRelativeToOriginalPosition() {
        int dx = 3, dy = 5;

        BoundingBox moved = original.translateBy(dx, dy);

        assertThat(moved, is(new BoundingBox(original.x + dx, original.y + dy, original.width, original.height)));
    }
}
