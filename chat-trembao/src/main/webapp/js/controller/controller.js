angular.module("chattrembao").controller("loginCtrl", function($scope, $http, $state){
	$scope.app = "Entrar";
	$scope.doLogin = function(user) {
		$http.post("http://localhost:8080/chat-trembao/rest/user/login", user).then(function(response) {
			console.log("Successful: response from submitting data to server was: " + response.data.login);
			if(response.data  != '' && response.data !== null && response.data !== undefined){
				$state.go('home', {login: user.login});
				delete $scope.user;
			} else {
				$scope.msgInvalidLogin = 'Usuário e/ou senha inválidos!';
			}
		},
		
		function (response) {
		      console.log("Error: response from submitting data to server was: " + response.data);

		      //USING THE PROMISE REJECT FUNC TO CATCH ERRORS
		      deferred.reject({
		        data: response //RETURNING RESPONSE SINCE `DATA` IS NOT DEFINED
		      });
						
		
		});
	};
	
	
	
});