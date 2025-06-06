// Robert Serban
// Handles huffman tree and huffman node properties and methods

package lab7;

import java.util.*;

public class HuffmanTree 
{
    private Node root;


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

    public HuffmanTree(Map<Character, Integer> freqMap)
    {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        for (var entry : freqMap.entrySet())
        {
            queue.add(new Node(entry.getKey(), entry.getValue()));
        }

        while (queue.size() > 1)
        {
            Node left = queue.poll();
            Node right = queue.poll();
            Node merge = new Node(left.frequency + right.frequency, left, right);
            queue.add(merge);
        }

        root = queue.poll();
    }

    public Node getRoot()
    {
        return root;
    }

    public Map<Character, String> buildEncodeMap()
    {
        Map<Character, String> map = new HashMap<>();
        buildMap(root, "", map);
        return map;
    }

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
