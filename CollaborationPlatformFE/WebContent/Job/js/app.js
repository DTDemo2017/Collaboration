var app = angular.module('myApp', ['ngRoute']);



app.config(function($routeProvider) {
  $routeProvider

  .when('/', {
    templateUrl : 'Job/view/home.html',
    controller : 'HomeController'
   
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

