app.controller('BlogCommentController', ['BlogCommentService', '$scope', '$location', '$rootScope',
	function(BlogCommentService, $scope, $location, $rootScope) {
		console.log('BlogCommentController...');

		var self = this;
		self.blogcomment = {
			id : '', 
			blogId : '', 
			comments : '', 
			commentDate : '',
			userId : '',
			userName : '',
			blogName : '',
			errorCode : '',
			errorMessage : ''
		};

		self.blogcomments = [];
		self.listBlogComments = function() {
			console.log("-->BlogCommentController : calling 'listBlogComments' method.");
			BlogCommentService
						.listBlogComments()
						.then(function(d) {
							self.blogcomments = d;
						},
						function(errResponse) {
							console.error("Error while getting blogcomment list.")
						});
		};		
		self.listBlogComments();
		self.createBlogComment = function(blogcomment) {
			console.log("-->BlogCommentController : calling 'createBlogComment' method.");
			BlogCommentService
						.createBlogComment(blogcomment)
						.then(function(d) {
							self.blogcomment = d;
							alert('BlogComment posted successfully...')
						},
						function(errResponse) {
							console.error('Error while posting new BlogComment...');
						});
		};
		
		self.submit = function() {
			{
				console.log("-->BlogCommentController : calling 'submit()' method.", self.blogcomment);
				self.createBlogComment(self.blogcomment);
				console.log('Saving new BlogComment', self.blogcomment);
			}
			self.reset();
		};
		
		
		self.reset = function() {
			console.log('submit a new blogcomment', self.blogcomment);
			self.blogcomment = {
					id : '', 
					blogId : '', 
					comments : '',
					commentDate : '',
					userId : '',
					userName : '',
					blogName : '',
					errorCode : '', 
					errorMessage : ''
			};
			$scope.myForm.$setPristine();	//reset form...
		};
}]);