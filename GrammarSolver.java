package GrammarSolver;

import java.util.*;
public class GrammarSolver {
    private Map <String, String[]> map;

    public GrammarSolver (List<String> rules) {
        if (rules == null || rules.isEmpty()) throw new IllegalArgumentException();
        // create a map
        map = new TreeMap <>();

        for (String line : rules) {
            String[] parts = line.split ("::=");
            String[] rightRules = parts[1].split("\\|");

            // check to see if parts[0] is in the map,
            // throw exception if so
            if (map.containsKey(parts[0])) throw new IllegalArgumentException();

            map.put (parts[0], rightRules);
        }
    }

    // TODO: modify the code below

    /**
     * contains (symbol)
     * @param symbol String word (could be terminal or non-terminal)
     * @return true if the given symbol is a non-terminal in the grammar false otherwise
     */
    public boolean contains(String symbol) {
        if (symbol == null) throw new IllegalArgumentException();

        //check with mapKeySet (non-terminal = true)
        return map.containsKey(symbol);
    }

    /**
     * getSymbols()
     * @return the keySet of map as a sorted set of String
     */
    public Set<String> getSymbols () {
        return map.keySet();
    }

    /**
     * generate (symbol)
     * @param symbol a String
     * @return the randomly generated sentence or word
     */
    public String generate (String symbol) {
        String sentence = "";
        if (symbol == null) throw new IllegalArgumentException();

        //base case: terminal case, return the symbol
        if (!contains(symbol)){
            return symbol;
        }

        //recursive case: non-terminal case
        else{
            Random rand = new Random();
            String[] rule = map.get(symbol);

            //randomly choose the rule
            String grammar = rule[rand.nextInt(rule.length)];

            //split by space to get the non-terminal
            String[] parts = grammar.trim().split("\\s+");

            //get the word randomly for each non-terminal
            for (int i=0; i< parts.length; i++){
                sentence += generate(parts[i]) + " ";
            }
        }

        return sentence;
    }
}

