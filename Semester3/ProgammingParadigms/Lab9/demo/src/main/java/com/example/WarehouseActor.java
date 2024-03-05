// WarehouseActor
package com.example;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;
import java.util.Map;

public class WarehouseActor extends AbstractActor {
    private Map<String, Integer> availableResources = new HashMap<>();

    public static Props props() {
        return Props.create(WarehouseActor.class);
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(ResourceMessage.class, this::handleResourceMessage)
                .match(ProductMessage.class, this::handleProductMessage)
                .match(InitializeWarehouse.class, this::initializeWarehouse)
                .match(EmbossyActor.RequestGrapes.class, this::handleRequestGrapes)
                .match(RequestWater.class, this::handleRequestWater)
                .match(RequestSugar.class, this::handleRequestSugar)
                .match(RequestBottles.class, this::handleRequestBottles)
                .build();
    }

    private void handleResourceMessage(ResourceMessage message) {
        String resourceName = message.getResourceName();
        int quantity = message.getQuantity();

        availableResources.put(resourceName, availableResources.getOrDefault(resourceName, 0) + quantity);
    }

    private void handleProductMessage(ProductMessage message) {
        String productName = message.getProductName();
        int quantity = message.getQuantity();

        availableResources.put(productName, availableResources.getOrDefault(productName, 0) + quantity);
    }

    private void initializeWarehouse(InitializeWarehouse message) {
        availableResources.put("Grapes", 15000);
        availableResources.put("Water", 2000);
        availableResources.put("Sugar", 500);
        availableResources.put("Bottle", 100);
    }


    private void handleRequestWater(RequestWater message) {
        int requestedQuantity = 20; // Adjust the quantity as needed
        if (availableResources.containsKey("Water") && availableResources.get("Water") >= requestedQuantity) {
            getSender().tell(new ResourceMessage("Water", requestedQuantity), getSelf());
            availableResources.put("Water", availableResources.get("Water") - requestedQuantity);
        } else {
            getSender().tell(new ResourceMessage("Water", 0), getSelf());
            System.out.println("WarehouseActor: Insufficient water, sending 0 water to the EmbossyActor");
            getSender().tell(new EmbossyActor.StopProcessing(), getSelf());
        }
        System.out.println("WarehouseActor: Sending water to the EmbossyActor");
    }

    private void handleRequestSugar(RequestSugar message) {
        int requestedQuantity = 5; 
        if (availableResources.containsKey("Sugar") && availableResources.get("Sugar") >= requestedQuantity) {
            getSender().tell(new ResourceMessage("Sugar", requestedQuantity), getSelf());
            availableResources.put("Sugar", availableResources.get("Sugar") - requestedQuantity);
        } else {
            getSender().tell(new ResourceMessage("Sugar", 0), getSelf());
            System.out.println("WarehouseActor: Insufficient sugar, sending 0 sugar to the EmbossyActor");
            getSender().tell(new EmbossyActor.StopProcessing(), getSelf());
        }
        System.out.println("WarehouseActor: Sending sugar to the EmbossyActor");
    }

    private void handleRequestBottles(RequestBottles message) {
        
        int requestedQuantity = 2; 
        if (availableResources.containsKey("Bottle") && availableResources.get("Bottle") >= requestedQuantity) {
            getSender().tell(new ResourceMessage("Bottle", requestedQuantity), getSelf());
            availableResources.put("Bottle", availableResources.get("Bottle") - requestedQuantity);
        } else {
            getSender().tell(new ResourceMessage("Bottle", 0), getSelf());
            System.out.println("WarehouseActor: Insufficient bottles, sending 0 bottles to the EmbossyActor");
            getSender().tell(new EmbossyActor.StopProcessing(), getSelf());
        }
        System.out.println("WarehouseActor: Sending bottles to the EmbossyActor");
    }

    private void handleRequestGrapes(EmbossyActor.RequestGrapes message) {
        int requestedQuantity = 10; 
        if (availableResources.containsKey("Grapes") && availableResources.get("Grapes") >= requestedQuantity) {
            getSender().tell(new ResourceMessage("Grapes", requestedQuantity), getSelf());
            availableResources.put("Grapes", availableResources.get("Grapes") - requestedQuantity);
        } else {
            getSender().tell(new ResourceMessage("Grapes", 0), getSelf());
            System.out.println("WarehouseActor: Insufficient grapes, sending 0 grapes to the EmbossyActor");
            getSender().tell(new EmbossyActor.StopProcessing(), getSelf());
        }
        System.out.println("WarehouseActor: Sending grapes to the EmbossyActor");
    }



    static class InitializeWarehouse {
    }
}
