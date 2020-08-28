import java.util.Stack;
class balancedBinaryTree2{

    public static class Node{
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right){
            this.data = data;
            this.left = left;
            this.right = this.right;
        }
    }

    private static class Pair{
        Node node;
        int state;

        Pair(Node node, int state){
            this.node = node;
            this.state = state;
        }
    }

    private static class BalPair{
        int ht;
        boolean isBal;
    }

    public static Node constructor(int[] arr){
        Node root = new Node(arr[0], null, null);
        Pair rtp = new Pair(root, 1);

        Stack<Pair> st = new Stack<>();
        st.push(rtp);
        int idx = 0;
        while(st.size()>0){
            Pair top = st.peek();
            if(top.state == 1){
                idx++;
                if(arr[idx] != -1){
                    Node ln = new Node(arr[idx], null, null);
                    top.node.left = ln;
                    Pair lp = new Pair(ln, 1);
                    st.push(lp);
                }
                top.state++;
            }else if(top.state == 2){
                idx++;
                if(arr[idx] != -1){
                    Node rn = new Node(arr[idx], null, null);
                    top.node.right = rn;
                    Pair rp = new Pair(rn, 1);
                    st.push(rp);
                }
                top.state++;
            }else{
                st.pop();
            }
        }return root;
    }

    public static BalPair isBalanced(Node node){
        if(node == null){
            BalPair bp = new BalPair();
            bp.ht = 0;
            bp.isBal = true;

            return bp;
        }

        BalPair lp = isBalanced(node.left);
        BalPair rp = isBalanced(node.right);

        BalPair mp = new BalPair();
        mp.isBal = lp.isBal && rp.isBal && (Math.abs(lp.ht-rp.ht) <= 1);
        mp.ht = Math.max(lp.ht, rp.ht) +1;

        return mp;
    }

    public static void main(String[] args){
        int[] arr = {50,25,12,-1,-1,37,30,-1,-1,-1,75,62,60,-1,-1,-1,-1};

        Node root = constructor(arr);
        BalPair mp =  isBalanced(root);
        System.out.println(mp.isBal);
    }
}