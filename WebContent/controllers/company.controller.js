angular.module("Coupon")
    .controller("companyController", function
        ($scope, $http, $rootScope, couponUtil,
         loginProxy, companyCouponProxy, incomeProxy,
         couponFactory, couponTypesFactory, couponFilterFactory) {

        // Coupons model array
        $scope.coupons = [];
        // Search text
        $scope.searchText = '';
        // client Type
        $scope.clientType = $rootScope.clientType;
        // Sidebar navigation click model
        $scope.sideBarRadioClickModel = "views/companyCoupon.view.html";
        // Coupon types
        $scope.couponType = couponTypesFactory;
        // Filter model
        $scope.couponFilter = couponFilterFactory();
        // List of coupon types
        $scope.types = couponTypesFactory;
        // Incomes model array
        $scope.incomes = [];

        // Clear Search Text
        $scope.ClearSearchText = function () {
            $scope.searchText = '';
        };

        // Validation method //
        // Validation method are called before sending data to the server
        // and stop the request if detecting illegal values

        // coupon title validation
        $scope.onberofesaveCouponTitle = function (data) {
            var coupTitle = [];
            for (var i = 0; i < $scope.coupons.length; i++) {
                coupTitle.push($scope.coupons[i].title);
            }
            return couponUtil.nameValidation(data, coupTitle);
        };
        // end date validation
        $scope.onberofesaveEndDate = function (data) {
            return couponUtil.dateValidation(data);
        };
        // price validation
        $scope.onberofesavePrice = function (data) {
            return couponUtil.PriceValidation(data);
        }


        ////////////
        // COUPON //
        ////////////
        // Get all coupons
        $scope.getAllCoupons = function () {
            $scope.couponFilter = couponFilterFactory();
            companyCouponProxy.getAll()
                .then(
                    function successCallback(response) {
                        $scope.coupons = response.data;
                    },
                    function errorCallback(response) {
                        couponUtil.handleBadResponse('ERROR:', response);
                    });
        };
        $scope.getAllCoupons(); // loading coupons on page startup
        // Remove coupon
        $scope.removeCoupon = function (index) {
            companyCouponProxy.remove($scope.coupons[index].id)
                .then(
                    function successCallback(response) {
                        console.log('DELETED:');
                        console.log(response.data);
                        // Delete coupon from model
                        $scope.coupons.splice(index, 1);
                    },
                    function errorCallback(response) {
                        couponUtil.handleBadResponse('ERROR:', response);
                    });
        };
        // Add coupon
        $scope.addCoupon = function (data) {
            // checks if there are null fields in coupon template
            if (couponUtil.newCouponValidation(data)) {
                console.debug($scope.couponTemplate);
                var img = document.getElementById("couponImage");
                // convert image to base64
                getBase64(img.files[0], function (imageBase64) {
                        data.image = imageBase64;
                        // if all checks out make a call to the server
                        companyCouponProxy.create(data)
                            .then(
                                function successCallback(response) {
                                    alert("New coupon was Added to DB");
                                    // update model
                                    $scope.coupons.push(response.data);
                                    $scope.sideBarRadioClickModel = "views/companyCoupon.view.html";
                                },
                                function errorCallback(response) {
                                    couponUtil.handleBadResponse('ERROR:', response);
                                });
                    },
                    function (getBase64Error) {
                        console.error(getBase64Error);
                    }
                );
            } else {
                alert("please make sure all fields are filled")
            }

        };
        // Update coupon
        $scope.updateCoupon = function (data, index) {
            // Update coupon in DB
            companyCouponProxy.update($scope.coupons[index].id, data)
                .then(
                    function successCallback(response) {
                        couponUtil.handleBadResponse('Coupon updated', response);
                        // update model
                        $scope.coupons[index] = response.data;
                    },
                    function errorCallback(response) {
                        couponUtil.handleBadResponse('ERROR:', response);
                    });
        };
        // Get coupon by type
        $scope.byType = function () {
            if ($scope.couponFilter.typeOnfocus == "All") {
                $scope.getAllCoupons();
            } else {
                companyCouponProxy.byType($scope.couponFilter.typeOnfocus)
                    .then(
                        function successCallback(response) {
                            $scope.coupons = response.data;
                            if ($scope.coupons == '') {
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
        // Get coupon by price
        $scope.byPrice = function () {
            // Set Type to All
            document.getElementById("typeSelect").selectedIndex = ($scope.couponType.length);
            // Purchased by price
            if ($scope.couponFilter.upToPrice != null) {
                companyCouponProxy.byPrice($scope.couponFilter.upToPrice)
                    .then(
                        function successCallback(response) {
                            $scope.coupons = response.data;
                            if ($scope.coupons.length == 0) {
                                $scope.message = 'no coupons in that price range';
                            } else {
                                $scope.message = '';
                            }
                        },
                        function errorCallback(response) {
                            couponUtil.handleBadResponse('ERROR:', response);
                        });
            }
        };
        // Get coupon by start or end date
        $scope.ByDate = function () {
            var date = $scope.couponFilter.date;
            if (date != null) {
                if ($scope.couponFilter.dateRadio == "start") {
                    companyCouponProxy.byStartDate(date)
                        .then(
                            function successCallback(response) {
                                $scope.coupons = response.data;
                                if ($scope.coupons != '') {
                                    $scope.couponFilter.message = '';
                                } else {
                                    $scope.couponFilter.message = "No coupon in that date range";
                                }
                            },
                            function errorCallback(response) {
                                couponUtil.handleBadResponse('ERROR:', response);
                            });
                } else if ($scope.couponFilter.dateRadio == "end") {
                    companyCouponProxy.byEndDate(date)
                        .then(
                            function successCallback(response) {
                                $scope.coupons = response.data;
                                if ($scope.coupons != '') {
                                    $scope.couponFilter.message = '';
                                } else {
                                    $scope.couponFilter.message = "No coupon in that date range";
                                }
                            },
                            function errorCallback(response) {
                                $scope.couponFilter.message = "No coupon in that date range";
                                couponUtil.handleBadResponse('ERROR:', response);
                            });
                }
            }
        };

        ///////////////
        //New Coupon //
        ///////////////
        // When entering new coupon mode a new coupon object is created
        // to hold the new coupon details
        $scope.generateCouponTemplate = function () {
            $scope.couponTemplate = couponFactory();

            var today = new Date;
            var todayPlus30 = new Date;
            todayPlus30.setDate(todayPlus30.getDate() + 30);

            $scope.couponTemplate.startDate = today;
            $scope.couponTemplate.endDate = todayPlus30;
        };
        $scope.imagePathChanged = function () {
            alert(document.getElementById("image"));
        };
        function getBase64(file, successCb, errorCb) {
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function () {
                successCb(reader.result);
            };
            reader.onerror = function (error) {
                errorCb(error);
            };
        };

        //////////////
        // Payments //
        //////////////
        $scope.getPayments = function () {
            incomeProxy.getConpanyPanyments()
                .then(
                    function successCallback(response) {
                        $scope.incomes = response.data;
                    },
                    function errorCallback(response) {
                        couponUtil.handleBadResponse('ERROR:', response);
                    });
        }
    });