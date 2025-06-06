// Robert Serban
// Handles encoding and decoding tasks

package lab7;

import java.util.Map;

public class Codec 
{
    // Encodes character String to a bitstring
    public static String encode(String input, Map<Character, String> encodingMap)
    {
        StringBuilder encoded = new StringBuilder(); 
        for (char c : input.toCharArray())
        {
            encoded.append(encodingMap.get(c));
        }
        return encoded.toString();
    }

    // Decodes bitstring back to a character String
    public static String decode(String bitString, HuffmanTree.Node root)
    {
        StringBuilder decoded = new StringBuilder();
        HuffmanTree.Node current = root;

        for (char bit : bitString.toCharArray())
        {
            if (bit == '0')
            {
                current = current.left;
            }

            else
            {
                current = current.right;
            }
            
            if (current.isLeaf())
            {
                decoded.append(current.character);
                current = root;
            }
        }
        return decoded.toString();
    }
}
