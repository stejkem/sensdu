   var routerApp = angular.module('routerApp', ['ui.router']);

   routerApp.config(function($stateProvider, $urlRouterProvider, $locationProvider) {

              $urlRouterProvider.otherwise('/input');
              $stateProvider
                  .state('input', {
                      url: '/',
                      templateUrl: 'input.html',
                      controller: 'inputController'
                  })
                  .state('output', {
                      url: '/',
                      templateUrl: 'output.html'
                  });

                  $locationProvider.html5Mode({
                        enabled: true,
                        requireBase: false
                  });
    })

   .controller('inputController', function($scope, $rootScope, $http, $state) {
         $scope.formSubmit = function() {
             $http.post('/resource', $scope.sensdu).then(function(response) {
                   $rootScope.sensdu = response.data;
                   $state.go('output');
             })
         }
   });
