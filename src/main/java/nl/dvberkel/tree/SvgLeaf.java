package nl.dvberkel.tree;

import nl.dvberkel.box.BoundingBox;

public class SvgLeaf implements SvgTree {
    private BoundingBox boundingBox;

    @Override
    public BoundingBox boundingBox(Configuration configuration) {
        if (boundingBox == null) {
            int size = 2 * (configuration.radius + configuration.padding);
            boundingBox = new BoundingBox(0, 0, size, size);
        }
        return boundingBox;
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
