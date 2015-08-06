package nl.dvberkel.box;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class BoundingBox {
    private static final BoundingBox EMPTY_BOUNDING_BOX = new BoundingBox(0, 0, 0, 0);

    public static BoundingBox mergeAll(BoundingBox... boxes) {
        if (boxes.length == 0) {
            return EMPTY_BOUNDING_BOX;
        } else {
            return boxes[0].merge(boxes);
        }
    }

    public final int x;
    public final int y;
    public final int width;
    public final int height;

    public BoundingBox(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public BoundingBox merge(BoundingBox... boxes) {
        int minX = this.x;
        int minY = this.y;
        int maxX = this.x + this.width;
        int maxY = this.y + this.height;
        for (BoundingBox box : boxes) {
            minX = min(minX, box.x);
            minY = min(minY, box.y);
            maxX = max(maxX, box.x + box.width);
            maxY = max(maxY, box.y + box.height);
        }
        return new BoundingBox(minX, minY, maxX - minX, maxY - minY);
    }


    public BoundingBox translateTo(int x, int y) {
        return new BoundingBox(x, y, this.width, this.height);
    }

    public BoundingBox translateBy(int dx, int dy) {
        return translateTo(this.x + dx, this.y + dy);
    }


    public BoundingBox translateBy(Translation translation) {
        return translateBy(translation.dx, translation.dy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoundingBox that = (BoundingBox) o;

        if (x != that.x) return false;
        if (y != that.y) return false;
        if (width != that.width) return false;
        return height == that.height;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + width;
        result = 31 * result + height;
        return result;
    }
}
