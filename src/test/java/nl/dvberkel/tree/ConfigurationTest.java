package nl.dvberkel.tree;

import org.junit.Test;

import static nl.dvberkel.tree.Configuration.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ConfigurationTest {
    @Test
    public void shouldHaveDefaults(){
        Configuration configuration = configuration();

        assertThat(configuration.nodeRadius, is(DEFAULT_NODE_RADIUS));
        assertThat(configuration.leafRadius, is(DEFAULT_LEAF_RADIUS));
        assertThat(configuration.padding, is(DEFAULT_PADDING));
        assertThat(configuration.stroke, is(DEFAULT_STROKE));
        assertThat(configuration.fill, is(DEFAULT_FILL));
        assertThat(configuration.strokeWidth, is(DEFAULT_STROKE_WIDTH));
    }

    @Test
    public void shouldBeAbleToSetNodeRadius(){
        int expectedRadius = 5;
        Configuration configuration = configuration().withNodeRadius(expectedRadius);

        assertThat(configuration.nodeRadius, is(expectedRadius));
        assertThat(configuration.leafRadius, is(DEFAULT_LEAF_RADIUS));
        assertThat(configuration.padding, is(DEFAULT_PADDING));
        assertThat(configuration.stroke, is(DEFAULT_STROKE));
        assertThat(configuration.fill, is(DEFAULT_FILL));
        assertThat(configuration.strokeWidth, is(DEFAULT_STROKE_WIDTH));
    }

    @Test
    public void shouldBeAbleToSetLeafRadius(){
        int expectedRadius = 5;
        Configuration configuration = configuration().withLeafRadius(expectedRadius);

        assertThat(configuration.nodeRadius, is(DEFAULT_NODE_RADIUS));
        assertThat(configuration.leafRadius, is(expectedRadius));
        assertThat(configuration.padding, is(DEFAULT_PADDING));
        assertThat(configuration.stroke, is(DEFAULT_STROKE));
        assertThat(configuration.fill, is(DEFAULT_FILL));
        assertThat(configuration.strokeWidth, is(DEFAULT_STROKE_WIDTH));
    }

    @Test
    public void shouldBeAbleToSetPadding(){
        int expectedPadding = 8;
        Configuration configuration = configuration().withPadding(expectedPadding);

        assertThat(configuration.nodeRadius, is(DEFAULT_NODE_RADIUS));
        assertThat(configuration.leafRadius, is(DEFAULT_LEAF_RADIUS));
        assertThat(configuration.padding, is(expectedPadding));
        assertThat(configuration.stroke, is(DEFAULT_STROKE));
        assertThat(configuration.fill, is(DEFAULT_FILL));
        assertThat(configuration.strokeWidth, is(DEFAULT_STROKE_WIDTH));
    }

    @Test
    public void shouldBeAbleToSetStroke(){
        String expectedStroke = "green";
        Configuration configuration = configuration().withStroke(expectedStroke);

        assertThat(configuration.nodeRadius, is(DEFAULT_NODE_RADIUS));
        assertThat(configuration.leafRadius, is(DEFAULT_LEAF_RADIUS));
        assertThat(configuration.padding, is(DEFAULT_PADDING));
        assertThat(configuration.stroke, is(expectedStroke));
        assertThat(configuration.fill, is(DEFAULT_FILL));
        assertThat(configuration.strokeWidth, is(DEFAULT_STROKE_WIDTH));
    }

    @Test
    public void shouldBeAbleToSetFill(){
        String expectedFill = "red";
        Configuration configuration = configuration().withFill(expectedFill);

        assertThat(configuration.nodeRadius, is(DEFAULT_NODE_RADIUS));
        assertThat(configuration.leafRadius, is(DEFAULT_LEAF_RADIUS));
        assertThat(configuration.padding, is(DEFAULT_PADDING));
        assertThat(configuration.stroke, is(DEFAULT_STROKE));
        assertThat(configuration.fill, is(expectedFill));
        assertThat(configuration.strokeWidth, is(DEFAULT_STROKE_WIDTH));
    }

    @Test
    public void shouldBeAbleToSetStrokeWidth(){
        int expectedStrokeWidth = 5;
        Configuration configuration = configuration().withStrokeWidth(expectedStrokeWidth);

        assertThat(configuration.nodeRadius, is(DEFAULT_NODE_RADIUS));
        assertThat(configuration.leafRadius, is(DEFAULT_LEAF_RADIUS));
        assertThat(configuration.padding, is(DEFAULT_PADDING));
        assertThat(configuration.stroke, is(DEFAULT_STROKE));
        assertThat(configuration.fill, is(DEFAULT_FILL));
        assertThat(configuration.strokeWidth, is(expectedStrokeWidth));
    }

    @Test
    public void shouldBeAbleToSetBothRadiusAndPadding(){
        int expectedRadius = 5;
        int expectedPadding = 8;
        Configuration configuration = configuration().withNodeRadius(expectedRadius).withPadding(expectedPadding);

        assertThat(configuration.nodeRadius, is(expectedRadius));
        assertThat(configuration.leafRadius, is(DEFAULT_LEAF_RADIUS));
        assertThat(configuration.padding, is(expectedPadding));
        assertThat(configuration.stroke, is(DEFAULT_STROKE));
        assertThat(configuration.fill, is(DEFAULT_FILL));
        assertThat(configuration.strokeWidth, is(DEFAULT_STROKE_WIDTH));
    }
}
