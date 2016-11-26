angular.module("Coupon")
    .controller("customerController", function
        ($scope, $http, $rootScope, couponUtil,
         customerCouponProxy, couponTypesFactory,
         couponFilterFactory) {

        // Coupon model arrays
        $scope.couponsToBrowse = [];
        $scope.purchasedCoupons = [];
        // Search text
        $scope.searchText = '';
        // client Type
        $scope.clientType = $rootScope.clientType;
        // sidebar navigation click model default is BrowseCoupons view
        $scope.sideBarRadioClickModel = "views/customerBrowseCoupons.view.html";
        // Coupon filter model
        $scope.couponFilter = couponFilterFactory();
        // Clear Search Text
        $scope.ClearSearchText = function () {
            $scope.searchText = '';
        };

        ///////////////////
        // Coupon Methods//
        ///////////////////
        // Browse coupons method
        $scope.browseCoupons = function () {
            customerCouponProxy.browse()
                .then(
                    function successCallback(response) {
                        $scope.couponsToBrowse = response.data;
                    },
                    function errorCallback(response) {
                        couponUtil.handleBadResponse('ERROR:', response);
                    });
        };
        $scope.browseCoupons(); // loading browse coupons on page startup
        // Customer(my) coupons method
        $scope.purchasedCoupon = function () {
            $scope.couponFilter = couponFilterFactory();
            customerCouponProxy.purchased()
                .then(
                    function successCallback(response) {
                        // Set Type to All
                        $scope.purchasedCoupons = response.data;
                    },
                    function errorCallback(response) {
                        couponUtil.handleBadResponse('ERROR:', response);
                    });
        };
        // Buy coupon method
        $scope.buyCoupon = function (id) {
            customerCouponProxy.buy(id)
                .then(
                    function successCallback(response) {
                        $scope.purchasedCoupons.push(response.data);
                        couponUtil.handleBadResponse('Purchased coupon:', response);
                        alert("Coupon Purchased");
                    },
                    function errorCallback(response) {
                        couponUtil.handleBadResponse('ERROR:', response);
                    });
        };
        // Purchased by type
        $scope.purchasedByType = function () {
            if ($scope.couponFilter.typeOnfocus == "All") {
                $scope.purchasedCoupon();
            } else {
                customerCouponProxy.purchasedByType($scope.couponFilter.typeOnfocus)
                    .then(
                        function successCallback(response) {
                            $scope.purchasedCoupons = response.data;
                            if ($scope.purchasedCoupons == '') {
                                $scope.couponFilter.message =
                                    "No coupons of type '" + $scope.couponFilter.typeOnfocus + "'";
                            } else {
                                $scope.couponFilter.message = '';
                            }
                        },
                        function errorCallback(response) {
                            couponUtil.handleBadResponse('ERROR:', response);
                        });
            }
        };
        // Purchased by price
        $scope.purchasedByPrice = function () {
            // Set Type to All
            $scope.couponFilter.typeOnfocus = "All";
            // Purchased by price
            if ($scope.couponFilter.price != null) {
                customerCouponProxy.purchasedByPrice($scope.couponFilter.price)
                    .then(
                        function successCallback(response) {
                            $scope.purchasedCoupons = response.data;
                            if ($scope.purchasedCoupons.length == 0) {
                                $scope.couponFilter.message = 'No coupons in that price range';
                            } else {
                                $scope.couponFilter.message = '';
                            }
                        },
                        function errorCallback(response) {
                            couponUtil.handleBadResponse('ERROR:', response);
                        });
            }
        };
    });