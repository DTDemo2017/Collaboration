app.controller('ForumController', ['ForumService', '$scope', '$location', '$rootScope',
	function(ForumService, $scope, $location, $rootScope) {
		console.log('ForumController...');

		var self = this;
		self.forum = {
			id : '', 
			description : '', 
			postDate : '',
			userId : '',
			status : '',
			username : '',
			errorCode : '',
			errorMessage : ''
		};

		self.forums = [];
		self.listForums = function() {
			console.log("-->ForumController : calling 'listForums' method.");
			ForumService
						.listForums()
						.then(function(d) {
							self.forums = d;
						},
						function(errResponse) {
							console.error("Error while getting forum list.")
						});
		};		
		self.listForums();
		self.createForum = function(forum) {
			console.log("-->ForumController : calling 'createForum' method.");
			ForumService
						.createForum(forum)
						.then(function(d) {
							self.forum = d;
							alert('Forum posted successfully...')
						},
						function(errResponse) {
							console.error('Error while posting new Forum...');
						});
		};
		
		
		self.getForum = function(id) {
			console.log("-->ForumController : calling 'getForum' method with id : "+id);
			ForumService
						.getForum(id)
						.then(function(d) {
							self.forum = d;
							$location.path('/view_forum/');
						},
						function(errResponse) {
							console.error('Error while fetching forum details...')
						});
		};
		
		self.submit = function() {
			{
				console.log("-->ForumController : calling 'submit()' method.", self.forum);
				self.createForum(self.forum);
				console.log('Saving new Forum', self.forum);
			}
			self.reset();
		};
		
		
		self.reset = function() {
			console.log('submit a new forum', self.forum);
			self.forum = {
					id : '', 
					description : '',
					postDate : '',
					userId : '',
					status : '', 
					errorCode : '', 
					errorMessage : ''
			};
			$scope.myForm.$setPristine();	//reset form...
		};
}]);