var app = angular.module('requestApp',[]);
app.controller('submitController', function ($scope, $http) {
    $scope.submit = function () {
        $http({
            method  : 'post',
            dataType: 'JSON',
            url     : 'Task/Create',
            params  : {'requestUrl':'www.example.com',
                       'referer':'www.example.com',
                       'parser':'AutoParser',
                       'creator':'admin',
                       'time':'2017-9-27',
                       'requestMethod':'POST',
                       'annotation':'Request Annotation here'}
        })
    }
})