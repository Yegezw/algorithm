package stage3.week12.set;

import port.Set;
import stage3.week12.tree.RBTree;

public class RBSet<E extends Comparable<E>> implements Set<E> {

    private final RBTree<E, Object> rbTree;

    public RBSet() {
        rbTree = new RBTree<>();
    }

    @Override
    public void add(E e) {
        rbTree.add(e, null);
    }

    @Override
    public void remove(E e) {
        rbTree.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return rbTree.contains(e);
    }

    @Override
    public int getSize() {
        return rbTree.getSize();
    }

    @Override
    public boolean isEmpty() {
        return rbTree.isEmpty();
    }
}
