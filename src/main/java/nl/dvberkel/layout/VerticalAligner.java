package nl.dvberkel.layout;

import nl.dvberkel.box.BoundingBox;

public class VerticalAligner implements Aligner {
    @Override
    public BoundingBox[] align(BoundingBox... boxes) {
        BoundingBox[] alignedBoxes = new BoundingBox[boxes.length];
        int y = 0;
        for (int index = 0; index < boxes.length; index++) {
            alignedBoxes[index] = new BoundingBox(0, y, boxes[index].width, boxes[index].height);
            y += boxes[index].height;
        }
        return alignedBoxes;
    }
}
