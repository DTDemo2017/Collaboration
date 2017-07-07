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
  
  
  .when('/blogs', {
    templateUrl : 'Blog/view/list_blogs.html',
    controller : 'BlogController as ctrl'
    
   
  })
  
  
  .when('/view_blog/', {
    templateUrl : 'Blog/view/view_blog.html',
    controller : 'BlogController as ctrl'
    
   
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

