// login proxy handels the login server call
angular.module("Coupon")
    .service('loginProxy', function ($http) {
        this.login = function (name, password, clientType) {
            return $http
                .get(
                    "http://localhost:8080/CouponWeb/coupon/login/"
                    + name + "/"
                    + password + "/"
                    + clientType)
        }

    });
