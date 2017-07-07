app.factory('UserService', ['$http', '$q', '$rootScope',
    function($http, $q, $rootScope) {
	console.log("UserService...");

		var BASE_URL='http://localhost:9500/CollaborationPlatform'
			return {
				fetchAllUsers : function() {
				console.log("--> UserService : calling 'fetchAllUsers' method.");
				return $http
							.get(BASE_URL+'/users')
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while fetching UserDetails...');
								return $q.reject(errResponse);
							});
			},
			
			searchForFriends : function() {
				console.log("--> UserService : calling 'fetchAllUsers' method.");
				return $http
							.get(BASE_URL+'/searchForFriends')
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while fetching UserDetails...');
								return $q.reject(errResponse);
							});
			},
			
			
			


			
			
			createUser : function(user) {
				console.log("-->UserService : calling 'createUser' method.");
				return $http
							.post(BASE_URL+'/user/', user)
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while posting new User...');
								return $q.reject(errResponse);
							});
			},
			
			
			updateUser : function(user, id) {
				console.log("--> UserService : calling 'updateUser' method.");
				return $http
							.put(BASE_URL+'/updateUser/'+id, user)
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while updating User...');
								return $q.reject(errResponse);
							});
			},

			
			
			getUser : function(id) {
				console.log("-->UserService : calling 'getUser' method with id : "+id);
				return $http
							.get(BASE_URL+'/getUser/'+id)
							.then(function(response) {
								$rootScope.selectedUser = response.data;
								return response.data;
							},
							function(errResponse) {
								console.error('Error while getting user details');
								return $q.reject(errResponse);
							});
			},
			
			
			
            
			
			deleteUser : function(id) {
				console.log("--> UserService : calling 'deleteUser' method.");
				return $http
							delete(BASE_URL+'/deleteUser/'+id)
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while deleting User...');
								return $q.reject(errResponse);
							});
			},

			authenticate : function(user) {
				console.log("--> UserService : calling 'authenticate' method.");
				return $http
							.post(BASE_URL+'/user/login', user)
							.then(function(response) {
								if (response.data.errorMessage!="") {
									$rootScope.currentUser = {
											id: response.data.id,
											name: response.data.name,
											password: response.data.password,
											email:response.data.email,
											role: response.data.role,
											
											phone:response.data.status,
											
											isOnline:response.data.isOnline
									};
								}
								return response.data;
							},
							function(errResponse) {
								console.error('Error while authenticate User...');
								return $q.reject(errResponse);
							});
			},
			
			logout: function() {
				console.log("--> UserService : calling 'logout' method.");
				return $http
							.get(BASE_URL+'/user/logout')
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while logging out.');
								return $q.reject(errResponse);
							}
						);
			}
			
			
		
		};
}]);
