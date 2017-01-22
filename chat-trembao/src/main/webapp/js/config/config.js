angular.module('chattrembao').config(['$stateProvider', '$urlRouterProvider',
	function($stateProvider, $urlRouterProvider) {

	$stateProvider.
//	state('home', {
//	url: '/',
//	templateUrl: 'index.html',
//	}).
	state('login', {
		url: '/login',
		templateUrl: 'login.html',
	}).
	state('user', {
		url: '/user',
		templateUrl: 'cadastreUser.html',
	}).
	state('home', {
		url: '/home/:login',
		templateUrl: 'home.html',
	}).
	state('chat', {
		url: '/chat/:login/:idSessao',
		templateUrl: 'chat.html',
	});


	// default to home
//	$urlRouterProvider.otherwise('/');
}
]);