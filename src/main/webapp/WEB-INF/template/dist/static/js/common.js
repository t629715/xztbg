export default{
    install(Vue,options){
      //  Vue.prototype.url= "http://www.baidu.com/"   //可以自定义变量
      //自定义方法 调用时 this.fun1
      Vue.prototype.fun1=function(){
        alert('hello1')
      };

      //判断是否为空
      Vue.prototype.isNotEmpty = function(str) {
          if (str != "" && str != null) {
            return true;
          } else {
            return false;
          }
      };

      //分转为圆，保留2位小数,为空返回0
      Vue.prototype.amountHandle1 = function(str) {
        return  Vue.prototype.isNotEmpty(str) ? (Number(str)/100).toFixed(2) : 0;
      };
      //分转为圆，保留2位小数,为空返回''
      Vue.prototype.amountHandle2 = function(str) {
        return  Vue.prototype.isNotEmpty(str) ? (Number(str)/100).toFixed(2) : '';
      };
      Vue.prototype.dateFormat = function(date) {
        var dateobj = new Date(date);
        var y = dateobj.getFullYear();
        var m = dateobj.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = dateobj.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = dateobj.getHours();
        h = h < 10 ? ('0' + h) : h;
        var minute = dateobj.getMinutes();
        var second = dateobj.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
      };

//控制页面所有路由开始之前结束之后的方法
      Vue.http.interceptors.push(function ( request, next ) {
    // 请求发送前的处理逻辑
            next(function (response) {
                // 请求发送后的处理逻辑
                // 更具请求的状态， response参数会返回给 successCallback或errorCallback
                    if(response.data.result == undefined){
                        this.$router.push('/')
                    }
//              return response
            });

        });
    }
}
