class constructBinaryTreeFromPreOrderAndInorder{

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

    public static Node buildTree(int[] preorder, int psi, int pei, int[] inorder, int isi, int iei){
        if(isi > iei) return null;

        int idx = isi;
        while(inorder[idx] != preorder[psi]) idx++;

        int count = idx - isi;

        Node node = new Node(preorder[psi], null, null);

        node.left = buildTree(preorder, psi + 1, psi + count, inorder, isi, idx - 1);
        node.right = buildTree(preorder, psi + count + 1, pei, inorder, idx + 1, iei);

        return node;

    }

    public static void main(String[] args){
        int[] preorder = {4, 2, 1, 3, 6, 5, 7};
        int[] inorder = {1, 2, 3, 4, 5, 6, 7};
        int n = preorder.length;
        Node node = buildTree(preorder, 0, n-1, inorder, 0, n-1);
        display(node);
    }
}
