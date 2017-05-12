package com.kiss.readerlibrary.core.tree;

/**
 * @author qinghui
 * @date 2017/5/3
 */

public class TreeNode<P, E extends Brother, C extends Parent> extends TreeLeaf<P, E> implements Node<P, E, C> {

    private final TreeRoot<C> treeRoot = new TreeRoot<>();

    public TreeNode(P p, E l, E r) {
        super(p, l, r);
    }

    @Override
    public void addChild(C c) {
        treeRoot.addChild(c);
    }

    @Override
    public void removeChild(C c) {
        treeRoot.removeChild(c);
    }

    @Override
    public void clearChild() {
        treeRoot.clearChild();
    }

    @Override
    public C getChild(int index) {
        return treeRoot.getChild(index);
    }

    @Override
    public int childSize() {
        return treeRoot.childSize();
    }

    @Override
    public int childIndexOf(C c) {
        return treeRoot.childIndexOf(c);
    }
}
