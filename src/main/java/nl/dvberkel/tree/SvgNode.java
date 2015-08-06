package nl.dvberkel.tree;

import nl.dvberkel.box.BoundingBox;
import nl.dvberkel.layout.Aligner;
import nl.dvberkel.layout.HorizontalAligner;
import nl.dvberkel.layout.VerticalAligner;

import static nl.dvberkel.box.BoundingBox.mergeAll;

public class SvgNode extends SvgLeaf implements SvgTree {
    private static final Aligner HORIZONTAL_ALIGNER = new HorizontalAligner();
    private static final Aligner VERTICAL_ALIGNER = new VerticalAligner();
    private final SvgTree left;
    private final SvgTree right;

    public SvgNode(SvgTree left, SvgTree right) {
        if (left == null) { throw new IllegalArgumentException("left should not be null"); }
        if (right == null) { throw new IllegalArgumentException("right should not be null"); }
        this.left = left;
        this.right = right;
    }

    @Override
    protected BoundingBox defaultBoundingBox(Configuration configuration) {
        BoundingBox leafBoundingBox = super.defaultBoundingBox(configuration);
        BoundingBox[] alignedSubTrees = HORIZONTAL_ALIGNER.align(left.boundingBox(configuration), right.boundingBox(configuration));
        BoundingBox mergedBoundingBox = mergeAll(alignedSubTrees);
        BoundingBox[] aligned = VERTICAL_ALIGNER.align(leafBoundingBox, mergedBoundingBox);
        return mergeAll(aligned);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SvgNode svgNode = (SvgNode) o;

        if (!left.equals(svgNode.left)) return false;
        return right.equals(svgNode.right);

    }

    @Override
    public int hashCode() {
        int result = left.hashCode();
        result = 31 * result + right.hashCode();
        return result;
    }
}
