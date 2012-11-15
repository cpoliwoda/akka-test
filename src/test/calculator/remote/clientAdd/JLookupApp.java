/**
 *  Copyright (C) 2009-2012 Typesafe Inc. <http://www.typesafe.com>
 * https://github.com/akka/akka/tree/v2.0.3/akka-samples/akka-sample-remote
 */
package test.calculator.remote.clientAdd;

import java.util.Random;
import test.calculator.remote.Op;

public class JLookupApp {
    
    public static void main(String[] args) {
        JLookupApplication app = new JLookupApplication();
        System.out.println("Started Lookup Application");
        
        Random r = new Random();
//        while (true) {
        for (int i = 0; i < 100; i++) {
            
            if (r.nextInt(100) % 2 == 0) {
                app.doSomething(new Op.Add(r.nextInt(100), r.nextInt(100)));    
            } else {
                app.doSomething(new Op.Subtract(r.nextInt(100), r.nextInt(100)));
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {               
            }
        }
        
        app.shutdown();
    }
}
