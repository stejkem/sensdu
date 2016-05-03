   var routerApp = angular.module('routerApp', ['ui.router']);

   routerApp.config(function($stateProvider, $urlRouterProvider, $locationProvider) {

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

           $locationProvider.html5Mode({
                 enabled: true,
                 requireBase: false
           });
   })

 .controller('mainController', function($http, $state) {
         var self = this;
         self.formSubmit = function() {
             $http.post('/resource', self.sensdu).then(function(response) {
                   self.sensdu = response.data;
                   $state.go('output');
             })
         }
 });
