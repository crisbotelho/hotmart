angular.module("chattrembao").controller("homeCtrl", function($scope, $http, $state, $stateParams){
	$scope.appName = 'Chat@' + $stateParams.login;
	$scope.contacts = [];
	$scope.messages = [];
	$scope.addContact = function(contact) {
		$http.post("http://localhost:8080/chat-trembao/rest/user/addcontact", {userLogin: $stateParams.login, contactLogin: contact.contactLogin}).then(function(response) {
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
	
	var urlListContacts = "http://localhost:8080/chat-trembao/rest/user/listcontacts" + "/" + $stateParams.login;
	$scope.listContacts = function() {
		$http.get(urlListContacts).then(function (response) {
				console.log("Successful: response from submitting data to server was: " + response.data);
				console.log("parâmetro: " + $stateParams.login);
				$scope.contacts = response.data;
			},
			
			function (response) {
			      console.log("Error: response from submitting data to server was: " + response);
			}
		);
	};
	
	$scope.showMwssage = function(eMessage) {
		$scope.messages.push({message : "oi oi oi oi oi"});     
    	delete $scope.chatMessage;
	}
	
	var websocket = new WebSocket("ws://localhost:8080/chat-trembao/chat");
	
	$scope.openChat = function(idUserContact) {		
		
		websocket.onopen = function() {
            var message = { userContactId: idUserContact, messageType: 'OPEN', message: 'teste', sender: $stateParams.login };
            websocket.send(JSON.stringify(message));
        };
        
        websocket.onmessage = function(e) {
        	console.log('on message: '+ e.data);
        	console.log('messages: '+ $scope.messages);
        	var textAreaMessage = document.getElementById('message');
        	var msgContainer = document.getElementById('msgContainer');
        	 if (msgContainer.childNodes.length == 100) {
                 msgContainer.removeChild(msgContainer.childNodes[0]);
             }
             
             var div = document.createElement('div');
           //  div.className = 'msgrow';
             var textnode = document.createTextNode(e.data);
             div.appendChild(textnode); 
             msgContainer.appendChild(div);
             
             msgContainer.scrollTop = msgContainer.scrollHeight;
             textAreaMessage.value = '';
        };
        
        websocket.onerror = function(e) {};
        websocket.onclose = function(e) {
            
        };
        
        var message = { userContactId: idUserContact, messageType: 'OPEN', message: 'teste', sender: $stateParams.login };
        websocket.send(JSON.stringify(message));
        
        $scope.idSessao = idUserContact;
        $state.go('chat', {login: $stateParams.login, idSessao: idUserContact});
	};
	
	
	$scope.sendMessage = function(idUserContact) {
		console.log("id sessao: " + $stateParams.idSessao);
		var message = {  userContactId: $stateParams.idSessao, messageType: 'MESSAGE', message: $scope.chatMessage, sender: $stateParams.login };
        
        // Send a message through the web-socket
        websocket.send(JSON.stringify(message));	
	};
	
});