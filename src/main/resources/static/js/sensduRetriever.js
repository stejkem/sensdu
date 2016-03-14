angular.module('sensduRest', [])
  .controller('jscontroller', function($http) {
  var self = this;
  $http.get('/resource/').success(function(data) {
    self.greeting = data;
  })
});