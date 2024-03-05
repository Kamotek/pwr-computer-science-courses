package com.example;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class Main {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("ProductionSystem");

        ActorRef warehouseActor = system.actorOf(WarehouseActor.props(), "warehouseActor");
        ActorRef bottlingActor = system.actorOf(BottlingActor.props(warehouseActor), "bottlingActor");
        ActorRef filterActor = system.actorOf(FilterActor.props(bottlingActor), "filterActor");
        ActorRef fermentationActor = system.actorOf(FermentationActor.props(warehouseActor, filterActor), "fermentationActor");
        ActorRef embossyActor = system.actorOf(EmbossyActor.props(warehouseActor, fermentationActor), "embossyActor");
        
        warehouseActor.tell(new WarehouseActor.InitializeWarehouse(), ActorRef.noSender());

        embossyActor.tell(new EmbossyActor.RequestGrapes(), ActorRef.noSender());

    }
}
