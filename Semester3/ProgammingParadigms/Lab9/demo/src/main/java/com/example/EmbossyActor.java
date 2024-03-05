package com.example;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

// ...

public class EmbossyActor extends AbstractActor {
    private int grapes = 0;
    private int grape_juice = 0;
    private static final int PROCESSING_TIME = 12 * 60;
    private static final double FAILURE_PROBABILITY = 0.0; 
    private static final int REQUEST_INTERVAL = 5000; 
    private final ActorRef warehouseActor;
    private final ActorRef fermentationActor;
    private boolean continueProcessing = true; 

    public static Props props(ActorRef warehouseActor, ActorRef fermentationActor) {
        return Props.create(EmbossyActor.class, () -> new EmbossyActor(warehouseActor, fermentationActor));
    }

    public EmbossyActor(ActorRef warehouseActor, ActorRef fermentationActor) {
        this.warehouseActor = warehouseActor;
        this.fermentationActor = fermentationActor;

        getContext().getSystem().scheduler().scheduleWithFixedDelay(
                Duration.Zero(), 
                Duration.create(REQUEST_INTERVAL, TimeUnit.MILLISECONDS), 
                getSelf(),
                new RequestGrapes(),
                getContext().getSystem().dispatcher(),
                getSelf()
        );
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(RequestGrapes.class, this::handleRequestGrapes)
                .match(ResourceMessage.class, this::handleResourceMessage)
                .match(ProductionResultMessage.class, this::processProductionResult)
                .match(StopProcessing.class, message -> continueProcessing = false) 
                .build();
    }

    private void handleRequestGrapes(RequestGrapes message) {
        System.out.println("EmbossyActor: Requesting grapes from the warehouse");
        warehouseActor.tell(new RequestGrapes(), getSelf());
        grapes += 10; 
    }

    private void handleResourceMessage(ResourceMessage message) {
        String resourceName = message.getResourceName();

        if (resourceName.equals("Grapes") && grapes > 10 && continueProcessing) {

            while (grapes >= 10 && continueProcessing) {
                System.out.println("EmbossyActor: Received grapes, simulating processing time");
                getContext().getSystem().scheduler().scheduleOnce(
                        Duration.create(PROCESSING_TIME, TimeUnit.MILLISECONDS),
                        getSelf(),
                        new ProductionResultMessage("GrapeJuice", 10),
                        getContext().getSystem().dispatcher(),
                        getSelf()
                );
                grape_juice ++;
                processProductionResult(new ProductionResultMessage("GrapeJuice", 10));
            }
        }
    }

    private void processProductionResult(ProductionResultMessage message) {
        if (grapes < 10) {
            if (!continueProcessing) {
                stopProcessing();
                return;
            }
            handleRequestGrapes(new RequestGrapes());

        }


        if (Math.random() > FAILURE_PROBABILITY && grape_juice > 0) {

    
           fermentationActor.tell(new FermentationActor.incrementJuice(), getSelf());
            System.out.println("EmbossyActor: Sending grape juice to the fermentation, grapes:" + grapes);
            grape_juice--;
        }

        getSender().tell(message, getSelf());
        grapes -= 10; 
    }
    

    private void stopProcessing() {
        System.out.println("EmbossyActor: Stopping processing loop");
        getContext().stop(getSelf());
    }

    static class RequestGrapes {
    }

    static class StopProcessing {
    }
}
