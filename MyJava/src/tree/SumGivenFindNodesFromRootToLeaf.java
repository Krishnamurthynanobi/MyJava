package tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class SumGivenFindNodesFromRootToLeaf {
    static int target = 21;
	public static void main(String... args){
		Node root = createTree(new int[]{10,8,2,3,5,2,-1});
		printPath(root,0);
        System.out.println();
        printPathUsingStack(root, 0);
		
	}

    static Deque<Integer> stack = new ArrayDeque<>();
    private static void  printPathUsingStack(Node root, int sum) {
        if(root == null)
            return ;
        if(root.left == null && root.right == null){
            if(sum + root.data == target){
                System.out.print(root.data);
                stack.stream().forEach(num -> System.out.print(" " + num));
                return ;
            }
        }
        stack.push(root.data);
        printPathUsingStack(root.left,sum + root.data);
        printPathUsingStack(root.right,sum + root.data);
        stack.pop();
    }


    private static boolean printPath(Node root, int sum) {
        if(root == null)
            return false;

        if(root.left == null && root.right == null){
            if(sum + root.data == target){
                System.out.print(root.data + " ");
                return true;
            }
        }

        if(printPath(root.left,sum+root.data) || printPath(root.right,sum+root.data)) {
            System.out.print(root.data + " ");
            return true;
        }
        return false;
    }

    static class Node{
        Node left;
        Node right;
        int data;

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }

        public Node(int data) {
            this.data = data;
        }
    }

    public static Node createTree(int[] input){
        Map<Integer,Node> map = new HashMap<>();
        Node root = null;
        for (int i = 0; i < input.length; i++) {
            if(input[i]!=-1){
                Node node = null;
                if(map.containsKey(input[i]))
                    node = map.get(input[i]);
                else {
                    node = new Node(input[i]);
                    map.put(input[i],node);
                }
                if(root == null)
                    root = node;
                int left = 2*i + 1;
                if(left < input.length && input[left]!=-1) {
                    node.left = new Node(input[left]);
                    map.put(input[left],node.left);
                }
                int right = 2*i + 2;
                if(right < input.length && input[right]!=-1) {
                    node.right = new Node(input[right]);
                    map.put(input[right],node.right);
                }

            }
        }
        return root;
    }
}
