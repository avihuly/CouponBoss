// customer proxy handels all server calls for customer objects manipulation
angular.module("Coupon")
    .service('customerProxy', function ($http) {
        var baseUrl = "coupon/admin";

        this.getAll = function () {
            return $http.get(baseUrl + "/getAllCustomers");
        };
        this.create = function (customer) {
            return $http({
                method: 'POST',
                url: baseUrl + "/createCustomer",
                headers: {'Content-Type': 'application/json'},
                data: customer
            })
        };
        this.update = function (id, customer) {
            var customerToUpdate = angular.copy(customer);
            customerToUpdate.id = id;

            return $http({
                method: 'POST',
                url: baseUrl + "/updateCustomer",
                headers: {'Content-Type': 'application/json'},
                data: customerToUpdate
            })
        };
        this.remove = function (id) {
            return $http({
                method: 'Delete',
                url: baseUrl + "/removeCustomer",
                headers: {'Content-Type': 'text/plain'},
                data: id
            })
        };
        this.getCoupons = function (id) {
            return $http({
                method: 'POST',
                url: baseUrl + "/getCustomerCoupons",
                headers: {'Content-Type': 'text/plain'},
                data: id
            })
        };
    });
