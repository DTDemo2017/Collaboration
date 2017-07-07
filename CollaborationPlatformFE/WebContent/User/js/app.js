var app = angular.module('myApp', ['ngRoute']);



app.config(function($routeProvider) {
  $routeProvider

  .when('/', {
    templateUrl : 'User/view/home.html',
    controller : 'HomeController'
   
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
  
  
  .when('/myprofile', {
    templateUrl : 'User/view/view_user.html',
    controller : 'UserController as ctrl'
    
   
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

