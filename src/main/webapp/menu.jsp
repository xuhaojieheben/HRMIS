 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="static/js/jquery-2.2.3.min.js"></script>
 <div id="tree-wrap">
     <div id="menus" class="side-bar">
     </div>
 </div>
 <script type="text/javascript">
 	$(function () {
 		$.ajax({  
 	        url : "menu/powerManage.do",  
 	        type: "POST",
 	        dataType: "json",
 	        contentType: "application/json",
 	        success: function(data) {
        		if(data.length > 0){
        			var rootUl = '<ul class="topnav menu-left-nest">';
        			var menuHtml = "";
        			var _menuLi = "";
        			for (var i = 0; i < data.length; i++) {
        				var menuLi = '<li><a href="#" style="border-left:0px solid!important;" class="title-menu-left"><span class="">' + data[i].menuTITLE + '</span><i data-toggle="tooltip" class="entypo-cog pull-right config-wrap"></i></a></li>';
        				rootUl += menuLi;
        				if(data[i].menuChild.length > 0){
                        	var root = '<li><a class="tooltip-tip ajax-load" href="#" title="Blog App"><i class="icon-document-edit"></i><span>{0}</span></a><ul>{1}</ul></li>'
                        	menuHtml = "";
                        	for (var j = 0; j < data[i].menuChild.length; j++) {
                        		var _liMenu = '<li onclick="changTitle(\'{0}\', \'{1}\')"><a class="tooltip-tip2 ajax-load" href="{2}" title="{3}"  target="main"><i class="entypo-doc-text"></i><span>{4}</span></a></li>';
                        		menuHtml += _liMenu.format(data[i].menuTITLE,data[i].menuChild[j].menuTITLE,data[i].menuChild[j].menuURL, data[i].menuChild[j].menuTITLE,data[i].menuChild[j].menuTITLE);
							}
                        	_menuLi = root.format(data[i].menuTITLE,menuHtml);
                        	rootUl += _menuLi;
                        }
					}
        			$("#menus").append(rootUl + '</ul>');
        		}
 	        },  
 	        error : function(data) {  
 	            console.log(data);
 	        }  
 	    });
 	});
 	
 	function changTitle(root, title){
		$("#home").attr('title',root);
		$("#page").attr('title',title);
		$("#home").text(root);
		$("#page").text(title);
 	}
 	
 	String.prototype.format = function () {
 	    var args = arguments;
 	    return this.replace(/\{(\d+)\}/g,
 	    function (m, i) {
 	        return args[i];
 	    });
 	}

 	String.format = function () {
 	    if (arguments.length == 0)
 	        return null;
 	    var str = arguments[0];
 	    for (var i = 1; i < arguments.length; i++) {
 	        var re = new RegExp('\\{' + (i - 1) + '\\}', 'gm');
 	        str = str.replace(re, arguments[i]);
 	    }
 	    return str;
 	}
 </script>