    var routerApp = angular.module('frontEndApp', ['ui.router' , 'ui.bootstrap']);

    routerApp.config(function($stateProvider, $urlRouterProvider, $locationProvider) {

        $urlRouterProvider.otherwise('/404');
        $stateProvider
            .state('input', {
                url: '/',
                templateUrl: 'input.html',
                controller: 'inputController'
            });

            $locationProvider.html5Mode({
                enabled: true,
                requireBase: false
            });
    })

    .controller('inputController', function($scope, $rootScope, $http, $state) {
        $scope.sensdu = {};
        $scope.formHelper = {};
        $scope.sensdu.state = "initiated";
        $scope.sensdu.fromLanguage = 'uk';
        $scope.sensdu.toLanguage = 'en';

        $scope.formHelper.fromLanguageCode = 'ru';
        $scope.formHelper.fromLanguage = 'Russian';

        $scope.formHelper.toLanguageCode = 'ru';
        $scope.formHelper.toLanguage = 'Russian';

        $scope.formSubmit = function() {
            $http.post('/resource', $scope.sensdu).then(function successCallback(response) {
                $scope.sensdu = response.data;
            },
            function errorCallback(response) {
                $scope.sensdu.sourceWord = null;
                $scope.sensdu.state = "error";
            })
        }

        $scope.changeLanguageInFromGroup = function(fromLanguageCode, fromLanguage) {
            $scope.formHelper.fromLanguageCode = fromLanguageCode;
            $scope.formHelper.fromLanguage = fromLanguage;
            $scope.sensdu.fromLanguage = fromLanguageCode;
        }

        $scope.reset = function() {
            $scope.sensdu = {};
            $scope.sensdu.fromLanguage = 'uk';
            $scope.sensdu.toLanguage = 'en';
            $scope.formHelper.fromLanguageCode = 'ru';
            $scope.formHelper.fromLanguage = 'Russian';

            $scope.formHelper.toLanguageCode = 'ru';
            $scope.formHelper.toLanguage = 'Russian';
        }

        $scope.partialReset = function() {
            $scope.sensdu.state = "initiated";
            $scope.sensdu.wordURL = null;
            $scope.sensdu.translatedWord = null;
            $scope.sensdu.translatedWordURL = null;
        }

         $scope.changeLanguageInToGroup = function(toLanguageCode, toLanguage) {
             $scope.formHelper.toLanguageCode = toLanguageCode;
             $scope.formHelper.toLanguage = toLanguage;
             $scope.sensdu.toLanguage = toLanguageCode;
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

    });