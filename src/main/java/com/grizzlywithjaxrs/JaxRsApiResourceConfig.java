package com.grizzlywithjaxrs;

import org.glassfish.jersey.server.ResourceConfig;

public class JaxRsApiResourceConfig
{
	public static ResourceConfig create()
	{
		return new ResourceConfig()
				.packages("com.grizzlywithjaxrs.api");
	}
}
