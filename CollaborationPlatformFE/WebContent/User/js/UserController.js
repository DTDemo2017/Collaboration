app.controller('UserController', ['$http', '$scope', 'UserService',  '$location', '$rootScope', '$window', 
	function($http, $scope, UserService,  $location, $rootScope, $window) {
		console.log('UserController...');

		var self = this;
		self.user = {
			id : '', 
			name : '', 
			password : '', 
			email : '',
			role : '', 
			status : '',
			isOnline : '',
			errorCode : '',
			errorMessage : ''
		};
		
		//self.users = [];
		
		
		self.userLoggedIn="";

		self.currentUser = {
			id : '',
			name : '',
			password : '',
			email : '',
			role : '',
	        status : '',
			isOnline : '',
			errorCode : '',
			errorMessage : ''
			
		};

		self.users = [];    // json array

		$scope.orderByMe = function(x) {
			$scope.myOrderBy = x;
		}

		self.friend = {
				id : '', 
				userId : '', 
				friendId : '', 
				status : '',
				isOnline : '',
				userName : '',
				friendName : '',
				friendDate : '',
				errorCode : '',
				errorMessage : ''
			};

			self.friends = [];
		

		self.fetchAllUsers = function() {
			console.log("--> UserController : calling fetchAllUsers method.");
			UserService
			           .fetchAllUsers()
			           .then(function(d) {
				         self.users = d;
			             }, 
			            function(errResponse) {
				console.error("Error while fetching Users...");
			});
		};
		self.fetchAllUsers();
		
		
		/*self.sendFriendRequest = function(friendId) {
			console.log("--> UserController : calling 'sendFriendRequest' method with friendId : "+friendId);
			var currentUser = $rootScope.currentUser
			if (typeof currentUser == 'undefined') {
				alert("Please Login to send a friend request...")
				console.log('User not logged in , can not send a friend request...');
				$location.path('/login');
			};
			UserService
							.sendFriendRequest(friendId)
							.then(function(d) {
								self.friend = d;
								alert("Friend request sent successfully...")
								self.fetchAllUsers();
								console.log("-->UserController : ", self.friend);
								console.log("-->UserController : ", self.user);
							},
							function(errResponse) {
								console.error("Error while fetching friends.");
							});
		};*/
		
		self.sendFriendRequest = function(user) {   //I made this method
			  console.log("-->UserController : calling 'sendFriendRequest' method.");
			  var currentUser = $rootScope.currentUser
						if (typeof currentUser == 'undefined') {
							alert("Please Login to send a Friend Request...")
							console.log('User not logged in , can not send a Friend Request...');
							$location.path('/user/login');
						};
			                UserService
									.sendFriendRequest(user)
									.then(function(d) {
										self.friend = d;
										alert("You have successfully sent a friend request...");
										self.fetchAllUsers();
										console.log("-->UserController : ", self.friend);
										console.log("-->UserController : ", self.user);
									},
									function(errResponse) {
										console.error('Error while sending friend request...')
									});

					};

			self.send = function() {   //I made this method
						console.log("-->UserController : calling 'send()' method.", self.user);
						self.sendFriendRequest(user);
						console.log('Friend Request sent successfully...', user);
					};


		
		self.createUser = function(user) {
			console.log("-->UserController : calling 'createUser' method.");
			UserService
						.createUser(user)
						.then(function(d) {
							self.user = d;
							alert('User posted successfully...')
						},
						function(errResponse) {
							console.error('Error while posting new User...');
						});
		};
		
		
		self.myProfile = function() {
			console.log("myProfile...")
			UserService
					.myProfile()
					.then(
							function(d) {
								self.user = d;
								$location
										.path("/myProfile")
							},
							function(errResponse) {
								console
										.error('Error while fetch profile.');
							});
		};

		
		
		self.updateUser = function(user, id) {
			console.log("--> UserController : calling 'updateUser' method.");
			UserService.updateUser(user, id).then(function(d) {
				self.user = d;
				$location.path('/myProfile');
				}, function(errResponse) {
					console.error('--> UserController : Error while updating User...');
				});
		};

		
		
		self.getUser = function(id) {
			console.log("-->UserController : calling 'getUser' method with id : "+id);
			UserService
						.getUser(id)
						.then(function(d) {
							self.user = d;
							$location.path('/view_user/');
						},
						function(errResponse) {
							console.error('Error while fetching user details...')
						});
		};
		
		
		/*self.updateUser = function(user, id) {
			console.log("--> UserController : calling updateUser method.");
			UserService.updateUser(user, id).then(function(d) {
				self.users = d;
				$location.path('/myprofile');
				}, function(errResponse) {
					console.error('--> UserController : Error while updating User...');
				});
		};*/
		
		self.authenticate = function(user) {
			console.log("--> UserController : calling authenticate method.");
			UserService.authenticate(user).then(function(d) {
				self.user = d;
				console.log("user.errorCode : "+self.user.errorCode);
				if(self.user.errorCode == "404") {
					alert("Invalid Credentials. Please try again.")
					
					self.user.id = "";
					self.user.password = "";
				} else {
					console.log("Valid Credentials. Navigating to home page.");
					$http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.currentUser;
					//$cookieStore.put('currentUser', $rootScope.currentUser);
					$location.path('/');
				}
			}, 
			function(errResponse) {
				console.error('Error while authenticate User...');
			});
		};
		

		/*self.logout = function() {
			console.log("--> UserController : calling logout method.");
			$rootScope.currentUser = {};
			$cookieStore.remove('currentUser');
			UserService.logout();
			console.log("-->UserController : User Logged out.");
			
			$window.location.reload();
			$location.path('/');
		}*/
		
		self.logout = function() {
			console.log("--> UserController : calling logout method.");
			
			$rootScope.currentUser = {};
			//$localStorage.currentUser.remove('currentUser');
			//$cookieStore.remove('currentUser');
			UserService.logout();
			
			console.log("-->UserController : User Logged out.");
			
			$window.location.reload();
			$location.path('/');
		}

		
		

		self.deleteUser = function(id) {
			console.log("--> UserController : calling deleteUser function.");
			UserService.deleteUser(id).then(self.fetchAllUsers,
					function(errResponse) {
						console.error('Error while deleting User...');
					});
		};

		self.fetchAllUsers();

		self.login = function() {
			{
				console.log('login validation ??????????', self.user);
				self.authenticate(self.user);
			}
		};

		self.register = function() {
			{
				console.log("--> UserController : calling register() method.", self.user);
				self.createUser(self.user);
				console.log('Saving new user...');
			}
			$location.path('/user/login');
			self.reset();
		};
		
		self.submit = function() {
			{
				console.log("-->UserController : calling 'submit()' method.", self.user);
				self.createUser(self.user)
				console.log('Saving new User', self.user);
			}
			self.reset();
		};
		
		self.reset = function() {
			console.log('submit a new user', self.user);
			self.user = {
					id : '', 
					name : '', 
					password : '', 
					email : '',
					role : '', 
					status : '', 
					isOnline : '',
					errorCode : '', 
					errorMessage : ''
			};
			$scope.myForm.$setPristine();	//reset form...
		};
		
		
		
}]);