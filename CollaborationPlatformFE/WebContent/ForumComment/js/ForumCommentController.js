app.controller('ForumCommentController', ['ForumCommentService', '$scope', '$location', '$rootScope',
	function(ForumCommentService, $scope, $location, $rootScope) {
		console.log('ForumCommentController...');

		var self = this;
		self.forumcomment = {
			id : '', 
			forumId : '', 
			comments : '', 
			userId : '',
			commentDate : '',
			forumName : '',
			userName : '',
			errorCode : '',
			errorMessage : ''
		};

		self.forumcomments = [];
		self.listForumComments = function() {
			console.log("-->ForumCommentController : calling 'listForumComments' method.");
			ForumCommentService
						.listForumComments()
						.then(function(d) {
							self.forumcomments = d;
						},
						function(errResponse) {
							console.error("Error while getting forumcomment list.")
						});
		};		
		self.listForumComments();
		self.createForumComment = function(forumcomment) {
			console.log("-->ForumCommentController : calling 'createForumComment' method.");
			var currentUser = $rootScope.currentUser
			if (typeof currentUser == 'undefined') {
				alert("Please Login to post a ForumComment...")
				console.log('User not logged in , can not post a forumcomment...');
				$location.path('/user/login');
			};
			ForumCommentService
						.createForumComment(forumcomment)
						.then(function(d) {
							self.forumcomment = d;
							alert('ForumComment posted successfully...')
						},
						function(errResponse) {
							console.error('Error while posting new ForumComment...');
						});
		};
		self.getForumComment = function(id) {
			console.log("-->ForumCommentController : calling 'getForumComment' method with id : "+id);
			ForumCommentService
						.getForumComment(id)
						.then(function(d) {
							self.forumcomment = d;
							$location.path('/view_forumcomment/');
						},
						function(errResponse) {
							console.error('Error while fetching forumcomment details...')
						});
		};
		
		self.submit = function() {
			{
				console.log("-->ForumCommentController : calling 'submit()' method.", self.forumcomment);
				self.createForumComment(self.forumcomment);
				console.log('Saving new ForumComment', self.forumcomment);
			}
			self.reset();
		};
		
		
		self.reset = function() {
			console.log('submit a new forumcomment', self.forumcomment);
			self.forumcomment = {
					id : '', 
					forumId : '', 
					comments : '',
					userId : '',
					commentDate : '',
					forumName : '',
					userName : '',
					errorCode : '', 
					errorMessage : ''
			};
			$scope.myForm.$setPristine();	//reset form...
		};
}]);