/**
 * 功能：APP健康照顾方案控制器
 * 作者：LGP
 * 时间：2016年9月1日
 */

var app = angular.module('myApp', ['infinite-scroll']);
app.controller('healthPlanController', function($scope, Plan) 
{
     $scope.demo = new Plan();
});
var hlp_pageNo = 1;
var hlp_pageSize = 10;
var hlp_category_id = $.trim($("#app_categoryid").val());
app.factory('Plan', function($http) 
{
    var Plan = function() 
	{
         this.items = [];
    };
    Plan.prototype.loadMore = function() 
    {
        var url = ROUTER_SERVER_URI + "/sltRouter?method=getProductByCategory&jsoncallback=JSON_CALLBACK&dataType=app&cId="+hlp_category_id+"&pageNo="+hlp_pageNo+"&pageSize="+hlp_pageSize;
        $http.jsonp(url).success(function(data) 
        {
        	hlp_pageNo += 1;
        	var o = data.list;
        	var l = o.length;
        	if(l == 0)
        	{
        		$alert("warn","没有更多商品可以显示了！");
        		return;
        	}
        	for(var i in o)
            {
                 this.items.push(o[i]);
            }
        }
        .bind(this));
    };
    Plan.prototype.addCart = function(id)
    {
    	addTao(1, id);
    };
    Plan.prototype.view = function(id, name)
    {
    	product(id, name);
    };
    return Plan;
});