package org.redfx.javaqc.ch06.classiccopy;

public class Main {

    static Boolean makeCopy(Boolean source) {
        Boolean target;
        if (source == true) {
            target = new Boolean(true);
        } else {
            target = new Boolean(false);
        }
        return target;
    }

    public static void main(String[] args) {
      Boolean trueSource = Boolean.TRUE;
      Boolean trueCopy = makeCopy(trueSource);
      System.err.println("Source: "+trueSource+" and copy : "+trueCopy);

      Boolean falseSource = Boolean.FALSE;
      Boolean falseCopy = makeCopy(falseSource);
      System.err.println("Source: "+falseSource+" and copy : "+falseCopy);
    }

}
