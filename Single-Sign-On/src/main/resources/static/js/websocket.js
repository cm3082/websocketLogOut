var lockReconnect = false;// 避免重复连接
var host = window.location.host;
var wsUrl = "wss://" + host + "/websocket";
var ws;
var tt;
function createWebSocket() {
	try {
		if (!ws) {
			ws = new WebSocket(wsUrl);
			init();
		}
	} catch (e) {
		reconnect(wsUrl);
	}
}
function init() {
	ws.onclose = function() {
		reconnect(wsUrl);
	};
	ws.onerror = function() {
		reconnect(wsUrl);
	};
	ws.onopen = function() {
		// 心跳检测重置
		heartCheck.start();
	};
	ws.onmessage = function(event) {
		// 拿到任何消息都说明当前连接是正常的
		if (event.data.indexOf("你的账号在别处登录") != -1) {
			layer.msg("你的账号在别处登录", {
				btn : [ '明白了' ]
			}, function() {
				window.location.href = 'login';
			});

		}
		heartCheck.start();
	}
}
function reconnect(url) {
	if (lockReconnect) {
		return;
	}
	lockReconnect = true;
	// 没连接上会一直重连，设置延迟避免请求过多
	tt && clearTimeout(tt);
	tt = setTimeout(function() {
		createWebSocket(url);
		lockReconnect = false;
	}, 4000);
}
// 心跳检测
var heartCheck = {
	timeout : 180000,
	timeoutObj : null,
	serverTimeoutObj : null,
	start : function() {
		var self = this;
		this.timeoutObj && clearTimeout(this.timeoutObj);
		this.serverTimeoutObj && clearTimeout(this.serverTimeoutObj);
		this.timeoutObj = setTimeout(function() {
			// 这里发送一个心跳，后端收到后，返回一个心跳消息，
			ws.send("webSocket heartCheck");
			self.serverTimeoutObj = setTimeout(function() {
				ws.close();
			}, self.timeout);

		}, this.timeout)
	}
}
createWebSocket();
window.onbeforeunload = function() {
	ws.close();
}
