package dfa;

import java.util.HashMap;
import java.util.Map;

public class TransitionTable extends HashMap<State, HashMap<Character, State>> {

    public TransitionTable(Transition[] transitions) {
        super();
        for (Transition t : transitions) {

//            create a hashmap for each state
            if (!this.containsKey(t.state1))
                this.put(t.state1, new HashMap<>());

//            put each variable to the hashmap of each state
            this.get(t.state1).put(t.variable, t.state2);
        }
    }

    @Override
    public String toString() {
        String string = "{";
        for (Map.Entry<State, HashMap<Character, State>> entry1 : this.entrySet()) {
            State state1 = entry1.getKey();
            for (Map.Entry<Character, State> entry2 : entry1.getValue().entrySet()) {
                char variable = entry2.getKey();
                State state2 = entry2.getValue();
                string += state1 + "->" + variable + "->" + state2 + ", ";
            }
        }
        string = string.substring(0, string.length() - 2) + "}";
        return string;
    }
}
