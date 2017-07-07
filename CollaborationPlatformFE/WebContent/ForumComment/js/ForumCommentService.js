app.factory('ForumCommentService', ['$http', '$q', '$rootScope',
    function($http, $q, $rootScope) {
	console.log("ForumCommentService...");

		var BASE_URL='http://localhost:9500/CollaborationPlatform'
			return {

			listForumComments : function() {
				console.log("-->ForumCommentService : calling 'listForumComments' method.");
				return $http
							.get(BASE_URL+'/forumcomments')
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while getting ForumComment list...');
								return $q.reject(errResponse);
							});
			},
			
			createForumComment : function(forumcomment) {
				console.log("-->ForumCommentService : calling 'createForumComment' method.");
				return $http
							.post(BASE_URL+'/forumcomment/', forumcomment)
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while posting new ForumComment...');
								return $q.reject(errResponse);
							});
			},
			
			
		
		};
}]);
