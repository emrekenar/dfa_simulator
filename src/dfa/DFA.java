package dfa;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DFA {
    public State[] states;                                              // Q
    public State[] goal_states;                                         // F
    public char[] variables;                                            // sigma
    public HashMap<State, HashMap<Character, State>> transition_table;  // delta
    public ArrayList<String> strings;                                   // input
    public State initial_state;                                         // q0
    public State current_state;
    public ArrayList<State> route;
    public String result = "";

    public DFA(State[] states, State[] goal_states, char[] variables, Transition[] transitions, ArrayList<String> strings) {
        this.states = states;
        this.goal_states = goal_states;
        this.variables = variables;
        this.transition_table = new TransitionTable(transitions);
        this.strings = strings;
        this.initial_state = states[0];  // assume that the initial state is the state that's given first
        this.current_state = this.initial_state;
        this.route = new ArrayList<>();
    }

    public void transit(char variable) {
        this.current_state = ((this.transition_table).get(this.current_state)).get(variable);
    }

    public boolean run(String string) {
        for (char variable : string.toCharArray()) {
            this.transit(variable);
            this.route.add(this.current_state);
        }
        boolean accepted = Arrays.asList(this.goal_states).contains(this.current_state);
        this.print_result(accepted);
        this.reset();
        return accepted;
    }

    public boolean run_next() {
        boolean accepted = this.run(this.strings.get(0));
        this.strings.remove(0);
        return accepted;
    }

    public boolean run_all() throws IOException {
        boolean accepted = true;
        while (!this.strings.isEmpty())
            accepted = accepted && this.run_next();
        return accepted;
    }

    public void print_result(boolean accepted) {
        String result = "";

        for (State state : this.route)
            result += state.name + " ";
        result += "(route taken)\n";

        if (accepted)
            result += "Accepted\n";
        else
            result += "Rejected\n";

        this.result += result;
    }

    public void reset() {
        this.current_state = this.initial_state;
        this.route = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "DFA{" +
                "Q=" + Arrays.toString(states) +
                ", q0=" + initial_state +
                ", F=" + Arrays.toString(goal_states) +
                ", sigma=" + Arrays.toString(variables) +
                ", delta=" + transition_table +
                '}';
    }
}
