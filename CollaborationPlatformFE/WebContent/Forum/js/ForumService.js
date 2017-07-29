app.factory('ForumService', ['$http', '$q', '$rootScope',
    function($http, $q, $rootScope) {
	console.log("ForumService...");

		var BASE_URL='http://localhost:9500/CollaborationPlatform'
			return {

			listForums : function() {
				console.log("-->ForumService : calling 'listForums' method.");
				return $http
							.get(BASE_URL+'/forums')
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while getting Forum list...');
								return $q.reject(errResponse);
							});
			},
			
			createForum : function(forum) {
				console.log("-->ForumService : calling 'createForum' method.");
				return $http
							.post(BASE_URL+'/forum/', forum)
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while posting new Forum...');
								return $q.reject(errResponse);
							});
			},
			
			
			
			getForum : function(id) {
				console.log("-->ForumService : calling 'getForum' method with id : "+id);
				return $http
							.get(BASE_URL+'/getForum/'+id)
							.then(function(response) {
								$rootScope.selectedForum = response.data;
								return response.data;
							},
							function(errResponse) {
								console.error('Error while getting forum details');
								return $q.reject(errResponse);
							});
			},
			
		
		};
}]);

