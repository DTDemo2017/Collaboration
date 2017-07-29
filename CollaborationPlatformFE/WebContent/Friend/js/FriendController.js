app.controller('FriendController', ['FriendService', 'UserService', '$scope', '$location', '$rootScope',
	function(FriendService, UserService, $scope, $location, $rootScope) {
		console.log('FriendController...');

		var self = this;
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
		
		self.user = {
				id : '',
				name : '',
				password : '',
				email : '',
				role : '',
				status : '',
				isOnline : '',
				errorCode : '',
				errorMessage : '',
			};
			self.users = [];
			
		    
			self.listFriends = function() {
				console.log("-->FriendController : calling 'listFriends' method.");
				FriendService
							.listFriends()
							.then(function(d) {
								self.friends = d;
							},
							function(errResponse) {
								console.error("Error while getting friend list.")
							});
			};		
			self.listFriends();
			
			
			self.createFriend = function(friend) {
				console.log("-->FriendController : calling 'createFriend' method.");
				FriendService
							.createFriend(friend)
							.then(function(d) {
								self.friend = d;
								alert('Friend posted successfully...')
							},
							function(errResponse) {
								console.error('Error while posting new Friend...');
							});
			};
			
			
			self.getFriend = function(id) {
				console.log("-->FriendController : calling 'getFriend' method with id : "+id);
				FriendService
							.getFriend(id)
							.then(function(d) {
								self.friend = d;
								$location.path('/view_friend/');
							},
							function(errResponse) {
								console.error('Error while fetching friend details...')
							});
			};
			
			
			self.getMyFriends = function(d) {
				console.log("--> FriendController : calling 'getMyFriends' method.");
				var currentUser = $rootScope.currentUser
				if (typeof currentUser == 'undefined') {
					alert("Please Sign in to check Friend List or to insert a row in friend table...")
					console.log('User not logged in to check Friend List...');
					$location.path('/user/login');
				};
				FriendService.getMyFriends()
				 .then(function(d) {
							self.friends = d;
							console.log("Got the Friendlist.");
						},
						function(errResponse) {
							console.error("Error while fetching Friends.");
						});
			};
			self.getMyFriends();


            
			self.getNewFriendRequests = function() {
				console.log("--> FriendController : calling 'getNewFriendRequests' method.");
				FriendService
								.getNewFriendRequests()
								.then(function(d) {
									self.friends = d;
									console.log("Got my new friend requests.");
								},
								function(errResponse) {
									console.error("Error while fetching new friend requests.");
								});
			};
			self.getNewFriendRequests();

		
		
		
		
		
		
		
		
		self.acceptFriend = function(friend, id) {
			console.log("--> FriendController : calling 'acceptFriend' method with id : "+id);
			console.log("--> FriendController",self.friend);
			FriendService
							.acceptFriend(friend, id)
							.then(function(d) {
								self.friend = d;
								alert('Friend request accepted successfully...');
								self.getNewFriendRequests();
							},
							function(errResponse) {
								console.error("Error while updating friend.");
							});
		};
		
		self.rejectFriend = function(friend, id) {
			console.log("--> FriendController : calling 'rejectFriend' method with id : "+id);
			console.log("--> FriendController",self.friend);
			FriendService
							.rejectFriend(friend, id)
							.then(function(d) {
								self.friend = d;
								alert('Friend request rejected successfully...');
								self.getNewFriendRequests();
							},
							function(errResponse) {
								console.error("Error while updating friend.");
							});
		};
		
		self.unFriend = function(friend, id) {
			console.log("--> FriendController : calling 'unFriend' method with id : "+id);
			console.log("--> FriendController",self.friend);
			FriendService
							.unFriend(friend, id)
							.then(function(d) {
								self.friend = d;
								self.getMyFriends();
							},
							function(errResponse) {
								console.error("Error while updating friend.");
							});
		};			
		
	


		
		self.submit = function() {
			{
				console.log("-->FriendController : calling 'submit()' method.", self.friend);
				self.createFriend(self.friend);
				console.log('Saving new Friend', self.friend);
			}
			self.reset();
		};
		
		
		self.reset = function() {
			console.log('submit a new friend', self.friend);
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
			$scope.myForm.$setPristine();	//reset form...
		};
}]);