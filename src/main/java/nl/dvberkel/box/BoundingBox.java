package nl.dvberkel.box;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class BoundingBox {
    private final int x;
    private final int y;
    private final int width;
    private final int height;

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
