package tkom.kkomar.przypominajka.interpreter.builtin_functions;

import tkom.kkomar.przypominajka.interpreter.Environment;
import tkom.kkomar.przypominajka.interpreter.TypedValue;
import tkom.kkomar.przypominajka.interpreter.nodes.Node;

import static java.lang.Thread.sleep;


    public class Sleep implements Node {

        @Override
        public TypedValue evalNode(Environment env) throws RuntimeException {
            try {
                sleep(5);
            } catch (InterruptedException ie)
            {} // not like it was critical
            return null;
        }
    }