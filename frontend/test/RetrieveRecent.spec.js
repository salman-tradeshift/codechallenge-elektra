'use strict';

describe('RetrieveRecent', function () {
  var mockRetrieveRecent, $httpBackend;
  beforeEach(angular.mock.module('message-app'));

  beforeEach(function () {
    angular.mock.inject(function ($injector) {
      $httpBackend = $injector.get('$httpBackend');
      mockRetrieveRecent = $injector.get('RetrieveRecent');
    });
  });

  it('Query() calls the REST api correctly.', inject(function (RetrieveRecent) {
    $httpBackend.expectGET('//localhost:8080/messages/recent')
        .respond({fakeson : 'fake JSON'});

    var result = mockRetrieveRecent.query({});
    $httpBackend.flush();
    expect(result.fakeson).toEqual("fake JSON");
  }));

});