app.factory('BlogCommentService', ['$http', '$q', '$rootScope',
    function($http, $q, $rootScope) {
	console.log("BlogCommentService...");

		var BASE_URL='http://localhost:9500/CollaborationPlatform'
			return {

			listBlogComments : function() {
				console.log("-->BlogCommentService : calling 'listBlogComments' method.");
				return $http
							.get(BASE_URL+'/blogcomments')
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while getting BlogComment list...');
								return $q.reject(errResponse);
							});
			},
			
			createBlogComment : function(blogcomment) {
				console.log("-->BlogCommentService : calling 'createBlogComment' method.");
				return $http
							.post(BASE_URL+'/blogcomment/', blogcomment)
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while posting new BlogComment...');
								return $q.reject(errResponse);
							});
			},
			
			getBlogComment : function(id) {
				console.log("-->BlogCommentService : calling 'getBlogComment' method with id : "+id);
				return $http
							.get(BASE_URL+'/getBlogComment/'+id)
							.then(function(response) {
								$rootScope.selectedBlogComment = response.data;
								return response.data;
							},
							function(errResponse) {
								console.error('Error while getting blogcomment details');
								return $q.reject(errResponse);
							});
			},
			
			
		
		};
}]);

