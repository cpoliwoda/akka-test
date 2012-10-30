/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.counter;

import akka.actor.UntypedActor;

 public class Counter extends UntypedActor {
        int count = 0;

     @Override
        public void onReceive(Object message) throws Exception {
            if ("tick".equals(message)) {
                count++;
            }
            else if ("get".equals(message)) {
                getSender().tell(count);
            }
            else {
                unhandled(message);
            }
        }
    }

