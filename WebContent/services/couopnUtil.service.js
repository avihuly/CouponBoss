angular.module("Coupon")
    .service('couponUtil', function ($location) {
        // password validation
        this.passwordValidation = function (password) {
            var pattern = /(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,10}/g;
            if (pattern.test(password) && (password.length < 10)) {
                return true;
            } else {
                return "Password must contain:\n"
                    + "6-10 characters\n"
                    + "At lest one upper case letter\n"
                    + "At lest one lower case letter\n"
                    + "At lest one digit";
            }
        };

        // return false if user name exsist
        this.nameValidation = function (name) {
            if (name.length < 1) {
                return "User name can't be empty";
            } else {
                return true;
            }
        };

        // return false if date is not in the correct format
        this.dateValidation = function (date) {
            var pattern = /(2)(?=.*[0-9]).{3}(-)(((0)(?=.*[1-9]).{1})|((1)(?=.*[0-2]).{1}))(-)(|((0)(?=.*[1-9]).{1})|((1)(?=.*[0-9]).{1})|((2)(?=.*[0-9]).{1})|((3)(?=.*[0-1]).{1}))/g;

            if (!(pattern.test(date) && date.length == 10)) {
                return "Date format: yyyy-mm-dd";
            } else if (Date.parse(date) < new Date()) {
                return "Date can't be in the past";
            } else {
                return true;
            }
        };

        // return false if price is illegal
        this.PriceValidation = function (data) {
            if (isNaN(data)) {
                return "Price must be a number";
            } else if (data < 0) {
                return "Price must be a positive number";
            } else {
                return true;
            }
        };

        // Check for null values in couponTemplate
        this.newCouponValidation = function (data) {
            var validCoupon = true;
            angular.forEach(data, function (value, key) {
                if (value == null && key != "id") {
                    validCoupon = false;
                }
            })
            return validCoupon;
        };

        // returns date in string format that the server can handel
        this.dateToStringFormat = function (date) {
            var StringFormattedDate =
                date.getFullYear() +
                "-" + ("0" + (date.getMonth() + 1)).slice(-2) +
                "-" + ("0" + date.getDate()).slice(-2);
            return StringFormattedDate;
        };

        // handel bad response from server
        this.handleBadResponse = function (message, response) {
            // Check for null session and redirect to login page
            var data = response.data;

            if (data.errorCode == 900) {
                $location.path("/login").replace();
            }
            if (400 <= response.status && response.status < 500) {
                alert(data.errorMessage);
            }
            else if (500 <= response.status && response.status < 600) {
                $location.path("/500").replace();
            }

            // log response
            console.log(message);
            console.log(data);
        };
    });
