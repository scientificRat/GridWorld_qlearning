package display.printable;

import baseDataStruct.Action;
import baseDataStruct.BlockState;

/**
 * Created by huangzhengyue on 5/31/16.
 */
public class PrintableNode {
    private BlockState blockState;
    private double[] qValue;
    private double value;
    private Action policy;

    public BlockState getBlockState() {
        return blockState;
    }

    public double[] getAllQValue() {
        return qValue.clone();
    }

    public double getQValue(Action action){
        return qValue[action.ordinal()];
    }

    public double getValue() {
        return value;
    }

    public Action getPolicy() {
        return policy;
    }

    public PrintableNode setBlockState(BlockState blockState) {
        this.blockState = blockState;
        return this;
    }

    public PrintableNode setqValue(double[] qValue) {
        this.qValue = qValue;
        return this;
    }

    public PrintableNode setValue(double value) {
        this.value = value;
        return this;
    }

    public PrintableNode setPolicy(Action policy) {
        this.policy = policy;
        return this;
    }




}
