import java.util.ArrayList;
import java.util.Stack;
class printKLevelsFar{

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

    static ArrayList<Node> path;
    public static boolean find(Node node, int data){
        if(node == null){
            return false;
        }
        if(node.data == data){
            path.add(node);
            return true;
        }

        boolean filc = find(node.left, data);
        if(filc){
            path.add(node);
            return true;
        }

        boolean firc = find(node.right, data);
        if(firc){
            path.add(node);
            return true;
        }

        return false;
    }

    public static void printKLevelsDown(Node node, int k, Node blocker){
        if(node == null || k<0 || node == blocker){
            return;
        }

        if(k==0){
            System.out.println(node.data + " ");
        }

        printKLevelsDown(node.left, k-1, blocker);
        printKLevelsDown(node.right, k-1, blocker);
    }

    public static void printKLevelsFar(Node node, int data, int k){
        path = new ArrayList<>();
        find(node, data);

        for(int i=0; i<path.size() && i<=k; i++){
            printKLevelsDown(path.get(i), k-i, i==0 ? null : path.get(i-1));
        }
    }

    public static void main(String[] args){
        int[] arr = {50,25,12,-1,-1,37,30,-1,-1,22,35,-1,-1,36,-1,-1,75,62,-1,70,-1,-1,87,-1,-1};

        Node root = constructor(arr);
        printKLevelsFar(root, 37, 2);
    

    }
}