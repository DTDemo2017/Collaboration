app.factory('BlogService', ['$http', '$q', '$rootScope',
    function($http, $q, $rootScope) {
	console.log("BlogService...");

		var BASE_URL='http://localhost:9500/CollaborationPlatform'
			return {

			listBlogs : function() {
				console.log("-->BlogService : calling 'listBlogs' method.");
				return $http
							.get(BASE_URL+'/blogs')
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while getting Blog list...');
								return $q.reject(errResponse);
							});
			},
			
			createBlog : function(blog) {
				console.log("-->BlogService : calling 'createBlog' method.");
				return $http
							.post(BASE_URL+'/blog/', blog)
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while posting new Blog...');
								return $q.reject(errResponse);
							});
			},
			
			getBlog : function(id) {
				console.log("-->BlogService : calling 'getBlog' method with id : "+id);
				return $http
							.get(BASE_URL+'/getBlog/'+id)
							.then(function(response) {
								$rootScope.selectedBlog = response.data;
								return response.data;
							},
							function(errResponse) {
								console.error('Error while getting blog details');
								return $q.reject(errResponse);
							});
			},
		
		};
}]);

