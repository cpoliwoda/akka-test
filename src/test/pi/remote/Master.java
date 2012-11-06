/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.pi.remote;

import akka.actor.ActorPath;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinRouter;
import akka.util.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Master extends UntypedActor {

    private final int nrOfMessages;
    private final int nrOfElements;
    private double pi;
    private int nrOfResults;
    private final long start = System.currentTimeMillis();
    private final ActorRef listener;
    private final ActorRef workerRouter;

    public Master(final int nrOfWorkers, int nrOfMessages, int nrOfElements, ActorRef listener) {
        this.nrOfMessages = nrOfMessages;
        this.nrOfElements = nrOfElements;
        this.listener = listener;

        workerRouter = createWorkerRouter(nrOfWorkers);

        // experiment start (not needed)
        for (int i = 0; i < nrOfWorkers; i++) {
            workerRouter.tell(i, getSelf());
        }
        // experiment end
    }

    @Override
    public void onReceive(Object message) {
        if (message instanceof Calculate) {

            createWorkers();

        } else if (message instanceof Result) {

            collectResults((Result) message);

        } else {
            unhandled(message);
        }
    }

    private ActorRef createWorkerRouter(final int nrOfWorkers) {

        // experiment start
        ArrayList<ActorRef> routees = new ArrayList<ActorRef>();

        String name = "actorname-";
        for (int i = 0; i < nrOfWorkers; i++) {
            routees.add(
                    getContext().actorOf(new Props(Worker.class), name+i));
        }
        
        ArrayList<ActorPath> routeesPath = new ArrayList<ActorPath>();
        for (int i = 0; i < routees.size(); i++) {
            routeesPath.add(routees.get(i).path());  
        }
        
        ArrayList<String> routeesPathString = new ArrayList<String>();
        for (int i = 0; i < routees.size(); i++) {
            routeesPathString.add(routees.get(i).path().toString());  
        }
        
        return this.getContext().actorOf(
                new Props(Worker.class).withRouter(new RoundRobinRouter(routeesPathString)),
                "workerRouter");
        // experiment end

//        return this.getContext().actorOf(
//                new Props(Worker.class).withRouter(new RoundRobinRouter(nrOfWorkers)),
//                "workerRouter");
    }

    private void createWorkers() {
        for (int i = 0; i < nrOfMessages; i++) {
            workerRouter.tell(new Work(i, nrOfElements), getSelf());
        }
    }

    private void collectResults(Result result) {
        pi += result.getValue();
        nrOfResults += 1;

        if (nrOfResults == nrOfMessages) {

            sendResultToListener();

            // Stops this actor and all its supervised children
            getContext().stop(getSelf());
        }
    }

    private void sendResultToListener() {
        // Send the result to the listener
        Duration duration = Duration.create(System.currentTimeMillis() - start, TimeUnit.MILLISECONDS);
        listener.tell(new PiApproximation(pi, duration), getSelf());
    }
}
