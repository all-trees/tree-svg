package nl.dvberkel.tree;

public class Configuration {
    public static final int DEFAULT_RADIUS = 1;
    public static final int DEFAULT_PADDING = 0;
    public static final String DEFAULT_STROKE = "black";
    public static final String DEFAULT_FILL = "white";
    public static final int DEFAULT_STROKE_WIDTH = 3;

    public static Configuration configuration(){
        return new Configuration(DEFAULT_RADIUS, DEFAULT_PADDING, DEFAULT_STROKE, DEFAULT_FILL, DEFAULT_STROKE_WIDTH);
    }

    public final int radius;
    public final int padding;
    public final String stroke;
    public final String fill;
    public final int strokeWidth;

    public Configuration(int radius, int padding, String stroke, String fill, int strokeWidth) {
        this.radius = radius;
        this.padding = padding;
        this.stroke = stroke;
        this.fill = fill;
        this.strokeWidth = strokeWidth;
    }

    public Configuration withRadius(int radius) {
        return new Configuration(radius, padding, DEFAULT_STROKE, DEFAULT_FILL, DEFAULT_STROKE_WIDTH);
    }

    public Configuration withPadding(int padding) {
        return new Configuration(radius, padding, DEFAULT_STROKE, DEFAULT_FILL, DEFAULT_STROKE_WIDTH);
    }

    public Configuration withStroke(String stroke) {
        return new Configuration(radius, padding, stroke, fill, strokeWidth);
    }

    public Configuration withFill(String fill) {
        return new Configuration(radius, padding, stroke, fill, strokeWidth);
    }

    public Configuration withStrokeWidth(int strokeWidth) {
        return new Configuration(radius, padding, stroke, fill, strokeWidth);
    }
}
