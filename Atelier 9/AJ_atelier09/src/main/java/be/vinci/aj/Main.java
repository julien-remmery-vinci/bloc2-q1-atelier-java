package be.vinci.aj;

import domaine.QueryFactory;
import server.ProxyServer;

public class Main {
    public static void main(String[] args) {
        ProxyServer proxyServer = new ProxyServer(new QueryFactory());
        proxyServer.startServer();
    }
}