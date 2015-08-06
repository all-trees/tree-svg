package nl.dvberkel.box;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BoundingBoxTranslationTest {
    @Test
    public void shouldDetermineTranslationToPlaceOriginOfBoundingBoxOnTopOfOtherBoundingBoxOrigin() {
        BoundingBox a = new BoundingBox(0, 0, 1, 2);
        BoundingBox b = new BoundingBox(5, 8, 1, 2);

        Translation difference = a.to(b);

        assertThat(difference, is(new Translation(b.x - a.x, b.y - a.y)));
    }
}
