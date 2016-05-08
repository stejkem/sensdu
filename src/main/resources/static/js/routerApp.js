   var routerApp = angular.module('routerApp', ['ui.router' , 'ui.bootstrap']);

   routerApp.config(function($stateProvider, $urlRouterProvider, $locationProvider) {

              $urlRouterProvider.otherwise('/404');
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
                  .state('400', {
                        url: '/',
                        templateUrl: '400.html'
                   });

                  $locationProvider.html5Mode({
                        enabled: true,
                        requireBase: false
                  });
    })

   .controller('inputController', function($scope, $rootScope, $http, $state) {
         $scope.sensdu = {};
         $scope.formHelper = {};
         $scope.sensdu.fromLanguage = 'uk';
         $scope.sensdu.toLanguage = 'en';

         $scope.formHelper.fromLanguageCode = 'ru';
         $scope.formHelper.fromLanguage = 'Russian';

         $scope.formHelper.toLanguageCode = 'ru';
         $scope.formHelper.toLanguage = 'Russian';

         $scope.changeLanguageInFromGroup = function(fromLanguageCode, fromLanguage) {
             $scope.formHelper.fromLanguageCode = fromLanguageCode;
             $scope.formHelper.fromLanguage = fromLanguage;
             $scope.sensdu.fromLanguage = fromLanguageCode;
         }

         $scope.changeLanguageInToGroup = function(toLanguageCode, toLanguage) {
             $scope.formHelper.toLanguageCode = toLanguageCode;
             $scope.formHelper.toLanguage = toLanguage;
             $scope.sensdu.toLanguage = toLanguageCode;
         }

         $scope.formSubmit = function() {
             $http.post('/resource', $scope.sensdu).then(function successCallback(response) {
                   $rootScope.sensdu = response.data;
                   $state.go('output');
             },
             function errorCallback(response) {
                    $state.go('400');
             })
         }
   });
