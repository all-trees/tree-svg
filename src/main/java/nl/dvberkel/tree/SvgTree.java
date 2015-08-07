package nl.dvberkel.tree;

import nl.dvberkel.box.BoundingBox;
import nl.dvberkel.box.Translation;

public interface SvgTree {
    BoundingBox boundingBox();

    void translateBy(Translation translation);
}
