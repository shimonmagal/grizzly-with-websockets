package com.grizzlywithjaxrs.config;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.files.FilesConfigurationSource;
import org.cfg4j.source.context.filesprovider.ConfigFilesProvider;
import org.cfg4j.provider.ConfigurationProvider;
import org.cfg4j.provider.ConfigurationProviderBuilder;
import org.cfg4j.source.reload.strategy.PeriodicalReloadStrategy;

public class ServerConfig
{
	private static final Logger logger = LoggerFactory.getLogger(ServerConfig.class);
	
	private static ServerConfigParameters instance;
	
	public static ServerConfigParameters get()
	{
		if (instance != null)
		{
			return instance;
		}
		
		synchronized (ServerConfigParameters.class)
		{
			if (instance != null)
			{
				return instance;
			}
			
			instance = ServerConfig.create();
			return instance;
		}
	}
	
	private static ServerConfigParameters create()
	{
		try
		{
			ConfigFilesProvider configFilesProvider = new ServerConfigFilesProvider();
			ConfigurationSource source = new FilesConfigurationSource(configFilesProvider);
			
			logger.info("Loading config from: {}", configFilesProvider);
			
			ConfigurationProvider provider = new ConfigurationProviderBuilder()
				.withConfigurationSource(source)
				.withReloadStrategy(new PeriodicalReloadStrategy(5, TimeUnit.SECONDS))
				.build();
			
			return provider.bind("", ServerConfigParameters.class);
		}
		catch (Exception e)
		{
			logger.error("Error loading configuration", e);
			return null;
		}
	}
}
