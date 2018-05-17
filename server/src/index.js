var app = require('express')();
var server = require('http').Server(app);
var io = require('socket.io')(server);
var tempPlayers = [];
var players = [];
var host;

server.listen(8080, function() {
    console.log("Server is now running...");
});

io.on('connection', function(socket) {
    console.log("Player connected!");
    socket.emit('SocketID', { id: socket.id })
    socket.broadcast.emit('NewPlayerJoined', { id: socket.id });
    socket.on('disconnect', function() {
        console.log("Player disconnected");
    });
});

function host(id, jsonGameState) {
    this.id = id;
    this.state = jsonGameState;
}

function player (id) {
    this.id = id;
}