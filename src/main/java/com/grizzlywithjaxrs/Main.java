package com.grizzlywithjaxrs;

import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpContainerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.grizzlywithjaxrs.config.ServerConfig;

public class Main
{
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	
	private static boolean initializeServices()
	{
		if (ServerConfig.get() == null)
		{
			return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) throws Exception
	{
		if (!initializeServices())
		{
			return;
		}
		
		
		HttpServer server = HttpServer.createSimpleServer("/", ServerConfig.get().serverPort());
		
		// api
		HttpHandler apiHandler = new GrizzlyHttpContainerProvider()
				.createContainer(HttpHandler.class, JaxRsApiResourceConfig.create());
		server.getServerConfiguration().addHttpHandler(apiHandler, "/api");
		
		// shutdown hook
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				server.shutdownNow();
			}
		}));
		
		server.start();
	}
}
