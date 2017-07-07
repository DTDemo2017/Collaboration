var app = angular.module('myApp', ['ngRoute']);



app.config(function($routeProvider) {
  $routeProvider

  .when('/', {
    templateUrl : 'Forum/view/home.html',
    controller : 'HomeController'
   
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

