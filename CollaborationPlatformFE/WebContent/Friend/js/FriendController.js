app.controller('FriendController', ['FriendService', '$scope', '$location', '$rootScope',
	function(FriendService, $scope, $location, $rootScope) {
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