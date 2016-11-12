# tree-svg
Code to generate SVG from trees

## Usage

Before we can create a [SVG][svg] of a binary tree, we first need a tree. We can create one via `Builder`

```java
Builder builder = new Builder();
Tree tree =  builder.build("112211211222");
```

The `Builder#build` method accepts a [Dyck word][dyck] and it will build the corresponding `Tree`.

We can transform the `Tree` into an `SvgTree` with a `TreeTransformer`. The `SvgTree` determines the bounding boxes of
all the subtrees. The `TreeTransformer` is configured with a `Configuration` that determines radius of the vertices and
the padding around each subtree.

```java
Configuration configuration = configuration().withNodeRadius(20).withLeafRadius(3).withPadding(5);
TreeTransformer treeTransformer = new TreeTransformer(configuration);
SvgTree svgTree = treeTransformer.transform(tree);
```

The `SvgTree` can be transformed into a `SVGSVGElement`, that is the root of a SVG document, with a `SvgTreeTransformer`.

```java
SvgTreeTransformer svgTreeTransformer = new SvgTreeTransformer(configuration);
SVGSVGElement root = svgTreeTransformer.transform(svgTree);
```

To stream the SVG document to a file we will use a `FileOutputStream` and a `Transformer`.
 
```java
FileOutputStream stream = new FileOutputStream(new File("src/main/resources/example.svg"));

TransformerFactory factory = TransformerFactory.newInstance();
Transformer transformer = factory.newTransformer();
StreamResult result = new StreamResult(stream);
transformer.transform(new DOMSource(root), result);
```

Put together it will produce the following image

![Binary Tree](https://raw.githubusercontent.com/all-trees/tree-svg/master/src/main/resources/example.svg)

[svg]: https://en.wikipedia.org/wiki/Scalable_Vector_Graphics
[dyck]: https://en.wikipedia.org/wiki/Dyck_language
