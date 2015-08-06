package nl.dvberkel.tree;

public class TreeTransformer {
    private final Configuration configuration;

    public TreeTransformer(Configuration configuration) {
        this.configuration = configuration;
    }

    public SvgTree transform(Tree tree) {
        if (tree == null) { throw new IllegalArgumentException("tree should not be null"); }
        if (tree instanceof Node) {
            Node node = (Node) tree;
            return new SvgNode(configuration, transform(node.left()), transform(node.right()));
        } else {
            return new SvgLeaf(configuration);
        }
    }
}
