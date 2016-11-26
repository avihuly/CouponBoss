// Used to created a new empty company object
angular.module("Coupon")
    .factory('companyFactory', function () {
        return function() {
            return {
                id: null,
                name: '',
                password: '',
                email: ''
            }
        }
    });
