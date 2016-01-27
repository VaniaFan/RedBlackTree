/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redblacktreetesterproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The RedBlackTreeTester is a spell checking program used to test the re black
 * tree.
 *
 * @author YutingFan
 */
public class RedBlackTreeTester {

    /**
     * The no-argument constructor for a red black tree tester.
     */
    public RedBlackTreeTester() {

    }

    /**
     * The RedBlackTreeTester is a spell checking program used to test the re
     * black tree.
     *
     * @param args The file name of the word list (to be used as a dictionary).
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String dictionaryPath = "src/files/shortwords.txt";
        File file = new File(dictionaryPath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String lineContents = null;
            RedBlackTree dicTree = new RedBlackTree();
            int count = 0;
            while ((lineContents = reader.readLine()) != null) {
                dicTree.insert(lineContents);
                count++;
            }
            reader.close();
            System.out.println("Red Black Tree is loaded with " + count + " words.\nThe height of the tree is " + dicTree.height() + "\n2 * Lg( n+1) = " + 2 * Math.log(count + 1) / Math.log(2));

            System.out.println("\nLegal commands are:");
            System.out.println("<p>   display the entire word tree with a level order traversal.");
            System.out.println("<s>   print the words of the tree in sorted order (use an inorder traversal).");
            System.out.println("<r>   print the words of the tree in reverse sorted order.");
            System.out.println("<!>   to quit.");
            System.out.println("<c>   <word> to spell check this word");
            System.out.println("<a>   <word> add word to tree.");
            System.out.println("<f>   <fileName> to check a text file for spelling errors.");

            System.out.print("\nPlease input a command.\n>");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            while (!runCommand(dicTree, command, dictionaryPath)) {
                System.out.print("Command is illegal. Please input again.\n>");
                scanner = new Scanner(System.in);
                command = scanner.nextLine();
            }
            while (!command.equals("!")) {
                System.out.print(">");
                scanner = new Scanner(System.in);
                command = scanner.nextLine();
                while (!runCommand(dicTree, command, dictionaryPath)) {
                    System.out.print("Command is illegal. Please input again.\n>");
                    scanner = new Scanner(System.in);
                    command = scanner.nextLine();
                }
            }
            System.out.println("Bye");
        } catch (IOException e) {
            e.getMessage();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.getMessage();
                }
            }
        }
    }

    public static boolean runCommand(RedBlackTree dicTree, String command, String filePath) {
        try {
            if (command.equals("!")) {
                return true;
            } else if (command.startsWith("c")) {
                /**
                 * Check if keyword is in the dictionary
                 */
                String keyword = command.substring(2, command.length());
                if (dicTree.contains("" + keyword)) {
                    System.out.println("Found \"" + keyword + "\" after " + dicTree.getRecentCompares() + " comparisons.");
                } else {
                    System.out.println("\"" + keyword + "\" not found in dictionary. Perhaps you mean \"" + dicTree.closeBy(keyword) + "\"");
                }
            } else if (command.startsWith("a")) {
                /**
                 * add word to tree
                 */
                String newWord = command.substring(2, command.length());
                if (!dicTree.contains(newWord)) {
                    try {
                        FileWriter writer = new FileWriter(filePath, true);
                        writer.write(newWord + "\n");
                        writer.close();
                        System.out.println("\"" + newWord + "\" is added to the dictionary.");
                    } catch (IOException e) {
                        e.getMessage();
                    }
                } else {
                    System.out.println("\"" + newWord + "\" is already in the dictionary.");
                }
            } else if (command.startsWith("f")) {
                /**
                 * check file for spelling
                 */
                BufferedReader reader = null;
                try {
                    String fileName = command.substring(2, command.length());
                    System.out.println("Check the spell of " + fileName);
                    File file = new File("src/files/" + fileName);
                    reader = new BufferedReader(new FileReader(file));
                    String lineContents = null;
                    while ((lineContents = reader.readLine()) != null) {
                        lineContents = lineContents.replaceAll("[^a-zA-Z ]", ""); //Regular expression to remove punctuations.
                        String[] contents = lineContents.split(" ");
                        for (String content : contents) {
                            if (!dicTree.contains(content)) {
                                System.out.println("\"" + content + "\" is not in the dictionary. The closest word is \"" + dicTree.closeBy(content) + "\".");
                            }
                        }
                    }
                    reader.close();
                } catch (IOException e) {
                    e.getMessage();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e1) {
                            e1.getMessage();
                        }
                    }
                }
            } else {
                switch (command) {
                    case "p": {
                        System.out.println("RBT level order");
                        dicTree.levelOrderTraversal();
                        break;
                    }
                    case "s": {
                        System.out.println("RBT in order");
                        dicTree.inOrderTraversal();
                        break;
                    }
                    case "r": {
                        System.out.println("RBT reverse order");
                        dicTree.reverseOrderTraversal();
                        break;
                    }
                    default:;//If the user presses the "enter" button, do nothing but enter a new line with ">" as its beginning.
                }
            }
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
        return true;
    }
}
