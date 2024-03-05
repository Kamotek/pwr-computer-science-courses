package com.example;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

import com.example.EmbossyActor.RequestGrapes;

class FermentationActor extends AbstractActor {
    private int grape_juice;
    private int water;
    private int sugar;
    private static final int PROCESSING_TIME = 14 * 24 * 60;
    private static final double FAILURE_PROBABILITY = 0.05; 
    private static final double REQUEST_INTERVAL = 1000;
    private final ActorRef warehouseActor;
    private final ActorRef filterActor;


    public static Props props() {
        return Props.create(FermentationActor.class);
    }

    public FermentationActor(ActorRef warehouseActor, ActorRef filterActor) {
        this.warehouseActor = warehouseActor;
        this.filterActor = filterActor;

                getContext().getSystem().scheduler().scheduleWithFixedDelay(
                Duration.Zero(), // Initial delay
                (FiniteDuration) Duration.create(REQUEST_INTERVAL, TimeUnit.MILLISECONDS), 
                getSelf(),
                new incrementJuice(),
                getContext().getSystem().dispatcher(),
                getSelf()
        );
        
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(ResourceMessage.class, this::handleResourceMessage)
                .match(ProductionResultMessage.class, this::processProductionResult)
                .match(incrementJuice.class, this::handleIncrementJuice)  
                .match(RequestWater.class, this::handleRequestWater)
                .match(RequestSugar.class, this::handleRequestSugar)
    
                .build();
    }

    static class incrementJuice {}

    public void handleIncrementJuice(incrementJuice message) {
        grape_juice += 1;
        handleResourceMessage(null);
    }

    private void handleResourceMessage(ResourceMessage message) {

        System.out.println("FermentationActor: Received resource message");

        if(water < 8) {
            handleRequestWater(new RequestWater());
            water++;
        }

        if(sugar < 2) {
            handleRequestSugar(new RequestSugar());
            sugar++;
        }

        if (water >= 8 && sugar >= 2 && grape_juice >= 5) {

            System.out.println("FermentationActor: Received grape juice, simulating processing time");
            getContext().getSystem().scheduler().scheduleOnce(
                    Duration.create(PROCESSING_TIME, TimeUnit.MILLISECONDS),
                    getSelf(),
                    new ProductionResultMessage("UnfilteredWine", 25),
                    getContext().getSystem().dispatcher(),
                    getSender()
            );
            processProductionResult(new ProductionResultMessage("UnfilteredWine", PROCESSING_TIME));
        } else {
            unhandled(message);
        }
    }

    private void processProductionResult(ProductionResultMessage message) {


        if (Math.random() > FAILURE_PROBABILITY && grape_juice == 15) {

    
             filterActor.tell(new FilterActor.RequestFilter(), getSelf());
             System.out.println("FermenationActor: Sending unfiltered wine to the filtration: " + grape_juice + " grape juic ");
             grape_juice =- 15;
             water =- 8;
             sugar =- 2;
         }


        getSender().tell(message, getSelf());
    }


    private void handleRequestWater(RequestWater message) {
        System.out.println("FermentationActor: Requesting water from the warehouse");
        warehouseActor.tell(new RequestWater(), getSelf());
        water += 8; 
    }

    private void handleRequestSugar(RequestSugar message) {
        System.out.println("FermentationActor: Requesting sugar from the warehouse");
        warehouseActor.tell(new RequestSugar(), getSelf());
        sugar += 2; 
    }

    

    static class ProductionResultMessage {
        private final String productName;
        private final int quantity;

        public ProductionResultMessage(String productName, int quantity) {
            this.productName = productName;
            this.quantity = quantity;
        }

        public String getProductName() {
            return productName;
        }

        public int getQuantity() {
            return quantity;
        }
    }

    public static Props props(ActorRef warehouseActor, ActorRef filterActor) {
        return Props.create(FermentationActor.class, () -> new FermentationActor(warehouseActor, filterActor));
    }
}
