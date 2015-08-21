package nl.dvberkel.tree;

import nl.dvberkel.box.BoundingBox;
import nl.dvberkel.box.Translation;
import nl.dvberkel.layout.*;

import static nl.dvberkel.box.BoundingBox.mergeAll;

public class SvgNode extends SvgLeaf implements SvgTree {
    private final SvgTree left;
    private final SvgTree right;

    public SvgNode(Configuration configuration, SvgTree left, SvgTree right) {
        super(configuration);
        if (left == null) { throw new IllegalArgumentException("left should not be null"); }
        if (right == null) { throw new IllegalArgumentException("right should not be null"); }
        this.left = left;
        this.right = right;
    }

    @Override
    protected BoundingBox defaultBoundingBox() {
        BoundingBox parent = super.defaultBoundingBox();

        Translation[] translations = configuration.layouter.layout(
                parent, new BoundingBox[]{ left().boundingBox(), right().boundingBox() });

        left.translateBy(translations[0]);
        right.translateBy(translations[1]);

        return mergeAll(parent, left.boundingBox(), right.boundingBox());
    }

    @Override
    public void translateBy(Translation translation) {
        super.translateBy(translation);
        left().translateBy(translation);
        right().translateBy(translation);
    }

    public SvgTree left() {
        return left;
    }

    public SvgTree right() {
        return right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SvgNode svgNode = (SvgNode) o;

        if (!left.equals(svgNode.left())) return false;
        return right.equals(svgNode.right());

    }

    @Override
    public int hashCode() {
        int result = left().hashCode();
        result = 31 * result + right().hashCode();
        return result;
    }
}
