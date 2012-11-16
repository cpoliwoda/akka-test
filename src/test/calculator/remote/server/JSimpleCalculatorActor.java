/**
 * ORIGINAL CODE FROM:
 *  Copyright (C) 2009-2012 Typesafe Inc. <http://www.typesafe.com>
 * https://github.com/akka/akka/tree/v2.0.3/akka-samples/akka-sample-remote
 */
package test.calculator.remote.server;

import akka.actor.UntypedActor;
import test.calculator.remote.Op;

//#actor
/**
 * Server actor that responds to request messages from clients with
 * result messages.
 * 
 * If the server system is not running before the client system is starting
 * messages will be send to the supposed address of the server system but without 
 * reply (nothing happens).
 * If the server would be started during the sending of messages the server will
 * start to respond to the CURRENT incomming messages. The messages that was sended
 * before want be noticed/replied.
 */
public class JSimpleCalculatorActor extends UntypedActor {
    
  @Override
  public void onReceive(Object message) {

    if (message instanceof Op.Add) {
      Op.Add add = (Op.Add) message;
      
      System.out.println("Calculating " + add.getN1() + " + " + add.getN2());
      
      // responds to the sender of the message with a NEW message that contains
      // the original send parameters, the result of the calculated operation
      // and a reference to the server actor (getSelf) that calculated the result
      getSender().tell( new Op.AddResult(
              add.getN1(), add.getN2(), add.getN1() + add.getN2()),
              getSelf());

    } else if (message instanceof Op.Subtract) {
      Op.Subtract subtract = (Op.Subtract) message;
      
      System.out.println("Calculating " + subtract.getN1() + " - " + subtract.getN2());
      
      // respond to the sender of the message with a NEW message that contains
      // the parameters and the result of the calculated operation
      // and reference to the actor (getSelf) that calculated the result
      getSender().tell( new Op.SubtractResult(
              subtract.getN1(), subtract.getN2(), subtract.getN1() - subtract.getN2()),
              getSelf());

    } else {
      unhandled(message);
    }
  }
}
// #actor
