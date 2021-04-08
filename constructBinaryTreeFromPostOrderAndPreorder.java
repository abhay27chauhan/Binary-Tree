class constructBinaryTreeFromPostOrderAndPreorder{

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

    public static Node buildTree(int[] preorder, int prsi, int prei, int[] postorder, int psi, int pei){
        if(prsi > prei) return null;
        Node node = new Node(preorder[prsi], null, null);
        if(prsi == prei) return  node;

        int idx = psi;
        while(postorder[idx] != preorder[prsi+1]) idx++;

        int count = idx - psi + 1;

        node.left = buildTree(preorder, prsi+1, prsi + count, postorder, psi, idx);
        node.right = buildTree(preorder, prsi + count +1, prei, postorder, idx+1, pei-1);

        return node;
    }

    public static void main(String[] args){
        int[] preorder = {4, 2, 1, 3, 6, 5, 7};
        int[] postorder = {1, 3, 2, 5, 7, 6, 4};
        int n = preorder.length;
        Node node = buildTree(preorder, 0, n-1, postorder, 0, n-1);
        display(node);
    }
}
