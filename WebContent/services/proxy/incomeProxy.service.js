angular.module("Coupon")
    .service('incomeProxy', function ($http) {
        var adminBaseUrl = "coupon/admin";
        var CompanyBaseUrl = "coupon/company";

        // can be called only from a COMPANY account
        this.getConpanyPanyments = function () {
            return $http.get(CompanyBaseUrl + "/getPayments");
        };

        // can be called only from a ADMIN account
        this.getAllIncome = function () {
            return $http.get(adminBaseUrl + "/getAllIncome");
        };

        this.getIncomeByCompany = function (id) {
            return $http({
                method: 'POST',
                url: adminBaseUrl + "/getIncomeByCompany",
                headers: {'Content-Type': 'text/plain'},
                data: id
            });
        };

        this.getIncomeByCustomer = function (id) {
            return $http({
                method: 'POST',
                url: adminBaseUrl + "/getIncomeByCustomer",
                headers: {'Content-Type': 'text/plain'},
                data: id
            });
        };
    });