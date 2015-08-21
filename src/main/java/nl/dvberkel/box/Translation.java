package nl.dvberkel.box;

public class Translation {
    public final int dx;
    public final int dy;

    public Translation(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Translation translateBy(Translation that) {
        return new Translation(this.dx + that.dx, this.dy + that.dy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Translation that = (Translation) o;

        if (dx != that.dx) return false;
        return dy == that.dy;

    }

    @Override
    public int hashCode() {
        int result = dx;
        result = 31 * result + dy;
        return result;
    }
}
