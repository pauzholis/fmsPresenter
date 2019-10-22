var app = angular.module("FmsList", []);

app.controller("FmsController", function ($scope, $http) {

    $scope.fmsList = [];
    $scope.fmsArchive = "";
    $scope.incorrectCode = "";
    $scope.fmsForm = {
        code: ""
    };

    $scope.getFmsByCode = function () {
        if ($scope.fmsForm.code === "") {
            $scope.fmsForm.code = null;
        }
        $http({
            method: "GET",
            url: "fms-presenter/fms/".concat($scope.fmsForm.code)
        }).then(function (res) {
            $scope.fmsList = res.data.data;
            $scope.incorrectCode = "";
            if (res.data.data.error != null) {
                $scope.incorrectCode = "Отделения ФМС по данному коду не найдены"
            }
        });
    };

    $scope.getAllFms = function () {
        $http({
            method: "GET",
            url: "fms-presenter/fms"
        }).then(function (res) {
            $scope.fmsList = res.data.data;
        });
    };

    $scope.getArchive = function () {
        $http({
            method: 'GET',
            url: 'fms-presenter/fms/archive'
        }).then(function (res) { // success
                $scope.fmsArchive = "Список успешно получен";
                if (res.data.data.error != null) {
                    $scope.fmsArchive = res.data.data.error;
                }
            }
        );
    };
});