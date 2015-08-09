package nl.dvberkel.tree;

import static java.lang.Math.abs;
import static nl.dvberkel.tree.Configuration.configuration;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import nl.dvberkel.box.BoundingBox;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class SvgLeafBoundingBoxTest {
    private Configuration configuration;
    private BoundingBox expectedBoundingBox;

    @Before
    public void createConfiguration() {
        Random random = new Random();
        configuration = configuration().withNodeRadius(abs(random.nextInt())).withPadding(abs(random.nextInt()));
        int expectedSize = 2 * (configuration.nodeRadius + configuration.padding);
        expectedBoundingBox = new BoundingBox(0, 0, expectedSize, expectedSize);
    }

    @Test
    public void shouldReturnBoundingBoxTightAroundACircleWithCertainRadiusAndWithPadding() {
        SvgTree tree = new SvgLeaf(configuration);

        BoundingBox box = tree.boundingBox();

        assertThat(box, is(expectedBoundingBox));
    }


}
