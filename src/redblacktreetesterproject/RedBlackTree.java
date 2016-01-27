/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redblacktreetesterproject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A red-black tree is a binary search tree with an extra bit of storage per
 * node
 *
 * @author YutingFan
 */
public class RedBlackTree {

    private final RedBlackNode sentinel;
    private RedBlackNode root;
    private int countComparison;

    /**
     * Return the root of this redblacktree. Theta(1) for no best case or worse
     * case.
     *
     * @return the root of this redblacktree
     */
    public RedBlackNode getRoot() {
        return root;
    }

    /**
     * The no-argument constructor for a red black RedBlackTree. This
     * constructor creates an empty RedBlackTree. Theta(1) for no best case or
     * worse case.
     */
    public RedBlackTree() {
        sentinel = new RedBlackNode("", 0, null, null, null);
        root = sentinel;
    }

    /**
     * Returns number of values inserted into the tree. Theta(1) for no best
     * case or worse case.
     *
     * @return number of values inserted into the tree
     */
    public int getSize() {
        return 2 ^ (height() / 2 - 1);
    }

    /**
     * The no argument inOrderTraversal() method calls the recursive
     * inOrderTraversal(RedBlackNode) - passing the root. Theta(1) for no best
     * case or worse case.
     */
    public void inOrderTraversal() {
        inOrderTraversal(this.root);
    }

    /**
     * Perfrom an inorder traversal of the tree. The
     * inOrderTraversal(RedBlackNode) method is recursive and displays the
     * content of the tree. It makes calls on System.out.println(). This method
     * would normally be private. Theta(n) for no best case or worse case.
     *
     * @param t the root of the tree on the first call
     */
    public void inOrderTraversal(RedBlackNode t) {
        if (t.getLc() != sentinel) {
            inOrderTraversal(t.getLc());
        }
        System.out.println(t);
        if (t.getRc() != sentinel) {
            inOrderTraversal(t.getRc());
        }
    }

    /**
     * The no argument reverseOrderTraversal() method calls the recursive
     * reverseOrderTraversal(RedBlackNode) - passing the root. Theta(1) for no
     * best case or worse case.
     */
    public void reverseOrderTraversal() {
        reverseOrderTraversal(this.root);
    }

    /**
     * Perform a reverseOrder traversal of the tree. The
     * reverseOrderTraversal(RedBlackNode) method is recursive and displays the
     * content of the tree in reverse order. This method would normally be
     * private. Theta(n) for no best case or worse case.
     *
     * @param t the root of the tree on the first call
     */
    public void reverseOrderTraversal(RedBlackNode t) {
        if (t.getRc() != sentinel) {
            inOrderTraversal(t.getRc());
        }
        System.out.println(t);
        if (t.getLc() != sentinel) {
            inOrderTraversal(t.getLc());
        }
    }

    /**
     * The insert() method places a data item into the tree. Pre-condition:
     * memory is available for insertion. Theta(log n) is the best case.
     * Theta(n) is the worse case.
     *
     * @param value is an integer to be inserted
     */
    public void insert(String value) {
        RedBlackNode y = sentinel;
        RedBlackNode x = root;
        while (x != sentinel) {
            y = x;
            if (value.compareTo(x.getData()) < 0) {
                x = x.getLc();
            } else {
                x = x.getRc();
            }
        }

        RedBlackNode z = new RedBlackNode(value, 1, sentinel, sentinel, sentinel);
        z.setP(y);
        if (y == sentinel) {
            this.root = z;
        } else {
            if (value.compareTo(y.getData()) < 0) {
                y.setLc(z);
            } else {
                y.setRc(z);
            }
        }
        z.setLc(sentinel);
        z.setRc(sentinel);
        z.setColor(1);
        RBInsertFixup(z);
    }

    /**
     * leftRotate() performs a single left rotation. Pre-condition is right
     * child of x isn't sentinel and root's parent should be sentinel. Theta(1)
     * for no best case or worst case.
     *
     * @param x the node needs to be rotated with left side
     */
    public void leftRotate(RedBlackNode x) {
        RedBlackNode y = x.getRc();
        x.setRc(y.getLc());
        y.getLc().setP(x);
        y.setP(x.getP());

        if (x.getP() == sentinel) {
            root = y;
        } else {
            if (x == x.getP().getLc()) {
                x.getP().setLc(y);
            } else {
                x.getP().setRc(y);
            }
        }
        y.setLc(x);
        x.setP(y);
    }

    /**
     * rightRotate() performs a single left rotation. Pre-condition is left
     * child of x isn't sentinel and root's parent should be sentinel. Theta(1)
     * for no best case or worst case.
     *
     * @param x the node needs to be rotated with right side
     */
    public void rightRotate(RedBlackNode x) {
        // pre: left[x] != nil[T]
        // pre: root's parent is nill[T]
        RedBlackNode y = x.getLc();
        x.setLc(y.getRc());
        y.getRc().setP(x);
        y.setP(x.getP());

        if (x.getP() == sentinel) {
            root = y;
        } else {
            if (x == x.getP().getLc()) {
                x.getP().setLc(y);
            } else {
                x.getP().setRc(y);
            }
        }
        y.setRc(x);
        x.setP(y);
    }

    /**
     * Fixing up the tree so that Red Black Properties are preserve. Theta(log
     * n) is the best case. Theta(n) is the worse case.
     *
     * @param z is the new node
     */
    public void RBInsertFixup(RedBlackNode z) {
        RedBlackNode y;
        while (z.getP().getColor() == 1) {
            if (z.getP() == z.getP().getP().getLc()) {
                y = z.getP().getP().getRc();
                if (y.getColor() == 1) {
                    z.getP().setColor(0);
                    y.setColor(0);
                    z.getP().getP().setColor(1);
                    z = z.getP().getP();
                } else {
                    if (z == z.getP().getRc()) {
                        z = z.getP();
                        leftRotate(z);
                    }
                    z.getP().setColor(0);
                    z.getP().getP().setColor(1);
                    rightRotate(z.getP().getP());
                }
            } else {
                y = z.getP().getP().getLc();
                if (y.getColor() == 1) {
                    z.getP().setColor(0);
                    y.setColor(0);
                    z.getP().getP().setColor(1);
                    z = z.getP().getP();
                } else {
                    if (z == z.getP().getLc()) {
                        z = z.getP();
                        rightRotate(z);
                    }
                    z.getP().setColor(0);
                    z.getP().getP().setColor(1);
                    leftRotate(z.getP().getP());
                }
            }
        }
        root.setColor(0);
    }

    /**
     * The boolean contains() returns true if the String v is in the
     * RedBlackTree and false otherwise. It counts each comparison it makes in
     * the variable recentCompares. Theta(log n) is the best case. Theta(n) is
     * the worse case.
     *
     * @param v the value to search for
     * @return true if v is in the tree, false otherwise;
     */
    public boolean contains(String v) {
        RedBlackNode current = root;
        countComparison = 0;
        while (current != sentinel) {
            if (current.getData().equals(v)) {
                countComparison++;
                return true;
            } else if (current.getData().compareTo(v) < 0) {
                countComparison++;
                current = current.getRc();
            } else {
                countComparison++;
                current = current.getLc();
            }
        }
        return false;
    }

    /**
     * Returns number of comparisons made in last call on the contains method.
     * Theta(1) for no best case or worst case.
     *
     * @return number of comparisons made in last call on the contains method
     */
    public int getRecentCompares() {
        return countComparison;
    }

    /**
     * The method closeBy(v) returns a value close to v in the tree. Theta(log
     * n) is the best case. Theta(n) is the worse case.
     *
     * @param v the value to search close by for
     * @return a value close to v in the tree
     */
    public String closeBy(String v) {
        RedBlackNode current = root;
        RedBlackNode node = current;
        while (current != sentinel) {
            node = current;
            if (current.getData().compareTo(v) < 0) {
                current = current.getRc();
            } else {
                current = current.getLc();
            }
        }
        return node.getData();
    }

    /**
     * This a recursive routine that is used to compute the height of the red
     * black tree. A tree with only sentinel is at height of 0. Theta(log n) for
     * no best case or worst case.
     *
     * @param t a pointer to a node in the tree
     * @return the height of node t
     */
    public int height(RedBlackNode t) {
        if (t == sentinel) {
            return 0;
        } else {
            return 1 + Math.max(height(t.getLc()), height(t.getRc()));
        }
    }

    /**
     * This method calls the recursive method. Theta(1) for no best case or
     * worst case.
     *
     * @return the height of the red black tree
     */
    public int height() {
        return height(root);
    }

    /**
     * This method displays the RedBlackTree in level order. It first displays
     * the root. Then it displays all children of the root. Then it displays all
     * nodes on level 3 and so on. It is not recursive. It uses a queue.
     * Theta(log n) is the best case. Theta(n) is the worse case.
     */
    public void levelOrderTraversal() {
//        RedBlackNode t = root;
        Queue queue = new Queue();

        queue.enQueue(root);
        while (!queue.isEmpty()) {
            RedBlackNode p = queue.deQueue();
            if (p != sentinel) {
                if (p.getLc() != sentinel) {
                    queue.enQueue(p.getLc());
                }
                if (p.getRc() != sentinel) {
                    queue.enQueue(p.getRc());
                }
            }
        }
        System.out.println(queue);
    }

    public void levelOrderBottom(){
        List<List<String>> resultList = levelOrderBottom(root);
        for(List<String> list : resultList){
            for(String s : list){
                if(!s.equals(""))
                    System.out.print(s+" ");
            }
            System.out.println();
        }
    }
    
    public List<List<String>> levelOrderBottom(RedBlackNode root) {
        if(root == null) return new LinkedList<>();
        List<List<String>> levels = new LinkedList<>();
        LinkedList<RedBlackNode> q = new LinkedList<>(); 
        q.add(root);

        while(!q.isEmpty()){
            List<String> list = new ArrayList<>(); 
            int size = q.size();
            for(int i = 0; i < size; i++){
                RedBlackNode node = q.remove();
                list.add(node.getData());
                if(node.getLc() != null) q.add(node.getLc());
                if(node.getRc() != null) q.add(node.getRc());
            }
            ((LinkedList)levels).addFirst(list);
        }
        return levels;
    }
    
    public List<List<String>> levelOrderTraversalToList(RedBlackNode root) {
        if(root == null) return new LinkedList<>();
        List<List<String>> levels = new LinkedList<>();
        LinkedList<RedBlackNode> q = new LinkedList<>(); 
        q.add(root);

        while(!q.isEmpty()){
            List<String> list = new ArrayList<>(); 
            int size = q.size();
            for(int i = 0; i < size; i++){
                RedBlackNode node = q.remove();
                list.add(node.getData());
                if(node.getLc() != null) q.add(node.getLc());
                if(node.getRc() != null) q.add(node.getRc());
            }
            ((LinkedList)levels).add(list);
        }
        return levels;
    }
    
    /**
     * Test the RedBlack tree
     *
     * @param args no command line arguments
     */
    public static void main(String[] args) {

        RedBlackTree rbt = new RedBlackTree();

        for (int j = 1; j <= 5; j++) {
            rbt.insert("" + j);
        }

        // after 1..5 are inserted
        System.out.println("RBT in order");
        rbt.inOrderTraversal();
        System.out.println("RBT reverse order");
        rbt.reverseOrderTraversal();
        System.out.println("RBT level order");
        rbt.levelOrderTraversal();

        System.out.println("RBT level order bottom");
        rbt.levelOrderBottom();
        System.out.println();
        
        // is 3 in the tree
        if (rbt.contains("" + 3)) {
            System.out.println("Found 3");
        } else {
            System.out.println("No 3 found");
        }

        // display the height
        System.out.println("The height is " + rbt.height());
    }
}
