'use strict';

require('angular');
require('angular-resource');

var app = angular.module('message-app', ['ngResource']);

app.factory('socket', ['$rootScope', function ($rootScope) {
    var socket = io.connect();

    return {
        on: function (eventName, callback) {
            function wrapper() {
                var args = arguments;
                $rootScope.$apply(function () {
                    callback.apply(socket, args);
                });
            }

            socket.on(eventName, wrapper);

            return function () {
                socket.removeListener(eventName, wrapper);
            };
        },

        emit: function (eventName, data, callback) {
            socket.emit(eventName, data, function () {
                var args = arguments;
                $rootScope.$apply(function () {
                    if(callback) {
                        callback.apply(socket, args);
                    }
                });
            });
        }
    };
}]);

app.factory('CreateMessage', ['$http', '$resource', function($http, $resource) {
    $http.defaults.headers.post['Content-Type'] = 'text/plain';
    return $resource('//' + location.hostname + '::port/messages/names/:content', {port: '8080', content : '@content'});
}]);

app.factory('RetrieveRecent', ['$resource', function($resource) {
    return $resource('//' + location.hostname + '::port/messages/recent', {port : '8080'},
        {
            query: {method: 'GET', isArray : false}
        });
}]);

app.controller('MessageController', ['$scope', 'socket', 'CreateMessage', 'RetrieveRecent', function($scope, socket, CreateMessage, RetrieveRecent) {

    $scope.content = 'Message';
    $scope.messageCount = 0;

    $scope.refresh = function () {
        RetrieveRecent.query({},
            function(data) {
                $scope.messageCount = data.messageCount;
                $scope.lastMessage = data.lastMessage;
                $scope.messages = data.messages;
            },
            function(data) { window.alert('ERROR:' + JSON.stringify(data)); });
    };

    $scope.create = function () {
        CreateMessage.save( { content : $scope.content },
            function() {
                $scope.refresh();
            },
            function(data) { window.alert('ERROR data:' + JSON.stringify(data)); });
        socket.emit('refresh');
    };

    socket.on('refresh', function(){
        $scope.refresh();
    });

}]);