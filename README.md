# Grizzly with websockets
I have always worked with Tomcat in my every day work, but I don't like Tomcat's approach.
Especially the fact they take over your main (main method that is).

I found solace in Grizzly. Here I pair it with jaxrs and websockets.
So this is a basic server that works and you can hit:
http://localhost:8080/api/hello from your browser after you start.
You can also test some basic websockets - using https://www.piesocket.com/websocket-tester

Two additional nice features you get:
1. Since this is a gradle project I also added some plugins that generate a bat or sh to run your server -
so if you hit `gradlew build` - it will create a dir bin with the script

2. You have a simple configuration system to control things like serverPort, but then of course other configurable parameters.

So overall this is a nice example to start from.