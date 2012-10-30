/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.pi;

import akka.actor.UntypedActor;

public class Listener extends UntypedActor {

    @Override
        public void onReceive(Object message) {
            if (message instanceof PiApproximation) {
                PiApproximation approximation = (PiApproximation) message;
                System.out.println(String.format("\n\tPi approximation: \t\t%s\n\tCalculation time: \t%s",
                        approximation.getPi(), approximation.getDuration()));
                getContext().system().shutdown();
            } else {
                unhandled(message);
            }
        }
    }
