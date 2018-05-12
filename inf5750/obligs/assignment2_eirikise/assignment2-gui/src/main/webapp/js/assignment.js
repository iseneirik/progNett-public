function getStudentData() {
    $.getJSON("/api/student", function (json) {
        populateStudentTable(json);
        populateStudentLocationForm(json);
    });
    initialize_map();
}

function populateStudentTable(json) {
	var formString = '';
    for (var s = 0; s < json.length; s++) {
        var student = explodeJSON(json[s]);

        var location = student.latitude != null
            ? student.latitude + ", " + student.longitude
            : "No Location";

        var courses = '';
        for (var j = 0; j < student.courses.length; j++) {
            courses += student.courses[j].courseCode + " ";
        }

        formString += '<tr>' +
            '<td>' + student.name + '</td>' +
            '<td>' + courses + '</td>' +
            '<td>' + location + '</td>' +
        '</tr>'

        var myLatlng = new google.maps.LatLng(student.latitude, student.longitude);
        var marker = new google.maps.Marker({
            position: myLatlng,
            map: map,
            title: student.name
        });
    }
    $('#studentTable').append(formString);
}

function populateStudentLocationForm(json) {
	var formString = '<tr><td><select id="selectedStudent" name="students">';
	for (var s = 0; s < json.length; s++) {
		var student = json[s];
		student = explodeJSON(student);
		formString += '<option value="' + student.id + '">' + student.name
				+ '</option>';
	}
	formString += '</select></td></tr>';
	
	$('#studentLocationTable').append(formString);

}

$('#locationbtn').on('click', function(e) {
	e.preventDefault();
	get_location();
});

// This function gets called when you press the Set Location button
function get_location() {
    if (Modernizr.geolocation) {
        navigator.geolocation.getCurrentPosition(location_found);
    } else {
        console.log("SOMETHING WENT WRONG!");
    }
}

// Call this function when you've succesfully obtained the location. 
function location_found(position) {
    var latitude = position.coords.latitude;
    var longitude = position.coords.longitude;
    var studentId = $("#selectedStudent").val();
    $.ajax({
        url: "/api/student/" + studentId + "/location?" +
            "lat=" + latitude + "&long=" + longitude
    }).then(function (json) {
        window.location.replace("/");
    });
}

var objectStorage = new Object();

function explodeJSON(object) {
	if (object instanceof Object == true) {
		objectStorage[object['@id']] = object;
		console.log('Object is object');
	} else {
		console.log('Object is not object');
		object = objectStorage[object];
		console.log(object);
	}
	console.log(object);
	return object;
}

var map;
function initialize_map() {
    var mapOptions = {
        zoom : 10,
        mapTypeId : google.maps.MapTypeId.ROADMAP
    };
    map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
    // Try HTML5 geolocation
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            var pos = new google.maps.LatLng(position.coords.latitude,
                position.coords.longitude);
            map.setCenter(pos);
        }, function() {
            handleNoGeolocation(true);
        });
    } else {
        // Browser doesn't support Geolocation
        // Should really tell the user…
    }
}