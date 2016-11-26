// customerCouponProxy handels all server calls for customer coupons manipulation
angular.module("Coupon")
    .service('customerCouponProxy', function ($http) {
        var baseUrl = "coupon/customer";

        this.browse = function () {
            return $http.get(baseUrl + "/browseCoupon");
        };
        this.purchased = function () {
            return $http.get(baseUrl + "/purchasedCoupons");
        };
        this.buy = function (id) {
            return $http({
                method: 'POST',
                url: baseUrl + "/purchaseCoupon",
                headers: {'Content-Type': 'text/plain'},
                data: id
            });
        };
        this.purchasedByType = function (type) {
            return $http({
                method: 'POST',
                url: baseUrl + "/purchasedByType",
                headers: {'Content-Type': 'text/plain'},
                data: type
            });
        };
        this.purchasedByPrice = function (price) {
            return $http({
                method: 'POST',
                url: baseUrl + "/purchasedByPrice",
                headers: {'Content-Type': 'text/plain'},
                data: price
            });
        };
    });