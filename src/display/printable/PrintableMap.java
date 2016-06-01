package display.printable;

import baseDataStruct.Action;
import baseDataStruct.BlockState;
import baseDataStruct.Vect2;

/**
 * Created by huangzhengyue on 5/31/16.
 */
public class PrintableMap {
    private int width;
    private int height;
    private Vect2 currentPos;
    private PrintableNode[][] printableNodes;


    public PrintableMap(int width, int height, Vect2 currentPos, PrintableNode[][] printableNodes) {
        this.width = width;
        this.height = height;
        this.currentPos = currentPos;
        this.printableNodes = printableNodes;
    }


    public BlockState getBlockState(int x,int y) {
        return printableNodes[x][y].getBlockState();
    }

    public double[] getAllQValue(int x,int y) {
        return printableNodes[x][y].getAllQValue();
    }

    public double getQValue(int x,int y,Action action){
        return printableNodes[x][y].getQValue(action);
    }

    public double getValue(int x,int y) {
        return printableNodes[x][y].getValue();
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Vect2 getCurrentPos() {
        return currentPos;
    }

    public Action getPolicy(int x,int y){
        return printableNodes[x][y].getPolicy();
    }
    public Action getPolicy(Vect2 pos){
        return printableNodes[pos.getX()][pos.getY()].getPolicy();
    }

}
