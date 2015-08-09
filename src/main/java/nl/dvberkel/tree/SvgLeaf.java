package nl.dvberkel.tree;

import nl.dvberkel.box.BoundingBox;
import nl.dvberkel.box.Translation;

public class SvgLeaf implements SvgTree {
    private final Configuration configuration;
    private BoundingBox boundingBox;

    public SvgLeaf(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public BoundingBox boundingBox() {
        if (boundingBox == null) {
            this.boundingBox = defaultBoundingBox();
        }
        return boundingBox;
    }

    @Override
    public void translateBy(Translation translation) {
        boundingBox = boundingBox().translateBy(translation);
    }

    protected BoundingBox defaultBoundingBox() {
        int size = 2 * (configuration.nodeRadius + configuration.padding);
        return new BoundingBox(0, 0, size, size);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && this.getClass().equals(obj.getClass());
    }
}
