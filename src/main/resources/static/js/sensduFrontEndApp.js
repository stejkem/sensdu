angular.module('sensduFrontEnd', [])
 .config(function($locationProvider) {
        // use the HTML5 History API
        $locationProvider.html5Mode({
          enabled: true,
          requireBase: false
        });
 })



  .controller('input', function($http) {
  var self = this;
  self.submitForm = function() {
      $http.post('/resource', self.sensdu).then(function(response) {
            self.sensdu = response.data;
            window.location.href = '/output';
      })
  }
  });

