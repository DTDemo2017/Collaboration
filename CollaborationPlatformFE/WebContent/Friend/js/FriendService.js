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
							.get(BASE_URL+'/user/getFriend/'+id)
							.then(function(response) {
								$rootScope.selectedFriend = response.data;
								return response.data;
							},
							function(errResponse) {
								console.error('Error while getting friend details');
								return $q.reject(errResponse);
							});
			},
			
			
			
			getMyFriends : function() {
				console.log("--> FriendService : calling 'getMyFriends' method.");
				return $http
						.get(BASE_URL + '/user/myFriends')
						.then(function(response) {
									return response.data;
								},
								function(errResponse) {
									console.error("-->FriendService : Error while fetching all my friends.");
									return $q.reject(errResponse);
								});
			},

			
			getNewFriendRequests : function() {
				console.log("--> FriendService : calling 'getNewFriendRequests' method.");
				return $http.get(BASE_URL+'/user/newFriendRequests')
				.then(function(response) {
							$rootScope.newRequest = response.data;
							return response.data;
						},
						function(errResponse) {
							console.error("-->FriendService : Error while fetching new friend requests.");
							return $q.reject(errResponse);
						});
			},
			
			
			
			acceptFriend : function(friend, id) {
				console.log("--> FriendService : calling 'acceptFriend' method with id : "+id);
				return $http.put(BASE_URL+'/user/acceptFriend/'+id, friend)
				.then(function(response) {
							return response.data;
						},
						function(errResponse) {
							console.error("-->FriendService : Error while accepting friend request.")
						});
			},
			
			rejectFriend : function(friend, id) {
				console.log("--> FriendService : calling 'rejectFriend' method with id : "+id);
				return $http.put(BASE_URL+'/user/rejectFriend/'+id, friend)
				.then(function(response) {
							return response.data;
						},
						function(errResponse) {
							console.error("-->FriendService : Error while rejecting friend request.")
						});
			},
			
			unFriend : function(friend, id) {
				console.log("--> FriendService : calling 'unFriend' method with id : "+id);
				return $http.put(BASE_URL+'/user/unFriend/'+id, friend)
				.then(function(response) {
							return response.data;
						},
						function(errResponse) {
							console.error("-->FriendService : Error while unFriending existing friend.")
						});
			},
		
		};
}]);

