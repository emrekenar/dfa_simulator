package dfa;

import java.util.Objects;

public class State {
    public String name;

    public State(String name) {
        this.name = name;
    }

    public static State[] generate_states(String[] state_names) {
        int n = state_names.length;
        State[] list = new State[n];
        for (int i = 0; i < n; i++)
            list[i] = new State(state_names[i]);
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(name, state.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
