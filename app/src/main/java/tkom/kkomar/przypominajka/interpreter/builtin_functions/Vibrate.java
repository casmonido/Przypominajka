package tkom.kkomar.przypominajka.interpreter.builtin_functions;

import tkom.kkomar.przypominajka.android.ChooseAlarm;
import tkom.kkomar.przypominajka.interpreter.Environment;
import tkom.kkomar.przypominajka.interpreter.TypedValue;
import tkom.kkomar.przypominajka.interpreter.nodes.Node;

/**
 * Created by casmonido on 22.01.2018.
 */

public class Vibrate implements Node {

    @Override
    public TypedValue evalNode(Environment env) throws RuntimeException {
        //ChooseAlarm.mVibrator.vibrate(new long[] {0, 1000, 1000}, 4);
        ChooseAlarm.mVibrator.vibrate(1000*5);
        return null;
    }
}