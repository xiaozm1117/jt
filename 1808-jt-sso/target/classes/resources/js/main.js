
;(function () {
	

	'use strict';

	// Placeholder
	var placeholderFunction = function() {
		$('input, textarea').placeholder({ customClass: 'my-placeholder' });
	}
	
	// Placeholder
	var contentWayPoint = function() {
		var i = 0;
		$('.animate-box').waypoint( function( direction ) {

			if( direction === 'down' && !$(this.element).hasClass('animated-fast') ) {
				
				i++;

				$(this.element).addClass('item-animate');
				setTimeout(function(){

					$('body .animate-box.item-animate').each(function(k){
						var el = $(this);
						setTimeout( function () {
							var effect = el.data('animate-effect');
							if ( effect === 'fadeIn') {
								el.addClass('fadeIn animated-fast');
							} else if ( effect === 'fadeInLeft') {
								el.addClass('fadeInLeft animated-fast');
							} else if ( effect === 'fadeInRight') {
								el.addClass('fadeInRight animated-fast');
							} else {
								el.addClass('fadeInUp animated-fast');
							}

							el.removeClass('item-animate');
						},  k * 200, 'easeInOutExpo' );
					});
					
				}, 100);
				
			}

		} , { offset: '85%' } );
	};
	
	// On load
	$(function(){
		placeholderFunction();
		contentWayPoint();
		
	});

}());

$("input[value='Sign In']").click(function () {
    
    var _username = $(".fh5co-form [id=username]").val();
    var _password = $(".fh5co-form [id=password]").val();
    $.ajax({
        type: "POST",
        //url: "/service/user/doLogin?r=" + Math.random(),
        url:"/user/login",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: {u:_username,p:_password},
        dataType : "json",
        error: function () {
//            $("#nloginpwd").attr({ "class": "text highlight2" });
//            $("#loginpwd_error").html("网络超时，请稍后再试").show().attr({ "class": "error" });
//            $("#loginsubmit").removeAttr("disabled");
//            $this.removeAttr("disabled");
        },
        success: function (result) {
            if (result) {
                var obj = eval(result);
                if (obj.status == 200) {
                	obj.success = "http://localhost:9001/result/{login success!!}";
                    var isIE = !-[1,];
                    if (isIE) {
                        var link = document.createElement("a");
                        link.href = obj.success;
                        link.style.display = 'none';
                        document.body.appendChild(link);
                        link.click();
                    } else {
                        window.location = obj.success;
                    }
                    return;
                }else{
//                $("#loginsubmit").removeAttr("disabled");
//                verc();
//                  $("#nloginpwd").attr({ "class": "text highlight2" });
//                  $("#loginpwd_error").html("账号或密码错误!").show().attr({ "class": "error" });
//                	
                }

            }
        }
    });
})
$("input[value='Sign Up']").click(function () {
	
	var _username = $(".fh5co-form [id=name]").val();
	var _password = $(".fh5co-form [id=password]").val();
	var _email=$(".fh5co-form [id=email]").val();
	var _phone=$(".fh5co-form [id=phone]").val();
	$.ajax({
		type: "POST",
		//url: "/service/user/doLogin?r=" + Math.random(),
		url:"/user/register",
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		data: {username:_username,password:_password,email:_email,phone:_phone},
		dataType : "json",
		error: function () {
//            $("#nloginpwd").attr({ "class": "text highlight2" });
//            $("#loginpwd_error").html("网络超时，请稍后再试").show().attr({ "class": "error" });
//            $("#loginsubmit").removeAttr("disabled");
//            $this.removeAttr("disabled");
		},
		success: function (result) {
			if (result) {
				var obj = eval(result);
				if (obj.status == 200) {
					obj.success = "http://localhost:9001/result/{register success!!}";
					var isIE = !-[1,];
					if (isIE) {
						var link = document.createElement("a");
						link.href = obj.success;
						link.style.display = 'none';
						document.body.appendChild(link);
						link.click();
					} else {
						window.location = obj.success;
					}
					return;
				}else{
//                $("#loginsubmit").removeAttr("disabled");
//                verc();
//                  $("#nloginpwd").attr({ "class": "text highlight2" });
//                  $("#loginpwd_error").html("账号或密码错误!").show().attr({ "class": "error" });
//                	
				}
				
			}
		}
	});
})


