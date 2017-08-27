import java.util.*;

/**
 * Created by ultra on 27.07.2017.
 */
public class TreeNodeImpl implements TreeNode {
    private TreeNode parent;
    private List<TreeNode> children;
    private Object data;
    private boolean expanded;

    public TreeNodeImpl()
    {
        this.parent = null;
        this.children = null;
        this.data = null;
        this.expanded = false;
    }

    public TreeNodeImpl(TreeNode parent, Object data, boolean expanded)
    {
        this.parent = null;
        this.data = null;
        this.expanded = false;
        this.children = null;
    }
    /**
     * Returns the parent <code>TreeNode</code>.
     */
    public TreeNode getParent()
    {
        return parent;
    }
    /**
     * Sets the parent <code>TreeNode</code>.<br/>
     * Is typically called in {@link #addChild(TreeNode)} and {@link #removeChild(TreeNode)} methods
     *  of the parent <code>TreeNode</code> itself.
     */
    public void setParent(TreeNode parent)
    {
        this.parent = parent;
    }
    /**
     * Returns the top of the tree that contains this <code>TreeNode</code>.
     * @return root node or null if the parent of this node is null (i.e. the returned node != this).
     */
    public TreeNode getRoot()
    {
        if (parent == null)
            return null;

        TreeNode smt = this;
        while (smt.getParent() != null)
            smt = smt.getParent();
        return smt;
    }

    /**
     * Returns false if this <code>TreeNode</code> has non-zero number of children.
     * @return true if the node is a leaf (i.e. does not have child nodes)
     */
    public boolean isLeaf()
    {
        if (children == null)
            return true;
        else
            return children.size() == 0;
    }
    /**
     * Returns the number of children which this <code>TreeNode</code> has.
     */
    public int getChildCount()
    {
        if (children != null){
            return children.size();
        }
        return 0;
    }
    /**
     * Returns children <code>TreeNode</code>'s as {@link Iterator}.
     */
    public Iterator<TreeNode> getChildrenIterator()
    {
        return children != null ? children.iterator() : null;
    }
    /**
     * Adds the given child to this <code>TreeNode</code> and sets this as the parent to it.
     */
    public void addChild(TreeNode child)
    {
        child.setParent(this);
        if (children != null){
            children.add(child);
        } else {
            children = new ArrayList<TreeNode>();
            children.add(child);
        }
    }
    /**
     * Removes the given child from this <code>TreeNode</code> and (if succeeded)
     *  sets null as the parent to it (in order to leave the tree in the consistent state).
     * @return true if succeeded, false if the child was not found
     */
    public boolean removeChild(TreeNode child)
    {
        child.setParent(null);
        return children.remove(child);
    }

    /**
     * Returns the "expanded" state of this <code>TreeNode</code>.<br/>
     * By default (unless {@link #setExpanded(boolean)} is called)
     *  "expanded" is false (i.e. the node is "collapsed").
     */
    public boolean isExpanded()
    {
        return expanded;
    }
    /**
     * Sets the "expanded" state to this <code>TreeNode</code> and to all its children, recursively
     * @param expanded true - to expand this tree branch, false - to collapse this tree branch.
     */
    public void setExpanded(boolean expanded)
    {
        this.expanded = expanded;
        if (children != null)
            for (TreeNode i : children)
                i.setExpanded(expanded);
    }

    /**
     * Returns the user object stored in this <code>TreeNode</code>
     *  or <code>null</code> if {@link #setData(Object)} was not called.
     */
    public Object getData()
    {
        return data;
    }
    /**
     * Sets the user data to store in this <code>TreeNode</code>.
     */
    public void setData(Object data)
    {
        this.data = data;
    }
    /**
     * Returns the string representation of the path from root to this <code>TreeNode</code>.<br/>
     * Path elements are separated by "->".<br/>
     * Each path element is either getData().toString() or "empty" if getData()==null.<br/>
     * For example: "rootNode0->node1->node13->empty" ("rootNode0" is a result of getRoot().getData().toString() in this example).
     */
    public String getTreePath()
    {
        if (parent == null){
            return this.toString();
        }else {
            return parent.getTreePath() + "->" +  this.toString();
        }
    }

    /** method override
     *
     * @return getData().toString() or "empty"
     */
    public String toString() {
        if (data == null){
            return "empty";
        }else{
            return data.toString();
        }
    }
    /**
     * Finds the (first) node with the given data among the parents' sequence of this <code>TreeNode</code>.
     * By convention, the parents' sequence includes this node itself (i.e. the following may be true: obj.findParent(*) == obj).<br/>
     * Data objects should be compared by equals() method (but if the given data is null then the parent with null data should be returned).
     * @param data the data to find; may be <code>null</code>
     * @return the node found or <code>null</code> if no matching data was found.
     */
    public TreeNode findParent(Object data)
    {
        if (data != null) {
            if (this.data != null && this.data.equals(data))
                return this;
            if (this.parent != null)
                return parent.findParent(data);

            return null;
        } else {
            if (this.data == null)
                return this;
            if (this.parent != null)
                return parent.findParent(data);

            return null;
        }
    }
    /**
     * Finds the (first) node with the given data among the children of this <code>TreeNode</code>.<br/>
     * Searches it recursively (if some child doesn't have the given data, the children of this child are searched, and so on).<br/>
     * Data objects should be compared by equals() method (but if the given data is null then the child with null data should be returned).
     * @param data the data to find; may be <code>null</code>
     * @return the node found or <code>null</code> if no matching data was found.
     */
    public TreeNode findChild(Object data)
    {
        TreeNode result;
        if (data != null)
        {
            if (children != null)
                for (TreeNode i : children) {
                    if (data.equals(i.getData()))
                        if (i.getData() != null)
                            return i;
                    result = i.findChild(data);
                    if (result != null)
                        return result;

                }
            return null;
        } else {
            if (children != null)
                for (TreeNode i : children) {
                    if (i.getData() == null)
                        return i;
                    result = i.findChild(data);
                    if (result != null)
                        return result;
                }
            return null;
        }
    }
}
