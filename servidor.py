#!/usr/bin/env python

import socket, sys
sock = socket.socket()

HOST= '0.0.0.0'
PORT=  2004
sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
sock.bind((HOST, PORT))
sock.listen(5)
datos = ''
while True:
	source,address = sock.accept()
	print source , address
	while True:
		datos=source.recv(1024)
		if len(datos) <=0:
				source.close()
				break
		print "Mensaje recibido:",datos
	 	f = open("img.jpg", "ab")
	 	f.write(datos)
	 	f.close()
	 	n+=1

#Código para enviar y recibir respuestas

source.close()
