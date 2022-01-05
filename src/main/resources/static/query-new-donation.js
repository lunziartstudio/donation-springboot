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

function sendRequest(name) {
	fetch('http://localhost:8080/is-there-a-new-donation')
		.then(function(response) {
			return response.json();
		})
		.then(function(json) {
			if (json == true) {
				console.log("thereIsANewDonation");
				let image = '<img src="./image/doge.jpg"; display:none; style="height: auto; max-width: 60%; display: block; margin-left: auto; margin-right: auto;" height="100%">'
				// let text = `<p id="donation-text">感謝</p>`;
				let text = `<p id="donation-text" style="padding-left: 100px; padding-right: 100px; -webkit-text-stroke: 0.5px black; color: white; margin-top:5px; font-size: 30px;">感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內感謝斗內</p>`;
				refresh(image + text);
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

