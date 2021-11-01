package dfa;

public class Transition {
    public State state1;
    public char variable;
    public State state2;

    public Transition(State state1, char variable, State state2) {
        this.state1 = state1;
        this.variable = variable;
        this.state2 = state2;
    }

    public static Transition[] generate_transitions(String[][] transition_names) {
        int n = transition_names.length;
        Transition[] list = new Transition[n];
        for (int i = 0; i < n; i++) {
            State state1 = new State(transition_names[i][0]);
            char variable = transition_names[i][1].charAt(0);
            State state2 = new State(transition_names[i][2]);
            list[i] = new Transition(state1, variable, state2);
        }
        return list;
    }

    @Override
    public String toString() {
        return this.state1 + "->" + this.variable + "->" + this.state2;
    }
}
