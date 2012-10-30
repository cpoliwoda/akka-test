/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.onMessageReceive;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class MyUntypedActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object message) throws Exception {
        
        if (message instanceof String) {
            log.info("\n Received String message: {}", message);
        } else {
            unhandled(message);
        }

    }
}
