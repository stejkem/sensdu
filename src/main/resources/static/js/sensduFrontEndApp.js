   var app = angular.module('sensduFrontEnd', ['ui.router']);

   app.config(function($stateProvider, $urlRouterProvider) {

       $urlRouterProvider.otherwise('/input');
       $stateProvider
           .state('input', {
               url: '/input',
               templateUrl: 'input.html'
           })
           .state('output', {
               url: '/output',
               templateUrl: 'output.html'
           });
   })

  function($http, $state) {
      var self = this;
      self.submitForm = function() {
          $http.post('/resource', self.sensdu).then(function(response) {
                self.sensdu = response.data;
                $state.go('output');
          })
      }
  };

