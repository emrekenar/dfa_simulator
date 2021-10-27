package simulator;

import dfa.DFA;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        DFA dfa = new Simulator().create();
        dfa.run_all();
        Simulator.write(dfa.result);
    }
}
