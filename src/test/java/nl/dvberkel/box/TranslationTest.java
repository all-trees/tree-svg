package nl.dvberkel.box;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TranslationTest {
    @Test
    public void shouldTranslateByATranslation() {
        Translation start = new Translation(3, 5);

        Translation finish = start.translateBy(new Translation(5, 3));

        assertThat(finish, is(new Translation(8, 8)));
    }
}
