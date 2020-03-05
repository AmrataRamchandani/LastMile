

#include <ESP8266WiFi.h>
#include <WiFiClient.h>
#include <ESP8266WebServer.h>

/* Set these to your desired credentials. */
const char *ssid = "ESPap"; // You can change it according to your ease
const char *password = "thereisnospoon"; // You can change it according to your ease

ESP8266WebServer server(80); // establishing server at port 80 (HTTP protocol's default port)


// Writing a simple HTML page.
char HTML[] = "<html><body><h1>ESP 8266 Cycle Lock</h1><h2>Tap button<button><a href=\"OFF\">LED OFF</a></button> </a> <button><a href=\"ON\">LED ON</a></button> </body></html>"; // --> Simple HTML page


// This function will be called whenever anyone requests 192.168.4.1 within the local area connection of this ESP module.
void handleRoot() 
{
	server.send(200, "text/html",HTML);
}





void OFF()
{
  digitalWrite(LED_BUILTIN, HIGH);
 // digitalWrite(LED_BUILTIN,!digitalRead(LED_BUILTIN));
  server.send(200, "text/html",HTML);
}
void ON()
{
  digitalWrite(LED_BUILTIN, LOW);
  //digitalWrite(LED_BUILTIN,!digitalRead(LED_BUILTIN));
  server.send(200, "text/html",HTML);
}

void setup() {
	delay(1000);
  pinMode(LED_BUILTIN,OUTPUT);
	Serial.begin(115200);
	Serial.println();

	Serial.print("Configuring access point...");
	/* You can remove the password parameter if you want the AP to be open. */
	WiFi.softAP(ssid, password); // --> This line will create a WiFi hotspot.

	IPAddress myIP = WiFi.softAPIP();
	Serial.print("AP IP address: ");
	Serial.println(myIP);
	server.on("/", handleRoot);

  server.on("/OFF",OFF);
  server.on("/ON",ON);
	server.begin();
	Serial.println("HTTP server started");
}

void loop() {
	server.handleClient();
}
