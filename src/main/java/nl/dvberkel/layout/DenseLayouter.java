package nl.dvberkel.layout;

import nl.dvberkel.box.BoundingBox;
import nl.dvberkel.box.Translation;

import static nl.dvberkel.box.BoundingBox.mergeAll;

public class DenseLayouter implements Layouter {
    private static final Aligner HORIZONTAL_ALIGNER = new HorizontalAligner();
    private static final Aligner VERTICAL_ALIGNER = new VerticalAligner();

    @Override
    public Translation[] layout(BoundingBox parent, BoundingBox[] children) {
        Translation[] translations = new Translation[] { new Translation(0, 0), new Translation(0, 0) };
        BoundingBox[] alignedSubBoundingBoxes = HORIZONTAL_ALIGNER.align(children);
        for (int index = 0; index < children.length; index++){
            translations[index] = translations[index].translateBy(children[index].to(alignedSubBoundingBoxes[index]));
        }

        BoundingBox mergedBoundingBox = mergeAll(alignedSubBoundingBoxes);
        BoundingBox[] boxes = new BoundingBox[] { parent, mergedBoundingBox };
        BoundingBox[] alignedBoxes = VERTICAL_ALIGNER.align(parent, mergedBoundingBox);

        Translation verticalTranslation = boxes[1].to(alignedBoxes[1]);
        for (int index = 0; index < translations.length; index++){
            translations[index] = translations[index].translateBy(verticalTranslation);
        }

        return translations;
    }
}
