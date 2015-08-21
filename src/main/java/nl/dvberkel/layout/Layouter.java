package nl.dvberkel.layout;

import nl.dvberkel.box.BoundingBox;
import nl.dvberkel.box.Translation;

public interface Layouter {
    Translation[] layout(BoundingBox parent, BoundingBox[] children);
}
