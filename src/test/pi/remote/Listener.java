/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.pi.remote;

import akka.actor.UntypedActor;

public class Listener extends UntypedActor {

    @Override
    public void onReceive(Object message) {
        if (message instanceof PiApproximation) {

            printResult((PiApproximation) message);

            getContext().system().shutdown();
            
        } else {
            unhandled(message);
        }
    }

    private void printResult(PiApproximation approximation) {
        System.out.println(String.format("\n\tPi approximation: "
                + "\t\t%s\n\tCalculation time: \t%s",
                approximation.getPi(),
                approximation.getDuration()));
    }
}
