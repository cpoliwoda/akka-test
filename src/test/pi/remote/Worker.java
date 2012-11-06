/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.pi.remote;

import akka.actor.UntypedActor;

public class Worker extends UntypedActor {

    private double calculatePiFor(int start, int nrOfElements) {
        double acc = 0.0;
        for (int i = start * nrOfElements; i <= ((start + 1) * nrOfElements - 1); i++) {
            acc += 4.0 * (1 - (i % 2) * 2) / (2 * i + 1);
        }
        return acc;
    }

    @Override
    public void onReceive(Object message) {

        // experiment start
        System.out.println(String.format("Received message '%s' in actor %s",
                message, getSelf().path().name()));
        // experiment end

        if (message instanceof Work) {
            
            Work work = (Work) message;
            double result = calculatePiFor(work.getStart(), work.getNrOfElements());
            
            getSender().tell(new Result(result), getSelf());
        } else {
            unhandled(message);
        }
    }
}
