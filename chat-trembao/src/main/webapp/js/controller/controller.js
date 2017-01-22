angular.module("chattrembao").controller("loginCtrl", function($scope, $http, $state){
	$scope.app = "Entrar";
	$scope.users = [{login: "cris", password: "123"}];
	$scope.doLogin = function(user) {
		$http.post("http://localhost:8080/chat-trembao/rest/user/login", user).then(function(response) {
			console.log("Successful: response from submitting data to server was: " + response.data);
			$scope.users.push(angular.copy(user));
			$scope.message = "Usuário " + user.login + " cadastrado com sucesso!";
			$scope.dataResponse = response.data;
			$state.go('home', {login: user.login});
			delete $scope.user;
		},
		
		function (response) {
		      console.log("Error: response from submitting data to server was: " + response);

		      //USING THE PROMISE REJECT FUNC TO CATCH ERRORS
		      deferred.reject({
		        data: response //RETURNING RESPONSE SINCE `DATA` IS NOT DEFINED
		      });
						
		
		});
	};
	
	
	
});