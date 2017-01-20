angular.module("chattrembao").controller("homeCtrl", function($scope, $http){
	$scope.contacts = [{userLogin: "CRIS", contactLogin: "ana"}, {userLogin: "CRIS", contactLogin: "ANDRÉ"}];
	$scope.addContact = function(contact) {
		$http.post("http://localhost:8080/chat-trembao/rest/user/addcontact", {userLogin: 'cris', contactLogin: contact.contactLogin}).then(function(response) {
			console.log("Successful: response from submitting data to server was: " + response.data);
			$scope.contacts.push(angular.copy(contact));
			$scope.message = "Contato " + contact.contactLogin + " adicionado com sucesso!";
		//	$scope.dataResponse = response.data;
			delete $scope.contact;
		},
		
		function (response) {
		      console.log("Error: response from submitting data to server was: " + response);

		      //USING THE PROMISE REJECT FUNC TO CATCH ERRORS
//		      deferred.reject({
//		        data: response //RETURNING RESPONSE SINCE `DATA` IS NOT DEFINED
//		      });
						
		
		});
	};
	
	var urlListContacts = "http://localhost:8080/chat-trembao/rest/user/listcontacts" + "/cris";
	$scope.listContacts = function() {
		$http.get(urlListContacts).then(function (response) {
				console.log("Successful: response from submitting data to server was: " + response.data);
				$scope.contacts = response.data;
			},
			
			function (response) {
			      console.log("Error: response from submitting data to server was: " + response);
			}
		);
	};
	
});