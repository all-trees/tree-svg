package nl.dvberkel.tree;

import nl.dvberkel.box.BoundingBox;

public class SvgLeaf implements SvgTree {
    private BoundingBox boundingBox;

    @Override
    public BoundingBox boundingBox(Configuration configuration) {
        if (boundingBox == null) {
            this.boundingBox = defaultBoundingBox(configuration);
        }
        return boundingBox;
    }

    private BoundingBox defaultBoundingBox(Configuration configuration) {
        int size = 2 * (configuration.radius + configuration.padding);
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
