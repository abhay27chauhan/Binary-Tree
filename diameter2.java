import java.util.Stack;
class diameter2{

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

    private static class Pair{
        Node node;
        int state;

        Pair(Node node, int state){
            this.node = node;
            this.state = state;
        }
    }

    private static class diaPair{
        int ht;
        int dia;
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

    public static diaPair diameter2(Node node){
        if(node == null){
            diaPair bp = new diaPair();
            bp.ht = -1;
            bp.dia = 0;
            return bp;
        }

        diaPair lp = diameter2(node.left);
        diaPair rp = diameter2(node.right);

        diaPair mp = new diaPair();
        mp.ht = Math.max(lp.ht, rp.ht) + 1;

        int f = lp.ht + rp.ht + 2;
        mp.dia = Math.max(f, Math.max(lp.dia, rp.dia));

        return mp;
    }

    public static void main(String[] args){
        int[] arr = {50,25,12,-1,-1,37,30,-1,-1,-1,75,62,60,-1,-1,-1,-1};

        Node root = constructor(arr);
        diaPair dp = diameter2(root);
        System.out.println(dp.dia);
    }
}