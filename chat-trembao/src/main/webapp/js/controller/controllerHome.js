angular.module("chattrembao").controller("homeCtrl", function($scope, $http, $state, $stateParams){
	$scope.appName = 'Chat@' + $stateParams.login;
	$scope.appChatName =  $stateParams.login + '@' + $stateParams.contactLogin;
	$scope.onlineContacts = [];
	$scope.offlineContacts = [];
	$scope.messages = [];
	$scope.addContact = function(contact) {
		$http.post("http://localhost:8080/chat-trembao/rest/user/addcontact", {userLogin: $stateParams.login, contactLogin: contact.contactLogin}).then(function(response) {
			console.log("Successful: response from submitting data to server was: " + response.data);
			$scope.contacts.push(angular.copy(contact));
			var msgContainer = document.getElementById('msgContainer');            
            var div = document.createElement('div');
            div.setAttribute('class', 'alert alert-success');
            var textnode = document.createTextNode("Contato " + contact.contactLogin + " adicionado com sucesso!");
            div.appendChild(textnode); 
            msgContainer.appendChild(div);
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
	
	var urlListOnlineContacts = "http://localhost:8080/chat-trembao/rest/user/listonlinecontacts" + "/" + $stateParams.login;
	var urlListOfflineContacts = "http://localhost:8080/chat-trembao/rest/user/listofflinecontacts" + "/" + $stateParams.login;
	$scope.listContacts = function() {
		$http.get(urlListOnlineContacts).then(function (response) {
				console.log("Successful: response from submitting data to server was: " + response.data);
				$scope.onlineContacts = response.data;
			},
			
			function (response) {
			      console.log("Error: response from submitting data to server was: " + response);
			}
		);
		
		$http.get(urlListOfflineContacts).then(function (response) {
			console.log("Successful: response from submitting data to server was: " + response.data);
			$scope.offlineContacts = response.data;
		},
		
		function (response) {
		      console.log("Error: response from submitting data to server was: " + response);
		}
		);
	};
	
	var websocket = new WebSocket("ws://localhost:8080/chat-trembao/chat");
	
	$scope.openChat = function(idUserContact, contactLogin) {		
		
		websocket.onopen = function() {
            var message = { userContactId: idUserContact, messageType: 'OPEN', message: 'teste', sender: $stateParams.login, receiver: '' };
            websocket.send(JSON.stringify(message));
        };
        
        websocket.onmessage = function(e) {
        	var textAreaMessage = document.getElementById('message');
        	var msgContainer = document.getElementById('msgContainer');
        	 if (msgContainer.childNodes.length == 100) {
                 msgContainer.removeChild(msgContainer.childNodes[0]);
             }
             
             var div = document.createElement('div');
             var textnode = document.createTextNode(e.data);
             div.appendChild(textnode); 
             msgContainer.appendChild(div);
             
             msgContainer.scrollTop = msgContainer.scrollHeight;
             textAreaMessage.value = '';
        };
        
        websocket.onerror = function(e) {};
        websocket.onclose = function(e) {
            
        };
        
        var message = { userContactId: idUserContact, messageType: 'OPEN', message: 'teste', sender: $stateParams.login, receiver:'' };
        websocket.send(JSON.stringify(message));
        
        $scope.idSessao = idUserContact;
        $scope.contactLogin = contactLogin;
        $state.go('chat', {login: $stateParams.login, idSessao: idUserContact, contactLogin: contactLogin});
	};
	
	
	$scope.sendMessage = function(idUserContact) {
		var message = {  userContactId: $stateParams.idSessao, messageType: 'MESSAGE', message: $scope.chatMessage, 
							sender: $stateParams.login, receiver: $stateParams.contactLogin };
        
        // Send a message through the web-socket
        websocket.send(JSON.stringify(message));	
	};
	
	$scope.doLogOut = function() {
		$http.post("http://localhost:8080/chat-trembao/rest/user/dologout", {userLogin: $stateParams.login, contactLogin: ' '}).then(function(response) {
			$state.go('login');
		},
		
		function (response) {
		      console.log("Error: response from submitting data to server was: " + response);

		      //USING THE PROMISE REJECT FUNC TO CATCH ERRORS
//		      deferred.reject({
//		        data: response //RETURNING RESPONSE SINCE `DATA` IS NOT DEFINED
//		      });
						
		
		});
	};
	$scope.listContacts();
});

