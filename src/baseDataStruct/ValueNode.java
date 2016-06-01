package baseDataStruct;

/**
 * Created by huangzhengyue on 5/29/16.
 */
public class ValueNode {

    private boolean isTerminal=false;

    private double[] qValue=new double[4];

    private int getMaxQvalueIndex(){
        if(isTerminal){
            return 0;
        }
        int maxQvalueIndex=0;
        double max=qValue[0];
        for(int i=1;i<4;i++){
            if(qValue[i] > max){
                max=qValue[i];
                maxQvalueIndex=i;
            }
        }
        return maxQvalueIndex;
    }

    //public:
    public ValueNode() {
        for(int i=0;i<4;i++){
            qValue[i]=0;
        }
    }


    public void setQvalue(Action action,double value){
        if(!this.isTerminal){
            qValue[action.ordinal()]=value;
        }
        else {
            qValue[0]=value;
            return;
        }
    }

    public void setTerminalValue(double value){
        this.isTerminal=true;
        qValue[0]=value;
    }

    public double getQvalue(Action action){
        if(!this.isTerminal){
            return qValue[action.ordinal()];
        }
        else {
            return qValue[0];
        }

    }
    public double[] getAllQValue(){
        return qValue.clone();
    }

    public double getValue() {
        return qValue[getMaxQvalueIndex()];
    }

    public Action getPolicy(){
       return Action.values()[getMaxQvalueIndex()];
    }


}
