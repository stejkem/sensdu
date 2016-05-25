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
                  .state('disambiguation', {
                      url: '/',
                      templateUrl: 'disambiguation.html',
                      controller: 'disambiguationController'

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
                   if ($rootScope.sensdu.translatedWord == null) {
                        $state.go('disambiguation');
                   } else {
                        $state.go('output');
                   }
             },
             function errorCallback(response) {
                    $state.go('400');
             })
         }

         $scope.reset = function() {
            $scope.sensdu.fromLanguage = 'uk';
            $scope.sensdu.toLanguage = 'en';
            $scope.formHelper.fromLanguageCode = 'ru';
            $scope.formHelper.fromLanguage = 'Russian';

            $scope.formHelper.toLanguageCode = 'ru';
            $scope.formHelper.toLanguage = 'Russian';
         }

         $scope.validateLanguagesInFromGroup = function() {
             if($scope.sensdu.fromLanguage == $scope.sensdu.toLanguage) {
                 if ($scope.sensdu.toLanguage == 'uk') {
                    $scope.sensdu.toLanguage = 'en';
                 } else {
                    $scope.sensdu.toLanguage = 'uk';
                 }
             }
         }

         $scope.validateLanguagesInToGroup = function() {
             if($scope.sensdu.toLanguage == $scope.sensdu.fromLanguage) {
                 if ($scope.sensdu.fromLanguage == 'en') {
                    $scope.sensdu.fromLanguage = 'uk';
                 } else {
                    $scope.sensdu.fromLanguage = 'en';
                 }
             }
         }

   })

     .controller('disambiguationController', function($scope, $rootScope, $http, $state) {
             $scope.sensdu.sourceWord = $scope.sensdu.wordSuggestion[0];

             $scope.formSubmit = function() {
                 $http.post('/resource', $scope.sensdu).then(function successCallback(response) {
                       $rootScope.sensdu = response.data;
                       if ($rootScope.sensdu.translatedWord == null) {
                            $state.go('400');
                       } else {
                            $state.go('output');
                       }
                 },
                 function errorCallback(response) {
                        $state.go('400');
                 })
             }

       });