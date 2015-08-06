package nl.dvberkel.tree;

import nl.dvberkel.box.BoundingBox;
import nl.dvberkel.layout.Aligner;
import nl.dvberkel.layout.HorizontalAligner;
import nl.dvberkel.layout.VerticalAligner;

public class SvgNode implements SvgTree {
    private final SvgTree left;
    private final SvgTree right;

    public SvgNode(SvgTree left, SvgTree right) {
        if (left == null) { throw new IllegalArgumentException("left should not be null"); }
        if (right == null) { throw new IllegalArgumentException("right should not be null"); }
        this.left = left;
        this.right = right;
    }

    @Override
    public BoundingBox boundingBox(Configuration configuration) {
        Aligner horizontalAligner = new HorizontalAligner();
        BoundingBox[] alignedSubTrees = horizontalAligner.align(left.boundingBox(configuration), right.boundingBox(configuration));
        BoundingBox mergedBoundingBox = alignedSubTrees[0].merge(alignedSubTrees[1]);
        Aligner verticalAligner = new VerticalAligner();
        int size  = 2 * (configuration.radius + configuration.padding);
        BoundingBox[] aligned = verticalAligner.align(new BoundingBox(0, 0, size, size), mergedBoundingBox);
        return aligned[0].merge(aligned[1]);
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
