package com.example;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

class BottlingActor extends AbstractActor {
    private static int empty_bottles;
    private static int full_bottles;
    private double filtered_wine;
    private static final int PROCESSING_TIME = 5;
    private static final double FAILURE_PROBABILITY = 0.05; 
    private final ActorRef warehouseActor;

    public static Props props() {
        return Props.create(BottlingActor.class);
    }

    public static Props props(ActorRef warehouseActor) {
        return Props.create(BottlingActor.class, () -> new BottlingActor(warehouseActor));
    }

    public BottlingActor(ActorRef warehouseActor) {
        this.warehouseActor = warehouseActor;
        
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(ResourceMessage.class, this::handleResourceMessage)
                .match(RequestBottles.class, this::handleRequestBottles)
                .match(ProductionResultMessage.class, this::processProductionResult)

                .build();
    }

    private void handleResourceMessage(ResourceMessage message) {
        String resourceName = message.getResourceName();
        int quantity = message.getQuantity();

        if(resourceName.equals("Bottle")){
               empty_bottles += quantity;
        }

        if(resourceName.equals("Wine")){
            filtered_wine += quantity;
        }


        if (resourceName.equals("Wine") && quantity >= 0.75
                && resourceName.equals("Bottle") && quantity >= 1) {
            getContext().getSystem().scheduler().scheduleOnce(
                    Duration.create(PROCESSING_TIME, TimeUnit.MILLISECONDS),
                    getSelf(),
                    new ProductionResultMessage("BottleOfWine", 1),
                    getContext().getSystem().dispatcher(),
                    getSender()
            );
            processProductionResult(new ProductionResultMessage("BottleOfWine", 1));
        } else {
            unhandled(message);
        }
    }

    private void processProductionResult(ProductionResultMessage message) {
        
        if(filtered_wine >= 0.75 && empty_bottles >= 1) {
            filtered_wine -= 0.75;
            empty_bottles -= 1;
            full_bottles += 1;
            System.out.println("BottlingActor: Sending bottle of wine to warehouse");
            warehouseActor.tell(new ResourceMessage("BottleOfWine", 1), getSelf());
        }

        getSender().tell(message, getSelf());
    }

    private void handleRequestBottles(RequestBottles message) {
        System.out.println("BottlingActor: Requesting bottles from the warehouse");
        warehouseActor.tell(new RequestBottles(), getSelf());
        empty_bottles += 10; 
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

    static class RequestBottles {
    }

}
