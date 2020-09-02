// BFS traversal => level order traversal
import java.util.ArrayList;
import java.util.Stack;
import java.util.Queue;
import java.util.ArrayDeque;
class rightSideViewIterative{

    private static class Node{
        int data; 
        Node left;
        Node right;

        Node(int data, Node left, Node right){
            this.data =data;
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
                    Node ln= new Node(arr[idx], null, null);
                    top.node.left = ln;
                    Pair lp = new Pair(ln, 1);
                    st.push(lp);
                }
                top.state++;
            }else if(top.state == 2){
                idx++;
                if(arr[idx] != -1){
                    Node rn= new Node(arr[idx], null, null);
                    top.node.right = rn;
                    Pair rp = new Pair(rn, 1);
                    st.push(rp);
                }
                top.state++;
            }else{
                st.pop();
            }
        } return root;
    }

    public static void rightSideViewIterative(Node node){
        Queue<Node> mq = new ArrayDeque<>();
        mq.add(node);
        Node pre = null;
        while(mq.size()>0){
            int cs = mq.size();
            while(cs-- >0){
                Node cn = mq.remove();
                pre = cn;
                if(cn.left != null){
                    mq.add(cn.left);
                } 
                if(cn.right != null){
                    mq.add(cn.right);
                }
            }
             System.out.print(pre.data + " ");
        }
    }

    public static void main(String[] args){
        int [] arr = {50,25,12,-1,-1,37,30,-1,-1,-1,75,62,-1,70,-1,-1,87,-1,-1};

        Node root = constructor(arr);
        rightSideViewIterative(root);
        System.out.println();
    }
}