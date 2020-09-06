package niuke;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Wangyi1 {
    // 3 10 0 1 0 -1 1 1 1 1 1 -1 0 1 2 1 2 -1 3 1 3 1     2 3 1 3 1    8 0 1 0 1 3 1 3 1 1 1 1 1 2 1 0 1
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int i = 0; i < T; i++) {
            int N = scanner.nextInt();
            Node root = new Node();
            Node currentNode = root;
            for (int j = 0; j < N; j++) {
                int dirction = scanner.nextInt();
                int result = scanner.nextInt();
                if (result == 1) {
                    if (dirction == 0) {
                        if (currentNode.up != null) currentNode = currentNode.up;
                        else {
                            Node currentNodeUp = new Node();
                            currentNode.up = currentNodeUp;
                            currentNodeUp.down = currentNode;
                            currentNode = currentNodeUp;
                        }
                    }else if (dirction == 1) {
                        if (currentNode.down != null) currentNode = currentNode.down;
                        else {
                            Node currentNodeDown = new Node();
                            currentNode.down = currentNodeDown;
                            currentNodeDown.up = currentNode;
                            currentNode = currentNodeDown;
                        }
                    } else if (dirction == 2) {
                        if (currentNode.left != null) currentNode = currentNode.left;
                        else {
                            Node currentNodeLeft = new Node();
                            currentNode.left = currentNodeLeft;
                            currentNodeLeft.right = currentNode;
                            currentNode = currentNodeLeft;
                        }
                    } else if (dirction == 3) {
                        if (currentNode.right != null) currentNode = currentNode.right;
                        else {
                            Node currentNodeRight = new Node();
                            currentNode.right = currentNodeRight;
                            currentNodeRight.left = currentNode;
                            currentNode = currentNodeRight;
                        }
                    }
                }
            }
            Node des = currentNode;
            System.out.println(bfs(root, des));
        }
    }
    private static int bfs(Node root, Node des){
        if (root == des) return 0;

        Queue<Node> queue1 = new LinkedList<>();
        Queue<Node> queue2 = new LinkedList<>();
        queue1.add(root);
        int res = 0;
        while (true){
            res++;
            if (queue2.isEmpty()){
                while (!queue1.isEmpty()) {
                    Node currentNode = queue1.poll();
                    if (currentNode.up != null) {
                        if (currentNode.up == des) return res;
                        queue2.add(currentNode.up);
                    }
                    if (currentNode.down != null) {
                        if (currentNode.down == des) return res;
                        queue2.add(currentNode.down);
                    }
                    if (currentNode.left != null) {
                        if (currentNode.left == des) return res;
                        queue2.add(currentNode.left);
                    }
                    if (currentNode.right != null) {
                        if (currentNode.right == des) return res;
                        queue2.add(currentNode.right);
                    }
                }
            } else {
                while (!queue2.isEmpty()){
                    Node currentNode = queue2.poll();
                    if (currentNode.up != null) {
                        if (currentNode.up == des) return res;
                        queue1.add(currentNode.up);
                    }
                    if (currentNode.down != null) {
                        if (currentNode.down == des) return res;
                        queue1.add(currentNode.down);
                    }
                    if (currentNode.left != null) {
                        if (currentNode.left == des) return res;
                        queue1.add(currentNode.left);
                    }
                    if (currentNode.right != null) {
                        if (currentNode.right == des) return res;
                        queue1.add(currentNode.right);
                    }
                }
            }
        }


    }

    static class Node{
        Node left;
        Node right;
        Node up;
        Node down;
        public Node(){}
    }


}
