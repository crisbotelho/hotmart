angular.module("chattrembao").controller("userCtrl", function($scope, $http, $state){
	$scope.users = [];
	$scope.addUser = function(user) {
		$http.post("http://localhost:8080/chat-trembao/rest/user/adduser", user).then(function(response) {
			console.log("Successful: response from submitting data to server was: " + response);
			$scope.users.push(angular.copy(user));
			$scope.message = "Usuário " + user.login + " cadastrado com sucesso!";
			delete $scope.user;
			$state.go('login');
		},
		
		function (response) {
		      console.log("Error: response from submitting data to server was: " + response);

		      //USING THE PROMISE REJECT FUNC TO CATCH ERRORS
		      deferred.reject({
		        data: response //RETURNING RESPONSE SINCE `DATA` IS NOT DEFINED
		      });
		
		
		
		
		
// 					.error(function(data, status){
// 						$scope.message = "Erro ao cadastrar usuário!: " + data;
// 					});				
		
		});
	};
	
});