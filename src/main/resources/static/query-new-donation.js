/*
let stompClient = null;

function setConnected(connected) {
	$("#connect").prop("disabled", connected);
	$("#disconnect").prop("disabled", !connected);
	if (connected) {
		$("#conversation").show();
	}
	else {
		$("#conversation").hide();
	}
	$("#donation-img").html("");
}

function connect(name, callback) {
	let socket = new SockJS('/gs-guide-websocket');
	stompClient = Stomp.over(socket);
	stompClient.debug = null;
	stompClient.connect({}, function(frame) {
		setConnected(true);
		// console.log('Connected: ' + frame);
		stompClient.subscribe('/topic/greetings', function(greeting) {
			if (greeting != null) {
				console.log("receive2");
			}
			// let image = '<div id="img1" style="width:80px;height:80px;display:none;background-color:red;"></div><br>';
			let image = '<img src="./image/doge.jpg"; display:none; height="100%">'
			showGreeting(image);
			// showGreeting(JSON.parse(greeting.body).content);
		});
		callback(name);
	});
}
*/

function sendRequest(streamer) {
	fetch('http://localhost:8080/is-there-a-new-donation?streamer=' + streamer)
		.then(function(response) {
			if (response.status == 200) {
				return response.json();
			}
			else {
				return null;
			}
		})
		.then(function(json) {
			if (json != null) {
				// console.log(json);
				let image = '<img src="./image/doge.jpg"; display:none; style="height: auto; max-width: 60%; display: block; margin-left: auto; margin-right: auto;" height="100%">'
				let nameAndAmount = `<p id="donation-text" style="margin: 15px 0 0 0; text-align: center; -webkit-text-stroke: 0.5px black; color: white;font-size: 30px;">感謝 ${json.name} 贊助的 ${json.amount} 元!</p>`;
				let message = `<p id="donation-text" style=" text-align: center; padding-left: 100px; padding-right: 100px; -webkit-text-stroke: 0.5px black; color: white; margin-top: 0px; font-size: 30px;">${json.message}</p>`;
				refresh(image + nameAndAmount + message);
			}
		});
}

function refresh(content) {
	$("#donation-content").replaceWith(content);
	$("#donation").each(function() {
		$(this).hide();
		$(this).delay(1250).fadeIn(1000).delay(7000).fadeOut(1250);
	});
}

