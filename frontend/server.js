var express = require('express');
var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);

app.set('port', process.env.PORT || 3000);
app.use('/', express.static(__dirname + '/public'));

io.on('connection', function(socket){
	socket.emit("refresh");

	socket.on('refresh', function() {
    	io.emit('refresh');
  	});
});

http.listen(app.get('port'), function () {
  console.log('Express server listening on port ' + app.get('port'));
});