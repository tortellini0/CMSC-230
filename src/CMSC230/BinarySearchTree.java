package CMSC230;

public class BinarySearchTree {
    private TreeNode root;
    /**
     * finds the Identified object associated with an Identity
     * @param identity - Identity - identity that is being used to find an Identified Object
     * @return - IdentifiedObject - returns the IdentifiedObject associated with the Identity given, or null if it isnt in the tree 
     */
    public IdentifiedObject find(Identity identity){
        if (identity == null){
            throw new IllegalArgumentException(
                "identity cant be null"
            );
        }
        TreeNode tempNode = findNode(root, identity);
        if (tempNode == null){
            return null;
        }else {
            return tempNode.data;
        }
    }

    private TreeNode findNode(TreeNode root, Identity identity){
        if (root == null){
            return null;
        }else{
            if(root.data.getIdentity().match(identity)){
                return root;
            }else if (identity.isLessThan(root.data.getIdentity())){
                return findNode(root.left, identity);
            }else {
                return findNode(root.right, identity);
            }
        }
    }
    /**
     * adds and IdentifiedObject to the tree 
     * @param object - IdentifiedObject - the object that is being added to the tree
     */
    public void add(IdentifiedObject object){
        if (object == null){
            throw new IllegalArgumentException(
                "object cant be null"
            );
        }
        root = addNode(root, new TreeNode(object));
    }

    private TreeNode addNode(TreeNode tempRoot, TreeNode add){
        if (tempRoot == null){
            return add;
        }else if (tempRoot.data.getIdentity().match(add.data.getIdentity())){
            return tempRoot;
        }else if (add.data.getIdentity().isLessThan(tempRoot.data.getIdentity())){
            tempRoot.left = addNode(tempRoot.left, add);
            return tempRoot;
        }else{
            tempRoot.right = addNode(tempRoot.right, add);
            return tempRoot;
        }
    }

    private class TreeNode{
        IdentifiedObject data;
        TreeNode left;
        TreeNode right;
        TreeNode(IdentifiedObject object){
            data = object;
        }
    }
}
