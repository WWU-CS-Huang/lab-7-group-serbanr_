// Robert Serban


// Main Huffman class; handles scanning text file and calling to other classes for encoding, decoding, tree building, etc
// Did solo because couldn't find a partner, if that means a 0 oh well :P

package lab7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;


public class Huffman 
{
    // Handles main program run of passing in text file
    // and calling to other huffman methods

    public static void main(String[] args)
    {
        // Error handling for file input------------------------>
        if (args.length < 1)
        {
            System.err.println("Please enter a filename!");
            System.exit(1);
        }

        String file = args[0];
        StringBuilder input = new StringBuilder();

        try
        {
            Scanner scanner = new Scanner(new File(file));
            while (scanner.hasNextLine())
            {
                input.append(scanner.nextLine()).append("\n");
            }
        }
        catch (FileNotFoundException e)
        {
            System.err.println("File " + file + " not present in lab7 directory!");
            return;
        }
        // Error handling for file input------------------------>


        // Make a character count map of the resulting string
        String fileText = input.toString(); 
        Map<Character, Integer> frequencies = FrequencyCount.count(fileText);

        // Create a new huffman tree and populate it 
        HuffmanTree tree = new HuffmanTree(frequencies);
        Map<Character, String> encoderMap = tree.buildEncodeMap();

        // Get encoded and decoded strings
        String encoded = Codec.encode(fileText, encoderMap);
        String decoded = Codec.decode(encoded, tree.getRoot());


        // handling for less than 100 words case
        if (input.length() < 100)
        {
            System.out.println("Input: " + input);
            System.out.println("Encoded bitstring: " + encoded);
            System.out.println("Decoded String: " + decoded);
        }


        // Check if the decoded string matches the original string
        boolean match = fileText.equals(decoded);
        System.out.println("Decoded matches input: " + match);

        //Calculate compression ratio
        double compressionRatio = (double) encoded.length() / (fileText.length() * 8.0);
        System.out.println("Compression ratio: " + compressionRatio);


        
    }
}
