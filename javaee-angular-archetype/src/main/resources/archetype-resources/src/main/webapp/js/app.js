
var app = angular.module("myapp", ["ngRoute"]);

app.config(function ($routeProvider) {

    $routeProvider
            .when("/contact", {
                templateUrl: "contact.html"
            })
            .when("/main", {
                templateUrl: "main.html"
            })
            .otherwise("/main");

});

app.controller("mainCtrl", function ($scope, $http) {

    $scope.content = 'contact stuff';

    $http.get("rest/hello").then(function (response) {
        $scope.helloMsg = response.data;
    });

});
