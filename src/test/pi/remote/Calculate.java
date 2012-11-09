/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.pi.remote;

import akka.actor.ActorRef;

/**
 *
 * @author Christian Poliwoda <christian.poliwoda@gcsc.uni-frankfurt.de>
 */
public class Calculate {

    final int nrOfWorkers;
    final int nrOfElements;
    final int nrOfMessages;
    final ActorRef listener;

    public Calculate(int nrOfWorkers, int nrOfElements, int nrOfMessages, ActorRef listener) {
        this.nrOfWorkers = nrOfWorkers;
        this.nrOfElements = nrOfElements;
        this.nrOfMessages = nrOfMessages;
        this.listener = listener;
    }
}
