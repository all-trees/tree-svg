package nl.dvberkel.tree;

public class TreeTransformer {
    public SvgTree transform(Tree tree) {
        if (tree == null) { throw new IllegalArgumentException("tree should not be null"); }
        if (tree instanceof Node) {
            Node node = (Node) tree;
            return new SvgNode(transform(node.left()), transform(node.right()));
        } else {
            return new SvgLeaf();
        }
    }
}
