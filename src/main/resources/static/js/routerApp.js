   var routerApp = angular.module('routerApp', ['ui.router' , 'ui.bootstrap']);

   routerApp.config(function($stateProvider, $urlRouterProvider, $locationProvider) {

              $urlRouterProvider.otherwise('/405');
              $stateProvider
                  .state('input', {
                      url: '/',
                      templateUrl: 'input.html',
                      controller: 'inputController'
                  })
                  .state('output', {
                      url: '/',
                      templateUrl: 'output.html'
                  })
                  .state('405', {
                        url: '/',
                        templateUrl: '405.html'
                   });

                  $locationProvider.html5Mode({
                        enabled: true,
                        requireBase: false
                  });
    })

   .controller('inputController', function($scope, $rootScope, $http, $state) {
         $scope.sensdu = {};
         $scope.sensdu.fromLanguage = 'uk';
         $scope.sensdu.toLanguage = 'en';

         $scope.formSubmit = function() {
             $http.post('/resource', $scope.sensdu).then(function successCallback(response) {
                   $rootScope.sensdu = response.data;
                   $state.go('output');
             },

             function errorCallback(response) {
                    $state.go('405');
             })
         }
   });
