angular.module("chattrembao").controller("userCtrl", function($scope, $http, $state){
	$scope.addUser = function(user) {
		$http.post("http://localhost:8080/chat-trembao/rest/user/adduser", user).then(function(response) {
			console.log("Successful: response from submitting data to server was: " + response);
			var msgContainer = document.getElementById('msgContainer');            
            var div = document.createElement('div');
            div.setAttribute('class', 'alert alert-success');
            var textnode = document.createTextNode("Usuário " + user.login + " cadastrado com sucesso!");
            div.appendChild(textnode); 
            msgContainer.appendChild(div);
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