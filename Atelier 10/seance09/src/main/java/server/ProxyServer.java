package server;

import java.util.Scanner;

import blacklist.BlacklistService;
import domaine.Query;
import domaine.Query.QueryMethod;
import domaine.QueryFactory;

public class ProxyServer {
	
	QueryFactory queryFactory;
	BlacklistService blacklistService;

	public ProxyServer(QueryFactory queryFactory, BlacklistService blacklistService) {
		this.queryFactory = queryFactory;
		this.blacklistService = blacklistService;
	}
	
	public void startServer() {
		try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
				String url = scanner.nextLine();
				Query query = this.queryFactory.getQuery();
				query.setMethod(QueryMethod.GET);
				query.setUrl(url);
				QueryHandler queryHandler = new QueryHandler(query);
				queryHandler.start();
			}
		}
	}

}
