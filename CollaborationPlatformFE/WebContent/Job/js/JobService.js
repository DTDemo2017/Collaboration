app.factory('JobService', ['$http', '$q', '$rootScope',
    function($http, $q, $rootScope) {
	console.log("JobService...");

		var BASE_URL='http://localhost:9500/CollaborationPlatform'
			return {

			listJobs : function() {
				console.log("-->JobService : calling 'listJobs' method.");
				return $http
							.get(BASE_URL+'/jobs')
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while getting Job list...');
								return $q.reject(errResponse);
							});
			},
			
			createJob : function(job) {
				console.log("-->JobService : calling 'createJob' method.");
				return $http
							.post(BASE_URL+'/job/', job)
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while posting new Job...');
								return $q.reject(errResponse);
							});
			},
			
			
			getJob : function(id) {
				console.log("-->JobService : calling 'getJob' method with id : "+id);
				return $http
							.get(BASE_URL+'/getJob/'+id)
							.then(function(response) {
								$rootScope.selectedJob = response.data;
								return response.data;
							},
							function(errResponse) {
								console.error('Error while getting job details');
								return $q.reject(errResponse);
							});
			},
			
			
			
			listVacantJobs : function() {
				console.log("-->JobService : calling 'listVacantJobs' method.");
				return $http
							.get(BASE_URL+'/listVacantJobs')
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error("Error while getting all vacant jobs.");
								return $q.reject(errResponse);
							});
			},
			
			
			
			applyForJob : function(job) {
				console.log("-->JobService : calling 'applyForJob' method.", self.job);
				return $http
							.post(BASE_URL+'/jobApplication', job)
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while applying for Job...');
								return $q.reject(errResponse);
							});
			}
		
		};
}]);


			
			
			

			

			

			
			
			

			