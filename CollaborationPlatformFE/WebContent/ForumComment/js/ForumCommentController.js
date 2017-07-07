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