angular.module('sensduRest', [])
  .controller('jscontroller', function($scope, $http) {
  $http.get('/resource/').success(function(data) {
    $scope.greeting = data;
  })
});