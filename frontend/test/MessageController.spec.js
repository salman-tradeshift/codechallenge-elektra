'use strict';

describe('MessageController', function(){

    var stubRetrieveJSON =  {
            'messageCount': 1,
            'lastMessage': '2015-06-26T15:26:29Z',
            'messages' : [{
                'message': {
                    'content': 'mock query'
                }}]
        };

    var scope;
    var mockSocket = {
        on  : function() { },
        emit: function() { }
    };
    var mockCreateMessage  = {
        save : function () { }
    };
    var mockRetrieveRecent = {
        query : function(query, success, error) { success(stubRetrieveJSON); }
    };

    beforeEach(module('message-app'));

    beforeEach(function () {

        module(function($provide) {
            $provide.value('socket', mockSocket);
            $provide.value('CreateMessage', mockCreateMessage);
            $provide.value('RetrieveRecent', mockRetrieveRecent);
        });

        inject(function ($rootScope, $controller, socket, CreateMessage, RetrieveRecent) {

            scope = $rootScope.$new();

            $controller('MessageController', {
                '$scope'           : scope,
                'socket'           : socket,
                'CreateMessage'    : CreateMessage,
                'RetrieveRecent'   : RetrieveRecent
            });
        });
      });

    it ('Default values are set.', function() {
       expect(scope.messageCount).toBe(0);
       expect(scope.content).toBe('Message');
    });

    it ('Refresh() calls the correct service and updates the scope.', function () {

       spyOn(mockRetrieveRecent, 'query').and.callThrough();
       scope.refresh();

       expect(mockRetrieveRecent.query).toHaveBeenCalled;
       expect(scope.messageCount).toBe(1);
       expect(scope.lastMessage).toBe('2015-06-26T15:26:29Z');
       expect(scope.messages[0].message.content).toBe('mock query');
    });

    it ('Create() calls the correct service and socket.', function () {
        spyOn(mockSocket, 'emit');
        spyOn(mockCreateMessage, 'save');

        scope.create();
        expect(mockCreateMessage.save).toHaveBeenCalled;
        expect(mockSocket.emit).toHaveBeenCalledWith('refresh');
    });
});