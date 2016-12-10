/**
 * Created by Avi on 07/10/2016.
 */
angular.module("Coupon")
    .controller("loginController", function ($scope, $rootScope, $http, $location, loginProxy, couponUtil) {
        // default values for login page
        $scope.clientType = "Company";
        $scope.name = '0';
        $scope.password = '0';
        // login status message show (for wrong user name or password)
        $scope.loginStatusShow = false;

        // Submit the login details
        $scope.mySubmit = function () {
            // checking for null values
            if (($scope.name.length > 0 ) && ($scope.password.length > 0)) {
                // login proxy $http(ajax call)
                loginProxy.login($scope.name, $scope.password, $scope.clientType)
                    .then(
                        function successCallback(response) {
                            // In case of false login
                            if (response.data == "false") {
                                // Set error message
                                $scope.loginStatus = "Username or Password are incorrect";
                                // Show error message
                                $scope.loginStatusShow = true;
                            // In case of true login
                            } else {
                                // Share the client type between to the Root Scope
                                $rootScope.clientType = $scope.clientType;
                                // Redirect to relevant client type home page
                                $location.path("/" + $scope.clientType.toLocaleLowerCase() + "home").replace();
                            }
                        },
                        function errorCallback(response) {
                            couponUtil.handleBadResponse('ERROR:', response);
                        }
                    );
            }
        }
    });
