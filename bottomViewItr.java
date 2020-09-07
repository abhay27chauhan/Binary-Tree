import java.util.Stack;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Arrays;
class bottomViewItr{

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

    static int leftMin = 0;
    static int rightMax = 0;
    public static void width(Node node, int level){
        if(node == null){
            return;
        }
        leftMin = Math.min(leftMin, level);
        rightMax = Math.max(rightMax, level);

        width(node.left, level-1);
        width(node.right, level+1);
    }

    public static int[] bottomViewItr(Node node){
        width(node, 0);
        int range = rightMax-leftMin+1;
        int[] ans = new int[range];
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(node, 0));

        while(q.size()>0){
            int sz = q.size();
            while(sz-- >0){
                Pair rp = q.remove();
                int val = rp.state;

                ans[val-leftMin] = rp.node.data;

                if(rp.node.left != null){
                    q.add(new Pair(rp.node.left, rp.state-1));
                }
                if(rp.node.right != null){
                    q.add(new Pair(rp.node.right, rp.state+1));
                }
            }
        }
        return ans;
    }

    public static void main(String[] args){
        int [] arr = {50,25,12,-1,-1,37,30,-1,-1,-1,75,62,-1,70,-1,-1,87,-1,-1};

        Node root = constructor(arr);
        int[] ans = bottomViewItr(root);

        for(int val: ans){
            System.out.print(val + " ");
        }

        System.out.println();
    }
}