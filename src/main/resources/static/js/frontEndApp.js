    var frontEndApp = angular.module('frontEndApp', ['ui.router' , 'ui.bootstrap']);

    frontEndApp.config(function($stateProvider, $urlRouterProvider, $locationProvider) {

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
        $scope.sensdu.userAgent = navigator.appVersion;

        $scope.formHelper.fromLanguageCode = 'ru';
        $scope.formHelper.fromLanguage = 'Russian';

        $scope.formHelper.toLanguageCode = 'ru';
        $scope.formHelper.toLanguage = 'Russian';

        $scope.formSubmit = function() {
            $scope.sensdu.userAgent = navigator.appVersion;
            $http.post('/resource', $scope.sensdu).then(function successCallback(response) {
                $scope.sensdu = response.data;
                $scope.sensdu.rememberSourceWord = response.data.sourceWord;
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
            $scope.sensdu.rememberSourceWord = null;
        }

        $scope.swapLanguages = function() {
            if(($scope.formHelper.fromLanguageCode == $scope.sensdu.fromLanguage) &&
                ($scope.formHelper.toLanguageCode != $scope.sensdu.toLanguage))   {
                 $scope.formHelper.toLanguage = $scope.formHelper.fromLanguage;
                 $scope.formHelper.toLanguageCode = $scope.formHelper.fromLanguageCode;
            }
            if (($scope.formHelper.toLanguageCode == $scope.sensdu.toLanguage) &&
                ($scope.formHelper.fromLanguageCode == $scope.sensdu.fromLanguage)) {

                $scope.formHelper.fromLanguage = $scope.formHelper.toLanguage;
                $scope.formHelper.fromLanguageCode = $scope.formHelper.toLanguageCode;
            }
            if (($scope.formHelper.fromLanguageCode == $scope.sensdu.fromLanguage) &&
                ($scope.formHelper.toLanguageCode == $scope.sensdu.toLanguage)) {
                var interMediate = $scope.formHelper.fromLanguage;
                var interMediateCode = $scope.formHelper.fromLanguageCode;
                $scope.formHelper.fromLanguage = $scope.formHelper.toLanguage;
                $scope.formHelper.fromLanguageCode = $scope.formHelper.toLanguageCode;
                $scope.formHelper.toLanguage = interMediate;
                $scope.formHelper.toLanguageCode = interMediateCode;
                $scope.sensdu.fromLanguage = $scope.formHelper.fromLanguageCode;
                $scope.sensdu.toLanguage = $scope.formHelper.toLanguageCode;

            } else {
                var interMediate = $scope.sensdu.fromLanguage;
                $scope.sensdu.fromLanguage = $scope.sensdu.toLanguage;
                $scope.sensdu.toLanguage = interMediate;
            }
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