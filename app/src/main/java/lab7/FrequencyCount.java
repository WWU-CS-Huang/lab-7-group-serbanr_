// Robert Serban
// Handles counting character frequency in the string and adds to map

package lab7;

import java.util.HashMap;
import java.util.Map;

public class FrequencyCount 
{
    public static Map<Character, Integer> count(String input)
    {
        Map<Character, Integer> freqMap = new HashMap<>();

        for (char c : input.toCharArray())
        {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        return freqMap;
    }    
}
