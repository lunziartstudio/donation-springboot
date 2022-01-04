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
	$("#greetings").html("");
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
			let image = '<div id="img1" style="width:80px;height:80px;display:none;background-color:red;"></div><br>';
			showGreeting(image);
			// showGreeting(JSON.parse(greeting.body).content);
		});
		callback(name);
	});
}

function disconnect() {
	if (stompClient !== null) {
		stompClient.disconnect();
	}
	setConnected(false);
	console.log("Disconnected");
}

function sendRequest(name) {
	// logic 
	setInterval(function() {
		fetch('http://localhost:8080/is-there-a-new-donation')
			.then(function(response) {
				return response.json();
			})
			.then(function(myJson) {
				if (myJson == true) {
					// pop up
					// query database donate-queue
					console.log("thereIsNewDonator");
					stompClient.send("/app/hello", {}, JSON.stringify(name));
				}
			});
	}, 5000);
}

function showGreeting(message) {
	// $("#greetings").append("<tr><td>" + message + "</td></tr>");
	console.log("showGreeting()");
	$("#greetings").append(message);
	$("#img1").fadeIn(2500);

	function myFunc() {
		setTimeout(function() {
			// document.getElementById("img1").style.display = "none";
			$("#img1").fadeOut(2500);
		}, 5);
	}
	myFunc();
}

$(function() {
	$("form").on('submit', function(e) {
		e.preventDefault();
	});
	$("#connect").click(function() { connect(); });
	$("#disconnect").click(function() { disconnect(); });
	$("#send").click(function() { sendName(); });
});

