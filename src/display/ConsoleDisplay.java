package display;

import baseDataStruct.BlockState;
import display.printable.PrintableMap;

/**
 * Created by huangzhengyue on 5/30/16.
 */
public class ConsoleDisplay implements BoardDisplay {
    private char[] policyChar={'^','>','v','<'};

    @Override
    public void printValueMap(PrintableMap printableMap) {
        int width=printableMap.getWidth();
        int height=printableMap.getHeight();
        int currentX=printableMap.getCurrentPos().getX();
        int currentY=printableMap.getCurrentPos().getY();
        char temp=' ';
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width ; j++) {
                if(printableMap.getBlockState(i,j)== BlockState.OBSTACLE){
                    System.out.printf("   #   |");
                    continue;
                }
                if(currentX==i && currentY==j){
                    temp='@';
                }
                else {
                    temp=' ';
                }
                System.out.printf("%5.2f%c%c|",printableMap.getValue(i,j),temp,policyChar[printableMap.getPolicy(i,j).ordinal()]);



            }
            System.out.println("");
        }
    }

    @Override
    public void printQValueMap(PrintableMap printableMap) {

    }
}
