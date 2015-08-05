package nl.dvberkel.layout;

import nl.dvberkel.box.BoundingBox;

public interface Aligner {
    BoundingBox[] align(BoundingBox... boxes);
}
