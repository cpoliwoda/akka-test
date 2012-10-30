/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.actors;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 *
 * @author Christian Poliwoda <christian.poliwoda@gcsc.uni-frankfurt.de>
 */
public class GreetingActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public void onReceive(Object message) throws Exception {
        
        if (message instanceof Greeting) {
            log.info("Hello " + ((Greeting) message).who);
        }
    }
}
