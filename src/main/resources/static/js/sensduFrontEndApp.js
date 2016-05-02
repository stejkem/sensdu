angular.module('sensduFrontEnd', [])
 .config(function($locationProvider) {
        // use the HTML5 History API
        $locationProvider.html5Mode({
          enabled: true,
          requireBase: false
        });
 })

  .controller('input', function($http, $location) {
  var self = this;
  self.submitForm = function() {
      $http.get('/resource').then(function(response) {
            self.sensdu = response.data;
            $location.path("/output");
      })
  }
  });