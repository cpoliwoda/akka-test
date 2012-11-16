/**
 * ORIGINAL CODE FROM:
 *  Copyright (C) 2009-2012 Typesafe Inc. <http://www.typesafe.com>
 * https://github.com/akka/akka/tree/v2.0.3/akka-samples/akka-sample-remote
 */
package test.calculator.remote.clientAdd;

import akka.actor.UntypedActor;
import test.calculator.remote.InternalMsg;
import test.calculator.remote.Op;

//#actor
/**
 * Client actor which redirects calculation requests/messages to a server actor
 * and prints the results(-messages) that where given by server actor to the command line.
 * 
 */
public class JLookupActor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {
      
        if (message instanceof InternalMsg.MathOpMsg) {
            
            InternalMsg.MathOpMsg msg = (InternalMsg.MathOpMsg) message;
            
            // send a message to server actor.
            // the informations
            // 1) which actor the server actor is, 
            // 2) which operation the server should do,
            // are stored in the message.
            msg.getActor().tell(msg.getMathOp(), getSelf());
            
        } else if (message instanceof Op.MathResult) {
          
            // START: receive a reply/result from server actor
            
            if (message instanceof Op.AddResult) {
                Op.AddResult result = (Op.AddResult) message;
                
                System.out.println("Add result: " + 
                        result.getN1() + " + " + result.getN2() + " = " + result.getResult());
                
            } else if (message instanceof Op.SubtractResult) {
                Op.SubtractResult result = (Op.SubtractResult) message;
                
                System.out.println("Sub result: " + 
                        result.getN1() + " - " + result.getN2() + " = " + result.getResult());
            }
            
            // END: receive a reply/result from server actor
            
        } else {
          unhandled(message);
        }
    }
}
//#actor
