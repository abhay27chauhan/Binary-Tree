class constructBinaryTreeFromPostOrderAndInorder{

    public static class Node{
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static void display(Node node){
        if(node == null){
            return;
        }

        String str = node.left != null ? node.left.data + "" : "." ;
        str += " <- " + node.data + " -> ";
        str += node.right != null ? node.right.data + "" : ".";

        System.out.println(str);

        display(node.left);
        display(node.right);
    }

    public static Node buildTree(int[] postorder, int psi, int pei, int[] inorder, int isi, int iei){
        if(isi > iei) return null;

        int idx = isi;
        while(inorder[idx] != postorder[pei]) idx++;

        int count = idx - isi;

        Node node = new Node(postorder[pei], null, null);

        node.left = buildTree(postorder, psi, psi + count - 1, inorder, isi, idx-1);
        node.right = buildTree(postorder, psi + count, pei - 1, inorder, idx+1, iei);

        return node;
    }

    public static void main(String[] args){
        int[] postorder = {1, 3, 2, 5, 7, 6, 4};
        int[] inorder = {1, 2, 3, 4, 5, 6, 7};
        int n = postorder.length;
        Node node = buildTree(postorder, 0, n-1, inorder, 0, n-1);
        display(node);
    }
}
