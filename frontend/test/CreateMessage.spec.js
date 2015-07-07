'use strict';

describe('CreateMessage', function () {
    var mockCreateMessage, $httpBackend;
    beforeEach(angular.mock.module('message-app'));

    beforeEach(function () {
        angular.mock.inject(function ($injector) {
            $httpBackend = $injector.get('$httpBackend');
            mockCreateMessage = $injector.get('CreateMessage');
        });
    });

    it('Save() calls the REST api correctly.', inject(function (CreateMessage) {
        $httpBackend.expectPOST('//localhost:8080/messages/names/test')
            .respond({'message': {'content': 'test'}});

        var result = mockCreateMessage.save( { content : 'test' });
        $httpBackend.flush();
        expect(result.message.content).toEqual('test');
    }));
});