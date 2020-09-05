import java.util.ArrayList;
import java.util.Stack;
import java.util.HashMap;
class topView{

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

    public static void topView(Node node, int level, HashMap<Integer,Integer> hm){
        if(node == null){
            return;
        }

        if(hm.containsKey(level) == false){
            hm.put(level, node.data);
        }
        topView(node.left, level-1, hm);
        topView(node.right, level+1, hm);
    }

    public static void main(String[] args){
        int [] arr = {50,25,12,-1,-1,37,30,-1,-1,-1,75,62,-1,70,-1,-1,87,-1,-1};

        Node root = constructor(arr);
        HashMap<Integer, Integer> hm = new HashMap<>();
        topView(root, 0, hm);
        width(root, 0);
        // for(int i=leftMin; i<=rightMax; i++){
        //     System.out.println(hm.get(i));
        // }
        System.out.print(root.data + " ");
        int i=-1;
        int j = 1;
        while(i>=leftMin && j<=rightMax){
            System.out.print(hm.get(i) + " " + hm.get(j) + " ");
            i--;
            j++;
        }
        while(i>=leftMin){
            System.out.print(hm.get(i) + " ");
            i--;
        }
        while(i>=leftMin){
            System.out.print(hm.get(j) + " ");
            j++;
        }

        System.out.println();
    }
}