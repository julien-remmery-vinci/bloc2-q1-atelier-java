package server;

import domaine.Query;
import domaine.QueryFactory;

import java.util.Scanner;

public class ProxyServer {
    QueryFactory factory;

    public ProxyServer(QueryFactory factory) {
        this.factory = factory;
    }

    public void startServer(){
        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Entrer une url: ");
            String adresse = scanner.next();
            Query query = QueryFactory.getQuery();
            query.setAdresse(adresse);
            QueryHandler queryHandler = new QueryHandler(query);
            queryHandler.get();
        }
    }
}
