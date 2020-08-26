import java.util.Stack;
class tranformToLeftClonedTree{

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
        }
        return root;
    }

    public static Node tranformToLeftClonedTree(Node node){
        if(node == null){
            return null;
        }

       Node lcr = tranformToLeftClonedTree(node.left);
       Node rcr = tranformToLeftClonedTree(node.right);

       Node nn = new Node(node.data, lcr, null);
       node.left = nn;
       node.right = rcr;

       return node;
    }

    public static void display(Node node){
        if(node == null){
            return;
        }

        System.out.print(node.left == null ? "." : node.left.data + "");
        System.out.print(" <- " + node.data + " -> ");
        System.out.println(node.right == null ? "." : node.right.data + "");

        display(node.left);
        display(node.right);
    }

    public static void main(String[] args){
        int[] arr = {1,2,4,-1,-1,5,-1,-1,3,6,-1,-1,7,-1,-1};

        Node root = constructor(arr);
        tranformToLeftClonedTree(root);
        display(root);
    }
}