package nl.dvberkel.tree;

import nl.dvberkel.box.BoundingBox;
import nl.dvberkel.box.Translation;
import nl.dvberkel.layout.Aligner;
import nl.dvberkel.layout.HorizontalAligner;
import nl.dvberkel.layout.VerticalAligner;

import static nl.dvberkel.box.BoundingBox.mergeAll;

public class SvgNode extends SvgLeaf implements SvgTree {
    private static final Aligner HORIZONTAL_ALIGNER = new HorizontalAligner();
    private static final Aligner VERTICAL_ALIGNER = new VerticalAligner();
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
        BoundingBox leafBoundingBox = super.defaultBoundingBox();
        BoundingBox[] subBoundingBoxes = new BoundingBox[]{ left().boundingBox(), right().boundingBox() };
        BoundingBox[] alignedSubBoundingBoxes = HORIZONTAL_ALIGNER.align(subBoundingBoxes);

        Translation[] translations = new Translation[subBoundingBoxes.length];
        for (int index = 0; index < subBoundingBoxes.length; index++){
            translations[index] = subBoundingBoxes[index].to(alignedSubBoundingBoxes[index]);
        }
        left.translateBy(translations[0]);
        right.translateBy(translations[1]);

        BoundingBox mergedBoundingBox = mergeAll(alignedSubBoundingBoxes);
        BoundingBox[] boxes = new BoundingBox[] { leafBoundingBox, mergedBoundingBox };
        BoundingBox[] alignedBoxes = VERTICAL_ALIGNER.align(leafBoundingBox, mergedBoundingBox);

        translations = new Translation[alignedBoxes.length];
        for (int index = 0; index < alignedBoxes.length; index++){
            translations[index] = boxes[index].to(alignedBoxes[index]);
        }
        left.translateBy(translations[1]);
        right.translateBy(translations[1]);

        return mergeAll(alignedBoxes);
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
