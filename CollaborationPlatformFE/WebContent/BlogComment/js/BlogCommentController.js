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
			var currentUser = $rootScope.currentUser
			if (typeof currentUser == 'undefined') {
				alert("Please Login to post a BlogComment...")
				console.log('User not logged in , can not post a blogcomment...');
				$location.path('/user/login');
			};
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
		self.getBlogComment = function(id) {
			console.log("-->BlogCommentController : calling 'getBlogComment' method with id : "+id);
			BlogCommentService
						.getBlogComment(id)
						.then(function(d) {
							self.blogcomment = d;
							$location.path('/view_blogcomment/');
						},
						function(errResponse) {
							console.error('Error while fetching blogcomment details...')
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