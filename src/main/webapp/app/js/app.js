
if (typeof $ === 'undefined') { throw new Error('This application\'s JavaScript requires jQuery'); }


var App = angular.module('angle', [
    'ngRoute',
    'ngAnimate',
    'ngStorage',
    'ngCookies',
    'pascalprecht.translate',
    'ui.bootstrap',
    'ui.router',
    'oc.lazyLoad',
    'cfp.loadingBar',
    'ngSanitize',
    'ngResource',
    'tmh.dynamicLocale',
    'ui.utils'
  ]);

App.run(["$rootScope", "$state", "$stateParams",  '$window', '$templateCache', function ($rootScope, $state, $stateParams, $window, $templateCache) {
  // Set reference to access them from any scope
  $rootScope.$state = $state;
  $rootScope.$stateParams = $stateParams;
  $rootScope.$storage = $window.localStorage;


  $rootScope.app = {
	name: '聚优顾',
    description: '聚优顾后台',
    year: ((new Date()).getFullYear()),
    layout: {
      isFixed: true,
      isCollapsed: true,
      isBoxed: false,
      isRTL: false,
      horizontal: false,
      isFloat: false,
      asideHover: false,
      theme: null
    },
    useFullLayout: false,
    hiddenFooter: false,
    viewAnimation: 'ng-fadeInUp'
  };

}]);

/**=========================================================
 * Module: config.js
 * App routes and resources configuration
 =========================================================*/

App.config(['$stateProvider', '$locationProvider', '$urlRouterProvider', 'RouteHelpersProvider',
function ($stateProvider, $locationProvider, $urlRouterProvider, helper) {
  'use strict';

  $locationProvider.html5Mode(false);

/*  $urlRouterProvider.otherwise('/app/main');*/
  $urlRouterProvider.otherwise('/page/login');

  // Application Routes
  // -----------------------------------   
  $stateProvider
    .state('app', {
        url: '/app',
        abstract: true,
        templateUrl: helper.basepath('app.html'),
        controller: 'AppController',
        resolve: helper.resolveFor('fastclick', 'modernizr', 'icons', 'screenfull', 'animo', 'sparklines', 'slimscroll', 'classyloader', 'toaster', 'whirl')
    })
     .state('app.syManagementAccount', {
        url: '/syManagementAccount',
        title: '账号管理',
        templateUrl: helper.basepath('syManagementAccount.html'),
         resolve: helper.resolveFor('datatables','ngGrid','bootstrapJs','xeditable')
    })
     .state('app.syManagementActer', {
        url: '/syManagementActer',
        title: '角色管理',
        templateUrl: helper.basepath('syManagementActer.html'),
        resolve: helper.resolveFor('datatables','ngGrid','bootstrapJs','xeditable')
    })
     .state('app.syManagementDialog', {
        url: '/syManagementDialog',
        title: '日志管理',
		templateUrl: helper.basepath('syManagementDialog.html'),
		  resolve: helper.resolveFor('datatables','ngGrid','bootstrapJs','xeditable')
    })
      .state('app.loginInfo', {
        url: '/loginInfo',
        title: '登录信息',
        templateUrl: helper.basepath('loginInfo.html'),
        resolve: helper.resolveFor('datatables','ngGrid','bootstrapJs','xeditable')
    })
       .state('app.accountInfo', {
        url: '/accountInfo',
        title: '账户信息',
        templateUrl: helper.basepath('accountInfo.html'),
        resolve: helper.resolveFor('datatables','ngGrid','bootstrapJs','xeditable')
    })
        .state('app.certification', {
        url: '/certification',
        title: '实名认证',
        templateUrl: helper.basepath('certification.html'),
        resolve: helper.resolveFor('datatables','ngGrid','bootstrapJs','xeditable')
    })
     .state('app.certificationjyg', {
        url: '/certificationjyg',
        title: '实名认证',
        templateUrl: helper.basepath('certificationjyg.html'),
        resolve: helper.resolveFor('datatables','ngGrid','bootstrapJs','xeditable')
    })
     .state('app.transactionInfo', {
        url: '/transactionInfo',
        title: '交易信息',
        templateUrl: helper.basepath('transactionInfo.html'),
        resolve: helper.resolveFor('datatables','ngGrid','bootstrapJs','xeditable')
    })
     
      .state('app.youguInfo', {
        url: '/youguInfo',
        title: '优顾信息',
        templateUrl: helper.basepath('youguInfo.html'),
        resolve: helper.resolveFor('datatables','ngGrid','bootstrapJs','xeditable')
    })
      
       .state('app.gameRaw', {
        url: '/gameRaw',
        title: '新手赛-交易信息',
        templateUrl: helper.basepath('gameRaw.html'),
        resolve: helper.resolveFor('datatables','ngGrid','bootstrapJs','xeditable')
    })
        .state('app.gamePro', {
        url: '/gamePro',
        title: '专业赛-交易信息',
        templateUrl: helper.basepath('gamePro.html'),
        resolve: helper.resolveFor('datatables','ngGrid','bootstrapJs','xeditable')
    })
      
      
      
     .state('app.main', {
        url: '/main',
        title: '首页',
        templateUrl: helper.basepath('main.html'),
        resolve: helper.resolveFor('datatables','ngGrid','bootstrapJs','xeditable')
    })
     .state('app.gameManagement', {
        url: '/gameManagement',
        title: '比赛信息',
        templateUrl: helper.basepath('gameManagement.html'),
        resolve: helper.resolveFor('datatables','ngGrid','bootstrapJs','xeditable')
    })
	   
	   
  	.state('app.rechargeRecord', {
        url: '/rechargeRecord',
        title: '充值记录',
        templateUrl: helper.basepath('rechargeRecord.html'),
        resolve: helper.resolveFor('datatables','ngGrid','bootstrapJs','xeditable')
    })
  	.state('app.purchaseHistory', {
        url: '/purchaseHistory',
        title: '消费记录',
        templateUrl: helper.basepath('purchaseHistory.html'),
        resolve: helper.resolveFor('datatables','ngGrid','bootstrapJs','xeditable')
    })
  	.state('app.bonusRecord', {
        url: '/bonusRecord',
        title: '奖金发放记录',
        templateUrl: helper.basepath('bonusRecord.html'),
        resolve: helper.resolveFor('datatables','ngGrid','bootstrapJs','xeditable')
    })
  	.state('app.cashManagement', {
        url: '/cashManagement',
        title: '出金管理',
        templateUrl: helper.basepath('cashManagement.html'),
        resolve: helper.resolveFor('datatables','ngGrid','bootstrapJs','xeditable')
    })
	   
     
 	.state('app.infoDelivery', {
        url: '/infoDelivery',
        title: '公告发布',
        templateUrl: helper.basepath('infoDelivery.html'),
        resolve: helper.resolveFor('datatables','ngGrid','bootstrapJs','xeditable')
    })
    .state('app.infoDeliveryNews', {
        url: '/infoDeliveryNews',
        title: '资讯发布',
        templateUrl: helper.basepath('infoDeliveryNews.html'),
        resolve: helper.resolveFor('datatables','ngGrid','bootstrapJs','xeditable')
    })
	.state('app.marketControl', {
        url: '/marketControl',
        title: '市场管控',
        templateUrl: helper.basepath('marketControl.html'),
        resolve: helper.resolveFor('datatables','ngGrid','bootstrapJs','xeditable')
	})
	.state('app.messageList', {
        url: '/messageList',
        title: '消息列表',
        templateUrl: helper.basepath('messageList.html'),
        resolve: helper.resolveFor('datatables','ngGrid','bootstrapJs','xeditable')
	})
	.state('app.commentManagement', {
        url: '/commentManagement',
        title: '评论管理',
        templateUrl: helper.basepath('commentManagement.html'),
        resolve: helper.resolveFor('datatables','ngGrid','bootstrapJs','xeditable')
	})
	.state('app.mAdvertisement', {
        url: '/mAdvertisement',
        title: '广告图管理',
        templateUrl: helper.basepath('mAdvertisement.html'),
        resolve: helper.resolveFor('datatables','ngGrid','bootstrapJs','xeditable')
	})
	.state('app.mMail', {
        url: '/mMail',
        title: '商城管理',
        templateUrl: helper.basepath('mMail.html'),
        resolve: helper.resolveFor('datatables','ngGrid','bootstrapJs','xeditable')
    })
	.state('app.mExchangeOrder', {
        url: '/mExchangeOrder',
        title: '兑换订单',
        templateUrl: helper.basepath('mExchangeOrder.html'),
        resolve: helper.resolveFor('datatables','ngGrid','bootstrapJs','xeditable')
    })
	
	
      .state('page.login', {
        url: '/login',
        title: "Login",
        templateUrl: 'app/pages/login.html'
    })
     
   	//  // Single Page Routes
//  // ----------------------------------- 
    .state('page', {
        url: '/page',
        templateUrl: 'app/pages/page.html',
        resolve: helper.resolveFor('modernizr', 'icons'),
        controller: ["$rootScope", function($rootScope) {
            $rootScope.app.layout.isBoxed = false;
        }]
    })
    .state('page.register', {
        url: '/register',
        title: "Register",
        templateUrl: 'app/pages/register.html'
    })
    .state('page.recover', {
        url: '/recover',
        title: "Recover",
        templateUrl: 'app/pages/recover.html'
    })
    .state('page.lock', {
        url: '/lock',
        title: "Lock",
        templateUrl: 'app/pages/lock.html'
    })
    .state('page.404', {
        url: '/404',
        title: "Not Found",
        templateUrl: 'app/pages/404.html'
    }) ;


}]).config(['$ocLazyLoadProvider', 'APP_REQUIRES', function ($ocLazyLoadProvider, APP_REQUIRES) {
    'use strict';

    // Lazy Load modules configuration
    $ocLazyLoadProvider.config({
      debug: false,
      events: true,
      modules: APP_REQUIRES.modules
    });

}]).config(['$controllerProvider', '$compileProvider', '$filterProvider', '$provide',
    function ( $controllerProvider, $compileProvider, $filterProvider, $provide) {
      'use strict';
      // registering components after bootstrap
      App.controller = $controllerProvider.register;
      App.directive  = $compileProvider.directive;
      App.filter     = $filterProvider.register;
      App.factory    = $provide.factory;
      App.service    = $provide.service;
      App.constant   = $provide.constant;
      App.value      = $provide.value;

}]).config(['$translateProvider', function ($translateProvider) {

    $translateProvider.useStaticFilesLoader({
        prefix : 'app/i18n/',
        suffix : '.json'
    });
    $translateProvider.preferredLanguage('en');
    $translateProvider.useLocalStorage();
    $translateProvider.usePostCompiling(true);

}]).config(['tmhDynamicLocaleProvider', function (tmhDynamicLocaleProvider) {

    tmhDynamicLocaleProvider.localeLocationPattern('vendor/angular-i18n/angular-locale_{{locale}}.js');

    // tmhDynamicLocaleProvider.useStorage('$cookieStore');

}]).config(['cfpLoadingBarProvider', function(cfpLoadingBarProvider) {

    cfpLoadingBarProvider.includeBar = true;
    cfpLoadingBarProvider.includeSpinner = false;
    cfpLoadingBarProvider.latencyThreshold = 500;
    cfpLoadingBarProvider.parentSelector = '.wrapper > section';

}]).config(['$tooltipProvider', function ($tooltipProvider) {

    $tooltipProvider.options({appendToBody: true});

}])
;

/**=========================================================
 * Module: constants.js
 * Define constants to inject across the application
 =========================================================*/
App
  .constant('APP_COLORS', {
    'primary':                '#5d9cec',
    'success':                '#27c24c',
    'info':                   '#23b7e5',
    'warning':                '#ff902b',
    'danger':                 '#f05050',
    'inverse':                '#131e26',
    'green':                  '#37bc9b',
    'pink':                   '#f532e5',
    'purple':                 '#7266ba',
    'dark':                   '#3a3f51',
    'yellow':                 '#fad732',
    'gray-darker':            '#232735',
    'gray-dark':              '#3a3f51',
    'gray':                   '#dde6e9',
    'gray-light':             '#e4eaec',
    'gray-lighter':           '#edf1f2'
  })
  .constant('APP_MEDIAQUERY', {
    'desktopLG':             1200,
    'desktop':                992,
    'tablet':                 768,
    'mobile':                 480
  })
  .constant('APP_REQUIRES', {
    // jQuery based and standalone scripts
    scripts: {
      'whirl':              ['vendor/whirl/dist/whirl.css'],
      'classyloader':       ['vendor/jquery-classyloader/js/jquery.classyloader.min.js'],
      'animo':              ['vendor/animo.js/animo.js'],
      'fastclick':          ['vendor/fastclick/lib/fastclick.js'],
      'modernizr':          ['vendor/modernizr/modernizr.js'],
      'animate':            ['vendor/animate.css/animate.min.css'],
      'icons':              ['vendor/skycons/skycons.js',
                             'vendor/fontawesome/css/font-awesome.min.css',
                             'vendor/simple-line-icons/css/simple-line-icons.css',
                             'vendor/weather-icons/css/weather-icons.min.css'],
      'sparklines':         ['app/vendor/sparklines/jquery.sparkline.min.js'],
      'wysiwyg':            ['vendor/bootstrap-wysiwyg/bootstrap-wysiwyg.js',
                             'vendor/bootstrap-wysiwyg/external/jquery.hotkeys.js'],
      'slimscroll':         ['vendor/slimScroll/jquery.slimscroll.min.js'],
      'screenfull':         ['vendor/screenfull/dist/screenfull.js'],
      'vector-map':         ['vendor/ika.jvectormap/jquery-jvectormap-1.2.2.min.js',
                             'vendor/ika.jvectormap/jquery-jvectormap-1.2.2.css'],
      'vector-map-maps':    ['vendor/ika.jvectormap/jquery-jvectormap-world-mill-en.js',
                             'vendor/ika.jvectormap/jquery-jvectormap-us-mill-en.js'],
      'loadGoogleMapsJS':   ['app/vendor/gmap/load-google-maps.js'],
      'flot-chart':         ['vendor/Flot/jquery.flot.js'],
      'flot-chart-plugins': ['vendor/flot.tooltip/js/jquery.flot.tooltip.min.js',
                             'vendor/Flot/jquery.flot.resize.js',
                             'vendor/Flot/jquery.flot.pie.js',
                             'vendor/Flot/jquery.flot.time.js',
                             'vendor/Flot/jquery.flot.categories.js',
                             'vendor/flot-spline/js/jquery.flot.spline.min.js'],
                            // jquery core and widgets
      'jquery-ui':          ['vendor/jquery-ui/ui/core.js',
                             'vendor/jquery-ui/ui/widget.js'],
                             // loads only jquery required modules and touch support
      'jquery-ui-widgets':  ['vendor/jquery-ui/ui/core.js',
                             'vendor/jquery-ui/ui/widget.js',
                             'vendor/jquery-ui/ui/mouse.js',
                             'vendor/jquery-ui/ui/draggable.js',
                             'vendor/jquery-ui/ui/droppable.js',
                             'vendor/jquery-ui/ui/sortable.js',
                             'vendor/jqueryui-touch-punch/jquery.ui.touch-punch.min.js'],
      'moment' :            ['vendor/moment/min/moment-with-locales.min.js'],
      'inputmask':          ['vendor/jquery.inputmask/dist/jquery.inputmask.bundle.min.js'],
      'flatdoc':            ['vendor/flatdoc/flatdoc.js'],
      'codemirror':         ['vendor/codemirror/lib/codemirror.js',
                             'vendor/codemirror/lib/codemirror.css'],
      // modes for common web files
      'codemirror-modes-web': ['vendor/codemirror/mode/javascript/javascript.js',
                               'vendor/codemirror/mode/xml/xml.js',
                               'vendor/codemirror/mode/htmlmixed/htmlmixed.js',
                               'vendor/codemirror/mode/css/css.js'],
      'taginput' :          ['vendor/bootstrap-tagsinput/dist/bootstrap-tagsinput.css',
                             'vendor/bootstrap-tagsinput/dist/bootstrap-tagsinput.min.js'],
      'filestyle':          ['vendor/bootstrap-filestyle/src/bootstrap-filestyle.js'],
      'parsley':            ['vendor/parsleyjs/dist/parsley.min.js'],
      'fullcalendar':       ['vendor/fullcalendar/dist/fullcalendar.min.js',
                             'vendor/fullcalendar/dist/fullcalendar.css'],
      'gcal':               ['vendor/fullcalendar/dist/gcal.js'],
      'chartjs':            ['vendor/Chart.js/Chart.js'],
      'morris':             ['vendor/raphael/raphael.js',
                             'vendor/morris.js/morris.js',
                             'vendor/morris.js/morris.css'],
      'loaders.css':          ['vendor/loaders.css/loaders.css'],
      'spinkit':              ['vendor/spinkit/css/spinkit.css'],
      'bootstrapCss':              ['vendor/loaders.css/bootstrap.min.css'],
      'bootstrapJs':  			 ['vendor/Flot/bootstrap.min.js'],
    },
    // Angular based script (use the right module name)
    modules: [
      {name: 'toaster',                   files: ['vendor/angularjs-toaster/toaster.js',
                                                 'vendor/angularjs-toaster/toaster.css']},
      {name: 'localytics.directives',     files: ['vendor/chosen_v1.2.0/chosen.jquery.min.js',
                                                 'vendor/chosen_v1.2.0/chosen.min.css',
                                                 'vendor/angular-chosen-localytics/chosen.js']},
      {name: 'ngDialog',                  files: ['vendor/ngDialog/js/ngDialog.min.js',
                                                 'vendor/ngDialog/css/ngDialog.min.css',
                                                 'vendor/ngDialog/css/ngDialog-theme-default.min.css'] },
      {name: 'ngWig',                     files: ['vendor/ngWig/dist/ng-wig.min.js'] },
      {name: 'ngTable',                   files: ['vendor/ng-table/dist/ng-table.min.js',
                                                  'vendor/ng-table/dist/ng-table.min.css']},
      {name: 'ngTableExport',             files: ['vendor/ng-table-export/ng-table-export.js']},
      {name: 'angularBootstrapNavTree',   files: ['vendor/angular-bootstrap-nav-tree/dist/abn_tree_directive.js',
                                                  'vendor/angular-bootstrap-nav-tree/dist/abn_tree.css']},
      {name: 'htmlSortable',              files: ['vendor/html.sortable/dist/html.sortable.js',
                                                  'vendor/html.sortable/dist/html.sortable.angular.js']},
      {name: 'xeditable',                 files: ['vendor/angular-xeditable/dist/js/xeditable.js',
                                                  'vendor/angular-xeditable/dist/css/xeditable.css']},
      {name: 'angularFileUpload',         files: ['vendor/angular-file-upload/angular-file-upload.js']},
      {name: 'ngImgCrop',                 files: ['vendor/ng-img-crop/compile/unminified/ng-img-crop.js',
                                                  'vendor/ng-img-crop/compile/unminified/ng-img-crop.css']},
      {name: 'ui.select',                 files: ['vendor/angular-ui-select/dist/select.js',
                                                  'vendor/angular-ui-select/dist/select.css']},
      {name: 'ui.codemirror',             files: ['vendor/angular-ui-codemirror/ui-codemirror.js']},
      {name: 'angular-carousel',          files: ['vendor/angular-carousel/dist/angular-carousel.css',
                                                  'vendor/angular-carousel/dist/angular-carousel.js']},
      {name: 'ngGrid',                    files: ['vendor/ng-grid/build/ng-grid.min.js',
                                                  'vendor/ng-grid/ng-grid.css' ]},
      {name: 'infinite-scroll',           files: ['vendor/ngInfiniteScroll/build/ng-infinite-scroll.js']},
      {name: 'ui.bootstrap-slider',       files: ['vendor/seiyria-bootstrap-slider/dist/bootstrap-slider.min.js',
                                                  'vendor/seiyria-bootstrap-slider/dist/css/bootstrap-slider.min.css',
                                                  'vendor/angular-bootstrap-slider/slider.js']},
      {name: 'ui.grid',                   files: ['vendor/angular-ui-grid/ui-grid.min.css',
                                                  'vendor/angular-ui-grid/ui-grid.min.js']},
      {name: 'textAngularSetup',          files: ['vendor/textAngular/src/textAngularSetup.js']},
      {name: 'textAngular',               files: ['vendor/textAngular/dist/textAngular-rangy.min.js',
                                                  'vendor/textAngular/src/textAngular.js',
                                                  'vendor/textAngular/src/textAngularSetup.js',
                                                  'vendor/textAngular/src/textAngular.css'], serie: true},
      {name: 'angular-rickshaw',          files: ['vendor/d3/d3.min.js',
                                                  'vendor/rickshaw/rickshaw.js',
                                                  'vendor/rickshaw/rickshaw.min.css',
                                                  'vendor/angular-rickshaw/rickshaw.js'], serie: true},
      {name: 'angular-chartist',          files: ['vendor/chartist/dist/chartist.min.css',
                                                  'vendor/chartist/dist/chartist.js',
                                                  'vendor/angular-chartist.js/dist/angular-chartist.js'], serie: true},
      {name: 'ui.map',                    files: ['vendor/angular-ui-map/ui-map.js']},
      {name: 'datatables',                files: ['vendor/datatables/media/css/jquery.dataTables.css',
                                                  'vendor/datatables/media/js/jquery.dataTables.js',
                                                  'vendor/angular-datatables/dist/angular-datatables.js'], serie: true},
      {name: 'angular-jqcloud',           files: ['vendor/jqcloud2/dist/jqcloud.css',
                                                  'vendor/jqcloud2/dist/jqcloud.js',
                                                  'vendor/angular-jqcloud/angular-jqcloud.js']},
      {name: 'angularGrid',               files: ['vendor/ag-grid/dist/angular-grid.css',
                                                  'vendor/ag-grid/dist/angular-grid.js',
                                                  'vendor/ag-grid/dist/theme-dark.css',
                                                  'vendor/ag-grid/dist/theme-fresh.css']},
      {name: 'ng-nestable',               files: ['vendor/ng-nestable/src/angular-nestable.js',
                                                  'vendor/nestable/jquery.nestable.js']},
      {name: 'akoenig.deckgrid',          files: ['vendor/angular-deckgrid/angular-deckgrid.js']}
    ]
  })
;
/**=========================================================
 * Module: access-login.js
 * Demo for login api
 =========================================================*/

/*App.controller('LoginFormController', ['$scope', '$http', '$state', function($scope, $http, $state) {
	 $scope.account = {};
	  var phoneNum =$('#phone').val();
	  var passwordNum =$('#password').val();

  $scope.login = function() {
	  var url = "/jygbg/login/login"
	  var data =  {phone: phoneNum, password: passwordNum};
	  console.log(data);
	  
	  
//将参数传递的方式改成form
	  postCfg = {
			  headers: { 'Content-Type': 'application/json;charse=UTF-8' },
			  transformRequest: function (data) {
				  return $.param(data);
			  }
	  };
      $http
        .post(url,data,postCfg)
        .then(function(response) {
        	if(response.msg==1){
        		$state.go('app.main');
        	}else{
        		alert('fail')
        	}
        });
  };

}]);*/

App.controller('LoginFormController', ['$scope', '$http', '$state', function($scope, $http, $state) {

	
	
	  $scope.login = function() {
	  var phoneNum =$('#phone').val();
	  var passwordNum =$('#password').val();
	  var data =  {phone: phoneNum, password: passwordNum};
		      $http
		        .post('/jygbg/login/login', data)
		        .then(function(response) {
		          	if(response.data.msg=='1'){
		        		$state.go('app.main');
		        	}else{
		        		alert('fail')
		        	}
		        });
	  };

	}]);
/**=========================================================
 * Module: access-register.js
 * Demo for register account api
 =========================================================*/

App.controller('RegisterFormController', ['$scope', '$http', '$state', function($scope, $http, $state) {

  // bind here all data from the form
  $scope.account = {};
  // place the message if something goes wrong
  $scope.authMsg = '';
    
  $scope.register = function() {
    $scope.authMsg = '';

    if($scope.registerForm.$valid) {

      $http
        .post('api/account/register', {email: $scope.account.email, password: $scope.account.password})
        .then(function(response) {
          // assumes if ok, response is an object with some data, if not, a string with error
          // customize according to your api
          if ( !response.account ) {
            $scope.authMsg = response;
          }else{
            $state.go('app.dashboard');
          }
        }, function(x) {
          $scope.authMsg = 'Server Request Error';
        });
    }
    else {
      // set as dirty if the user click directly to login so we show the validation messages
      $scope.registerForm.account_email.$dirty = true;
      $scope.registerForm.account_password.$dirty = true;
      $scope.registerForm.account_agreed.$dirty = true;
      
    }
  };

}]);


///**=========================================================
// * Module: angular-grid.js
// * Example for Angular Grid
// =========================================================*/
//
App.controller('AngularGridController', ['$scope', '$http', function ($scope, $http) {
    'use strict';

    // Basic
    var columnDefs = [
        {displayName: 'Athlete', field: 'athlete', width: 150},
        {displayName: 'Age', field: 'age', width: 90},
        {displayName: 'Country', field: 'country', width: 120},
        {displayName: 'Year', field: 'year', width: 90},
        {displayName: 'Date', field: 'date', width: 110},
        {displayName: 'Sport', field: 'sport', width: 110},
        {displayName: 'Gold', field: 'gold', width: 100},
        {displayName: 'Silver', field: 'silver', width: 100},
        {displayName: 'Bronze', field: 'bronze', width: 100},
        {displayName: 'Total', field: 'total', width: 100}
    ];

    $scope.gridOptions = {
        columnDefs: columnDefs,
        rowData: null,
        ready: function(api){
          api.sizeColumnsToFit();
        }
    };

    // Filter Example
    var irishAthletes = ['John Joe Nevin','Katie Taylor','Paddy Barnes','Kenny Egan','Darren Sutherland', 'Margaret Thatcher', 'Tony Blair', 'Ronald Regan', 'Barack Obama'];

    var columnDefsFilter = [
        {displayName: 'Athlete', field: 'athlete', width: 150, filter: 'set',
            filterParams: { cellHeight: 20, values: irishAthletes} },
        {displayName: 'Age', field: 'age', width: 90, filter: 'number'},
        {displayName: 'Country', field: 'country', width: 120},
        {displayName: 'Year', field: 'year', width: 90},
        {displayName: 'Date', field: 'date', width: 110},
        {displayName: 'Sport', field: 'sport', width: 110},
        {displayName: 'Gold', field: 'gold', width: 100, filter: 'number'},
        {displayName: 'Silver', field: 'silver', width: 100, filter: 'number'},
        {displayName: 'Bronze', field: 'bronze', width: 100, filter: 'number'},
        {displayName: 'Total', field: 'total', width: 100, filter: 'number'}
    ];

    $scope.gridOptions1 = {
        columnDefs: columnDefsFilter,
        rowData: null,
        enableFilter: true,
        ready: function(api){
          api.sizeColumnsToFit();
        }

    };


    // Pinning Example

    $scope.gridOptions2 = {
        columnDefs: columnDefs,
        rowData: null,
        pinnedColumnCount: 2,
        ready: function(api){
          api.sizeColumnsToFit();
        }
    };

    //-----------------------------
    // Get the data from SERVER
    //-----------------------------

    $http.get('server/ag-owinners.json')
        .then(function(res){
            // basic
            $scope.gridOptions.rowData = res.data;
            $scope.gridOptions.api.onNewRows();
            // filter
            $scope.gridOptions1.rowData = res.data;
            $scope.gridOptions1.api.onNewRows();
            // pinning
            $scope.gridOptions2.rowData = res.data;
            $scope.gridOptions2.api.onNewRows();
        });

}]);
App.controller('DatepickerDemoCtrl', ['$scope', function ($scope) {
	
	$scope.clear = function () {
	    $scope.dt = null;
	};
	
	
	$scope.toggleMin = function() {
	    $scope.minDate = $scope.minDate ? null : new Date();
	};
	$scope.toggleMin();
	
	$scope.open = function($event) {
	    $event.preventDefault();
	    $event.stopPropagation();
	
	    $scope.opened = true;
	};
	
	$scope.dateOptions = {
	    formatYear: 'yy',
	    startingDay: 1
	};
	
	
	$scope.initDate = new Date('2016-15-20');
	$scope.formats = ['yyyy-MM-dd', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
	$scope.format = $scope.formats[0];
	
	}]);
App.controller('DatepickerDemoCtrl1', ['$scope', function ($scope) {
	
	$scope.clear = function () {
	     $scope.dt1 = null;
	};
	
	
	$scope.toggleMin = function() {
	    $scope.minDate = $scope.minDate ? null : new Date();
	};
	$scope.toggleMin();
	
	$scope.open = function($event) {
	    $event.preventDefault();
	    $event.stopPropagation();
	
	    $scope.opened = true;
	};
	
	$scope.dateOptions = {
	    formatYear: 'yy',
	    startingDay: 1
	};
	
	
	$scope.initDate = new Date('2016-15-20');
	$scope.formats = ['yyyy-MM-dd', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
	$scope.format = $scope.formats[0];
	
	}]);
///**=========================================================
// * Module: main.js
// * Main Application Controller
// =========================================================*/
//
App.controller('AppController',
['$rootScope', '$scope', '$state', '$translate', '$window', '$localStorage', '$timeout', 'toggleStateService', 'colors', 'browser', 'cfpLoadingBar',
function($rootScope, $scope, $state, $translate, $window, $localStorage, $timeout, toggle, colors, browser, cfpLoadingBar) {
    "use strict";

    // Setup the layout mode
    $rootScope.app.layout.horizontal = ( $rootScope.$stateParams.layout == 'app-h') ;

    // Loading bar transition
    // ----------------------------------- 
    var thBar;
    $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams) {
        if($('.wrapper > section').length) // check if bar container exists
          thBar = $timeout(function() {
            cfpLoadingBar.start();
          }, 0); // sets a latency Threshold
    });
    $rootScope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams) {
        event.targetScope.$watch("$viewContentLoaded", function () {
          $timeout.cancel(thBar);
          cfpLoadingBar.complete();
        });
    });


    // Hook not found
    $rootScope.$on('$stateNotFound',
      function(event, unfoundState, fromState, fromParams) {
          console.log(unfoundState.to); // "lazy.state"
          console.log(unfoundState.toParams); // {a:1, b:2}
          console.log(unfoundState.options); // {inherit:false} + default options
      });
    // Hook error
    $rootScope.$on('$stateChangeError',
      function(event, toState, toParams, fromState, fromParams, error){
        console.log(error);
      });
    // Hook success
    $rootScope.$on('$stateChangeSuccess',
      function(event, toState, toParams, fromState, fromParams) {
        // display new view from top
        $window.scrollTo(0, 0);
        // Save the route title
        $rootScope.currTitle = $state.current.title;
      });

    $rootScope.currTitle = $state.current.title;
    $rootScope.pageTitle = function() {
      var title = $rootScope.app.name + ' - ' + ($rootScope.currTitle || $rootScope.app.description);
      document.title = title;
      return title;
    };

    $rootScope.$watch('app.layout.isCollapsed', function(newValue, oldValue) {
      if( newValue === false )
        $rootScope.$broadcast('closeSidebarMenu');
    });

    // Restore layout settings
    if( angular.isDefined($localStorage.layout) )
      $scope.app.layout = $localStorage.layout;
    else
      $localStorage.layout = $scope.app.layout;

    $rootScope.$watch("app.layout", function () {
      $localStorage.layout = $scope.app.layout;
    }, true);

    
    // Allows to use branding color with interpolation
    // {{ colorByName('primary') }}
    $scope.colorByName = colors.byName;

    // Hides/show user avatar on sidebar
    $scope.toggleUserBlock = function(){
      $scope.$broadcast('toggleUserBlock');
    };

    // Internationalization
    // ----------------------

    $scope.language = {
      // Handles language dropdown
      listIsOpen: false,
      // list of available languages
      available: {
        'en':       'English',
        'es_AR':    'Español'
      },
      // display always the current ui language
      init: function () {
        var proposedLanguage = $translate.proposedLanguage() || $translate.use();
        var preferredLanguage = $translate.preferredLanguage(); // we know we have set a preferred one in app.config
        $scope.language.selected = $scope.language.available[ (proposedLanguage || preferredLanguage) ];
      },
      set: function (localeId, ev) {
        // Set the new idiom
        $translate.use(localeId);
        // save a reference for the current language
        $scope.language.selected = $scope.language.available[localeId];
        // finally toggle dropdown
        $scope.language.listIsOpen = ! $scope.language.listIsOpen;
      }
    };

    $scope.language.init();

    // Restore application classes state
    toggle.restoreState( $(document.body) );

    // cancel click event easily
    $rootScope.cancel = function($event) {
      $event.stopPropagation();
    };

}]);
///**=========================================================
// * Module: ng-grid.js
// * ngGrid demo
// =========================================================*/
//
App.controller('NGGridController', ['$scope', '$http', '$timeout', function($scope, $http, $timeout) {

    $scope.filterOptions = {
        filterText: "",
        useExternalFilter: true
    };
    $scope.totalServerItems = 0;
    $scope.pagingOptions = {
        pageSizes:   [10, 20, 50],  // page size options
        pageSize:    10,              // default page size
        currentPage: 1                 // initial page
    };

    $scope.gridOptions = {
        data:             'myData',
        enablePaging:     true,
        showFooter:       true,
        rowHeight:        36,
        headerRowHeight:  38,
        totalServerItems: 'totalServerItems',
        pagingOptions:    $scope.pagingOptions,
        filterOptions:    $scope.filterOptions
    };

    $scope.setPagingData = function(data, page, pageSize){
        // calc for pager
        var pagedData = data.slice((page - 1) * pageSize, page * pageSize);
        // Store data from server
        $scope.myData = pagedData;
        // Update server side data length
        $scope.totalServerItems = data.length;

        if (!$scope.$$phase) {
            $scope.$apply();
        }

    };

    $scope.getPagedDataAsync = function (pageSize, page, searchText) {
      var ngGridResourcePath = 'server/ng-grid-data.json';

      $timeout(function () {

          if (searchText) {
              var ft = searchText.toLowerCase();
              $http.get(ngGridResourcePath).success(function (largeLoad) {
                  var data = largeLoad.filter(function(item) {
                      return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
                  });
                  $scope.setPagingData(data,page,pageSize);
              });
          } else {
              $http.get(ngGridResourcePath).success(function (largeLoad) {
                  $scope.setPagingData(largeLoad,page,pageSize);
              });
          }
      }, 100);
    };


    $scope.$watch('pagingOptions', function (newVal, oldVal) {
        if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
          $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
        }
    }, true);
    $scope.$watch('filterOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
          $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
        }
    }, true);

    $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);

}]);
//
///**=========================================================
// * Module: NGTableCtrl.js
// * Controller for ngTables
// =========================================================*/
//
App.controller('NGTableCtrl', NGTableCtrl);

function NGTableCtrl($scope, $filter, ngTableParams, $resource, $timeout, ngTableDataService) {
'use strict';
// required for inner references
var vm = this;

var Api = $resource('server/table-data.json');

vm.tableParams = new ngTableParams({
      page: 1,            // show first page
      count: 10           // count per page
}, {
      total: 0,           // length of data
      counts: [],         // hide page counts control
      getData: function($defer, params) {
          ngTableDataService.getData( $defer, params, Api);
      }
});

}
NGTableCtrl.$inject = ["$scope", "$filter", "ngTableParams", "$resource", "$timeout", "ngTableDataService"];

App.service('ngTableDataService', function() {

var TableData = {
    cache: null,
    getData: function($defer, params, api){
      // if no cache, request data and filter
      if ( ! TableData.cache ) {
        if ( api ) {
          api.get(function(data){
            TableData.cache = data;
            filterdata($defer, params);
          });
        }
      }
      else {
        filterdata($defer, params);
      }
      
      function filterdata($defer, params) {
        var from = (params.page() - 1) * params.count();
        var to = params.page() * params.count();
        var filteredData = TableData.cache.result.slice(from, to);

        params.total(TableData.cache.total);
        $defer.resolve(filteredData);
      }

    }
};

return TableData;

});
///**=========================================================
// loginInfoNGTableCtrl
// =========================================================*/
//
App.controller('loginInfoNGTableCtrl', NGTableCtrl);

function NGTableCtrl($scope, $filter, ngTableParams, $resource, $timeout, ngTableDataService) {
'use strict';
// required for inner references
var vm = this;

var Api = $resource('server/table-data-login-info.json');

vm.tableParams = new ngTableParams({
      page: 1,            // show first page
      count: 10           // count per page
}, {
      total: 0,           // length of data
      counts: [],         // hide page counts control
      getData: function($defer, params) {
          ngTableDataService.getData( $defer, params, Api);
      }
});

}
NGTableCtrl.$inject = ["$scope", "$filter", "ngTableParams", "$resource", "$timeout", "ngTableDataService"];

App.service('ngTableDataService', function() {

var TableData = {
    cache: null,
    getData: function($defer, params, api){
      // if no cache, request data and filter
      if ( ! TableData.cache ) {
        if ( api ) {
          api.get(function(data){
            TableData.cache = data;
            filterdata($defer, params);
          });
        }
      }
      else {
        filterdata($defer, params);
      }
      
      function filterdata($defer, params) {
        var from = (params.page() - 1) * params.count();
        var to = params.page() * params.count();
        var filteredData = TableData.cache.result.slice(from, to);

        params.total(TableData.cache.total);
        $defer.resolve(filteredData);
      }

    }
};

return TableData;

});
///**=========================================================
//获取菜单栏
// =========================================================*/
//
App.controller('SidebarController', ['$rootScope', '$scope', '$state', '$http', '$timeout', 'Utils',
function($rootScope, $scope, $state, $http, $timeout, Utils){

    var collapseList = [];

    $rootScope.$watch('app.layout.asideHover', function(oldVal, newVal){
      if ( newVal === false && oldVal === true) {
        closeAllBut(-1);
      }
    });

    var isActive = function(item) {

      if(!item) return;

      if( !item.sref || item.sref == '#') {
        var foundActive = false;
        angular.forEach(item.submenu, function(value, key) {
          if(isActive(value)) foundActive = true;
        });
        return foundActive;
      }
      else
        return $state.is(item.sref) || $state.includes(item.sref);
    };

    // 获取菜单栏----------------------------------- 
    
    $scope.getMenuItemPropClasses = function(item) {
      return (item.heading ? 'nav-heading' : '') +
             (isActive(item) ? ' active' : '') ;
    };

    $scope.loadSidebarMenu = function() {
    	var menuJson =' /jygbg/role/selectByPermission';
          menuURL  = menuJson + '?v=' + (new Date().getTime()); // jumps cache
      $http.get(menuURL)
        .success(function(items) {
           $scope.menuItems = items.data;
        })
        .error(function(data, status, headers, config) {
          alert('Failure loading menu');
        });
     };

     $scope.loadSidebarMenu();

    // Handle sidebar collapse items
    // ----------------------------------- 

    $scope.addCollapse = function($index, item) {
      collapseList[$index] = $rootScope.app.layout.asideHover ? true : !isActive(item);
    };

    $scope.isCollapse = function($index) {
      return (collapseList[$index]);
    };

    $scope.toggleCollapse = function($index, isParentItem) {


      // collapsed sidebar doesn't toggle drodopwn
      if( Utils.isSidebarCollapsed() || $rootScope.app.layout.asideHover ) return true;

      // make sure the item index exists
      if( angular.isDefined( collapseList[$index] ) ) {
        if ( ! $scope.lastEventFromChild ) {
          collapseList[$index] = !collapseList[$index];
          closeAllBut($index);
        }
      }
      else if ( isParentItem ) {
        closeAllBut(-1);
      }
      
      $scope.lastEventFromChild = isChild($index);

      return true;
    
    };

    function closeAllBut(index) {
      index += '';
      for(var i in collapseList) {
        if(index < 0 || index.indexOf(i) < 0)
          collapseList[i] = true;
      }
    }

    function isChild($index) {
      return (typeof $index === 'string') && !($index.indexOf('-') < 0);
    }

}]);
///**=========================================================
//系统管理控制器
// =========================================================*/
//
App.controller('TablexEditableController', ['$scope', '$filter', '$http', 'editableOptions', 'editableThemes','$q','$timeout',
function($scope, $filter, $http, editableOptions, editableThemes, $q,$timeout) {
	$scope.pageSize = 10;
	$scope.pageNumber = 1;
	var data ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
	function loadTable(){
		 var  dataArr = new Array();
		  $http({  
			    method:'post',  
			    url:'/jygbg/user/selectByUsers',  
			    data:data,
			    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
			    transformRequest: function(obj) {
			    	var str = [];
			    	for(var p in obj){
			    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
			    	}
			    	return str.join("&");
		    	}
			    }).success(function(req){
		    		dataArr = req.list;
					//数据源
					$scope.data =   dataArr;
					$scope.items = $scope.data;
					$scope.users = $scope.items;
					//分页总数
					$scope.pages = req.pages; //分页数
					$scope.newPages = $scope.pages > $scope.pageSize? $scope.pageSize : $scope.pages;
					$scope.pageList = [];
					$scope.selPage = $scope.pageNumber;
					//设置表格数据源(分页)
						//分页要repeat的数组
					for (var i = 0; i < $scope.newPages; i++) {
						$scope.pageList.push(i + 1);
					}
		})
	}
	//初始化
	loadTable();
	//打印当前选中页索引
	$scope.setData = function () {
		//通过当前页数筛选出表格当前显示数据
		$scope.pageNumber = $scope.selPage;
		var data ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
		 $http({  
		    method:'post',  
		    url:'/jygbg/user/selectByUsers',  
		    data:data,
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	$scope.items = req.list;
		    	$scope.users = $scope.items;	
		    })
	}
	$scope.selectPage = function (page) {
		//不能小于1大于最大
		if (page < 1 || page > $scope.pages) return;
		//最多显示分页数5
		if (page > 2) {
			//因为只显示5个页数，大于2页开始分页转换
			var newpageList = [];
			for (var i = (page - 3) ; i < ((page + 2) > $scope.pages ? $scope.pages : (page + 2)) ; i++) {
				newpageList.push(i + 1);
			}
			$scope.pageList = newpageList;
		}
		$scope.selPage = page;
		$scope.setData();
		$scope.isActivePage(page);
	};
	//设置当前选中页样式
	$scope.isActivePage = function (page) {
		
		return $scope.selPage == page;
	};
	//上一页
	$scope.Previous = function () {
		$scope.selectPage($scope.selPage - 1);
	}
	//下一页
	$scope.Next = function () {
		$scope.selectPage($scope.selPage + 1);
	};
	
	//获取角色列表
	$http({  
	    method:'post',  
	    url:'/jygbg/role/selectByRoleAll',  
	    async:false,
	    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
	    transformRequest: function(obj) {
	    	var str = [];
	    	for(var p in obj){
	    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
	    	}
	    	return str.join("&");
    	}
	    }).success(function(req){
	    	$scope.acterListArr=req;
	    })
	
	//新增用户
	$scope.addUser = function () {
		var userName = $('#userName').val();
		var inputPassword = $('#inputPassword').val();
		var selectVal = $('#selectUser').val();
		$http({  
		    method:'post',  
		    url:'/jygbg/user/insertUser',  
		    data:{phone:userName,password:inputPassword,rids:[selectVal]},
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	if(req.msg==1){
		    		loadTable();
		    	}else if(req.msg==-1){
		    		alert('用户名已存在')
		    	}else{
		    		alert('fail')
		    	}
		    })
	};
	//删除传参
	$scope.Values=function($event){
		 var dataId = $event.target.getAttribute('acterid');
		$("#id").val(dataId);
	}
	//删除用户
	$scope.deleteUser = function ($event) {
		
		 var dataId = $('#id').val();
		$http({  
		    method:'post',  
		    url:'/jygbg/user/deleteUser',  
		    data:{id:dataId},
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	if(req.msg==1){
		    		loadTable();
		    		alert('修改成功')
		    	}else if(req.msg==-1){
		    		alert('用户名已存在')
		    	}else{
		    		alert('fail')
		    	}
		    })
	};
	//编辑传参
	$scope.editParams=function($event){
		//渲染数据
		var datatr = $($event.currentTarget.parentNode.parentNode.parentNode);
		var a = datatr.children().eq(1).children().html();
		var b = datatr.children().eq(2).children().html();
		var c = datatr.children().eq(3).children().html();
		$('#userName1').val(a);
		$('#inputPassword1').val(b);
		var dataArr2 = $scope.acterListArr
		var d = "";
		for(var i=0;i<dataArr2.length;i++){
			if(dataArr2[i].name==c){
				d=dataArr2[i].id;
			}
		}
		$('#selectUser1').val(d);
		var dataId1 = $event.target.getAttribute('acterid');
	/*	console.log(dataId1);
		$("#editid").val(dataId1);*/
		$scope.dataIdNum = dataId1;
	}
	//编辑
	$scope.edituser= function ($event) {
		var userName = $('#userName1').val();
		var inputPassword = $('#inputPassword1').val();
		var selectVal = $('#selectUser1').val();
		/*var dataId =$('#editid').val();*/
		var dataId = $scope.dataIdNum;
		console.log(dataId);
		$http({  
		    method:'post',  
		    url:'/jygbg/user/updateUser',  
		    data:{phone:userName,password:inputPassword,rids:[selectVal],id:dataId},
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	if(req.msg==1){
		    		alert('修改成功')
		    	}else if(req.msg==-1){
		    		alert('该用户名已存在')
		    	}else{
		    		alert('修改失败')
		    	}
		    })
	};
	//清除
	$scope.clearInput= function () {
		document.getElementById("searchForms").reset();
	};
	//查询
	$scope.search= function () {
		var input1 = $('#input1').val();
		var input2 = $('#input2').val();
		var input3 = $('#input3').val();
		data={pageSize:$scope.pageSize,pageNum:$scope.pageNumber,phone:input1,startTime:input2,endTime:input3};
		console.log(data);
		loadTable();
	};
}]);
//       角色管理控制器///////////////////////////////////

App.controller('acterManamentController', ['$scope', '$filter', '$http', 'editableOptions', 'editableThemes','$q','$timeout',
function($scope, $filter, $http, editableOptions, editableThemes, $q,$timeout) {
	$scope.pageSize = 10;
	$scope.pageNumber = 1;
	var data ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
	$scope.loadTable = function(){
		  $http({  
			    method:'post',  
			    url:'/jygbg/role/selectByRoleUsers',  
			    data:data,
			    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
			    transformRequest: function(obj) {
			    	var str = [];
			    	for(var p in obj){
			    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
			    	}
			    	return str.join("&");
		    	}
			    }).success(function(req){
			    	//数据源
			    	$scope.users = req.list;
			    	$scope.pages = req.pages; //分页数
					//分页
					$scope.paging();
		})
	}
	//初始化
	$scope.loadTable();
	$scope.paging=function(){
		$scope.newPages = $scope.pages > $scope.pageSize? $scope.pageSize : $scope.pages;
		$scope.pageList = [];
		$scope.selPage = $scope.pageNumber;
		//设置表格数据源(分页)
			//分页要repeat的数组
		for (var i = 0; i < $scope.newPages; i++) {
			$scope.pageList.push(i + 1);
		}
		$scope.setData = function () {
			//通过当前页数筛选出表格当前显示数据
			$scope.pageNumber = $scope.selPage;
			var data ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
			 $http({  
			    method:'post',  
			    url:'/jygbg/role/selectByRoleUsers',  
			    data:data,
			    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
			    transformRequest: function(obj) {
			    	var str = [];
			    	for(var p in obj){
			    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
			    	}
			    	return str.join("&");
		    	}
			    }).success(function(req){
			    	$scope.users = req.list;
			    })
		}
		$scope.selectPage = function (page) {
			//不能小于1大于最大
			if (page < 1 || page > $scope.pages) return;
			//最多显示分页数5
			if (page > 2) {
				//因为只显示5个页数，大于2页开始分页转换
				var newpageList = [];
				for (var i = (page - 3) ; i < ((page + 2) > $scope.pages ? $scope.pages : (page + 2)) ; i++) {
					newpageList.push(i + 1);
				}
				$scope.pageList = newpageList;
			}
			if($scope.selPage!=page){
				$scope.selPage = page;
				$scope.setData();
				$scope.isActivePage(page);
			}
		};
		//设置当前选中页样式
		$scope.isActivePage = function (page) {
			
			return $scope.selPage == page;
		};
		//上一页
		$scope.Previous = function () {
			$scope.selectPage($scope.selPage - 1);
		}
		//下一页
		$scope.Next = function () {
			$scope.selectPage($scope.selPage + 1);
		};
	}
	//获取角色列表
	$http({  
	    method:'post',  
	    url:'/jygbg/role/selectByRidPermissionAll',  
	    async:false,
	    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
	    transformRequest: function(obj) {
	    	var str = [];
	    	for(var p in obj){
	    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
	    	}
	    	return str.join("&");
    	}
	    }).success(function(req){
	    	$scope.acterListArr=req.submenu;
	    })
	
	//新增用户
	$scope.addUser = function () {
		var formData=$("#insetFormqwer").serializeArray();
		var pidsArr =new Array;
		for(var i=1;i<formData.length;i++){
			pidsArr.push(formData[i].value)
		}
		var namaparam = formData[0].value;
		$http({  
		    method:'post',  
		    url:'/jygbg/role/insertRole',  
		    data:{name:namaparam,pids:pidsArr},
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	if(req.msg==1){
		    		$scope.loadTable();
		    	}else if(req.msg==-1){
		    		alert('用户名已存在')
		    	}else{
		    		alert('fail')
		    	}
		    })
	};
	//删除传参
	$scope.Values=function($event){
		 var dataId = $event.target.getAttribute('acterid');
		$("#id").val(dataId);
	}
	//删除用户
	$scope.deleteUser = function ($event) {
		
		 var dataId = $('#id').val();
		$http({  
		    method:'post',  
		    url:'/jygbg/role/deleteRole',  
		    data:{id:dataId},
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	if(req.msg==1){
		    		$scope.loadTable();
		    	}else if(req.msg==-1){
		    		alert('用户名已存在')
		    	}else{
		    		alert('fail')
		    	}
		    })
	};
	//编辑传参
	$scope.editParams=function($event){
		//渲染数据
		var datatr = $($event.currentTarget.parentNode.parentNode.parentNode);
		var a = datatr.children().eq(1).children().html();
		var b = datatr.children().eq(2).children().html();
		var c = datatr.children().eq(3).children().html();
		$('#userName1').val(a);
		$('#inputPassword1').val(b);
		var dataArr2 = $scope.acterListArr
		var d = "";
		for(var i=0;i<dataArr2.length;i++){
			if(dataArr2[i].name==c){
				d=dataArr2[i].id;
			}
		}
		$('#selectUser1').val(d);
		var dataId = $event.target.getAttribute('acterid');
		$scope.idParams=$event.target.getAttribute('acterid');
	}
	//编辑
	$scope.edituser= function ($event) {
		var formData=$("#insetFormqwer1").serializeArray();
		var pidsArr =new Array;
		for(var i=1;i<formData.length;i++){
			pidsArr.push(formData[i].value)
		}
		var namaparam = formData[0].value;
		var id2 =$scope.idParams;
		$http({  
		    method:'post',  
		    url:'/jygbg/role/updateRole',  
		    data:{name:namaparam,pids:pidsArr,id:id2},
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	if(req.msg==1){
		    		$scope.loadTable();
		    	}else if(req.msg==-1){
		    		alert('用户名已存在')
		    	}else{
		    		alert('fail')
		    	}
		    })
	};
	//清除
	$scope.clearInput= function () {
		document.getElementById("searchForms").reset();
	};
	//查询
	
	$scope.search=function(){
		var input1 = $('#Actinput1').val();
		var input2 = $('#Actinput2').val();
		var input3 = $('#Actinput3').val();
		data={pageSize:$scope.pageSize,pageNum:$scope.pageNumber,phone:input1,startTime:input2,endTime:input3};
		console.log(data);
		$scope.loadTable();
	};
	//查询设置用户接口
	$scope.pageSizeSetting = 5;
	$scope.pageNumberSetting =1;
	var datasetting ={pageSize:$scope.pageSizeSetting,pageNum:$scope.pageNumberSetting}
	$http({  
	    method:'post',  
	    url:'/jygbg/role/selectByUserRoleAll',  
	    async:false,
	    data:datasetting,
	    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
	    transformRequest: function(obj) {
	    	var str = [];
	    	for(var p in obj){
	    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
	    	}
	    	return str.join("&");
    	}
	    }).success(function(req){
	    	$scope.settingUsers=req.list;
	    	$scope.pages1 = req.pages; //分页数
			//分页
	    	
			$scope.paging1();
	    })
	    $scope.paging1=function(){
				
			$scope.newPages1 = $scope.pages1 > $scope.pageSizeSetting? $scope.pageSizeSetting : $scope.pages1;
			$scope.pageList1 = [];
			$scope.selPage1 = $scope.pageNumberSetting;
			//设置表格数据源(分页)
				//分页要repeat的数组
			for (var i = 0; i < $scope.newPages1; i++) {
				$scope.pageList1.push(i + 1);
			}
			$scope.setData1 = function () {
				//通过当前页数筛选出表格当前显示数据
				$scope.pageNumberSetting = $scope.selPage1;
				
				var datasetting ={pageSize:$scope.pageSizeSetting,pageNum:$scope.pageNumberSetting};
				console.log(datasetting);
				 $http({  
				    method:'post',  
				    url:'/jygbg/role/selectByUserRoleAll',  
				    data:datasetting,
				    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
				    transformRequest: function(obj) {
				    	var str = [];
				    	for(var p in obj){
				    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
				    	}
				    	return str.join("&");
			    	}
				    }).success(function(req){
				    	$scope.settingUsers=req.list;
				    })
			}
			$scope.selectPage1 = function (page) {
				//不能小于1大于最大
				if (page < 1 || page > $scope.pages1) return;
				//最多显示分页数5
				if (page > 2) {
					//因为只显示5个页数，大于2页开始分页转换
					var newpageList1 = [];
					for (var i = (page - 3) ; i < ((page + 2) > $scope.pages1 ? $scope.pages1 : (page + 2)) ; i++) {
						newpageList1.push(i + 1);
					}
					$scope.pageList1 = newpageList1;
				}
				console.log($scope.selPage1);
				if($scope.selPage1!=page){
					$scope.selPage1 = page;
					
					$scope.setData1();
					$scope.isActivePage1(page);
				}
			};
			//设置当前选中页样式
			$scope.isActivePage1 = function (page) {
				
				return $scope.selPage1 == page;
			};
			//上一页
			$scope.Previous1 = function () {
				$scope.selectPage1($scope.selPage1 - 1);
			}
			//下一页
			$scope.Next1 = function () {
				$scope.selectPage1($scope.selPage1 + 1);
			};
	}   
	   
	    
	//设置传参
	$scope.ValueSetting=function($event){
		 var dataId = $event.target.getAttribute('acterid');
		$scope.settingId = dataId;
	}
	//设置用户v
	
	$scope.setting =function($event){
		var userIdArr = new Array();
		var formData1=$("#settingForm").serializeArray();
		console.log(formData1);
		for(var i=0;i<formData1.length;i++){
			userIdArr.push(formData1[i].name)
		}
		console.log(userIdArr);
		$http({  
		    method:'post',  
		    url:'/jygbg/role/insertUserRole',  
		    async:false,
		    data:{uids:userIdArr,rids:$scope.settingId},
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	console.log(req)
		    })
		
	}
}]);
///**=========================================================
//* 登录信息控制器 
//=========================================================*/
App.controller('loginInfoController', ['$scope', '$filter', '$http', 'editableOptions', 'editableThemes','$q','$timeout',
	   function($scope, $filter, $http, editableOptions, editableThemes, $q,$timeout) {
	   	$scope.pageSize = 10;
	   	$scope.pageNumber = 1;
	   	var data ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
	   	$scope.url ="/jygbg/userLoginInfo/selectByAll";
	   	$scope.loadTable = function(){
	   		  $http({  
	   			    method:'post',  
			    url:$scope.url,  
			    data:data,
			    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
			    transformRequest: function(obj) {
			    	var str = [];
			    	for(var p in obj){
			    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
			    	}
			    	return str.join("&");
		    	}
			    }).success(function(req){
			    	//数据源
			    	$scope.users = req.list;
			    	$scope.pages = req.pages; //分页数
					//分页
					$scope.paging();
		})
	}
	//初始化
	$scope.loadTable();
	$scope.paging=function(){
		$scope.newPages = $scope.pages > $scope.pageSize? $scope.pageSize : $scope.pages;
		$scope.pageList = [];
		$scope.selPage = $scope.pageNumber;
		//设置表格数据源(分页)
			//分页要repeat的数组
		for (var i = 0; i < $scope.newPages; i++) {
			$scope.pageList.push(i + 1);
		}
		$scope.setData = function () {
			//通过当前页数筛选出表格当前显示数据
			$scope.pageNumber = $scope.selPage;
			var data ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
			 $http({  
			    method:'post',  
			    url:$scope.url,  
			    data:data,
			    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
			    transformRequest: function(obj) {
			    	var str = [];
			    	for(var p in obj){
			    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
			    	}
			    	return str.join("&");
		    	}
			    }).success(function(req){
			    	$scope.users = req.list;
			    })
		}
		$scope.selectPage = function (page) {
			//不能小于1大于最大
			if (page < 1 || page > $scope.pages) return;
			//最多显示分页数5
			if (page > 2) {
				//因为只显示5个页数，大于2页开始分页转换
				var newpageList = [];
				for (var i = (page - 3) ; i < ((page + 2) > $scope.pages ? $scope.pages : (page + 2)) ; i++) {
					newpageList.push(i + 1);
				}
				$scope.pageList = newpageList;
			}
			if($scope.selPage!=page){
				$scope.selPage = page;
				$scope.setData();
				$scope.isActivePage(page);
			}
		};
		//设置当前选中页样式
		$scope.isActivePage = function (page) {
			
			return $scope.selPage == page;
		};
		//上一页
		$scope.Previous = function () {
			$scope.selectPage($scope.selPage - 1);
		}
		//下一页
		$scope.Next = function () {
			$scope.selectPage($scope.selPage + 1);
		};
	}
	
	//清除
	$scope.clearInput= function () {
		document.getElementById("searchForms").reset();
	};
	//查询
	
	$scope.search=function(){
		var input1 = $('#Actinput1').val();
		var input2 = $('#Actinput2').val();
		var input3 = $('#Actinput3').val();
		var input4 = $('#selectUser').val();
   		data={pageSize:$scope.pageSize,pageNum:$scope.pageNumber,loginFrom:input4,userName:input1,startTime:input2,endTime:input3};
   		console.log(data);
   		$scope.loadTable();	
	   	};
  }]);

///**=========================================================
//* 账户信息控制器 
//=========================================================*/
App.controller('accountInfoController', ['$scope', '$filter', '$http', 'editableOptions', 'editableThemes','$q','$timeout',
   function($scope, $filter, $http, editableOptions, editableThemes, $q,$timeout) {
   	$scope.pageSize = 10;
   	$scope.pageNumber = 1;
   	$scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
   	$scope.url ="/jygbg/userInfo/selectByAccountMessage";
   	$scope.loadTable = function(){
   		  $http({  
		    method:'post',  
		    url:$scope.url,  
		    data:$scope.dataJson,
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	//数据源
		    	$scope.users = req.list;
		    	$scope.pages = req.pages; //分页数
				//分页
				$scope.paging();
	})
}
//初始化
$scope.loadTable();
$scope.paging=function(){
	$scope.newPages = $scope.pages > $scope.pageSize? $scope.pageSize : $scope.pages;
	$scope.pageList = [];
	$scope.selPage = $scope.pageNumber;
	//设置表格数据源(分页)
		//分页要repeat的数组
	for (var i = 0; i < $scope.newPages; i++) {
		$scope.pageList.push(i + 1);
	}
	$scope.setData = function () {
		//通过当前页数筛选出表格当前显示数据
		$scope.pageNumber = $scope.selPage;
		var data ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
		 $http({  
		    method:'post',  
		    url:$scope.url,  
		    data:data,
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	$scope.users = req.list;
		    })
	}
	$scope.selectPage = function (page) {
		//不能小于1大于最大
		if (page < 1 || page > $scope.pages) return;
		//最多显示分页数5
		if (page > 2) {
			//因为只显示5个页数，大于2页开始分页转换
			var newpageList = [];
			for (var i = (page - 3) ; i < ((page + 2) > $scope.pages ? $scope.pages : (page + 2)) ; i++) {
				newpageList.push(i + 1);
			}
			$scope.pageList = newpageList;
		}
		if($scope.selPage!=page){
			$scope.selPage = page;
			$scope.setData();
			$scope.isActivePage(page);
		}
	};
	//设置当前选中页样式
	$scope.isActivePage = function (page) {
		
		return $scope.selPage == page;
	};
	//上一页
	$scope.Previous = function () {
		$scope.selectPage($scope.selPage - 1);
	}
	//下一页
	$scope.Next = function () {
		$scope.selectPage($scope.selPage + 1);
	};
}

//清除
$scope.clearInput= function () {
	document.getElementById("searchForms").reset();
};
//查询

$scope.search=function(){
		var formData=$("#accountInfoForm").serializeArray();
		var pidsArr =new Array;
		for(var i=0;i<formData.length;i++){
			pidsArr.push(formData[i].value)
		}
		$scope.dataJson={pageSize:$scope.pageSize,pageNum:$scope.pageNumber,accountNum:pidsArr[3],userName:pidsArr[0],status:pidsArr[1],startTime:pidsArr[5],endTime:pidsArr[6],realName:pidsArr[2],phone:pidsArr[4],registerFrom:pidsArr[7]};
   		console.log($scope.dataJson);
   		$scope.loadTable();
   	};
   //禁用/启用

   	$scope.fobidden=function($event,params){
   			var userid = $event.target.getAttribute('acterid');
   	   		data={status:params,userid:userid};
   	   		console.log(data);
	   	   	 $http({  
	 		    method:'post',  
	 		    url:" /jygbg/userLoginInfo/updateLoginStatus",  
	 		    data:data,
	 		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
	 		    transformRequest: function(obj) {
	 		    	var str = [];
	 		    	for(var p in obj){
	 		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
	 		    	}
	 		    	return str.join("&");
	 	    	}
	 		    }).success(function(req){
	 		    	if(req.msg==1){
	 		    		 $scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
	 		    		$scope.loadTable();
	 		    		 $scope.alertTxt ="修改成功"
		   	   	 }else{
		   	   		 $scope.alertTxt ="修改失败"
		   	   	 }
 		    })
   	   	};
  }]);


///**=========================================================
//*实名认证控制器
//=========================================================*/
App.controller('certificationController', ['$scope', '$filter', '$http', 'editableOptions', 'editableThemes','$q','$timeout',
 function($scope, $filter, $http, editableOptions, editableThemes, $q,$timeout) {
 	$scope.pageSize = 10;
 	$scope.pageNumber = 1;
 	$scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
 	$scope.url ="/jygbg/userInfo/selectByAll";
 	$scope.loadTable = function(){
 		  $http({  
		    method:'post',  
		    url:$scope.url,  
		    data:$scope.dataJson,
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	//数据源
		    	$scope.users = req.list;
		    	$scope.pages = req.pages; //分页数
				//分页
				$scope.paging();
	})
}
//初始化
$scope.loadTable();
$scope.paging=function(){
	$scope.newPages = $scope.pages > $scope.pageSize? $scope.pageSize : $scope.pages;
	$scope.pageList = [];
	$scope.selPage = $scope.pageNumber;
	//设置表格数据源(分页)
		//分页要repeat的数组
	for (var i = 0; i < $scope.newPages; i++) {
		$scope.pageList.push(i + 1);
	}
	$scope.setData = function () {
		//通过当前页数筛选出表格当前显示数据
		$scope.pageNumber = $scope.selPage;
		var data ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
		 $http({  
		    method:'post',  
		    url:$scope.url,  
		    data:data,
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	$scope.users = req.list;
		    })
	}
	$scope.selectPage = function (page) {
		//不能小于1大于最大
		if (page < 1 || page > $scope.pages) return;
		//最多显示分页数5
		if (page > 2) {
			//因为只显示5个页数，大于2页开始分页转换
			var newpageList = [];
			for (var i = (page - 3) ; i < ((page + 2) > $scope.pages ? $scope.pages : (page + 2)) ; i++) {
				newpageList.push(i + 1);
			}
			$scope.pageList = newpageList;
		}
		if($scope.selPage!=page){
			$scope.selPage = page;
			$scope.setData();
			$scope.isActivePage(page);
		}
	};
	//设置当前选中页样式
	$scope.isActivePage = function (page) {
		
		return $scope.selPage == page;
	};
	//上一页
	$scope.Previous = function () {
		$scope.selectPage($scope.selPage - 1);
	}
	//下一页
	$scope.Next = function () {
		$scope.selectPage($scope.selPage + 1);
	};
}

//清除
$scope.clearInput= function () {
	document.getElementById("searchForms").reset();
};
//查询

$scope.search=function(){
		var formData=$("#accountInfoForm").serializeArray();
		var pidsArr =new Array;
		for(var i=0;i<formData.length;i++){
			pidsArr.push(formData[i].value)
		}
		$scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber,
			userName:pidsArr[0],authStatic:pidsArr[1],realName:pidsArr[2],
			iDCard:pidsArr[3],applyTimeEnd:pidsArr[4],applyTimeStart:pidsArr[5],
			approveTimeEnd:pidsArr[6],approveTimeStart:pidsArr[7]
		};
 		console.log($scope.dataJson);
 		$scope.loadTable();
 	};
 //审核
 	$scope.cerfitication=function($event,params){
 			var userid = $event.target.getAttribute('acterid');
 	   		data={type:params,userId:userid};
 	   		console.log(data);
	   	   	 $http({  
	 		    method:'post',  
	 		    url:" /jygbg/userInfo/certification",  
	 		    data:data,
	 		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
	 		    transformRequest: function(obj) {
	 		    	var str = [];
	 		    	for(var p in obj){
	 		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
	 		    	}
	 		    	return str.join("&");
	 	    	}
	 		    }).success(function(req){
	 		    	if(req.msg==1){
	 		    		 $scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
	 		    		 $scope.loadTable();
	 		    		 $scope.alertTxt ="修改成功"
		   	   	 }else{
		   	   		 $scope.alertTxt ="修改失败"
		   	   	 }
		    })
 	   	};
 	  //查看照片传路径
 	   	$scope.imgSrc= function($event){
 	   		$scope.imgSrcPath = $event.target.getAttribute('imgSrc');
 	   	}
 	   
}]);

///**=========================================================
//*优顾认证控制器
//=========================================================*/
App.controller('certificationjygController', ['$scope', '$filter', '$http', 'editableOptions', 'editableThemes','$q','$timeout',
function($scope, $filter, $http, editableOptions, editableThemes, $q,$timeout) {
	$scope.pageSize = 10;
	$scope.pageNumber = 1;
	$scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
	$scope.url ="/jygbg/userInfo/selectByAll";
	$scope.loadTable = function(){
		  $http({  
		    method:'post',  
		    url:$scope.url,  
		    data:$scope.dataJson,
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	//数据源
		    	$scope.users = req.list;
		    	$scope.pages = req.pages; //分页数
				//分页
				$scope.paging();
	})
}
//初始化
$scope.loadTable();
$scope.paging=function(){
	$scope.newPages = $scope.pages > $scope.pageSize? $scope.pageSize : $scope.pages;
	$scope.pageList = [];
	$scope.selPage = $scope.pageNumber;
	//设置表格数据源(分页)
		//分页要repeat的数组
	for (var i = 0; i < $scope.newPages; i++) {
		$scope.pageList.push(i + 1);
	}
	$scope.setData = function () {
		//通过当前页数筛选出表格当前显示数据
		$scope.pageNumber = $scope.selPage;
		var data ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
		 $http({  
		    method:'post',  
		    url:$scope.url,  
		    data:data,
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	$scope.users = req.list;
		    })
	}
	$scope.selectPage = function (page) {
		//不能小于1大于最大
		if (page < 1 || page > $scope.pages) return;
		//最多显示分页数5
		if (page > 2) {
			//因为只显示5个页数，大于2页开始分页转换
			var newpageList = [];
			for (var i = (page - 3) ; i < ((page + 2) > $scope.pages ? $scope.pages : (page + 2)) ; i++) {
				newpageList.push(i + 1);
			}
			$scope.pageList = newpageList;
		}
		if($scope.selPage!=page){
			$scope.selPage = page;
			$scope.setData();
			$scope.isActivePage(page);
		}
	};
	//设置当前选中页样式
	$scope.isActivePage = function (page) {
		
		return $scope.selPage == page;
	};
	//上一页
	$scope.Previous = function () {
		$scope.selectPage($scope.selPage - 1);
	}
	//下一页
	$scope.Next = function () {
		$scope.selectPage($scope.selPage + 1);
	};
}

//清除
$scope.clearInput= function () {
	document.getElementById("searchForms").reset();
};
//查询

$scope.search=function(){
		var formData=$("#accountInfoForm").serializeArray();
		var pidsArr =new Array;
		for(var i=0;i<formData.length;i++){
			pidsArr.push(formData[i].value)
		}
		$scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber,
			userName:pidsArr[0],authStatic:pidsArr[1],realName:pidsArr[2],
			iDCard:pidsArr[3],applyTimeEnd:pidsArr[4],applyTimeStart:pidsArr[5],
			approveTimeEnd:pidsArr[6],approveTimeStart:pidsArr[7]
		};
		console.log($scope.dataJson);
		$scope.loadTable();
	};
//审核
	$scope.cerfitication=function($event,params){
			var userid = $event.target.getAttribute('acterid');
	   		data={type:params,userId:userid};
	   		console.log(data);
	   	   	 $http({  
	 		    method:'post',  
	 		    url:" /jygbg/userInfo/certificationYG",  
	 		    data:data,
	 		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
	 		    transformRequest: function(obj) {
	 		    	var str = [];
	 		    	for(var p in obj){
	 		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
	 		    	}
	 		    	return str.join("&");
	 	    	}
	 		    }).success(function(req){
	 		    	if(req.msg==1){
	 		    		 $scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
	 		    		 $scope.loadTable();
	 		    		 $scope.alertTxt ="修改成功"
		   	   	 }else{
		   	   		 $scope.alertTxt ="修改失败"
		   	   	 }
		    })
	   	};
	  //查看照片传路径
	   	$scope.imgSrc= function($event){
	   		$scope.imgSrcPath = $event.target.getAttribute('imgSrc');
	   		console.log($scope.imgSrcPath);
	   	}
	   
}]);
//**=========================================================
//*优顾信息控制器
//=========================================================*/
App.controller('youguInfoController', ['$scope', '$filter', '$http', 'editableOptions', 'editableThemes','$q','$timeout',
function($scope, $filter, $http, editableOptions, editableThemes, $q,$timeout) {
	$scope.pageSize = 10;
	$scope.pageNumber = 1;
	$scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
	$scope.url ="/jygbg/userInfo/selectByAll";
	$scope.loadTable = function(){
		  $http({  
		    method:'post',  
		    url:$scope.url,  
		    data:$scope.dataJson,
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	//数据源
		    	$scope.users = req.list;
		    	$scope.pages = req.pages; //分页数
				//分页
				$scope.paging();
	})
}
//初始化
$scope.loadTable();
$scope.paging=function(){
	$scope.newPages = $scope.pages > $scope.pageSize? $scope.pageSize : $scope.pages;
	$scope.pageList = [];
	$scope.selPage = $scope.pageNumber;
	//设置表格数据源(分页)
		//分页要repeat的数组
	for (var i = 0; i < $scope.newPages; i++) {
		$scope.pageList.push(i + 1);
	}
	$scope.setData = function () {
		//通过当前页数筛选出表格当前显示数据
		$scope.pageNumber = $scope.selPage;
		var data ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
		 $http({  
		    method:'post',  
		    url:$scope.url,  
		    data:data,
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	$scope.users = req.list;
		    })
	}
	$scope.selectPage = function (page) {
		//不能小于1大于最大
		if (page < 1 || page > $scope.pages) return;
		//最多显示分页数5
		if (page > 2) {
			//因为只显示5个页数，大于2页开始分页转换
			var newpageList = [];
			for (var i = (page - 3) ; i < ((page + 2) > $scope.pages ? $scope.pages : (page + 2)) ; i++) {
				newpageList.push(i + 1);
			}
			$scope.pageList = newpageList;
		}
		if($scope.selPage!=page){
			$scope.selPage = page;
			$scope.setData();
			$scope.isActivePage(page);
		}
	};
	//设置当前选中页样式
	$scope.isActivePage = function (page) {
		
		return $scope.selPage == page;
	};
	//上一页
	$scope.Previous = function () {
		$scope.selectPage($scope.selPage - 1);
	}
	//下一页
	$scope.Next = function () {
		$scope.selectPage($scope.selPage + 1);
	};
}

//清除
$scope.clearInput= function () {
	document.getElementById("accountInfoForm").reset();
};
//查询

$scope.search=function(){
		var formData=$("#accountInfoForm").serializeArray();
		var pidsArr =new Array;
		for(var i=0;i<formData.length;i++){
			pidsArr.push(formData[i].value)
		}
		$scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber,
			userName:pidsArr[0],authStatic:pidsArr[1],realName:pidsArr[2],
			iDCard:pidsArr[3],applyTimeEnd:pidsArr[4],applyTimeStart:pidsArr[5],
			approveTimeEnd:pidsArr[6],approveTimeStart:pidsArr[7]
		};
		console.log($scope.dataJson);
		$scope.loadTable();
	};
	
	
}]);


  

///**=========================================================
//*充值记录控制器
//=========================================================*/
App.controller('rechargeRecordController', ['$scope', '$filter', '$http', 'editableOptions', 'editableThemes','$q','$timeout',
function($scope, $filter, $http, editableOptions, editableThemes, $q,$timeout) {
	$scope.pageSize = 10;
	$scope.pageNumber = 1;
	$scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
	$scope.url ="/jygbg/userRecharge/selectAll";
	$scope.loadTable = function(){
		  $http({  
		    method:'post',  
		    url:$scope.url,  
		    data:$scope.dataJson,
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	//数据源
		    	$scope.users = req.list;
		    	$scope.pages = req.pages; //分页数
				//分页
				$scope.paging();
	})
}
//初始化
$scope.loadTable();
$scope.paging=function(){
	$scope.newPages = $scope.pages > $scope.pageSize? $scope.pageSize : $scope.pages;
	$scope.pageList = [];
	$scope.selPage = $scope.pageNumber;
	//设置表格数据源(分页)
		//分页要repeat的数组
	for (var i = 0; i < $scope.newPages; i++) {
		$scope.pageList.push(i + 1);
	}
	$scope.setData = function () {
		//通过当前页数筛选出表格当前显示数据
		$scope.pageNumber = $scope.selPage;
		var data ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
		 $http({  
		    method:'post',  
		    url:$scope.url,  
		    data:data,
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	$scope.users = req.list;
		    })
	}
	$scope.selectPage = function (page) {
		//不能小于1大于最大
		if (page < 1 || page > $scope.pages) return;
		//最多显示分页数5
		if (page > 2) {
			//因为只显示5个页数，大于2页开始分页转换
			var newpageList = [];
			for (var i = (page - 3) ; i < ((page + 2) > $scope.pages ? $scope.pages : (page + 2)) ; i++) {
				newpageList.push(i + 1);
			}
			$scope.pageList = newpageList;
		}
		if($scope.selPage!=page){
			$scope.selPage = page;
			$scope.setData();
			$scope.isActivePage(page);
		}
	};
	//设置当前选中页样式
	$scope.isActivePage = function (page) {
		
		return $scope.selPage == page;
	};
	//上一页
	$scope.Previous = function () {
		$scope.selectPage($scope.selPage - 1);
	}
	//下一页
	$scope.Next = function () {
		$scope.selectPage($scope.selPage + 1);
	};
}

//清除
$scope.clearInput= function () {
	document.getElementById("rechargeRecordForm").reset();
};
//查询

$scope.search=function(){
		var formData=$("#rechargeRecordForm").serializeArray();
		var pidsArr =new Array;
		for(var i=0;i<formData.length;i++){
			pidsArr.push(formData[i].value)
		}
		$scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber,
			username:pidsArr[0],rechargeid:pidsArr[1],merchantordernum:pidsArr[2],
			startTime:pidsArr[3],endTime:pidsArr[4],rechargechannel:pidsArr[5],
			status:pidsArr[6]
		};
		console.log($scope.dataJson);
		$scope.loadTable();
	};
	//导出
	$scope.export=function(){
		var formData=$("#rechargeRecordForm").serializeArray();
		var pidsArr =new Array;
		for(var i=0;i<formData.length;i++){
			pidsArr.push(formData[i].value)
		}
		$scope.dataJson =
			'username='+pidsArr[0]+'&'+'rechargeid	='+pidsArr[1]+'&'+'merchantordernum='+pidsArr[2]+'&'+
			'startTime='+pidsArr[3]+'&'+'endTime='+pidsArr[4]+'&'+'rechargechannel='+pidsArr[5]+'&'+
			'status='+pidsArr[6];
		$scope.url="/jygbg/userRecharge/excelRecharge?"+$scope.dataJson;
		window.location.href=$scope.url;
	};
}]);

///**=========================================================
//*消费记录控制器
//=========================================================*/
App.controller('purchaseHistoryController', ['$scope', '$filter', '$http', 'editableOptions', 'editableThemes','$q','$timeout',
function($scope, $filter, $http, editableOptions, editableThemes, $q,$timeout) {
	$scope.pageSize = 10;
	$scope.pageNumber = 1;
	$scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
	$scope.url ="/jygbg/userAccountRecord/selectByAll";
	$scope.loadTable = function(){
		  $http({  
		    method:'post',  
		    url:$scope.url,  
		    data:$scope.dataJson,
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	//数据源
		    	$scope.users = req.list;
		    	$scope.pages = req.pages; //分页数
				//分页
				$scope.paging();
	})
}
//初始化
$scope.loadTable();
$scope.paging=function(){
	$scope.newPages = $scope.pages > $scope.pageSize? $scope.pageSize : $scope.pages;
	$scope.pageList = [];
	$scope.selPage = $scope.pageNumber;
	//设置表格数据源(分页)
		//分页要repeat的数组
	for (var i = 0; i < $scope.newPages; i++) {
		$scope.pageList.push(i + 1);
	}
	$scope.setData = function () {
		//通过当前页数筛选出表格当前显示数据
		$scope.pageNumber = $scope.selPage;
		var data ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
		 $http({  
		    method:'post',  
		    url:$scope.url,  
		    data:data,
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	$scope.users = req.list;
		    })
	}
	$scope.selectPage = function (page) {
		//不能小于1大于最大
		if (page < 1 || page > $scope.pages) return;
		//最多显示分页数5
		if (page > 2) {
			//因为只显示5个页数，大于2页开始分页转换
			var newpageList = [];
			for (var i = (page - 3) ; i < ((page + 2) > $scope.pages ? $scope.pages : (page + 2)) ; i++) {
				newpageList.push(i + 1);
			}
			$scope.pageList = newpageList;
		}
		if($scope.selPage!=page){
			$scope.selPage = page;
			$scope.setData();
			$scope.isActivePage(page);
		}
	};
	//设置当前选中页样式
	$scope.isActivePage = function (page) {
		
		return $scope.selPage == page;
	};
	//上一页
	$scope.Previous = function () {
		$scope.selectPage($scope.selPage - 1);
	}
	//下一页
	$scope.Next = function () {
		$scope.selectPage($scope.selPage + 1);
	};
}

//清除
$scope.clearInput= function () {
	document.getElementById("rechargeRecordForm").reset();
};
//查询

$scope.search=function(){
		var formData=$("#rechargeRecordForm").serializeArray();
		var pidsArr =new Array;
		for(var i=0;i<formData.length;i++){
			pidsArr.push(formData[i].value)
		}
		$scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber,
				userName:pidsArr[0],startTime:pidsArr[1],endTime:pidsArr[2],
		};
		console.log($scope.dataJson);
		$scope.loadTable();
	};
	//导出
	$scope.export=function(){
		var formData=$("#rechargeRecordForm").serializeArray();
		var pidsArr =new Array;
		for(var i=0;i<formData.length;i++){
			pidsArr.push(formData[i].value)
		}
		$scope.dataJson = 'userName='+pidsArr[0]+'&'+'startTime	='+pidsArr[1]+'&'+'endTime='+pidsArr[2];
		$scope.url="/jygbg/userAccountRecord/excelAccountRecord?"+$scope.dataJson;
		window.location.href=$scope.url;
	};
}]);

///**=========================================================
//*出金管理控制器
//=========================================================*/
App.controller('cashManagementController', ['$scope', '$filter', '$http', 'editableOptions', 'editableThemes','$q','$timeout',
function($scope, $filter, $http, editableOptions, editableThemes, $q,$timeout) {
	$scope.pageSize = 10;
	$scope.pageNumber = 1;
	$scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
	$scope.url ="/jygbg/userWithdrawCash/selectAll";
	$scope.loadTable = function(){
		  $http({  
		    method:'post',  
		    url:$scope.url,  
		    data:$scope.dataJson,
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	//数据源
		    	$scope.users = req.list;
		    	$scope.pages = req.pages; //分页数
				//分页
				$scope.paging();
	})
}
//初始化
$scope.loadTable();
$scope.paging=function(){
	$scope.newPages = $scope.pages > $scope.pageSize? $scope.pageSize : $scope.pages;
	$scope.pageList = [];
	$scope.selPage = $scope.pageNumber;
	//设置表格数据源(分页)
		//分页要repeat的数组
	for (var i = 0; i < $scope.newPages; i++) {
		$scope.pageList.push(i + 1);
	}
	$scope.setData = function () {
		//通过当前页数筛选出表格当前显示数据
		$scope.pageNumber = $scope.selPage;
		var data ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
		 $http({  
		    method:'post',  
		    url:$scope.url,  
		    data:data,
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	$scope.users = req.list;
		    })
	}
	$scope.selectPage = function (page) {
		//不能小于1大于最大
		if (page < 1 || page > $scope.pages) return;
		//最多显示分页数5
		if (page > 2) {
			//因为只显示5个页数，大于2页开始分页转换
			var newpageList = [];
			for (var i = (page - 3) ; i < ((page + 2) > $scope.pages ? $scope.pages : (page + 2)) ; i++) {
				newpageList.push(i + 1);
			}
			$scope.pageList = newpageList;
		}
		if($scope.selPage!=page){
			$scope.selPage = page;
			$scope.setData();
			$scope.isActivePage(page);
		}
	};
	//设置当前选中页样式
	$scope.isActivePage = function (page) {
		
		return $scope.selPage == page;
	};
	//上一页
	$scope.Previous = function () {
		$scope.selectPage($scope.selPage - 1);
	}
	//下一页
	$scope.Next = function () {
		$scope.selectPage($scope.selPage + 1);
	};
}

//清除
$scope.clearInput= function () {
	document.getElementById("withdrawRecordForm").reset();
};
//查询

$scope.search=function(){
		var formData=$("#withdrawRecordForm").serializeArray();
		var pidsArr =new Array;
		for(var i=0;i<formData.length;i++){
			pidsArr.push(formData[i].value)
		}
		
		
		
		if(pidsArr[3]==""){
			$scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber,
					userName:pidsArr[0],startTime:pidsArr[1],endTime:pidsArr[2]
			};
		}else{
			$scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber,
					userName:pidsArr[0],startTime:pidsArr[1],endTime:pidsArr[2],status:pidsArr[3]
			};
		}
		console.log($scope.dataJson);
		$scope.loadTable();
	};
	//导出
	$scope.export=function(){
		var formData=$("#withdrawRecordForm").serializeArray();
		var pidsArr =new Array;
		for(var i=0;i<formData.length;i++){
			pidsArr.push(formData[i].value)
		}
		$scope.dataJson = 'userName='+pidsArr[0]+'&'+'startTime	='+pidsArr[1]+'&'+'endTime='+pidsArr[2]+'&'+'status='+pidsArr[3];
		$scope.url="/jygbg/userWithdrawCash/excelRecharge?"+$scope.dataJson;
		window.location.href=$scope.url;
	};
	//体现完成 
	$scope.complete=function($event,params){
			var userid =$event.target.getAttribute('acterid');
	   		data={type:params,withdrawid:userid};
	   		console.log(data);
   	   	 $http({  
 		    method:'post',  
 		    url:" /jygbg/userWithdrawCash/editStatus",  
 		    data:data,
 		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
 		    transformRequest: function(obj) {
 		    	var str = [];
 		    	for(var p in obj){
 		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
 		    	}
 		    	return str.join("&");
 	    	}
 		    }).success(function(req){
 		    	if(req.msg==1){
 		    		 $scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
 		    		$scope.loadTable();
 		    		 $scope.alertTxt ="操作成功"
	   	   	 }else{
	   	   		 $scope.alertTxt ="操作失败"
	   	   	 }
		    }).error(function(){
		    	alert('fail')
		    })
	   	};
}]);

///**=========================================================
//*奖金发放记录控制器
//=========================================================*/
App.controller('bonusRecordController', ['$scope', '$filter', '$http', 'editableOptions', 'editableThemes','$q','$timeout',
function($scope, $filter, $http, editableOptions, editableThemes, $q,$timeout) {
	$scope.pageSize = 10;
	$scope.pageNumber = 1;
	$scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
	$scope.url ="/jygbg/userAccountRecord/selectGameAward";
	$scope.loadTable = function(){
		  $http({  
		    method:'post',  
		    url:$scope.url,  
		    data:$scope.dataJson,
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	//数据源
		    	$scope.users = req.list;
		    	$scope.pages = req.pages; //分页数
				//分页
				$scope.paging();
	})
}
//初始化
$scope.loadTable();
$scope.paging=function(){
	$scope.newPages = $scope.pages > $scope.pageSize? $scope.pageSize : $scope.pages;
	$scope.pageList = [];
	$scope.selPage = $scope.pageNumber;
	//设置表格数据源(分页)
		//分页要repeat的数组
	for (var i = 0; i < $scope.newPages; i++) {
		$scope.pageList.push(i + 1);
	}
	$scope.setData = function () {
		//通过当前页数筛选出表格当前显示数据
		$scope.pageNumber = $scope.selPage;
		var data ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
		 $http({  
		    method:'post',  
		    url:$scope.url,  
		    data:data,
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	$scope.users = req.list;
		    })
	}
	$scope.selectPage = function (page) {
		//不能小于1大于最大
		if (page < 1 || page > $scope.pages) return;
		//最多显示分页数5
		if (page > 2) {
			//因为只显示5个页数，大于2页开始分页转换
			var newpageList = [];
			for (var i = (page - 3) ; i < ((page + 2) > $scope.pages ? $scope.pages : (page + 2)) ; i++) {
				newpageList.push(i + 1);
			}
			$scope.pageList = newpageList;
		}
		if($scope.selPage!=page){
			$scope.selPage = page;
			$scope.setData();
			$scope.isActivePage(page);
		}
	};
	//设置当前选中页样式
	$scope.isActivePage = function (page) {
		
		return $scope.selPage == page;
	};
	//上一页
	$scope.Previous = function () {
		$scope.selectPage($scope.selPage - 1);
	}
	//下一页
	$scope.Next = function () {
		$scope.selectPage($scope.selPage + 1);
	};
}

//清除
$scope.clearInput= function () {
	document.getElementById("rechargeRecordForm").reset();
};
//查询

$scope.search=function(){
		var formData=$("#rechargeRecordForm").serializeArray();
		var pidsArr =new Array;
		for(var i=0;i<formData.length;i++){
			pidsArr.push(formData[i].value)
		}
		$scope.url ="/jygbg/userAccountRecord/selectGameAward";
		$scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber,
				userName:pidsArr[0],startTime:pidsArr[1],endTime:pidsArr[2],
		};
		console.log($scope.dataJson);
		$scope.loadTable();
	};
	//导出
	$scope.export=function(){
		var formData=$("#rechargeRecordForm").serializeArray();
		var pidsArr =new Array;
		for(var i=0;i<formData.length;i++){
			pidsArr.push(formData[i].value)
		}
		$scope.dataJson = 'userName='+pidsArr[0]+'&'+'startTime	='+pidsArr[1]+'&'+'endTime='+pidsArr[2];
		$scope.url="/jygbg/userAccountRecord/excelGameAward?"+$scope.dataJson;
		window.location.href=$scope.url;
	};
}]);

///**=========================================================
//*信息发布控制器
//=========================================================*/
App.controller('infoDeliveryController', ['$scope', '$filter', '$http', 'editableOptions', 'editableThemes','$q','$timeout',
function($scope, $filter, $http, editableOptions, editableThemes, $q,$timeout) {
	$scope.pageSize = 10;
	$scope.pageNumber = 1;
	$scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
	$scope.url ="/jygbg/infoInforMation/selectByInfoInforMation";
	$scope.loadTable = function(){
		  $http({  
		    method:'post',  
		    url:$scope.url,  
		    data:$scope.dataJson,
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	//数据源
		    	$scope.users = req.list;
		    	$scope.pages = req.pages; //分页数
				//分页
				$scope.paging();
	})
}
//初始化
$scope.loadTable();
$scope.paging=function(){
	$scope.newPages = $scope.pages > $scope.pageSize? $scope.pageSize : $scope.pages;
	$scope.pageList = [];
	$scope.selPage = $scope.pageNumber;
	//设置表格数据源(分页)
		//分页要repeat的数组
	for (var i = 0; i < $scope.newPages; i++) {
		$scope.pageList.push(i + 1);
	}
	$scope.setData = function () {
		//通过当前页数筛选出表格当前显示数据
		$scope.pageNumber = $scope.selPage;
		var data ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
		 $http({  
		    method:'post',  
		    url:$scope.url,  
		    data:data,
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	$scope.users = req.list;
		    })
	}
	$scope.selectPage = function (page) {
		//不能小于1大于最大
		if (page < 1 || page > $scope.pages) return;
		//最多显示分页数5
		if (page > 2) {
			//因为只显示5个页数，大于2页开始分页转换
			var newpageList = [];
			for (var i = (page - 3) ; i < ((page + 2) > $scope.pages ? $scope.pages : (page + 2)) ; i++) {
				newpageList.push(i + 1);
			}
			$scope.pageList = newpageList;
		}
		if($scope.selPage!=page){
			$scope.selPage = page;
			$scope.setData();
			$scope.isActivePage(page);
		}
	};
	//设置当前选中页样式
	$scope.isActivePage = function (page) {
		
		return $scope.selPage == page;
	};
	//上一页
	$scope.Previous = function () {
		$scope.selectPage($scope.selPage - 1);
	}
	//下一页
	$scope.Next = function () {
		$scope.selectPage($scope.selPage + 1);
	};
}

//清除
$scope.clearInput= function () {
	document.getElementById("searchForms").reset();
};
//查询

$scope.search=function(){
		var formData=$("#accountInfoForm").serializeArray();
		var pidsArr =new Array;
		for(var i=0;i<formData.length;i++){
			pidsArr.push(formData[i].value)
		}
		$scope.url ="/jygbg/infoInforMation/selectByInfoInforMation";
		$scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber,
				title:pidsArr[0],startTime:pidsArr[1],endTime:pidsArr[2],
				state:pidsArr[3]
		};
		console.log($scope.dataJson);
		$scope.loadTable();
	};
//发布
	$scope.editNew=function($event){
			var userid = $event.target.getAttribute('acterid');
			var outOrIn =$('#outOrIn').prop('checked');
			var title=$('#Intitle').val();
			var content=$('#Incontent').val();
			if(outOrIn==true){
				outOrIn=2;
			}else{
				outOrIn=1;
			}
	   		data={contentfromtype:outOrIn,title:title,contentpath:content};
	   		console.log(data);
	   	   	 $http({  
	 		    method:'post',  
	 		    url:" /jygbg/infoInforMation/posted",  
	 		    data:data,
	 		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
	 		    transformRequest: function(obj) {
	 		    	var str = [];
	 		    	for(var p in obj){
	 		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
	 		    	}
	 		    	return str.join("&");
	 	    	}
	 		    }).success(function(req){
	 		    	if(req.msg==1){
	 		    		 $scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
	 		    		 $scope.loadTable();
	 		    		 $scope.alertTxt ="修改成功"
		   	   	 }else{
		   	   		 $scope.alertTxt ="修改失败"
		   	   	 }
		    })
	   	};
	  //查看
	   	$scope.look=function($event){
				var serialno = $event.target.getAttribute('acterid');
		   		data={serialNo:serialno};
		   	   	 $http({  
		 		    method:'post',  
		 		    url:" /jygbg/infoInforMation/selectBySerialNo",  
		 		    data:data,
		 		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		 		    transformRequest: function(obj) {
		 		    	var str = [];
		 		    	for(var p in obj){
		 		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		 		    	}
		 		    	return str.join("&");
		 	    	}
		 		    }).success(function(req){
		 		    		$scope.Intitlelook=req.title;
		 		    		$scope.Incontentlook=req.contentpath;
		 		    		if(req.contentfromtype==1){
		 		    			$('#outOrInlook').prop('checked',true);
		 		    		}else{
		 		    			$('#outOrInlook').prop('checked',false);
		 		    		}
			    })
		   	};
		   	//编辑确定按钮
		   	$scope.editNew1=function($event){
		   		var serialno=$scope.serialno;
				var outOrIn =$('#outOrIn2').prop('checked');
				var title=$('#Intitle2').val();
				var content=$('#Incontent2').val();
				if(outOrIn==true){
					outOrIn=2;
				}else{
					outOrIn=1;
				}
		   		data={serialno:serialno,contentfromtype:outOrIn,title:title,contentpath:content,state:1};
		   		console.log(data);
		   	   	 $http({  
		 		    method:'post',  
		 		    url:" /jygbg/infoInforMation/edit",  
		 		    data:data,
		 		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		 		    transformRequest: function(obj) {
		 		    	var str = [];
		 		    	for(var p in obj){
		 		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		 		    	}
		 		    	return str.join("&");
		 	    	}
		 		    }).success(function(req){
		 		    	if(req.msg==1){
		 		    		 $scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
		 		    		 $scope.loadTable();
		 		    		 $scope.alertTxt ="修改成功"
			   	   	 }else{
			   	   		 $scope.alertTxt ="修改失败"
			   	   	 }
			    })
		   	};
		   	//编辑显示李彪
		   	$scope.edit1=function($event){
		   		var serialno = $event.target.getAttribute('acterid');
		   		$scope.serialno = serialno;
		   		data={serialNo:serialno};
		   	   	 $http({  
		 		    method:'post',  
		 		    url:" /jygbg/infoInforMation/selectBySerialNo",  
		 		    data:data,
		 		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		 		    transformRequest: function(obj) {
		 		    	var str = [];
		 		    	for(var p in obj){
		 		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		 		    	}
		 		    	return str.join("&");
		 	    	}
		 		    }).success(function(req){
		 		    		$scope.Intitle1=req.title;
		 		    		$scope.Incontent1=req.contentpath;
		 		    		if(req.contentfromtype==1){
		 		    			$('#outOrIn2').prop('checked',true);
		 		    		}else{
		 		    			$('#outOrIn2').prop('checked',false);
		 		    		}
			    })
		   	};
	  //删除
		$scope.deleteMsg=function($event){
				var serialno = $event.target.getAttribute('acterid');
		   		data={serialNo:serialno};
		   		console.log(data);
		   	   	 $http({  
		 		    method:'post',  
		 		    url:" /jygbg/infoInforMation/deleteById",  
		 		    data:data,
		 		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		 		    transformRequest: function(obj) {
		 		    	var str = [];
		 		    	for(var p in obj){
		 		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		 		    	}
		 		    	return str.join("&");
		 	    	}
		 		    }).success(function(req){
		 		    	if(req.msg==1){
		 		    		 $scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
		 		    		 $scope.loadTable();
		 		    		 $scope.alertTxt ="修改成功"
			   	   	 }else{
			   	   		 $scope.alertTxt ="修改失败"
			   	   	 }
			    })
		   	};
		   	//置顶/取消置顶
		   	$scope.goTop=function($event,params){
				var serialno = $event.target.getAttribute('acterid');
		   		data={serialNo:serialno,topState:params};
		   		console.log(data);
		   	   	 $http({  
		 		    method:'post',  
		 		    url:" /jygbg/infoInforMation/editTopState",  
		 		    data:data,
		 		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		 		    transformRequest: function(obj) {
		 		    	var str = [];
		 		    	for(var p in obj){
		 		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		 		    	}
		 		    	return str.join("&");
		 	    	}
		 		    }).success(function(req){
		 		    	if(req.msg==1){
		 		    		 $scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
		 		    		 $scope.loadTable();
		 		    		 $scope.alertTxt ="修改成功"
			   	   	 }else{
			   	   		 $scope.alertTxt ="修改失败"
			   	   	 }
			    })
		   	};
}]);
///**=========================================================
//*公告发布控制器
//=========================================================*/
App.controller('infoDeliveryNewsController', ['$scope', '$filter', '$http', 'editableOptions', 'editableThemes','$q','$timeout',
function($scope, $filter, $http, editableOptions, editableThemes, $q,$timeout) {
	$scope.pageSize = 10;
	$scope.pageNumber = 1;
	$scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
	$scope.url ="/jygbg/infoNotice/seelctAll";
	$scope.loadTable = function(){
		  $http({  
		    method:'post',  
		    url:$scope.url,  
		    data:$scope.dataJson,
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	//数据源
		    	$scope.users = req.list;
		    	$scope.pages = req.pages; //分页数
				//分页
				$scope.paging();
	})
}
//初始化
$scope.loadTable();
$scope.paging=function(){
	$scope.newPages = $scope.pages > $scope.pageSize? $scope.pageSize : $scope.pages;
	$scope.pageList = [];
	$scope.selPage = $scope.pageNumber;
	//设置表格数据源(分页)
		//分页要repeat的数组
	for (var i = 0; i < $scope.newPages; i++) {
		$scope.pageList.push(i + 1);
	}
	$scope.setData = function () {
		//通过当前页数筛选出表格当前显示数据
		$scope.pageNumber = $scope.selPage;
		var data ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
		 $http({  
		    method:'post',  
		    url:$scope.url,  
		    data:data,
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	$scope.users = req.list;
		    })
	}
	$scope.selectPage = function (page) {
		//不能小于1大于最大
		if (page < 1 || page > $scope.pages) return;
		//最多显示分页数5
		if (page > 2) {
			//因为只显示5个页数，大于2页开始分页转换
			var newpageList = [];
			for (var i = (page - 3) ; i < ((page + 2) > $scope.pages ? $scope.pages : (page + 2)) ; i++) {
				newpageList.push(i + 1);
			}
			$scope.pageList = newpageList;
		}
		if($scope.selPage!=page){
			$scope.selPage = page;
			$scope.setData();
			$scope.isActivePage(page);
		}
	};
	//设置当前选中页样式
	$scope.isActivePage = function (page) {
		
		return $scope.selPage == page;
	};
	//上一页
	$scope.Previous = function () {
		$scope.selectPage($scope.selPage - 1);
	}
	//下一页
	$scope.Next = function () {
		$scope.selectPage($scope.selPage + 1);
	};
}

//清除
$scope.clearInput= function () {
	document.getElementById("searchForms").reset();
};
//查询

$scope.search=function(){
		var formData=$("#accountInfoForm").serializeArray();
		var pidsArr =new Array;
		for(var i=0;i<formData.length;i++){
			pidsArr.push(formData[i].value)
		}
		$scope.url ="/jygbg/infoInforMation/selectByInfoInforMation";
		$scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber,
				title:pidsArr[0],startTime:pidsArr[1],endTime:pidsArr[2],
				state:pidsArr[3]
		};
		console.log($scope.dataJson);
		$scope.loadTable();
	};
//发布
	$scope.editNew=function($event){
			var userid = $event.target.getAttribute('acterid');
			var title=$('#Intitle').val();
			var content=$('#Incontent').val();
	   		data={title:title,content:content};
	   		console.log(data);
	   	   	 $http({  
	 		    method:'post',  
	 		    url:" /jygbg/infoNotice/add",  
	 		    data:data,
	 		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
	 		    transformRequest: function(obj) {
	 		    	var str = [];
	 		    	for(var p in obj){
	 		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
	 		    	}
	 		    	return str.join("&");
	 	    	}
	 		    }).success(function(req){
	 		    	if(req.msg==1){
	 		    		 $scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
	 		    		 $scope.loadTable();
	 		    		 $scope.alertTxt ="修改成功"
		   	   	 }else{
		   	   		 $scope.alertTxt ="修改失败"
		   	   	 }
		    })
	   	};
	  //查看
	   	$scope.look=function($event){
				var serialno = $event.target.getAttribute('acterid');
		   		data={serialNo:serialno};
		   	   	 $http({  
		 		    method:'post',  
		 		    url:" /jygbg/infoNotice/selectAll",  
		 		    data:data,
		 		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		 		    transformRequest: function(obj) {
		 		    	var str = [];
		 		    	for(var p in obj){
		 		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		 		    	}
		 		    	return str.join("&");
		 	    	}
		 		    }).success(function(req){
		 		    		$scope.Intitlelook=req.title;
		 		    		$scope.Incontentlook=req.contentpath;
		 		    		if(req.contentfromtype==1){
		 		    			$('#outOrInlook').prop('checked',true);
		 		    		}else{
		 		    			$('#outOrInlook').prop('checked',false);
		 		    		}
			    })
		   	};
		   	//编辑确定按钮
		   	$scope.editNew1=function($event){
		   		var serialno=$scope.serialno;
				var outOrIn =$('#outOrIn2').prop('checked');
				var title=$('#Intitle2').val();
				var content=$('#Incontent2').val();
				if(outOrIn==true){
					outOrIn=2;
				}else{
					outOrIn=1;
				}
		   		data={serialno:serialno,contentfromtype:outOrIn,title:title,contentpath:content,state:1};
		   		console.log(data);
		   	   	 $http({  
		 		    method:'post',  
		 		    url:" /jygbg/infoInforMation/edit",  
		 		    data:data,
		 		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		 		    transformRequest: function(obj) {
		 		    	var str = [];
		 		    	for(var p in obj){
		 		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		 		    	}
		 		    	return str.join("&");
		 	    	}
		 		    }).success(function(req){
		 		    	if(req.msg==1){
		 		    		 $scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
		 		    		 $scope.loadTable();
		 		    		 $scope.alertTxt ="修改成功"
			   	   	 }else{
			   	   		 $scope.alertTxt ="修改失败"
			   	   	 }
			    })
		   	};
		   	//编辑显示列表
		   	$scope.edit1=function($event){
		   		var serialno = $event.target.getAttribute('acterid');
		   		$scope.serialno = serialno;
		   		data={serialNo:serialno};
		   	   	 $http({  
		 		    method:'post',  
		 		    url:" /jygbg/infoInforMation/selectBySerialNo",  
		 		    data:data,
		 		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		 		    transformRequest: function(obj) {
		 		    	var str = [];
		 		    	for(var p in obj){
		 		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		 		    	}
		 		    	return str.join("&");
		 	    	}
		 		    }).success(function(req){
		 		    		$scope.Intitle1=req.title;
		 		    		$scope.Incontent1=req.contentpath;
		 		    		if(req.contentfromtype==1){
		 		    			$('#outOrIn2').prop('checked',true);
		 		    		}else{
		 		    			$('#outOrIn2').prop('checked',false);
		 		    		}
			    })
		   	};
		   	//置顶/取消置顶
		   	$scope.goTop=function($event,params){
				var serialno = $event.target.getAttribute('acterid');
		   		data={serialNo:serialno,topState:params};
		   		console.log(data);
		   	   	 $http({  
		 		    method:'post',  
		 		    url:" /jygbg/infoInforMation/editTopState",  
		 		    data:data,
		 		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		 		    transformRequest: function(obj) {
		 		    	var str = [];
		 		    	for(var p in obj){
		 		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		 		    	}
		 		    	return str.join("&");
		 	    	}
		 		    }).success(function(req){
		 		    	if(req.msg==1){
		 		    		 $scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
		 		    		 $scope.loadTable();
		 		    		 $scope.alertTxt ="修改成功"
			   	   	 }else{
			   	   		 $scope.alertTxt ="修改失败"
			   	   	 }
			    })
		   	};
		    //删除
			$scope.deleteMsg=function($event){
					var serialno = $event.target.getAttribute('acterid');
			   		data={serialNo:serialno};
			   		console.log(data);
			   	   	 $http({  
			 		    method:'post',  
			 		    url:" /jygbg/infoNotice/delete",  
			 		    data:data,
			 		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
			 		    transformRequest: function(obj) {
			 		    	var str = [];
			 		    	for(var p in obj){
			 		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
			 		    	}
			 		    	return str.join("&");
			 	    	}
			 		    }).success(function(req){
			 		    	if(req.msg==1){
			 		    		 $scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
			 		    		 $scope.loadTable();
			 		    		 $scope.alertTxt ="修改成功"
				   	   	 }else{
				   	   		 $scope.alertTxt ="修改失败"
				   	   	 }
				    })
			   	};
}]);
///**=========================================================
//*消息列表控制器
//=========================================================*/
App.controller('msgListController', ['$scope', '$filter', '$http', 'editableOptions', 'editableThemes','$q','$timeout',
function($scope, $filter, $http, editableOptions, editableThemes, $q,$timeout) {
	$scope.pageSize = 10;
	$scope.pageNumber = 1;
	$scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
	$scope.url ="/jygbg/userMessage/selectByAll";
	$scope.loadTable = function(){
		  $http({  
		    method:'post',  
		    url:$scope.url,  
		    data:$scope.dataJson,
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	//数据源
		    	$scope.users = req.list;
		    	$scope.pages = req.pages; //分页数
				//分页
				$scope.paging();
	})
}
//初始化
$scope.loadTable();
$scope.paging=function(){
	$scope.newPages = $scope.pages > $scope.pageSize? $scope.pageSize : $scope.pages;
	$scope.pageList = [];
	$scope.selPage = $scope.pageNumber;
	//设置表格数据源(分页)
		//分页要repeat的数组
	for (var i = 0; i < $scope.newPages; i++) {
		$scope.pageList.push(i + 1);
	}
	$scope.setData = function () {
		//通过当前页数筛选出表格当前显示数据
		$scope.pageNumber = $scope.selPage;
		var data ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
		 $http({  
		    method:'post',  
		    url:$scope.url,  
		    data:data,
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	$scope.users = req.list;
		    })
	}
	$scope.selectPage = function (page) {
		//不能小于1大于最大
		if (page < 1 || page > $scope.pages) return;
		//最多显示分页数5
		if (page > 2) {
			//因为只显示5个页数，大于2页开始分页转换
			var newpageList = [];
			for (var i = (page - 3) ; i < ((page + 2) > $scope.pages ? $scope.pages : (page + 2)) ; i++) {
				newpageList.push(i + 1);
			}
			$scope.pageList = newpageList;
		}
		if($scope.selPage!=page){
			$scope.selPage = page;
			$scope.setData();
			$scope.isActivePage(page);
		}
	};
	//设置当前选中页样式
	$scope.isActivePage = function (page) {
		
		return $scope.selPage == page;
	};
	//上一页
	$scope.Previous = function () {
		$scope.selectPage($scope.selPage - 1);
	}
	//下一页
	$scope.Next = function () {
		$scope.selectPage($scope.selPage + 1);
	};
}
}]);
///**=========================================================
//*评论管理控制器
//=========================================================*/
App.controller('commentMgController', ['$scope', '$filter', '$http', 'editableOptions', 'editableThemes','$q','$timeout',
function($scope, $filter, $http, editableOptions, editableThemes, $q,$timeout) {
	$scope.pageSize = 10;
	$scope.pageNumber = 1;
	$scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
	$scope.url ="/jygbg/infoComment/selectCommentAll";
	$scope.loadTable = function(){
		  $http({  
		    method:'post',  
		    url:$scope.url,  
		    data:$scope.dataJson,
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	//数据源
		    	$scope.users = req.list;
		    	$scope.pages = req.pages; //分页数
				//分页
				$scope.paging();
	})
}
//初始化
$scope.loadTable();
$scope.paging=function(){
	$scope.newPages = $scope.pages > $scope.pageSize? $scope.pageSize : $scope.pages;
	$scope.pageList = [];
	$scope.selPage = $scope.pageNumber;
	//设置表格数据源(分页)
		//分页要repeat的数组
	for (var i = 0; i < $scope.newPages; i++) {
		$scope.pageList.push(i + 1);
	}
	$scope.setData = function () {
		//通过当前页数筛选出表格当前显示数据
		$scope.pageNumber = $scope.selPage;
		var data ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
		 $http({  
		    method:'post',  
		    url:$scope.url,  
		    data:data,
		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		    	var str = [];
		    	for(var p in obj){
		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    	}
		    	return str.join("&");
	    	}
		    }).success(function(req){
		    	$scope.users = req.list;
		    })
	}
	$scope.selectPage = function (page) {
		//不能小于1大于最大
		if (page < 1 || page > $scope.pages) return;
		//最多显示分页数5
		if (page > 2) {
			//因为只显示5个页数，大于2页开始分页转换
			var newpageList = [];
			for (var i = (page - 3) ; i < ((page + 2) > $scope.pages ? $scope.pages : (page + 2)) ; i++) {
				newpageList.push(i + 1);
			}
			$scope.pageList = newpageList;
		}
		if($scope.selPage!=page){
			$scope.selPage = page;
			$scope.setData();
			$scope.isActivePage(page);
		}
	};
	//设置当前选中页样式
	$scope.isActivePage = function (page) {
		
		return $scope.selPage == page;
	};
	//上一页
	$scope.Previous = function () {
		$scope.selectPage($scope.selPage - 1);
	}
	//下一页
	$scope.Next = function () {
		$scope.selectPage($scope.selPage + 1);
	};
}
	  //查看
	   	$scope.look=function($event){
				var serialno = $event.target.getAttribute('acterid');
		   		data={serialNo:serialno};
		   	   	 $http({  
		 		    method:'post',  
		 		    url:" /jygbg/infoInforMation/selectBySerialNo",  
		 		    data:data,
		 		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		 		    transformRequest: function(obj) {
		 		    	var str = [];
		 		    	for(var p in obj){
		 		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		 		    	}
		 		    	return str.join("&");
		 	    	}
		 		    }).success(function(req){
		 		    		$scope.Intitlelook=req.title;
		 		    		$scope.Incontentlook=req.contentpath;
		 		    		if(req.contentfromtype==1){
		 		    			$('#outOrInlook').prop('checked',true);
		 		    		}else{
		 		    			$('#outOrInlook').prop('checked',false);
		 		    		}
			    })
		   	};
		   	//编辑确定按钮
		   	$scope.editNew1=function($event){
		   		var serialno=$scope.serialno;
				var outOrIn =$('#outOrIn2').prop('checked');
				var title=$('#Intitle2').val();
				var content=$('#Incontent2').val();
				if(outOrIn==true){
					outOrIn=2;
				}else{
					outOrIn=1;
				}
		   		data={serialno:serialno,contentfromtype:outOrIn,title:title,contentpath:content,state:1};
		   		console.log(data);
		   	   	 $http({  
		 		    method:'post',  
		 		    url:" /jygbg/infoInforMation/edit",  
		 		    data:data,
		 		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		 		    transformRequest: function(obj) {
		 		    	var str = [];
		 		    	for(var p in obj){
		 		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		 		    	}
		 		    	return str.join("&");
		 	    	}
		 		    }).success(function(req){
		 		    	if(req.msg==1){
		 		    		 $scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
		 		    		 $scope.loadTable();
		 		    		 $scope.alertTxt ="修改成功"
			   	   	 }else{
			   	   		 $scope.alertTxt ="修改失败"
			   	   	 }
			    })
		   	};
		   	//编辑显示李彪
		   	$scope.edit1=function($event){
		   		var serialno = $event.target.getAttribute('acterid');
		   		$scope.serialno = serialno;
		   		data={serialNo:serialno};
		   	   	 $http({  
		 		    method:'post',  
		 		    url:" /jygbg/infoInforMation/selectBySerialNo",  
		 		    data:data,
		 		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		 		    transformRequest: function(obj) {
		 		    	var str = [];
		 		    	for(var p in obj){
		 		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		 		    	}
		 		    	return str.join("&");
		 	    	}
		 		    }).success(function(req){
		 		    		$scope.Intitle1=req.title;
		 		    		$scope.Incontent1=req.contentpath;
		 		    		if(req.contentfromtype==1){
		 		    			$('#outOrIn2').prop('checked',true);
		 		    		}else{
		 		    			$('#outOrIn2').prop('checked',false);
		 		    		}
			    })
		   	};
	  //删除
		$scope.deleteMsg=function($event){
				var serialno = $event.target.getAttribute('acterid');
		   		data={serialNo:serialno};
		   		console.log(data);
		   	   	 $http({  
		 		    method:'post',  
		 		    url:" /jygbg/infoComment/deleteComment",  
		 		    data:data,
		 		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		 		    transformRequest: function(obj) {
		 		    	var str = [];
		 		    	for(var p in obj){
		 		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		 		    	}
		 		    	return str.join("&");
		 	    	}
		 		    }).success(function(req){
		 		    	if(req.msg==1){
		 		    		 $scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
		 		    		 $scope.loadTable();
		 		    		 $scope.alertTxt ="修改成功"
			   	   	 }else{
			   	   		 $scope.alertTxt ="修改失败"
			   	   	 }
			    })
		   	};
		   	//置顶/取消置顶
		   	$scope.goTop=function($event,params){
				var serialno = $event.target.getAttribute('acterid');
		   		data={serialNo:serialno,topState:params};
		   		console.log(data);
		   	   	 $http({  
		 		    method:'post',  
		 		    url:" /jygbg/infoInforMation/editTopState",  
		 		    data:data,
		 		    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
		 		    transformRequest: function(obj) {
		 		    	var str = [];
		 		    	for(var p in obj){
		 		    		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		 		    	}
		 		    	return str.join("&");
		 	    	}
		 		    }).success(function(req){
		 		    	if(req.msg==1){
		 		    		 $scope.dataJson ={pageSize:$scope.pageSize,pageNum:$scope.pageNumber}
		 		    		 $scope.loadTable();
		 		    		 $scope.alertTxt ="修改成功"
			   	   	 }else{
			   	   		 $scope.alertTxt ="修改失败"
			   	   	 }
			    })
		   	};
}]);

















 ///**=========================================================
// * Module panel-tools.js
// * Directive tools to control panels. 
// =========================================================*/
App.directive('paneltool', ["$compile", "$timeout", function($compile, $timeout){
var templates = {
    /* jshint multistr: true */
    collapse:"<a href='#' panel-collapse='' tooltip='Collapse Panel' ng-click='{{panelId}} = !{{panelId}}'> \
                <em ng-show='{{panelId}}' class='fa fa-plus'></em> \
                <em ng-show='!{{panelId}}' class='fa fa-minus'></em> \
              </a>",
    dismiss: "<a href='#' panel-dismiss='' tooltip='Close Panel'>\
               <em class='fa fa-times'></em>\
             </a>",
    refresh: "<a href='#' panel-refresh='' data-spinner='{{spinner}}' tooltip='Refresh Panel'>\
               <em class='fa fa-refresh'></em>\
             </a>"
};

function getTemplate( elem, attrs ){
    var temp = '';
    attrs = attrs || {};
    if(attrs.toolCollapse)
      temp += templates.collapse.replace(/{{panelId}}/g, (elem.parent().parent().attr('id')) );
    if(attrs.toolDismiss)
      temp += templates.dismiss;
    if(attrs.toolRefresh)
      temp += templates.refresh.replace(/{{spinner}}/g, attrs.toolRefresh);
    return temp;
}

return {
    restrict: 'E',
    scope: false,
    link: function (scope, element, attrs) {

      var tools = scope.panelTools || attrs;

      $timeout(function() {
        element.html(getTemplate(element, tools )).show();
        $compile(element.contents())(scope);
        
        element.addClass('pull-right');
      });

    }
};
}])
/**=========================================================
   * Dismiss panels * [panel-dismiss]
   =========================================================*/
.directive('panelDismiss', ["$q", "Utils", function($q, Utils){
'use strict';
return {
    restrict: 'A',
    controller: ["$scope", "$element", function ($scope, $element) {
      var removeEvent   = 'panel-remove',
          removedEvent  = 'panel-removed';

      $element.on('click', function () {

        // find the first parent panel
        var parent = $(this).closest('.panel');

        removeElement();

        function removeElement() {
          var deferred = $q.defer();
          var promise = deferred.promise;
          
          // Communicate event destroying panel
          $scope.$emit(removeEvent, parent.attr('id'), deferred);
          promise.then(destroyMiddleware);
        }

        // Run the animation before destroy the panel
        function destroyMiddleware() {
          if(Utils.support.animation) {
            parent.animo({animation: 'bounceOut'}, destroyPanel);
          }
          else destroyPanel();
        }

        function destroyPanel() {

          var col = parent.parent();
          parent.remove();
          // remove the parent if it is a row and is empty and not a sortable (portlet)
          col
            .filter(function() {
            var el = $(this);
            return (el.is('[class*="col-"]:not(.sortable)') && el.children('*').length === 0);
          }).remove();

          // Communicate event destroyed panel
          $scope.$emit(removedEvent, parent.attr('id'));

        }
      });
    }]
};
}])
///**=========================================================
// * Collapse panels * [panel-collapse]
// =========================================================*/
.directive('panelCollapse', ['$timeout', function($timeout){
'use strict';

var storageKeyName = 'panelState',
      storage;

return {
    restrict: 'A',
    scope: false,
    controller: ["$scope", "$element", function ($scope, $element) {

      // Prepare the panel to be collapsible
      var $elem   = $($element),
          parent  = $elem.closest('.panel'), // find the first parent panel
          panelId = parent.attr('id');

      storage = $scope.$storage;

      // Load the saved state if exists
      var currentState = loadPanelState( panelId );
      if ( typeof currentState !== 'undefined') {
        $timeout(function(){
            $scope[panelId] = currentState; },
          10);
      }

      // bind events to switch icons
      $element.bind('click', function() {

        savePanelState( panelId, !$scope[panelId] );

      });
    }]
};

function savePanelState(id, state) {
    if(!id) return false;
    var data = angular.fromJson(storage[storageKeyName]);
    if(!data) { data = {}; }
    data[id] = state;
    storage[storageKeyName] = angular.toJson(data);
}

function loadPanelState(id) {
    if(!id) return false;
    var data = angular.fromJson(storage[storageKeyName]);
    if(data) {
      return data[id];
    }
}

}])
///**=========================================================
// * Module: play-animation.js
// * Provides a simple way to run animation with a trigger
// * Requires animo.js
// =========================================================*/
// 
App.directive('animate', ["$window", "Utils", function($window, Utils){

'use strict';

var $scroller = $(window).add('body, .wrapper');

return {
    restrict: 'A',
    link: function (scope, elem, attrs) {

      // Parse animations params and attach trigger to scroll
      var $elem     = $(elem),
          offset    = $elem.data('offset'),
          delay     = $elem.data('delay')     || 100, // milliseconds
          animation = $elem.data('play')      || 'bounce';
      
      if(typeof offset !== 'undefined') {
        
        // test if the element starts visible
        testAnimation($elem);
        // test on scroll
        $scroller.scroll(function(){
          testAnimation($elem);
        });

      }

      // Test an element visibilty and trigger the given animation
      function testAnimation(element) {
          if ( !element.hasClass('anim-running') &&
              Utils.isInView(element, {topoffset: offset})) {
          element
            .addClass('anim-running');

          setTimeout(function() {
            element
              .addClass('anim-done')
              .animo( { animation: animation, duration: 0.7} );
          }, delay);

        }
      }

      // Run click triggered animations
      $elem.on('click', function() {

        var $elem     = $(this),
            targetSel = $elem.data('target'),
            animation = $elem.data('play') || 'bounce',
            target    = $(targetSel);

        if(target && target.length) {
          target.animo( { animation: animation } );
        }
        
      });
    }
};

}]);
///**=========================================================
// * Module: scroll.js
// * Make a content box scrollable
// =========================================================*/
//
App.directive('scrollable', function(){
return {
    restrict: 'EA',
    link: function(scope, elem, attrs) {
      var defaultHeight = 250;
      elem.slimScroll({
          height: (attrs.height || defaultHeight)
      });
    }
};
});
/**=========================================================
   * Module: sidebar.js
   * Wraps the sidebar and handles collapsed state
   =========================================================*/

App.directive('sidebar', ['$rootScope', '$window', 'Utils', function($rootScope, $window, Utils) {

var $win  = $($window);
var $body = $('body');
var $scope;
var $sidebar;
var currentState = $rootScope.$state.current.name;

return {
    restrict: 'EA',
    template: '<nav class="sidebar" ng-transclude></nav>',
    transclude: true,
    replace: true,
    link: function(scope, element, attrs) {
      
      $scope   = scope;
      $sidebar = element;

      var eventName = Utils.isTouch() ? 'click' : 'mouseenter' ;
      var subNav = $();
      $sidebar.on( eventName, '.nav > li', function() {

        if( Utils.isSidebarCollapsed() || $rootScope.app.layout.asideHover ) {

          subNav.trigger('mouseleave');
          subNav = toggleMenuItem( $(this) );

          // Used to detect click and touch events outside the sidebar          
          sidebarAddBackdrop();

        }

      });

      scope.$on('closeSidebarMenu', function() {
        removeFloatingNav();
      });

      // Normalize state when resize to mobile
      $win.on('resize', function() {
        if( ! Utils.isMobile() )
          $body.removeClass('aside-toggled');
      });

      // Adjustment on route changes
      $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams) {
        currentState = toState.name;
        // Hide sidebar automatically on mobile
        $('body.aside-toggled').removeClass('aside-toggled');

        $rootScope.$broadcast('closeSidebarMenu');
      });

      // Allows to close
      if ( angular.isDefined(attrs.sidebarAnyclickClose) ) {

        $('.wrapper').on('click.sidebar', function(e){
          // don't check if sidebar not visible
          if( ! $body.hasClass('aside-toggled')) return;

          // if not child of sidebar
          if( ! $(e.target).parents('.aside').length ) {
            $body.removeClass('aside-toggled');          
          }

        });
      }

    }
};

function sidebarAddBackdrop() {
    var $backdrop = $('<div/>', { 'class': 'dropdown-backdrop'} );
    $backdrop.insertAfter('.aside-inner').on("click mouseenter", function () {
      removeFloatingNav();
    });
}

// Open the collapse sidebar submenu items when on touch devices 
// - desktop only opens on hover
function toggleTouchItem($element){
    $element
      .siblings('li')
      .removeClass('open')
      .end()
      .toggleClass('open');
}

// Handles hover to open items under collapsed menu
// ----------------------------------- 
function toggleMenuItem($listItem) {

    removeFloatingNav();

    var ul = $listItem.children('ul');
    
    if( !ul.length ) return $();
    if( $listItem.hasClass('open') ) {
      toggleTouchItem($listItem);
      return $();
    }

    var $aside = $('.aside');
    var $asideInner = $('.aside-inner'); // for top offset calculation
    // float aside uses extra padding on aside
    var mar = parseInt( $asideInner.css('padding-top'), 0) + parseInt( $aside.css('padding-top'), 0);
    var subNav = ul.clone().appendTo( $aside );
    
    toggleTouchItem($listItem);

    var itemTop = ($listItem.position().top + mar) - $sidebar.scrollTop();
    var vwHeight = $win.height();

    subNav
      .addClass('nav-floating')
      .css({
        position: $scope.app.layout.isFixed ? 'fixed' : 'absolute',
        top:      itemTop,
        bottom:   (subNav.outerHeight(true) + itemTop > vwHeight) ? 0 : 'auto'
      });

    subNav.on('mouseleave', function() {
      toggleTouchItem($listItem);
      subNav.remove();
    });

    return subNav;
}

function removeFloatingNav() {
    $('.dropdown-backdrop').remove();
    $('.sidebar-subnav.nav-floating').remove();
    $('.sidebar li.open').removeClass('open');
}

}]);
///**=========================================================
// * Module: skycons.js
// * Include any animated weather icon from Skycons
// =========================================================*/
//
App.directive('skycon', function(){

return {
    restrict: 'A',
    link: function(scope, element, attrs) {
      
      var skycons = new Skycons({'color': (attrs.color || 'white')});

      element.html('<canvas width="' + attrs.width + '" height="' + attrs.height + '"></canvas>');

      skycons.add(element.children()[0], attrs.skycon);

      skycons.play();

    }
};
});
///**=========================================================
// * Module: sparkline.js
// * SparkLines Mini Charts
// =========================================================*/
// 
App.directive('sparkline', ['$timeout', '$window', function($timeout, $window){

'use strict';

return {
    restrict: 'EA',
    controller: ["$scope", "$element", function ($scope, $element) {
      var runSL = function(){
        initSparLine($element);
      };

      $timeout(runSL);
    }]
};

function initSparLine($element) {
    var options = $element.data();

    options.type = options.type || 'bar'; // default chart is bar
    options.disableHiddenCheck = true;

    $element.sparkline('html', options);

    if(options.resize) {
      $(window).resize(function(){
        $element.sparkline('html', options);
      });
    }
}

}]);
/**=========================================================
	//自定义多选指令
   =========================================================*/

App.directive('checkAll', function() {
'use strict';

return {
    restrict: 'A',
    controller: ["$scope", "$element", function($scope, $element){
      
      $element.on('change', function() {
        var $this = $(this),
            index= $this.index() + 1,
            checkbox = $this.find('input[type="checkbox"]'),
            table = $this.parents('table');
        table.find('tbody > tr > td:nth-child('+index+') input[type="checkbox"]')
          .prop('checked', checkbox[0].checked);

      });
    }]
};

});
/**=========================================================
   * Module: tags-input.js
   * Initializes the tag inputs plugin
   =========================================================*/

App.directive('tagsinput', ["$timeout", function($timeout) {
return {
    restrict: 'A',
    require: 'ngModel',
    link: function(scope, element, attrs, ngModel) {

      element.on('itemAdded itemRemoved', function(){
        // check if view value is not empty and is a string
        // and update the view from string to an array of tags
        if(ngModel.$viewValue && ngModel.$viewValue.split) {
          ngModel.$setViewValue( ngModel.$viewValue.split(',') );
          ngModel.$render();
        }
      });

      $timeout(function(){
        element.tagsinput();
      });

    }
};
}]);

/**=========================================================
   * Module: toggle-state.js
   * Toggle a classname from the BODY Useful to change a state that 
   * affects globally the entire layout or more than one item 
   * Targeted elements must have [toggle-state="CLASS-NAME-TO-TOGGLE"]
   * User no-persist to avoid saving the sate in browser storage
   =========================================================*/

App.directive('toggleState', ['toggleStateService', function(toggle) {
'use strict';

return {
    restrict: 'A',
    link: function(scope, element, attrs) {

      var $body = $('body');

      $(element)
        .on('click', function (e) {
          e.preventDefault();
          var classname = attrs.toggleState;
          
          if(classname) {
            if( $body.hasClass(classname) ) {
              $body.removeClass(classname);
              if( ! attrs.noPersist)
                toggle.removeState(classname);
            }
            else {
              $body.addClass(classname);
              if( ! attrs.noPersist)
                toggle.addState(classname);
            }
            
          }

      });
    }
};

}]);

/**=========================================================
   * Module: trigger-resize.js
   * Triggers a window resize event from any element
   =========================================================*/

App.directive("triggerResize", ['$window', '$timeout', function ($window, $timeout) {
return {
    restrict: 'A',
    link: function (scope, element, attrs) {
      element.on('click', function(){
        $timeout(function(){
          $window.dispatchEvent(new Event('resize'))
        });
      });
    }
};
}]);

/**=========================================================
   * Module: validate-form.js
   * Initializes the validation plugin Parsley
   =========================================================*/

App.directive('validateForm', function() {
return {
    restrict: 'A',
    controller: ["$scope", "$element", function($scope, $element) {
      var $elem = $($element);
      if($.fn.parsley)
        $elem.parsley();
    }]
};
});

/**=========================================================
   * Module: vector-map.js.js
   * Init jQuery Vector Map plugin
   =========================================================*/

App.directive('vectorMap', ['vectorMap', function(vectorMap){
'use strict';

var defaultColors = {
      markerColor:  '#23b7e5',      // the marker points
      bgColor:      'transparent',      // the background
      scaleColors:  ['#878c9a'],    // the color of the region in the serie
      regionFill:   '#bbbec6'       // the base region color
};

return {
    restrict: 'EA',
    link: function(scope, element, attrs) {

      var mapHeight   = attrs.height || '300',
          options     = {
            markerColor:  attrs.markerColor  || defaultColors.markerColor,
            bgColor:      attrs.bgColor      || defaultColors.bgColor,
            scale:        attrs.scale        || 1,
            scaleColors:  attrs.scaleColors  || defaultColors.scaleColors,
            regionFill:   attrs.regionFill   || defaultColors.regionFill,
            mapName:      attrs.mapName      || 'world_mill_en'
          };
      
      element.css('height', mapHeight);
      
      vectorMap.init( element , options, scope.seriesData, scope.markersData);

    }
};

}]);




/**=========================================================
 * Module: browser.js
 * Browser detection
 =========================================================*/

App.service('browser', function(){
  "use strict";

  var matched, browser;

  var uaMatch = function( ua ) {
    ua = ua.toLowerCase();

    var match = /(opr)[\/]([\w.]+)/.exec( ua ) ||
      /(chrome)[ \/]([\w.]+)/.exec( ua ) ||
      /(version)[ \/]([\w.]+).*(safari)[ \/]([\w.]+)/.exec( ua ) ||
      /(webkit)[ \/]([\w.]+)/.exec( ua ) ||
      /(opera)(?:.*version|)[ \/]([\w.]+)/.exec( ua ) ||
      /(msie) ([\w.]+)/.exec( ua ) ||
      ua.indexOf("trident") >= 0 && /(rv)(?::| )([\w.]+)/.exec( ua ) ||
      ua.indexOf("compatible") < 0 && /(mozilla)(?:.*? rv:([\w.]+)|)/.exec( ua ) ||
      [];

    var platform_match = /(ipad)/.exec( ua ) ||
      /(iphone)/.exec( ua ) ||
      /(android)/.exec( ua ) ||
      /(windows phone)/.exec( ua ) ||
      /(win)/.exec( ua ) ||
      /(mac)/.exec( ua ) ||
      /(linux)/.exec( ua ) ||
      /(cros)/i.exec( ua ) ||
      [];

    return {
      browser: match[ 3 ] || match[ 1 ] || "",
      version: match[ 2 ] || "0",
      platform: platform_match[ 0 ] || ""
    };
  };

  matched = uaMatch( window.navigator.userAgent );
  browser = {};

  if ( matched.browser ) {
    browser[ matched.browser ] = true;
    browser.version = matched.version;
    browser.versionNumber = parseInt(matched.version);
  }

  if ( matched.platform ) {
    browser[ matched.platform ] = true;
  }

  // These are all considered mobile platforms, meaning they run a mobile browser
  if ( browser.android || browser.ipad || browser.iphone || browser[ "windows phone" ] ) {
    browser.mobile = true;
  }

  // These are all considered desktop platforms, meaning they run a desktop browser
  if ( browser.cros || browser.mac || browser.linux || browser.win ) {
    browser.desktop = true;
  }

  // Chrome, Opera 15+ and Safari are webkit based browsers
  if ( browser.chrome || browser.opr || browser.safari ) {
    browser.webkit = true;
  }

  // IE11 has a new token so we will assign it msie to avoid breaking changes
  if ( browser.rv )
  {
    var ie = "msie";

    matched.browser = ie;
    browser[ie] = true;
  }

  // Opera 15+ are identified as opr
  if ( browser.opr )
  {
    var opera = "opera";

    matched.browser = opera;
    browser[opera] = true;
  }

  // Stock Android browsers are marked as Safari on Android.
  if ( browser.safari && browser.android )
  {
    var android = "android";

    matched.browser = android;
    browser[android] = true;
  }

  // Assign the name and platform variable
  browser.name = matched.browser;
  browser.platform = matched.platform;


  return browser;

});
/**=========================================================
 * Module: colors.js
 * Services to retrieve global colors
 =========================================================*/
 
App.factory('colors', ['APP_COLORS', function(colors) {
  
  return {
    byName: function(name) {
      return (colors[name] || '#fff');
    }
  };

}]);

/**=========================================================
 * Module: nav-search.js
 * Services to share navbar search functions
 =========================================================*/
 
App.service('navSearch', function() {
  var navbarFormSelector = 'form.navbar-form';
  return {
    toggle: function() {
      
      var navbarForm = $(navbarFormSelector);

      navbarForm.toggleClass('open');
      
      var isOpen = navbarForm.hasClass('open');
      
      navbarForm.find('input')[isOpen ? 'focus' : 'blur']();

    },

    dismiss: function() {
      $(navbarFormSelector)
        .removeClass('open')      // Close control
        .find('input[type="text"]').blur() // remove focus
        .val('')                    // Empty input
        ;
    }
  };

});
/**=========================================================
 * Module: notify.js
 * Create a notifications that fade out automatically.
 * Based on Notify addon from UIKit (http://getuikit.com/docs/addons_notify.html)
 =========================================================*/

App.service('Notify', ["$timeout", function($timeout){
    this.alert = alert;

    ////////////////

    function alert(msg, opts) {
        if ( msg ) {
            $timeout(function(){
                $.notify(msg, opts || {});
            });
        }
    }

}]);



/**
 * Notify Addon definition as jQuery plugin
 * Adapted version to work with Bootstrap classes
 * More information http://getuikit.com/docs/addons_notify.html
 */

(function($, window, document){

    var containers = {},
        messages   = {},

        notify     =  function(options){

            if ($.type(options) == 'string') {
                options = { message: options };
            }

            if (arguments[1]) {
                options = $.extend(options, $.type(arguments[1]) == 'string' ? {status:arguments[1]} : arguments[1]);
            }

            return (new Message(options)).show();
        },
        closeAll  = function(group, instantly){
            if(group) {
                for(var id in messages) { if(group===messages[id].group) messages[id].close(instantly); }
            } else {
                for(var id in messages) { messages[id].close(instantly); }
            }
        };

    var Message = function(options){

        var $this = this;

        this.options = $.extend({}, Message.defaults, options);

        this.uuid    = "ID"+(new Date().getTime())+"RAND"+(Math.ceil(Math.random() * 100000));
        this.element = $([
            // @geedmo: alert-dismissable enables bs close icon
            '<div class="uk-notify-message alert-dismissable">',
                '<a class="close">&times;</a>',
                '<div>'+this.options.message+'</div>',
            '</div>'

        ].join('')).data("notifyMessage", this);

        // status
        if (this.options.status) {
            this.element.addClass('alert alert-'+this.options.status);
            this.currentstatus = this.options.status;
        }

        this.group = this.options.group;

        messages[this.uuid] = this;

        if(!containers[this.options.pos]) {
            containers[this.options.pos] = $('<div class="uk-notify uk-notify-'+this.options.pos+'"></div>').appendTo('body').on("click", ".uk-notify-message", function(){
                $(this).data("notifyMessage").close();
            });
        }
    };


    $.extend(Message.prototype, {

        uuid: false,
        element: false,
        timout: false,
        currentstatus: "",
        group: false,

        show: function() {

            if (this.element.is(":visible")) return;

            var $this = this;

            containers[this.options.pos].show().prepend(this.element);

            var marginbottom = parseInt(this.element.css("margin-bottom"), 10);

            this.element.css({"opacity":0, "margin-top": -1*this.element.outerHeight(), "margin-bottom":0}).animate({"opacity":1, "margin-top": 0, "margin-bottom":marginbottom}, function(){

                if ($this.options.timeout) {

                    var closefn = function(){ $this.close(); };

                    $this.timeout = setTimeout(closefn, $this.options.timeout);

                    $this.element.hover(
                        function() { clearTimeout($this.timeout); },
                        function() { $this.timeout = setTimeout(closefn, $this.options.timeout);  }
                    );
                }

            });

            return this;
        },

        close: function(instantly) {

            var $this    = this,
                finalize = function(){
                    $this.element.remove();

                    if(!containers[$this.options.pos].children().length) {
                        containers[$this.options.pos].hide();
                    }

                    delete messages[$this.uuid];
                };

            if(this.timeout) clearTimeout(this.timeout);

            if(instantly) {
                finalize();
            } else {
                this.element.animate({"opacity":0, "margin-top": -1* this.element.outerHeight(), "margin-bottom":0}, function(){
                    finalize();
                });
            }
        },

        content: function(html){

            var container = this.element.find(">div");

            if(!html) {
                return container.html();
            }

            container.html(html);

            return this;
        },

        status: function(status) {

            if(!status) {
                return this.currentstatus;
            }

            this.element.removeClass('alert alert-'+this.currentstatus).addClass('alert alert-'+status);

            this.currentstatus = status;

            return this;
        }
    });

    Message.defaults = {
        message: "",
        status: "normal",
        timeout: 5000,
        group: null,
        pos: 'top-center'
    };


    $["notify"]          = notify;
    $["notify"].message  = Message;
    $["notify"].closeAll = closeAll;

    return notify;

}(jQuery, window, document));

/**=========================================================
 * Module: helpers.js
 * Provides helper functions for routes definition
 =========================================================*/

App.provider('RouteHelpers', ['APP_REQUIRES', function (appRequires) {
  "use strict";

  // Set here the base of the relative path
  // for all app views
  this.basepath = function (uri) {
    return 'app/views/' + uri;
  };

  // Generates a resolve object by passing script names
  // previously configured in constant.APP_REQUIRES
  this.resolveFor = function () {
    var _args = arguments;
    return {
      deps: ['$ocLazyLoad','$q', function ($ocLL, $q) {
        // Creates a promise chain for each argument
        var promise = $q.when(1); // empty promise
        for(var i=0, len=_args.length; i < len; i ++){
          promise = andThen(_args[i]);
        }
        return promise;

        // creates promise to chain dynamically
        function andThen(_arg) {
          // also support a function that returns a promise
          if(typeof _arg == 'function')
              return promise.then(_arg);
          else
              return promise.then(function() {
                // if is a module, pass the name. If not, pass the array
                var whatToLoad = getRequired(_arg);
                // simple error check
                if(!whatToLoad) return $.error('Route resolve: Bad resource name [' + _arg + ']');
                // finally, return a promise
                return $ocLL.load( whatToLoad );
              });
        }
        // check and returns required data
        // analyze module items with the form [name: '', files: []]
        // and also simple array of script files (for not angular js)
        function getRequired(name) {
          if (appRequires.modules)
              for(var m in appRequires.modules)
                  if(appRequires.modules[m].name && appRequires.modules[m].name === name)
                      return appRequires.modules[m];
          return appRequires.scripts && appRequires.scripts[name];
        }

      }]};
  }; // resolveFor

  // not necessary, only used in config block for routes
  this.$get = function(){
    return {
      basepath: this.basepath
    }
  };

}]);


/**=========================================================
 * Module: toggle-state.js
 * Services to share toggle state functionality
 =========================================================*/

App.service('toggleStateService', ['$rootScope', function($rootScope) {

  var storageKeyName  = 'toggleState';

  // Helper object to check for words in a phrase //
  var WordChecker = {
    hasWord: function (phrase, word) {
      return new RegExp('(^|\\s)' + word + '(\\s|$)').test(phrase);
    },
    addWord: function (phrase, word) {
      if (!this.hasWord(phrase, word)) {
        return (phrase + (phrase ? ' ' : '') + word);
      }
    },
    removeWord: function (phrase, word) {
      if (this.hasWord(phrase, word)) {
        return phrase.replace(new RegExp('(^|\\s)*' + word + '(\\s|$)*', 'g'), '');
      }
    }
  };

  // Return service public methods
  return {
    // Add a state to the browser storage to be restored later
    addState: function(classname){
      var data = angular.fromJson($rootScope.$storage[storageKeyName]);
      
      if(!data)  {
        data = classname;
      }
      else {
        data = WordChecker.addWord(data, classname);
      }

      $rootScope.$storage[storageKeyName] = angular.toJson(data);
    },

    // Remove a state from the browser storage
    removeState: function(classname){
      var data = $rootScope.$storage[storageKeyName];
      // nothing to remove
      if(!data) return;

      data = WordChecker.removeWord(data, classname);

      $rootScope.$storage[storageKeyName] = angular.toJson(data);
    },
    
    // Load the state string and restore the classlist
    restoreState: function($elem) {
      var data = angular.fromJson($rootScope.$storage[storageKeyName]);
      
      // nothing to restore
      if(!data) return;
      $elem.addClass(data);
    }

  };

}]);
/**=========================================================
 * Module: utils.js
 * Utility library to use across the theme
 =========================================================*/

App.service('Utils', ["$window", "APP_MEDIAQUERY", function($window, APP_MEDIAQUERY) {
    'use strict';
    
    var $html = angular.element("html"),
        $win  = angular.element($window),
        $body = angular.element('body');

    return {
      // DETECTION
      support: {
        transition: (function() {
                var transitionEnd = (function() {

                    var element = document.body || document.documentElement,
                        transEndEventNames = {
                            WebkitTransition: 'webkitTransitionEnd',
                            MozTransition: 'transitionend',
                            OTransition: 'oTransitionEnd otransitionend',
                            transition: 'transitionend'
                        }, name;

                    for (name in transEndEventNames) {
                        if (element.style[name] !== undefined) return transEndEventNames[name];
                    }
                }());

                return transitionEnd && { end: transitionEnd };
            })(),
        animation: (function() {

            var animationEnd = (function() {

                var element = document.body || document.documentElement,
                    animEndEventNames = {
                        WebkitAnimation: 'webkitAnimationEnd',
                        MozAnimation: 'animationend',
                        OAnimation: 'oAnimationEnd oanimationend',
                        animation: 'animationend'
                    }, name;

                for (name in animEndEventNames) {
                    if (element.style[name] !== undefined) return animEndEventNames[name];
                }
            }());

            return animationEnd && { end: animationEnd };
        })(),
        requestAnimationFrame: window.requestAnimationFrame ||
                               window.webkitRequestAnimationFrame ||
                               window.mozRequestAnimationFrame ||
                               window.msRequestAnimationFrame ||
                               window.oRequestAnimationFrame ||
                               function(callback){ window.setTimeout(callback, 1000/60); },
        touch: (
            ('ontouchstart' in window && navigator.userAgent.toLowerCase().match(/mobile|tablet/)) ||
            (window.DocumentTouch && document instanceof window.DocumentTouch)  ||
            (window.navigator['msPointerEnabled'] && window.navigator['msMaxTouchPoints'] > 0) || //IE 10
            (window.navigator['pointerEnabled'] && window.navigator['maxTouchPoints'] > 0) || //IE >=11
            false
        ),
        mutationobserver: (window.MutationObserver || window.WebKitMutationObserver || window.MozMutationObserver || null)
      },
      // UTILITIES
      isInView: function(element, options) {

          var $element = $(element);

          if (!$element.is(':visible')) {
              return false;
          }

          var window_left = $win.scrollLeft(),
              window_top  = $win.scrollTop(),
              offset      = $element.offset(),
              left        = offset.left,
              top         = offset.top;

          options = $.extend({topoffset:0, leftoffset:0}, options);

          if (top + $element.height() >= window_top && top - options.topoffset <= window_top + $win.height() &&
              left + $element.width() >= window_left && left - options.leftoffset <= window_left + $win.width()) {
            return true;
          } else {
            return false;
          }
      },
      langdirection: $html.attr("dir") == "rtl" ? "right" : "left",
      isTouch: function () {
        return $html.hasClass('touch');
      },
      isSidebarCollapsed: function () {
        return $body.hasClass('aside-collapsed');
      },
      isSidebarToggled: function () {
        return $body.hasClass('aside-toggled');
      },
      isMobile: function () {
        return $win.width() < APP_MEDIAQUERY.tablet;
      }
    };
}]);
/**=========================================================
 * Module: vector-map.js
 * Services to initialize vector map plugin
 =========================================================*/

App.service('vectorMap', function() {
  'use strict';
  return {
    init: function($element, opts, series, markers) {
          $element.vectorMap({
            map:             opts.mapName,
            backgroundColor: opts.bgColor,
            zoomMin:         1,
            zoomMax:         8,
            zoomOnScroll:    false,
            regionStyle: {
              initial: {
                'fill':           opts.regionFill,
                'fill-opacity':   1,
                'stroke':         'none',
                'stroke-width':   1.5,
                'stroke-opacity': 1
              },
              hover: {
                'fill-opacity': 0.8
              },
              selected: {
                fill: 'blue'
              },
              selectedHover: {
              }
            },
            focusOn:{ x:0.4, y:0.6, scale: opts.scale},
            markerStyle: {
              initial: {
                fill: opts.markerColor,
                stroke: opts.markerColor
              }
            },
            onRegionLabelShow: function(e, el, code) {
              if ( series && series[code] )
                el.html(el.html() + ': ' + series[code] + ' visitors');
            },
            markers: markers,
            series: {
                regions: [{
                    values: series,
                    scale: opts.scaleColors,
                    normalizeFunction: 'polynomial'
                }]
            },
          });
        }
  };
});
// To run this code, edit file 
// index.html or index.jade and change
// html data-ng-app attribute from
// angle to myAppName
// ----------------------------------- 

var myApp = angular.module('myAppName', ['angle']);

myApp.run(["$log", function($log) {

  $log.log('I\'m a line from custom.js');

}]);

myApp.config(["RouteHelpersProvider", function(RouteHelpersProvider) {

  // Custom Route definition
  
}]);

myApp.controller('oneOfMyOwnController', ["$scope", function($scope) {
  /* controller code */
}]);

myApp.directive('oneOfMyOwnDirectives', function() {
  /*directive code*/
});

myApp.config(["$stateProvider", function($stateProvider /* ... */) {
  /* specific routes here (see file config.js) */
}]);