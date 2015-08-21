package nl.dvberkel.tree;

import nl.dvberkel.layout.DenseLayouter;
import nl.dvberkel.layout.Layouter;

public class Configuration {
    public static final int DEFAULT_NODE_RADIUS = 10;
    public static final int DEFAULT_LEAF_RADIUS = 3;
    public static final int DEFAULT_PADDING = 0;
    public static final String DEFAULT_STROKE = "black";
    public static final String DEFAULT_FILL = "white";
    public static final int DEFAULT_STROKE_WIDTH = 3;
    public static final Layouter DEFAULT_LAYOUTER = new DenseLayouter();

    public static Configuration configuration(){
        return new Configuration(DEFAULT_LAYOUTER, DEFAULT_NODE_RADIUS, DEFAULT_LEAF_RADIUS, DEFAULT_PADDING, DEFAULT_STROKE, DEFAULT_FILL, DEFAULT_STROKE_WIDTH);
    }

    public final Layouter layouter;
    public final int nodeRadius;
    public final int leafRadius;
    public final int padding;
    public final String stroke;
    public final String fill;
    public final int strokeWidth;

    public Configuration(Layouter layouter, int nodeRadius, int leafRadius, int padding, String stroke, String fill, int strokeWidth) {
        this.layouter = layouter;
        this.nodeRadius = nodeRadius;
        this.leafRadius = leafRadius;
        this.padding = padding;
        this.stroke = stroke;
        this.fill = fill;
        this.strokeWidth = strokeWidth;
    }

    public Configuration withLayouter(Layouter layouter) {
        return new Configuration(layouter, nodeRadius, leafRadius, padding, DEFAULT_STROKE, DEFAULT_FILL, DEFAULT_STROKE_WIDTH);
    }

    public Configuration withNodeRadius(int nodeRadius) {
        return new Configuration(layouter, nodeRadius, leafRadius, padding, DEFAULT_STROKE, DEFAULT_FILL, DEFAULT_STROKE_WIDTH);
    }

    public Configuration withPadding(int padding) {
        return new Configuration(layouter, nodeRadius, leafRadius, padding, DEFAULT_STROKE, DEFAULT_FILL, DEFAULT_STROKE_WIDTH);
    }

    public Configuration withStroke(String stroke) {
        return new Configuration(layouter, nodeRadius, leafRadius, padding, stroke, fill, strokeWidth);
    }

    public Configuration withFill(String fill) {
        return new Configuration(layouter, nodeRadius, leafRadius, padding, stroke, fill, strokeWidth);
    }

    public Configuration withStrokeWidth(int strokeWidth) {
        return new Configuration(layouter, nodeRadius, leafRadius, padding, stroke, fill, strokeWidth);
    }

    public Configuration withLeafRadius(int leafRadius) {
        return new Configuration(layouter, nodeRadius, leafRadius, padding, stroke, fill, strokeWidth);
    }
}
