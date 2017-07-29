var app = angular.module('myApp', ['ngRoute']);



app.config(function($routeProvider) {
  $routeProvider

  .when('/', {
    templateUrl : 'Blog/view/home.html',
    controller : 'HomeController'
   
  })

  
   .when('/blog', {
    templateUrl : 'Blog/view/post_blog.html',
    controller : 'BlogController as ctrl'
    
   
  })
  
  
  .when('/new_blogs', {
    templateUrl : 'Blog/view/list_newblogs.html',
    controller : 'BlogController as ctrl'
    
   
  })
  
  
  .when('/appr_blogs', {
    templateUrl : 'Blog/view/list_blogs.html',
    controller : 'BlogController as ctrl'
    
   
  })
  
  
  .when('/rej_blogs', {
    templateUrl : 'Blog/view/list_rejblogs.html',
    controller : 'BlogController as ctrl'
    
   
  })
  
  
  .when('/view_blog/', {
    templateUrl : 'Blog/view/view_blog.html',
    controller : 'BlogController as ctrl'
    
   
  })
  
  
   .when('/blogcomment', {
    templateUrl : 'BlogComment/view/post_blogcomment.html',
    controller : 'BlogCommentController as ctrl'
    
   
  })
  
  
  .when('/blogcomments', {
    templateUrl : 'BlogComment/view/list_blogcomments.html',
    controller : 'BlogCommentController as ctrl'
    
   
  })
  
  
  .when('/view_blogcomment/', {
    templateUrl : 'BlogComment/view/view_blogcomment.html',
    controller : 'BlogCommentController as ctrl'
    
   
  })
  
  .when('/forum', {
    templateUrl : 'Forum/view/post_forum.html',
    controller : 'ForumController as ctrl'
    
   
  })
  
  
  
  .when('/forums', {
    templateUrl : 'Forum/view/list_forums.html',
    controller : 'ForumController as ctrl'
    
   
  })
  
  
  .when('/view_forum/', {
    templateUrl : 'Forum/view/view_forum.html',
    controller : 'ForumController as ctrl'
    
   
  })
  
  
   .when('/forumcomment', {
    templateUrl : 'ForumComment/view/post_forumcomment.html',
    controller : 'ForumCommentController as ctrl'
    
   
  })
  
  
  
  .when('/forumcomments', {
    templateUrl : 'ForumComment/view/list_forumcomments.html',
    controller : 'ForumCommentController as ctrl'
    
   
  })
  
  
  .when('/view_forumcomment/', {
    templateUrl : 'ForumComment/view/view_forumcomment.html',
    controller : 'ForumCommentController as ctrl'
    
   
  })
  
  
   .when('/friend', {
    templateUrl : 'Friend/view/post_friend.html',
    controller : 'FriendController as ctrl'
    
   
  })
  
  
  
  .when('/friends', {
    templateUrl : 'Friend/view/list_friends.html',
    controller : 'FriendController as ctrl'
    
   
  })
  
  
   .when('/view_friend/', {
    templateUrl : 'Friend/view/view_friend.html',
    controller : 'FriendController as ctrl'
    
   
  })
  
  .when('/new_requests/', {
    templateUrl : 'Friend/view/new_request_list.html',
    controller : 'FriendController as ctrl'
    
   
  })
  
  
  .when('/friends_list/', {
    templateUrl : 'Friend/view/friends_list_with_U.html',
    controller : 'FriendController as ctrl'
    
   
  })
  
  
  
  .when('/job', {
    templateUrl : 'Job/view/post_job.html',
    controller : 'JobController as ctrl'
    
   
  })
  
  
   .when('/jobs', {
    templateUrl : 'Job/view/list_jobs.html',
    controller : 'JobController as ctrl'
    
   
  })
  
  
   .when('/view_job/', {
    templateUrl : 'Job/view/view_job.html',
    controller : 'JobController as ctrl'
    
   
  })
  
  
  .when('/vacantjobs', {
    templateUrl : 'Job/view/list_vacantjobs.html',
    controller : 'JobController as ctrl'
    
   
  })
  
  
  .when('/all_appliedjobs', {
    templateUrl : 'Job/view/list_appliedjobs.html',
    controller : 'JobController as ctrl'
    
   
  })
  
  
  .when('/my_appliedjobs', {
    templateUrl : 'Job/view/list_appliedjobs.html',
    controller : 'JobController as ctrl'
    
   
  })
  
  
  .when('/change_status_of_appliedjobs', {
    templateUrl : 'Job/view/list_recentappliedjobs.html',
    controller : 'JobController as ctrl'
    
   
  })
  
  .when('/user', {
    templateUrl : 'User/view/post_user.html',
    controller : 'UserController as ctrl'
    
   
  })
  
  
   .when('/users', {
    templateUrl : 'User/view/list_users.html',
    controller : 'UserController as ctrl'
    
   
  })
  
  
   .when('/view_user/', {
    templateUrl : 'User/view/view_user.html',
    controller : 'UserController as ctrl'
    
   
  })
  
  
  .when('/myProfile', {
    templateUrl : 'User/view/my_profile.html',
    controller : 'UserController as ctrl'
    
   
  })
  
  
  
  .when('/about_us', {
    templateUrl : 'Blog/view/about_us.html'
    
  })
  
  
  .when('/contact_us', {
    templateUrl : 'Blog/view/contact_us.html'
    
  })
  
  
  .when('/user/login', {
    templateUrl : 'User/view/login.html',
    controller : 'UserController as ctrl'
  })
  
  
   .when('/user/logout', {
    templateUrl : 'Blog/view/home.html',
    controller : 'UserController as ctrl'
  })

  .otherwise({redirectTo: '/'});
});

app.run(function($rootScope, $location, $http) {		//run() block gives us facility to inject any instance and constants in our application.
	console.log("--> app : entered app.run");

	$rootScope.$on('$locationChangeStart', function(event, next, current) {		//The $locationChangeStart event can be used to prevent a location change going forward.
		console.log("--> $rootScope.$on <--");
		// redirect to login page if try to access any other page rather than the restricted pages
		var restrictedPage = $.inArray($location.path(), [ '/', 
		                                                   '/user/login', 
		                                                   '/user/logout', 
		                                                   '/user', 
		                                                   '/about_us',
		                                                   '/contact_us',
		                                                   '/forums', 
		                                                   '/view_forum/', 
		                                                   '/jobs', 
		                                                   '/view_job/',
		                                                   '/all_appliedjobs',
		                                                   '/friend', 
		                                                   '/friends', 
		                                                   '/forumcomments', 
		                                                   '/view_forumcomment/', 
		                                                   '/blogcomments',
		                                                   '/view_blogcomment/']) === -1;

		console.log("restrictedPage : " + restrictedPage);
		if($rootScope.currentUser!=undefined)
		var loggedIn = $rootScope.currentUser.id;		//taking currentUser.id in $rootScope as 'loggedIn' so that we can use it throughout the session. 
		else
			var loggedIn =0;	
		console.log("loggedIn : " + loggedIn);
		if (restrictedPage && !loggedIn) {
			console.log("Navigating to login page.");
			$location.path('/user/login');
		}
	});


	// keep user logged in after page refresh...
	/*
	 * $rootScope.currentUser = $cookieStore.get('currentUser') || {}; if
	 * ($rootScope.currentUser) { $http.defaults.header.common['Authorization'] =
	 * 'Basic' + $rootScope.currentUser; }
	 */
});



