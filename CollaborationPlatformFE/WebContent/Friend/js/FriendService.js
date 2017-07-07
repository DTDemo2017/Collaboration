app.factory('FriendService', ['$http', '$q', '$rootScope',
    function($http, $q, $rootScope) {
	console.log("FriendService...");

		var BASE_URL='http://localhost:9500/CollaborationPlatform'
			return {

			listFriends : function() {
				console.log("-->FriendService : calling 'listFriends' method.");
				return $http
							.get(BASE_URL+'/friends')
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while getting Friend list...');
								return $q.reject(errResponse);
							});
			},
			
			createFriend : function(friend) {
				console.log("-->FriendService : calling 'createFriend' method.");
				return $http
							.post(BASE_URL+'/friend/', friend)
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while posting new Friend...');
								return $q.reject(errResponse);
							});
			},
			
			
			
			getFriend : function(id) {
				console.log("-->FriendService : calling 'getFriend' method with id : "+id);
				return $http
							.get(BASE_URL+'/getFriend/'+id)
							.then(function(response) {
								$rootScope.selectedFriend = response.data;
								return response.data;
							},
							function(errResponse) {
								console.error('Error while getting friend details');
								return $q.reject(errResponse);
							});
			},
			
		
		};
}]);

