package nl.dvberkel.layout;

import nl.dvberkel.box.BoundingBox;

public class HorizontalAligner implements Aligner {
    @Override
    public BoundingBox[] align(BoundingBox... boxes) {
        BoundingBox[] alignedBoxes = new BoundingBox[boxes.length];
        int x = 0;
        for (int index = 0; index < boxes.length; index++) {
            alignedBoxes[index] = new BoundingBox(x,0, boxes[index].width, boxes[index].height);
            x += boxes[index].width;
        }
        return alignedBoxes;
    }
}
