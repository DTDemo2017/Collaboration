var app = angular.module('myApp', ['ngRoute']);



app.config(function($routeProvider) {
  $routeProvider

  .when('/', {
    templateUrl : 'ForumComment/view/home.html',
    controller : 'HomeController'
   
  })

  
   .when('/forumcomment', {
    templateUrl : 'ForumComment/view/post_forumcomment.html',
    controller : 'ForumCommentController as ctrl'
    
   
  })
  
  
  
  .when('/forumcomments', {
    templateUrl : 'ForumComment/view/list_forumcomments.html',
    controller : 'ForumCommentController as ctrl'
    
   
  })
  
  
  .when('/user/login', {
    templateUrl : 'User/view/login.html',
    controller : 'UserController as ctrl'
  })
  
  
   .when('/user/logout', {
    templateUrl : 'User/view/home.html',
    controller : 'HomeController as ctrl'
  })

  .otherwise({redirectTo: '/'});
});

