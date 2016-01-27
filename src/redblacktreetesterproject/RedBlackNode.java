/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redblacktreetesterproject;

/**
 * RedBlackNode is the class for nodes in a red black tree.
 *
 * @author YutingFan
 */
public class RedBlackNode {

    public static final int black = 0;
    public static final int red = 1;
    private RedBlackNode lc;
    private RedBlackNode rc;
    private RedBlackNode p;
    private String data;
    private int color;

    /**
     * Construct a RedBlackNode with data, color, parent pointer, left child
     * pointer and right child pointer. Theta(1) for no best case or worse case.
     *
     * @param data a simple value held in the tree
     * @param color either RED or BLACK
     * @param p the parent pointer
     * @param lc the pointer to the left child (will be null only for the node
     * that represents all external nulls
     * @param rc the pointer to the right child (will be null only for the node
     * that represents all external nulls
     */
    RedBlackNode(String data, int color, RedBlackNode p, RedBlackNode lc, RedBlackNode rc) {
        this.data = data;
        this.color = color;
        this.p = p;
        this.lc = lc;
        this.rc = rc;
    }

    /**
     * The toString() methods returns a string representation of the
     * RedBlackNode. Theta(1) for no best case or worse case.
     *
     * @return the string representation of a RedBlackNode
     */
    @Override
    public String toString() {
        if (this.lc != null && this.rc != null) {
            return "data=" + this.data + " : color=" + (this.color == 0 ? "Black" : "Red") + " : parent=" + (this.p.data.equals("") ? "-1" : this.p.data) + " : LC=" + (this.lc.data.equals("") ? "-1" : this.lc.data) + " : RC=" + (this.rc.data.equals("") ? "-1" : this.rc.data);
        }
        return this.data;
    }

    /**
     * The getColor() method returns RED or BLACK. Theta(1) for no best case or
     * worse case.
     *
     * @return the color value (RED or BLACK)
     */
    public int getColor() {
        return this.color;
    }

    /**
     * The getData() method returns the data in the node. Theta(1) for no best
     * case or worse case.
     *
     * @return the data value in the node
     */
    public String getData() {
        return this.data;
    }

    /**
     * The getLc() method returns the left child of the RedBlackNode. Theta(1)
     * for no best case or worse case.
     *
     * @return the left child field
     */
    public RedBlackNode getLc() {
        return this.lc;
    }

    /**
     * The getP() method returns the parent of the RedBlackNode. Theta(1) for no
     * best case or worse case.
     *
     * @return the parent field
     */
    public RedBlackNode getP() {
        return this.p;
    }

    /**
     * The getRc() method returns the right child of the RedBlackNode. Theta(1)
     * for no best case or worse case.
     *
     * @return the right child field
     */
    public RedBlackNode getRc() {
        return this.rc;
    }

    /**
     *
     * The setColor() method sets the color of the RedBlackNode. Theta(1) for no
     * best case or worse case.
     *
     * @param color the color of the RedBlackNode
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * The setData() method sets the data or key of the RedBlackNode. Theta(1)
     * for no best case or worse case.
     *
     * @param data is an int holding a node's data value
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * The setLc() method sets the left child of the RedBlackNode. Theta(1) for
     * no best case or worse case.
     *
     * @param lc establishes a left child for this node
     */
    public void setLc(RedBlackNode lc) {
        this.lc = lc;
    }

    /**
     * The setP() method sets the parent of the RedBlackNode. Theta(1) for no
     * best case or worse case.
     *
     * @param p establishes a parent pointer for this node
     */
    public void setP(RedBlackNode p) {
        this.p = p;
    }

    /**
     * The setRc() method sets the right child of the RedBlackNode. Theta(1) for
     * no best case or worse case.
     *
     * @param rc establishes a right child for this node.
     */
    public void setRc(RedBlackNode rc) {
        this.rc = rc;
    }
}
