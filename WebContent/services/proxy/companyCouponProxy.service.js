// companyCouponProxy handels all server calls for company coupons manipulation
angular.module("Coupon")
    .service('companyCouponProxy', function ($http, couponFactory, couponUtil) {
        var baseUrl = "coupon/company";

        this.getAll = function () {
            return $http.get(baseUrl + "/getAllCoupons");
        };
        this.create = function (coupon) {
            coupon.startDate = couponUtil.dateToStringFormat(coupon.startDate);
            coupon.endDate = coupon.endDate = couponUtil.dateToStringFormat(coupon.endDate);

            return $http({
                method: 'POST',
                url: baseUrl + "/createCoupon",
                headers: {'Content-Type': 'application/json'},
                data: coupon
            });
        };
        this.update = function (id, coupon) {
            // server needs a full coupon object
            var couponToUpdate = couponFactory();
            couponToUpdate.id = id;
            couponToUpdate.price = coupon.price;
            couponToUpdate.endDate = coupon.endDate;

            return $http({
                method: 'POST',
                url: baseUrl + "/updateCoupon",
                headers: {'Content-Type': 'application/json'},
                data: couponToUpdate
            })
        };
        this.remove = function (id) {
            return $http({
                method: 'Delete',
                url: baseUrl + "/removeCoupon",
                headers: {'Content-Type': 'text/plain'},
                data: id
            });
        };

        this.byType = function (type) {
            return $http({
                method: 'POST',
                url: baseUrl + "/getCouponByType",
                headers: {'Content-Type': 'text/plain'},
                data: type
            });
        };
        this.byPrice = function (price) {
            return $http({
                method: 'POST',
                url: baseUrl + "/getCouponByPrice",
                headers: {'Content-Type': 'text/plain'},
                data: price
            });
        };
        this.byStartDate = function (date) {
            //format date to dd-mm-yyyy
            date = couponUtil.dateToStringFormat(date);
            return $http({
                method: 'POST',
                url: baseUrl + "/getCouponByStartDate",
                headers: {'Content-Type': 'text/plain'},
                data: date
            });
        };
        this.byEndDate = function (date) {
            date = couponUtil.dateToStringFormat(date);
            return $http({
                method: 'POST',
                url: baseUrl + "/getCouponByEndDate",
                headers: {'Content-Type': 'text/plain'},
                data: date
            });
        };
    });
