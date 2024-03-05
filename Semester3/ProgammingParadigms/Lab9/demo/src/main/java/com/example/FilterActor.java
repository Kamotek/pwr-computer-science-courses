package com.example;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

class FilterActor extends AbstractActor {
    private static final int PROCESSING_TIME = 12 * 60;
    private int unfilteredWine;
    private int filteredWine;
    private final ActorRef bottlingActor;

    public static Props props() {
        return Props.create(FilterActor.class);
    }

    public static Props props(ActorRef bottlingActor) {
        return Props.create(FilterActor.class, () -> new FilterActor(bottlingActor));
    }

    public FilterActor(ActorRef bottlingActor) {
        this.bottlingActor = bottlingActor;
    }

    
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(ResourceMessage.class, this::handleResourceMessage)
                .match(RequestFilter.class, this::handleRequestFilter)
                .match(ProductionResultMessage.class, this::processProductionResult)
                .build();
    }

    private void handleResourceMessage(ResourceMessage message) {
        String resourceName = message.getResourceName();
        int quantity = message.getQuantity();


        System.out.println("FilterActor: Received resource message, unfiltered wine: " + quantity);
        if (resourceName.equals("UnfilteredWine") && quantity == 25) {
            getContext().getSystem().scheduler().scheduleOnce(
                    Duration.create(PROCESSING_TIME, TimeUnit.MILLISECONDS),
                    getSelf(),
                    new ProductionResultMessage("Wine", 24),
                    getContext().getSystem().dispatcher(),
                    getSender()
            );
            processProductionResult(new ProductionResultMessage("Filter", 24));
        } else {
            unhandled(message);
        }
    }

    private void processProductionResult(ProductionResultMessage message) {
        if(unfilteredWine >= 25) {
            unfilteredWine =- 25;
            System.out.println("FilterActor: Sending filtered wine to bottling");
            bottlingActor.tell(new ResourceMessage("Wine", 24), getSelf());
        }

        getSender().tell(message, getSelf());
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

    public void handleRequestFilter(RequestFilter message) {
        unfilteredWine += 1;
        handleResourceMessage(new ResourceMessage("UnfilteredWine", 1));
    }

    static class RequestFilter {
    }


}
