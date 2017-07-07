var app = angular.module('myApp', ['ngRoute']);



app.config(function($routeProvider) {
  $routeProvider

  .when('/', {
    templateUrl : 'BlogComment/view/home.html',
    controller : 'HomeController'
   
  })

  
   .when('/blogcomment', {
    templateUrl : 'BlogComment/view/post_blogcomment.html',
    controller : 'BlogCommentController as ctrl'
    
   
  })
  
  
  .when('/blogcomments', {
    templateUrl : 'BlogComment/view/list_blogcomments.html',
    controller : 'BlogCommentController as ctrl'
    
   
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

