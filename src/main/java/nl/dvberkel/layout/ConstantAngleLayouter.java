package nl.dvberkel.layout;

import nl.dvberkel.box.BoundingBox;
import nl.dvberkel.box.Translation;

import static nl.dvberkel.box.BoundingBox.mergeAll;

public class ConstantAngleLayouter implements Layouter {
    private static final Aligner HORIZONTAL_ALIGNER = new HorizontalAligner();
    private final double factor;

    public ConstantAngleLayouter(double angle) {
        this.factor = Math.sin(angle);
    }

    @Override
    public Translation[] layout(BoundingBox parent, BoundingBox[] children) {
        Translation[] translations = new Translation[] { new Translation(0, 0), new Translation(0, 0) };
        BoundingBox[] alignedChildren = HORIZONTAL_ALIGNER.align(children);
        for (int index = 0; index < children.length; index++){
            translations[index] = translations[index].translateBy(children[index].to(alignedChildren[index]));
        }

        BoundingBox merged = mergeAll(alignedChildren);
        int height = (int) (this.factor * merged.width/4);

        Translation verticalTranslation = new Translation(0, height);
        for (int index = 0; index < translations.length; index++){
            translations[index] = translations[index].translateBy(verticalTranslation);
        }

        return translations;
    }
}
