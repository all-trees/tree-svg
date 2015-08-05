package nl.dvberkel.tree;

public class Configuration {
    public static final int DEFAULT_RADIUS = 1;
    public static final int DEFAULT_PADDING = 0;

    public static Configuration configuration(){
        return new Configuration(DEFAULT_RADIUS, DEFAULT_PADDING);
    }

    public final int radius;
    public final int padding;

    public Configuration(int radius, int padding) {
        this.radius = radius;
        this.padding = padding;
    }

    public Configuration withRadius(int radius) {
        return new Configuration(radius, padding);
    }

    public Configuration withPadding(int padding) {
        return new Configuration(radius, padding);
    }
}
