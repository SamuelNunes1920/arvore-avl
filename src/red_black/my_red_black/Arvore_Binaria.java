package red_black.my_red_black;



/**
 * A simple interface for binary trees.  An empty binary tree is
 * represented with the value null; a non-empty tree by its root
 * node.
 */
 public interface Arvore_Binaria {

    
    /**
     * Visits the nodes in this tree in preorder.
     */
    void traversePreorder(Visitor visitor);

    /**
     * Visits the nodes in this tree in postorder.
     */
    void traversePostorder(Visitor visitor);

    /**
     * Visits the nodes in this tree in inorder.
     */
    void traverseInorder(Visitor visitor);

    /**
     * Simple visitor interface.
     */
    public interface Visitor {
        
        void visit(My_Red_Black_Node node);
    }
}