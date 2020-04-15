package com.grizzlywithjaxrs.ws;

import org.glassfish.grizzly.http.HttpRequestPacket;
import org.glassfish.grizzly.websockets.*;

import java.util.HashSet;
import java.util.Set;

public class MessagesWebSocket extends WebSocketApplication {
	Set<WebSocket> sockets = new HashSet<>();

	@Override
	public final WebSocket createSocket(ProtocolHandler handler, HttpRequestPacket requestPacket, WebSocketListener... listeners) {
		WebSocket webSocket = super.createSocket(handler, requestPacket, listeners);

		sockets.add(webSocket);

		return webSocket;
	}

	@Override
	public void onMessage(WebSocket socket, String message)
	{
		System.out.println("Got message and will echo it back: " + message);

		socket.send(message);
	}

	@Override
	public void onClose(WebSocket socket, DataFrame frame) {
		sockets.remove(socket);
	}
}