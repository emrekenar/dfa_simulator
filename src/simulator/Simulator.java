package simulator;

import dfa.DFA;
import dfa.State;
import dfa.Transition;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Simulator {

    public static void write(String result) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        writer.write(result);
        System.out.println(result);
        writer.close();
    }

    public dfa.DFA create() {
//        create a DFA using an input file
        DFA dfa = null;
        try {
            Scanner reader = new Scanner(new File("input.txt"));

            int n_states = this.read_first_number(reader);
            int n_variables = this.read_first_number(reader);
            int n_goal_states = this.read_first_number(reader);

            String[] state_names = this.read_list(reader, n_states);
            String[] goal_state_names = this.read_list(reader, n_goal_states);
            String[] variables_string = this.read_list(reader, n_variables);

//            there is a transition from each string with each variable in DFAs
            int n_transitions = n_states * n_variables;
            String[][] transition_names = this.read_transitions(reader, n_transitions);

            ArrayList<String> strings = this.read_strings(reader);

            reader.close();

//            convert string input into parameters
            State[] states = State.generate_states(state_names);
            State[] goal_states = State.generate_states(goal_state_names);
            char[] variables = new char[n_variables];
            for (int i = 0; i < n_variables; i++)
                variables[i] = variables_string[i].charAt(0);
            Transition[] transitions = Transition.generate_transitions(transition_names);

            dfa = new DFA(states, goal_states, variables, transitions, strings);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return dfa;
    }

    private int read_first_number(Scanner reader) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(reader.nextLine());
        if (m.find())
            return Integer.parseInt(m.group());
        return 0;
    }

    private String[] read_list(Scanner reader, int n) {
//        read a list of words of size n
        String[] list = new String[n];
        Pattern p = Pattern.compile("[a-zA-Z0-9]+");
        Matcher m = p.matcher(reader.nextLine());
        int i = 0;
        while (i++ < n && m.find())
            list[i - 1] = m.group();
        return list;
    }

    private String[][] read_transitions(Scanner reader, int n) {
        String[][] list = new String[n][3];
        for (int i = 0; i < n; ++i)
            list[i] = (this.read_list(reader, 3));
        return list;
    }

    private ArrayList<String> read_strings(Scanner reader) {
        ArrayList<String> list = new ArrayList<>();
        Pattern p = Pattern.compile("[a-zA-Z0-9]+");
        while (reader.hasNextLine()) {
            Matcher m = p.matcher(reader.nextLine());
            if (m.find())
                list.add(m.group());
        }
        return list;
    }
}
