package nl.dvberkel.tree;

import org.junit.Test;

import static nl.dvberkel.tree.Configuration.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ConfigurationTest {
    @Test
    public void shouldHaveDefaults(){
        Configuration configuration = configuration();

        assertThat(configuration.radius, is(DEFAULT_RADIUS));
        assertThat(configuration.padding, is(DEFAULT_PADDING));
    }

    @Test
    public void shouldBeAbleToSetRadius(){
        int expectedRadius = 5;
        Configuration configuration = configuration().withRadius(expectedRadius);

        assertThat(configuration.radius, is(expectedRadius));
        assertThat(configuration.padding, is(DEFAULT_PADDING));
    }

    @Test
    public void shouldBeAbleToSetPadding(){
        int expectedPadding = 8;
        Configuration configuration = configuration().withPadding(expectedPadding);

        assertThat(configuration.radius, is(DEFAULT_RADIUS));
        assertThat(configuration.padding, is(expectedPadding));
    }

    @Test
    public void shouldBeAbleToSetBothRadiusAndPadding(){
        int expectedRadius = 5;
        int expectedPadding = 8;
        Configuration configuration = configuration().withRadius(expectedRadius).withPadding(expectedPadding);

        assertThat(configuration.radius, is(expectedRadius));
        assertThat(configuration.padding, is(expectedPadding));
    }
}
