// Robert Serban


// Handles huffman tree and huffman node properties and methods
// Chose to use a java priority queue instead of heap for huffman handling

package lab7;

import java.util.*;

public class HuffmanTree 
{
    private Node root;


    // create node properties for huffman tree
    public static class Node implements Comparable<Node>
    {
        char character;
        int frequency;
        Node left, right;

        public Node(char character, int frequency)
        {
            this.character = character;
            this.frequency = frequency;
        }

        public Node(int frequency, Node left, Node right)
        {
            this.character = '\0';
            this.frequency = frequency;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Node o)
        {
            return Integer.compare(this.frequency, o.frequency);
        }

        public boolean isLeaf()
        {
            if (left == null && right == null)
            {
                return true;
            }
            return false;
        }
    }

    // Huffman tree construction using java priority queue
    public HuffmanTree(Map<Character, Integer> freqMap)
    {
        // initialize priority queue of node objects
        PriorityQueue<Node> queue = new PriorityQueue<>();
        for (var entry : freqMap.entrySet())
        {
            queue.add(new Node(entry.getKey(), entry.getValue()));
        }

        // actually build the tree
        while (queue.size() > 1)
        {
            Node left = queue.poll();
            Node right = queue.poll();
            Node merge = new Node(left.frequency + right.frequency, left, right);
            queue.add(merge);
        }

        // set last node as root
        root = queue.poll();
    }

    // Self explanatory :)
    public Node getRoot()
    {
        return root;
    }

    // Main call method for building the char to binary value map 
    public Map<Character, String> buildEncodeMap()
    {
        Map<Character, String> map = new HashMap<>();
        buildMap(root, "", map);
        return map;
    }

    // recursive helper method for building the char to binary value map
    private void buildMap(Node node, String path, Map<Character, String> map)
    {
        if (node == null)
        {
            return;
        }
        
        if (node.isLeaf())
        {
            map.put(node.character, path);
        }
        else
        {
            buildMap(node.left, path + "0", map);
            buildMap(node.right, path + "1", map);
        }
    }
}
