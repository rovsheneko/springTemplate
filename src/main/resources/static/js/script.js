'use strict';
//ngResource a factory which allows you to interact with RESTful server-side data sources.
const ngHelp = angular.module('ngHelp', ['ngResource']);

  ngHelp.controller('CustomerController', ['$scope', function($scope) {
    $scope.naomi = { name: 'Naomi', address: '1600 Amphitheatre' };
    $scope.igor = { name: 'Igor', address: '123 Somewhere' };

  }]);

  ngHelp.directive('myCustomer', function() {
    return {
      restrict: 'E',
      scope: {
        customerInfo: '=info'
      },
      templateUrl: '../templates/my-customer-iso.html'
    };
  });

  ngHelp.controller('DataFormController', ['$scope', function($scope) {
       $scope.naomi = { name: 'Naomi', address: '1600 Amphitheatre' };
       $scope.user = { name: 'Rov' };

   }]);

   ngHelp.factory('usersRestApiFactory', function($resource){
        return{
            getUsers : $resource("http://localhost:8080/users")
        }
   });

   ngHelp.controller('DataMapCtr', ['$scope', '$http', 'usersRestApiFactory',  function($scope, $http, usersRestApiFactory){
        $scope.users = [{"id":1,"firstName":"Rov","lastName":"Rov"}];
        $scope.users = usersRestApiFactory.getUsers.query({});
   }]);

   ngHelp.controller('FormCtr', ['$scope', '$resource', 'usersRestApiFactory',  function($scope, $resource, usersRestApiFactory){

        var Visitor = $resource('http://localhost:8080/users',
         {}, {
          addUser: {method:'POST', params:{}}
         });

        $scope.users = [{"id":1,"firstName":"Rov","lastName":"Rov"}];
        $scope.users = usersRestApiFactory.getUsers.query({});
        $scope.submit = function(){
//            let newUser = new Visitor();
//            newUser.firstName = $scope.name;
//            newUser.lastName = $scope.lastName;
//            newUser.$save();
            let newUser = usersRestApiFactory.getUsers.save({"id":"", "firstName":$scope.name,"lastName":$scope.lastName})
            $scope.users.push(newUser);
        }
        $scope.delete = function(index){
            let userToDelete = $scope.users[index];
            usersRestApiFactory.getUsers.delete(userToDelete)
            .$promise.then(function(user){
                console.log(user);
                $scope.users.splice(index,1);
            }, function(error){
                console.error(error);
            });
        }
   }]);

