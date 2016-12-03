var brower = 
{
	versions:function()
	{
		var u = window.navigator.userAgent;
		var num ;
		if(u.indexOf('Trident') > -1)
		{
			return "IE";
		}
		else if(u.indexOf('Presto') > -1)
		{
			return "Opera";
		}
		else if(u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1)
		{
			return "Firefox";
		}
		else if(u.indexOf('AppleWebKit' && u.indexOf('Safari') > -1) > -1)
		{
			if(u.indexOf('Chrome') > -1)
			{
				return "Chrome";
			}
			else if(u.indexOf('OPR'))
			{
				return "Opera_webkit";
			}
			else
			{
				return "Safari";
			}
		}
		else if(u.indexOf('Mobile') > -1)
		{
			if(!!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/))
			{
				if(u.indexOf('iPhone') > -1)
				{
					return "iPhone";	
				}
				else if(u.indexOf('iPod') > -1)
				{
					return "iPod";
				}
				else if(u.indexOf('iPad') > -1)
				{
					return "iPad";
				}
			}
			else if(u.indexOf('Android') > -1 || u.indexOf('Linux') > -1)
			{
				num = u.substr(u.indexOf('Android') + 8, 3);
				return {"type":"Android", "version": num};
			}
			else if(u.indexOf('BB10') > -1 )
			{
				return "BB10";//黑莓bb10系统
			}
			else if(u.indexOf('IEMobile'))
			{
				return "Windows Phone";
			}
		}
	}
};
	
